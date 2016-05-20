package hillbillies.model;

/**
 * A class describing the Boulders that are active in the {@link World}.
 * 
 * @author 	Kevin Algoet & Jeroen Depuydt
 * @version 1.0
 * @note	Repository link: https://github.com/KivinA/ProjectOGP
 * 
 * @invar	This Boulder must have a proper World.
 */
public class Boulder extends WorldObject{
	
	/**
	 * Initialize this new Boulder with the given position, weight and world.
	 * 
	 * @param 	position
	 * 			The position for this new Boulder.
	 * @param 	weight
	 * 			The weight for this new Boulder.
	 * @param	world
	 * 			The {@link World} for this new Boulder.
	 * @throws 	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 * @effect	Initialize this Boulder as a WorldObject with the given position, weight and World.
	 */
	public Boulder(double[] position, int weight, World world) throws IllegalArgumentException
	{
		super(weight, position, world);
	}
	
	/**
	 * Check whether this Boulder has a proper {@link World} attached to it.
	 * 
	 * @return	True if and only if this Boulder can have its attached World as its World and if the attached World is ineffective
	 * 			or if the attached World has this Boulder as one of its Boulders.
	 */
	@Override
	public boolean hasProperWorld()
	{
		return canHaveAsWorld(getWorld()) && (getWorld() == null || getWorld().hasAsBoulder(this)); 
	}
}
