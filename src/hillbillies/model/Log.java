package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;
import ogp.framework.util.ModelException;

/**
 * A class describing Logs that are active in the World.
 * 
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.7
 * 
 * @invar	The weight of each Log must be a valid weight for any Log.
 * 
 * @invar  	Each Log can have its position as position.
 * 
 * @invar	Each Log can have its isFalling indicator as its isFalling indicator.
 */
public class Log {
	/**
	 * Initialize this new Log with the given position, weight and world.
	 * 
	 * @param 	position
	 * 			The position for this new Log.
	 * @param 	weight
	 * 			The weight for this new Log.
	 * @param 	world
	 * 			The world for this new Log.
	 * 
	 * @throws 	ModelException 
	 * 			The given weight for this new Log is an invalid weight for any Log.
	 * 
	 * @effect	The position of this new Log is set to the given position.
	 * 
	 * @effect	The World of this new Log is set to the given World.
	 * 
	 * @post   	The weight of this new Log is equal to the given weight.
	 */
	public Log(double[] position, int weight, World world) throws ModelException
	{
		this.world = world;
		
		if (!IsValidWeight(weight))
			throw new ModelException("The given weight is invaid for this Log.");
		this.weight = weight;
		
		setPosition(position);		
		
		world.addLog(this);
	}
	
	/**
	 * Advance the time of the World.
	 */
	public void advanceTime() throws ModelException
	{
		if (isFalling())
		{
			double[] position = {getPosition()[0], getPosition()[1], getPosition()[2] - 1};
			setPosition(position);
		}
	}
	
	/**
	 * Return the weight of this Log.
	 */
	@Basic @Raw @Immutable
	public int getWeight() 
	{
		return this.weight;
	}
	
	/**
	 * Check whether this Log can have the given weight as its weight.
	 *  
	 * @param  	weight
	 *         	The weight to check.
	 * @return 	True if and only if the given weight is between 10 and 50.
	*/
	@Raw
	public static boolean IsValidWeight(int weight) 
	{
		return ((weight >= 10) && (weight <= 50));
	}
	
	/**
	 * Variable registering the weight of this Log.
	 */
	private final int weight;

	/**
	 * Return the position of this Log.
	 */
	@Basic @Raw
	public double[] getPosition() 
	{
		return this.position;
	}
	
	/**
	 * Check whether this Log can have the given position as its position.
	 *  
	 * @param  	position
	 *         	The position to check.
	 * @return 	True if and only if every component of the position is between the lower boundary 
	 * 			and the upper boundary of this world and if the given position cube has a solid neighbouring cube and if the given 
	 * 			position is passable.
	 */
	public boolean canHaveAsPosition(int x, int y, int z) 
	{	
			return getWorld().isBetweenBoundaries(x, y, z) && getWorld().isPassableCube(x, y, z);
	}
	
	/**
	 * Set the position of this Log to the given position.
	 * 
	 * @param  	position
	 *         	The new position for this Log.
	 *         
	 * @post  	The position of this new Log is equal to the given position.
	 * 
	 * @effect	The isFalling indicator of this Log is activated if the given position is effective, the z component of the given position
	 * 			isn't zero, the Log isn't already falling and the given position isn't hovering above a solid cube.
	 * 
	 * @effect	The isFalling indicator of this Log is disabled if the given position is effecting, if the Log is hovering above a solid 
	 * 			cube and if the Log is falling.
	 * 
	 * @throws 	ModelException
	 *         	This Log cannot have the given position as its position.
	 *         
	 * @note	[FIXME] The position deciding code can be put into a seperate method because it is also used in the 
	 * 			canHaveAsIsFalling indicator, and in the Boulder class. We will however wait till part 3 to fix this, 
	 * 			because then inheritance will be introduced.
	 */
	@Raw
	public void setPosition(double[] position) throws ModelException 
	{
		if (position != null)
		{
			int halfCubeLength = (int) (getWorld().getCubeLength() / 2.0);
			int x = (int)(position[0] - halfCubeLength);
			int y = (int)(position[1] - halfCubeLength);
			int z = (int)(position[2] - halfCubeLength);
		
			if (! canHaveAsPosition(x, y, z))
				throw new ModelException("The given position is invalid for this Unit.");
			this.position = position;
			
	
			if (z != 0)
			{
				if (!getWorld().isSolidCube(x, y, z - 1) && !isFalling())
						setIsFalling(true);
				else if (isFalling())
					setIsFalling(false);
			}
			else if (isFalling())
					setIsFalling(false);
		}
		else
			this.position = position;
	}
	
	/**
	 * Variable registering the position of this Log.
	 */
	private double[] position;

	/**
	 * Return the world of this Log.
	 */
	@Basic @Raw
	public World getWorld() 
	{
		return world;
	}
	
	/**
	 * Set the World of this Log to the given World.
	 * 
	 * @param 	world
	 * 			The new World for this Log.
	 * 
	 * @post	The World of this new Log is equal to the given Log.
	 */
	@Raw
	public void setWorld(World world)
	{
		this.world = world;
	}
	
	/**
	 * Variable registering the world of this Log.
	 */
	private World world;
	
	/**
	 * Return the isFalling indicator of this Log.
	 */
	@Basic @Raw
	public boolean isFalling()
	{
		return this.isFalling;
	}
	
	/**
	 * Check whether this Log can have the given isFalling indicator as its isFalling indicator.
	 * 
	 * @param 	isFalling
	 * 			The given isFalling indicator to check.
	 * @return	True if and only if the given isFalling indicator is true and this Log isn't above a solid cube or if the given
	 * 			isFalling indicator is false and this Log is above a solid cube.
	 * 
	 * @note	[FIXME] This coincides with the checker of the class Boulder canHaveAsIsFalling. This code could be put in a 
	 * 			method and call upon this method from both classes.
	 */
	public boolean canHaveAsIsFalling(boolean isFalling)
	{
		int halfCubeLength = (int) (getWorld().getCubeLength() / 2.0);
		int x = (int)(getPosition()[0] - halfCubeLength);
		int y = (int)(getPosition()[1] - halfCubeLength);
		int z = (int)(getPosition()[2] - halfCubeLength);
		
		if (isFalling)
		{
			if (!getWorld().isSolidCube(x, y, z - 1))
				return true;
			return false;
		}
		
		else 
		{
			if (z == 0)
				return true;
			if (getWorld().isSolidCube(x, y, z - 1))
				return true;
			return false;
		}	
	}
	
	/**
	 * Set the isFalling indicator of this Log to the given isFalling indicator.
	 * 
	 * @param 	isFalling
	 * 			The new isFalling indicator for this Log.
	 * 
	 * @throws	ModelException
	 * 			This Log cannot have the given isFalling indicator as its isFalling indicator.
	 * 
	 * @post	The isFalling indicator of this new Log is equal to the given isFalling indicator.
	 */
	public void setIsFalling(boolean isFalling) throws ModelException
	{
		if (!canHaveAsIsFalling(isFalling))
			throw new ModelException("This Log cannot have the given isFalling indicator as its isFalling indicator.");
		this.isFalling = isFalling;
	}
	
	/**
	 * Variable registering the isFalling indicator of this Log.
	 */
	private boolean isFalling;
}
