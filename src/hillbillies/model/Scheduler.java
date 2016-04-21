package hillbillies.model;

import java.util.ArrayList;
import java.util.Iterator;
import be.kuleuven.cs.som.annotate.*;
import ogp.framework.util.ModelException;

/**
 * A class describing the Scheduler, which is attached to a Faction and stores Tasks.
 * 
 * @version	0.8
 * @author 	Kevin Algoet & Jeroen Depuydt
 * 
 * @invar	This Scheduler can have its Faction as Faction.
 * 			| canHaveAsFaction(getFaction())
 * 
 */
public class Scheduler {
	/**
	 * Initialize this Scheduler with the given Faction and World.
	 * 
	 * @param 	faction
	 * 			The new Faction for this Scheduler.
	 * @param 	world
	 * 			The new World for this Scheduler.
	 * @throws 	ModelException
	 * 			A condition was violated or an error was thrown.
	 * @effect	The Faction of this new Scheduler is set to the given Faction.
	 * 			| this.setFaction(faction)
	 * @effect	The World of this new Scheduler is set to the given World.
	 * 			| this.setWorld(world)
	 */
	public Scheduler(Faction faction, World world) throws ModelException
	{
		setFaction(faction);
		setWorld(world);
	}

	/**
	 * Return an ArrayList collecting all Tasks of this Scheduler.
	 */
	public ArrayList<Task> getAllTasks()
	{
		return this.tasks;
	}
	
	/**
	 * Check whether this Scheduler has the given Task as one of its Tasks.
	 * 
	 * @param 	task
	 * 			The given Task to check.
	 * @return	True if and only if the given Task is contained in the Tasks collection of this Scheduler.
	 * 			| result == tasks.contains(task)
	 */
	public boolean hasAsTask(Task task)
	{
		return tasks.contains(task);
	}
	
	/**
	 * Check whether this Scheduler has proper Tasks attached to it.
	 */
	public boolean hasProperTasks()
	{
		return false;
	}
	
	/**
	 * Add a given Task to this Scheduler.
	 * 
	 * @param 	task
	 * 			The given Task to add.
	 * @post	This Sheduler has the given Task as one of its Tasks.
	 * 			| new.hasAsTask(task) == true
	 */
	public void addTask(Task task)
	{
		tasks.add(task);
	}
	
	/**
	 * Check whether this Scheduler can remove the given Task from its Tasks.
	 * 
	 * @param 	task
	 * 			The given Task to check.
	 * @return	True if and only if... [TODO]
	 */
	public boolean canRemoveAsTask(Task task)
	{
		return task != null && hasAsTask(task);
	}
	
	/**
	 * Remove a given Task from this Scheduler.
	 * 
	 * @param 	task
	 * 			The given Task to remove.
	 */
	public void removeTask(Task task) throws ModelException
	{
		if (!canRemoveAsTask(task))
			throw new ModelException("The given Task cannot be removed from this Scheduler's list of Tasks.");
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
	 * @throws	ModelException
	 * 			A condition was violated or an error was thrown.
	 */
	public void replaceTask(Task original, Task replacement) throws ModelException
	{
		stopExecutingTask(original);
		int index = tasks.indexOf(original);
		tasks.set(index, replacement);
		removeTask(original);
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
	 * @effect	The assigned Task of a Unit will be set to null, if this Unit has the given Task as its assigned Task.
	 * 			| (for each Unit in getFaction().getAllUnits():
	 * 			|	if (Unit.getTask() == task)
	 * 			|		then unit.setTask(null))
	 */
	private void stopExecutingTask(Task task)
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
	 * Return the Faction to which this Scheduler is attached.
	 */
	@Basic
	public Faction getFaction()
	{
		return this.faction;
	}
	
	/**
	 * Check whether this Scheduler can have the given Faction as its Faction.
	 * 
	 * @param 	faction
	 * 			The given Faction to check.
	 * @return	True if and only if the given Faction is null or if the given Faction is part of the same World as the Scheduler and if the given
	 * 			Faction doesn't have a Scheduler attached to it.
	 * 			| if (faction == null)
	 * 			| 	then result == null
	 * 			| else
	 * 			| 	result == getWorld().hasAsFaction(faction)
	 */
	public boolean canHaveAsFaction(Faction faction)
	{
		if (faction == null)
			return true;
		return getWorld().hasAsFaction(faction) && (faction.getScheduler() == null);
	}
	
	/**
	 * Set the Faction to which this Scheduler is attached to the given Faction.
	 * 
	 * @param 	faction
	 * 			The new Faction for this Scheduler.
	 * 
	 * @post	The Faction of this Scheduler is equal to the given Faction
	 * 			| new.getFaction() == faction
	 */
	@Raw
	public void setFaction(Faction faction) throws ModelException
	{
		if (!canHaveAsFaction(faction))
			throw new ModelException("This Scheduler cannot have the given Faction as its Faction.");
		this.faction = faction;
	}
	
	/**
	 * Variable referencing the Faction to which this Scheduler is attached.
	 */
	private Faction faction;
	
	/**
	 * Return the World in which this Scheduler operates.
	 */
	@Basic
	public World getWorld()
	{
		return this.world;
	}
	
	/**
	 * Set the World of this Scheduler to the given World.
	 * 
	 * @param 	world
	 * 			The new World for this Scheduler.
	 * 
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
