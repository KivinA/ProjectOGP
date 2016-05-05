package hillbillies.model;

import java.util.Arrays;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of objects that are in the {@link World}.
 * 
 * @author 	Kevin Algoet & Jeroen Depuydt
 * @version	0.9
 * 
 * @invar	The weight of each WorldObject must be a valid weight for any WorldObject.
 * @invar	This WorldObject can have its position as its position.
 * @invar	This WorldObject can have its falling state as its falling state.
 * @note	The name 'WorldObject' would imply that every object inside the World implements this class. However, we only 
 * 			created this class for Boulder and Log, thus the class Unit won't implement this.
 */
public abstract class WorldObject {
	
	/**
	 * Initialize this WorldObject with the given weight, position and {@link World}.
	 * 
	 * @param 	weight
	 * 			The weight for this new WorldObject.
	 * @param 	position
	 * 			The position for this new WorldObject.
	 * @param 	world
	 * 			The {@link World} for this WorldObject.
	 */
	protected WorldObject(int weight, double[] position, World world) {
		setWorld(world);
		if (!isValidWeight(weight))
			throw new IllegalArgumentException("The given weight is invalid for any ..."); // [FIXME] Try to add the subclass name here!
		this.weight = weight;
		setPosition(position);
	}
	
	/**
	 * Advance the time of this WorldObject.
	 * 
	 * @throws 	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 * @effect	If this WorldObject is falling, the position is set to the cube beneath the current position.
	 */
	public void advanceTime() throws IllegalArgumentException
	{
		if (isFalling())
		{
			double[] position = {getPosition()[0], getPosition()[1], getPosition()[2] - 1};
			setPosition(position);
		}
	}
	
	/**
	 * Terminate this WorldObject.
	 * 
	 * @post	The terminated state of this WorldObject is enabled.
	 * @effect	Set the World of this WorldObject ineffective.
	 * @effect	Set the position of this WorldObject ineffective.
	 */
	public void terminate()
	{
		this.isTerminated = true;
		setWorld(null);
		setPosition(null);
	}
	
	/**
	 * Check whether this WorldObject is terminated.
	 */
	public boolean isTerminated()
	{
		return this.isTerminated;
	}
	
	/**
	 * Variable referencing whether this WorldObject is terminated.
	 */
	private boolean isTerminated = false;
	
	/**
	 * Return the {@link World} to which this WorldObject is attached.
	 */
	public World getWorld()
	{
		return this.world;
	}
	
	/**
	 * Check whether this WorldObject can have the given {@link World} as its {@link World}.
	 * 
	 * @param 	world
	 * 			The {@link World} to check.
	 */
	public abstract boolean canHaveAsWorld(World world);
	
	/**
	 * Set the {@link World} of this WorldObject to the given {@link World}.
	 * 
	 * @param 	world
	 * 			The {@link World} for this WorldObject.
	 * @post	The World of this WorldObject is equal to the given World.
	 * @throws	IllegalArgumentException
	 * 			This WorldObject cannot have the given World as its World.
	 */
	public void setWorld(World world)
	{
		if (!canHaveAsWorld(world))
			throw new IllegalArgumentException("This" + this.getClass() + "cannot have the given World as its World."); // [FIXME] Try to add the name of the subclass calling this.
		this.world = world;
	}
	
	/**
	 * Variable referencing the World to which this WorldObject is attached.
	 */
	private World world;
	
	/**
	 * Return the weight of this WorldObject
	 */
	@Basic @Raw
	public int getWeight()
	{
		return this.weight;
	}
	
	/**
	 * Check whether this WorldObject can have the given weight as its weight.
	 *  
	 * @param  	weight
	 *         	The weight to check.
	 * @return 	True if and only if the given weight is between 10 and 50 (inclusive).
	 */
	public static boolean isValidWeight(int weight)
	{
		return (weight >= 10) && (weight <= 50);
	}
	
	/**
	 * Variable registering the weight of this WorldObject.
	 */
	protected final int weight;
	
	/**
	 * Return the position of this WorldObject.
	 */
	public double[] getPosition()
	{
		return Arrays.copyOf(position, 3);
	}
	
	/**
	 * Check whether this WorldObject can have the given position as its position.
	 * 
	 * @param 	position
	 * 			The position to check.
	 * @return	True if and only if the given position is between the boundaries of the World of this WorldObject and if
	 * 			the cube which holds this position is passable.
	 */
	public boolean canHaveAsPosition(int[] coordinates)
	{
		if (isTerminated())
			return coordinates == null;
		return getWorld().isBetweenBoundaries(coordinates[0], coordinates[1], coordinates[2]) 
				&& getWorld().isPassableCube(coordinates[0], coordinates[1], coordinates[2]);
	}
	
	/**
	 * Set the position of this WorldObject to the given position.
	 * 
	 * @param 	position
	 * 			The position for this WorldObject.
	 * @post	The position of this worldObject is equal to the given position.
	 * @throws	IllegalArgumentException
	 * 			This WorldObject cannot have the given position as its position.
	 */
	public void setPosition(double[] position) throws IllegalArgumentException
	{
		int[] coordinates = getCubeCoordinates(position);
		if (!canHaveAsPosition(coordinates))
			throw new IllegalArgumentException("This " + this.getClass() + " cannot have the given position as its position.");
		this.position = Arrays.copyOf(position, position.length);
		
		if (position != null)
		{
			if (coordinates[2] != 0)
			{
				if (!getWorld().isSolidCube(coordinates[0], coordinates[1], coordinates[2]))
					setFalling(true);
				else if (isFalling())
					setFalling(false);
			}
			else if (isFalling())
				setFalling(false);
		}
	}
	
	/**
	 * Get the integer cube coordinates which hold the given coordinates.
	 * 
	 * @param 	coordinates
	 * 			The coordinates that are in the calculated cube.
	 */
	@Raw @Model
	private int[] getCubeCoordinates(double[] coordinates)
	{
		if (coordinates != null)
		{
			int[] cube = new int[coordinates.length];
			double halfCubeLength = getWorld().getCubeLength() / 2.0;
			for (int i = 0; i < coordinates.length; i++)
			{
				cube[i] = (int)(coordinates[i] - halfCubeLength);
			}
			return cube;
		}
		else
			return null;
	}
	
	/**
	 * Variable referencing the exact position of this WorldObject.
	 */
	private double[] position;
	
	/**
	 * Check whether this WorldObject is falling.
	 */
	@Basic @Raw
	public boolean isFalling()
	{
		return this.isFalling;
	}
	
	/**
	 * Check whether this WorldObject can have the given falling state as its falling state.
	 * 
	 * @param 	isFalling
	 * 			The falling state to check.
	 * @return	If the given fallen state is true, true if and only if the cube beneath this WorldObject is passable.
	 * 			Otherwise, true if and only if the z coordinates of this WorldObject is equal to 0
	 * 			or if the cube beneath this WorldObject is solid.
	 */
	public boolean canHaveAsFallingState(boolean isFalling)
	{
		int[] coordinates = getCubeCoordinates(getPosition());
		if (isFalling())
		{
			return (getWorld().isPassableCube(coordinates[0], coordinates[1], coordinates[2]));	
		}
		else
		{
			if (coordinates[2] == 0)
				return true;
			return getWorld().isSolidCube(coordinates[0], coordinates[1], coordinates[2]);
		}
	}
	
	/**
	 * Set the falling state of this WorldObject to the given flag.
	 * 
	 * @param 	flag
	 * 			The falling state for this WorldObject.
	 * @throws	IllegalArgumentException
	 * 			This WorldObject cannot have the given falling state as its falling state.
	 */
	@Raw
	public void setFalling(boolean flag) throws IllegalArgumentException
	{
		if (!canHaveAsFallingState(flag))
			throw new IllegalArgumentException("Invalid falling state!");
		isFalling = flag;
	}
	
	/**
	 * Variable registering the falling state of this WorldObject.
	 */
	private boolean isFalling;
}
