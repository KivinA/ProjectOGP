package hillbillies.model.expressions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.Faction;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class PositionWorkshopTest {

	private static World world;
	private Task task;
	private static Unit unit;
	private static Faction faction;
	private final static int TYPE_ROCK = 1;
	private final static int TYPE_WORKSHOP = 3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		int[][][] terrain = new int[3][3][3];
		terrain[1][1][0] = TYPE_WORKSHOP;
		terrain[0][1][0] = TYPE_ROCK;
		terrain[1][0][0] = TYPE_ROCK;
		terrain[1][0][1] = TYPE_WORKSHOP;
		world = new World(terrain, new DefaultTerrainChangeListener());
		faction = new Faction(world);
		world.addFaction(faction);
		unit = new Unit("Bleh", 30, 30, 30, 30, new int[]{0,  0, 0}, false, world);
		world.addUnit(unit);
		unit.setFaction(faction);
		faction.addUnit(unit);
	}

	@Before
	public void setUp() throws Exception {
		task = new Task("something", 0);
		task.addScheduler(faction.getScheduler());
		faction.getScheduler().addTask(task);
		task.setUnit(unit);
	}

	@Test
	public void PositionWorkshop_SingleCase() {
		Integer[] expected = {1, 1, 0};
		PositionWorkshop test = new PositionWorkshop();
		Integer[] result = test.evaluate(task);
		assertArrayEquals(expected, result);
	}

}
