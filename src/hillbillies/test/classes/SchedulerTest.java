package hillbillies.test.classes;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.*;
import hillbillies.model.*;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import ogp.framework.util.ModelException;

public class SchedulerTest {
	private Scheduler scheduler;
	private static World world;
	private Faction faction;
	
	@BeforeClass
	public static void setUpBeforeClass() throws ModelException
	{
		int[][][] terrain = new int[3][3][3];
		world = new World(terrain, new DefaultTerrainChangeListener());
	}
	
	@Before
	public void setUp() throws ModelException
	{
		faction = new Faction(world);
		world.addFaction(faction);
	}
	
	@Test (expected = ModelException.class)
	public void testInitialization() throws ModelException
	{
		scheduler = new Scheduler(faction, world);
		assertEquals(scheduler.getFaction(), faction);
		assertEquals(scheduler.getWorld(), world);
		
		Faction faction2 = new Faction(world);
		// This initialization will throw an error because faction2 isn't added to this World's Factions.
		scheduler = new Scheduler(faction2, world);
	}
	
	@Test
	public void testTasks() throws ModelException
	{
		scheduler = new Scheduler(faction, world);
		Task task1 = new Task("Task 1", 10);
		Task task2 = new Task("Task 2", 20);
		Task task3 = new Task("Task 3", 5);
		Task[] tasks = { task2, task1, task3 };
		
		scheduler.addTask(task1);
		scheduler.addTask(task2);
		scheduler.addTask(task3);
		
		Iterator<Task> iter = scheduler.getAllTasksIterator();
		int i = 0;
		while(iter.hasNext())
		{
			assertEquals(tasks[i], iter.next());
			i++;
		}
	}
	
	@After
	public void tearDown() throws ModelException
	{
		scheduler.setFaction(null);
		faction.setScheduler(null);
		world.removeFaction(faction);
	}
}
