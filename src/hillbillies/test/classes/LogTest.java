package hillbillies.test.classes;

import static org.junit.Assert.*;
import org.junit.*;
import hillbillies.model.*;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import ogp.framework.util.ModelException;

public class LogTest {
	
	private static World world;
	private Log log1;
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
	public void logPosition_LegalCase() throws ModelException
	{
		log1 = new Log(null, DEFAULT_WEIGHT, world);
		
		double[] position = {0.5, 0.5, 0.5};
		log1.setPosition(position);
		assertEquals(position, log1.getPosition());
		
		log1.setPosition(null);
		assertEquals(null, log1.getPosition());
	}
	
	@Test (expected = ModelException.class)
	public void logPosition_IllegalCase() throws ModelException
	{
		executeAfterMethod = false;
		double[] position = {1.5, 1.5, 1.5};
		log1 = new Log(position, DEFAULT_WEIGHT, world);
	}
	
	@Test
	public void weight_LegalCase1() throws ModelException
	{
		int weight = 10;
		log1 = new Log(null, weight, world);
		assertEquals(weight, log1.getWeight());
	}
	
	@Test
	public void weight_LegalCase2() throws ModelException
	{
		int weight = 50;
		log1 = new Log(null, weight, world);
		assertEquals(weight, log1.getWeight());
	}
	
	@Test (expected = ModelException.class)
	public void weight_IllegalCase() throws ModelException
	{
		executeAfterMethod = false;
		int weight = 70;
		log1 = new Log(null, weight, world);
	}
	
	@Test
	public void logIsFallingTrue() throws ModelException
	{
		double[] position = {2.5, 2.5, 2.5};
		log1 = new Log(position, DEFAULT_WEIGHT, world);
		assertTrue(log1.canHaveAsIsFalling(true));
	}
	
	@Test
	public void logIsFallingFalse()throws ModelException
	{
		double[] position = {0.5, 0.5, 0.5};
		log1 = new Log(position, DEFAULT_WEIGHT, world);
		assertTrue(log1.canHaveAsIsFalling(false));
	}
	
	@After
	public void reset() throws ModelException
	{
		if (executeAfterMethod)
		{
			log1.setWorld(null);
			world.removeLog(log1);
			log1 = null;
		}
	}
}
