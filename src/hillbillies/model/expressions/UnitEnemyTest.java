package hillbillies.model.expressions;

import static org.junit.Assert.*;
import org.junit.*;

import hillbillies.model.Faction;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class UnitEnemyTest {

	private static World world;
	private Task task;
	private static Unit unit;
	private static Faction faction1, faction2, faction3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		int[][][] terrain = new int[3][3][3];
		world = new World(terrain, new DefaultTerrainChangeListener());
		faction1 = new Faction(world);
		world.addFaction(faction1);
		unit = new Unit("Bleh", 30, 30, 30, 30, new int[]{0,  0, 0}, false, world);
		world.addUnit(unit);
		unit.setFaction(faction1);
		faction1.addUnit(unit);
	}

	@Before
	public void setUp() throws Exception {
		faction2 = new Faction(world);
		faction3 = new Faction(world);
		world.addFaction(faction2);
		world.addFaction(faction3);
		task = new Task("something", 0);
		task.setUnit(unit);
	}

	@Test
	public void UnitEnemy_DifferentZLvls() 
	{
		Unit unit2 = new Unit("Meh", 30, 30, 30, 30, new int[] {1, 1, 1}, false, world);
		Unit unit3 = new Unit("Meh", 30, 30, 30, 30, new int[] {1, 1, 0}, false, world);
		world.addUnit(unit3);
		world.addUnit(unit2);
		unit2.setFaction(faction2);
		unit3.setFaction(faction3);
		faction3.addUnit(unit3);
		faction2.addUnit(unit2);
		UnitEnemy test = new UnitEnemy();
		Unit result = test.evaluate(task);
		assertEquals(unit3, result);
		unit2.die();
		unit3.die();
		faction3.removeUnit(unit3);
		faction2.removeUnit(unit2);
		world.removeUnit(unit3);
		world.removeUnit(unit2);
	}

	@Test
	public void UnitEnemy_SameZLvls()
	{
		Unit unit2 = new Unit("Meh", 30, 30, 30, 30, new int[] {2, 2, 0}, false, world);
		Unit unit3 = new Unit("Meh", 30, 30, 30, 30, new int[] {1, 2, 0}, false, world);
		world.addUnit(unit3);
		world.addUnit(unit2);
		unit2.setFaction(faction2);
		unit3.setFaction(faction3);
		faction3.addUnit(unit3);
		faction2.addUnit(unit2);
		UnitEnemy test = new UnitEnemy();
		Unit result = test.evaluate(task);
		assertEquals(unit3, result);
		unit2.die();
		unit3.die();
		faction3.removeUnit(unit3);
		faction2.removeUnit(unit2);
		world.removeUnit(unit3);
		world.removeUnit(unit2);
	}
	
	@Test
	public void UnitEnemy_SameFaction()
	{
		Unit unit2 = new Unit("Meh", 30, 30, 30, 30, new int[] {1, 1, 1}, false, world);
		Unit unit3 = new Unit("Meh", 30, 30, 30, 30, new int[] {1, 1, 0}, false, world);
		world.addUnit(unit3);
		world.addUnit(unit2);
		unit2.setFaction(faction1);
		unit3.setFaction(faction1);
		faction1.addUnit(unit3);
		faction1.addUnit(unit2);
		UnitEnemy test = new UnitEnemy();
		Unit result = test.evaluate(task);
		assertEquals(null, result);
	}
}
