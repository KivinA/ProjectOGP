package hillbillies.test.classes;

import static org.junit.Assert.*;
import org.junit.*;
import hillbillies.model.*;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class BoulderTest {
	
	private static World world;
	private Boulder boulder;
	private static int TYPE_ROCK = 1;
	private static int TYPE_LOG = 2;
	private static int TYPE_WORKSHOP = 3;
	private static int DEFAULT_WEIGHT = 30;
	private final double[] POSITION = {1.5, 0.5, 2.5};
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		int[][][] terrain = new int[3][3][3];
		terrain[1][1][0] = TYPE_WORKSHOP;
		terrain[1][1][1] = TYPE_ROCK;
		terrain[1][0][1] = TYPE_LOG;
		
		world = new World(terrain, new DefaultTerrainChangeListener());
	}
	
	@Before
	public void setUp() throws Exception
	{
		boulder = new Boulder(POSITION, DEFAULT_WEIGHT, world);
		world.addBoulder(boulder);
	}
	
	@After
	public void tearDown() throws Exception
	{
		if (!boulder.isTerminated())
			boulder.terminate();
		world.removeBoulder(boulder);
	}
	
	@Test
	public void constructor_SingleCase()
	{
		double[] position = {0.5, 0.5, 0.5};
		int weight = 21;
		Boulder theBoulder = new Boulder(position, weight, world);
		assertArrayEquals(position, theBoulder.getPosition(), 0);	
		assertEquals(weight, theBoulder.getWeight());
		assertSame(world, theBoulder.getWorld());
	}
	
	@Test 
	public void testIsFalling()
	{
		double[] position = {2.5, 2.5, 2.5};
		double[] expectedPosition = {2.5, 2.5, 0.5};
		Boulder theBoulder = new Boulder(position, DEFAULT_WEIGHT, world);
		theBoulder.advanceTime();
		theBoulder.advanceTime();
		assertArrayEquals(expectedPosition, theBoulder.getPosition(), 0);
	}
	
	@Test
	public void terminate_LegalCase()
	{
		double[] position = {0.5, 0.5, 0.5};
		Boulder theBoulder = new Boulder(position, DEFAULT_WEIGHT, world);
		theBoulder.terminate();
		assertTrue(theBoulder.isTerminated());
		assertNull(theBoulder.getPosition());
		assertNull(theBoulder.getWorld());
	}
	
	@Test (expected = IllegalStateException.class)
	public void terminate_AlreadyTerminatedBoulder() throws Exception
	{
		boulder.terminate();
		boulder.terminate();
	}
	
	@Test
	public void canHaveAsWorld_EffectiveWorld()
	{
		assertTrue(boulder.canHaveAsWorld(world));
	}
	
	@Test
	public void canHaveAsWorld_NonEffectiveWorldTerminatedBoulder()
	{
		boulder.terminate();
		assertTrue(boulder.canHaveAsWorld(null));
	}
	
	@Test
	public void canHaveAsWorld_NonEffectiveWorld()
	{
		assertFalse(boulder.canHaveAsWorld(null));
	}
	
	@Test
	public void canHaveAsWorld_EffectiveWorldTerminatedBoulder()
	{
		boulder.terminate();
		assertFalse(boulder.canHaveAsWorld(world));
	}
	
	// We don't specify a legal case for setWorld, because the only legal case is either in the constructor or in the terminate method.
	@Test (expected = IllegalStateException.class)
	public void setWorld_AlreadyHasAWorld() throws Exception
	{
		boulder.setWorld(world);
	}
	
	@Test
	public void isValidWeight_TrueCase()
	{
		assertTrue(Boulder.isValidWeight(25));
	}
	
	@Test
	public void isValidWeight_FalseCase()
	{
		assertFalse(Boulder.isValidWeight(55));
	}
	
	@Test
	public void canHaveAsPosition_EffectivePosition()
	{
		int[] coordinates = {0, 0, 0};
		assertTrue(boulder.canHaveAsPosition(coordinates));
	}
	
	@Test
	public void canHaveAsPosition_NonEffectivePositionTerminatedBoulder()
	{
		boulder.terminate();
		assertTrue(boulder.canHaveAsPosition(null));
	}
	
	@Test
	public void canHaveAsPosition_NonEffectivePosition()
	{
		assertFalse(boulder.canHaveAsPosition(null));
	}
	
	@Test
	public void canHaveAsPosition_EffectivePositionTerminatedBoulder()
	{
		int[] coordinates = {0, 0, 0};
		boulder.terminate();
		assertFalse(boulder.canHaveAsPosition(coordinates));
	}
	
	@Test
	public void canHaveAsPosition_SolidCube()
	{
		int[] coordinates = {1, 0, 1};
		assertFalse(boulder.canHaveAsPosition(coordinates));
	}
	
	@Test
	public void canHaveAsPosition_OutOfBoundaries()
	{
		int[] coordinates = {5, 5, 5};
		assertFalse(boulder.canHaveAsPosition(coordinates));
	}
	
	@Test
	public void setPosition_LegalCase()
	{
		double[] coordinates = {0.5, 0.5, 0.5};
		boulder.setPosition(coordinates);
	}
	
	// This test exists because we needed to test whether or not the position can also be different from 0.5
	// Since there is no specification whether or not this should be possible, we didn't implement a checker to check if the given
	// position is in the middle of the cube.
	// However, insider our implementation we only use the middle of cubes. So this is actually just a trivial test.
	@Test
	public void setPosition_NotMiddle()
	{
		double[] coordinates = {0.8, 0.6, 0.7};
		boulder.setPosition(coordinates);
		assertArrayEquals(coordinates, boulder.getPosition(), 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setPosition_IllegalCase() throws Exception
	{
		boulder.setPosition(null);
	}
	
	@Test
	public void canHaveAsFallingState_TrueCasePassableCube()
	{
		double[] position = {2.5, 2.5, 2.5};
		boulder.setPosition(position);
		assertTrue(boulder.canHaveAsFallingState(true));
	}
	
	@Test
	public void canHaveAsFallingState_TrueCaseReachedLevelZero()
	{
		double[] position = {0.5, 0.5, 0.5};
		boulder.setPosition(position);
		assertTrue(boulder.canHaveAsFallingState(false));
	}
	
	@Test
	public void canHaveAsFallingState_TrueCaseSolidCubeBeneath()
	{
		double[] position = {1.5, 1.5, 2.5};
		boulder.setPosition(position);
		assertTrue(boulder.canHaveAsFallingState(false));
	}
	
	// It's no use stating false cases, since these are the only cases where the function will return true, thus all other calls will
	// return false.
	
	@Test (expected = IllegalArgumentException.class)
	public void setFalling_IllegalCase() throws Exception
	{
		boulder.setFalling(true);
	}
}
