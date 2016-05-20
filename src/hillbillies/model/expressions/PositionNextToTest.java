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

public class PositionNextToTest {

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
		task.setUnit(unit);
	}

	@Test
	public void PositionNextTo_SingleCase() {
		Integer[] original = {1, 1, 0};
		PositionXYZ testLiteral = new PositionXYZ(original[0], original[1], original[2]);
		PositionNextTo test = new PositionNextTo(testLiteral);
		Integer[] result = test.evaluate(task);
		assertTrue(isNeighbour(result, original));
		System.out.println("The calculated position is: (" + result[0] + ", " + result[1] + ", " + result[2] + ").");
	}

	private boolean isNeighbour(Integer[] position, Integer[] original)
	{
		return ( ( (original[0] == position[0]) || (original[0] + 1 == position[0]) || (original[0] - 1 == position[0]) )
				&& ( (original[1] == position[1]) || (original[1] + 1 == position[1]) || (original[1] - 1 == position[1]) ) 
				&& ( (original[2] == position[2]) || (original[2] + 1 == position[2]) || (original[2] - 1 == position[2]) ) );
	}
}
