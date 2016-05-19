package hillbillies.model;

import java.util.*;
import be.kuleuven.cs.som.annotate.*;

/* 
 * 		1. Check all TODO's.
 * 		5. Add @Raw annotation to associations with objects that doesn't have to be in proper state (basically almost every association)
 */

/** 
 * A class of Units which can have a name, agility, strength, weight, toughness, maximum hitpoints, maximum staminapoints, current hitpoints, 
 * current staminapoints, orientation, cube coordinates, position, default behaviour.
 * 
 * @version 1.0
 * @author 	Kevin Algoet & Jeroen Depuydt
 * 
 * @invar  	The name of each Unit must be a valid name for any Unit.
 *       	| isValidName(getName())
 * 
 * @invar  	The strength of each Unit must be a valid strength for any Unit.
 *       	| isValidStrength(getStrength())
 * 
 * @invar  	The agility of each Unit must be a valid agility for any Unit.
 *       	| isValidAgility(getAgility())
 * 
 * @invar  	The toughness of each Unit must be a valid toughness for any Unit.
 *       	| isValidToughness(getToughness())
 *      
 * @invar  	Each Unit can have its weight as its weight.
 *       	| canHaveAsWeight(getWeight())
 *       
 * @invar  	The maxHitpoints of each Unit must be a valid maxHitpoints for any Unit.
 *       	| isValidMaxHitpoints(this.getMaxHitpoints())
 *       
 * @invar  	Each Unit can have its currentHitpoints as its currentHitpoints.
 *       	| canHaveAsCurrentHitpoints(getCurrentHitpoints())
 *       
 * @invar  	Each Unit can have its tempHitpoint as its tempHitpoint.
 *       	| canHaveAsTempHitpoint(getTempHitpoint())
 *       
 * @invar  	The maxStaminapoints of each Unit must be a valid maxStaminapoints for any Unit.
 *       	| isValidMaxStaminaPoints(this.getMaxStaminaPoints())
 *       
 * @invar   Each Unit can have its currentStaminaPoints as its currentStaminapoints.
 *       	| canHaveAsCurrentStaminapoints(getCurrentStaminapoints())
 *       
 * @invar  	Each Unit can have its tempStaminapoint as its tempStaminapoint.
 *       	| canHaveAsTempStaminapoint(getTempStaminapoint())
 *       
 * @invar  	The orientation of each Unit must be a valid orientation for any Unit.
 *       	| isValidOrientation(getOrientation())
 *       
 * @invar  	Each Unit can have its cubeCoordinates as its cubeCoordinates
 *       	| canHaveAsCubeCoordinates(getCubeCoordinates())
 *       	
 * @invar  	Each Unit can have its isFalling indicator as its isFalling indicator.
 *       	| canHaveAsIsFalling(getisFalling())
 *       
 * @invar  	Each Unit can have its nextCubeCoordinates as its nextCubeCoordinates.
 *       	| canHaveAsNextCubeCoordinates(getNextCubeCoordinates())
 *       
 * @invar  	The deltaNewPositions of each Unit must be a valid deltaNewPositions for any Unit.
 *       	| isValidDeltaNewPositions(getDeltaNewPositions())

 * @invar  	Each Unit can have its walkingSpeed as its walkingSpeed.
 *       	| canHaveAsWalkingSpeed(getWalkingSpeed())
 *       
 * @invar  	Each Unit can have its sprintSpeed as its sprintSpeed.
 *       	| canHaveAsSprintSpeed(getSprintSpeed())
 *    		
 * @invar  	Each Unit can have its current speed as its currentSpeed.
 *       	| canHaveAsCurrentSpeed(getCurrentSpeed())
 *       
 * @invar  	Each Unit can have its isSprinting indicator as its isSprinting indicator.
 *       	| canHaveAsIsSprinting(getIsSprinting())
 *       
 * @invar  	The moving indicator of each Unit must be a valid moving indicator for any Unit.
 *       	| canHaveIsMoving(getIsMoving())
 *       
 * @invar  	The sprinting duration of each Unit must be a valid sprinting duration for any Unit.
 *       	| isValidSprintingDuration(getSprintingDuration())
 *       
 * @invar  	Each Unit can have its wasMoving indicator as its wasMoving indicator.
 *       	| canHaveAsWasMoving(getWasMoving())
 *       
 * @invar  	Each Unit can have its isMovingTo indicator as its isMovingTo indicator.
 *       	| canHaveAsIsMovingTo(getIsMovingTo())
 *       
 * @invar  	Each Unit can have its isMovingToAdjacent indicator as its isMovingToAdjacent indicator.
 *       	| canHaveAsIsMovingToAdjacent(getIsMovingToAdjacent())
 *       
 * @invar  	Each Unit can have its isWorking indicator as its isWorking indicator.
 *       	| canHaveAsIsWorking(getIsWorking())
 *       	
 * @invar  	The targetCube of each Unit must be a valid targetCube for any Unit.
 *       	| isValidTargetCube(getTargetCube())
 *       
 * @invar  	Each Unit can have its workingDuration as its workingDuration.
 *       	| canHaveAsWorkingDuration(getWorkingDuration()) 
 *       
 * @invar  	This Unit can have its isCarrying indicator as isCarrying indicator.
 *       	| canHaveAsIsCarryingLog(getIsCarryingLog())
 *       
 * @invar  	This Unit can have its log as log.
 *       	| canHaveAsLog(getLog())
 *       
 * @invar  	This Unit can have its isCarryingBoulder indicator as isCarryingBoulder indicator.
 *       	| canHaveAsIsCarryingBoulder(getIsCarryingBoulder())
 *       
 * @invar  	This Unit can have its boulder as boulder.
 *       	| canHaveAsBoulder(getBoulder())
 *       
 * @invar  	The fighting duration of each Unit must be a valid fighting duration for any Unit.
 *       	| isValidFightingDuration(getFightingDuration())
 *       
 * @invar  	Each Unit can have its isResting indicator as its isResting indicator.
 *       	| canHaveAsIsResting(getIsResting())
 *       
 * @invar  	The resting period of each Unit must be a valid resting period for any Unit.
 *       	| isValidRestingPeriod(getRestingPeriod())
 *       
 * @invar  	The resting duration of each Unit must be a valid resting duration for any Unit.
 *       	| isValidRestingDuration(getRestingDuration())
 *       
 * @invar  	Each Unit can have its hasRestedOnePoint indicator as its hasRestedOnePoint indicator.
 *       	| canHaveAsHasRestedOnePoint(getHasRestedOnePoint())
 *       
 * @invar  	Each Unit can have its isDefaultBehaviour indicator as its isDefaultBehaviour indicator.
 *       	| canHaveAsIsDefaultBehaviour(getIsDefaultBehaviour())
 *       
 * @invar  	The faction of each unit must be a valid faction for any unit.
 *       	| isValidFaction(getFaction())
 *       
 * @invar  	The experience of each Unit must be a valid experience for any Unit.
 *       	| isValidExperience(getExperience())
 *       
 * @invar  	Each Unit can have its isAlive indicator as its isAlive indicator.
 *       	| canHaveAsIsAlive(getIsAlive())
 * @invar	Each Unit must have a proper World to which it is attached.
 * 			| hasProperWorld(getWorld())
 */

public class Unit {
	
	/**
	 * Create a new Unit with a given name, strength, agility, toughness, weight, initial position, default behaviour and {@link World}.
	 * 
	 * @param  	name
	 *         	The name for this new Unit.
	 * @param 	strength
	 *         	The strength for this new Unit.
	 * @param  	agility
	 *         	The agility for this new Unit.
	 * @param  	toughness
	 *         	The toughness for this new Unit.   
	 * @param  	weight
	 *         	The weight for this new Unit.
	 * @param	initialPosition
	 * 			The initial position for this new Unit.
	 * @param	enableDefaultBehaviour
	 * 			The enableDefaultBehaviour for this new Unit.
	 * @param	world
	 * 			The {@link World} in which this Unit lives.
	 * 
	 * @throws 	IllegalArgumentException 
	 *       	A condition was violated or an error was thrown.
	 *       
	 * @effect	The world of this new Unit is set to the given world.
 	 * 			| this.setWorld(world)
	 * @effect 	The name of this new Unit is set to the given name.
	 *       	| this.setName(name)
	 * @effect	If the given strength is a valid initial property value for any Unit, the strength of this new Unit is set to the given strength.
	 * 			Otherwise, the strength of this new Unit is set to the default property value.
	 * 			| if (isValidInitialValue(strength))
	 * 			|	then this.setStrength(strength)
	 * 			| else this.setStrength(DEFAULT_PROPERTY_VALUE)
	 * @effect 	If the given agility is a valid initial property value for any Unit, the agility of this new Unit is set to the given agility.
	 * 			Otherwise, the agility of this new Unit is set to the default property value.
	 * 			| if (isValidInitialValue(agility))
	 * 			|	then this.setAgility(agility)
	 * 			| else this.setAgility(DEFAULT_PROPERTY_VALUE)
	 * @effect	If the given toughness is a valid initial property value for any Unit, the toughness of this new Unit 
	 * 			is set to the given toughness.
	 * 			Otherwise, the toughness of this new Unit is set to the default property value.
	 * 			| if (isValidInitialValue(toughness))
	 * 			|	then this.setToughness(toughness)
	 * 			| else this.setToughness(DEFAULT_PROPERTY_VALUE)
	 * @effect	If the given weight is a valid initial property value for any Unit, the weight of this new Unit is set to the given weight.
	 * 			Otherwise, the weight of this new Unit is set to the default weight.
	 * 			| if (isValidInitialValue(weight))
	 * 			| 	then this.setWeight(weight)
	 * 			| else this.setWeight(getDefaultWeight())
	 * @effect	Set the current hitpoints of this new Unit to the maximum hitpoints.
	 * 			| this.setCurrentHitpoints(getMaxHitpoints())
	 * @effect	Set the current staminapoints of this new Unit to the maximum staminapoints.
	 * 			| this.setCurrentStaminapoints(getMaxStaminapoints())
 	 * @effect 	The cubeCoordinates of this new Unit is set to the given initial position.
 	 *       	| this.setCubeCoordinates(initialPosition)
 	 * @effect	The isDefaultBehaviour of this new Unit is set to the given enableDefaultBehaviour.
 	 * 			| this.setIsDefaultBehaviour(enableDefaultBehaviour)
 	 * @effect	Set the experience of this new Unit to zero.
 	 * 			| this.setExperience(0)       
	 * @post	The maxHitpoints of this new Unit is equal to a rounded integer which is 200 times a 100th of its weight 
	 * 			times a 100th of its toughness.
	 * 			| new.getMaxHitpoints() == (int)Math.round(200*((double)this.weight/100)*((double)this.toughness/100))
	 * @post	The maxStaminapoints of this new Unit is equal to a rounded integer which is 200 times a 100th of its weight
	 * 			times a 100th of its toughness.
	 * 			| new.getMaxStaminaPoints() == (int)Math.round(200*((double)this.weight/100)*((double)this.toughness/100))
	 */
	public Unit(String name, int strength, int agility, int toughness, int weight, int[] initialPosition, boolean enableDefaultBehaviour
			, World world) throws IllegalArgumentException
	{
			setWorld(world);
			setName(name);
			
			if (! isValidInitialValue(strength))
				strength = DEFAULT_PROPERTY_VALUE;
			setStrength(strength);
			if (! isValidInitialValue(agility))
				agility = DEFAULT_PROPERTY_VALUE;
			setAgility(agility);
			if (! isValidInitialValue(toughness))
				toughness = DEFAULT_PROPERTY_VALUE;
			setToughness(toughness);
			if (! isValidInitialValue(weight))
				weight = getDefaultWeight();	
			setWeight(weight);
			
			this.maxHitpoints = ((int)Math.round(200*((double)this.weight/100)*((double)this.toughness/100)));
			setCurrentHitpoints(getMaxHitpoints());
			
			this.maxStaminaPoints = ((int)Math.round(200*((double)this.weight/100)*((double)this.toughness/100)));
			setCurrentStaminapoints(getMaxStaminapoints());
			
			setCubeCoordinates(initialPosition);
			setDefaultBehaviour(enableDefaultBehaviour);
			setExperience(0);
	}
	
	/**
	 * Initialize this new Unit with the given name, strength, agility, toughness, weight, initialPosition, enableDefaultBeahviour and
	 * no world.
	 * 
	 * @param 	name
	 * 			The name for this new Unit.
	 * @param 	strength
	 * 			The strength for this new Unit.
	 * @param 	agility
	 * 			The agility for this new Unit.
	 * @param 	toughness
	 * 			The toughness for this new Unit.
	 * @param 	weight
	 * 			The weight for this new Unit.
	 * @param 	initialPosition
	 * 			The initialPosition for this new Unit.
	 * @param 	enableDefaultBehaviour
	 * 			The enableDefaultBehaviour for this new Unit.
	 * @throws 	UnsupportedOperationException
	 * 			This method is deprecated, because a Unit needs a World for all of its calculations. 
	 * @note	We didn't delete this method because the Facade class still uses this method (and we aren't allowed to change that).
	 * 
	 */
	@Deprecated
	public Unit(String name, int strength, int agility, int toughness, int weight, int[] initialPosition, boolean enableDefaultBehaviour) 
			throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException("Please use the other constructor, which provides a World for the Unit to live in.");
	}
	
	/**
	 * Advance the time of the game.
	 * 
	 * @param 	duration
	 * 			The amount of time that has been passed.
	 * @throws 	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 */
	public void advanceTime(double duration) throws IllegalArgumentException
	{
		if ((duration <= 0.2 && duration >= 0) && isAlive()) // [FIXME] NORMALLY THIS SHOULD BE BETWEEN 0.2 (EXCLUSIVE) AND 0 (INCLUSIVE).
		{
			setCurrentRestingPeriod(getCurrentRestingPeriod() + duration); // Add time to the counter.
			
			// Resting every 180 seconds:
			if (getCurrentRestingPeriod() >= RESTING_PERIOD)
			{
				setCurrentRestingPeriod(0);
				rest();
			}
			
			// Checking experience:
			if (getExperience() >= 10)
			{
				setExperience(getExperience() - 10);
				if (getStrength() < MAX_PROPERTY_VALUE || getAgility() < MAX_PROPERTY_VALUE || getToughness() < MAX_PROPERTY_VALUE)
					increaseSkills();
			}
			
			// Falling:
			if (mustUnitFall(getCubeCoordinates()))
				setFallingState(true);
			
			if (isFalling())
			{
				if (!isMoving())
					moveToAdjacent(0, 0, -1);
			}
			// Resting:
			if (isResting())
			{
				setRestingDuration(getRestingDuration() + duration);
				
				if (getRestingDuration() >= 0.2) // Every 0.2 seconds we add a few points.
				{
					setRestingDuration(getRestingDuration() - 0.2);
					
					if (getCurrentHitpoints() == getMaxHitpoints()) // Check if hitpoints are full.
					{
						if (getCurrentStaminapoints() == getMaxStaminapoints()) // Check if staminapoints are full.
						{
							setResting(false);
							if (wasMoving())
							{
								setMovingState(true);
								setWasMoving(false);
							}
						}
						else
						{
							// Count down the initial period of 1 hitpoint recovery:
							if (!hasRestedInitialRecoveryPeriod())
							{
								setTempHitpoint(getTempHitpoint() + (getToughness() / 200.0)); // Count the temp hitpoint.
								
								if (getTempHitpoint() >= 1)
										setRecoveryState(true);
							}
								
							setTempStaminapoint(getTempStaminapoint() + (getToughness() / 100.0)); // Temporary staminapoint. Once this is 1, we add one staminapoint.
							if (getTempStaminapoint() >= 1)
							{
								setCurrentStaminapoints(getCurrentStaminapoints() + 1);
								setTempStaminapoint(0);
							}
						}
						
					}
					else
					{
						setTempHitpoint(getTempHitpoint() + (getToughness() / 200.0)); // Temporary hitpoint. Once this is 1, we add one hitpoint.
						
						if (getTempHitpoint() >= 1)
						{
							// Initial period of 1 hitpoint recovery: 
							if (!hasRestedInitialRecoveryPeriod())
								setRecoveryState(true);
							setCurrentHitpoints(getCurrentHitpoints() + 1);
							setTempHitpoint(0);
						}
					}
				}
			}
			
			// Attacking: 
			else if (isAttacking() || isDefending())
			{
				if (getFightingDuration() >= 1) // The fighting takes place for one second.
				{
					if (isAttacking())
						setIsAttacking(false);
					else
						setIsDefending(false);
					setFightingDuration(0);
					if (wasMoving())
					{
						setMovingState(true);
						setWasMoving(false);
						// Reset the orientation: 
						setOrientation(Math.atan2(getVelocity()[1], getVelocity()[0]));
					}
					
					setExperience(getExperience() + 20);
				}
				else
					setFightingDuration(getFightingDuration() + duration);
			}
			
			// Moving: 
			else if (isMoving())
			{
				// Moving to a cube: calculate the next cube in a path if we are moving to a cube further than an adjacent one and while we aren't 
				// currently moving to an adjacent cube.
				if (isMovingTo() && !isMovingToAdjacent()) 
				{
					findPath();
				}
				
				// Moving to adjacent cube: 
				for(int i = 0; i < getPosition().length; i++)
				{
					setPositionAt(i, getPosition()[i] + (getVelocity()[i]*duration));
				}
				
				// Sprinting: 
				if (isSprinting())
				{
					if (getCurrentStaminapoints() > 0)
					{
						if (getSprintingDuration() >= 0.1)
						{
							setCurrentStaminapoints(getCurrentStaminapoints() - 1);
							setSprintingDuration(0);
						}
						else
							setSprintingDuration(getSprintingDuration() + duration);
					}
					else
						stopSprinting();
				}
				
				// Check whether destination is reached: 
				if (destinationReached())
				{	
					if (isMovingToAdjacent())
					{
						setIsMovingToAdjacent(false); // Done moving to an adjacent cube.
						
						// Add experience points
						if (!isFalling())
							setExperience(getExperience() + 1);
					}
					
					// If the path is finished, we can exit the moving action:
					if (!isMovingTo())
					{
						setMovingState(false);
						//System.out.println("Stop with sprinting...");
						if (isSprinting())
							setSprintingState(false);
					}	
					
					setCubeCoordinates(getNextCoordinates()); // Correct the current cube coordinates.
				}
			}
			
			// Working:
			else if (isWorking())
			{
				//System.out.println("Working duration of this Unit: " + getWorkingDuration());
				if (getWorkingDuration() > 0)
					setWorkingDuration(getWorkingDuration() - duration);
				else
				{
					//System.out.println("Calculating result of work task...");
					// Several cases:
					double x = getTargetCube()[0] + (World.getCubeLength() / 2.0);
					double y = getTargetCube()[1] + (World.getCubeLength() / 2.0);
					double z = getTargetCube()[2] + (World.getCubeLength() / 2.0);
					double[] target = {x, y, z};
					Log log = logPresentAtCube(target);
					Boulder boulder = boulderPresentAtCube(target);
					boolean hasCompletedWork = true;
					
					// Target cube is wood:
					if (getWorld().getCubeType(getTargetCube()[0], getTargetCube()[1], getTargetCube()[2]) == 2)
					{
						//System.out.println("Target will now spawn a log...");
						getWorld().spawnLog(target);
					}
					
					// Target cube is rock:
					else if (getWorld().getCubeType(getTargetCube()[0], getTargetCube()[1], getTargetCube()[2]) == 1)
					{
						//System.out.println("Target will now spawn a boulder...");
						getWorld().spawnBoulder(target);
					}
					
					// Unit is currently carrying a Boulder:
					else if (isCarryingBoulder())
					{
						//System.out.println("Drop current boulder at target...");
						dropBoulder(target);
					}
					
					// Unit is currently carrying a Log:
					else if (isCarryingLog())
					{
						//System.out.println("Drop current log at target...");
						dropLog(target);
					}
					
					// The target cube is a workshop and a Log and a Boulder is available on this cube:
					else if (getWorld().getCubeType(getTargetCube()[0], getTargetCube()[1], getTargetCube()[2]) == 3 && log != null 
							&& boulder != null)
					{
						//System.out.println("Work at the workshop...");
						
						// Remove Boulder:
						boulder.setWorld(null);
						getWorld().removeBoulder(boulder);
						
						// Remove Log:
						log.setWorld(null);
						getWorld().removeLog(log);
						
						// Increase toughness, if possible:
						if (getToughness() < MAX_PROPERTY_VALUE)
						{
							setToughness(getToughness() + 1);
							setWeight(getWeight());
						}
					}
					
					// A boulder is present on the target cube:
					else if (boulder != null)
					{
						//System.out.println("Pick up the boulder at target...");
						setBoulder(boulder);
						setIsCarryingBoulder(true);
						boulder.setPosition(null);
					}
					
					// A log is present on the target cube:
					else if (log != null)
					{
						//System.out.println("Pick up the log at target...");
						setLog(log);
						setIsCarryingLog(true);
						log.setPosition(null);
					}
					
					else 
					{
						//System.out.println("No useful task was completed, thus no exp was gained...");
						hasCompletedWork = false;
					}
						
					
					if (hasCompletedWork)
						setExperience(getExperience() + 10);
					
					setWorking(false);
					
				}
			}
			
			// Default behaviour:
			else if (isDefaultBehaviourEnabled())
			{
				if (getTask() != null)
				{
					
				}
				else
				{
					Task newTask = getFaction().getScheduler().getTaskWithHighestPriority();
					if (newTask != null)
					{
						
					}
					else
						SelectBehaviour();
				}
			}
		}
//		else
//			throw new IllegalArgumentException("The duration of advanceTime was too large: " + duration);
	}
	
	/**
	 * Move this unit to an adjacent cube.
	 * 
	 * @param 	dx
	 * 			The difference between the x-component of the current cube and the x-component of the destination cube.
	 * @param 	dy
	 * 			The difference between the y-component of the current cube and the y-component of the destination cube.
	 * @param 	dz
	 * 			The difference between the z-component of the current cube and the z-component of the destination cube.
	 * 
	 * @throws	IllegalArgumentException
	 * 			A condition was violated or an error was thrown. 
	 * 			If this Unit is already moving to an adjacent cube and if the function is called upon with all of its parameters equal to 0, 
	 * 			an exception will be thrown.
	 * 			| if (!isMovingToAdjacent() && !(dx == 0 && dy == 0 && dz == 0))
	 * 
	 * @effect	The isMoving indicator of this Unit is enabled if the Unit's isMovingToAdjacent indicator is disabled, and if the given dx, dy
	 * 			and dz are equal to zero and if the Unit has finished the initial resting period and if the Unit isn't currently moving already.
	 * 			| if (!isMovingToAdjacent() && !(dx == 0 && dy == 0 && dz == 0) && hasRestedOnePoint() && !isMoving())
	 * 			| then this.setIsMoving(true)
	 * 
	 * @effect	The isResting indicator of this Unit is disabled if the Unit's isMovingToAdjacent indicator is disabled, and if the given dx, dy
	 * 			and dz are equal to zero and if the Unit has finished the initial resting period and if the Unit is currently resting.
	 * 			| if (!isMovingToAdjacent() && !(dx == 0 && dy == 0 && dz == 0) && hasRestedOnePoint() && isResting())
	 * 			| then this.setIsResting(false)
	 * 
	 * @effect	The isWorking indicator of this Unit is disabled if the Unit's isMovingToAdjacent indicator is disabled, and if the given dx, dy
	 * 			and dz are equal to zero and if the Unit has finished the initial resting period and if the Unit is currently working.
	 * 			| if (!isMovingToAdjacent() && !(dx == 0 && dy == 0 && dz == 0) && hasRestedOnePoint() && isWorking())
	 * 			| then this.setIsWorking(false)
	 * 
	 * @effect	The isMovingToAdjacent indicator of this Unit is enabled if the Unit's isMovingToAdjacent indicator is disabled, 
	 * 			and if the given dx, dy and dz are equal to zero and if the Unit has finished the initial resting period.
	 * 			| if (!isMovingToAdjacent() && !(dx == 0 && dy == 0 && dz == 0) && hasRestedOnePoint())
	 * 			| then this.setIsMovingToAdjacent(true)
	 * 
	 * @effect 	The deltaNewPositions of this Unit is set to the given dx, dy, dz collected in the array dCoordinates, 
	 * 			if the Unit's isMovingToAdjacent indicator is disabled, and if the given dx, dy and dz are equal to zero 
	 * 			and if the Unit has finished the initial resting period and if the newCoordinates are valid coordinates.
	 * 			| if (!isMovingToAdjacent() && !(dx == 0 && dy == 0 && dz == 0) && hasRestedOnePoint() && isValidCubeCoordinates(newCoordinates))
	 *       	| then this.setDeltaNewPositions(dCoordinates)
	 *       
	 * @effect	The nextCubeCoordinates of this Unit is set to the calculated newCoordinates, if the Unit's isMovingToAdjacent indicator 
	 * 			is disabled, and if the given dx, dy and dz are equal to zero and if the Unit has finished the initial resting period
	 * 			and if the newCoordinates are valid coordinates.
	 * 			| if (!isMovingToAdjacent() && !(dx == 0 && dy == 0 && dz == 0) && hasRestedOnePoint() && isValidCubeCoordinates(newCoordinates))
	 * 			| then this.setNextCubeCoordinates(newCoordinates)
	 *       
	 * @effect 	The base speed of this Unit is set to the calculated base speed newBaseSpeed, if the Unit's isMovingToAdjacent indicator 
	 * 			is disabled, and if the given dx, dy and dz are equal to zero and if the Unit has finished the initial resting period
	 * 			and if the newCoordinates are valid coordinates.
	 * 			| if (!isMovingToAdjacent() && !(dx == 0 && dy == 0 && dz == 0) && hasRestedOnePoint() && isValidCubeCoordinates(newCoordinates))
	 *       	| this.setBaseSpeed(newBaseSpeed)
	 *      	
	 * @effect	The walking speed of this Unit is set to either 1/2 times the base speed, 1.2 times the base speed, or the base speed itself
	 * 			depending whether the difference between the current z coordinate and the new z coordinate is either -1, 0 or 1 and
	 * 			if the Unit's isMovingToAdjacent indicator is disabled, and if the given dx, dy and dz are equal to zero and 
	 * 			if the Unit has finished the initial resting period and if the newCoordinates are valid coordinates.
	 * 			| if (!isMovingToAdjacent() && !(dx == 0 && dy == 0 && dz == 0) && hasRestedOnePoint() && isValidCubeCoordinates(newCoordinates))
	 * 			| then
	 * 			| 	if (getCubeCoordinates()[2] - newCoordinates[2] == -1)
	 * 			| 	then this.setWalkingSpeed(getBaseSpeed() / 2)
	 * 			| 	else if (getCubeCoordinates()[2] - newCoordinates[2] == 1)
	 * 			| 	then this.setWalkingSpeed(getBaseSpeed() * 1.2)
	 * 			| 	else this.setwalkingSpeed(getBaseSpeed())
	 * 
	 * @effect	The sprintSpeed of this Unit is set to 2 times its walking speed if the Unit's isMovingToAdjacent indicator 
	 * 			is disabled, and if the given dx, dy and dz are equal to zero and if the Unit has finished the initial resting period
	 * 			and if the newCoordinates are valid coordinates.
	 * 			| if (!isMovingToAdjacent() && !(dx == 0 && dy == 0 && dz == 0) && hasRestedOnePoint() && isValidCubeCoordinates(newCoordinates))
	 * 			| then this.setSprintSpeed(getWalkingSpeed() * 2)
	 * 
	 * @effect 	The velocity of this Unit is set to the calculated velocity using the new coordinates of the Unit if the Unit's isMovingToAdjacent
	 * 			indicator is disabled, and if the given dx, dy and dz are equal to zero and if the Unit has finished the initial resting period
	 * 			and if the newCoordinates are valid coordinates.
	 * 			| if (!isMovingToAdjacent() && !(dx == 0 && dy == 0 && dz == 0) && hasRestedOnePoint() && isValidCubeCoordinates(newCoordinates))
	 * 			| then this.setVelocity(newCoordinates)
	 * 
	 * @effect	The orientation of this Unit is set to the arctagent of the x- and y-component of the Unit's velocity if the Unit's 
	 * 			isMovingToAdjacent indicator is disabled, and if the given dx, dy and dz are equal to zero and if the Unit has 
	 * 			finished the initial resting period and if the newCoordinates are valid coordinates.
	 * 			| if (!isMovingToAdjacent() && !(dx == 0 && dy == 0 && dz == 0) && hasRestedOnePoint() && isValidCubeCoordinates(newCoordinates))
	 * 			| this.setOrientation(Math.atan2(getVelocity()[1], getVelocity()[0]))
	 * 
	 * @effect	The current speed of this Unit is either set to its walking speed or its sprinting speed depending on whether the Unit is sprinting
	 * 			if the Unit's isMovingToAdjacent indicator is disabled, and if the given dx, dy and dz are equal to zero 
	 * 			and if the Unit has finished the initial resting period and if the newCoordinates are valid coordinates.
	 * 			| if (!isMovingToAdjacent() && !(dx == 0 && dy == 0 && dz == 0) && hasRestedOnePoint() && isValidCubeCoordinates(newCoordinates))
	 * 			| then 
	 * 			| 	if (isSprinting())
	 * 			| 	then this.setCurrentSpeed(getSprintSpeed())
	 * 			| 	else this.setCurrentSpeed(getWalkingSpeed())
	 */
	public void moveToAdjacent(int dx, int dy, int dz) throws IllegalArgumentException, IllegalStateException
	{
		// REVISION:
		if (!isAlive())
			throw new IllegalStateException("Unit cannot move while its dead!");
		// This method will only work if the Unit isn't currently already moving and if the destination cube isn't the same cube.
		if (!isMovingToAdjacent() && !(dx == 0 && dy == 0 && dz == 0) && hasRestedInitialRecoveryPeriod())
		{
			try {
				// Disable behaviours and enable isMoving if it isn't already: 
				if (!isMoving())
					setMovingState(true);
				if (isResting())
					setResting(false);
				if (isWorking())
					setWorking(false);
				setIsMovingToAdjacent(true);
				
				// Calculate new coordinates:
				int[] dCoordinates = {dx, dy, dz};
	
				int newXPos = getCubeCoordinates()[0] + dCoordinates[0];
				int newYPos = getCubeCoordinates()[1] + dCoordinates[1];
				int newZPos = getCubeCoordinates()[2] + dCoordinates[2];
				
				int[] newCoordinates = {newXPos, newYPos, newZPos};
				
				// Check whether the new cube coordinates are valid. If so, calculate the speeds and orientation:
				if (canHaveAsCubeCoordinates(newCoordinates))
				{
					// Set the delta coordinates:
					setDeltaNewPositions(dCoordinates);
					
					// Set the next cube coordinates to the new coordinates:
					setNextCoordinates(newCoordinates);
					
					// Set walking speed:
					setWalkingSpeed(dz);
					
					// Set the velocity:
					if (!isFalling())
						setVelocity(newCoordinates);
					else
					{
						for (int i = 0; i < velocity.length; i++)
						{
							if (i == 2)
								this.velocity[i] = -3;
							else
								this.velocity[i] = 0;
						}
						setCurrentHitpoints(getCurrentHitpoints() - 10); // If the Unit is falling, subtract 10 hitpoints
					}
						
					// Set the orientation: 
					setOrientation(Math.atan2(getVelocity()[1], getVelocity()[0]));
					
					// Set the current speed:
					if (isSprinting())
						setCurrentSpeed(getSprintSpeed());
					else
						setCurrentSpeed(getWalkingSpeed());
				}
				else 
					throw new IllegalArgumentException("Can't move towards this cube.");
			}
			catch (IllegalArgumentException e)
			{
				// Disable movement:
				setIsMovingToAdjacent(false);
				setMovingState(false);
				//throw e;
			}
		}
	}
	
	/**
	 * Move this unit to the given cube.
	 * 
	 * @param 	cube
	 * 			The given cube to move to.
	 * 
	 * @throws 	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 * @throws	IllegalStateException
	 * 			This Unit is dead.
	 * 			| !isAlive()
	 * 
	 * @effect	The isMovingTo indicator of this Unit is enabled, if the Unit has finished the initial resting period.
	 * 			| if (hasRestedOnePoint())
	 * 			| then this.setIsMovingTo(true)
	 * 
	 * @effect	The isMoving indicator of this Unit is enabled, only if it was disabled and if the Unit has finised the initial resting period.
	 * 			| if (!isMoving() && hasRestedOnePoint())
	 * 			| then this.setIsMoving(true)
	 * 
	 * @effect	The isResting indicator of this Unit is disabled, only if it was enabled and if the Unit has finised the initial resting period.
	 * 			| if (isResting() && hasRestedOnePoint())
	 * 			| then this.setIsResting(false)
	 * 
	 * @effect	The isWorking indicator of this Unit is disabled, only if it was enabled and if the Unit has finised the initial resting period.
	 * 			| if (isWorking() && hasRestedOnePoint())
	 * 			| then this.setIsWorking(false)
	 * 
	 * @effect	The destinationCube of this Unit is set to the given cube, if the Unit has finised the initial resting period.
	 * 			| if (hasRestedOnePoint())
	 * 			| then this.setDestinationCube(cube)
	 */
	public void moveTo(int[] cube) throws IllegalArgumentException
	{
		if (!isAlive())
			throw new IllegalStateException("Cannot move to a cube, Unit is dead!");
		if (hasRestedInitialRecoveryPeriod() && (!Arrays.equals(cube, getCubeCoordinates())))
		{
			try
			{
				if (!isMoving())
					setMovingState(true);
				if (isResting())
					setResting(false);
				if (isWorking())
					setWorking(false);
				
				setMovingTo(true);
				setDestinationCube(cube);
			}
			catch (IllegalArgumentException e)
			{
				setMovingTo(false);
				setMovingState(false);
				//throw e;
			}
		}
		
	}
	
	/**
	 * [TODO] Complete this function.
	 * [FIXME] Add a checker to check values as well in (c,n| n <= n0).
	 * @param position
	 * @param n
	 */
	private ArrayList<Tuple<int[], Integer>> search(Tuple<int[], Integer> startTuple, ArrayList<Tuple<int[], Integer>> tempPaths)
	{
		// let l be a List of cube positions.
		List<int[]> l = new ArrayList<int[]>();
		
		for (int i = -1; i <= 1; i++)
		{
			for (int j = -1; j <= 1; j++)
			{
				for (int k = -1; k <= 1; k++)
				{
					int x = startTuple.getFirstValue()[0] + i;
					int y = startTuple.getFirstValue()[1] + j;
					int z = startTuple.getFirstValue()[2] + k;
					
					int[] neighBouringCube = {x, y, z};
					
					/* such that each cube position in the list:
					 *	-	is neighbouring c0 (startTuple): neighBouringCube != startTuple.getFirstValue())
					 *	-	is of passable terrain: canHaveAsCubeCoordinates(neighBouringCube)
					 *	-	is neighbouring solid terrain: getWorld().hasSolidNeighbouringCube(x, y, z)
					 *	-	(c,n| n <= n0) is not an element of Queue: Check whether the queue doesn't already have a tuple containing the given
					 *		coordinate.
					 */ 
					if (getWorld().hasSolidNeighbouringCube(x, y, z) && canHaveAsCubeCoordinates(neighBouringCube) 
							&& (neighBouringCube != startTuple.getFirstValue()) && !hasTupleWithGivenCoordinates(neighBouringCube, pathQueue)
							&& !hasTupleWithGivenCoordinates(neighBouringCube, tempPaths))
					{
						l.add(neighBouringCube);
					}
				}
			}
		}
		
		ArrayList<Tuple<int[], Integer>> results = new ArrayList<>();
		
		for (int i = 0; i < l.size(); i++)
		{
			Tuple<int[], Integer> tuple = new Tuple<int[], Integer>(l.get(i), (startTuple.getSecondValue() + 1));
			results.add(tuple);
		}
		
		return results;
	}

	/**
	 * [TODO]
	 * @throws IllegalArgumentException
	 */
	private void findPath() throws IllegalArgumentException
	{
		// Calculate the Path: 
		//System.out.println("Destination cube is: " + "[" + getDestinationCube()[0] + ", " + getDestinationCube()[1] + ", " + getDestinationCube()[2] + "]");
		pathQueue = new ArrayList<Tuple<int[], Integer>>();
		Tuple<int[], Integer> destination = new Tuple<int[], Integer>(getDestinationCube(), 0);
		pathQueue.add(destination);
		
		Iterator<Tuple<int[], Integer>> iter;
		ArrayList<Tuple<int[], Integer>> searchedDestinations = new ArrayList<>();
		while (!hasTupleWithGivenCoordinates(getCubeCoordinates(), pathQueue))
		{
			searchedDestinations = new ArrayList<>();
			iter = pathQueue.iterator();
			while(iter.hasNext())
			{
				Tuple<int[], Integer> next = iter.next();
				searchedDestinations.addAll(search(next, searchedDestinations));
			}
			
			if (searchedDestinations.isEmpty())
				break;
			else
				pathQueue.addAll(searchedDestinations);
		}

		//System.out.println("Size of possible paths: " + pathQueue.size());
		
		// Check is we can still move...
		if (hasTupleWithGivenCoordinates(getCubeCoordinates(), pathQueue))
		{
			//System.out.println("This Unit can move to its destination.");
			Tuple<int[], Integer> next = findNextCube(getCubeCoordinates());
			// If the returned tuple from the queue is null, pathing is terminated.
			if (next.getFirstValue() == null)
			{
				//System.out.println("Pathing will now end, because there isn't a next cube.");
				setMovingTo(false);
				setMovingState(false);
				throw new IllegalArgumentException("Pathing will end here.");
			}
			
			else
			{
				int dx;
				int dy;
				int dz;
				
				if (getCubeCoordinates()[0] == next.getFirstValue()[0])
					dx = 0;
				else if (getCubeCoordinates()[0] < next.getFirstValue()[0])
					dx = 1;
				else
					dx = -1;
				
				if (getCubeCoordinates()[1] == next.getFirstValue()[1])
					dy = 0;
				else if (getCubeCoordinates()[1] < next.getFirstValue()[1])
					dy = 1;
				else
					dy = -1;
				
				if (getCubeCoordinates()[2] == next.getFirstValue()[2])
					dz = 0;
				else if (getCubeCoordinates()[2] < next.getFirstValue()[2])
					dz = 1;
				else
					dz = -1;
				
				if (dx == 0 && dy == 0 && dz == 0)
				{
					setMovingTo(false);
				}

				else 
					moveToAdjacent(dx, dy, dz);
			}
		}
		else
		{
			setMovingTo(false);
			setMovingState(false);
			//System.out.println("Location is impossible to reach! Terminating movement...");
		}
			
	}
	
	/**
	 * Check whether the given ArrayList contains a Tuple with the given coordinates.
	 * 
	 * @param 	coordinates
	 * 			The given coordinates to check upon.
	 * @param 	list
	 * 			The given list to check.
	 * @return	True if and only if there is a tuple in the given list with the given coordinates as its first value.
	 * 			| (for each Tuple in list:
	 * 			| 	if (Tuple.getFirstValue()[0] == coordinates[0] && currentTuple.getFirstValue()[1] == coordinates[1] 
	 * 			|			&& currentTuple.getFirstValue()[2] == coordinates[2]) 
	 * 			| 		result == true)
	 */
	private boolean hasTupleWithGivenCoordinates(int[] coordinates, ArrayList<Tuple<int[], Integer>> list)
	{
		Iterator<Tuple<int[], Integer>> iter = list.iterator();
		while(iter.hasNext())
		{
			Tuple<int[], Integer> currentTuple = iter.next();
			if (currentTuple.getFirstValue()[0] == coordinates[0] && currentTuple.getFirstValue()[1] == coordinates[1]
					&& currentTuple.getFirstValue()[2] == coordinates[2])
					return true;
		}
		return false;
	}
	
	/**
	 * Get the next cube in the path, based on the given coordinates. This method will return a non-empty Tuple, if a cube is available.
	 * 
	 * @param 	coordinates
	 * 			The coordinates to use to find the next cube.
	 * @return	A non-empty Tuple from the pathQueue variable, if and only if there is a Tuple with coordinates that are neihbouring the
	 * 			given coordinates and if the destination cost (the second value of the Tuple) is the lowest of all the Tuple with the same
	 * 			coordinates. If there is no Tuple found, an empty Tuple will be returned with the destination cost of the upper boundary
	 * 			this World.
	 * 			| Tuple resultTuple = new Tuple(null, getWorld().getNbCubesX())
	 * 			| (for each Tuple in pathQueue: 
	 * 			|		 if (isNeighbouringCube(Tuple.getFirstValue()))
	 * 			|			if (Tuple.getSecondValue() < result.getSecondValue())
	 * 			|				resultTuple = Tuple)
	 * 			| result == resultTuple
	 */
	private Tuple<int[], Integer> findNextCube(int[] coordinates)
	{
		Iterator<Tuple<int[], Integer>> iter = pathQueue.iterator();
		Tuple<int[], Integer> result = new Tuple<int[], Integer>(null, getWorld().getNbCubesX());
		while (iter.hasNext())
		{
			Tuple<int[], Integer> next = iter.next();
			if (World.isNeighbouringCube(getCubeCoordinates(), next.getFirstValue()))
			{
				if (next.getSecondValue() < result.getSecondValue())
					result = next;
			}
		}
		return result;
	}
	
	/**
	 * Variable registering the list with all possible paths to move to.
	 */
	private ArrayList<Tuple<int[], Integer>> pathQueue = new ArrayList<Tuple<int[], Integer>>();
	
	/**
	 * Check whether the Unit has reached its destination.
	 * TODO Finish the Return statement (or change method)
	 * @return	True if and only if the current Unit position components are greater or lower (depends on direction) or equal to
	 * 			the cube coordinates + the cube length divided by 2.
	 * 			| result == hasReached
	 * 			| for 
	 * 			| if ...
	 * 			| then hasReached = true 
	 * 			| else .. 
	 */
	private boolean destinationReached()
	{
		boolean hasReached = false;
		if (getDeltaNewPositions() == null)
			return hasReached;
		for (int i = 0; i < getPosition().length; i++)
		{
			// The new unit position component is higher than the current one, thus we check with operator <=
			if (getDeltaNewPositions()[i] == 1)
			{
				if (getPosition()[i] <= (getNextCoordinates()[i] + (World.getCubeLength() / 2.0)))
				{
					hasReached = false;
					break;
				}
				else
					hasReached = true;
			}
			// The new unit position component is lower than the current one, thus we check with operator >=
			else if (getDeltaNewPositions()[i] == -1)
			{
				if (getPosition()[i] >= (getNextCoordinates()[i] + (World.getCubeLength() / 2.0)))
				{
					hasReached = false;
					break;
				}
				else
					hasReached = true;
			}
		}
		
		return hasReached;
	}
	
	/**
	 * Increase one of the following skills of this Unit: strength, agility or toughness.
	 * 
	 * [TODO]	effects, posts, etc.
	 */
	private void increaseSkills()
	{	
		int oldStrength = getStrength();
		int oldAgility = getAgility();
		int oldToughness = getToughness();
		
		while (oldStrength == getStrength() && oldAgility == getAgility() && oldToughness == getToughness())
		{
			int choiceOfSkill = new Random().nextInt(3);
			switch (choiceOfSkill)
			{
			case 0:
				if (getStrength() < MAX_PROPERTY_VALUE)
					setStrength(getStrength() + 1);
				break;
			case 1:
				if (getAgility() < MAX_PROPERTY_VALUE)
					setAgility(getAgility() + 1);
				break;
			case 2:
				if (getToughness() < MAX_PROPERTY_VALUE)
					setToughness(getToughness() + 1);
				break;
			}
		}
		setWeight(getWeight());
	}
	
	/**
	 * Check whether there is a Log present at the given targetCube.
	 * 
	 * @param 	targetCube
	 * 			The given cube to check.
	 * 
	 * @return	A Log that is effective if there is a Boulder with the given position that is a part of this Unit's World 
	 * 			collection of Logs. 
	 * 			| while (getWorld().getAllLogs().iterator().hasNext())
	 * 			| 	if (Arrays.equals(getWorld().getAllLogs().iterator().next().getPosition(), targetCube)
	 * 			|		then result == getWorld().getAllLogs().iterator().next()
	 * 
	 * @return	A Log that is ineffective if there isn't a Boulder with the given position that is a part of this Unit's World collection
	 * 			of Log.
	 * 			| while (getWorld().getAllLogs().iterator().hasNext())
	 * 			| 	if (Arrays.equals(getWorld().getAllLogs().iterator().next().getPosition(), targetCube)
	 * 			|		then result == getWorld().getAllLogs().iterator().next()
	 * 			| result == null
	 */
	private Log logPresentAtCube(double[] targetCube)
	{
		Iterator<Log> iterator = getWorld().getAllLogs().iterator(); 
		Log currentLog;
		while (iterator.hasNext())
		{
			currentLog = iterator.next();
			if (Arrays.equals(currentLog.getPosition(), targetCube))
				return currentLog;
		}
		return null;
	}
	
	/**
	 * Check whether there is a Boulder present at the given targetCube.
	 * 
	 * @param 	targetCube
	 * 			The given cube to check.
	 * 
	 * @return	A Boulder that is effective if there is a Boulder with the given position that Boulder is a part of this Unit's World 
	 * 			collection of Boulders. 
	 * 			| while (getWorld().getAllBoulders().iterator().hasNext())
	 * 			| 	if (Arrays.equals(getWorld().getAllBoulders().iterator().next().getPosition(), targetCube)
	 * 			|		then result == getWorld().getAllBoulders().iterator().next()
	 * 
	 * @return	A Boulder that is ineffective if there isn't a Boulder with the given position that is a part of this Unit's World collection
	 * 			of Boulders.
	 * 			| while (getWorld().getAllBoulders().iterator().hasNext())
	 * 			| 	if (Arrays.equals(getWorld().getAllBoulders().iterator().next().getPosition(), targetCube)
	 * 			|		then result == getWorld().getAllBoulders().iterator().next()
	 * 			| result == null
	 */
	private Boulder boulderPresentAtCube(double[] targetCube)
	{
		Iterator<Boulder> iterator = getWorld().getAllBoulders().iterator();
		Boulder currentBoulder;
		while (iterator.hasNext())
		{
			currentBoulder = iterator.next();
			if(Arrays.equals(currentBoulder.getPosition(), targetCube))
				return currentBoulder;
		}
		return null;
	}
	
	/**
	 * Check whether there is a Unit of this Unit's World available in the neighbouring cubes.
	 * 
	 * @return	A Unit that is effective if and only if there is a Unit in the neighbouring cubes and if that Unit is from a different Faction.
	 * 
	 * [TODO] Formal specifications
	 */
	private Unit isNeighbouringUnitPresent()
	{
		for (int i = -1; i <= 1; i++)
		{
			for (int j = -1; j <= 1; j++)
			{
				for (int k = -1; k <= 1; k++)
				{
					int x = getCubeCoordinates()[0] + i;
					int y = getCubeCoordinates()[1] + j;
					int z = getCubeCoordinates()[2] + k;
					
					int[] position = {x, y, z};
					if (x != 0 && y != 0 && z != 0)
					{
						Iterator<Unit> iterator = getWorld().getAllUnits().iterator();
						while (iterator.hasNext())
						{
							Unit currentUnit = iterator.next();
							if (Arrays.equals(position, currentUnit.getCubeCoordinates()) && currentUnit.getFaction() != getFaction())
								return currentUnit;
						}
					}
				}
			}
		}
		return null;
	}
	
	private void SelectBehaviour() throws IllegalStateException
	{
		int choice = new Random().nextInt(4);
		int x, y, z;
		switch (choice)
		{
		// Move to random position:
		case 0:
			x = new Random().nextInt(getWorld().getNbCubesX());
			y = new Random().nextInt(getWorld().getNbCubesY());
			z = new Random().nextInt(getWorld().getNbCubesZ());
			boolean choiceSprint = new Random().nextBoolean();
			int[] cube = {x, y, z};
			moveTo(cube);
			if (choiceSprint)
				startSprinting();
			break;
		// Work:	
		case 1:
			int i = new Random().nextInt(3) - 1;
			int j = new Random().nextInt(3) - 1;
			int k = new Random().nextInt(3) - 1;
			
			x = getCubeCoordinates()[0] + i;
			y = getCubeCoordinates()[1] + j;
			z = getCubeCoordinates()[2] + k;
			
			workAt(x, y, z);
			
			break;
		// Rest:	
		case 2:
			rest();
			break;
		// Fight potential enemies:
		case 3:
			Unit potentialEnemy = isNeighbouringUnitPresent();
			if (potentialEnemy != null)
			{
				attack(potentialEnemy);
				potentialEnemy.defend(this);
			}
			break;
		// Error catching:
		default:
			throw new IllegalStateException("Error with default behaviour choice.");					
		}
	}
	
	
	private void executeTask(Task task, double duration)
	{
		setTask(task);
		
	}
	
	/**
	 * Let this Unit die.
	 * 
	 * @throws	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 * 
	 * [TODO]	Effects, post conditions, etc.
	 * [FIXME] 	Add checker to see if hitpoints is zero.
	 */
	public void die() throws IllegalArgumentException
	{
		if (isCarryingLog())
			dropLog(getPosition());
		else if (isCarryingBoulder())
			dropBoulder(getPosition());
		
		// Disable all behaviour:
		setMovingState(false);
		setWorking(false);
		setIsAttacking(false);
		setIsDefending(false);
		setResting(false);
		setDefaultBehaviour(false);
		
		// Kill the Unit:
		this.isAlive = false;
		
		// Delete the Unit:
		setFaction(null);
		setWorld(null);
	}
	
	/**
	 * Check whether the given initial value (for strength, agility, weight or toughness) is a valid initial value for any Unit.
	 * 
	 * @param 	value
	 * 			The initial value to check.
	 * @return	True if and only if the initial value is between 25 and 100 (inclusive).
	 * 			| result == ((value >= 25) && (value <= 100))
	 */
	public static boolean isValidInitialValue(int value)
	{
		return ((value >= 25) && (value <= 100));
	}
	
	/**
	 * Check whether the given property value is a valid property value for any Unit.
	 * 
	 * @param 	value
	 * 			The property value to check.
	 * @return	True if and only if the given property value is between the minimal and maximum property value.
	 * 			| result == ( (value >= MIN_PROPERTY_VALUE) && (value <= MAX_PROPERTY_VALUE) )
	 */
	public static boolean isValidPropertyValue(int value)
	{
		return ((value >= MIN_PROPERTY_VALUE) && (value <= MAX_PROPERTY_VALUE));
	}
	
	/**
	 * Variable registering the default property value for this Unit.
	 */
	private final static int DEFAULT_PROPERTY_VALUE = 50;
	
	/**
	 * Constant registering the maximum value for strength, weight, agility and toughness.
	 */
	private final static int MAX_PROPERTY_VALUE = 200;
	
	/**
	 * Constant registering the minimum value for strength, weight, agility and toughness.
	 */
	private final static int MIN_PROPERTY_VALUE = 1;
	
	/**
	 * Return the name of this Unit.
	 */
	@Basic @Raw
	public String getName() 
	{
		return this.name;
	}
	
	/**
	 * Check whether the given name is a valid name for any Unit.
	 *  
	 * @param  	name
	 *         	The name to check.
	 * @return 	True if and only if the name is more than 2 characters long, starts with an uppercase letter and uses 
	 * 			only letters, spaces and quotes (both single and double).
	 * 			| if ( (name.length() >= MIN_NAMELENGTH) && Character.isUpperCase(name.chartAt(0)) )
	 * 			|	then for each I in 0..name.length():
	 * 			|		let
	 * 			|			current = name.charAt(i)
	 * 			|		in
	 * 			|			if  (!(Character.isLetter(current) || (current == '"') || (current == '\'') || Character.isWhitespace(current)))
	 * 			|				then result == false;
	 * 			|	result == true;
	 * 			| else
	 * 			|	result == false;
	 */
	public static boolean isValidName(String name)
	{
		if(name.length() >= MIN_NAMELENGTH && Character.isUpperCase(name.charAt(0)))
		{
			for(int i=0; i<name.length(); i++)
			{
				char current = name.charAt(i);
				if (!(Character.isLetter(current) || (current == '"') || (current == '\'') || Character.isWhitespace(current)))
					return false;
			}
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Set the name of this Unit to the given name.
	 * 
	 * @param  	name
	 *         	The new name for this Unit.
	 * @post   	The name of this new Unit is equal to the given name.
	 *       	| new.getName() == name
	 * @throws 	IllegalArgumentException
	 *         	The given name is not a valid name for any Unit.
	 *       	| !isValidName(getName())
	 */
	@Raw
	public void setName(String name) throws IllegalArgumentException 
	{
		if (!isValidName(name))
			throw new IllegalArgumentException("The given name isn't valid for this Unit.");
		this.name = name;
	}
	
	/**
	 * Variable registering the name of this Unit.
	 */
	private String name;
	
	/**
	 * Constant registering the minimal length of a name.
	 */
	private final static int MIN_NAMELENGTH = 2;
		
	/**
	 * Return the strength of this Unit.
	 */
	@Basic @Raw
	public int getStrength() 
	{
		return this.strength;
	}
	
	/**
	 * Set the strength of this Unit to the given strength.
	 * 
	 * @param  	strength
	 *         	The new strength for this Unit.
	 * @post   	If the given strength is a valid property value for any Unit, the strength of this new Unit is 
	 * 			equal to the given strength.
	 *       	| if (isValidPropertyValue(strength))
	 *       	|   then new.getStrength() == strength
	 */
	@Raw
	public void setStrength(int strength) 
	{
		if (isValidPropertyValue(strength))
			this.strength = strength;
	}
	
	/**
	 * Variable registering the strength of this Unit.
	 */
	private int strength;
	
	/**
	 * Return the agility of this Unit.
	 */
	@Basic @Raw
	public int getAgility() 
	{
		return this.agility;
	}
	
	/**
	 * Set the agility of this Unit to the given agility.
	 * 
	 * @param  	agility
	 *         	The new agility for this Unit.
	 * @post   	If the given agility is a valid property value for any Unit, the agility of this 
	 * 			new Unit is equal to the given agility.
	 *       	| if (isValidPropertyValue(agility))
	 *       	|   then new.getAgility() == agility
	 */
	@Raw
	public void setAgility(int agility) 
	{
		if (isValidPropertyValue(agility))
			this.agility = agility;
	}
	
	/**
	 * Variable registering the agility of this Unit.
	 */
	private int agility;
	
	/**
	 * Return the toughness of this Unit.
	 */
	@Basic @Raw
	public int getToughness() 
	{
		return this.toughness;
	}
	
	/**
	 * Set the toughness of this Unit to the given toughness.
	 * 
	 * @param  	toughness
	 *         	The new toughness for this Unit.
	 * @post   	If the given toughness is a valid property value for any Unit, 
	 * 			the toughness of this new Unit is equal to the given toughness.
	 *       	| if (isValidPropertyValue(toughness))
	 *       	|   then new.getToughness() == toughness
	 */
	@Raw
	public void setToughness(int toughness) 
	{
		if (isValidPropertyValue(toughness))
			this.toughness = toughness;
	}
	
	/**
	 * Variable registering the toughness of this Unit.
	 */
	private int toughness;
	
	// ----------------------
	// |					|
	// |					|
	// |	  WEIGHT		|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the weight of this Unit.
	 */
	@Basic @Raw
	public int getWeight()
	{
		return this.weight;
	}
	
	/**
	 * Check whether this Unit can have the given weight as its weight.
	 *  
	 * @param  	weight
	 *         	The weight to check.
	 * @return 	True if and only if the given weight is a valid property value for any Unit and if the given weight 
	 * 			is higher or equal than the default weight.
	 *       	| result == ( isValidProperyValue(weight) && (weight >= getDefaultWeight()) )
	*/
	public boolean canHaveAsWeight(int weight) 
	{
		return isValidPropertyValue(weight) && (weight >= getDefaultWeight());
	}
	
	/**
	 * Set the weight of this Unit to the given weight or the default weight.
	 * 
	 * @param  	weight
	 *         	The new weight for this Unit.
	 * @post   	If this Unit can have the given weight as its weight, the weight of this new Unit is equal to the given weight. 
	 * 			Otherwise, the weight of this new Unit is equal to the default weight.
	 *       	| if (canHaveAsWeight(weight))
	 *       	|   then new.getWeight() == weight
	 *       	| else
	 *       	| 	new.getWeight() == getDefaultWeight()
	 */
	@Raw
	public void setWeight(int weight) 
	{
		if (canHaveAsWeight(weight))
			this.weight = weight;
		else
			this.weight = getDefaultWeight();
	}
	
	/**
	 * Variable registering the weight of this Unit.
	 */
	private int weight;

	/**
	 * Return the default weight of this Unit, which is the sum of its strength and agility divided by 2.
	 */
	@Basic @Raw @Model
	private int getDefaultWeight() 
	{
		return ( (getStrength() + getAgility()) / 2 );
	}
	
	/**
	 * Return the maximum hitpoints of this Unit.
	 */
	@Basic @Raw @Immutable
	public int getMaxHitpoints() 
	{
		return this.maxHitpoints;
	}
	
	/**
	 * Check whether the given points are valid points for any Unit.
	 *  
	 * @param  	points
	 *         	The points to check.
	 * @return 	True if and only if the given points is positive.
	 *       	| result == ( (hitpoints > 0) )
	*/
	public static boolean isValidPoints(int points) 
	{
		return (points > 0);
	}
	
	/**
	 * Variable registering the maximum hitpoints of this Unit.
	 */
	private final int maxHitpoints;
	
	/**
	 * Return the current hitpoints of this Unit.
	 */
	@Basic @Raw
	public int getCurrentHitpoints() 
	{
		return this.currentHitpoints;
	}
	
	/**
	 * Check whether this Unit can have the given current hitpoints as its current hitpoints.
	 *  
	 * @param  	currentHitpoints
	 *         	The current hitpoints to check.
	 * @return 	True if and only if the current hitpoints are a valid hitpoints for any Unit and if they are lower or equal to this Unit's
	 * 			maximum hitpoints.
	 *       	| result == ( isValidHitpoints(currentHitpoints) && (currentHitpoints <= getMaxHitpoints()))
	 */
	@Raw
	public boolean canHaveAsCurrentHitpoints(int currentHitpoints) 
	{
		return ( isValidPoints(currentHitpoints) && (currentHitpoints <= getMaxHitpoints()));
	}
	
	/**
	 * Set the currentHitpoints of this Unit to the given currentHitpoints.
	 * 
	 * @param  	currentHitpoits
	 *        	The new current Hitpoints for this Unit.
	 * @pre    	This Unit can have the given current hitpoints as its current hitpoints.
	 *       	| canHaveAsCurrentHitpoints(currentHitpoits)
	 * @post   	The currentHitpoints of this Unit is equal to the given currentHitpoints.
	 *       	| new.getCurrentHitpoints() == currentHitpoits
	 * @effect	This Unit will die if this Unit can't have the given currentHitpoints as its currentHitpoints, 
	 * 			and if the given currentHitpoints is lower than or equal to 0.
	 * 			| if (!canHaveAsCurrentHitpoints(currentHitpoints)
	 * 			| 		then if (currentHitpoints <= 0)
	 * 			|			then this.die()
	 */
	@Raw
	public void setCurrentHitpoints(int currentHitpoints) 
	{
		try
		{
			assert canHaveAsCurrentHitpoints(currentHitpoints) : "Precondition: Cannot have the given current hitpoints as its current hitpoints.";
			this.currentHitpoints = currentHitpoints;
		}
		catch (AssertionError exc)
		{
			if (currentHitpoints <= 0)
			{
				this.currentHitpoints = 0;
				die();
			}
			else
				throw exc;
		}
	}
	
	/**
	 * Variable registering the currentHitpoints of this Unit.
	 */
	private int currentHitpoints;
	
	/**
	 * Return the temporary hitpoint of this Unit.
	 */
	@Basic @Raw @Model
	private double getTempHitpoint() 
	{
		return this.tempHitpoint;
	}
	
	/**
	 * Check whether this Unit can have the given temporary hitpoint as its temporary hitpoint.
	 *  
	 * @param  	tempHitpoint
	 *         	The temporary hitpoint to check.
	 * @return 	True if and only if the temporary hitpoint is between zero and the sum of one and this unit's toughness divided by 200.
	 *       	| result == ( (tempHitpoint >= 0) && (tempHitpoint < 1 + (getToughness() / 200.0)) )
	 */
	@Raw @Model
	private boolean canHaveAsTempHitpoint(double tempHitpoint) 
	{
		return ((tempHitpoint >= 0) && (tempHitpoint < (1 + (getToughness() / 200.0))));
	}
	
	/**
	 * Set the temporary hitpoint of this Unit to the given temporary hitpoint.
	 * 
	 * @param  	tempHitpoint
	 *         	The new temporary hitpoint for this Unit.
	 * @post   	The temporary hitpoint of this new Unit is equal to the given temporary hitpoint.
	 *       	| new.getTempHitpoint() == tempHitpoint
	 * @throws 	IllegalArgumentException
	 *         	This Unit cannot have the given temporary hitpoint as its temporary hitpoint.
	 *       	| !canHaveAsTempHitpoint(getTempHitpoint())
	 */
	@Raw @Model
	private void setTempHitpoint(double tempHitpoint) throws IllegalArgumentException 
	{
		if (!canHaveAsTempHitpoint(tempHitpoint))
			throw new IllegalArgumentException("The temporary hitpoint is too large.");
		this.tempHitpoint = tempHitpoint;
	}
	
	/**
	 * Variable registering the temporary hitpoint of this Unit.
	 */
	private double tempHitpoint = 0L;
	
	/**
	 * Return the maximum staminapoints of this Unit.
	 */
	@Basic @Raw @Immutable
	public int getMaxStaminapoints() 
	{
		return this.maxStaminaPoints;
	}
	
	/**
	 * Variable registering the maximum staminaPoints of this Unit.
	 */
	private final int maxStaminaPoints;
	
	/**
	 * Return the currentStaminapoints of this Unit.
	 */
	@Basic @Raw
	public int getCurrentStaminapoints() 
	{
		return this.currentStaminapoints;
	}
	
	/**
	 * Check whether this Unit can have the given current staminapoints as its current staminapoints.
	 * 
	 * @param  	currentStaminapoints
	 *         	The current staminapoints to check.
	 * @return 	True if and only if the current staminapoints aren't negative and if they are lower or equal 
	 * 			to this Unit's maximum staminapoints. 
	 *       	| result == ( (currentStaminapoints >= 0) && (currentStaminapoints <= getMaxHitpoints()) )
	 * @note	As opposed to current hitpoints, we do implement equal to zero in this checker, because the current staminapoints should
	 * 			be able to reach zero, at which point a Unit can't sprint anymore.
	*/
	public boolean canHaveAsCurrentStaminapoints(int currentStaminapoints) 
	{
		return ((currentStaminapoints >= 0) && (currentStaminapoints <= getMaxStaminapoints()));
	}
	
	/**
	 * Set the currentStaminapoints of this Unit to the given currentStaminapoints.
	 * 
	 * @param  	currentStaminapoints
	 *         	The new currentStaminapoints for this Unit.
	 * @pre    	The given currentStaminapoints must be a valid currentStaminapoints for any Unit.
	 *       	| canHaveAsCurrentStaminapoints(currentStaminapoints)
	 *       
	 * @post   	The currentStaminapoints of this Unit is equal to the given currentStaminapoints.
	 *       	| new.getCurrentStaminapoints() == currentStaminapoints
	 */
	@Raw
	public void setCurrentStaminapoints(int currentStaminapoints) 
	{
		assert canHaveAsCurrentStaminapoints(currentStaminapoints) : "Precondition: Cannot have the given current staminapoints as current staminapoints.";
		this.currentStaminapoints = currentStaminapoints;
	}
	
	/**
	 * Variable registering the current staminapoints of this Unit.
	 */
	private int currentStaminapoints;

	/**
	 * Return the temporary staminapoint of this Unit.
	 */
	@Basic @Raw @Model
	private double getTempStaminapoint() 
	{
		return this.tempStaminapoint;
	}
	
	/**
	 * Check whether this Unit can have the given temporary staminapoint as its temporary staminapoint.
	 *  
	 * @param  	temporary staminapoint
	 *         	The temporary staminapoint to check.
	 * @return 	True if and only if the given temporary staminapoint is between zero and 
	 * 			the sum of one and this Unit's toughness divided by 100.
	 *       	| result == ( (tempStaminapoint >= 0) && (tempStaminapoint <= 1 + (getToughness() / 100)) )
	 */
	@Raw @Model
	private boolean canHaveAsTempStaminapoint(double tempStaminapoint) 
	{
		return ((tempStaminapoint >= 0) && (tempStaminapoint <= (1 + (getToughness() / 100.0))));
	}
	
	/**
	 * Set the temporary staminapoint of this Unit to the given temporary staminapoint.
	 * 
	 * @param  	tempStaminapoint
	 *         	The new temporary staminapoint for this Unit.
	 * @post   	The temporary staminapoint of this new Unit is equal to the given temporary staminapoint.
	 *       	| new.getTempStaminapoint() == tempStaminapoint
	 * @throws 	IllegalArgumentException
	 *         	This Unit cannot have the given temporary staminapoint as its temporary staminapoint.
	 *       	| ! canHaveAsTempStaminapoint(getTempStaminapoint())
	 */
	@Raw @Model
	private void setTempStaminapoint(double tempStaminapoint) throws IllegalArgumentException 
	{
		if (!canHaveAsTempStaminapoint(tempStaminapoint))
			throw new IllegalArgumentException("The temporary staminapoint is too large.");
		this.tempStaminapoint = tempStaminapoint;
	}
	
	/**
	 * Variable registering the temporary staminapoint of this Unit.
	 */
	private double tempStaminapoint = 0L;
	
	/**
	 * Return the orientation of this Unit.
	 */
	@Basic @Raw
	public double getOrientation() 
	{
		return this.orientation;
	}
	
	/**
	 * Check whether the given orientation is a valid orientation for any Unit.
	 *  
	 * @param  	orientation
	 *         	The orientation to check.
	 * @return 	True if and only if the orientation is between negative PI and PI.
	 *       	| result == ( (orientation >= -(Math.PI)) && (orientation <= Math.PI) )
	 */
	public static boolean isValidOrientation(double orientation) 
	{
		return ((orientation >= -(Math.PI)) && (orientation <= Math.PI));
	}
	
	/**
	 * Set the orientation of this Unit to the given orientation.
	 * 
	 * @param  	orientation
	 *         	The new orientation for this Unit.
	 * @post   	If the given orientation is a valid orientation for any Unit, 
	 * 			the orientation of this new Unit is equal to the given orientation.
	 *       	| if (isValidOrientation(orientation))
	 *       	|   then new.getOrientation() == orientation
	 */
	@Raw
	public void setOrientation(double orientation) 
	{
		if (isValidOrientation(orientation))
			this.orientation = orientation;
	}
	
	/**
	 * Variable registering the orientation of this Unit, with a default value of PI/2
	 */
	private double orientation = (Math.PI/2);
	
	/**
	 * Return the current cube coordinates in which this Unit is located.
	 * 
	 * @return 	The given array length is equals to the array length of this Unit's current cube coordinates.
	 * 			| result.lenght == this.cubeCoordinates.length;
	 * @return	Each elemen of the resulting array corresponds with the element of this Unit's current cube coordinates at the corresponding
	 * 			index.
	 * 			| for each i in 0..result.length:
	 * 			|	result[i] == this.cubeCoordinates[i]
	 */
	@Basic @Raw
	public int[] getCubeCoordinates() 
	{
		return Arrays.copyOf(cubeCoordinates, cubeCoordinates.length);
	}
	
	/**
	 * Check whether this Unit can have the given current cube coordinates as its current cube coordinates.
	 * 
	 * @param  	coordinates
	 *         	The cube coordinates to check.
	 * @return	If this Unit is dead, true if and only if the given cube coordinates are ineffective.
	 * 			Otherwise, true if and only if the given cube coordinates are within the boundaries of this Unit's World
	 * 			and if the given cube coordinates correspond to a passable cube.
	 * 			| if (!isAlive())
	 * 			|	result == (coordinates == null)
	 * 			| else
	 * 			|	result == ( getWorld().isBetweenBoundaries(coordinates[0], coordinates[1], coordinates[2])
	 * 			|					&& getWorld().isPassableCube(coordinates[0], coordinates[1], coordinates[2]) )
	 */
	@Raw
	public boolean canHaveAsCubeCoordinates(int[] coordinates) 
	{
		if (!isAlive())
			return coordinates == null;
		return  (getWorld().isBetweenBoundaries(coordinates[0], coordinates[1], coordinates[2])
				&& getWorld().isPassableCube(coordinates[0], coordinates[1], coordinates[2]));
	}

	/**
	 * Set the current cube coordinates of this Unit to the given cube coordinates.
	 * 
	 * @param  	cubeCoordinates
	 *         	The new cube coordinates for this Unit.
	 * @post   	The current cube coordinates of this new Unit is equal to the given cube coordinates.
	 *       	| new.getCubeCoordinates() == cubeCoordinates
	 * @throws 	IllegalArgumentException
	 *         	This Unit cannot have the given cube coordinates as its current cube coordinates.
	 *       	| !canHaveAsCubeCoordinates(cubeCoordinates)
	 * @effect	Set the precise position of this Unit according to the given cube coordinates.
	 * 			| this.setPosition(cubeCoordinates)
	 * @note	We immediately update the position of this Unit if we change its coordinates. This is because these properties
	 * 			work along with one another.
	 */
	@Raw
	public void setCubeCoordinates(int[] cubeCoordinates) throws IllegalArgumentException 
	{
		if (!canHaveAsCubeCoordinates(cubeCoordinates))
			throw new IllegalArgumentException("Cannot have cube coordinates!");
		this.cubeCoordinates = Arrays.copyOf(cubeCoordinates, 3);
		setPosition(cubeCoordinates);
	}
	
	/**
	 * Variable registering the current cube coordinates of this Unit.
	 */
	private int[] cubeCoordinates = new int[3];
	
	/**
	 * Return the falling state of this Unit.
	 */
	@Basic @Raw
	public boolean isFalling() 
	{
		return this.isFalling;
	}
	
	/**
	 * Check whether this Unit can have the given falling state as its falling state.
	 *  
	 * @param  	isFalling
	 *         	The falling state to check.
	 * @return	If this Unit is dead, true if and only if the given falling state is false.
	 * 			Otherwise, true if and only if the given falling state is true and the current cube coordinates of this Unit don't have a
	 * 			solid neighbouring cube, or if the given falling state is false and the current cube coordinates of this Unit do have
	 * 			a solid neighbouring cube.
	 * 			| if (!isAlive)
	 * 			|	result == (!isFalling)
	 * 			| else
	 * 			|	let 
	 * 			|		coordinates = getCubeCoordinates()
	 * 			|	in
	 * 			|		result == ( (isFalling && !(getWorld().hasSolidNeighbouringCube((coordinates[0], coordinates[1], coordinates[2]))))
	 * 			|					|| (!isFalling && getWorld().hasSolidNeighbouringCube((coordinates[0], coordinates[1], coordinates[2]))) )
	 */
	@Raw
	public boolean canHaveAsFallingState(boolean isFalling) 
	{
		if (!isAlive())
			return !isFalling;
		else
		{
			int[] coordinates = getCubeCoordinates();
			return (isFalling && mustUnitFall(coordinates)) || (!isFalling && !mustUnitFall(coordinates));
		}
	}
	
	/**
	 * Set the falling state of this Unit to the given falling state.
	 * 
	 * @param  	isFalling
	 *         	The new falling state for this Unit.
	 * @post   	The falling state of this new Unit is equal to the given falling state.
	 *       	| new.isFalling() == isFalling
	 * @throws 	IllegalArgumentException
	 *         	This Unit cannot have the given falling state as its falling state.
	 *       	| !canHaveAsIsFalling(getisFalling())
	 */
	@Raw
	public void setFallingState(boolean isFalling) throws IllegalArgumentException 
	{
		if (!canHaveAsFallingState(isFalling))
			throw new IllegalArgumentException("Cannot have falling state!");
		this.isFalling = isFalling;
	}
	
	/**
	 * Check whether this Unit must fall.
	 * 
	 * @param 	coordinates
	 * 			The coordinates to use in this calculation.
	 * @return	False if and only if the given z-coordinate of the given coordinates is equal to zero.
	 * 			Otherwise, true if and only if the given cube coordinates don't have a solid neighbouring cube.
	 * 			| if (coordinates[2] == 0)
	 * 			|	then result == false;
	 * 			| else
	 * 			|	result = ( !getWorld().hasSolidNeighbouringCube(coordinates[0], coordinates[1], coordinates[2]) )
	 */
	@Raw @Model
	private boolean mustUnitFall(int[] coordinates)
	{
		if (coordinates[2] == 0)
			return false;
		return (!getWorld().hasSolidNeighbouringCube(coordinates[0], coordinates[1], coordinates[2]));
	}
	
	/**
	 * Variable registering the falling state of this Unit.
	 */
	private boolean isFalling;
		
	/**
	 * Return the coordinates of the cube to which this Unit will move to next.
	 */
//	 * @return	The length of the resulting array is equal to the length of this Unit's next cube coordinates array.
//	 * 			| result.length == this.nextCoordinates.length()
//	 * @return	Each element of the resulting array corresponds with the element of this Unit's next cube coordinates array
//	 * 			at the corresponding index.
//	 * 			| for each i in 0..result.length:
//	 * 			|	result[i] == this.nextCoordinates[i]
	@Basic @Raw
	private int[] getNextCoordinates() 
	{
		//return Arrays.copyOf(nextCoordinates, nextCoordinates.length);
		return this.nextCoordinates;
	}
	
	/**
	 * Check whether this Unit can have the given next cube coordinates as its next cube coordinates.
	 *  
	 * @param  	nextCoordinates
	 *         	The next cube coordinates to check.
	 * @return	If this Unit is dead, true if and only if the given next cube coordinates is ineffective.
	 * 			Otherwise, true if and only if this Unit can have the next cube coordinates as its current cube coordinates and if
	 *			the given next cube coordinates are different from the current cube coordinates.
	 *			| if (!isAlive())
	 *			|	then result == (nextCoordinates == null)
	 *			| else
	 *			|	result == ( canHaveAsCubeCoordinates(nextCoordinates) && !Arrays.equals(getCubeCoordinates(), nextCoordinates) )
	 */
	@Raw
	private boolean canHaveAsNextCoordinates(int[] nextCoordinates) 
	{
		if (!isAlive())
			return nextCoordinates == null;
		return (canHaveAsCubeCoordinates(nextCoordinates) && !Arrays.equals(getCubeCoordinates(), nextCoordinates));
	}
	
	/**
	 * Set the next cube coordinates of this Unit to the given next cube coordinates.
	 * 
	 * @param  	nextCoordinates
	 *         	The new next cube coordinates for this Unit.
	 * @post   	The next cube coordinates of this new Unit is equal to the given next cube coordinates.
	 *       	| new.getNextCubeCoordinates() == nextCubeCoordinates
	 * @throws 	IllegalArgumentException
	 *         	This Unit cannot have the given next cube coordinates as its next cube coordinates.
	 *       	| !canHaveAsNextCubeCoordinates(nextCoordinates)
	 * @throws	IllegalArgumentException
	 * 			The Unit isn't currently falling and there isn't a solid cube neighbouring the next cube coordinates.
	 *       	| !isFalling() && !getWorld().hasSolidNeighbouringCube(nextCoordinates[0], nextCoordinates[1], nextCoordinates[2])
	 */
	@Raw
	private void setNextCoordinates(int[] nextCoordinates) throws IllegalArgumentException 
	{
		if (! canHaveAsNextCoordinates(nextCoordinates))
			throw new IllegalArgumentException("Cannot have the next cube coordinates!");
		
		// Check whether the Unit is falling, if so it can have the given nextCubeCoordinates as its nextCubeCoordinates:
		if (isFalling())
			this.nextCoordinates = Arrays.copyOf(nextCoordinates, nextCoordinates.length);
		// Else: check whether the nextCubeCoordinates has a solid neigbouring cube.
		else
		{
			if (!getWorld().hasSolidNeighbouringCube(nextCoordinates[0], nextCoordinates[1], nextCoordinates[2]))
				throw new IllegalArgumentException("There isn't a solid cube neighbouring the given next cube coordinates!");
			this.nextCoordinates = Arrays.copyOf(nextCoordinates, nextCoordinates.length);				
		}
	}
		
	/**
	 * Variable registering the coordinates of the cube to which this Unit will move to next.
	 */
	private int[] nextCoordinates;
	
	/**
	 * Return the position of this Unit.
	 * 
	 * @return	The length of the resulting array is equals to the length of this Unit's position array.
	 * 			| result.length == this.position.length
	 * @return	Each element of the resulting array equals with the elemen of this Unit's position array at the corresponding index.
	 * 			| for each i in 0..result.length:
	 * 			|	result[i] == this.position[i]
	 */
	@Basic @Raw
	public double[] getPosition() 
	{
		return Arrays.copyOf(position, position.length);
	}
	
	/**
	 * Calculate the precise position with the given cube coordinates.
	 * 
	 * @param  	coordinates
	 *         	The cube coordinates used to calculate the new position of this Unit.
	 * @post   	Each element of the position of this Unit is equal to the sum of the corresponding element of the given coordinates
	 * 			and the cubelength of any World divided by 2. 
	 * 			| for each i in 0..coordinates.length:
	 * 			|	new.position[i] == (coordinates[i] + (World.getCubeLength() / 2.0))       
	 * @throws 	IllegalArgumentException
	 *         	This Unit cannot have the given coordinates as its cubeCoordinates.
	 *       	| ! canHaveAsCubeCoordinates(coordinates)
	 * @effect	Enable the falling state of this Unit, if it must fall with the current cube coordinates.
	 * 			| if (mustUnitFall(getCubeCoordinates())
	 * 			|	then this.setFallingState(true)
	 * @effect	Disable the falling state of this Unit, if the unit is currently falling.
	 * 			| if (isFalling())
	 * 			|	then this.setFallingState(false)
	 */
	@Raw
	public void setPosition(int[] coordinates) throws IllegalArgumentException 
	{
		if (! canHaveAsCubeCoordinates(coordinates))
			throw new IllegalArgumentException("The given cube coordinates to calculate the Unit's position are not valid!");
		position = World.getPreciseCoordinates(coordinates);
		
		if (mustUnitFall(getCubeCoordinates()))
			setFallingState(true);
		else if (isFalling())
			setFallingState(false);	
	}
	
	/**
	 * Check whether this Unit can have the given position value at the given index of its position.
	 * 
	 * @param 	value
	 * 			The value to be checked.
	 * @param	index
	 * 			The index of which component must be used in the checker.
	 * @return	True if and only if the given value is between the lower boundary of this World
	 * 			and either the amount of x cubes, y cubes or z cubes of this World depending on the given index of the position.
	 * 			| if (index == 0)
	 * 			| 	then result == ( (value >= getWorld().getLowerBoundary()) && (value < getWorld().getNbCubesX()) )
	 * 			| else if (index == 1)
	 * 			|	then result == ( (value >= getWorld().getLowerBoundary()) && (value < getWorld().getNbCubesY()) )
	 * 			| else if (index == 2)
	 * 			|	then result == ( (value >= getWorld().getLowerBoundary()) && (value < getWorld().getNbCubesZ()) )
	 * 			| else
	 * 			|	result == false
	 */
	public boolean canHaveAsPositionAt(int index, double value)
	{
		/* 
		 * We use this method mainly in the setter to set each value of the position individually. We could use the setter of position to 
		 * set the entire position at once. This seems feasible but according to the programming rules of this course 
		 * this would allow the setter to change some properties without changing it entirely. 
		 * Example: the x component is valid, thus it is changed, BUT the y component
		 * is invalid and thus an error is thrown. The property is partially changed and this is not allowed due to the rules. 
		 */
		switch (index)
		{
		case 0:
			return ((value >= getWorld().getLowerBoundary()) && (value < getWorld().getNbCubesX()));
		case 1:
			return ((value >= getWorld().getLowerBoundary()) && (value < getWorld().getNbCubesY()));
		case 2:
			return ((value >= getWorld().getLowerBoundary()) && (value < getWorld().getNbCubesZ()));
		default:
			return false;
		}
	}
	
	/**
	 * Set the value of this Unit's position at the given index equal to the given value.
	 * 
	 * @param 	index
	 * 			The index of the position component to be changed.
	 * @param 	value
	 * 			The new value for this Unit's position component.
	 * @throws 	IllegalArgumentException
	 * 			This Unit cannot have the given value as its value for its position at the given index.
	 * 			| !canHaveAsUnitPositionValue(value)
	 * @throws	IllegalStateException
	 * 			This Unit is dead.
	 * 			| !isAlive()
	 * @post	The value of the element of the position with the given index is equal to the given value.
	 * 			| new.getPosition()[index] == value
	 */
	public void setPositionAt(int index, double value) throws IllegalArgumentException, IllegalStateException
	{
		if (!isAlive())
			throw new IllegalStateException("Cannot change position at the given index if Unit is dead!");
		if (!canHaveAsPositionAt(index, value))
			throw new IllegalArgumentException("Value or index is invalid for this Unit position. Value: " + value);
		this.position[index] = value;
	}
	
	/**
	 * Variable registering the precise position of this Unit.
	 */
	private double[] position;
	
	/**
	 * Return the difference between current and next positions of this Unit.
	 */
	@Basic @Raw @Model
	private int[] getDeltaNewPositions() 
	{
		return this.deltaNewPositions;
	}
	
	/**
	 * Check whether the given difference between current and next positions is a valid difference between current and next positions
	 * for any Unit.
	 *  
	 * @param  	deltaNewPositions
	 *         	The difference between current and next positions to check.
	 * @return 	True if and only if every delta coordinate in the array is either -1, 0 or 1.
	 * 			| for each i in 0..deltaNewPosition.length:
	 *       	| 	if (!(deltaNewPositions[i] == 0 || deltaNewPositions[i] == 1 || deltaNewPositions[i] == -1))
	 *       	|		then result == false
	 *       	| result == true
	 */
	@Raw @Model
	private static boolean isValidDeltaNewPositions(int[] deltaNewPositions) 
	{
		for (int i = 0; i < deltaNewPositions.length; i++)
		{
			if (!(deltaNewPositions[i] == 0 || deltaNewPositions[i] == 1 || deltaNewPositions[i] == -1))
				return false;
		}
		return true;
	}
	
	/**
	 * Set the difference between current and next positions of this Unit to the given difference between current and next positions.
	 * 
	 * @param  	deltaNewPositions
	 *         	The new difference between current and next positions for this Unit.
	 * @post   	The difference between current and next positions of this new Unit is equal to the
	 * 			given difference between current and next positions.
	 *       	| new.getDeltaNewPositions() == deltaNewPositions
	 * @throws 	IllegalArgumentException
	 *         	The given difference between current and next positions is not a valid difference between current and next positions
	 *         	for any Unit.
	 *       	| !isValidDeltaNewPositions(getDeltaNewPositions())
	 */
	@Raw @Model
	private void setDeltaNewPositions(int[] deltaNewPositions) throws IllegalArgumentException {
		if (!isValidDeltaNewPositions(deltaNewPositions))
			throw new IllegalArgumentException("The given difference between current and next positions is invalid for any Unit!");
		this.deltaNewPositions = Arrays.copyOf(deltaNewPositions, 3);
	}
	
	/**
	 * Variable registering the difference between current and next positions of this Unit.
	 */
	private int[] deltaNewPositions;
	
	/**
	 * Return the base speed of this Unit.
	 */
	@Basic @Raw
	private double getBaseSpeed() 
	{
		return 1.5 * ( (getStrength() + getAgility()) / (200.0 * (getWeight() / 100.0)) );
	}
	
	/**
	 * Return the walking speed of this Unit.
	 */
	@Basic @Raw @Model
	private double getWalkingSpeed() 
	{
		return this.walkingSpeed;
	}
	
	/**
	 * Set the walking speed of this Unit to the given walking speed.
	 * 
	 * @param  	dz
	 * 			The difference between z-coordinates to calculate the walking speed.
	 * @post	The walking speed of this Unit is equal to the base speed divided by 2, if the given difference is equal to -1.
	 * 			| if (dz == -1)
	 * 			|	then new.getWalkingSpeed() == (getBaseSpeed() / 2.0)
	 * @post	The walking speed of this Unit is equal to the base speed multiplied by 1.2, if the given difference is equal to 1.
	 * 			| if (dz == 1)
	 * 			|	then new.getWalkingSpeed() == (getBaseSpeed() * 1.2)
	 * @post   	The walking speed of this Unit is equal to the base speed, if the given difference is equal to 0.
	 *       	| if (dz == 0)
	 *       	|	then new.getWalkingSpeed() == getBaseSpeed()
	 * @throws 	IllegalArgumentException
	 *         	The given difference isn't equal to -1, 0 or 1.
	 *         	| ! (dz == -1 || dz == 0 || dz == 1)
	 */
	@Raw @Model
	private void setWalkingSpeed(int dz) throws IllegalArgumentException 
	{
		double baseSpeed = getBaseSpeed();
		switch(dz)
		{
		case -1:
			walkingSpeed = (baseSpeed / 2.0);
			break;
		case 1:
			walkingSpeed = (baseSpeed * 1.2);
			break;
		case 0:
			walkingSpeed = baseSpeed;
			break;
		default:
			throw new IllegalArgumentException("Cannot use the given difference between z-coordinates to calculate walking speed.");
		}
	}
	
	/**
	 * Variable registering the walking speed of this Unit.
	 */
	private double walkingSpeed = 0L;

	/**
	 * Return the sprinting speed of this Unit.
	 */
	@Basic @Raw
	private double getSprintSpeed() 
	{
		return (2 * getWalkingSpeed());
	}
	
	/**
	 * Return the velocity of this Unit.
	 */
	@Basic @Raw @Model @Immutable
	private double[] getVelocity() 
	{
		return this.velocity;
	}
	
	/**
	 * Calculate the velocity with the given next cube coordinates, the walking or sprinting speed of this Unit, the current cube coordinates.
	 * 
	 * @param  	coordinates
	 *         	The next cube coordinates to calculate the velocity.
	 * @post   	The velocity of this new Unit is equal to the multiplication of the sprint speed of this Unit, if this Unit is currently sprinting,
	 * 			or it is equal to the multiplication of the walking speed of this Unit, if this Unit is currently walking, 
	 * 			with the difference between the given coordinate components and the current coordinate components divided by the square root
	 * 			of the sum of the power of these differences.
	 * 			| let 
	 * 			|	cubeCoordinates = getCubeCoordinates(),
	 * 			|	components = {coordinates[0] - cubeCoordinates[0], coordinates[1] - cubeCoordinates[1], coordinates[2] - cubeCoordinates[2]},
	 * 			|	d = Math.sqrt( Math.pow(components[0], 2) +  Math.pow(components[1], 2) + Math.pow(components[2], 2) ),
	 * 			|	walkingSpeed = getWalkingSpeed(),
	 * 			|	sprintSpeed = getSprintSpeed()
	 * 			| in
	 * 			|	for each i in 0..velocity.length:
	 * 			|		if (isSprinting())
	 * 			|			then new.getVelocity()[i] == (sprintSpeed * (components[i] / d))
	 * 			|		else
	 * 			|			new.getVelocity()[i] == (walkingSpeed * (components[i] / d))    
	 * @throws 	IllegalArgumentException
	 *         	This Unit cannot have the given coordinates in the calculation of the velocity.
	 *      	| !canHaveAsCubeCoordinates(coordinates)
	 */
	@Raw @Model
	private void setVelocity(int[] coordinates) throws IllegalArgumentException 
	{
		if (!canHaveAsCubeCoordinates(coordinates))
			throw new IllegalArgumentException("Cannot have the given cube coordinates in the calculation of the velocity!");
		
		int[] cubeCoordinates = getCubeCoordinates();
		double[] components = {coordinates[0] - cubeCoordinates[0], coordinates[1] - cubeCoordinates[1], coordinates[2] - cubeCoordinates[2]};
		double d = Math.sqrt(Math.pow(components[0], 2) + Math.pow(components[1], 2) + Math.pow(components[2], 2));
		double walkingSpeed = getWalkingSpeed();
		double sprintSpeed = getSprintSpeed();
		for(int i=0; i< velocity.length; i++)
		{
			if (isSprinting())
				this.velocity[i] = sprintSpeed*(components[i]/d);
			else
				this.velocity[i] = walkingSpeed*(components[i]/d);
		}
	}
	
	/**
	 * Variable registering the velocity of this Unit.
	 */
	private final double[] velocity = new double[3];
	
	/**
	 * Return the current speed of this Unit.
	 */
	@Basic @Raw
	public double getCurrentSpeed() 
	{
		return this.currentSpeed;
	}
	
	/**
	 * Check whether this Unit can have the given current speed as its current speed.
	 *  
	 * @param  	currentSpeed
	 *         	The current speed to check.
	 * @return 	If this Unit is dead, true if and only if the given current speed is zero.
	 * 			Otherwise, true if and only if the current speed is either the Unit's walking speed, the Unit's sprinting speed or zero.+
	 * 			| if (!isAlive())
	 * 			|	then result == (currentSpeed == 0)
	 * 			| else
	 *       	| 	result == ((currentSpeed == getWalkingSpeed()) || (currentSpeed == getSprintSpeed()))
	 */
	@Raw
	public boolean canHaveAsCurrentSpeed(double currentSpeed)
	{
		if (!isAlive())
			return currentSpeed == 0;
		return ((currentSpeed == getWalkingSpeed()) || (currentSpeed == getSprintSpeed()) || (currentSpeed == 0));
	}
	
	/**
	 * Set the current speed of this Unit to the given current speed.
	 * 
	 * @param  	currentSpeed
	 *         	The new current speed for this Unit.
	 * @post   	The current speed of this new Unit is equal to the given current speed.
	 *       	| new.getCurrentSpeed() == currentSpeed
	 * @throws 	IllegalArgumentException
	 *         	This Unit cannot have the given currentSpeed as its currentSpeed.
	 *       	| !canHaveAsCurrentSpeed(currentSpeed)
	 */
	@Raw
	public void setCurrentSpeed(double currentSpeed) throws IllegalArgumentException 
	{
		if (! canHaveAsCurrentSpeed(currentSpeed))
			throw new IllegalArgumentException("Cannot have the given current speed!");
		this.currentSpeed = currentSpeed;
	}
	
	/**
	 * Variable registering the current speed of this Unit.
	 */
	private double currentSpeed = 0L;

	/**
	 * Return the sprinting state of this Unit.
	 */
	@Basic @Raw
	public boolean isSprinting() 
	{
		return this.isSprinting;
	}
	
	/**
	 * Check whether this Unit can have the given sprinting state as its sprinting state.
	 *  
	 * @param  	isSprinting
	 *         	The sprinting state to check.
	 * @return 	If this Unit is dead, true if and only if the given sprinting state is false.
	 * 			Otherwise, true if and only if the given sprinting state is true and this Unit is currently moving or if the given 
	 * 			spriting state is false.
	 * 			| if (!isAlive())
	 * 			|	then result == (!isFalling)
	 * 			| else
	 *       	| 	result == ( (isSprinting && isMoving()) || !isSprinting )
	 */
	@Raw
	public boolean canHaveAsSprintingState(boolean isSprinting) 
	{
		if (!isAlive())
			return !isSprinting;
		return (isSprinting && isMoving()) || !isSprinting;
	}
	
	/**
	 * Set the sprinting state of this Unit to the given sprinting state.
	 * 
	 * @param  	isSprinting
	 *         	The new sprinting state for this Unit.
	 * @post   	The sprinting state of this new Unit is equal to the given sprinting state.
	 *       	| new.isSprinting() == isSprinting
	 * @throws 	IllegalArgumentException
	 *         	This Unit cannot have the given sprinting state as its sprinting state.
	 *       	| !canHaveAsIsSprinting(getIsSprinting())
	 */
	@Raw @Model
	private void setSprintingState(boolean isSprinting) throws IllegalArgumentException 
	{
		if (! canHaveAsSprintingState(isSprinting))
			throw new IllegalArgumentException("Cannot have the given sprinting state!");
		this.isSprinting = isSprinting;
	}
	
	/**
	 * Let this Unit sprint.
	 * 
	 * @throws 	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 * @effect	The sprinting state of this Unit is enabled, if its current staminapoints is higher than 0, if the Unit is currently moving, and
	 * 			if the Unit isn't falling.
	 * 			| if ( (getCurrentStaminapoints() > 0) && isMoving() && !isFalling() )
	 * 			| 	then this.setIsSprinting(true)
	 * @effect	The current speed of this Unit is set to its sprint speed, if its current staminapoints is higher than 0, if the Unit is
	 * 			currently moving, and if the Unit isn't falling.
	 * 			| if ( (getCurrentStaminapoints() > 0) && isMoving() && !isFalling() )
	 * 			| 	then this.setCurrentSpeed(getSprintSpeed())
	 * @effect	The velocity of this Unit is set to the calculated velocity using this Unit's cube coordinates, if its currentStaminapoints
	 * 			is higher than 0, if the Unit is currently moving, if the Unit isn't falling and if the isDefaultBehaviourEnabled indicator
	 * 			is deactivated.
	 * 			| if ( (getCurrentStaminapoints() > 0) && isMoving() && !isFalling() && !isDefaultBehaviourEnabled() )
	 * 			| 	then this.setVelocity(getNextCubeCoordinates())
	 */
	public void startSprinting() throws IllegalArgumentException
	{
		if ((getCurrentStaminapoints() > 0) && isMoving() && !isFalling())
		{
			setSprintingState(true);
			setCurrentSpeed(getSprintSpeed());
			
			// Only set the velocity if this method isn't called by the default behaviour. If the default behaviour decides to sprint, the correct 
			// velocity will be calculated in the MoveToAdjacent method.
			if (!isDefaultBehaviourEnabled())
				setVelocity(getNextCoordinates()); // Velocity will be set wrongly because we don't have the correct integer coordinates. TEST THIS!
		}
	}
	
	/**
	 * Let this Unit stop sprinting.
	 * 
	 * @throws 	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 * @effect	The isSprinting indicator of this Unit is disabled, if this Unit is currently moving.
	 * 			| if (isMoving())
	 * 			| 	then this.setIsSprinting(false)
	 * @effect	The current speed of this Unit is set to its walking speed, if this Unit is currently moving.
	 * 			| if (isMoving())
	 * 			| 	then this.setCurrentSpeed(getWalkingSpeed())
	 */
	public void stopSprinting() throws IllegalArgumentException
	{
		if (isMoving())
		{
			setSprintingState(false);
			setCurrentSpeed(getWalkingSpeed());
		}
	}
	
	/**
	 * Variable registering the sprinting state of this Unit.
	 */
	private boolean isSprinting = false;
	
	/**
	 * Return the sprinting duration of this Unit.
	 */
	@Basic @Raw
	private double getSprintingDuration() 
	{
		return this.sprintingDuration;
	}
	
	/**
	 * Check whether the given sprinting duration is a valid sprinting duration for any Unit.
	 *  
	 * @param  	sprinting duration
	 *         	The sprinting duration to check.
	 * @return 	True if and only if the given sprinting duration is between 0 and 0.2.
	 *       	| result == ((sprintingDuration >= 0) && (sprintingDuration <= 0.2))
	*/
	private static boolean isValidSprintingDuration(double sprintingDuration) 
	{
		/*
		 * Sprinting works every 0.1 second. Since we add the duration to this property and check whether its higher than 0.1, it is 
		 * impossible to get higher than 0.2 with our implementation.
		 */
		return ((sprintingDuration >= 0) && (sprintingDuration <= 0.2));
	}
	
	/**
	 * Set the sprinting duration of this Unit to the given sprinting duration.
	 * 
	 * @param  	sprintingDuration
	 *        	The new sprinting duration for this Unit.
	 *        
	 * @post   	The sprinting duration of this new Unit is equal to the given sprinting duration.
	 *       	| new.getSprintingDuration() == sprintingDuration
	 *       
	 * @throws 	IllegalArgumentException
	 *         	The given sprinting duration is not a valid sprinting duration for any Unit.
	 *       	| ! isValidSprintingDuration(getSprintingDuration())
	 */
	@Raw
	private void setSprintingDuration(double sprintingDuration) throws IllegalArgumentException 
	{
		if (! isValidSprintingDuration(sprintingDuration))
			throw new IllegalArgumentException("The sprinting duration is invalid for any Unit.");
		this.sprintingDuration = sprintingDuration;
	}
	
	/**
	 * Variable registering the sprinting duration of this Unit.
	 */
	private double sprintingDuration;
	
	/**
	 * Check whether this Unit is moving.
	 */
	@Basic @Raw
	public boolean isMoving() 
	{
		return this.isMoving;
	}
	
	/**
	 * Check whether this Unit can have the given moving state as its moving state.
	 *  
	 * @param  	isMoving
	 *         	The moving state to check.
	 * @return 	True if and only if the given moving state is true and the Unit isn't currently attacking or if the given 
	 * 			moving state is false.
	 *       	| result == ((isMoving && !isAttacking()) || !isMoving)
	 */
	@Raw
	public boolean canHaveAsIsMoving(boolean isMoving) 
	{
		return ((isMoving && !isAttacking()) || !isMoving);
	}
	
	/**
	 * Set the moving state of this Unit to the given moving state.
	 * 
	 * @param  	isMoving
	 *         	The new moving state for this Unit.
	 * @post   	The moving state of this new Unit is equal to the given moving stater.
	 *       	| new.getIsMoving() == isMoving
	 * @throws 	IllegalArgumentException
	 *         	This Unit cannot have the given moving state as its moving state.
	 *       	| ! canHaveAsIsMoving(getIsMoving())
	 */
	@Raw @Model
	private void setMovingState(boolean isMoving) throws IllegalArgumentException 
	{
		if (! canHaveAsIsMoving(isMoving))
			throw new IllegalArgumentException("The given value isMoving is invalid for this Unit.");
		this.isMoving = isMoving;
	}
	
	/**
	 * Variable registering the moving state of this Unit.
	 */
	private boolean isMoving = false;
	
	/**
	 * Check whether this Unit was moving.
	 */
	@Basic @Raw @Model
	private boolean wasMoving() 
	{
		return this.wasMoving;
	}
	
	/**
	 * Check whether this Unit can have the given was moving state as its was moving state.
	 *  
	 * @param  	wasMoving
	 *         	The was moving state to check.
	 * @return 	True if and only if the given state is true and the Unit isn't moving currently or if the Unit is currently Moving and the given
	 * 			state is false.
	 *       	| result == ( (wasMoving && !isMoving()) || (!wasMoving && isMoving()) )
	 */
	@Raw @Model
	private boolean canHaveAsWasMoving(boolean wasMoving) 
	{
		return ((wasMoving && !isMoving()) || (!wasMoving && isMoving()));
	}
	
	/**
	 * Set the was moving state of this Unit to the given state.
	 * 
	 * @param  	wasMoving
	 *         	The new was moving state for this Unit.
	 * @post   	The was moving state of this new Unit is equal to the given state.
	 *       	| new.getWasMoving() == wasMoving
	 * @throws 	IllegalArgumentException
	 *         	This Unit cannot have the given was moving state as its was moving state.
	 *       	| !canHaveAsWasMoving(wasMoving)
	 */
	@Raw @Model
	private void setWasMoving(boolean wasMoving) throws IllegalArgumentException 
	{
		if (! canHaveAsWasMoving(wasMoving))
			throw new IllegalArgumentException("Cannot have the given was moving state!");
		this.wasMoving = wasMoving;
	}
	
	/**
	 * Variable registering whether this Unit was moving.
	 */
	private boolean wasMoving = false;
		
	/**
	 * Check whether this Unit is currently moving to a cube in the {@link World}.
	 */
	@Basic @Raw @Model
	private boolean isMovingTo() 
	{
		return this.isMovingTo;
	}
	
	/**
	 * Check whether this Unit can have the given moving to state as its moving to state.
	 *  
	 * @param  	isMovingTo
	 *         	The moving to state to check.
	 * @return 	True if and only if the Unit is currently moving.
	 *       	| result == ( isMoving() )
	 * @note	The moving to state must be enabled after the moving state is enabled and it must be disabled before the moving
	 * 			state is disabled. This means at all times the moving state must be true.
	 */
	@Raw @Model
	private boolean canHaveAsMovingTo(boolean isMovingTo) 
	{
		return isMoving();
	}
	
	/**
	 * Set the moving to state of this Unit to the given moving to state.
	 * 
	 * @param  	isMovingTo
	 *         	The new moving to state for this Unit.
	 * @post   	The moving to state of this new Unit is equal to the given moving to state.
	 *       	| new.getIsMovingTo() == isMovingTo
	 * @throws 	IllegalArgumentException
	 *         	This Unit cannot have the given moving state as its moving to state.
	 *       	| !canHaveAsIsMovingTo(isMovingTo)
	 */
	@Raw @Model
	private void setMovingTo(boolean isMovingTo) throws IllegalArgumentException 
	{
		if (! canHaveAsMovingTo(isMovingTo))
			throw new IllegalArgumentException("Cannot have the given moving to state!");
		this.isMovingTo = isMovingTo;
	}
	
	/**
	 * Variable registering whether this Unit is moving to a cube in the {@link World}.
	 */
	private boolean isMovingTo;

	/**
	 * Check whether this Unit is currently moving to an adjacent cube in the {@link World}.
	 */
	@Basic @Raw @Model
	private boolean isMovingToAdjacent() 
	{
		return this.isMovingToAdjacent;
	}
	
	/**
	 * Set the moving to adjacent cube state of this Unit to the given moving to adjacent cube state.
	 * 
	 * @param  	isMovingToAdjacent
	 *         	The new moving to adjacent cube state for this Unit.
	 * @post   	The moving to adjacent cube state of this new Unit is equal to the given moving to adjacent cube state.
	 *       	| new.getIsMovingToAdjacent() == isMovingToAdjacent
	 * @throws 	IllegalArgumentException
	 *         	This Unit cannot have the given isMovingTo state as its isMovingTo state.
	 *       	| !canHaveAsIsMovingTo(isMovingToAdjacent)
	 * @note	We use the same checker, because this state is almost the same as the isMovingTo state. The only difference is that we use
	 * 			this state to check if its moving to an adjacent cube.
	 */
	@Raw @Model
	private void setIsMovingToAdjacent(boolean isMovingToAdjacent) throws IllegalArgumentException 
	{
		if (!canHaveAsMovingTo(isMovingToAdjacent))
			throw new IllegalArgumentException("Cannot have the given is moving to adjacent cube state!");
		this.isMovingToAdjacent = isMovingToAdjacent;
	}
	
	/**
	 * Variable registering whether this Unit is currently moving to an adjacent cube in the {@link World}.
	 */
	private boolean isMovingToAdjacent;
	
	/**
	 * Return the destination cube of this Unit.
	 */
	@Basic @Raw @Model
	private int[] getDestinationCube() {
		return this.destinationCube;
	}
	
	/**
	 * Set the destination cube of this Unit to the given destination cube.
	 * 
	 * @param  	destinationCube
	 *         	The new destination cube for this Unit.
	 * @post   	The destination cube of this new Unit is equal to the given destination cube.
	 *       	| new.getDestinationCube() == destinationCube
	 * @throws 	IllegalArgumentException
	 *         	This Unit cannot have the given destinationCube as its cubeCoordinates.
	 *       	| ! canHaveAsCubeCoordinates(destinationCube)
	 */
	@Raw @Model
	private void setDestinationCube(int[] destinationCube) throws IllegalArgumentException 
	{
		if (! canHaveAsCubeCoordinates(destinationCube))
			throw new IllegalArgumentException("The given destinationCube is invalid for this Unit.");
		this.destinationCube = Arrays.copyOf(destinationCube, 3);
	}
	
	/**
	 * Variable registering the destination cube of this Unit.
	 */
	private int[] destinationCube;
	
	/**
	 * Check whether this Unit is working.
	 */
	@Basic @Raw
	public boolean isWorking() 
	{
		return this.isWorking;
	}
	
	/**
	 * Check whether this Unit can have the given working state as its working state.
	 *  
	 * @param  	isWorking
	 *         	The working state to check.
	 * @return 	True if and only if the Unit isn't currently moving or attacking or defending.
	 *       	| result == ((!isMoving() || !isAttacking() || !isDefending)
	 */
	@Raw 
	public boolean canHaveAsIsWorking(boolean isWorking) {
		return (!isMoving() || !isAttacking() || !isDefending());
	}
	
	/**
	 * Set the working state of this Unit to the given working state.
	 * 
	 * @param  	isWorking
	 *         	The new working state for this Unit.
	 * @post   	The working state of this new Unit is equal to the given working state.
	 *       	| new.getIsWorking() == isWorking
	 * @throws 	IllegalArgumentException
	 *         	This Unit cannot have the given working state as its working state.
	 *       	| ! canHaveAsIsWorking(getIsWorking())
	 */
	@Raw @Model
	private void setWorking(boolean isWorking) throws IllegalArgumentException {
		if (!canHaveAsIsWorking(isWorking))
			throw new IllegalArgumentException("The given value isWorking is invalid for this Unit.");
		this.isWorking = isWorking;
	}
	
	/**
	 * Make this Unit work.
	 * 
	 * @throws	IllegalArgumentException
	 * 			This Unit is moving, attacking, already working or didn't rest its initial recovery period yet.
	 * 			| !(isMoving() || isAttacking()) && !isWorking() && hasRestedOnePoint()
	 * @throws 	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 * @effect	The resting indicator of this Unit is disabled, only if the Unit isn't moving, attacking or already working and if the Unit
	 * 			has completed the initial resting period and if the Unit is currently resting.
	 * 			| if ((!(isMoving() || isAttacking()) && !isWorking()) && hasRestedOnePoint() && isResting())
	 * 			| then this.setIsResting(false)
	 * @effect	The working indicator of this Unit is enabled, only if the Unit isn't moving, attacking or already working and if the Unit
	 * 			has completed the initial resting period.
	 * 			| if (!(isMoving() || isAttacking()) && !isWorking() && hasRestedOnePoint()) 
	 * 			| then this.setWorking(true)
	 * @effect	The working duration of this Unit is set to 500 divided by its strength, only if the Unit isn't moving, attacking
	 * 			or already working and if the Unit has completed the initial restin)g period.
	 * 			| if (!(isMoving() || isAttacking() && !isWorking() && hasRestedOnePoint())
	 * 			| then this.setWorkingDuration(500/getStrength())
	 */
	@Raw
	public void work() throws IllegalArgumentException
	{
		if (!(isMoving() || isAttacking()) && !isWorking() && hasRestedInitialRecoveryPeriod())
		{
			if (isResting())
				setResting(false);
			
			setWorking(true);
			setWorkingDuration(500/getStrength());
		}
		else
		{
			if (isMoving())
				System.err.println("Unit is still moving, thus no work can be done!");
			if (isAttacking())
				System.err.println("Unit is still attacking, thus no work can be done!");
			if (isWorking())
				System.err.println("Unit is already working, wait till Unit has finished working!");
			if (!hasRestedInitialRecoveryPeriod())
				System.err.println("Unit hasn't rested its initial recovery period!");
			throw new IllegalArgumentException("Unable to work right now!");
		}
			
	}
	
	/**
	 * Make this Unit work at a given cube.
	 * 
	 * @param 	x
	 * 			The given x coordinate of the target cube.
	 * @param 	y
	 * 			The given y coordinate of the target cube.
	 * @param 	z
	 * 			The given z coordinate of the target cube.
	 * @throws 	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 * @effect	Set the target cube to the given cube coordinates, if the Unit isn't already working.
	 * 			| if (!isWorking())
	 * 			| 	then
	 * 			|		 targetCube = {x, y, z}	 
	 * 			|		 this.setTargetCube(targetCube)
	 * @effect	The orientation is set to the arctangens of the difference between the given y coordinate and the current y coordinate
	 * 			and the difference between the given x coordinate and the current x coordinate, if the Unit isn't already working.
	 * 			| if (!isWorking())
	 * 			|	then this.setOrientation((y - getCubeCoordinates()[1]), (x - getCubeCoordinates[0]))
	 * @effect	If the Unit isn't already working, let the Unit work.
	 * 			| if (!isWorking())
	 * 			|	then this.work()
	 */
	@Raw
	public void workAt(int x, int y, int z) throws IllegalArgumentException
	{
		if (!isWorking())
		{
			try
			{
				int[] targetCube = {x, y, z};
				setTargetCube(targetCube);
				
				// A little extra: let the Unit face the cube he's working at. This clears up any confusion for the user.
				double arg0 = y - getCubeCoordinates()[1];
				double arg1 = x - getCubeCoordinates()[0];
				setOrientation(Math.atan2(arg0, arg1));

				work();
			}
			catch (IllegalArgumentException e)
			{
				setWorking(false);
				throw e;
			}
		}
	}
	
	/**
	 * Variable registering the working state of this Unit.
	 */
	private boolean isWorking = false;
	
	/**
	 * Return the target cube of this Unit.
	 */
	@Basic @Raw @Model
	private int[] getTargetCube() 
	{
		return this.targetCube;
	}
	
	/**
	 * Check whether this Unit can have the given target cube as its target cube.
	 *  
	 * @param  	targetCube
	 *         	The target cube to check.
	 * @return 	True if and only if the target cube coordinates are between this World's boundaries
	 * 			and the target cube is a neighbouring cube of the current Unit's cube or the current cube itself.
	 *       	| result == getWorld().isBetweenBoundaries(targetCube[0], targetCube[1], targetCube[2]) && World.isNeighbouringCube(getCubeCoordinates(), targetCube)
	 */
	@Raw
	private boolean canHaveAsTargetCube(int[] targetCube) 
	{
		return getWorld().isBetweenBoundaries(targetCube[0], targetCube[1], targetCube[2]) && World.isNeighbouringCube(getCubeCoordinates(), targetCube);
	}
	
	/**
	 * Set the targetCube of this Unit to the given target cube.
	 * 
	 * @param  	targetCube
	 *         	The new target cube for this Unit.
	 * @post   	The target cube of this new Unit is equal to the given target cube.
	 *       	| new.getTargetCube() == targetCube
	 * @throws 	IllegalArgumentException
	 *         	This Unit cannot have the given targetCube as its targetCube.
	 *       	| ! canHaveAsTargetCube(getTargetCube())
	 */
	@Raw @Model
	private void setTargetCube(int[] targetCube) throws IllegalArgumentException 
	{
		if (! canHaveAsTargetCube(targetCube))
			throw new IllegalArgumentException("The given targetCube to work at is invalid.");
		this.targetCube = targetCube;
	}
	
	/**
	 * Variable registering the target cube of this Unit.
	 */
	private int[] targetCube;
			
	/**
	 * Return the working duration of this Unit.
	 */
	@Basic @Raw @Model
	private double getWorkingDuration() 
	{
		return this.workingDuration;
	}
	
	/**
	 * Check whether this Unit can have the given workingDuration as its workingDuration.
	 *  
	 * @param  	workingDuration
	 *         	The working duration to check.
	 * @return 	True if and only if the given working duration is lower than (500/getStrength())
	 *       	| result == ( workingDuration <= (500 / getStrength() )
	 * @note	If the working duration goes beneath zero, we will immediately set it to 0.
	 */
	@Raw
	private boolean canHaveAsWorkingDuration(double workingDuration)
	{
		return (workingDuration <= (500 / getStrength()));
	}
	
	/**
	 * Set the working duration of this Unit to the given working duration.
	 * 
	 * @param  	workingDuration
	 *         	The new working duration for this Unit.
	 * @post   	The working duration of this new Unit is equal to the given working duration, if the given duration is positive.
	 * 			Otherwise, the working duration of this new Unit is equal to zero.
	 * 			| if (workingDuration >= 0)
	 *       	| 	then new.getWorkingDuration() == workingDuration
	 *       	| else
	 *       	|	new.getWorkingDuration() == 0
	 * @throws 	IllegalArgumentException
	 *         	This Unit cannot have the given working duration as its working duration.
	 *       	| ! canHaveAsWorkingDuration(getWorkingDuration())
	 */
	@Raw @Model
	private void setWorkingDuration(double workingDuration) throws IllegalArgumentException 
	{
		if (! canHaveAsWorkingDuration(workingDuration))
		{
			System.err.println("Max working duration: "  + (500/getStrength()));
			throw new IllegalArgumentException("The given workingDuration is invalid for this Unit." + workingDuration);
		}
			
		if (workingDuration < 0)
			this.workingDuration = 0;
		else
			this.workingDuration = workingDuration;
	}
	
	/**
	 * Variable registering the working duration of this Unit.
	 */
	private double workingDuration;

	/**
	 * Check whether this Unit is carrying a log.
	 */
	@Basic @Raw
	public boolean isCarryingLog() 
	{
		return this.isCarryingLog;
	}
	
	/**
	 * Check whether this Unit can have the given carrying log state as its carrying log state.
	 *  
	 * @param  	isCarryingLog
	 *         	The carrying log state to check.
	 * @return 	True if and only if the given carrying log state is true and the Unit currently has a log object 
	 * 			or if the carrying log state is false and the Unit currently has no log object.
	 *       	| result == ((isCarryingLog && getLog() != null) || (!isCarryingLog && getLog() == null))
	 */
	@Raw
	public boolean canHaveAsIsCarryingLog(boolean isCarryingLog) 
	{
		return ((isCarryingLog && getLog() != null) || (!isCarryingLog && getLog() == null));
	}
	
	/**
	 * Set the carrying log state of this Unit to the given carrying log state.
	 * 
	 * @param  	isCarryingLog
	 *         	The new carrying log state for this Unit.
	 * @post   	The carrying log state of this new Unit is equal to the given carrying log state.
	 *       	| new.getIsCarryingLog() == isCarryingLog
	 * @throws 	IllegalArgumentException
	 *         	This Unit cannot have the given carrying log state as its carrying log state.
	 *       	| ! canHaveAsIsCarryingLog(getIsCarryingLog())
	 */
	@Raw @Model
	private void setIsCarryingLog(boolean isCarryingLog) throws IllegalArgumentException 
	{
		if (! canHaveAsIsCarryingLog(isCarryingLog))
			throw new IllegalArgumentException("Cannot have the given carrying log state!");
		this.isCarryingLog = isCarryingLog;
	}
	
	/**
	 * Variable registering the carrying log state of this Unit.
	 */
	private boolean isCarryingLog;
	
	/**
	 * Return the {@link Log} of this Unit.
	 */
	@Basic @Raw
	public Log getLog()
	{
		return this.log;
	}
	
	/**
	 * Check whether this Unit can have the given {@link Log} as its {@link Log}.
	 *  
	 * @param  	log
	 *         	The {@link Log} to check.
	 * @return	If this Unit is dead, true if and only if the given Log is ineffective.
	 * 			Otherwise, true if and only if this Unit isn't carrying a boulder or if the given Log is ineffective.
	 * 			| if (!isAlive())
	 * 			|	then result == (log == null)
	 * 			| else
	 * 			|	result == ( (log == null) || !isCarryingBoulder() )
	 */
	@Raw
	public boolean canHaveAsLog(Log log) 
	{
		if (!isAlive())
			return log == null;
		return log == null || !isCarryingBoulder();
	}
	
	/**
	 * Set the {@link Log} of this Unit to the given {@link Log}.
	 * 
	 * @param  	log
	 *         	The new {@link Log} for this Unit.
	 * @effect	Change the weight according to the given Log.
	 * 			| this.changeWeight(log)
	 * @post   	The Log of this new Unit is equal to the given Log.
	 *       	| new.getLog() == log
	 * @throws 	IllegalArgumentException
	 *         	This Unit can't have the given Log as its Log.
	 *       	| ! canHaveAsLog(getLog())
	 * @note	If the Log is effective, the weight is manually set to the current weight + the weight of the log. This is done without
	 * 			the setter, because this temporary weight may exceed the checker.
	 */
	@Raw @Model
	private void setLog(Log log) throws IllegalArgumentException 
	{
		if (! canHaveAsLog(log))
			throw new IllegalArgumentException("The given log is invalid for this Unit.");
		changeWeight(log);
		this.log = log;
	}
	
	/**
	 * Check whether this Unit has a proper {@link Log} attached to it.
	 * 
	 * @return	True if and only if this Unit's can have its Log as its Log and if this Log is ineffective or
	 * 			if this Log is part of this Unit's World.
	 * 			| result == ( canHaveAsLog(getLog()) && ( (log == null) || getWorld().hasAsLog(getLog()) ) ) 
	 */
	public boolean hasProperLog()
	{
		return canHaveAsLog(getLog()) && ( (log == null) || getWorld().hasAsLog(getLog()) );
	}
	
	/**
	 * Drop this Unit's Log at the given target cube.
	 * 
	 * @param 	target
	 * 			The given target cube to drop the Log on.
	 * @throws 	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 * @effect	Set the position of this Unit's Log to the given target position.
	 * 			| this.getLog().setPosition(target)
	 * @effect	Set this Unit's Log ineffective.
	 * 			| this.setLog()
	 * @effect	Disable the carrying log state of this Unit.
	 * 			| this.setIsCarryingLog(false)
	 */
	@Raw @Model
	private void dropLog(double[] target) throws IllegalArgumentException
	{
		getLog().setPosition(target);
		setLog(null);
		setIsCarryingLog(false);
	}
	
	/**
	 * Variable registering the {@link Log} of this Unit.
	 */
	private Log log;
	
	/**
	 * Check whether this Unit is carrying a {@link Boulder}.
	 */
	@Basic @Raw
	public boolean isCarryingBoulder() 
	{
		return this.isCarryingBoulder;
	}
	
	/**
	 * Check whether this Unit can have the given carrying {@link Boulder} state as its carrying {@link Boulder} state.
	 *  
	 * @param  	isCarryingBoulder
	 *         	The carrying {@link Boulder} state to check.
	 * @return 	True if and only if the given carrying Boulder state is true and this Unit's Boulder isn't null 
	 * 			or if the given carrying Boulder state is false and this Unit's Boulder is null.
	 *       	| result == ((isCarryingBoulder && getBoulder() != null) || (!isCarryingBoulder && getBoulder() == null))
	 */
	@Raw
	public boolean canHaveAsIsCarryingBoulder(boolean isCarryingBoulder) 
	{
		return ((isCarryingBoulder && getBoulder() != null) || (!isCarryingBoulder && getBoulder() == null));
	}
	
	/**
	 * Set the carrying {@link Boulder} state of this Unit to the given carrying {@link Boulder} state.
	 * 
	 * @param  	isCarryingBoulder
	 *         	The new carrying {@link Boulder} state for this Unit.
	 * @post   	The carrying Boulder state of this new Unit is equal to the given carrying Boulder state.
	 *       	| new.getIsCarryingBoulder() == isCarryingBoulder
	 * @throws 	IllegalArgumentException
	 *         	This Unit can't have the given carrying Boulder state as its carrying Boulder state.
	 *       	| ! canHaveAsIsCarryingBoulder(getIsCarryingBoulder())
	 */
	@Raw @Model
	private void setIsCarryingBoulder(boolean isCarryingBoulder) throws IllegalArgumentException
	{
		if (! canHaveAsIsCarryingBoulder(isCarryingBoulder))
			throw new IllegalArgumentException("The given isCarryingBoulder indicator is invalid for this Unit.");
		this.isCarryingBoulder = isCarryingBoulder;
	}
	
	/**
	 * Variable registering whether this Unit is carrying a {@link Boulder}.
	 */
	private boolean isCarryingBoulder;
	
	/**
	 * Return the {@link Boulder} of this Unit.
	 */
	@Basic @Raw
	public Boulder getBoulder() 
	{
		return this.boulder;
	}
	
	/**
	 * Check whether this Unit can have the given {@link Boulder} as its {@link Boulder}.
	 *  
	 * @param  	boulder
	 *         	The {@link Boulder} to check.
	 * @return 	If this Unit is dead, true if and only if the given Boulder is ineffective.
	 * 			Otherwise, true if and only if the given boulder is ineffective or if this Unit isn't already carrying a Log.
	 * 			| if (!isAlive())
	 * 			|	then result == (boulder == null)
	 * 			| else
	 * 			|	result == ( (boulder == null) || (!isCarryingLog()) )
	 */
	@Raw
	public boolean canHaveAsBoulder(Boulder boulder) 
	{
		if (!isAlive())
			return boulder == null;
		return (boulder == null || !isCarryingLog());
	}
	
	/**
	 * Attach the given {@link Boulder} to this Unit.
	 * 
	 * @param  	boulder
	 *         	The new Boulder for this Unit.
	 * @effect	Change the weight according to the given Boulder.
	 * 			| this.changeWeight(boulder)
	 * @post   	The Boulder of this new Unit is equal to the given Boulder.
	 *       	| new.getBoulder() == boulder
	 * @throws 	IllegalArgumentException
	 *         	This Unit can't have the given Boulder as its Boulder.
	 *       	| !canHaveAsBoulder(getBoulder())
	 */
	@Raw @Model
	private void setBoulder(Boulder boulder) throws IllegalArgumentException 
	{
		if (! canHaveAsBoulder(boulder))
			throw new IllegalArgumentException("The given Boulder is invalid for this Unit.");
		changeWeight(boulder);
		this.boulder = boulder;
	}
	
	/**
	 * Check whether this Unit has a proper {@link Boulder} attached to it.
	 * 
	 * @return	True if and only if this Unit can have its Boulder as its Boulder and if that Boulder is either ineffective or if it is
	 * 			part of this Unit's World.
	 * 			| result == ( canHaveAsBoulder(getBoulder()) && ( (boulder == null) || getWorld().hasBoulder(getBoulder()) ) )
	 */
	public boolean hasProperBoulder()
	{
		return (canHaveAsBoulder(getBoulder()) && ( (boulder == null) || getWorld().hasAsBoulder(getBoulder()) ));
	}
	
	/**
	 * Drop this Unit's {@link Boulder} at the given target position.
	 * 
	 * @param 	target
	 * 			The given target position to drop the Boulder.
	 * @throws 	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 * @effect	The position of this Unit's Boulder is set to the given target position.
	 * 			| this.getBoulder().setPosition(target)
	 * @effect	This Unit's Boulder is set ineffective.
	 * 			| this.setBoulder(null)
	 * @effect	This Unit's isCarryingBoulder indicator is disabled.
	 * 			| this.setIsCarryingBoulder(false)
	 */
	@Raw
	public void dropBoulder(double[] target) throws IllegalArgumentException
	{
		getBoulder().setPosition(target);
		setBoulder(null);
		setIsCarryingBoulder(false);
	}
	
	/**
	 * Variable referencing the {@link Boulder} attached to this Unit.
	 */
	private Boulder boulder;

	/**
	 * Change the weight of this Unit according to the given {@link Log} or {@link Boulder}.
	 * 
	 * @param 	object
	 * 			The {@link Log} or {@link Boulder} for this change of weight.
	 * @post	The weight of this Unit is equal to the sum of the current weight and the weight of the given object, if the given object
	 * 			is effective.
	 * 			| if (object != null)
	 * 			|	then new.getWeight == (this.getWeight() + object.getWeight())
	 * @effect	If the given object is ineffective, set the weight to the difference between the current weight and the weight of the Log or Boulder
	 * 			this Unit is carrying.
	 * 			| if (object == null)
	 * 			|	then
	 * 			|		if (isCarryingLog())
	 * 			|			then this.setWeight(this.getWeight() - getLog().getWeight())
	 * 			|		else
	 * 			|			this.setWeight(this.getWeight() - getBoulder().getWeight())
	 */
	@Raw 
	private void changeWeight(WorldObject object)
	{
		if (object != null)
			this.weight = getWeight() + object.getWeight();
		else
		{
			if (isCarryingLog())
				setWeight(getWeight() - getLog().getWeight());
			else if (isCarryingBoulder())
				setWeight(getWeight() - getBoulder().getWeight());
		}
	}
	
	/**
	 * Check whether this Unit is attacking another Unit.
	 */
	@Basic @Raw
	public boolean isAttacking() 
	{
		return this.isAttacking;
	}
	
	/**
	 * Set the attacking state of this Unit to the given attacking state.
	 * 
	 * @param  	isAttacking
	 *         	The new attacking state for this Unit.
	 * @post   	The attacking state of this new Unit is equal to the given attacking state.
	 *       	| new.getIsAttacking() == isAttacking
	 */
	@Raw @Model
	private void setIsAttacking(boolean isAttacking)
	{
		this.isAttacking = isAttacking;
	}
	
	/**
	 * Variable registering the attacking state of this Unit.
	 */
	private boolean isAttacking;
	
	/**
	 * Check whether this Unit is defending itself.
	 */
	@Basic @Raw
	public boolean isDefending() 
	{
		return this.isDefending;
	}
	
	/**
	 * Set the defending state of this Unit to the given defending state.
	 * 
	 * @param  	isDefending
	 *         	The new defending state for this Unit.
	 * @post   	The defending state of this new Unit is equal to the given defending state.
	 *       	| new.getIsDefending() == isDefending
	 */
	@Raw
	public void setIsDefending(boolean isDefending)
	{
		this.isDefending = isDefending;
	}
	
	/**
	 * Variable registering the defending state of this Unit.
	 */
	private boolean isDefending;
	
	/**
	 * Return the fighting duration of this Unit.
	 */
	@Basic @Raw @Model
	private double getFightingDuration() 
	{
		return this.fightingDuration;
	}
	
	/**
	 * Check whether the given fighting duration is a valid fighting duration for any Unit.
	 *  
	 * @param  	fightingDuration
	 *         	The fighting duration to check.
	 * @return 	True if and only if the given fightingDuration is positive.
	 *       	| result == ((fightingDuration >= 0) && (fightingDuration < 1.2))
	 */
	private static boolean isValidFightingDuration(double fightingDuration) 
	{
		return (fightingDuration >= 0);
	}
	
	/**
	 * Set the fighting furation of this Unit to the given duration.
	 * 
	 * @param  	duration
	 *         	The new fighting duration for this Unit.
	 * @post   	The fighting duration of this new Unit is equal to the given duration.
	 *       	| new.getFightingDuration() == duration
	 * @throws 	IllegalArgumentException
	 *         	The given fightingDuration is not a valid fightingDuration for any Unit.
	 *       	| ! isValidFightingDuration(getFightingDuration())
	 */
	@Raw
	private void setFightingDuration(double duration) throws IllegalArgumentException 
	{
		if (! isValidFightingDuration(duration))
			throw new IllegalArgumentException("The given fightingDuration is too large.");
		this.fightingDuration = duration;
	}
	
	/**
	 * Variable registering the fighting duration of this Unit.
	 */
	private double fightingDuration;
	
	/**
	 * Attack another Unit.
	 * 
	 * @param 	defender
	 * 			The Unit that's being attacked.
	 * 
	 * @throws 	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 * 			Both Units aren't next to each other or they are in the same faction, thus they can't attack each other.
	 * 			| if (!World.isNeighbouringCube(getCubeCoordinates(), defender.getCubeCoordinates()) && defender.getFaction() == getFaction())
	 * @effect	The isWorking indicator of this Unit is disabled, only if both Units are next to each other, if they are from different Factions
	 * 			and if the given Unit doesn't equal this Unit and if the isWorking indicator is enabled.
	 * 			| if (World.isNeighbouringCube(getCubeCoordinates(), defender.getCubeCoordinates()) 
	 * 			|		&& defender.getFaction() != getFaction() && defender != this && isWorking())
	 * 			| 	then this.setIsWorking(false)
	 * @effect	The isResting indicator of this Unit is disabled, only if both Units are next to each other, if they are from different Factions
	 * 			and if the given Unit doesn't equal this Unit and if the isResting indicator is enabled.
	 * 			| if (World.isNeighbouringCube(getCubeCoordinates(), defender.getCubeCoordinates()) && defender.getFaction() != getFaction() 
	 * 			|		&& defender != this && isResting())
	 * 			| 	then this.setIsResting(false)
	 * @effect	The orientation of this Unit is set to the arctangent of the difference
	 * 			of the y-component of the other Unit and the y-component of this Unit
	 * 			and the difference of the x-component of the other Unit and the x-component
	 * 			of this Unit, only if both Units are next to each other and if they are from different Factions.
	 * 			| if (World.isNeighbouringCube(getCubeCoordinates(), defender.getCubeCoordinates()) && defender.getFaction() != getFaction())
	 * 			| 	then this.setOrientation(Math.atan2(defender.getUnitPosition()[1] - getUnitPosition()[1],
	 * 			| 		defender.getUnitPosition()[0] - getUnitPosition()[0]))
	 * @effect	The isAttacking indicator of this Unit is enabled, only if both Units are
	 * 			next to each other and if they are from different Factions.
	 * 			| if (World.isNeighbouringCube(getCubeCoordinates(), defender.getCubeCoordinates()) && defender.getFaction() != getFaction())
	 * 			| 	then this.setIsAttacking(true)
	 * @effect	The isMoving indicator of this Unit is disabled, only if both Units are next to each other and if they are from
	 * 			different Factions and if this Unit is currently moving.
	 * 			| if (World.isNeighbouringCube(getCubeCoordinates(), defender.getCubeCoordinates()) && defender.getFaction() != getFaction() 
	 * 			|		&& isMoving())
	 * 			| 	then this.setIsMoving(false)
	 * @effect	The wasMoving indicator of this Unit is enabled, only if both Units are next to each other and if they are from  
	 * 			different Factions and if this Unit is currently moving.
	 * 			| if (World.isNeighbouringCube(getCubeCoordinates(), defender.getCubeCoordinates()) && defender.getFaction() != getFaction() 
	 * 			|		&& isMoving())
	 * 			| 	then this.setWasMoving(true)
	 */
	public void attack(Unit defender) throws IllegalArgumentException
	{
		if (World.isNeighbouringCube(getCubeCoordinates(), defender.getCubeCoordinates()) && defender.getFaction() != getFaction())
		{
				if (isWorking())
					setWorking(false);
				if (isResting())
					setResting(false);
				if (isMoving())
				{
					setMovingState(false);
					setWasMoving(true);
				}
				double arg0 = defender.getPosition()[1] - getPosition()[1];
				double arg1 = defender.getPosition()[0] - getPosition()[0];
				setOrientation(Math.atan2(arg0, arg1));
				setIsAttacking(true);
		}
		else
			throw new IllegalArgumentException("The units must be next to each other.");
	}
	
	/**
	 * Defend from the attack of another Unit.
	 * 
	 * @param 	attacker
	 * 			The Unit that is attacking this Unit.
	 * 
	 * @throws 	IllegalArgumentException
	 * 			A condition was violated or an eeror was thrown.
	 * 			Both Units aren't next to each other or are from the same Faction, thus they can't attack each other.
	 * 			| if (!World.isNeighbouringCube(getCubeCoordinates(), attacker.getCubeCoordinates()) && attacker.getFaction() != getFaction())
	 * @effect	The isWorking indicator of this Unit is disabled, only if both Units are
	 * 			next to each other, if they are from different Factions and if the isWorking indicator is enabled.
	 * 			| if (World.isNeighbouringCube(getCubeCoordinates(), attacker.getCubeCoordinates()) && attacker.getFaction() != getFaction() 
	 * 			|		&& isWorking())
	 * 			| 	then this.setIsWorking(false)
	 * @effect	The isResting indicator of this Unit is disabled, only if both Units are
	 * 			next to each other, if they are from different Factions and if the isResting indicator is enabled.
	 * 			| if (World.isNeighbouringCube(getCubeCoordinates(), attacker.getCubeCoordinates()) && attacker.getFaction() != getFaction() 
	 * 			|		&& isResting())
	 * 			| 	then this.setIsResting(false)
	 * @effect	The isDefending indicator of this Unit is enabled, only if both Units are
	 * 			next to each other and if they are from different Factions.
	 * 			| if (World.isNeighbouringCube(getCubeCoordinates(), attacker.getCubeCoordinates()) && attacker.getFaction() != getFaction())
	 * 			| 	then this.setIsDefending(true)
	 * @effect	The orientation of this Unit is set to the arctangent of the difference
	 * 			of the y-component of the other Unit and the y-component of this Unit
	 * 			and the difference of the x-component of the other Unit and the x-component
	 * 			of this Unit, only if both Units are next to each other and if they are from different Factions.
	 * 			| if (World.isNeighbouringCube(getCubeCoordinates(), attacker.getCubeCoordinates()) && attacker.getFaction() != getFaction())
	 * 			| 	then this.setOrientation(Math.atan2(attacker.getUnitPosition()[1] - getUnitPosition()[1],
	 * 			| 		attacker.getUnitPosition()[0] - getUnitPosition()[0]))
	 * @effect	The current hitpoints of this Unit is set to the difference between its current hitpoints
	 * 			and the other Unit's strength divided by 10, only if both Units are next to each other,
	 * 			if they are from differen Factions and if this Unit didn't block or dodge the attack.
	 * 			| if (World.isNeighbouringCube(getCubeCoordinates(), attacker.getCubeCoordinates()) && attacker.getFaction() != getFaction() 
	 * 			|		&& !Dodged(attacker) && !Blocked(attacker))
	 * 			| 	then this.setCurrentHitpoints(getCurrentHitpoints() - (attacker.getStrength() / 10.0))
	 * @effect	The isMoving indicator of this Unit is disabled, only if both Units are next to each other, if they are from 
	 * 			differen Factions and if this Unit is currently moving.
	 * 			| if (World.isNeighbouringCube(getCubeCoordinates(), attacker.getCubeCoordinates()) && attacker.getFaction() != getFaction() 
	 * 			|		&& isMoving())
	 * 			| 	then this.setIsMoving(false)
	 * @effect	The wasMoving indicator of this Unit is enabled, only if both Units are next to each other, if they are from
	 * 			different Factions and if this Unit is currently moving.
	 * 			| if (World.isNeighbouringCube(getCubeCoordinates(), attacker.getCubeCoordinates()) && attacker.getFaction() != getFaction()
	 * 			|		 && isMoving())
	 * 			| 	then this.setWasMoving(true)
	 * @effect	The experience of this Unit is set to the sum of the current experience and 20, only if both Units are next to each
	 * 			other, if they are from different Factions and if this Unit has either dogded or blocked the incoming attack.
	 * 			| if (World.isNeighbouringCube(getCubeCoordinates(), attacker.getCubeCoordinates()) && attacker.getFaction() != getFaction()
	 * 			|		&& (hasDogded() || hasBlocked()))
	 * 			|	then this.setExperience(getExperience() + 20)
	 */
	public void defend(Unit attacker) throws IllegalArgumentException
	{
		if (World.isNeighbouringCube(getCubeCoordinates(), attacker.getCubeCoordinates()) && attacker.getFaction() != getFaction())
		{
				if (isWorking())
					setWorking(false);
				if (isResting())
					setResting(false);
				if (isMoving())
				{
					setMovingState(false);
					setWasMoving(true);
				}
				setIsDefending(true);
				boolean hasEarnedExp = false;
				double arg0 = attacker.getPosition()[1] - getPosition()[1];
				double arg1 = attacker.getPosition()[0] - getPosition()[0];
				setOrientation(Math.atan2(arg0, arg1));
				
				if (hasDogded(attacker))
				{
					System.out.println("Dogded the attack.");
					int dx = new Random().nextInt(2);
					int dy = new Random().nextInt(2);
					int dz = new Random().nextInt(2);
					
					moveToAdjacent(dx, dy, dz);
					hasEarnedExp = true;
				}
				else if (hasBlocked(attacker))
				{
					hasEarnedExp = true;
					System.out.println("Blocked the attack.");
				}
				else
				{
					double damage = attacker.getStrength() / 10.0;
					setCurrentHitpoints(getCurrentHitpoints() - (int)damage);
				}
				if (hasEarnedExp)
					setExperience(getExperience() + 20);
		}
		else
			throw new IllegalArgumentException("The units must be next to each other.");
	}
	
	/**
	 * Check whether this Unit has dodged an incoming attack.
	 * 
	 * @param 	attacker
	 * 			The Unit attacking this Unit.
	 * @return	True if and only if the new random double is lower or equal than 0.2 times this Unit's agility divided
	 * 			by the attacker's agility.
	 * 			| result == new Random().nextDouble() <= 0.2*(getAgility()/attacker.getAgility())
	 */
	private boolean hasDogded(Unit attacker)
	{
		double propability = 0.2*(getAgility()/attacker.getAgility());
		boolean hasDogded = new Random().nextDouble() <= propability;
		return hasDogded;
	}
	
	
	/**
	 * Check whether this Unit has blocked an incoming attack.
	 * 
	 * @param 	attacker
	 * 			The Unit attacking this Unit.
	 * @return	True if and only if the new random double is lower or equal than 0.25 times the sum of this Unit's strength
	 * 			and agility divided by the sum of the attackers strength and agility.
	 * 			| result == new Random().nextDouble() <= 0.25*( (getStrength() + getAgility()) / 
	 * 			| ( attacker.getStrength() + attacker.getAgility() ) )
	 */
	private boolean hasBlocked(Unit attacker)
	{
		double propability = 0.25*((getStrength() + getAgility()) / (attacker.getStrength() + attacker.getAgility()));
		boolean hasBlocked = new Random().nextDouble() <= propability;
		return hasBlocked;
	}
	
	/**
	 * Check whether this Unit is resting.
	 */
	@Basic @Raw
	public boolean isResting()
	{
		return this.isResting;
	}
	
	/**
	 * Check whether this Unit can have the given resting state as its resting state.
	 * 
	 * @param  	isResting
	 *         	The resting state to check.
	 * @return 	True if and only if the given isResting indicator is enabled and this Unit isn't currently attacking or defending or if
	 * 			the given isResting indicator is false.
	 *       	| result == ( (isResting && (!isAttacking() || !isDefending())) || !isResting )
	 */
	public boolean canHaveAsIsResting(boolean isResting)
	{
		return ( (isResting && (!isAttacking() || !isDefending()) ) || !isResting);
	}
	
	/**
	 * Set the resting state of this Unit to the given resting state.
	 * 
	 * @param  	isResting
	 *         	The new resting state for this Unit.
	 * @post   	The resting state of this new Unit is equal to the given resting state.
	 *       	| new.getIsResting() == isResting
	 * @throws 	IllegalArgumentException
	 *         	This Unit cannot have the given resting state as its resting state.
	 *       	| ! canHaveAsIsResting(getIsResting())
	 */
	@Raw @Model
	private void setResting(boolean isResting) throws IllegalArgumentException 
	{
		if (! canHaveAsIsResting(isResting))
			throw new IllegalArgumentException("The given value isResting is invalid for this Unit.");
		this.isResting = isResting;
	}
	
	/**
	 * Make this Unit rest.
	 * 
	 * @throws 	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 * @effect	The isWorking indicator of this Unit is disabled, only if this Unit isn't attacking and if this Unit's current hitpoints
	 * 			are lower than the max hitpoints and if this Unit's current staminapoints is lower than the max staminapoints and if the
	 * 			Unit isn't already Resting and if the Unit is working.
	 * 			| if (!isAttacking() && (getCurrentHitpoints() < getMaxHitpoints() || getCurrentStaminapoints() < getMaxStaminapoints()) 
	 * 			| 	&& !isResting() && isWorking())
	 * 			| 	then this.setIsWorking(false)
	 * @effect	The isResting indicator of this Unit is enabled, only if this Unit isn't attacking and if this Unit's current hitpoints 
	 * 			are lower than the max hitpoints or if this Unit's current stamina points is lower than the max staminapoints and if the 
	 * 			Unit isn't already resting.
	 * 			| if (!isAttacking() && (getCurrentHitpoints() < getMaxHitpoints() || getCurrentStaminapoints() < getMaxStaminapoints())
	 * 			| 	&& !isResting())
	 * 			| 	then this.setIsResting(true)
	 * @effect	The restingDuration of this Unit is set to 0, only if this Unit isn't attacking and if this Unit's current hitpoints
	 * 			are lower than the max hitpoints or if this Unit's current stamina points is lower than the max staminapoints and if the
	 * 			Unit isn't already resting.
	 *  		| if (!isAttacking() && (getCurrentHitpoints() < getMaxHitpoints() || getCurrentStaminapoints() < getMaxStaminapoints())
	 *  		| 	&& !isResting())
	 * 			| 	then this.setRestingDuration(0)
	 * @effect	The tempHitpoint of this Unit is set to 0, only if this Unit isn't attacking and if this Unit's current hitpoints
	 * 			are lower than the max hitpoints or if this Unit's current stamina points is lower than the max staminapoints and if the
	 * 			Unit isn't already resting.
	 * 			| if (!isAttacking() && (getCurrentHitpoints() < getMaxHitpoints() || getCurrentStaminapoints() < getMaxStaminapoints())
	 * 			| 	&& !isResting())
	 * 			| 	then this.setTempHitpoint(0)
	 * @effect	The tempStaminapoint of this Unit is set to 0, only if this Unit isn't attacking and if this Unit's current hitpoints 
	 * 			are lower than the max hitpoints or if this Unit's current stamina points is lower than the max staminapoints and if the
	 * 			Unit isn't already resting.
	 * 	 		| if (!isAttacking() && (getCurrentHitpoints() < getMaxHitpoints() || getCurrentStaminapoints() < getMaxStaminapoints())
	 * 			|	&& !isResting())
	 * 			| 	then this.setTempStaminapoint(0)
	 * @effect	The isMoving indicator is disabled, only if this Unit isn't attacking and if this Unit's current hitpoits are lower
	 * 			than the max hitpoints or if this Unit's current staminapoints are lower than the max staminapoints and if the Unit isn't
	 * 			already resting and if the Unit is currently moving.
	 * 			| if (!isAttacking() && (getCurrentHitpoints() < getMaxHitpoints() || getCurrentStaminapoints() < getMaxStaminapoints())
	 * 			| 	&& !isResting() && isMoving())
	 * 			| 	then this.setIsMoving(false)
	 * @effect	The wasMoving indicator is enabled, only if this Unit isn't attacking and if this Unit's current hitpoints are kiwer 
	 * 			than the max hitpoints or if this Unit's current staminapoints are lower than the max staminapoints and if the Unit isn't
	 * 			already resting and if the Unit is currently moving.
	 * 			| if (!isAttacking() && (getCurrentHitpoints() < getMaxHitpoints() || getCurrentStaminapoints() < getMaxStaminapoints())
	 * 			| 	&& !isResting() && isMoving())
	 * 			| 	then this.setWasMoving(true)
	 */
	public void rest() throws IllegalArgumentException
	{
		if (!isAttacking() && (getCurrentHitpoints() < getMaxHitpoints() || getCurrentStaminapoints() < getMaxStaminapoints()) && !isResting())
		{
			if (isWorking())
				setWorking(false);
			if (isMoving())
			{
				setMovingState(false);
				setWasMoving(true);
			}
			if (hasRestedInitialRecoveryPeriod())
				setRecoveryState(false);
			setResting(true);
			setRestingDuration(0);
			setTempHitpoint(0);
			setTempStaminapoint(0);
		}
		/*else
			throw new IllegalStateException("Unable to rest right now.");*/
	}
	
	/**
	 * Variable registering the resting state of this Unit.
	 */
	private boolean isResting;

	/**
	 * Return the period for which this Unit hasn't been resting.
	 */
	@Basic @Raw @Model
	private double getCurrentRestingPeriod() 
	{
		return this.currentRestingPeriod;
	}
	
	/**
	 * Check whether the given resting period is a valid resting period for any Unit.
	 *  
	 * @param  	resting period
	 *         	The resting period to check.
	 * @return 	True if and only if the given restingPeriod is between 0 and RESTING_PERIOD + 0.2 (inclusive).
	 *       	| result == ( (currentRestingPeriod >= 0) && (currentRestingPeriod <= RESTING_PERIOD + 0.2) )
	 * @note	The resting period can only be between 0 and the resting period + 0.2, because the advanceTime can have 0.2 
	 * 			as its max value to work.
	 */
	private static boolean isValidRestingPeriod(double currentRestingPeriod) 
	{
		return ((currentRestingPeriod >= 0) && (currentRestingPeriod <= RESTING_PERIOD + 0.2));
	}
	
	/**
	 * Set the resting period of this Unit to the given resting period.
	 * 
	 * @param  	restingPeriod
	 *         	The new resting period for this Unit.
	 * @post  	The resting period of this new Unit is equal to the given resting period.
	 *       	| new.getRestingPeriod() == restingPeriod
	 * @throws 	IllegalArgumentException
	 *         	The given resting period is not a valid resting period for any Unit.
	 *       	| ! isValidRestingPeriod(getRestingPeriod())
	 */
	@Raw @Model
	private void setCurrentRestingPeriod(double currentRestingPeriod) throws IllegalArgumentException 
	{
		if (! isValidRestingPeriod(currentRestingPeriod))
			throw new IllegalArgumentException("The current resting period is too large.");
		this.currentRestingPeriod = currentRestingPeriod;
	}
	
	/**
	 * Variable registering the period of time the Unit hasn't been resting. After 180 seconds, the Unit must rest.
	 */
	private double currentRestingPeriod;
	
	/**
	 * Constant registering the period whenever a Unit must rest, which is after 180 seconds.
	 */
	private final static int RESTING_PERIOD = 180;

	/**
	 * Return the duration this Unit has been resting for.
	 */
	@Basic @Raw @Model
	private double getRestingDuration() 
	{
		return this.restingDuration;
	}
	
	/**
	 * Check whether the given resting duration is a valid resting duration for any Unit.
	 *  
	 * @param  	resting duration
	 *         	The resting duration to check.
	 * @return	True if and only if the given restingDuration is positive or equal to zero.
	 *       	| result == (restingDuration >= 0)
	 */
	private static boolean isValidRestingDuration(double restingDuration) 
	{
		return (restingDuration >= 0);
	}
	
	/**
	 * Set the resting duration of this Unit to the given resting duration.
	 * 
	 * @param  	restingDuration
	 *         	The new resting duration for this Unit.
	 * @post   	The resting duration of this new Unit is equal to the given resting duration.
	 *       	| new.getRestingDuration() == restingDuration
	 * @throws 	IllegalArgumentException
	 *         	The given resting duration is not a valid resting duration for any Unit.
	 *       	| ! isValidRestingDuration(getRestingDuration())
	 */
	@Raw @Model
	private void setRestingDuration(double restingDuration) throws IllegalArgumentException 
	{
		if (! isValidRestingDuration(restingDuration))
			throw new IllegalArgumentException("The given restingDuration is invalid for this Unit.");
		this.restingDuration = restingDuration;
	}
	
	/**
	 * Variable registering the resting duration of this Unit.
	 */
	private double restingDuration;

	/**
	 * Check wheter this Unit has rested its initial recovery period.
	 */
	@Basic @Raw
	private boolean hasRestedInitialRecoveryPeriod() 
	{
		return this.hasRestedInitialRecoveryPeriod;
	}
	
	/**
	 * Set the recovery state of this Unit to the given recovery state.
	 * 
	 * @param  	recoveryState
	 *         	The new recovery state for this Unit.
	 * @post   	The recovery state of this new Unit is equal to the given recovery state.
	 *       	| new.getHasRestedOnePoint() == recoveryState
	 */
	@Raw @Model
	private void setRecoveryState(boolean recoveryState) 
	{
		this.hasRestedInitialRecoveryPeriod = recoveryState;
	}
	
	/**
	 * Variable registering whether this Unit has rested its Initial recovery period.
	 */
	private boolean hasRestedInitialRecoveryPeriod = true;
	
	/**
	 * Check whether this Unit has its default behaviour enabled.
	 */
	@Basic @Raw
	public boolean isDefaultBehaviourEnabled() 
	{
		return this.isDefaultBehaviourEnabled;
	}
	
	/**
	 * Check whether this Unit can have the given isDefaultBehaviourEnabled indicator as its isDefaultBehaviourEnabled indicator.
	 * 
	 * @param  	isDefaultBehaviourEnabled
	 *         	The default behaviour indicator to check.
	 * @return 	True if and only if the Unit isn't currently attacking or defending.
	 *       	| result == ((!isAttacking() || !isDefending())
	 */
	@Raw
	public boolean canHaveAsIsDefaultBehaviour(boolean isDefaultBehaviourEnabled) 
	{
		return (!isAttacking() || !isDefending());
	}
	
	/**
	 * Set the default behaviour state of this Unit to the given default behaviour state.
	 * 
	 * @param  	isDefaultBehaviourEnabled
	 *         	The new default behaviour state for this Unit.
	 * @post   	The default behaviour state of this new Unit is equal to the given default behaviour state.
	 *       	| new.getIsDefaulBehaviour() == isDefaultBehaviourEnabled
	 * @throws 	IllegalArgumentException
	 *         	This Unit cannot have the given default behaviour state as its default behaviour state.
	 *       	| ! canHaveAsIsDefaulBehaviour(getIsDefaulBehaviour())
	 */
	@Raw
	public void setDefaultBehaviour(boolean isDefaultBehaviourEnabled) throws IllegalArgumentException 
	{
		if (! canHaveAsIsDefaultBehaviour(isDefaultBehaviourEnabled))
			throw new IllegalArgumentException("The given value isDefaultBehaviourEnabled is invalid for this Unit.");
		this.isDefaultBehaviourEnabled = isDefaultBehaviourEnabled;
	}
	
	/**
	 * Variable registering the default behaviour stater of this Unit.
	 */
	private boolean isDefaultBehaviourEnabled;

	/**
	 * Return the {@link Faction} to which this Unit belongs.
	 */
	@Basic @Raw
	public Faction getFaction() 
	{
		return this.faction;
	}
	
	/**
	 * Check whether this Unit can have the given {@link Faction} as the {@link Faction} to which this Unit can be attached to.
	 *  
	 * @param  	faction
	 *         	The {@link Faction} to check.
	 * @return 	If this Unit is dead, true if and only if the given Faction is ineffective.
	 * 			Otherwise, true if and only if the given Faction is part of this Unit's World and if that Faction can have this Unit as
	 * 			one of its Units.
	 * 			| if (!isAlive())
	 * 			|	result == (faction == null)
	 * 			| else
	 * 			|	result == ( getWorld().hasAsFaction(faction) && faction.canHaveAsUnit(this) )
	 */
	@Raw
	public boolean canHaveAsFaction(Faction faction)
	{
		if (!isAlive())
			return faction == null;
		else
			return getWorld().hasAsFaction(faction) && faction.canHaveAsUnit(this);
	}
	
	/**
	 * Set the faction of this unit to the given faction.
	 * 
	 * @param  	faction
	 *         	The new Faction for this unit.
	 *         
	 * @post   	The faction of this new unit is equal to the given faction.
	 *       	| new.getFaction() == faction
	 *       
	 * @effect	Add this Unit to given Faction, if the given Faction is effective.
	 * 			| if (faction != null)
	 * 			| 	then this.getFaction().addUnit(this)
	 * 
	 * @throws 	IllegalArgumentException
	 *         	This Unit cannot have the given Faction as one of its Factions.
	 *       	| ! canHaveAsFaction(getFaction())
	 */
	@Raw
	public void setFaction(Faction faction) throws IllegalArgumentException
	{
		if (! canHaveAsFaction(faction))
			throw new IllegalArgumentException("The given faction is invalid for any Unit.");
		this.faction = faction;
	}
	
	/**
	 * Check whether this Unit has a proper {@link Faction} attached to it.
	 * 
	 * @return	True if and only if this Unit can have its Faction as its Faction and if this Faction has this Unit as its Unit.
	 * 			| result == ( (getFaction() == null) || (canHaveAsFaction(getFaction()) && getFaction().hasAsUnit(this)) )
	 */
	public boolean hasProperFaction()
	{
		return (getFaction() == null) || (canHaveAsFaction(getFaction()) && getFaction().hasAsUnit(this));
	}
	
	/**
	 * Variable registering the {@link Faction} of this unit.
	 */
	private Faction faction;
	
	/**
	 * Return the experience of this Unit.
	 */
	@Basic @Raw
	public int getExperience() 
	{
		return this.experience;
	}
	
	/**
	 * Check whether the given experience is a valid experience for any Unit.
	 *  
	 * @param  	experience
	 *         	The experience to check.
	 * @return 	True if and only if the given experience is positive.
	 *       	| result == (experience >= 0) 
	 */
	public static boolean isValidExperience(int experience) 
	{
		return (experience >= 0);
	}
	
	/**
	 * Set the experience of this Unit to the given experience.
	 * 
	 * @param 	experience
	 *        	The new experience for this Unit.
	 * @post   	The experience of this new Unit is equal to the given experience.
	 *       	| new.getExperience() == experience
	 * @throws 	IllegalArgumentException
	 *         	The given experience is not a valid experience for any Unit.
	 *       	| ! isValidExperience(getExperience())
	 */
	@Raw @Model
	private void setExperience(int experience) throws IllegalArgumentException 
	{
		if (! isValidExperience(experience))
			throw new IllegalArgumentException("The given experience is invalid for any Unit.");
		this.experience = experience;
	}
	
	/**
	 * Variable registering the experience of this Unit.
	 */
	private int experience;
	
	/**
	 * Check whether this Unit is alive.
	 */
	@Basic @Raw
	public boolean isAlive() 
	{
		return this.isAlive;
	}
	
	/**
	 * Variable registering whether this Unit is alive.
	 */
	private boolean isAlive = true;
	
	/**
	 * Return the {@link World} to which this Unit is attached.
	 */
	public World getWorld()
	{
		return this.world;
	}
	
	/**
	 * Check if this Unit can have the given {@link World} as the {@link World} to which it is attached.
	 * 
	 * @param 	world
	 * 			The {@link World} to check.
	 * @return	If this Unit is dead, true if and only if the given World is ineffective.
	 * 			Otherwise, true if and only if the given World is effective and if that World can have this Unit as one of its Units.
	 * 			| if (!isAlive())
	 * 			|	then result == (world == null)
	 * 			| else
	 * 			|	result == ( (world != null) && world.canHaveAsUnit(this) )
	 */
	public boolean canHaveAsWorld(World world)
	{
		if (!isAlive)
			return (world == null);
		else return world != null && world.canHaveAsUnit(this);
	}
	
	/**
	 * Set the {@link World} to which this Unit is attached to the given {@link World}.
	 * 
	 * @param 	world
	 * 			The {@link World} to which this Unit will be attached. 
	 * @post	The World of this new Unit is equal to the given World.
	 * 			| new.getWorld() == world
	 * @throws	IllegalArgumentException
	 * 			This Unit cannot have the given World as the World to which it is attached.
	 * 			| (!canHaveAsWorld(world))
	 */
	@Raw
	public void setWorld(World world) throws IllegalArgumentException
	{
		if (!canHaveAsWorld(world))
			throw new IllegalArgumentException("Invalid World for this Unit!");
		this.world = world;
	}
	
	/**
	 * Check whether this Unit has a proper {@link World} to which it is attached.
	 * 
	 * @return	True if and only if this Unit can have its World as the World to which it is attached and if that World has 
	 * 			this Unit as one of its Units.
	 *			| if ( (!canHaveAsWorld(getWorld())) && (!getWorld().hasAsUnit(this)) )
	 *			| 	then result == false
	 *			| else 
	 *			| 	result == true
	 */
	public boolean hasProperWorld()
	{
		if ((!canHaveAsWorld(getWorld())) && (!getWorld().hasAsUnit(this)))
			return false;
		return true;
	}
	
	/**
	 * Variable referencing the {@link World} to which this Unit is attached.
	 */
	private World world;
	
	/**
	 * Return the {@link Task} that is assigned to this Unit.
	 */
	@Basic @Raw
	public Task getTask()
	{
		return this.task;
	}
	
	/**
	 * Check whether this Unit can have the given {@link Task} as its assigned {@link Task}.
	 * 
	 * @param 	task
	 * 			The Task to check.
	 * @return	True if and only if the given Task is ineffective or if it is effective and listed as one of the Tasks of the Scheduler
	 * 			of this Unit's Faction.
	 * 			| if (task == null)
	 * 			|	result == true
	 * 			| else
	 * 			|	result == getFaction().getScheduler().hasAsTask(task)
	 */
	@Raw
	public boolean canHaveAsTask(Task task)
	{
		if (task == null)
			return true;
		return getFaction().getScheduler().hasAsTask(task);
	}
	
	/**
	 * Assign the given Task as this Unit's Task.
	 * 
	 * @param 	task
	 * 			The given Task to assign.
	 * @post	The Task of this Unit is equal to the given Task.
	 * 			| new.getTask() == task
	 * @throws	IllegalArgumentException
	 * 			This Unit cannot have the given Task as its assigned Task
	 * 			| !canHaveAsTask(task)
	 */
	@Raw
	public void setTask(Task task) throws IllegalArgumentException
	{
		if (!canHaveAsTask(task))
			throw new IllegalArgumentException("This Unit cannot assign the given Task as its Task.");
		this.task = task;
	}
	
	/**
	 * Check whether this Unit has a proper {@link Task} assigned to it.
	 * 
	 * @return	True if and only if this Unit can have its Task as its Task, if this Unit is alive and if the Task has this Unit as its Unit or
	 * 			if this Unit is dead.
	 * 			| if (canHaveAsTask(getTask())
	 * 			|	then
	 * 			|		if (isAlive())
	 * 			|			then result == ( getTask().getUnit() == this )
	 * 			|		else
	 * 			|			result == true
	 * 			| else
	 * 			|	result == false
	 */
	public boolean hasProperTask()
	{
		if (canHaveAsTask(getTask()))
			if (isAlive())
				return getTask().getUnit() == this;
			else
				return true;
		return false;
	}
	
	/**
	 * Variable referencing the {@link Task} assigned to this Unit.
	 */
	private Task task;
}