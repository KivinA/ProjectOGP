package hillbillies.test.classes;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.*;
import hillbillies.model.*;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.part2.listener.TerrainChangeListener;
import ogp.framework.util.ModelException;

public class WorldTest 
{
	private World world;
	private int[][][] terrain;
	private static TerrainChangeListener modelListener;
	private static final int TYPE_AIR = 0;
	private static final int TYPE_ROCK = 1;
	private static final int TYPE_LOG = 2;
	private static final int TYPE_WORKSHOP = 3;
	private static final int DEFAULT_PROPERTY = 30;
	private static final int[] POSITION = {1, 1, 2};
	
	@BeforeClass
	public static void setUpBeforeClass()
	{
		modelListener = new DefaultTerrainChangeListener();
	}
	
	@Before
	public void setUp() throws Exception
	{
		terrain = new int[5][5][5];
		terrain[1][1][1] = TYPE_ROCK;
		terrain[2][1][1] = TYPE_ROCK;
		terrain[0][0][1] = TYPE_ROCK;
		terrain[3][1][1] = TYPE_LOG;
		terrain[0][0][4] = TYPE_LOG;
		terrain[1][1][3] = TYPE_LOG;
		terrain[2][2][0] = TYPE_WORKSHOP;
		terrain[1][1][0] = TYPE_WORKSHOP;
		terrain[3][2][2] = TYPE_WORKSHOP;
		
		world = new World(terrain, modelListener);
	}
	
	@Test
	public void constructor_LegalCase()
	{
		int[][][] theTerrain = new int[5][5][5];
		theTerrain[0][0][0] = TYPE_ROCK;
		theTerrain[1][1][1] = TYPE_LOG;
		theTerrain[1][1][0] = TYPE_WORKSHOP;
		TerrainChangeListener theListener = new DefaultTerrainChangeListener();
		World theWorld = new World(theTerrain, theListener);
		
		assertSame(theListener, theWorld.getModelListener());
		assertEquals(theTerrain.length, theWorld.getNbCubesX());
		assertEquals(theTerrain[0].length, theWorld.getNbCubesY());
		assertEquals(theTerrain[0][0].length, theWorld.getNbCubesZ());
		assertEquals(0, theWorld.getLowerBoundary());
		assertEquals(1, theWorld.getCubeLength());
		assertEquals(TYPE_LOG, theWorld.getCubeType(1, 1, 1));
		assertEquals(TYPE_ROCK, theWorld.getCubeType(0, 0, 0));
		assertEquals(TYPE_WORKSHOP, theWorld.getCubeType(1, 1, 0));
		assertEquals(TYPE_AIR, theWorld.getCubeType(1, 2, 1));
	}
	
	@Test
	public void isValidTerrainChangeListener_EffectiveTerrainChangeListener()
	{
		assertTrue(World.isValidTerrainChangeListerener(new DefaultTerrainChangeListener()));
	}
	
	@Test
	public void isValidTerrainChangeListener_NonEffectiveTerrainChangeListener()
	{
		assertFalse(World.isValidTerrainChangeListerener(null));
	}
	
	@Test
	public void isConnectedToBorder_TrueCase()
	{
		assertTrue(world.isConnectedToBorder(0, 0, 1));
	}
	
	@Test
	public void isConnectedToBorder_FalseCase()
	{
		int[][][] theTerrain = new int[3][3][3];
		theTerrain[1][1][1] = TYPE_ROCK;
		World theWorld = new World(theTerrain, new DefaultTerrainChangeListener());
		assertFalse(theWorld.isConnectedToBorder(1, 1, 1));
	}
	
	@Test
	public void hasSolidNeighbouringCube_TrueCase()
	{
		assertTrue(world.hasSolidNeighbouringCube(1, 1, 1));
	}
	
	@Test
	public void hasSolidNeighBouringCube_FalseCase()
	{
		int[][][] theTerrain = new int[3][3][3];
		theTerrain[1][1][1] = TYPE_ROCK;
		World theWorld = new World(theTerrain, new DefaultTerrainChangeListener());
		assertFalse(theWorld.hasSolidNeighbouringCube(1, 1, 1));
	}
	
	@Test
	public void isBetweenBoundaries_TrueCase()
	{
		assertTrue(world.isBetweenBoundaries(4, 4, 3));
	}
	
	@Test
	public void isBetweenBoundaries_FalseCase()
	{
		assertFalse(world.isBetweenBoundaries(10, 10, 10));
	}
	
	@Test
	public void isPassableCube_TrueCase()
	{
		assertTrue(world.isPassableCube(2, 2, 0));
		assertTrue(world.isPassableCube(3, 3, 1));
	}
	
	@Test
	public void isPassableCube_FalseCase()
	{
		assertFalse(world.isPassableCube(1, 1, 1));
		assertFalse(world.isPassableCube(3, 1, 1));
	}
	
	@Test
	public void isSolidCube_TrueCase()
	{
		assertTrue(world.isSolidCube(1, 1, 1));
		assertTrue(world.isSolidCube(3, 1, 1));
	}
	
	@Test
	public void isSolidCube_FalseCase()
	{
		assertFalse(world.isSolidCube(2, 2, 0));
		assertFalse(world.isSolidCube(3, 3, 1));
	}
	
	@Test
	public void isValidTerrain_TrueCase()
	{
		int[][][] theTerrain = new int[5][5][5];
		assertTrue(World.isValidTerrain(theTerrain));
		
		theTerrain = new int[50][50][50];
		assertTrue(World.isValidTerrain(theTerrain));
		
		theTerrain = new int[1000][22][35];
		assertTrue(World.isValidTerrain(theTerrain));
	}
	
	@Test
	public void isValidTerrain_NonEffectiveTerrain()
	{
		assertFalse(World.isValidTerrain(null));
	}
	
	@Test
	public void isValidTerrain_NegativeLengths()
	{
		int[][][] theTerrain = new int[0][0][1];
		assertFalse(World.isValidTerrain(theTerrain));
	}
	
	@Test
	public void isValidCube_TrueCase()
	{
		assertTrue(World.isValidCubeType(TYPE_AIR));
		assertTrue(World.isValidCubeType(TYPE_LOG));
		assertTrue(World.isValidCubeType(TYPE_ROCK));
		assertTrue(World.isValidCubeType(TYPE_WORKSHOP));
	}
	
	@Test
	public void isValidCube_FalseCase()
	{
		assertFalse(World.isValidCubeType(-1));
		assertFalse(World.isValidCubeType(4));
	}
	
	@Test
	public void setCubeType_LegalCase()
	{
		world.setCubeType(2, 2, 3, TYPE_ROCK);
		world.setCubeType(3, 4, 3, TYPE_LOG);
		world.setCubeType(4, 4, 4, TYPE_WORKSHOP);
		world.setCubeType(1, 1, 1, TYPE_AIR);
		assertEquals(TYPE_ROCK, world.getCubeType(2, 2, 3));
		assertEquals(TYPE_LOG, world.getCubeType(3, 4, 3));
		assertEquals(TYPE_WORKSHOP, world.getCubeType(4, 4, 4));
		assertEquals(TYPE_AIR, world.getCubeType(1, 1, 1));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setCubeType_CubeOutOfBoundaries() throws Exception
	{
		world.setCubeType(15, 15, 3, TYPE_LOG);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setCubeType_IllegalCubeTypeValue() throws Exception
	{
		world.setCubeType(1, 1, 1, 5);
	}
	
	@Test // TODO delete exception
	public void canHaveAsUnit_TrueCase() throws Exception
	{
		Unit unit = new Unit("Hillbilly", DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, 
				POSITION, false, world);
		assertTrue(world.canHaveAsUnit(unit));
	}
	
	@Test
	public void canHaveAsUnit_NonEffectiveUnit()
	{
		assertFalse(world.canHaveAsUnit(null));
	}
	
	@Test
	public void canHaveAsUnit_TooManyUnits() throws Exception // TODO delete exception
	{
		for (int i = 0; i < 100; i++)
			world.addUnit(new Unit("Hillbilly", DEFAULT_PROPERTY + i, DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, 
				POSITION, false, world));
		Unit unit = new Unit("Hillbilly", DEFAULT_PROPERTY - 1, DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, 
				POSITION, false, world);
		assertFalse(world.canHaveAsUnit(unit));
	}
	
	@Test
	public void addUnit_LegalCase() throws Exception // TODO delete exception
	{
		Unit unit = new Unit("Hillbilly", DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, 
				POSITION, false, world);
		world.addUnit(unit);
		assertTrue(world.hasAsUnit(unit));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addUnit_IllegalCase() throws Exception
	{
		world.addUnit(null);
	}
	
	@Test
	public void canRemoveAsUnit_TrueCase() throws Exception // TODO delete exception
	{
		Unit unit = new Unit("Hillbilly", DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, 
				POSITION, false, world);
		world.addUnit(unit);
		unit.die();
		assertTrue(world.canRemoveAsUnit(unit));
	}
	
	@Test
	public void canRemoveAsUnit_NonEffectiveUnit()
	{
		assertFalse(world.canRemoveAsUnit(null));
	}
	
	@Test
	public void canRemoveAsUnit_UnitNotDead() throws Exception // TODO delete exception
	{
		Unit unit = new Unit("Hillbilly", DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, 
				POSITION, false, world);
		world.addUnit(unit);
		assertFalse(world.canRemoveAsUnit(unit));
	}
	
	@Test
	public void canRemoveAsUnit_UnitNotAttached() throws Exception // TODO delete exception
	{
		Unit unit = new Unit("Hillbilly", DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, 
				POSITION, false, world);
		assertFalse(world.canRemoveAsUnit(unit));
	}
	
	@Test
	public void removeUnit_LegalCase() throws Exception // TODO delete exception
	{
		Unit unit = new Unit("Hillbilly", DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, 
				POSITION, false, world);
		world.addUnit(unit);
		unit.die();
		assertTrue(world.hasAsUnit(unit));
		world.removeUnit(unit);
		assertFalse(world.hasAsUnit(unit));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void removeUnit_IllegalCase() throws Exception
	{
		world.removeUnit(null);
	}
	
	@Test
	public void hasProperUnits_TrueCase() throws Exception // TODO delete exception
	{
		Unit unit = new Unit("Hillbilly", DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, 
				POSITION, false, world);
		world.addUnit(unit);
		assertTrue(world.hasProperUnits());
	}
	
	@Test
	public void hasProperUnits_UnitWithDifferentWorld() throws Exception // TODO delete exception
	{
		World theWorld = new World(terrain, modelListener);
		Unit unit = new Unit("Hillbilly", DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, 
				POSITION, false, theWorld);
		world.addUnit(unit);
		assertFalse(world.hasProperUnits());
	}
	
	@Test
	public void hasProperUnits_CannotHaveUnit() throws Exception // TODO delete exception
	{
		Unit unit = new Unit("Hillbilly", DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, 
				POSITION, false, world);
		world.addUnit(unit);
		unit.die();
		assertFalse(world.hasProperUnits());
	}
	
	@Test
	public void testGetAllUnits() throws Exception // TODO delete exception
	{
		Unit unit = new Unit("Hillbilly", DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, 
				POSITION, false, world);
		world.addUnit(unit);
		Set<Unit> units = world.getAllUnits();
		assertTrue(units.containsAll(world.getAllUnits()));
		assertEquals(world.getNbOfUnits(), units.size());
	}
	
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
