package hillbillies.model;

import java.util.ArrayList;

/**
 * A class of ...
 * 
 * @version	0.8
 * @author 	Kevin Algoet & Jeroen Depuydt
 * 
 */
public class Scheduler {
	public Scheduler()
	{
		
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
	 * Add a given Task to this Scheduler.
	 * 
	 * @param 	task
	 * 			The given Task to add.
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
	public void removeTask(Task task)
	{
		
	}
	
	/**
	 * Variable referencing all the tasks of this Scheduler.
	 */
	ArrayList<Task> tasks = new ArrayList<Task>();
}
