package hillbillies.test.classes;

import static org.junit.Assert.*;
import org.junit.*;
import hillbillies.model.*;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class UnitTest {

	private static World world;
	private Unit unit;
	private final static int TYPE_AIR = 0;
	private final static int TYPE_ROCK = 1;
	private final static int TYPE_LOG = 2;
	private final static int TYPE_WORKSHOP = 3;
	private final static int DEFAULT_PROPERTY = 30;
	private final static int[] POSITION = {1, 1, 1};
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		int[][][] terrain = new int[3][3][3];
		terrain[1][1][0] = TYPE_ROCK;
		terrain[0][0][0] = TYPE_LOG;
		terrain[1][0][2] = TYPE_ROCK;
		terrain[2][2][2] = TYPE_WORKSHOP;
		terrain[2][2][1] = TYPE_LOG;
		
		world = new World(terrain, new DefaultTerrainChangeListener());
	}

	@Before
	public void setUp() throws Exception 
	{
		unit = new Unit("Hillbilly", DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, 
				POSITION, false, world);
		world.addUnit(unit);
	}

	@After
	public void tearDown() throws Exception 
	{
		unit.die();
		world.removeUnit(unit);
	}

	@Test
	public void isValidInitialValue_TrueCase()
	{
		assertTrue(Unit.isValidInitialValue(25));
		assertTrue(Unit.isValidInitialValue(50));
		assertTrue(Unit.isValidInitialValue(75));
		assertTrue(Unit.isValidInitialValue(100));
	}
	
	@Test
	public void isValidInitialValue_FalseCase()
	{
		assertFalse(Unit.isValidInitialValue(10));
		assertFalse(Unit.isValidInitialValue(110));
	}
	
	@Test
	public void isValidPropertyValue_TrueCase()
	{
		assertTrue(Unit.isValidPropertyValue(1));
		assertTrue(Unit.isValidPropertyValue(15));
		assertTrue(Unit.isValidPropertyValue(125));
		assertTrue(Unit.isValidPropertyValue(150));
		assertTrue(Unit.isValidPropertyValue(175));
		assertTrue(Unit.isValidPropertyValue(200));
	}
	
	@Test
	public void isValidPropertyValue_FalseCase()
	{
		assertFalse(Unit.isValidPropertyValue(-10));
		assertFalse(Unit.isValidPropertyValue(0));
		assertFalse(Unit.isValidPropertyValue(201));
		assertFalse(Unit.isValidPropertyValue(215));
	}
	
	@Test
	public void isValidName_TrueCase()
	{
		assertTrue(Unit.isValidName("Hillbilly"));
		assertTrue(Unit.isValidName("Kevin"));
		assertTrue(Unit.isValidName("Jeroen Depuydt"));
		assertTrue(Unit.isValidName("James O' Haraha"));
		assertTrue(Unit.isValidName("HILLBILLY\""));
	}
}
