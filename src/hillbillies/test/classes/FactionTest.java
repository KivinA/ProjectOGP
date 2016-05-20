package hillbillies.test.classes;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.*;
import ogp.framework.util.ModelException;
import hillbillies.model.*;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

/**
 * A class collecting tests for {@link Faction}s.
 * 
 * @author 	Kevin Algoet & Jeroen Depuydt
 * @version	0.9
 */
public class FactionTest {
	
	private Faction faction;
	private static World world;
	private static final int DEFAULT_PROPERTY_VALUE = 30;
	private static final int TYPE_ROCK = 1;
	private static final int TYPE_LOG = 2;
	private static final int TYPE_WORKSHOP = 3;
	private static final int[] POSITION = {0, 0, 0};
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		int[][][] terrain = new int[3][3][3];
		terrain[1][1][0] = TYPE_WORKSHOP;
		terrain[1][1][1] = TYPE_LOG;
		terrain[1][1][2] = TYPE_ROCK;
		terrain[1][0][1] = TYPE_LOG;
		terrain[1][0][2] = TYPE_ROCK;
		
		world = new World(terrain, new DefaultTerrainChangeListener());
	}
	
	@Before
	public void setUp() throws Exception
	{
		faction = new Faction(world);
		world.addFaction(faction);
	}
	
	@After
	public void tearDown() throws Exception
	{
		if (world.hasAsFaction(faction))
		{
			if (faction.getNbOfUnits() > 0)
				for (Unit unit: faction.getAllUnits())
				{
					unit.die();
					faction.removeUnit(unit);
				}
					
			else
			{
				if (!faction.isTerminated())
					faction.terminate();
				world.removeFaction(faction);
			}
		}
		
		for (Iterator<Unit> i = world.getAllUnits().iterator(); i.hasNext();)
		{
			Unit unit = i.next();
			unit.die();
			i.remove();
		}
	}
	
	@Test
	public void constructor_SingleCase()
	{
		Faction theFaction = new Faction(world);
		world.addFaction(theFaction);
		assertSame(world, theFaction.getWorld());
		assertTrue(world.hasAsFaction(theFaction));
		assertNotNull(theFaction.getScheduler());
		assertEquals(0, theFaction.getNbOfUnits());
		assertTrue(theFaction.hasProperUnits());
		assertTrue(theFaction.hasProperScheduler());
		assertTrue(theFaction.hasProperWorld());
	}
	
	@Test
	public void terminate_LegalCase()
	{
		Faction theFaction = new Faction(world);
		world.addFaction(theFaction);
		theFaction.terminate();
		assertEquals(0, theFaction.getNbOfUnits());
		assertTrue(theFaction.isTerminated());
		assertNull(theFaction.getScheduler());
		assertNull(theFaction.getWorld());
		assertTrue(world.hasAsFaction(theFaction));
		world.removeFaction(theFaction);
		assertFalse(world.hasAsFaction(theFaction));
	}
	
	@Test
	public void terminate_LegalCaseZeroUnits() throws Exception
	{
		Faction theFaction = new Faction(world);
		world.addFaction(theFaction);
		Unit unit = new Unit("Hillbilly", DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, POSITION, false, world);
		world.addUnit(unit);
		theFaction.addUnit(unit);
		theFaction.removeUnit(unit);
		assertEquals(0, theFaction.getNbOfUnits());
		assertTrue(theFaction.isTerminated());
		assertNull(theFaction.getScheduler());
		assertNull(theFaction.getWorld());
	}
	
	@Test (expected = IllegalStateException.class)
	public void terminate_NonEmptyUnits() throws Exception
	{
		Unit unit = new Unit("Hillbilly", DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, POSITION, false, world);
		world.addUnit(unit);
		faction.addUnit(unit);
		faction.terminate();
	}
	
	@Test
	public void canHaveAsUnit_TrueCase() throws Exception // [FIXME] This Exception should be deleted once possible.
	{
		Unit unit = world.spawnUnit(false);
		assertTrue(faction.canHaveAsUnit(unit));
	}
	
	@Test
	public void canHaveAsUnit_NonEffectiveUnit()
	{
		assertFalse(faction.canHaveAsUnit(null));
	}
	
	@Test
	public void canHaveAsUnit_UnitNotPartOfWorld() throws Exception // [FIXME] This Exception should be deleted once possible.
	{
		Unit unit = new Unit("Hillbilly", DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, POSITION, false, world);
		assertFalse(faction.canHaveAsUnit(unit));
	}
	
	@Test
	public void canHaveAsUnit_AmountExceeded() throws Exception // [FIXME] This Exception should be deleted once possible.
	{
		Unit unit;
		for (int i = 0; i < 50; i++)
		{
			unit = new Unit ("Hillbilly", DEFAULT_PROPERTY_VALUE + i, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, POSITION, false, world);
			world.addUnit(unit);
			faction.addUnit(unit);
		}
		
		unit = new Unit("Hillbilly", 10, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, POSITION, false, world);
		assertFalse(faction.canHaveAsUnit(unit));
	}
	
	@Test
	public void addUnit_LegalCase() throws Exception // [FIXME] This Exception should be deleted once possible.
	{
		Unit unit = new Unit ("Hillbilly", 10, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, POSITION, false, world);
		world.addUnit(unit);
		faction.addUnit(unit);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addUnit_IllegalCase() throws Exception
	{
		faction.addUnit(null);
	}
	
	@Test
	public void canRemoveAsUnit_TrueCase() throws Exception // [FIXME] This Exception should be deleted once possible.
	{
		Unit unit =  new Unit ("Hillbilly", 10, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, POSITION, false, world);
		world.addUnit(unit);
		faction.addUnit(unit);
		unit.die();
		assertTrue(faction.canRemoveAsUnit(unit));
	}
	
	@Test
	public void canRemoveAsUnit_NonEffectiveUnit()
	{
		assertFalse(faction.canRemoveAsUnit(null));
	}
	
	@Test
	public void canRemoveAsUnit_IllegalUnit() throws Exception  // [FIXME] This Exception should be deleted once possible.
	{
		Unit unit = new Unit ("Hillbilly", 10, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, POSITION, false, world);
		world.addUnit(unit);
		assertFalse(faction.canRemoveAsUnit(unit));
	}
	
	@Test
	public void canRemoveAsUnit_UnitWithFaction() throws Exception // [FIXME] This Exception should be deleted once possible.
	{
		Faction theFaction = new Faction(world);
		world.addFaction(theFaction);
		Unit unit = new Unit ("Hillbilly", 10, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, POSITION, false, world);
		world.addUnit(unit);
		theFaction.addUnit(unit);
		unit.setFaction(faction);
		assertFalse(theFaction.canRemoveAsUnit(unit));
	}
	
	@Test
	public void removeUnit_LegalCase() throws Exception // [FIXME] This Exception should be deleted once possible.
	{
		Unit unit = new Unit ("Hillbilly", 10, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, POSITION, false, world);
		world.addUnit(unit);
		faction.addUnit(unit);
		faction.removeUnit(unit);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void removeUnit_IllegalCase() throws Exception
	{
		Unit unit = new Unit ("Hillbilly", 10, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, POSITION, false, world);
		world.addUnit(unit);
		faction.removeUnit(unit);
	}
	
	@Test
	public void hasProperUnits_TrueCase() throws Exception // [FIXME] This Exception should be deleted once possible.
	{
		Unit unit;
		for (int i = 0; i < 10; i++)
		{
			unit = new Unit("Hillbilly", DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, POSITION, false, world);
			world.addUnit(unit);
			faction.addUnit(unit);
			unit.setFaction(faction);
		}
		assertTrue(faction.hasProperUnits());
	}
	
	@Test
	public void hasProperUnits_FalseCase() throws ModelException
	{
		Unit unit;
		for (int i = 0; i < 10; i++)
		{
			unit = new Unit("Hillbilly", DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, POSITION, false, world);
			world.addUnit(unit);
			faction.addUnit(unit);
			unit.setFaction(faction);
		}
		unit = new Unit("Hillbilly", DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, POSITION, false, world);
		world.addUnit(unit);
		faction.addUnit(unit);
		assertFalse(faction.hasProperUnits());
	}
	
	@Test
	public void canHaveAsWorld_TrueCase()
	{
		assertTrue(faction.canHaveAsWorld(world));
	}
	
	@Test
	public void canHaveAsWorld_FactionTerminated()
	{
		faction.terminate();
		assertFalse(faction.canHaveAsWorld(world));
	}
	
	@Test
	public void canHaveAsWorld_NonEffectiveWorldNonTerminatedFaction()
	{
		assertFalse(faction.canHaveAsWorld(null));
	}
	
	@Test
	public void hasProperWorld_TrueCase()
	{
		assertTrue(faction.hasProperWorld());
	}
	
	@Test
	public void hasProperWorld_FactionNotAddedToWorld()
	{
		Faction theFaction = new Faction(world);
		assertFalse(theFaction.hasProperWorld());
	}
	
	@Test
	public void canHaveAsScheduler_TrueCase() throws Exception
	{
		assertTrue(faction.canHaveAsScheduler(faction.getScheduler()));
	}
	
	@Test
	public void canHaveAsScheduler_NonEffectiveSchedulerNonTerminatedFaction()
	{
		assertFalse(faction.canHaveAsScheduler(null));
	}
	
	/*
	 * We cannot further implement these test cases, since it is IMPOSSIBLE to create a Scheduler on its own.
	 */
//	@Test 
//	public void canHaveAsScheduler_SchedulerAlreadyAttached() throws Exception
//	{
//		Faction theFaction = new Faction(world);
//		world.addFaction(theFaction);
//		Scheduler theScheduler = new Scheduler(null, world);
//		assertFalse(faction.canHaveAsScheduler(theScheduler));
//	}
//	
//	@Test
//	public void canHaveAsScheduler_SchedulerReferencingOtherFaction() throws Exception
//	{
//		Faction faction2 = new Faction(world);
//		faction2.setScheduler(null);
//		Scheduler theScheduler = new Scheduler(faction2, world);
//		faction2.setScheduler(theScheduler);
//		assertFalse(faction.canHaveAsScheduler(theScheduler));
//		world.removeFaction(faction2);
//	}
	
	// Furthermore, the invariant hasProperScheduler can only be true, since we cannot create a Scheduler on its own.
	@Test
	public void hasProperScheduler_TrueCase()
	{
		assertTrue(faction.hasProperScheduler());
	}
}
