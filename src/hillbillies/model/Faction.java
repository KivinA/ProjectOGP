package hillbillies.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import be.kuleuven.cs.som.annotate.*;
import ogp.framework.util.ModelException;

/**
 * A class describing Factions that are active in the World, with a set of Units.
 * 
 * @version 0.7
 * @author 	Kevin Algoet & Jeroen Depuydt
 *
 * @invar	Each Faction must have proper Units.
 */
public class Faction {
	
	/**
	 * Initialize this new Faction.
	 */
	public Faction()
	{
		
	}
	
	/**
	 * Return the number of Units associated with this Faction.
	 */
	@Basic @Raw
	public int getNbOfUnits()
	{
		return units.size();
	}
	
	/**
	 * Check whether this Faction has the given Unit as one of its Units.
	 * 
	 * @param 	unit
	 * 			The given unit to check upon.
	 * 
	 * @return	True if and only if the given Unit is registered as a Unit of this Faction.
	 */
	public boolean hasAsUnit(Unit unit)
	{
		return units.contains(unit);
	}
	
	/**
	 * Check whether this Faction can have the given Unit as one of its Units.
	 * 
	 * @param 	unit
	 * 			The given Unit to check.
	 * @return	True if and only if the given Unit is effective and the number of Units isn't higher or equal to the maximum number 
	 * 			of Units.
	 */
	@Raw
	public boolean canHaveAsUnit(Unit unit)
	{
		return (unit != null) && (getNbOfUnits() < MAX_AMOUNT_OF_UNITS);
	}
	
	/**
	 * Add a given Unit to this faction.
	 * 
	 * @param 	unit
	 * 			The given Unit to add to this faction.
	 * 
	 * @throws 	ModelException 
	 * 			This Faction cannot have the given unit as one of its units.
	 * 
	 * @post	The number of units of this Faction is incremented by 1.
	 * @post	This Faction has the given unit as one of its units.
	 */
	public void addUnit(Unit unit) throws ModelException
	{
		if (! canHaveAsUnit(unit))
			throw new ModelException("This Faction cannot have the given Unit as one of its Units.");
		units.add(unit);
	}
	
	/**
	 * Check whether this Faction can remove the given unit from its units.
	 * 
	 * @param 	unit
	 * 			The given Unit to check.
	 * @return	True if and only if the given Unit is effective, this Faction has the given Unit as one of its Units and
	 * 			if the given Unit doesn't reference any Faction.
	 */
	@Raw
	public boolean canRemoveAsUnit(Unit unit)
	{
		return (unit != null) && hasAsUnit(unit) && (unit.getFaction() == null);
	}
	
	/**
	 * Remove a given Unit from the list of Units of this Faction.
	 * 
	 * @param 	unit
	 * 			The given Unit to remove from this faction.
	 * 
	 * @throws	ModelException
	 * 			This Faction cannot remove the given unit from its units.
	 * 
	 * @post	The number of units of this Faction is decremented by 1.
	 * @post	This Faction no longer has the given Unit as one of its Units.
	 */
	@Raw
	public void removeUnit(Unit unit) throws ModelException
	{
		unit.setFaction(null);
		if (!canRemoveAsUnit(unit))
			throw new ModelException("The given Unit cannot be removed from this Faction's units.");
		units.remove(unit);
	}
	
	/**
	 * Return a set containing all the Units of this Faction.
	 */
	@Basic @Raw
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
	 * Variable referencing a set collecting all Units in which this Faction is involved.
	 * 
	 * @invar	The references set is effective.
	 * @invar	Each Unit registered in the referenced set is effective.
	 * @invar	No Unit is referenced more than once in the set.
	 */
	private Set<Unit> units = new HashSet<Unit>();
	
	/**
	 * Constant registering the maximum amount of Units of this Faction.
	 */
	private final static int MAX_AMOUNT_OF_UNITS = 50;
}
