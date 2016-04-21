package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of...
 * 
 * @version	0.8	
 * @author 	Kevin Algoet & Jeroen Depuydt
 * 
 */
public class Task {
	public Task(String name, int priority)
	{
		this.name = name;
		setPriority(priority);
	}
	
	/**
	 * Return the name of this Task.
	 */
	@Basic @Raw @Immutable
	public String getName() 
	{
		return this.name;
	}
	
	/**
	 * Variable referencing the name of this Task.
	 */
	private final String name;
	
	/**
	 * Return the priority of this Task.
	 */
	@Basic @Raw
	public int getPriority()
	{
		return this.priority;
	}
	
	/**
	 * Set the priority of this Task to the given priority.
	 * 
	 * @param 	priority
	 * 			The new priority for this Task.
	 * @post	The priority of this new Task is equal to the given priority.
	 * 			| new.getPriority() == priority
	 */
	@Raw
	public void setPriority(int priority)
	{
		this.priority = priority;
	}
	
	/**
	 * Variable referencing the priority of this Task.
	 */
	private int priority;
	
	/**
	 * Return the Unit that is currently executing this Task.
	 */
	@Basic @Raw
	public Unit getUnit()
	{
		return this.unit;
	}
	
	/**
	 * Set the Unit that will execute this Task to the given Unit.
	 * 
	 * @param 	unit
	 * 			The Unit that will be executing this Task.
	 */
	@Raw
	public void setUnit(@Raw Unit unit)
	{
		this.unit = unit;
	}
	
	/**
	 * Variable referencing the Unit that is currently executing this Task.
	 */
	private Unit unit;
}
