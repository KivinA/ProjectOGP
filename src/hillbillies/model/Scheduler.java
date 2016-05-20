package hillbillies.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class describing the Scheduler, which is attached to a Faction and stores Tasks.
 * 
 * @version	1.0
 * @author 	Kevin Algoet & Jeroen Depuydt
 * @note	Repository link: https://github.com/KivinA/ProjectOGP
 * 
 * @invar	Each Scheduler can have its Faction as Faction.
 * 			| canHaveAsFaction(getFaction())
 * @invar	Each Scheduler must have proper Tasks attached to it.
 * 			| hasProperTasks(getAllTasks())
 */
public class Scheduler {
	/**
	 * Initialize this Scheduler with the given Faction and World.
	 * 
	 * @param 	faction
	 * 			The new Faction for this Scheduler.
	 * @param 	world
	 * 			The new World for this Scheduler.
	 * @throws 	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 * @effect	The Faction of this new Scheduler is set to the given Faction.
	 * 			| this.setFaction(faction)
	 * @effect	The World of this new Scheduler is set to the given World.
	 * 			| this.setWorld(world)
	 */
	public Scheduler(Faction faction, World world) throws IllegalArgumentException
	{
		setWorld(world);
		setFaction(faction);
	}

	/**
	 * Terminated this Scheduler.
	 * 
	 * @post	The collection of Tasks attached to this Scheduler is cleared.
	 * @post	The World to which this Scheduler is attached is ineffective.
	 * @post	The Faction to which this Scheduler is attached is ineffective.
	 * @throws 	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 */
	public void terminate()
	{
		tasks.clear();
		this.isTerminated = true;
		setWorld(null);
		setFaction(null);
	}
	
	/**
	 * Check whether this Scheduler is terminated.
	 */
	@Basic @Raw
	public boolean isTerminated()
	{
		return this.isTerminated;
	}
	
	/**
	 * Variable registering whether this Scheduler is terminated.
	 */
	private boolean isTerminated = false;
	
	/**
	 * Return an ArrayList collecting all Tasks of this Scheduler.
	 */
	@Raw @Basic
	public ArrayList<Task> getAllTasks()
	{
		sortTasks();
		return this.tasks;
	}
	
	/**
	 * Return an Iterator for all Tasks currently managed by this Scheduler.
	 */
	@Raw
	public Iterator<Task> getAllTasksIterator()
	{
		sortTasks();
		return tasks.iterator();
	}
	
	/**
	 * Sort the Tasks of this Scheduler.
	 * 
	 * @effect	Sort the Tasks of this Scheduler according to the Comparator.
	 */
	@Raw @Model
	private void sortTasks()
	{
		Collections.sort(tasks, new Comparator<Task>(){ 
			@Override
			public int compare(Task t1, Task t2) {
				if (t1.getPriority() > t2.getPriority())
					return -1;
				if (t1.getPriority() < t2.getPriority())
					return 1;
				return 0;
			}
		});
	}
	
	/**
	 * Return the Task with the highest priority that is currently not being executed.
	 * 
	 * @return	
	 */
	@Raw @Basic
	public Task getTaskWithHighestPriority()
	{
		for (Task task : getAllTasks())
		{
			if (task.getUnit() == null)
				return task;
		}
		return null;
	}
	
	/**
	 * Check whether the given collections of Tasks are all Tasks assigned to this Scheduler.
	 * 
	 * @param 	tasks
	 * 			The collection of Tasks to check.
	 * @return	True if and only if all Tasks of this collection are assigned to this Scheduler.
	 * 			| result == tasks.containsAll(tasks)
	 */
	@Raw @Basic
	public boolean areTasksPartOfThisScheduler(Collection<Task> tasks)
	{
		return tasks.containsAll(tasks);
	}
	
	/**
	 * Check whether this Scheduler has the given Task as one of its Tasks.
	 * 
	 * @param 	task
	 * 			The given Task to check.
	 * @return	True if and only if the given Task is contained in the Tasks collection of this Scheduler.
	 * 			| result == tasks.contains(task)
	 */
	@Raw @Basic
	public boolean hasAsTask(Task task)
	{
		return tasks.contains(task);
	}
	
	/**
	 * Check whether this Scheduler has proper {@link Task}s attached to it.
	 * 
	 * @return	True if and only if the Scheduler can have each Task of this Scheduler as one of its Tasks and if that Task 
	 * 			has this Scheduler as one of its Schedulers.
	 * 			| for each task in getAllTasks():
	 * 			|	if (!canHaveAsTask(task))
	 * 			|		then result == false
	 * 			|	if (!task.hasAsScheduler(this))
	 * 			|		then result == false
	 * 			| result == true
	 */
	public boolean hasProperTasks()
	{
		for (Task task : getAllTasks())
		{
			if (!canHaveAsTask(task))
				return false;
			if (!task.hasAsScheduler(this))
				return false;
		}
		return true;
	}
	
	/**
	 * Check whether this Scheduler can have the given {@link Task} as one of its {@link Task}s.
	 * 
	 * @param 	task
	 * 			The {@link Task} to check.
	 * @return	True if and only if this Scheduler isn't terminated and if the given Task isn't terminated.
	 * 			| result == ( (!isTerminated()) && (!task.isTerminated()) )
	 */
	@Raw
	public boolean canHaveAsTask(Task task)
	{
		return !isTerminated() && !task.isTerminated();
	}
	
	/**
	 * Add a given Task to this Scheduler.
	 * 
	 * @param 	task
	 * 			The given Task to add.
	 * @post	This Sheduler has the given Task as one of its Tasks.
	 * 			| new.hasAsTask(task) == true
	 */
	@Raw
	public void addTask(Task task)
	{
		tasks.add(task);
	}
	
	/**
	 * Check whether this Scheduler can remove the given Task from its Tasks.
	 * 
	 * @param 	task
	 * 			The Task to check.
	 * @return	True if and only if the given Task is effective and part of this Scheduler's Tasks.
	 * 			| result == (task != null && hasAsTask(task))
	 * TODO 	Complete implementation.
	 */
	@Raw
	public boolean canRemoveAsTask(Task task)
	{
		return (task != null) && hasAsTask(task);
	}
	
	/**
	 * Remove a given Task from this Scheduler.
	 * 
	 * @param 	task
	 * 			The Task to remove.
	 * @post	The given Task is no longer part of this Scheduler's collection of Tasks.
	 * 			| new.hasAsTask(task) == false
	 */
	@Raw
	public void removeTask(Task task) throws IllegalArgumentException
	{
		if (!canRemoveAsTask(task))
			throw new IllegalArgumentException("The given Task cannot be removed from this Scheduler's list of Tasks.");
		tasks.remove(task);
	}
	
	/**
	 * Replace a Task in the list of Tasks with a given replacement Task.
	 * 
	 * @param 	original
	 * 			The Task to replace.
	 * @param 	replacement
	 * 			The replacement Task.
	 * @effect	The replacement Task will be added to the list, at the index of the original Task.
	 * 			| this.tasks.set(index, replacement)
	 * @effect	The original Task is removed from the list of Tasks.
	 * 			| this.removeTask(task)
	 * @throws	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 */
	@Raw
	public void replaceTask(Task original, Task replacement) throws IllegalArgumentException
	{
		stopExecutingTask(original);
		int index = tasks.indexOf(original);
		tasks.set(index, replacement);
		removeTask(original);
	}
	
	/**
	 * Mark the given Task, by creating a bidirectional association with the given Unit.
	 * 
	 * @param 	task
	 * 			The Task to mark.
	 * @param 	unit
	 * 			The Unit to execute the given Task.
	 * @throws 	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 * @effect	The Task of the given Unit is set to the given Task.
	 * 			| unit.setTask(task)
	 * @effect	The Unit of the given Task is set to the given Unit.
	 * 			| task.setUnit(unit)
	 */
	@Raw
	public void markTask(Task task, Unit unit) throws IllegalArgumentException
	{
		task.setUnit(unit);
		unit.setTask(task);
	}
	
	/**
	 * Unmark the given Task, by destroying the bidirectional association with the given Unit.
	 * 
	 * @param 	task
	 * 			The Task to unmark.
	 * @param 	unit
	 * 			The Unit to unmark.
	 * @throws 	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 * @effect	The Task of the given Unit is set to an ineffective state.
	 * 			| unit.setTask(null)
	 * @effect	The Unit of the given Task is set to an ineffective state.
	 * 			| task.setUnit(null)
	 */
	@Raw
	public void unmarkTask(Task task, Unit unit) throws IllegalArgumentException
	{
		unit.setTask(null);
		task.setUnit(null);
	}
	
	/**
	 * Variable referencing all the tasks of this Scheduler.
	 */
	private ArrayList<Task> tasks = new ArrayList<Task>();
	
	/**
	 * If the given Task is being executed by one of the Unit's of the attached Faction, stop the execution of this Task.
	 * 
	 * @param 	task
	 * 			The given Task to check whether it's being executed or not.
	 * @throws 	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 * @effect	The assigned Task of a Unit will be set to null, if this Unit has the given Task as its assigned Task.
	 * 			| (for each Unit in getFaction().getAllUnits():
	 * 			|	if (Unit.getTask() == task)
	 * 			|		then unit.setTask(null))
	 */
	@Model @Raw
	private void stopExecutingTask(Task task) throws IllegalArgumentException
	{
		Iterator<Unit> iter = getFaction().getAllUnits().iterator();
		while (iter.hasNext())
		{
			Unit unit = iter.next();
			if (unit.getTask() == task)
				unit.setTask(null);
		}
	}
	
	/**
	 * Return the {@link Faction} to which this Scheduler is attached.
	 */
	@Basic @Raw
	public Faction getFaction()
	{
		return this.faction;
	}
	
	/**
	 * Check whether this Scheduler can have the given {@link Faction} as its {@link Faction}.
	 * 
	 * @param 	faction
	 * 			The given {@link Faction} to check.
	 * @return	True if and only if the given Faction is null or if the given Faction is part of the same World as the Scheduler and if the given
	 * 			Faction doesn't have a Scheduler attached to it.
	 * 			| if (faction == null)
	 * 			| 	then result == null
	 * 			| else
	 * 			| 	result == getWorld().hasAsFaction(faction)
	 */
	@Raw
	public boolean canHaveAsFaction(Faction faction)
	{
		if (isTerminated())
			return faction == null;
		return (faction != null) && faction.canHaveAsScheduler(this);
	}
	
	/**
	 * Set the {@link Faction} to which this Scheduler is attached to the given {@link Faction}.
	 * 
	 * @param 	faction
	 * 			The new {@link Faction} for this Scheduler.
	 * @post	The Faction of this Scheduler is equal to the given Faction
	 * 			| new.getFaction() == faction
	 * @effect	The given Faction will assign this Scheduler as its Scheduler, if the given faction is effective.
	 * 			| if (faction != null)
	 * 			|	then faction.setScheduler(this)
	 * @effect	The World in which this Scheduler operates will be set to ineffective if the given faction is ineffective.
	 * 			| if (faction == null)
	 * 			|	then this.setWorld(null)
	 */
	@Raw
	public void setFaction(Faction faction) throws IllegalArgumentException
	{
		if (!canHaveAsFaction(faction))
			throw new IllegalArgumentException("This Scheduler cannot have the given Faction as its Faction.");
		this.faction = faction;
	}
	
	/**
	 * Variable referencing the Faction to which this Scheduler is attached.
	 */
	private Faction faction;
	
	/**
	 * Return the World in which this Scheduler operates.
	 */
	@Basic @Raw
	public World getWorld()
	{
		return this.world;
	}
	
	/**
	 * Check whether this Scheduler can have the given {@link World} as its {@link World}.
	 * 
	 * @param 	world
	 * 			The {@link World} to check.
	 * @return	If this Scheduler is terminated, true if and only if the given World is ineffective.
	 * 			Otherwise, true if and only if the given World is effective, and if this Scheduler's Faction has the same World
	 * 			as the given world.
	 */
	@Raw
	public boolean canHaveAsWorld(World world)
	{
		if (isTerminated())
			return world == null;
		return (world != null) && getFaction().getWorld().equals(world);
	}
	
	/**
	 * Set the World of this Scheduler to the given World.
	 * 
	 * @param 	world
	 * 			The new World for this Scheduler.
	 * @post	The World in which this Scheduler operates is equal to the given World.
	 * 			| new.getWorld() == world
	 */
	@Raw
	public void setWorld(World world)
	{
		this.world = world;
	}
	
	/**
	 * Variable referencing the World in which this Scheduler operates.
	 */
	private World world;
}
