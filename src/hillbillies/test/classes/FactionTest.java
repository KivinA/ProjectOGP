package hillbillies.test.classes;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.*;
import ogp.framework.util.ModelException;
import hillbillies.model.*;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class FactionTest {
	
	private Faction faction;
	private static World world;
	private static final int DEFAULT_PROPERTY_VALUE = 30;
	private static final int TYPE_ROCK = 1;
	private static final int TYPE_LOG = 2;
	private static final int TYPE_WORKSHOP = 3;
	private static final int[] POSITION = {0,0,0};
	private boolean executeExtendedAfterMethod = false;
	
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
	}
	
	@Before
	public void setUp()
	{
		if (executeExtendedAfterMethod)
			executeExtendedAfterMethod = false;
		faction = new Faction();
	}
	
	@Test
	public void legalAmountOfUnits() throws ModelException
	{
		int amountOfUnitsFaction = 35;
		int[] position = {0,0,0};
		for (int i = 0; i < amountOfUnitsFaction; i++)
			faction.addUnit(new Unit("Hillbilly", DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE
					, position, false, world));
		
		assertEquals(amountOfUnitsFaction, faction.getNbOfUnits());
	}
	
	@Test (expected = ModelException.class)
	public void illegalAmountOfUnits() throws ModelException
	{
		int amountOfUnitsFaction = 55;
		
		for (int i = 0; i < amountOfUnitsFaction; i++)
			faction.addUnit(new Unit("Hillbilly", DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE
					, POSITION, false, world));
	}
	
	@Test
	public void illegalCanHaveAsUnit()
	{
		assertFalse(faction.canHaveAsUnit(null));
	}
	
	@Test
	public void legalCanRemoveAsUnit() throws ModelException
	{
		Unit unit = new Unit("Hillbilly", DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE
				, POSITION, false, world);
		
		faction.addUnit(unit);
		world.addFaction(faction);
		unit.setFaction(faction);
		unit.setFaction(null);
		assertTrue(faction.canRemoveAsUnit(unit));
	}
	
	@Test
	public void illegalCanRemoveAsUnit1()
	{
		assertFalse(faction.canRemoveAsUnit(null));
	}
	
	@Test
	public void illegalCanRemoveAsUnit2() throws ModelException
	{
		executeExtendedAfterMethod = true;
		Unit unit = new Unit("Hillbilly", DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE
				, POSITION, false, world);
		faction.addUnit(unit);
		world.addFaction(faction);
		unit.setFaction(faction);
		assertFalse(faction.canRemoveAsUnit(unit));
	}
	
	@Test
	public void illegalCanRemoveAsUnit3() throws ModelException
	{
		Unit unit = new Unit("Hillbilly", DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE
				, POSITION, false, world);
		assertFalse(faction.canRemoveAsUnit(unit));
	}
	
	@Test
	public void legalHasProperUnits() throws ModelException
	{
		executeExtendedAfterMethod = true;
		world.addFaction(faction);
		for (int i = 0; i < 10; i++)
		{
			Unit unit = new Unit("Hillbilly", DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE
					, POSITION, false, world);
			unit.setFaction(faction);
			faction.addUnit(unit);
		}
		assertTrue(faction.hasProperUnits());
	
	}
	
	@Test
	public void illegalHasProperUnits() throws ModelException
	{
		for (int i = 0; i < 10; i++)
		{
			faction.addUnit(new Unit("Hillbilly", DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE, DEFAULT_PROPERTY_VALUE
					, POSITION, false, world));
		}
		assertFalse(faction.hasProperUnits());
	}
	
	@After
	public void resetFaction() throws ModelException
	{
		if (executeExtendedAfterMethod)
		{
			Iterator<Unit> iterator = faction.getAllUnits().iterator();
			while (iterator.hasNext())
			{
				Unit unit = iterator.next();
				unit.setFaction(null);
				iterator.remove();
			}
			world.removeFaction(faction);
		}
		
		faction = null;
	}
}
