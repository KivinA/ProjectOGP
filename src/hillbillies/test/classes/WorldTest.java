package hillbillies.test.classes;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.*;
import hillbillies.model.*;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.part2.listener.TerrainChangeListener;

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
	private static final double[] POSITION_OBJECT = {0.5, 0.5, 0.5};
	
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
	public void addUnit_LegalCase() throws Exception // TODO delete exception
	{
		Unit unit = new Unit("Hillbilly", DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, 
				POSITION, false, world);
		world.addUnit(unit);
		assertTrue(world.hasAsUnit(unit));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addUnit_IllegalUnit() throws Exception
	{
		world.addUnit(null);
	}
	
	@Test (expected = IllegalStateException.class)
	public void addUnit_TooManyUnits() throws Exception
	{
		for (int i = 0; i < 100; i++)
		{
			world.addUnit(new Unit("Hillbilly", DEFAULT_PROPERTY + i, DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, 
				POSITION, false, world));
		}
		Unit unit = new Unit("Hillbilly", DEFAULT_PROPERTY - 1, DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, 
				POSITION, false, world);
		world.addUnit(unit);
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
		assertEquals(1, world.getNbOfUnits());
		world.removeUnit(unit);
		assertFalse(world.hasAsUnit(unit));
		assertEquals(0, world.getNbOfUnits());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void removeUnit_IllegalUnit() throws Exception
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
	
	@Test
	public void spawnUnit_LegalCase() throws Exception
	{
		Unit unit = world.spawnUnit(false);
		assertTrue(world.hasAsUnit(unit));
		assertEquals(1, world.getNbOfFactions());
	}
	
	@Test
	public void canHaveAsFaction_TrueCase()
	{
		Faction faction = new Faction(world);
		assertTrue(world.canHaveAsFaction(faction));
	}
	
	@Test
	public void canHaveAsFaction_TerminatedFaction()
	{
		Faction faction = new Faction(world);
		faction.terminate();
		assertFalse(world.canHaveAsFaction(faction));
	}
	
	@Test
	public void canHaveAsFaction_NonEffectiveFaction()
	{
		assertFalse(world.canHaveAsFaction(null));
	}
	
	@Test
	public void addFaction_LegalCase()
	{
		Faction faction = new Faction(world);
		world.addFaction(faction);
		assertTrue(world.hasAsFaction(faction));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addFaction_IllegalFaction() throws Exception
	{
		world.addFaction(null);
	}
	
	@Test (expected = IllegalStateException.class)
	public void addFaction_TooManyFactions() throws Exception
	{
		for (int i = 0; i < 5; i++)
			world.addFaction(new Faction(world));
		world.addFaction(new Faction(world));
	}
	
	@Test
	public void canRemoveAsFaction_TrueCase()
	{
		Faction faction = new Faction(world);
		world.addFaction(faction);
		faction.terminate();
		assertTrue(world.canRemoveAsFaction(faction));
	}
	
	@Test
	public void canRemoveAsFaction_NonEffectiveFaction()
	{
		assertFalse(world.canRemoveAsFaction(null));
	}
	
	@Test
	public void canRemoveAsFaction_NonTerminatedFaction()
	{
		Faction faction = new Faction(world);
		world.addFaction(faction);
		assertFalse(world.canRemoveAsFaction(faction));
	}
	
	@Test
	public void canRemoveAsFaction_FactionNotAtttached()
	{
		Faction faction = new Faction(world);
		faction.terminate();
		assertFalse(world.canRemoveAsFaction(faction));
	}
	
	@Test
	public void removeAsFaction_LegalCase()
	{
		Faction faction = new Faction(world);
		world.addFaction(faction);
		assertTrue(world.hasAsFaction(faction));
		assertEquals(1, world.getNbOfFactions());
		faction.terminate();
		world.removeFaction(faction);
		assertFalse(world.hasAsFaction(faction));
		assertEquals(0, world.getNbOfFactions());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void removeAsFaction_IllegalFaction() throws Exception
	{
		Faction faction = new Faction(world);
		world.addFaction(faction);
		world.removeFaction(faction);
	}
	
	@Test
	public void hasProperFactions_TrueCase()
	{
		Faction faction = new Faction(world);
		world.addFaction(faction);
		assertTrue(world.hasAsFaction(faction));
	}
	
	@Test
	public void hasProperFactions_IllegalFaction()
	{
		Faction faction = new Faction(world);
		world.addFaction(faction);
		faction.terminate();
		assertFalse(world.hasProperFactions());
	}
	
	@Test
	public void hasProperFactions_FactionAttachedToOtherWorld()
	{
		World theWorld = new World(terrain, modelListener);
		Faction faction = new Faction(theWorld);
		world.addFaction(faction);
		assertFalse(world.hasProperFactions());
	}
	
	@Test
	public void canHaveAWorldObject_TrueCaseLog()
	{
		Log log = new Log(POSITION_OBJECT, DEFAULT_PROPERTY, world);
		assertTrue(world.canHaveAsWorldObject(log));
	}
	
	@Test
	public void canHaveAsWorldObject_TrueCaseBoulder()
	{
		Boulder boulder = new Boulder(POSITION_OBJECT, DEFAULT_PROPERTY, world);
		assertTrue(world.canHaveAsWorldObject(boulder));
	}
	
	@Test
	public void canHaveAsWorldObject_NonEffectiveWorldObject()
	{
		assertFalse(world.canHaveAsWorldObject(null));
	}
	
	@Test
	public void canHaveAsWorldObject_TerminatedWorldObject()
	{
		Boulder boulder = new Boulder(POSITION_OBJECT, DEFAULT_PROPERTY, world);
		boulder.terminate();
		assertFalse(world.canHaveAsWorldObject(boulder));
	}
	
	@Test
	public void addWorldObject_LegalCaseBoulder()
	{
		Boulder boulder = new Boulder(POSITION_OBJECT, DEFAULT_PROPERTY, world);
		world.addWorldObject(boulder);
		assertTrue(world.hasAsBoulder(boulder));
		assertEquals(1, world.getNbOfBoulders());
	}
	
	@Test
	public void addWorldObject_LegalCaseLog()
	{
		Log log = new Log(POSITION_OBJECT, DEFAULT_PROPERTY, world);
		world.addWorldObject(log);
		assertTrue(world.hasAsLog(log));
		assertEquals(1, world.getNbOfLogs());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addWorldObject_IllegalWorldObject() throws Exception
	{
		world.addWorldObject(null);
	}
	
	@Test
	public void canRemoveAsLog_TrueCase()
	{
		Log log = new Log(POSITION_OBJECT, DEFAULT_PROPERTY, world);
		world.addWorldObject(log);
		log.terminate();
		assertTrue(world.canRemoveAsLog(log));
	}
	
	@Test
	public void canRemoveAsLog_NonEffectiveLog()
	{
		assertFalse(world.canRemoveAsLog(null));
	}
	
	@Test
	public void canRemoveAsLog_NotAttachedLog()
	{
		Log log = new Log(POSITION_OBJECT, DEFAULT_PROPERTY, world);
		log.terminate();
		assertFalse(world.canRemoveAsLog(log));
	}
	
	@Test
	public void canRemoveAsLog_NonTerminatedLog()
	{
		Log log = new Log(POSITION_OBJECT, DEFAULT_PROPERTY, world);
		world.addWorldObject(log);
		assertFalse(world.canRemoveAsLog(log));
	}
	
	@Test
	public void removeLog_LegalCase()
	{
		Log log = new Log(POSITION_OBJECT, DEFAULT_PROPERTY, world);
		world.addWorldObject(log);
		assertTrue(world.hasAsLog(log));
		assertEquals(1, world.getNbOfLogs());
		log.terminate();
		world.removeLog(log);
		assertFalse(world.hasAsLog(log));
		assertEquals(0, world.getNbOfLogs());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void removeLog_IllegalCase() throws Exception
	{
		world.removeLog(null);
	}
	
	@Test
	public void hasProperLogs_TrueCase()
	{
		Log log = new Log(POSITION_OBJECT, DEFAULT_PROPERTY, world);
		world.addWorldObject(log);
		assertTrue(world.hasProperLogs());
	}
	
	@Test
	public void hasProperLogs_IllegalLog()
	{
		Log log = new Log(POSITION_OBJECT, DEFAULT_PROPERTY, world);
		world.addWorldObject(log);
		log.terminate();
		assertFalse(world.hasProperLogs());
	}
	
	@Test
	public void hasProperLogs_LogAttachedToOtherWorld()
	{
		World theWorld = new World(terrain, modelListener);
		Log log = new Log(POSITION_OBJECT, DEFAULT_PROPERTY, theWorld);
		world.addWorldObject(log);
		assertFalse(world.hasProperLogs());
	}
	
	@Test
	public void spawnLog_LegalCase()
	{
		world.spawnLog(POSITION_OBJECT);
		assertEquals(1, world.getNbOfLogs());
		assertTrue(world.hasProperBoulders());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void spawnLog_NonEffectivePosition() throws Exception
	{
		world.spawnLog(null);
	}
	
	@Test
	public void spawnLog_SolidPosition()
	{
		double[] pos = {1.5, 1.5, 1.5};
		world.spawnLog(pos);
		assertEquals(TYPE_AIR, world.getCubeType(1, 1, 1));
		assertEquals(1,  world.getNbOfLogs());
		assertTrue(world.hasProperLogs());
	}
	
	@Test
	public void canRemoveAsBoulder_TrueCase()
	{
		Boulder boulder = new Boulder(POSITION_OBJECT, DEFAULT_PROPERTY, world);
		world.addWorldObject(boulder);
		boulder.terminate();
		assertTrue(world.canRemoveAsBoulder(boulder));
	}
	
	@Test
	public void canRemoveAsBoulder_NonEffectiveBoulder()
	{
		assertFalse(world.canRemoveAsBoulder(null));
	}
	
	@Test
	public void canRemoveAsBoulder_NotAttachedBoulder()
	{
		Boulder boulder = new Boulder(POSITION_OBJECT, DEFAULT_PROPERTY, world);
		boulder.terminate();
		assertFalse(world.canRemoveAsBoulder(boulder));
	}
	
	@Test
	public void canRemoveAsBoulder_NonTerminatedBoulder()
	{
		Boulder boulder = new Boulder(POSITION_OBJECT, DEFAULT_PROPERTY, world);
		world.addWorldObject(boulder);
		assertFalse(world.canRemoveAsBoulder(boulder));
	}
	
	@Test
	public void removeBoulder_LegalCase()
	{
		Boulder boulder = new Boulder(POSITION_OBJECT, DEFAULT_PROPERTY, world);
		world.addWorldObject(boulder);
		assertTrue(world.hasAsBoulder(boulder));
		boulder.terminate();
		world.removeBoulder(boulder);
		assertFalse(world.hasAsBoulder(boulder));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void removeBoulder_IllegalCase() throws Exception
	{
		world.removeBoulder(null);
	}
	
	@Test
	public void hasProperBoulders_TrueCase()
	{
		Boulder boulder = new Boulder(POSITION_OBJECT, DEFAULT_PROPERTY, world);
		world.addWorldObject(boulder);
		assertTrue(world.hasProperBoulders());
	}
	
	@Test
	public void hasProperBoulders_IllegalBoulder()
	{
		Boulder boulder = new Boulder(POSITION_OBJECT, DEFAULT_PROPERTY, world);
		world.addWorldObject(boulder);
		boulder.terminate();
		assertFalse(world.hasProperBoulders());
	}
	
	@Test
	public void hasProperBoulders_BoulderAttachedToOtherWorld()
	{
		World theWorld = new World(terrain, modelListener);
		Boulder boulder = new Boulder(POSITION_OBJECT, DEFAULT_PROPERTY, theWorld);
		world.addWorldObject(boulder);
		assertFalse(world.hasProperBoulders());
	}
	
	@Test
	public void spawnBoulder_LegalCase()
	{
		world.spawnBoulder(POSITION_OBJECT);
		assertEquals(1, world.getNbOfBoulders());
		assertTrue(world.hasProperBoulders());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void spawnBoulder_NonEffectivePosition() throws Exception
	{
		world.spawnBoulder(null);
	}
	
	@Test
	public void spawnBoulder_SolidPosition()
	{
		double[] pos = {1.5, 1.5, 1.5};
		world.spawnBoulder(pos);
		assertEquals(TYPE_AIR, world.getCubeType(1, 1, 1));
		assertEquals(1, world.getNbOfBoulders());
		assertTrue(world.hasProperBoulders());
	}
	
	@Test
	public void testCaveIn() throws Exception
	{
		int[][][] theTerrain = new int[3][3][3];
		terrain[1][1][0] = TYPE_LOG;
		terrain[1][1][1] = TYPE_ROCK;
		terrain[1][1][2] = TYPE_WORKSHOP;
		World theWorld = new World(theTerrain, modelListener);
		assertFalse(theWorld.isConnectedToBorder(1, 1, 0));
		assertFalse(theWorld.isConnectedToBorder(1, 1, 1));
		advanceTimeFor(theWorld, 100, 0.02);
		assertEquals(TYPE_AIR, theWorld.getCubeType(1, 1, 0));
		assertEquals(TYPE_AIR, theWorld.getCubeType(1, 1, 1));
	}
	
	@Test
	public void testAdvanceTimeLogs() throws Exception
	{
		double[] pos = {0.5, 0.5, 3.5};
		double[] expectedPos = {0.5, 0.5, 2.5};
		Log log = new Log(pos, DEFAULT_PROPERTY, world);
		world.addWorldObject(log);
		advanceTimeFor(world, 100, 0.02);
		assertArrayEquals(expectedPos, log.getPosition(), 0);
	}
	
	@Test
	public void testAdvanceTimeBoulders() throws Exception
	{
		double[] pos = {0.5, 0.5, 3.5};
		double[] expectedPos = {0.5, 0.5, 2.5};
		Boulder boulder = new Boulder(pos, DEFAULT_PROPERTY, world);
		world.addWorldObject(boulder);
		advanceTimeFor(world, 100, 0.02);
		assertArrayEquals(expectedPos, boulder.getPosition(), 0);
	}
	
	// TODO Add one final test to test the advanceTime of Units. Currently not implemented because the Unit class
	// hasn't been revised yet.
	
	/**
	 * Helper method to advance the time of the given world by some given time.
	 * 
	 * @param 	world
	 * 			The World to advance the time.
	 * @param 	time
	 * 			The amount of time to advance.
	 * @param 	step
	 * 			The step size, in seconds, to advance the time.
	 * @throws 	Exception
	 * 			A condition was violated or an error was thrown.
	 */
	private void advanceTimeFor(World world, double time, double step) throws Exception
	{
		int n = (int)(time/step);
		for (int i = 0; i < n; i++)
			world.advanceTime(step);
		world.advanceTime(time - n * step);
	}
}
