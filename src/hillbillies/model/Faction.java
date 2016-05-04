package hillbillies.model;

import java.util.HashSet;
import java.util.Set;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class describing Factions that are active in the {@link World}, with a set of {@link Unit}s 
 * and a {@link Scheduler}.
 * 
 * @version 0.9
 * @author 	Kevin Algoet & Jeroen Depuydt
 *
 * @invar	Each Faction must have proper Units.
 * @invar	Each Faction must have a proper Scheduler.
 * @invar	Each Faction must have a proper World.
 * 
 */
public class Faction {
	
	/**
	 * Initialize this new Faction with the given {@link World}.
	 * 
	 * @effect	Set the World of this Faction to the given World.
	 * @effect	Set the Scheduler of this Faction to the newly created Scheduler.
	 * @throws	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 */
	public Faction(World world) throws IllegalArgumentException
	{
		setWorld(world);
		setScheduler(new Scheduler(this, world));
	}
	
	/**
	 * Terminate this Faction.
	 * 
	 * @effect	Set the World of this Faction ineffective.
	 * @effect	Terminate the Scheduler of this Faction.
	 * @effect	Set the Scheduler of this Faction ineffective.
	 * @post	The terminated state of this Faction is enabled.
	 * @throws 	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 * @throws 	IllegalStateException
	 * 			There are still Units attached to this Faction.
	 */
	public void terminate() throws IllegalArgumentException, IllegalStateException
	{
		if (getNbOfUnits() != 0)
			throw new IllegalStateException("This Faction still has Units attached to it!");
		this.isTerminated = true;
		setWorld(null);
		getScheduler().terminate();
		setScheduler(null);
	}
	
	/**
	 * Check whether this Faction is terminated or not.
	 */
	@Basic @Raw
	public boolean isTerminated()
	{
		return this.isTerminated;
	}
	
	/**
	 * Variable registering whether this Faction is terminated or not.
	 */
	private boolean isTerminated = false;
	
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
	 * @return	True if and only if the given Unit is effective, if the number of Units isn't higher or equal to the maximum number 
	 * 			of Units and if the given Unit is part of this Faction's World.
	 */
	@Raw
	public boolean canHaveAsUnit(Unit unit)
	{
		return (unit != null) && (getNbOfUnits() < MAX_AMOUNT_OF_UNITS) && (getWorld().hasAsUnit(unit));
	}
	
	/**
	 * Add a given {@link Unit} to this faction.
	 * 
	 * @param 	unit
	 * 			The {@link Unit} to add.
	 * @post	The number of Units of this Faction is incremented by 1.
	 * @post	This Faction has the given unit as one of its units.
	 * @effect	The Faction of the given Unit is set to this Faction.
	 * @throws 	IllegalArgumentException
	 * 			This Faction cannot have the given Unit as one of its Units.
	 * @throws	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 */
	@Raw
	public void addUnit(@Raw Unit unit) throws IllegalArgumentException
	{
		if (! canHaveAsUnit(unit))
			throw new IllegalArgumentException("This Faction cannot have the given Unit as one of its Units.");
		units.add(unit);
		unit.setFaction(this);
	}
	
	/**
	 * Check whether this Faction can remove the given {@link Unit} from its {@link Unit}s.
	 * 
	 * @param 	unit
	 * 			The {@link Unit} to check.
	 * @return	True if and only if the given Unit is effective, if this Faction has the given Unit as one of its Units and
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
	 * @throws	IllegalArgumentException
	 * 			This Faction cannot remove the given Unit from its Units.
	 * @throws	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 * @post	The number of Units of this Faction is decremented by 1.
	 * @post	This Faction no longer has the given Unit as one of its Units.
	 * @effect	If the list of Units is empty after removing the given Unit, remove this Faction from the World.
	 */
	@Raw
	public void removeUnit(@Raw Unit unit) throws IllegalArgumentException
	{
		if (!canRemoveAsUnit(unit))
			throw new IllegalArgumentException("The given Unit cannot be removed from this Faction's units.");
		units.remove(unit);
		
		// Remove this Faction once it has no more Units left after removing the last Unit.
		if (units.isEmpty())
			getWorld().removeFaction(this);
	}
	
	/**
	 * Return a set containing all the {@link Unit}s of this Faction.
	 * 
	 * @return	The number of elements in the resulting list is equal to the number of Units attached to this Faction.
	 * @return	Each element of the resulting set equals a Unit that is attached to this Faction.
	 */
	public Set<Unit> getAllUnits()
	{
		return new HashSet<Unit>(units);
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
		for (Unit unit: units)
		{
			if (!canHaveAsUnit(unit))
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
	 * Check whether this Faction can have the given {@link World} as its {@link World}.
	 * 
	 * @param 	world
	 * 			The {@link World} to check.
	 * @return	If this Faction is terminated, true if and only if the given World is ineffective.
	 * 			Otherwise, true if and only if the given World is effective and if the given World can have this Faction
	 * 			as one of its Factions.
	 */
	public boolean canHaveAsWorld(World world)
	{
		if (isTerminated())
			return world == null;
		else
			return (world != null) && world.canHaveAsFaction(this);
	}
	
	/**
	 * Set the {@link World} of this Faction to the given {@link World}.
	 * 
	 * @param 	world
	 * 			The new {@link World} for this Faction.
	 * @post	The World of this Faction is equal to the given World.
	 */
	@Raw
	public void setWorld(World world) throws IllegalArgumentException
	{
		if (!canHaveAsWorld(world))
			throw new IllegalArgumentException("Invalid World for this Faction!");
		this.world = world;
	}
	
	/**
	 * Check whether this Faction has a proper World attached to it.
	 * 
	 * @return	True if and only if this Faction can have the attached World as its World and if that World is ineffective or has
	 * 			this Faction as one of its Factions.
	 */
	public boolean hasProperWorld()
	{
		return canHaveAsWorld(getWorld()) && (getWorld() == null || getWorld().hasAsFaction(this));
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
	 * @return	If this Faction is terminated, true if and only if the given Scheduler is ineffective.
	 * 			Otherwise, true if and only if this Faction doesn't have a Scheduler, if the given Scheduler is effective
	 * 			and if the given Scheduler isn't terminated.
	 */
	@Raw
	public boolean canHaveAsScheduler(Scheduler scheduler)
	{
		if (isTerminated())
			return scheduler == null;
		return (getScheduler() == null) && (scheduler != null) && (!scheduler.isTerminated());
	}
	
	/**
	 * Assign the given {@link Scheduler} to this Faction.
	 * 
	 * @param 	scheduler
	 * 			The new {@link Scheduler} for this Faction.
	 * @throws 	IllegalArgumentException
	 * 			This Faction cannot have the given Scheduler as its Scheduler.
	 * @post	The Scheduler of this new Faction is equal to the given Scheduler.
	 */
	@Raw
	public void setScheduler(Scheduler scheduler) throws IllegalArgumentException
	{
		if (!canHaveAsScheduler(scheduler))
			throw new IllegalArgumentException("This Faction cannot have the given Scheduler as its Scheduler!");
		this.scheduler = scheduler;
	}
	
	/**
	 * Check whether this Faction has a proper {@link Scheduler} attached to it.
	 * 
	 * @return	True if and only if this Faction can have the attached Scheduler as its Scheduler and if the attached Scheduler is
	 * 			either ineffective or if the Faction of this Scheduler is equal to this Faction.
	 */
	public boolean hasProperScheduler()
	{
		return canHaveAsScheduler(getScheduler()) && (getScheduler() == null || getScheduler().getFaction().equals(this));
	}
	
	/**
	 * Variable referencing the {@link Scheduler} attached to this Faction.
	 */
	private Scheduler scheduler;
}
