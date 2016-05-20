package hillbillies.model.expressions;

import static org.junit.Assert.*;
import org.junit.*;
import hillbillies.model.*;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class UnitFriendTest {
	
	private static World world;
	private Task task;
	private static Unit unit;
	private static Faction faction;
	
	@BeforeClass
	public static void setUpBeforeClass()
	{
		int[][][] terrain = new int[3][3][3];
		world = new World(terrain, new DefaultTerrainChangeListener());
		faction = new Faction(world);
		world.addFaction(faction);
		unit = new Unit("Bleh", 30, 30, 30, 30, new int[]{0,  0, 0}, false, world);
		world.addUnit(unit);
		unit.setFaction(faction);
		faction.addUnit(unit);
	}
	
	@Before
	public void setUp() throws Exception
	{
		task = new Task("something", 0);
		task.setUnit(unit);
	}
	
	@Test
	public void UnitFriend_DifferentZLvls()
	{
		Unit unit2 = new Unit("Meh", 30, 30, 30, 30, new int[] {1, 1, 1}, false, world);
		Unit unit3 = new Unit("Meh", 30, 30, 30, 30, new int[] {1, 1, 0}, false, world);
		world.addUnit(unit3);
		world.addUnit(unit2);
		unit2.setFaction(faction);
		unit3.setFaction(faction);
		faction.addUnit(unit3);
		faction.addUnit(unit2);
		UnitFriend test = new UnitFriend();
		Unit result = test.evaluate(task);
		assertEquals(unit3, result);
	}

	@Test
	public void UnitFriend_SameZLvls()
	{
		Unit unit2 = new Unit("Meh", 30, 30, 30, 30, new int[] {2, 2, 0}, false, world);
		Unit unit3 = new Unit("Meh", 30, 30, 30, 30, new int[] {1, 2, 0}, false, world);
		world.addUnit(unit3);
		world.addUnit(unit2);
		unit2.setFaction(faction);
		unit3.setFaction(faction);
		faction.addUnit(unit3);
		faction.addUnit(unit2);
		UnitFriend test = new UnitFriend();
		Unit result = test.evaluate(task);
		assertEquals(unit3, result);
	}
	
}
