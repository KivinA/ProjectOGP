package hillbillies.model;

import java.util.*;
import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.expressions.Expression;
import hillbillies.model.statements.Statement;

/**
 * A class of Tasks than can be assigned to {@link Unit}s, with a name, priority, activity, list of schedulers, variable declarations and a
 * selected coordinate.
 * 
 * @version	1.0
 * @author 	Kevin Algoet & Jeroen Depuydt
 * 			1ba Informatica
 * @note	Repository link: https://github.com/KivinA/ProjectOGP
 * 
 * @invar	Each Task must have proper Schedulers.
 * 			| hasProperSchedulers()
 * @invar	Each Task must have a proper Unit.
 * 			| hasProperUnit()
 */
public class Task {
	
	/**
	 * Initialize this Task with a given name and priority, and an ineffective coordinate and activity.
	 *  
	 * @param 	name
	 * 			The name for this Task.
	 * @param 	priority
	 * 			The priority for this Task.
	 * @effect	Initialize this Task with the given name as its name, the given priority as its priority, an ineffective activity Statement
	 * 			and an ineffective selected coordinate.
	 * 			| this(name, priority, null, null)
	 * @note	This constructor should only be used as a quick alternative for easy test setups. 
	 * 			DO NOT USE THIS CONSTRUCTOR TO INITIALIZE A TASK IN A CLASS.
	 * 			Use the other constructor.
	 */
	@Deprecated
	public Task(String name, int priority)
	{
		this(name, priority, null, null);
	}
	
	/**
	 * Create a new Task with the given name, priority, activity {@link Statement} and a selected coordinate.
	 * 
	 * @param 	name
	 * 			The name for this new Task.
	 * @param 	priority
	 * 			The priority for this new Task.
	 * @param 	activity
	 * 			The activity {@link Statement} for this new Task.
	 * @param 	selectedCube
	 * 			The selected coordinate for this new Task.
	 * 
	 * @post	The name of this new Task is equal to the given name.
	 * 			| new.getName() == name
	 * @post	The activity of this new Task is equal to the given activity.
	 * 			| new.getActivity() == activity
	 * @post	The selected coordinate of this Task is equal to the given selected coordinates, if it is effective.
	 * 			Otherwise the selected coordiante of this new Task is ineffective.
	 * 			| if (selectedCube != null)
	 * 			| 	new.getSelected() == Arrays.copyOf(selectedCube, selectedCube.length)
	 * 			| else
	 * 			|	new.getSelected() == null
	 *  @effect	The priority of this Task is set to the given priority.
	 *  		| this.setPriority(priority)
	 */
	public Task(String name, int priority, Statement activity, int[] selectedCube)
	{
		this.name = name;
		setPriority(priority);
		this.activity = activity;
		if (selectedCube != null)
			this.selectedCube = Arrays.copyOf(selectedCube, selectedCube.length);
		else
			this.selectedCube = null;
	}
	
	/**
	 * Terminate this Task.
	 * 
	 * @post	The terminated state of this Task equals true.
	 * 			| new.isTerminated() == true
	 * @post	The list of Schedulers is empty.
	 * 			| new.getSchedulers().isEmpty() == true
	 * @post	The map of variables of this Task is empty.
	 * 			| new.variables.isEmpty() == true
	 * @post	The activity Statement of this Task is ineffective.
	 * 			| new.getActivity() == null
	 * @effect	Remove this Task from all Schedulers to which this Task is attached.
	 * 			| for each scheduler in getAllSchedulers():
	 * 			|	scheduler.removeTask(this)
	 */
	public void terminate()
	{
		this.terminated = true;
		for (Scheduler scheduler : getAllSchedulers())
			scheduler.removeTask(this);
		setUnit(null);
		schedulers.clear();
		variables.clear();
		activity = null;
	}
	
	/**
	 * Check whether this Task is terminated.
	 */
	@Basic @Raw
	public boolean isTerminated()
	{
		return this.terminated;
	}
	
	/**
	 * Variable registering whether this Task is terminated.
	 */
	private boolean terminated = false;
	
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
	 * @return	If this unit is terminated, true if and only if the given Unit is ineffective.
	 * 			Otherwise, true if and only if the given Unit is ineffective or if this Unit is effective and has this Task as its assigned Task.
	 * 			| if (isTerminated())
	 * 			|	then result == (unit == null)
	 * 			| if (unit == null)
	 * 			|	then result == true
	 * 			| else
	 * 			|	result == (unit.getTask() == this)
	 */
	@Raw
	public boolean canHaveAsUnit(Unit unit)
	{
		if (isTerminated())
			return unit == null;
		if (unit == null)
			return true;
		return unit.canHaveAsTask(this);
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
	 * Check whether this Task has a proper {@link Unit} attached to it.
	 * 
	 * @return	True if and only if this Task can have its Unit as its Unit, if this Task is terminated or if this Task isn't terminated,
	 * 			and the Unit has this Task as its Task.
	 * 			| if (canHaveAsUnit(getUnit())
	 * 			|	then
	 * 			|		if (isTerminated())
	 * 			|			then result == true
	 * 			|		else result == (getUnit().getTask() == this)
	 * 			| else result == false
	 */
	public boolean hasProperUnit()
	{
		if (canHaveAsUnit(getUnit()))
		{
			if (isTerminated())
				return true;
			else
				return getUnit().getTask() == this;
		}
		return false;
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
	@Raw
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
	@Raw
	public boolean canHaveAsScheduler(Scheduler scheduler)
	{
		return (scheduler != null);
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
	@Raw
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
	@Raw
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
	@Raw
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
	
	/**
	 * Get the activity of this Task.
	 */
	@Basic @Raw
	public Statement getActivity()
	{
		return this.activity;
	}
	
	/**
	 * Execute the activity of this Task.
	 * 
	 * @post	The activity of this Task is ineffective, if the activity has been executed.
	 * 			| if (getActivity().isExecuted())
	 * 			|	then getActivity() == null
	 * @effect	Execute the activity of this Task, if it hasn't been executed already.
	 * 			| if (!getActivity().isExecuted())
	 * 			|	then this.getActivity().execute(this)
	 */
	@Raw
	public void executeActivity()
	{
		if (getActivity().isExecuted())
			activity = null;
		else
			getActivity().execute(this);
	}
	
	/**
	 * Variable referencing the activities linked to this Task. This will most likely be a sequence of statements.
	 */
	private Statement activity;
	
	/**
	 * Check whether the given variable is in the map of variables.
	 * 
	 * @param 	variableName
	 * 			The name of the variable to check.
	 * @return	True if and only if the given variable name is in the map of variables as a key.
	 * 			| result == variables.containsKey(variableName)
	 */
	@Basic @Raw
	public boolean hasAsVariable(String variableName)
	{
		return variables.containsKey(variableName);
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
	@Raw
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
	@Raw
	public void replaceValue(String variableName, Expression value)
	{
		variables.replace(variableName, value);
	}
	
	/**
	 * Get the value which is mapped to the given variable name in the list of variables.
	 * 
	 * @param 	variableName
	 * 			The variable name that is linked to the value to return.
	 */
	@Basic @Raw
	public Expression getVariableValue(String variableName)
	{
		return variables.get(variableName);
	}
	
	/**
	 * A variable referencing to a map which stores the used variables in this Task with their values.
	 * 
	 * @invar	The referenced map is effective.
	 * 			| variables != null
	 * @invar	The referenced map doesn't contain the same key twice.
	 * 			| for each key in variables.keySet():
	 * 			|	... (?)
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
