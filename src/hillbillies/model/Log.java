package hillbillies.model;

/**
 * A class describing Logs that are active in the {@link World}.
 * 
 * @author 	Kevin Algoet & Jeroen Depuydt
 * @version 0.9
 * 
 * @invar	This Log must have a proper World.
 */
public class Log extends WorldObject{
	/**
	 * Initialize this new Log with the given position, weight and {@link World}.
	 * 
	 * @param 	position
	 * 			The position for this new Log.
	 * @param 	weight
	 * 			The weight for this new Log.
	 * @param 	world
	 * 			The {@link World} for this new Log.
	 * @throws	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 * @effect	Initialize this Log as a WorldObject with the given weight, position and World.
	 */
	public Log(double[] position, int weight, World world) throws IllegalArgumentException
	{
		super(weight, position, world);
	}

	/**
	 * Check whether this Log can have the given {@link World} as its {@link World}.
	 * 
	 * @return	If this Log is terminated, true if and only if the given World is ineffective.
	 * 			Otherwise, true if and only if the given World is effective and if this Log is a valid Log for any World.
	 */
	@Override
	public boolean canHaveAsWorld(World world) 
	{
		if (isTerminated())
			return world == null;
		return world != null && World.isValidLog(this);
	}
	
	/**
	 * Check whether this Log has a proper {@link World} attached to it.
	 * 
	 * @return	True if and only if this Log can have its attached World as its World and if the attached World is ineffective 
	 * 			or if this World has this Log as one of its Logs.
	 */
	@Override
	public boolean hasProperWorld()
	{
		return canHaveAsWorld(getWorld()) && (getWorld() == null || getWorld().hasAsLog(this));
	}
	
}
