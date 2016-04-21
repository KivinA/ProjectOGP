package hillbillies.test.classes;

import static org.junit.Assert.*;
import org.junit.*;
import hillbillies.model.*;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import ogp.framework.util.ModelException;

public class BoulderTest {
	private static World world;
	private Boulder boulder;
	private static int TYPE_ROCK = 1;
	private static int TYPE_LOG = 2;
	private static int TYPE_WORKSHOP = 3;
	private static int DEFAULT_WEIGHT = 30;
	private static boolean executeAfterMethod;
	
	@BeforeClass
	public static void setUpBeforeClass() throws ModelException
	{
		int[][][] terrain = new int[3][3][3];
		terrain[1][1][0] = TYPE_WORKSHOP;
		terrain[1][1][1] = TYPE_LOG;
		terrain[1][1][2] = TYPE_ROCK;
		terrain[1][0][1] = TYPE_LOG;
		terrain[1][0][2] = TYPE_ROCK;
		
		world = new World(terrain, new DefaultTerrainChangeListener());
		executeAfterMethod = true;
	}
	
	@Before
	public void setUp()
	{
		if (!executeAfterMethod)
			executeAfterMethod = true;
	}
	
	@Test
	public void boulderPosition_LegalCase() throws ModelException
	{
		double[] position = {0.5, 0.5, 0.5};
		boulder = new Boulder(position, DEFAULT_WEIGHT, world);
		assertEquals(position, boulder.getPosition());
		
		position = null;
		boulder.setPosition(null);
		assertEquals(position, boulder.getPosition());
	}
	
	@Test (expected = ModelException.class)
	public void boulderPosition_IllegalCase() throws ModelException
	{
		executeAfterMethod = false;
		double[] position = {1.5, 1.5, 1.5}; 
		boulder = new Boulder(position, DEFAULT_WEIGHT, world);
	}
	
	@Test
	public void weight_LegalCase1() throws ModelException
	{
		int weight = 10;
		boulder = new Boulder(null, weight, world);
		assertEquals(weight, boulder.getWeight());
	}
	
	@Test
	public void weight_LegalCase2() throws ModelException
	{
		int weight = 50;
		boulder = new Boulder(null, weight, world);
		assertEquals(weight, boulder.getWeight());
	}
	
	@Test (expected = ModelException.class)
	public void weight_IllegalCase() throws ModelException
	{
		executeAfterMethod = false;
		int weight = 80;
		boulder = new Boulder(null, weight, world);
	}
	
	@Test
	public void boulderIsFallingTrue() throws ModelException
	{
		double[] position = {2.5, 2.5, 2.5};
		boulder = new Boulder(position, DEFAULT_WEIGHT, world);
		assertTrue(boulder.canHaveAsIsFalling(true));
	}
	
	@Test
	public void boulderIsFallingFalse() throws ModelException
	{
		double[] position = {0.5, 0.5, 0.5};
		boulder = new Boulder(position, DEFAULT_WEIGHT, world);
		assertTrue(boulder.canHaveAsIsFalling(false));
	}
	
	@After
	public void reset() throws ModelException
	{
		if (executeAfterMethod)
		{
			boulder.setWorld(null);
			world.removeBoulder(boulder);
			boulder = null;
		}
	}
}
