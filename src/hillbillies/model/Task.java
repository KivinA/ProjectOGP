package hillbillies.model;

import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.expressions.Expression;
import hillbillies.model.statements.Statement;
import ogp.framework.util.ModelException;

/**
 * A class of...
 * 
 * @version	0.8	
 * @author 	Kevin Algoet & Jeroen Depuydt
 * 
 * @invar	Each Task must have proper Schedulers.
 * 			| hasProperSchedulers()
 * @invar	Each Task can have its Unit as Unit.
 * 			| canHaveAsUnit(getUnit())
 */
public class Task {
	
	public Task(String name, int priority)
	{
		this.name = name;
		setPriority(priority);
		this.selectedCube = null;
	}
	
	public Task(String name, int priority, Statement activity, int[] selectedCube)
	{
		this.name = name;
		setPriority(priority);
		this.acitivity = activity;
		this.selectedCube = Arrays.copyOf(selectedCube, selectedCube.length);
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
	 * Check whether this Task can be assigned to the given Unit.
	 * 
	 * @param 	unit
	 * 			The Unit to check.
	 * @return	True if and only if the given Unit is ineffective or if this Unit is effective and has this Task as its assigned Task.
	 * 			| if (unit == null)
	 * 			|	then result == true
	 * 			| else
	 * 			|	result == (unit.getTask() == this)
	 */
	public boolean canHaveAsUnit(Unit unit)
	{
		if (unit == null)
			return true;
		return unit.getTask() == null;
	}
	
	/**
	 * Set the Unit that will execute this Task to the given Unit.
	 * 
	 * @param 	unit
	 * 			The Unit that will be executing this Task.
	 * @throws	ModelException
	 * 			This Task cannot have the given Unit as its Unit.
	 * 			| ! canHaveAsUnit(unit)
	 * @post	The Unit to which this new Task is asssigned is equal to the given Unit.
	 * 			| new.getUnit() == unit
	 */
	@Raw
	public void setUnit(@Raw Unit unit) throws IllegalArgumentException
	{
		if (!canHaveAsUnit(unit))
			throw new IllegalArgumentException("This Task cannot be assigned to the given Unit.");
		this.unit = unit;
	}
	
	/**
	 * Variable referencing the Unit that is currently executing this Task.
	 */
	private Unit unit;
	
	/**
	 * Return a set which contains every Scheduler where this Task is one of the Tasks of the Scheduler.
	 */
	@Basic @Raw
	public Set<Scheduler> getAllSchedulers()
	{
		return this.schedulers;
	}
	
	/**
	 * Check whether the given Scheduler is attached to this Task.
	 * 
	 * @param 	scheduler
	 * 			The given Scheduler to check.
	 * @return	True if and only if the given Scheduler is attached to this Task as one of its Schedulers.
	 * 			| result == schedulers.contains(scheduler)
	 */
	public boolean hasAsScheduler(Scheduler scheduler)
	{
		return schedulers.contains(scheduler);
	}
	
	/**
	 * Check whether this Task can have the given Scheduler as one of its Schedulers.
	 * 
	 * @param 	scheduler
	 * 			The Scheduler to check.
	 * @return	True if and only if the given Scheduler is effective and if the given Scheduler has this Task listed as one of its Tasks.
	 * 			| result == (scheduler != null) && (scheduler.hasAsTask(this))
	 */
	public boolean canHaveAsScheduler(Scheduler scheduler)
	{
		return (scheduler != null) && (scheduler.hasAsTask(this));
	}
	
	/**
	 * Add the given Scheduler to the list of Schedulers to which this Task is assigned.
	 * 
	 * @param 	scheduler
	 * 			The given Scheduler to add.
	 * @post	This Task has the given Scheduler as one of its Schedulers.
	 * 			| new.hasAsScheduler(scheduler) == true
	 * @throws	ModelException
	 * 			This Task cannot have the given Scheduler as one of its Schedulers.
	 * 			| !canHaveAsScheduler(scheduler)
	 */
	public void addScheduler(Scheduler scheduler) throws IllegalArgumentException
	{
		if (!canHaveAsScheduler(scheduler))
			throw new IllegalArgumentException("This Task cannot have the given Scheduler as one of its Schedulers.");
		schedulers.add(scheduler);
	}
	
	/**
	 * Check whether this Task can remove the given Scheduler from its list of Schedulers.
	 * 
	 * @param 	scheduler
	 * 			The given scheduler to check.
	 * @return	True if and only if the given Scheduler is effective and doesn't have this Task listed as one of its Tasks.
	 * 			| result == (scheduler != null) && (!scheduler.hasAsTask(this))
	 */
	public boolean canRemoveAsScheduler(Scheduler scheduler)
	{
		return (scheduler != null) && (!scheduler.hasAsTask(this));
	}
	
	/**
	 * Remove the given Scheduler from the list of Schedulers to which this Task is assigned.
	 * 
	 * @param 	scheduler
	 * 			The given Scheduler to remove.
	 * @post	This Task no longer has the given Scheduler as one of its Schedulers.
	 * 			| new.hasAsScheduler(scheduler) == false
	 * @throws	ModelException
	 * 			The given Scheduler cannot be removed from the list of Schedulers.
	 * 			| !canRemoveAsScheduler(scheduler)
	 */
	public void removeScheduler(Scheduler scheduler) throws IllegalArgumentException
	{
		if (!canRemoveAsScheduler(scheduler))
			throw new IllegalArgumentException("This Task cannot remove the given Scheduler from the list of Schedulers.");
		schedulers.remove(scheduler);
	}
	
	/**
	 * Check whether this Task has proper Schedulers attached to it.
	 * 
	 * @return	True if and only if each Scheduler of this Task's collection of Schedulers is effective and if this Task can have
	 * 			each Scheduler as one of its Schedulers.
	 * 			| (for each Scheduler in schedulers:
	 * 			|	if (Scheduler == null)
	 * 			|		then result == false
	 * 			|	if (!canHaveAsScheduler(Scheduler))
	 * 			|		then result == false)
	 * 			| result == true
	 */
	public boolean hasProperSchedulers()
	{
		Iterator<Scheduler> iter = getAllSchedulers().iterator();
		while (iter.hasNext())
		{
			Scheduler scheduler = iter.next();
			if (scheduler == null)
				return false;
			if (!canHaveAsScheduler(scheduler))
				return false;
		}
		return true;
	}
	
	/**
	 * Variable referencing a set collecting all the Schedulers in which this Task is present.
	 * 
	 * @invar	The referenced set is effective.
	 * 			| schedulers != null
	 * @invar	Each Scheduler referenced in the set is effective and has this Task as one of its Tasks.
	 * 			| (for each Scheduler in schedulers:
	 * 			|	(Scheduler != null) && (Scheduler.hasAsTask(this)))
	 * @invar	Each Scheduler referenced in the set is referenced only once.
	 * 			| (for each I,J in 0...schedulers.size():
	 * 			|	(I == J) || schedulers.get(I) != schedulers.get(J))
	 */
	private Set<Scheduler> schedulers = new HashSet<Scheduler>();
	
	public Statement getActivity()
	{
		return this.acitivity;
	}
	
	/**
	 * Variable referencing the activities linked to this Task. This will most likely be a sequence of statements.
	 */
	private Statement acitivity;
	
	/**
	 * TODO Doc
	 * @return
	 */
	public Map<String, Expression> getVariables() {
		return this.variables;
	}
	
	/**
	 * Add the given variable to the list of variables, along with its value.
	 * 
	 * @param 	variableName
	 * 			The name of the variable.
	 * @param 	value
	 * 			The value of the variable.
	 * @post	The given variable name is added to the set of keys of the variables map.
	 * 			| new.variables.keySet().contains(variableName)
	 * @post	The value of the given variable name is equal to the given value.
	 * 			| new.getVariableValue(variableName) == value
	 */
	public void addVariable(String variableName, Expression value)
	{
		variables.put(variableName, value);
	}
	
	/**
	 * Replace the value of the already existing variable with the new given variable.
	 * 
	 * @param 	variableName
	 * 			The name of the variable to replace the value of.
	 * @param 	value
	 * 			The value to replace.
	 * @post	The given variable name has the new given value as its value.
	 * 			| new.getVariableValue(variableName) == value
	 */
	public void replaceValue(String variableName, Expression value)
	{
		variables.replace(variableName, value);
	}
	
	/**
	 * Check whether the given variable is in the list of variables.
	 * 
	 * @param	variableName
	 * 			The variable name to check.
	 * @return	True if and only if the given variable is in the list of variables.
	 * 			| result == variables.containsKey(variableName)
	 */
	public boolean hasVariable(String variableName)
	{
		return variables.containsKey(variableName);
	}
	
	/**
	 * Get the value which is mapped to the given variable name in the list of variables.
	 * 
	 * @param 	variableName
	 * 			The variable name that is linked to the value to return.
	 */
	public Expression getVariableValue(String variableName)
	{
		return variables.get(variableName);
	}
	
	/**
	 * A variable referencing to a map which stores the used variables in this Task with their values.
	 */
	private Map<String, Expression> variables = new HashMap<String, Expression>();
	
	/**
	 * Get the selected cube of this Task.
	 */
	@Basic @Raw @Immutable
	public int[] getSelected()
	{
		return this.selectedCube;
	}
	
	/**
	 * Variable referencing the selected cube of this Task.
	 */
	private final int[] selectedCube;
	
}
