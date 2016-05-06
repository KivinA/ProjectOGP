package hillbillies.test.classes;

import static org.junit.Assert.*;
import org.junit.*;
import hillbillies.model.*;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.part2.listener.TerrainChangeListener;
import ogp.framework.util.ModelException;

public class WorldTest 
{
//	private World world;
//	private int[][][] terrain;
//	private static TerrainChangeListener modelListener;
//	private static int TYPE_AIR = 0;
//	private static int TYPE_ROCK = 1;
//	private static int TYPE_LOG = 2;
//	private static int TYPE_WORKSHOP = 3;
//	
//	
//	@BeforeClass
//	public static void setUpBefore()
//	{
//		modelListener = new DefaultTerrainChangeListener();
//	}
//	
//	private void initializeCorrectWorld() throws ModelException
//	{
//		terrain = new int[3][3][3];
//		terrain[1][1][1] = TYPE_ROCK;
//		terrain[2][1][1] = TYPE_ROCK;
//		terrain[0][0][1] = TYPE_LOG;
//		terrain[0][0][2] = TYPE_LOG;
//		terrain[1][1][2] = TYPE_WORKSHOP;
//		terrain[2][1][2] = TYPE_WORKSHOP;
//		world = new World(terrain, modelListener);
//	}
//	
//	@Test
//	public void legalTerrain()
//	{
//		terrain = new int[3][3][3];
//		assertTrue(World.isValidTerrain(terrain));
//		
//		terrain = new int[1][1][1];
//		assertTrue(World.isValidTerrain(terrain));
//		
//		terrain = new int[50][50][50];
//		assertTrue(World.isValidTerrain(terrain));
//		
//		terrain = new int[100][100][50];
//		assertTrue(World.isValidTerrain(terrain));
//		
//		terrain = new int[50][25][10];
//		assertTrue(World.isValidTerrain(terrain));
//	}
//	
//	@Test
//	public void illegalTerrain()
//	{
//		terrain = null;
//		assertFalse(World.isValidTerrain(terrain));
//		
//		terrain = new int[0][1][2];
//		assertFalse(World.isValidTerrain(terrain));
//		
//		terrain = new int[1][0][2];
//		assertFalse(World.isValidTerrain(terrain));
//		
//		terrain = new int[1][2][0];
//		assertFalse(World.isValidTerrain(terrain));
//		
//		terrain = new int[0][0][0];
//		assertFalse(World.isValidTerrain(terrain));
//	}
//	
//	@Test
//	public void testInitialization() throws ModelException
//	{
//		initializeCorrectWorld();
//		
//		assertEquals(TYPE_AIR, world.getCubeType(2, 0, 2));
//		assertEquals(TYPE_AIR, world.getCubeType(0, 0, 0));
//		assertEquals(TYPE_ROCK, world.getCubeType(1, 1, 1));
//		assertEquals(TYPE_ROCK, world.getCubeType(2, 1, 1));
//		assertEquals(TYPE_LOG, world.getCubeType(0, 0, 1));
//		assertEquals(TYPE_LOG, world.getCubeType(0, 0, 2));
//		assertEquals(TYPE_WORKSHOP, world.getCubeType(1, 1, 2));
//		assertEquals(TYPE_WORKSHOP, world.getCubeType(2, 1, 2));
//		assertEquals(modelListener, world.getModelListener());
//	}
//	
//	@Test (expected = ModelException.class)
//	public void testIllegalInitialization() throws ModelException
//	{
//		terrain = new int[3][3][3];
//		terrain[0][0][0] = 4;
//		terrain[1][1][1] = TYPE_LOG;
//		
//		world = new World(terrain, modelListener);
//	}
//	
//	@Test
//	public void testPassableAndSolidCubes() throws ModelException
//	{
//		initializeCorrectWorld();
//		
//		// TYPE_AIR
//		assertTrue(world.isPassableCube(0, 0, 0));
//		assertTrue(world.isPassableCube(2, 2, 2));
//		
//		// TYPE_WORKSHOP
//		assertTrue(world.isPassableCube(1, 1, 2));
//		assertTrue(world.isPassableCube(2, 1, 2));
//		
//		//TYPE_ROCK
//		assertTrue(world.isSolidCube(1, 1, 1));
//		assertTrue(world.isSolidCube(2, 1, 1));
//		
//		// TYPE_LOG
//		assertTrue(world.isSolidCube(0, 0, 1));
//		assertTrue(world.isSolidCube(0, 0, 2));
//	}
//	
//	@Test
//	public void testBoundaries() throws ModelException
//	{
//		initializeCorrectWorld();
//		
//		assertTrue(world.isBetweenBoundaries(0, 0, 0));
//		assertTrue(world.isBetweenBoundaries(1, 1, 1));
//		assertTrue(world.isBetweenBoundaries(0, 1, 0));
//		assertTrue(world.isBetweenBoundaries(0, 2, 2));
//		
//		assertFalse(world.isBetweenBoundaries(world.getNbCubesX(), world.getNbCubesY(), world.getNbCubesZ()));
//		assertFalse(world.isBetweenBoundaries(5, 0, 1));
//		assertFalse(world.isBetweenBoundaries(2, -4, 3));
//		assertFalse(world.isBetweenBoundaries(-1, 0, 0));
//	}
//	
//	@Test
//	public void testConnectionsToBorder() throws ModelException
//	{
//		terrain = new int[3][3][5];
//		terrain[1][1][2] = TYPE_ROCK;
//		terrain[0][0][0] = TYPE_ROCK;
//		terrain[1][1][4] = TYPE_ROCK;
//		world = new World(terrain, modelListener);
//		int[] notConnectedPosition = {1, 1, 2};
//		assertTrue(world.isConnectedToBorder(0, 0, 0));
//		assertTrue(world.isConnectedToBorder(1, 1, 4)); // This will be true because z level4 is the border z level of the World.
//		assertFalse(world.isConnectedToBorder(1, 1, 2));
//		// Test the list notConnected.
//		assertTrue(world.getAllNotConnected().get(0)[0] == notConnectedPosition[0] && world.getAllNotConnected().get(0)[1] == notConnectedPosition[1]
//				&& world.getAllNotConnected().get(0)[2] == notConnectedPosition[2]);
//	}
//	
//	@Test
//	public void testHasSolidNeighBouringCube() throws ModelException
//	{
//		terrain = new int[3][3][3];
//		terrain[1][2][2] = TYPE_ROCK;
//		world = new World(terrain, modelListener);
//		
//		assertTrue(world.hasSolidNeighbouringCube(2, 2, 2));
//		assertTrue(world.hasSolidNeighbouringCube(1, 1, 1));
//		assertTrue(world.hasSolidNeighbouringCube(2, 2, 1));
//		assertFalse(world.hasSolidNeighbouringCube(1, 0, 1));
//		assertFalse(world.hasSolidNeighbouringCube(1, 2, 0));
//		assertFalse(world.hasSolidNeighbouringCube(0, 0, 0));
//	}
//	
//	@Test
//	public void testUnitsOfWorld() throws ModelException
//	{
//		initializeCorrectWorld();
//		
//		Unit unit1 = new Unit("Hillbilly", 30, 30, 30, 30, new int[] {0,1,1} , false, world);
//		Unit unit2 = new Unit("Hillbilly", 50, 50, 50, 50, new int[] {0,1,2} , false, world);
//		Unit unit3 = null;
//		
//		assertTrue(world.canHaveAsUnit(unit1));
//		assertTrue(world.canHaveAsUnit(unit2));
//		assertFalse(world.canHaveAsUnit(unit3));
//		
//		world.addUnit(unit1);
//		assertEquals(world.getNbOfUnits(), 1);
//		assertTrue(world.hasAsUnit(unit1));
//		
//		world.addUnit(unit2);
//		assertEquals(world.getNbOfUnits(), 2);
//		assertTrue(world.hasAsUnit(unit2));
//		assertTrue(world.hasProperUnits());
//		
//		unit2.setWorld(null);
//		assertFalse(world.hasProperUnits());
//		assertTrue(world.canRemoveAsUnit(unit2));
//		world.removeUnit(unit2);
//		assertEquals(world.getNbOfUnits(), 1);
//		assertTrue(world.hasProperUnits());
//		
//		world.removeUnit(unit1);
//		assertEquals(world.getNbOfUnits(), 0);
//		assertTrue(world.hasProperUnits()); // This is trivial true , because there are no Units.
//	}
//
//	@Test (expected = ModelException.class)
//	public void spawnUnits_IllegalCaseTooManyUnits() throws ModelException
//	{
//		initializeCorrectWorld();
//		for (int i = 0; i < 101; i++)
//			world.spawnUnit(false);
//	}
//	
//	@Test
//	public void spawnUnit_LegalCase() throws ModelException
//	{
//		initializeCorrectWorld();
//		int amount = 50;
//		for (int i = 0; i < amount; i++)
//			world.spawnUnit(false);
//		
//		assertEquals(world.getNbOfUnits(), amount);
//	}
//	
//	@Test
//	public void testFactionsOfWorld() throws ModelException
//	{
//		initializeCorrectWorld();
//		
//		Faction faction1 = new Faction(world);
//		Faction faction2 = new Faction(world);
//		Faction faction3 = null;
//		Unit unit = new Unit("Hillbilly", 50, 50, 50, 50, new int[]{ 0, 1, 1}, false, world);
//		
//		assertTrue(world.canHaveAsFaction(faction1));
//		assertTrue(world.canHaveAsFaction(faction2));
//		assertFalse(world.canHaveAsFaction(faction3));
//		
//		world.addFaction(faction1);
//		assertEquals(world.getNbOfFactions(), 1);
//		world.addFaction(faction2);
//		assertTrue(world.hasAsFaction(faction2));
//		assertTrue(world.hasProperFactions());
//		
//		assertTrue(world.canRemoveAsFaction(faction1));
//		assertTrue(world.canRemoveAsFaction(faction2));
//		world.removeFaction(faction2);
//		unit.setFaction(faction1);
//		assertFalse(world.canRemoveAsFaction(faction1));
//		faction1.removeUnit(unit);
//		assertTrue(world.canRemoveAsFaction(faction1));
//		world.removeFaction(faction1);
//		assertEquals(world.getNbOfFactions(), 0);
//	}
//
//	@Test (expected = ModelException.class)
//	public void spawnFactions_IllegalCaseTooManyFactions() throws ModelException
//	{
//		initializeCorrectWorld();
//		
//		for (int i = 0; i < 6; i++)
//			world.addFaction(new Faction(world));
//	}
//
//	@Test // See also LogTest.java
//	public void testLogsOfWorld() throws ModelException
//	{
//		initializeCorrectWorld();
//		
//		Log log1 = new Log(new double[]{0.5, 1.5, 1.5}, 30, world);
//		
//		assertTrue(World.isValidLog(log1));
//		assertTrue(world.hasAsLog(log1));
//		world.spawnLog(new double[]{0.5, 1.5, 2.5});
//		assertEquals(world.getAllLogs().size(), 2);
//		assertTrue(world.hasProperLogs());
//		
//		log1.setWorld(null);
//		assertTrue(world.canRemoveAsLog(log1));
//		world.removeLog(log1);
//	}
//	
//	@Test // See also BoulderTest.java
//	public void testBouldersOfWorld() throws ModelException
//	{
//		initializeCorrectWorld();
//		Boulder boulder = new Boulder(new double[]{0.5, 1.5, 1.5}, 30, world);
//		
//		assertTrue(World.isValidBoulder(boulder));
//		assertTrue(world.hasAsBoulder(boulder));
//		world.spawnBoulder(new double[]{0.5, 1.5, 2.5});
//		assertEquals(world.getAllBoulders().size(), 2);
//		assertTrue(world.hasProperBoulders());
//		
//		boulder.setWorld(null);
//		assertTrue(world.canRemoveAsBoulder(boulder));
//		world.removeBoulder(boulder);
//	}
}
