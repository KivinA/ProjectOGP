package hillbillies.model;

/**
 * A class describing the Boulders that are active in the world.
 * 
 * @author 	Kevin Algoet & Jeroen Depuydt
 * @version 0.9
 * 
 * @invar	This Boulder can have its World as its World.
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
	 * Check whether this Boulder can have the given {@link World} as its {@link World}.
	 * 
	 * @return	If this Boulder is terminated, true if and only if the given World is ineffective.
	 * 			Otherwise, true if and only if the given World is effective and if this Boulder is a valid Boulder for any World.
	 */
	@Override
	public boolean canHaveAsWorld(World world) 
	{
		if (isTerminated())
			return world == null;
		return world != null && World.isValidBoulder(this);
	}
}
