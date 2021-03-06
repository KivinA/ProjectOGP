package hillbillies.model.expressions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.*;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class PositionLogTest {
	
	private static World world;
	private Task task;
	private static Unit unit;
	private static Faction faction;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public void setUp() throws Exception {
		task = new Task("something", 0);
		task.addScheduler(faction.getScheduler());
		faction.getScheduler().addTask(task);
		task.setUnit(unit);
	}

	@Test
	public void PositionBoulder_SingleCase() {
		Log log1 = new Log(new double[]{1.5, 1.5, 1.5}, 30, world);
		Log log2 = new Log(new double[]{2.5, 2.5, 0.5}, 30, world);
		int[] tempexp = World.getCubeCoordinates(log2.getPosition());
		Integer[] expected = new Integer[3];
		for (int i = 0; i < tempexp.length; i++)
			expected[i] = tempexp[i];
		world.addWorldObject(log1);
		world.addWorldObject(log2);
		PositionLog test = new PositionLog();
		Integer[] result = test.evaluate(task);
		assertArrayEquals(expected, result);
	}
}
