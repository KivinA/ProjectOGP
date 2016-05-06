package hillbillies.test.classes;

import static org.junit.Assert.*;
import org.junit.*;
import hillbillies.model.*;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class LogTest {

	private static World world;
	private Log log;
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
		log = new Log(POSITION, DEFAULT_WEIGHT, world);
		world.addLog(log);
	}
	
	@After
	public void tearDown() throws Exception
	{
		if (!log.isTerminated())
			log.terminate();
		world.removeLog(log);
	}
	
	@Test
	public void constructor_SingleCase()
	{
		double[] position = {0.5, 0.5, 0.5};
		int weight = 45;
		Log theLog = new Log(position, weight, world);
		assertArrayEquals(position, theLog.getPosition(), 0);
		assertEquals(weight, theLog.getWeight());
		assertSame(world, theLog.getWorld());
	}
	
	@Test
	public void testIsFalling()
	{
		double[] position = {2.5, 2.5, 2.5};
		double[] expectedPosition = {2.5, 2.5, 0.5};
		Log theLog = new Log(position, DEFAULT_WEIGHT, world);
		theLog.advanceTime();
		theLog.advanceTime();
		assertArrayEquals(expectedPosition, theLog.getPosition(), 0);
	}
	
	@Test
	public void terminate_LegalCase()
	{
		double[] position = {0.5, 0.5, 0.5};
		Log theLog = new Log(position, DEFAULT_WEIGHT, world);
		theLog.terminate();
		assertTrue(theLog.isTerminated());
		assertNull(theLog.getPosition());
		assertNull(theLog.getWorld());
	}
	
	@Test (expected = IllegalStateException.class)
	public void terminate_AlreadyTerminated() throws Exception
	{
		log.terminate();
		log.terminate();
	}
	
	@Test
	public void canHaveAsWorld_EffectiveWorld()
	{
		assertTrue(log.canHaveAsWorld(world));
	}
	
	@Test
	public void canHaveAsWorld_NonEffectiveWorldTerminatedLog()
	{
		log.terminate();
		assertTrue(log.canHaveAsWorld(null));
	}
	
	@Test
	public void canHaveAsWorld_NonEffectiveWorld()
	{
		assertFalse(log.canHaveAsWorld(null));
	}
	
	@Test
	public void canHaveAsWorld_EffectiveWorldTerminatedLog()
	{
		log.terminate();
		assertFalse(log.canHaveAsWorld(world));
	}
	
	@Test
	public void hasProperWorld_TrueCase()
	{
		assertTrue(log.hasProperWorld());
	}
	
	@Test
	public void hasProperWorld_LogNotAddedToWorld()
	{
		Log theLog = new Log(POSITION, DEFAULT_WEIGHT, world);
		assertFalse(theLog.hasProperWorld());
	}
	
	// We don't specify a legal case for setWorld, because the only legal case is either in the constructor or in the terminate method.
	@Test (expected = IllegalStateException.class)
	public void setWorld_AlreadyHasWorld() throws Exception
	{
		log.setWorld(world);
	}
	
	@Test
	public void isValidWeight_TrueCase()
	{
		assertTrue(Log.isValidWeight(15));
	}
	
	@Test
	public void isValidWeight_FalseCase()
	{
		assertFalse(Log.isValidWeight(95));
	}
	
	@Test
	public void canHaveAsPosition_EffectivePosition()
	{
		int[] coordinates = {0, 0, 0};
		assertTrue(log.canHaveAsPosition(coordinates));
	}
	
	@Test
	public void canHaveAsPosition_NonEffectivePositionTerminatedLog()
	{
		log.terminate();
		assertTrue(log.canHaveAsPosition(null));
	}
	
	@Test
	public void canHaveAsPosition_NonEffectivePosition()
	{
		assertFalse(log.canHaveAsPosition(null));
	}
	
	@Test
	public void canHaveAsPosition_EffectivePositionTerminatedLog()
	{
		log.terminate();
		int[] coordinates = {0, 0, 0};
		assertFalse(log.canHaveAsPosition(coordinates));
	}
	
	@Test
	public void canHaveAsPosition_SolidCube()
	{
		int[] coordinates = {1, 1, 1};
		assertFalse(log.canHaveAsPosition(coordinates));
	}
	
	@Test
	public void canHaveAsPosition_OutOfBoundaries()
	{
		int[] coordinates = {5, 5, 5};
		assertFalse(log.canHaveAsPosition(coordinates));
	}
	
	@Test
	public void setPosition_LegalCase()
	{
		double[] coordinates = {0.5, 0.5, 0.5};
		log.setPosition(coordinates);
	}
	
	// This test exists because we needed to test whether or not the position can also be different from 0.5
	// Since there is no specification whether or not this should be possible, we didn't implement a checker to check if the given
	// position is in the middle of the cube.
	// However, insider our implementation we only use the middle of cubes. So this is actually just a trivial test.
	@Test
	public void setPosition_NotMiddle()
	{
		double[] coordinates = {0.8, 0.6, 0.7};
		log.setPosition(coordinates);
		assertArrayEquals(coordinates, log.getPosition(), 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setPosition_IllegalCase() throws Exception
	{
		double[] position = {5.5, 5.5, 5.5};
		log.setPosition(position);
	}
	
	@Test
	public void canHaveAsFallingState_TrueCasePassableCube()
	{
		double[] position = {2.5, 2.5, 2.5};
		log.setPosition(position);
		assertTrue(log.canHaveAsFallingState(true));
	}
	
	@Test
	public void canHaveAsFallingState_TrueCaseReachedLevelZero()
	{
		double[] position = {2.5, 2.5, 0.5};
		log.setPosition(position);
		assertTrue(log.canHaveAsFallingState(false));
	}
	
	@Test
	public void canHaveAsFallingState_TrueCaseSolidCubeBeneath()
	{
		double[] position = {1.5, 1.5, 2.5};
		log.setPosition(position);
		assertTrue(log.canHaveAsFallingState(false));
	}
	
	// It's no use stating false cases, since these are the only cases where the function will return true, thus all other calls will
	// return false.
	
	@Test (expected = IllegalArgumentException.class)
	public void setFalling_IllegalCase() throws Exception
	{
		log.setFalling(true);
	}
}
