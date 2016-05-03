package hillbillies.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import be.kuleuven.cs.som.annotate.*;
import ogp.framework.util.ModelException;

/**
 * A class describing Factions that are active in the {@link World}, with a set of {@link Unit}s and a {@link Scheduler}.
 * 
 * @version 0.9
 * @author 	Kevin Algoet & Jeroen Depuydt
 *
 * @invar	Each Faction must have proper Units.
 * @invar	Each Faction can have its Scheduler as its Scheduler.
 * 
 * [TODO]	Added World association, check the association in World.java.
 */
public class Faction {
	
	/**
	 * Initialize this new Faction with the given {@link World}.
	 * @throws ModelException 
	 * 
	 * @post	The World of this new Faction is equal to the given World.
	 */
	public Faction(World world) throws ModelException
	{
		setWorld(world);
		world.addFaction(this);
		setScheduler(new Scheduler(this, world));
	}
	
	/**
	 * Terminate this Faction.
	 * 
	 * @post	The World of this Faction is ineffective.
	 * @post	The Scheduler of this Faction is ineffective.
	 * @effect	The Scheduler of this Faction is terminated.
	 * @throws 	ModelException
	 * 			A condition was violated or an error was thrown.
	 */
	public void terminate() throws ModelException
	{
		setWorld(null);
		getScheduler().terminate();
		setScheduler(null);
	}
	
	/**
	 * Return the number of {@link Unit}s associated with this Faction.
	 */
	@Basic @Raw
	public int getNbOfUnits()
	{
		return units.size();
	}
	
	/**
	 * Check whether this Faction has the given {@link Unit} as one of its {@link Unit}s.
	 * 
	 * @param 	unit
	 * 			The {@link Unit} to check.
	 * @return	True if and only if the given Unit is registered as a Unit of this Faction.
	 */
	public boolean hasAsUnit(@Raw Unit unit)
	{
		return units.contains(unit);
	}
	
	/**
	 * Check whether this Faction can have the given {@link Unit} as one of its {@link Unit}s.
	 * 
	 * @param 	unit
	 * 			The {@link Unit} to check.
	 * @return	True if and only if the given Unit is effective and the number of Units isn't higher or equal to the maximum number 
	 * 			of Units.
	 */
	@Raw
	public boolean canHaveAsUnit(Unit unit)
	{
		return (unit != null) && (getNbOfUnits() < MAX_AMOUNT_OF_UNITS);
	}
	
	/**
	 * Add a given {@link Unit} to this faction.
	 * 
	 * @param 	unit
	 * 			The {@link Unit} to add.
	 * @throws 	ModelException 
	 * 			This Faction cannot have the given Unit as one of its Units.
	 * @post	The number of Units of this Faction is incremented by 1.
	 * @post	This Faction has the given unit as one of its units.
	 */
	@Raw
	public void addUnit(@Raw Unit unit) throws ModelException
	{
		if (! canHaveAsUnit(unit))
			throw new ModelException("This Faction cannot have the given Unit as one of its Units.");
		units.add(unit);
	}
	
	/**
	 * Check whether this Faction can remove the given {@link Unit} from its {@link Unit}s.
	 * 
	 * @param 	unit
	 * 			The {@link Unit} to check.
	 * @return	True if and only if the given Unit is effective, this Faction has the given Unit as one of its Units and
	 * 			if the given Unit doesn't reference any Faction.
	 */
	@Raw
	public boolean canRemoveAsUnit(Unit unit)
	{
		return (unit != null) && hasAsUnit(unit) && (unit.getFaction() == null);
	}
	
	/**
	 * Remove a given {@link Unit} from the list of {@link Unit}s of this Faction.
	 * 
	 * @param 	unit
	 * 			The {@link Unit} to remove.
	 * @throws	ModelException
	 * 			This Faction cannot remove the given Unit from its Units.
	 * @post	The number of Units of this Faction is decremented by 1.
	 * @post	This Faction no longer has the given Unit as one of its Units.
	 */
	@Raw
	public void removeUnit(@Raw Unit unit) throws ModelException
	{
		unit.setFaction(null);
		if (!canRemoveAsUnit(unit))
			throw new ModelException("The given Unit cannot be removed from this Faction's units.");
		units.remove(unit);
		
		if (units.isEmpty())
			getWorld().removeFaction(this);
	}
	
	/**
	 * Return a set containing all the {@link Unit}s of this Faction.
	 */
	public Set<Unit> getAllUnits()
	{
		return this.units;
	}
	
	/**
	 * Check whether this Faction has proper Units attached to it.
	 * 
	 * @return	True if and only if this Faction can have each Unit as one its Units and if 
	 * 			each of these Units references this Faction as the Faction to which they are
	 * 			attached.
	 */
	public boolean hasProperUnits()
	{
		Iterator<Unit> iterator = units.iterator();
		while (iterator.hasNext())
		{
			Unit unit = iterator.next();
			if (! canHaveAsUnit(unit))
				return false;
			if (unit.getFaction() != this)
				return false;
		}
		return true;
	}
	
	/**
	 * Variable referencing a set collecting all {@link Unit}s that are attached to this Faction.
	 * 
	 * @invar	The referenced set is effective.
	 * @invar	Each Unit registered in the referenced set is effective and has this Faction as its Faction.
	 * @invar	No Unit is referenced more than once in the set.
	 */
	private Set<Unit> units = new HashSet<Unit>();
	
	/**
	 * Constant registering the maximum amount of {@link Unit}s of this Faction.
	 */
	private final static int MAX_AMOUNT_OF_UNITS = 50;
	
	/**
	 * Return the {@link World} to which this Faction is attached.
	 */
	@Basic @Raw
	public World getWorld()
	{
		return this.world;
	}
	
	/**
	 * Set the {@link World} of this Faction to the given {@link World}.
	 * 
	 * @param 	world
	 * 			The new {@link World} for this Faction.
	 * @post	The World of this Faction is equal to the given World.
	 */
	@Raw
	public void setWorld(World world)
	{
		this.world = world;
	}
	
	/**
	 * Variable referencing the {@link World} to which this Faction is attached.
	 */
	private World world;
	
	/**
	 * Return the {@link Scheduler} that is attached to this Faction.
	 */
	@Basic @Raw
	public Scheduler getScheduler()
	{
		return this.scheduler;
	}
	
	/**
	 * Check whether this Faction can have the given {@link Scheduler} as its {@link Scheduler}.
	 * 
	 * @param 	scheduler
	 * 			The {@link Scheduler} to check.
	 * @return	True if and only if the given Scheduler is not effective or if the given Scheduler has this Faction as its Faction.
	 */
	@Raw
	public boolean canHaveAsScheduler(Scheduler scheduler)
	{
		if (scheduler == null)
			return true;
		return (scheduler.getFaction() == this);
	}
	
	/**
	 * Assign the given {@link Scheduler} to this Faction.
	 * 
	 * @param 	scheduler
	 * 			The new {@link Scheduler} for this Faction.
	 * @throws 	ModelException
	 * 			This Faction cannot have the given Scheduler as its Scheduler.
	 * @post	The Scheduler of this new Faction is equal to the given Scheduler.
	 */
	@Raw
	public void setScheduler(Scheduler scheduler) throws ModelException
	{
		if (!canHaveAsScheduler(scheduler))
			throw new ModelException("This Faction cannot have the given Scheduler as its Scheduler.");
		this.scheduler = scheduler;
	}
	
	/**
	 * Variable referencing the {@link Scheduler} attached to this Faction.
	 */
	private Scheduler scheduler;
}
