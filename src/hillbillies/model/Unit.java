package hillbillies.model;

import java.util.*;
import be.kuleuven.cs.som.annotate.*;
import ogp.framework.util.ModelException;

/* 
 * QUESTIONS PART 3:
 *
 * STUFF TODO:
 * 		1. Check all TODO's.
 * 		2. Check which methods can be private.
 * 		3. Initialize default values in the constructor.
 * 		4. Check bidirectional associations (see coding rule 93 and book p420-441)
 * 		5. Add @Raw annotation to associations with objects that doesn't have to be in proper state (basically almost every association)
 * 		6. Change Faction to allow it to remove itself if there are no Units left. To do this, we must create an association with World.
 * 		7. There is an error with isFalling, which happens in rare occurences. Please fix.
 * 		8. Don't forget to add @Model tags to all private methods that are used in a specification of another method.
 */

/** 
 * A class of Units which can have a name, agility, strength, weight, toughness, maximum hitpoints, maximum staminapoints, current hitpoints, 
 * current staminapoints, orientation, cube coordinates, position, default behaviour.
 * 
 * @version 0.7
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
 */

public class Unit {
	/**
	 * Create a new Unit with a given name, strength, agility, toughness, weight, initialPosition and enableDefaultBehaviour.
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
	 * 
	 * @throws 	ModelException 
	 *       	A condition was violated or an error was thrown.
	 *       
	 * @effect	The world of this new Unit is set to the given world.
 	 * 			| this.setWorld(world)
 	 * 
	 * @effect 	The name of this new Unit is set to the given name.
	 *       	| this.setName(name)
	 * 
	 * @effect	The orientation of this new Unit is set to the default orientation.
	 * 			| this.setOrientation(DEFAULT_ORIENTATION)
	 *       
 	 * @effect 	The cubeCoordinates of this new Unit is set to the given initialPosition.
 	 *       	| this.setCubeCoordinates(initialPosition)
 	 *       
 	 * @effect	The Unit position of this new Unit is set to the current cube coordinates.
 	 * 			| this.setUnitPosition(getCubeCoordinates())
 	 * 
 	 * @effect	The isDefaultBehaviour of this new Unit is set to the given enableDefaultBehaviour.
 	 * 			| this.setIsDefaultBehaviour(enableDefaultBehaviour)
 	 * 
 	 * @effect	The hasRestedOnePoint of this new Unit is set to true.
 	 * 			| this.setHasRestedOnePoint(true)
 	 * 
 	 * @effect	The experience of this new Unit is set to 0.
 	 * 			| this.setExperience(0)
 	 * 
	 * @post   	If the given strength is a valid strength for any Unit,
	 *         	the strength of this new Unit is equal to the given
	 *        	strength. Otherwise, the strength of this new Unit is equal
	 *         	to DEFAULT_STRENGTH.
	 *       	| if (isValidInitialValue(strength))
	 *       	|   then new.getStrength() == strength
	 *       	|   else new.getStrength() == DEFAULT_STRENGTH
	 *       
	 * @post   	If the given agility is a valid agility for any Unit,
	 *         	the agility of this new Unit is equal to the given
	 *         	agility. Otherwise, the agility of this new Unit is equal
	 *         	to DEFAULT_AGILITY.
	 *       	| if (isValidInitialValue(agility))
	 *       	|   then new.getAgility() == agility
	 *       	|   else new.getAgility() == DEFAULT_AGILITY
	 *       
	 * @post   	If the given toughness is a valid initial toughness for any Unit,
	 *         	the toughness of this new Unit is equal to the given
	 *         	toughness. Otherwise, the toughness of this new Unit is equal
	 *         	to DEFAULT_TOUGHNESS.
	 *       	| if (isValidInitialValue(toughness))
	 *       	|   then new.getToughness() == toughness
	 *       	|   else new.getToughness() == DEFAULT_TOUGHNESS
	 *       
	 * @post   	If the given weight is a valid initial weight for any Unit,
	 *         	the weight of this new Unit is equal to the given
	 *         	weight. Otherwise, the weight of this new Unit is equal
	 *         	to DEFAULT_WEIGHT.
	 *       	| if (isValidInitialValue(weight))
	 *       	|   then new.getWeight() == weight
	 *       	|   else new.getWeight() == DEFAULT_WEIGHT
	 *       
	 * @post	The maxHitpoints of this new Unit is equal to a rounded integer which is 200 times a 100th of its weight 
	 * 			times a 100th of its toughness.
	 * 			| new.getMaxHitpoints() == (int)Math.round(200*((double)this.weight/100)*((double)this.toughness/100))
	 * 
	 * @post	The maxStaminapoints of this new Unit is equal to a rounded integer which is 200 times a 100th of its weight
	 * 			times a 100th of its toughness.
	 * 			| new.getMaxStaminaPoints() == (int)Math.round(200*((double)this.weight/100)*((double)this.toughness/100))
	 * 
	 * @post	The currentHitpoints of this new Unit is equal to the maxHitpoints of this Unit.
	 * 			| new.getCurrentHitpoints() == getMaxHitpoints()
	 * 
	 * @post	The currentStaminapoints of this new Unit is equal to the currentStaminapoints of this Unit.
	 * 			| new.getCurrentStaminapoints() == getMaxStaminapoints()
	 */
	public Unit(String name, int strength, int agility, int toughness, int weight, int[] initialPosition, boolean enableDefaultBehaviour
			, World world) throws ModelException
	{
			// The world of this Unit:
			setWorld(world);
			
			// Make the Unit alive: 
			setIsAlive(true);
			
			// Name of this Unit:
			setName(name);
			
			// Strength of this Unit:
			if (! isValidInitialValue(strength))
				strength = DEFAULT_STRENGTH;
			setStrength(strength);
			
			// Agility of this Unit: 
			if (! isValidInitialValue(agility))
				agility = DEFAULT_AGILITY;
			setAgility(agility);
			
			// Toughness of this Unit:
			if (! isValidInitialValue(toughness))
				toughness = DEFAULT_TOUGHNESS;
			setToughness(toughness);
			
			// Weight of this Unit: 
			setDefaultWeight();
			if (! isValidInitialValue(weight))
				weight = getDefaultWeight();	
			setWeight(weight);
			
			// Hitpoints of this Unit:
			//this.maxHitpoints = (int)Math.round(200*((double)this.weight/100)*((double)this.toughness/100));
			 this.maxHitpoints = 10;
			setCurrentHitpoints(getMaxHitpoints());
			
			// Staminapoints of this Unit:
			this.maxStaminaPoints = (int)Math.round(200*((double)this.weight/100)*((double)this.toughness/100));
			setCurrentStaminapoints(getMaxStaminapoints());
			
			// Orientation of this Unit:
			setOrientation(DEFAULT_ORIENTATION);
			
			// Position of this Unit:
			setCubeCoordinates(initialPosition);
			setUnitPosition(getCubeCoordinates());
			
			// Default behaviour of this Unit: We will only change the behaviour to true if true is given. Otherwise the checker is invalid!
			if (enableDefaultBehaviour)
				setIsDefaultBehaviour(enableDefaultBehaviour);
			
			setIsDefaultBehaviour(false);
			
			// Set the hasRestedOnePoint boolean true, because this has to be true, only if the Unit starts to rest, this will become false.
			setHasRestedOnePoint(true);
			
			// Set the experience to 0:
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
	 * @throws 	ModelException
	 * 			A condition was violated or an error was thrown.
	 * 
	 * @effect	This new Unit is initialized with the given name, strength, agility, toughness, weight, initialPosition, enableDefaultBeahviour and
	 * 			no world.
	 * 			| this(name, strength, agility, toughness, weight, initialPosition, enableDefaultBehaviour, null);
	 */
	public Unit(String name, int strength, int agility, int toughness, int weight, int[] initialPosition, boolean enableDefaultBehaviour) 
			throws ModelException
	{
		this(name, strength, agility, toughness, weight, initialPosition, enableDefaultBehaviour, null);
	}
	
	/**
	 * Advance the time of the game.
	 * 
	 * @param 	duration
	 * 			The amount of time that has been passed.
	 * @throws 	ModelException
	 * 			An exception or error was thrown or the duration was too large (> 0.2).
	 */
	public void advanceTime(double duration) throws ModelException
	{
		if (duration <= 0.2 && duration >= 0) // [FIXME] NORMALLY THIS SHOULD BE BETWEEN 0.2 (EXCLUSIVE) AND 0 (INCLUSIVE).
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
				if (getStrength() < MAX_ATTRIBUTE_VALUE || getAgility() < MAX_ATTRIBUTE_VALUE || getToughness() < MAX_ATTRIBUTE_VALUE)
					increaseSkills();
			}
			
			// Falling:
			if (mustUnitFall(getCubeCoordinates()))
				setIsFalling(true);
			
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
							setIsResting(false);
							if (wasMoving())
							{
								setIsMoving(true);
								setWasMoving(false);
							}
						}
						else
						{
							// Count down the initial period of 1 hitpoint recovery:
							if (!hasRestedOnePoint())
							{
								setTempHitpoint(getTempHitpoint() + (getToughness() / 200.0)); // Count the temp hitpoint.
								
								if (getTempHitpoint() >= 1)
										setHasRestedOnePoint(true);
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
							if (!hasRestedOnePoint())
								setHasRestedOnePoint(true);
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
						setIsMoving(true);
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
				for(int i = 0; i < getUnitPosition().length; i++)
				{
					setUnitPosition(i, getUnitPosition()[i] + (getVelocity()[i]*duration));
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
						setIsMoving(false);
						System.out.println("Stop with sprinting...");
						if (isSprinting())
							setIsSprinting(false);
					}	
					
					setCubeCoordinates(getNextCubeCoordinates()); // Correct the current cube coordinates.
					setUnitPosition(getNextCubeCoordinates()); // Correct the unit's position.
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
					double x = getTargetCube()[0] + (getWorld().getCubeLength() / 2.0);
					double y = getTargetCube()[1] + (getWorld().getCubeLength() / 2.0);
					double z = getTargetCube()[2] + (getWorld().getCubeLength() / 2.0);
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
						if (getToughness() < MAX_ATTRIBUTE_VALUE)
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
					
					setIsWorking(false);
					
				}
			}
			
			// Default behaviour:
			else if (isDefaultBehaviourEnabled())
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
					throw new ModelException("Error with default behaviour choice.");					
				}
			}
		}
		else
			throw new ModelException("The duration of advanceTime was too large: " + duration);
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
	 * @throws	ModelException
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
	public void moveToAdjacent(int dx, int dy, int dz) throws ModelException
	{
		// This method will only work if the Unit isn't currently already moving and if the destination cube isn't the same cube.
		if (!isMovingToAdjacent() && !(dx == 0 && dy == 0 && dz == 0) && hasRestedOnePoint())
		{
			try {
				// Disable behaviours and enable isMoving if it isn't already: 
				if (!isMoving())
					setIsMoving(true);
				if (isResting())
					setIsResting(false);
				if (isWorking())
					setIsWorking(false);
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
					setNextCubeCoordinates(newCoordinates);
					
					// Set base speed:
					double baseSpeed = 1.5*((getStrength() + getAgility()) / (200.0*(getWeight() / 100.0)));
					setBaseSpeed(baseSpeed);
					
					// Set walking speed:
					switch(getCubeCoordinates()[2]- newCoordinates[2])
					{
					case -1:
						setWalkingSpeed(getBaseSpeed() / 2);
						break;
					case 1:
						setWalkingSpeed(getBaseSpeed()*1.2);
						break;
					default:
						setWalkingSpeed(getBaseSpeed());
						break;
					}
					
					// Set sprinting speed:
					setSprintSpeed(getWalkingSpeed() * 2);
					
					// Set the velocity:
					if (!isFalling())
						setVelocity(newCoordinates);
					else
					{
						setVelocity(0, 0, -3);
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
					throw new ModelException("Can't move towards this cube.");
			}
			catch (ModelException e)
			{
				//e.printStackTrace();
				
				// Disable movement:
				setIsMovingToAdjacent(false);
				setIsMoving(false);
			}
		}
	}
	
	/**
	 * Move this unit to the given cube.
	 * 
	 * @param 	cube
	 * 			The given cube to move to.
	 * 
	 * @throws 	ModelException
	 * 			A condition was violated or an error was thrown.
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
	public void moveTo(int[] cube) throws ModelException
	{
		if (hasRestedOnePoint())
		{
			try
			{
				if (!isMoving())
					setIsMoving(true);
				if (isResting())
					setIsResting(false);
				if (isWorking())
					setIsWorking(false);
				
				setIsMovingTo(true);
				setDestinationCube(cube);
				
			}
			catch (ModelException e)
			{
				//e.printStackTrace();
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
	 * @throws ModelException
	 */
	private void findPath() throws ModelException
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
			System.out.println("This Unit can move to its destination.");
			Tuple<int[], Integer> next = findNextCube(getCubeCoordinates());
			// If the returned tuple from the queue is null, pathing is terminated.
			if (next.getFirstValue() == null)
			{
				System.out.println("Pathing will now end, because there isn't a next cube.");
				setIsMovingTo(false);
				throw new ModelException("Pathing will here.");
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
					setIsMovingTo(false);
				}

				else 
					moveToAdjacent(dx, dy, dz);
			}
		}
		else
		{
			setIsMovingTo(false);
			setIsMoving(false);
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
			if (isNeighbouringCube(next.getFirstValue()))
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
		for (int i = 0; i < getUnitPosition().length; i++)
		{
			// The new unit position component is higher than the current one, thus we check with operator <=
			if (getDeltaNewPositions()[i] == 1)
			{
				if (getUnitPosition()[i] <= (getNextCubeCoordinates()[i] + (getWorld().getCubeLength() / 2.0)))
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
				if (getUnitPosition()[i] >= (getNextCubeCoordinates()[i] + (getWorld().getCubeLength() / 2.0)))
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
				if (getStrength() < MAX_ATTRIBUTE_VALUE)
					setStrength(getStrength() + 1);
				break;
			case 1:
				if (getAgility() < MAX_ATTRIBUTE_VALUE)
					setAgility(getAgility() + 1);
				break;
			case 2:
				if (getToughness() < MAX_ATTRIBUTE_VALUE)
					setToughness(getToughness() + 1);
				break;
			}
		}
		
		setDefaultWeight();
		if (!canHaveAsWeight(getWeight()))
			setWeight(getDefaultWeight());
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
	
	/**
	 * Let this Unit die.
	 * 
	 * @throws	ModelException
	 * 			A condition was violated or an error was thrown.
	 * 
	 * [TODO]	Effects, post conditions, etc.
	 */
	private void die() throws ModelException
	{
		if (isCarryingLog())
			dropLog(getUnitPosition());
		else if (isCarryingBoulder())
			dropBoulder(getUnitPosition());
		
		// Disable all behaviour:
		setIsMoving(false);
		setIsWorking(false);
		setIsAttacking(false);
		setIsDefending(false);
		setIsResting(false);
		setIsDefaultBehaviour(false);
		
		// Kill the Unit:
		setIsAlive(false);
		
		// Delete the Unit:
		getFaction().removeUnit(this);
		getWorld().removeUnit(this);
	}
	
	// ----------------------
	// |					|
	// |					|
	// |	   NAME			|
	// |					|
	// |					|
	// ----------------------
	
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
	 *         
	 * @return 	True if and only if the name is more than 2 characters long, starts with an uppercase letter and uses 
	 * 			only letters, spaces and quotes (both single and double).
	 * 			| if (name.length() >= MIN_NAMELENGTH && Character.isUpperCase(name.charAt(0)))
	 * 			| then for (int i = 0; i < name.length(); i++)
	 * 			| 	if (Character.isLetter(current) || current == '"' || current == '\'' || Character.isWhitespace(current))
	 * 			|	then result == true
	 * 			| 	else result == false
	 * 			|		break		
	 */
	public static boolean isValidName(String name)
	{
		boolean isValidName = false;
		
		if(name.length() >= MIN_NAMELENGTH && Character.isUpperCase(name.charAt(0)))
		{
			// Loop over the entire name:
			for(int i=0; i<name.length(); i++)
			{
				char current = name.charAt(i); // Select a character
				
				// Check is the current character is a letter, whitespace, double or single quote:
				if (Character.isLetter(current) || current == '"' || current == '\'' || Character.isWhitespace(current))
					isValidName = true;
				else
				{
					isValidName = false;
					break;
				}
			}
		}
		return isValidName;
	}
	
	/**
	 * Set the name of this Unit to the given name.
	 * 
	 * @param  	name
	 *         	The new name for this Unit.
	 *         
	 * @post   	The name of this new Unit is equal to
	 *         	the given name.
	 *       	| new.getName() == name
	 *       
	 * @throws 	ModelException
	 *         	The given name is not a valid name for any
	 *         	Unit.
	 *       	| ! isValidName(getName())
	 */
	@Raw
	public void setName(String name) throws ModelException 
	{
		if (! isValidName(name))
			throw new ModelException("The given name isn't valid for this Unit.");
		this.name = name;
	}
	
	/**
	 * Variable registering the name of this Unit.
	 */
	private String name;
	
	/**
	 * Variable registering the minimal length of a name.
	 */
	private final static int MIN_NAMELENGTH = 2;
	
	// ----------------------
	// |					|
	// |					|
	// |	  STRENGTH		|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the strength of this Unit.
	 */
	@Basic @Raw
	public int getStrength() 
	{
		return this.strength;
	}
	
	/**
	 * Check whether the given strength is a valid strength for any Unit.
	 *  
	 * @param  	strength
	 *         	The strength to check.
	 * @return 	True if and only if the given strength is between the minimun attribute value and the maximum attribute value (inclusive).
	 *       	| result == ((strength >= MIN_ATTRIBUTE_VALUE) && (strength <= MAX_ATTRIBUTE_VALUE))
	*/
	public static boolean isValidStrength(int strength)
	{
		return ((strength >= MIN_ATTRIBUTE_VALUE) && (strength <= MAX_ATTRIBUTE_VALUE));
	}
	
	/**
	 * Set the strength of this Unit to the given strength.
	 * 
	 * @param  	strength
	 *         	The new strength for this Unit.
	 *         
	 * @post   	If the given strength is a valid strength for any Unit, the strength of this new Unit is equal to the given strength.
	 *       	| if (isValidStrength(strength))
	 *       	|   then new.getStrength() == strength
	 */
	@Raw
	public void setStrength(int strength) 
	{
		if (isValidStrength(strength))
			this.strength = strength;
	}
	
	/**
	 * Variable registering the strength of this Unit.
	 */
	private int strength;
	
	/**
	 * Constant registering the default strength.
	 */
	private static final int DEFAULT_STRENGTH = 50;
	
	// ----------------------
	// |					|
	// |					|
	// |	  AGILITY		|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the agility of this Unit.
	 */
	@Basic @Raw
	public int getAgility() 
	{
		return this.agility;
	}
	
	/**
	 * Check whether the given agility is a valid agility for any Unit.
	 *  
	 * @param  	agility
	 *         	The agility to check.
	 * @return 	True if and only if the agility is between the minimum attribute value and the maximum attribute value(inclusive).
	 *       	| result == ((agility >= MIN_ATTRIBUTE_VALUE) && (agility <= MAX_ATTRIBUTE_VALUE))
	 */
	public static boolean isValidAgility(int agility) 
	{
		return ((agility >= MIN_ATTRIBUTE_VALUE) && (agility <= MAX_ATTRIBUTE_VALUE));
	}
	
	/**
	 * Set the agility of this Unit to the given agility.
	 * 
	 * @param  	agility
	 *         	The new agility for this Unit.
	 *         
	 * @post   	If the given agility is a valid agility for any Unit, the agility of this new Unit is equal to the given agility.
	 *       	| if (isValidAgility(agility))
	 *       	|   then new.getAgility() == agility
	 */
	@Raw
	public void setAgility(int agility) 
	{
		if (isValidAgility(agility))
			this.agility = agility;
	}
	
	/**
	 * Variable registering the agility of this Unit.
	 */
	private int agility;
	
	/**
	 * Constant registering the default agility.
	 */
	private static final int DEFAULT_AGILITY = 50;
	
	// ----------------------
	// |					|
	// |					|
	// |	  TOUGHNESS		|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the toughness of this Unit.
	 */
	@Basic @Raw
	public int getToughness() 
	{
		return this.toughness;
	}
	
	/**
	 * Check whether the given toughness is a valid toughness for any Unit.
	 *  
	 * @param  	toughness
	 *         	The toughness to check.
	 * @return 	True if and only if the toughness is between the minimum attribute value and the maximumt attribute value(inclusive).
	 *       	| result == ((toughness >= MIN_ATTRIBUTE_VALUE) && (toughness <= MAX_ATTRIBUTE_VALUE))
	*/
	public static boolean isValidToughness(int toughness) 
	{
		return ((toughness >= MIN_ATTRIBUTE_VALUE) && (toughness <= MAX_ATTRIBUTE_VALUE));
	}
	
	/**
	 * Set the toughness of this Unit to the given toughness.
	 * 
	 * @param  	toughness
	 *         	The new toughness for this Unit.
	 *         
	 * @post   	If the given toughness is a valid toughness for any Unit, the toughness of this new Unit is equal to the given toughness.
	 *       	| if (isValidToughness(toughness))
	 *       	|   then new.getToughness() == toughness
	 */
	@Raw
	public void setToughness(int toughness) 
	{
		if (isValidToughness(toughness))
			this.toughness = toughness;
	}
	
	/**
	 * Variable registering the toughness of this Unit.
	 */
	private int toughness;
	
	/**
	 * Constant registering the default toughness.
	 */
	private static final int DEFAULT_TOUGHNESS = 50;
	
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
	 * @return 	True if and only if the weight is between the minimum attribut value and the maximum attribute value
	 * 			and if it is higher or equal to the division of the sum of  this unit's strength and agility by 2.
	 *       	| result == ((weight >= MIN_ATTRIBUTE_VALUE) && (weight <= MAX_ATTRIBUTE_VALUE) 
	 *       	| 	&& (weight >= (getStrength() + getAgility()) / 2))
	*/
	public boolean canHaveAsWeight(int weight) 
	{
		return ((weight >= MIN_ATTRIBUTE_VALUE) && (weight <= MAX_ATTRIBUTE_VALUE) && (weight >= (getStrength() + getAgility()) / 2));
	}
	
	/**
	 * Set the weight of this Unit to the given weight or the default weight.
	 * 
	 * @param  	weight
	 *         	The new weight for this Unit.
	 *         
	 * @post   	If this Unit can have the given weight as its weight, the weight of this new Unit is equal to the given weight. 
	 * 			Else the weight of this new Unit is equal to the default weight.
	 *       	| if (canHaveAsWeight(weight))
	 *       	|   then new.getWeight() == weight
	 *       	| else 
	 *       	|	setDefaultWeight()
	 *       	| 	new.getWeight() == getDefaultWeight()
	 */
	@Raw
	public void setWeight(int weight) 
	{
		if (canHaveAsWeight(weight))
			this.weight = weight;
		else
		{
			setDefaultWeight();
			this.weight = getDefaultWeight();
		}
	}
	
	/**
	 * Variable registering the weight of this Unit.
	 */
	private int weight;

	/**
	 * Return the defaultWeight of this Unit.
	 */
	@Basic @Raw
	public int getDefaultWeight() 
	{
		return this.defaultWeight;
	}
	
	/**
	 * Set the default weight of this Unit equal to the sum of its strength and agility divided by 2.
	 * 
	 * @post	The default weight of this new Unit is equal to the sum of its strength and agility divided by 2.
	 * 			| new.getDefaultWeight == (getStrength() + getAgility()) / 2
	 */
	@Raw
	public void setDefaultWeight()
	{
		this.defaultWeight = (getStrength() + getAgility()) / 2;
	}
	
	/**
	 * Variable registering the default weight of this Unit.
	 */
	private int defaultWeight;
	
	/**
	 * Constant registering the maximum value for strength, weight, agility and toughness.
	 */
	private final static int MAX_ATTRIBUTE_VALUE = 200;
	
	/**
	 * Constant registering the minimum value for strength, weight, agility and toughness.
	 */
	private final static int MIN_ATTRIBUTE_VALUE = 1;
	
	// ----------------------
	// |					|
	// |					|
	// |	  HITPOINTS		|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the maxHitpoints of this Unit.
	 */
	@Basic @Raw @Immutable
	public int getMaxHitpoints() 
	{
		return this.maxHitpoints;
	}
	
	/**
	 * Check whether the given maxHitpoints is a valid maxHitpoints for any Unit.
	 *  
	 * @param  	maxHitpoints
	 *         	The maxHitpoints to check.
	 * @return 	True if and only if the maxHitpoints are positive and lower than the max value of integers.
	 *       	| result == ((maxHitpoints > 0) && (maxHitpoints < Integer.MAX_VALUE))
	*/
	@Raw
	public boolean isValidMaxHitpoints(int maxHitpoints) 
	{
		/*
		 * This checker isn't used in the program, it is just specified for the documentation of the maxHitpoints of any Unit.
		 * We could simply write the return line as the formal specification of the class invariant.
		 */
		return ((maxHitpoints > 0) && (maxHitpoints < Integer.MAX_VALUE));
	}
	
	/**
	 * Variable registering the maxHitpoints of this Unit.
	 */
	private final int maxHitpoints;
	
	/**
	 * Return the currentHitpoints of this Unit.
	 */
	@Basic @Raw
	public int getCurrentHitpoints() 
	{
		return this.currentHitpoints;
	}
	
	/**
	 * Check whether this Unit can have the given currentHitpoints as its currentHitpoints.
	 *  
	 * @param  	currentHitpoints
	 *         	The currentHitpoints to check.
	 * @return 	True if and only if the current hitpoints are positive and if they are lower or equal to this unit's
	 * 			max hitpoints.
	 *       	| result == ((currentHitpoints > 0) && (currentHitpoints <= getMaxHitpoints()))
	 *       
	 * @note	We don't include zero in this checker because if the currentHitpoints reach zero, the Unit must die. Thus there must be an
	 * 			assertionError in setCurrentHitpoints.
	*/
	public boolean canHaveAsCurrentHitpoints(int currentHitpoints) 
	{
		return ((currentHitpoints > 0) && (currentHitpoints <= getMaxHitpoints()));
	}
	
	/**
	 * Set the currentHitpoints of this Unit to the given currentHitpoints.
	 * 
	 * @param  	currentHitpoits
	 *        	The new currentHitpoints for this Unit.
	 *        
	 * @throws 	ModelException 
	 *          A condition was violated or an error was thrown.
	 *          
	 * @pre    	This Unit can have the given currentHitpoints as its CurrentHitpoints.
	 *       	| canHaveAsCurrentHitpoints(currentHitpoits)
	 *       
	 * @post   	The currentHitpoints of this Unit is equal to the given currentHitpoints.
	 *       	| new.getCurrentHitpoints() == currentHitpoits
	 * 
	 * @effect	This Unit will die if this Unit can't have the given currentHitpoints as its currentHitpoints, and if the given currentHitpoints
	 * 			is lower than or equal to 0.
	 * 			| catch (AssertionError e)
	 * 			| 		if (currentHitpoints <= 0)
	 * 			|			then this.currentHitpoints = 0
	 * 			|			     this.die()
	 */
	@Raw
	public void setCurrentHitpoints(int currentHitpoints) throws ModelException 
	{
		try
		{
			assert canHaveAsCurrentHitpoints(currentHitpoints);
			this.currentHitpoints = currentHitpoints;
		}
		catch (AssertionError e)
		{
			if (currentHitpoints <= 0)
			{
				this.currentHitpoints = 0;
				die();
			}
		}
	}
	
	/**
	 * Variable registering the currentHitpoints of this Unit.
	 */
	private int currentHitpoints;
	
	/**
	 * Return the temporary hitpoint of this Unit.
	 */
	@Basic @Raw
	private double getTempHitpoint() 
	{
		return this.tempHitpoint;
	}
	
	/**
	 * Check whether this Unit can have the given tempHitpoint as its tempHitpoint.
	 *  
	 * @param  	tempHitpoint
	 *         	The temporary hitpoint to check.
	 * @return 	True if and only if the tempHitpoint is between zero and the sum of one and this unit's toughness divided by 200.
	 *       	| result == ((tempHitpoint >= 0) && (tempHitpoint < 1 + (getToughness() / 200.0)))
	 *       
	 * @note	Could have potential errors. Please check this implementation if needed.
	*/
	private boolean canHaveAsTempHitpoint(double tempHitpoint) 
	{
		return ((tempHitpoint >= 0) && (tempHitpoint < (1 + (getToughness() / 200.0))));
	}
	
	/**
	 * Set the temporary hitpoint of this Unit to the given temporary hitpoint.
	 * 
	 * @param  	tempHitpoint
	 *         	The new temporary hitpoint for this Unit.
	 *         
	 * @post   	The temporary hitpoint of this new Unit is equal to the given temporary hitpoint.
	 *       	| new.getTempHitpoint() == tempHitpoint
	 *       
	 * @throws 	ModelException
	 *         	This Unit cannot have the given tempHitpoint as its tempHitpoint.
	 *       	| ! canHaveAsTempHitpoint(getTempHitpoint())
	 */
	@Raw
	private void setTempHitpoint(double tempHitpoint) throws ModelException 
	{
		if (! canHaveAsTempHitpoint(tempHitpoint))
			throw new ModelException("The temporary hitpoint counter is too large.");
		this.tempHitpoint = tempHitpoint;
	}
	
	/**
	 * Variable registering the temporary hitpoint of this Unit.
	 */
	private double tempHitpoint;
	
	// ----------------------
	// |					|
	// |					|
	// |    STAMINAPOINTS	|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the maxStaminaPoints of this Unit.
	 */
	@Basic @Raw @Immutable
	public int getMaxStaminapoints() 
	{
		return this.maxStaminaPoints;
	}
	
	/**
	 * Check whether the given maxStaminapoints is a valid maxStaminapoints for any Unit.
	 * 
	 * @param 	maxStaminapoints
	 *         	The maxStaminaPoints to check.
	 * @return 	True if and only if the maxHitpoints are positive and lower than the max value of integers.
	 *       	| result == ((maxStaminapoints > 0) && (maxStaminapoints < Integer.MAX_VALUE))
	*/
	@Raw
	public static boolean isValidMaxStaminaPoints(int maxStaminapoints) 
	{
		/*
		 * This checker isn't used in the program, it is just specified for the documentation of the maxHitpoints of any Unit.
		 * We could simply write the return line as the formal specification of the class invariant.
		 */
		return ((maxStaminapoints > 0) && (maxStaminapoints < Integer.MAX_VALUE));
	}
	
	/**
	 * Variable registering the maxStaminaPoints of this Unit.
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
	 * Check whether this unit can have the given currentStaminapoints as its currentStaminapoints.
	 * 
	 * @param  	currentStaminapoints
	 *         	The currentStaminapoints to check.
	 * @return 	True if and only if the current stamina points is zero or positive and if it is lower or equal 
	 * 			to this unit's max staminapoints. 
	 *       	| result == ((currentStaminapoints >= 0) && (currentStaminapoints <= getMaxHitpoints()))
	 *       
	 * @note	As opposed to currentHitpoints, we do implement equal to zero in this checker, because the currentStaminapoints should
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
	 *         
	 * @pre    	The given currentStaminapoints must be a valid currentStaminapoints for any Unit.
	 *       	| canHaveAsCurrentStaminapoints(currentStaminapoints)
	 *       
	 * @post   	The currentStaminapoints of this Unit is equal to the given currentStaminapoints.
	 *       	| new.getCurrentStaminapoints() == currentStaminapoints
	 */
	@Raw
	public void setCurrentStaminapoints(int currentStaminapoints) 
	{
		assert canHaveAsCurrentStaminapoints(currentStaminapoints);
		this.currentStaminapoints = currentStaminapoints;
	}
	
	/**
	 * Variable registering the currentStaminapoints of this Unit.
	 */
	private int currentStaminapoints;

	/**
	 * Return the temporary staminapoint of this Unit.
	 */
	@Basic @Raw
	private double getTempStaminapoint() 
	{
		return this.tempStaminapoint;
	}
	
	/**
	 * Check whether the given temporary staminapoint is a valid temporary staminapoint for any Unit.
	 *  
	 * @param  	temporary staminapoint
	 *         	The temporary staminapoint to check.
	 * @return 	True if and only if the given tempStaminapoint is between zero and the sum of one and this Unit's toughness divided by 100.
	 *       	| result == ((tempStaminapoint >= 0) && (tempStaminapoint <= 1 + (getToughness() / 100)))
	 *       
	 * @note	Could have potential errors. Please check this implementation if needed.
	 */
	private boolean canHaveAsTempStaminapoint(double tempStaminapoint) 
	{
		return ((tempStaminapoint >= 0) && (tempStaminapoint <= (1 + (getToughness() / 100.0))));
	}
	
	/**
	 * Set the temporary staminapoint of this Unit to the given temporary staminapoint.
	 * 
	 * @param  	tempStaminapoint
	 *         	The new temporary staminapoint for this Unit.
	 *         
	 * @post   	The temporary staminapoint of this new Unit is equal to the given temporary staminapoint.
	 *       	| new.getTempStaminapoint() == tempStaminapoint
	 *       
	 * @throws 	ModelException
	 *         	The given temporary staminapoint is not a valid temporary staminapoint for any Unit.
	 *       	| ! canHaveAsTempStaminapoint(getTempStaminapoint())
	 */
	@Raw
	private void setTempStaminapoint(double tempStaminapoint) throws ModelException 
	{
		if (! canHaveAsTempStaminapoint(tempStaminapoint))
			throw new ModelException("The temporary staminapoint is too large. " + tempStaminapoint);
		this.tempStaminapoint = tempStaminapoint;
	}
	
	/**
	 * Variable registering the temporary staminapoint of this Unit.
	 */
	private double tempStaminapoint;
	
	// ----------------------
	// |					|
	// |					|
	// |    ORIENTATION		|
	// |					|
	// |					|
	// ----------------------
	
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
	 *       	| result == ((orientation >= -(Math.PI)) && (orientation <= Math.PI))
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
	 *         
	 * @post   	If the given orientation is a valid orientation for any Unit, the orientation of this new Unit is equal to the given orientation.
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
	 * Variable registering the orientation of this Unit.
	 */
	private double orientation;
	
	/**
	 * Constant registering the default orientation.
	 */
	private static final double DEFAULT_ORIENTATION = (Math.PI/2);

	// ----------------------
	// |					|
	// |					|
	// |   CUBE COORDINATES	|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the cubeCoordinates of this Unit.
	 */
	@Basic @Raw
	public int[] getCubeCoordinates() 
	{
		return this.cubeCoordinates;
	}
	
	/**
	 * Check whether this Unit can have the given cubeCoordinates as its cubeCoordinates.
	 * 
	 * @param  	cubeCoordinates
	 *         	The cubeCoordinates to check.
	 * @return	True if and only if the given coordinates are within the boundaries of this world and if the given coordinates 
	 * 			is a passable cube.
	 *       	| result == getWorld().isBetweenBoundaries(cubeCoordinates[0], cubeCoordinates[1], cubeCoordinates[2])
	 *			| 	&& getWorld().isPassableCube(cubeCoordinates[0], cubeCoordinates[1], cubeCoordinates[2])
	 */
	public boolean canHaveAsCubeCoordinates(int[] cubeCoordinates) 
	{
		return  getWorld().isBetweenBoundaries(cubeCoordinates[0], cubeCoordinates[1], cubeCoordinates[2])
				&& getWorld().isPassableCube(cubeCoordinates[0], cubeCoordinates[1], cubeCoordinates[2]);
	}
	
	/**
	 * Set the cubeCoordinates of this Unit to the given cubeCoordinates.
	 * 
	 * @param  	cubeCoordinates
	 *         	The new cubeCoordinates for this Unit.
	 *         
	 * @post   	The cubeCoordinates of this new Unit is equal to the given cubeCoordinates.
	 *       	| new.getCubeCoordinates() == cubeCoordinates
	 *       
	 * @throws 	ModelException
	 *         	This Unit cannot have the given cubeCoordinates as its cubeCoordinates.
	 *       	| ! canHaveAsCubeCoordinates(getCubeCoordinates())
	 *       
	 * @effect	The isFalling indicator of this Unit is activated if the current cubeCoordinates doesn't have a solid neighbouring cube.
	 * 			| if (!getWorld().hasSolidNeighbouringCube(cubeCoordinates[0], cubeCoordinates[1], cubeCoordinates[2]))
	 * 			| 	then this.setIsFalling(true)
	 */
	@Raw
	public void setCubeCoordinates(int[] cubeCoordinates) throws ModelException 
	{
		if (! canHaveAsCubeCoordinates(cubeCoordinates))
			throw new ModelException("The given cube coordinates aren't valid for this Unit.");
		this.cubeCoordinates = Arrays.copyOf(cubeCoordinates, 3);
		
		// Check if this Unit must fall:
		if (mustUnitFall(getCubeCoordinates()))
			setIsFalling(true);
		else if (isFalling())
			setIsFalling(false);	
	}
	
	/**
	 * Variable registering the cubeCoordinates of this Unit.
	 */
	private int[] cubeCoordinates = new int[3];
	
	// ----------------------
	// |					|
	// |					|
	// |     IS FALLING		|
	// |					|
	// |					|
	// ----------------------

	/**
	 * Return the isFalling indicator of this Unit.
	 */
	@Basic @Raw
	public boolean isFalling() 
	{
		return this.isFalling;
	}
	
	/**
	 * Check whether this Unit can have the given isFalling indicator as its isFalling indicator.
	 *  
	 * @param  	isFalling
	 *         	The isFalling indicator to check.
	 * @return 	True if and only if the given isFalling indicator is true and the current cubeCoordinates doesn't have a solid neighbouring
	 * 			cube or if the given isFalling indicator is false and the current cubecoordinates does have a solid neighbouring cube.
	 *       	| result == return (isFalling && 
	 *       	|			!getWorld().hasSolidNeighbouringCube(getCubeCoordinates()[0], getCubeCoordinates()[1], getCubeCoordinates()[2]))
	 *			|	|| (!isFalling && 
	 *			|		getWorld().hasSolidNeighbouringCube(getCubeCoordinates()[0], getCubeCoordinates()[1], getCubeCoordinates()[2]));
	 */
	public boolean canHaveAsIsFalling(boolean isFalling) 
	{
		return (isFalling && !getWorld().hasSolidNeighbouringCube(getCubeCoordinates()[0], getCubeCoordinates()[1], getCubeCoordinates()[2]))
				|| (!isFalling &&
						getWorld().hasSolidNeighbouringCube(getCubeCoordinates()[0], getCubeCoordinates()[1], getCubeCoordinates()[2]));
	}
	
	/**
	 * Set the isFalling indicator of this Unit to the given isFalling indicator.
	 * 
	 * @param  	isFalling
	 *         	The new isFalling indicator for this Unit.
	 *         
	 * @post   	The isFalling indicator of this new Unit is equal to the given isFalling indicator.
	 *       	| new.getisFalling() == isFalling
	 *       
	 * @throws 	ModelException
	 *         	This Unit cannot have the given isFalling indicator as its isFalling indicator.
	 *       	| ! canHaveAsIsFalling(getisFalling())
	 */
	@Raw
	public void setIsFalling(boolean isFalling) throws ModelException 
	{
		if (! canHaveAsIsFalling(isFalling))
			throw new ModelException("This Unit cannot have the given isFalling indicator as its isFalling indicator.");
		this.isFalling = isFalling;
	}
	
	/**
	 * Check whether this Unit must fall.
	 * 
	 * @param 	cubeCoordinates
	 * 			The given coordinates to check.
	 * @return	True if and only if the given cube doesn't have a solid neighbouring cube and if the Unit isn't already falling.
	 */
	private boolean mustUnitFall(int[] cubeCoordinates)
	{
		return !getWorld().hasSolidNeighbouringCube(cubeCoordinates[0], cubeCoordinates[1], cubeCoordinates[2]) && !isFalling();
	}
	
	/**
	 * Variable registering the isFalling indicator of this Unit.
	 */
	private boolean isFalling;
	
	// ----------------------
	// |					|
	// |		NEXT		|
	// |  CUBE COORDINATES	|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the nextCubeCoordinates of this Unit.
	 */
	@Basic @Raw
	public int[] getNextCubeCoordinates() 
	{
		return this.nextCubeCoordinates;
	}
	
	/**
	 * Check whether this Unit can have the given nextCubeCoordinates as its nextCubeCoordinates.
	 *  
	 * @param  	nextCubeCoordinates
	 *         	The nextCubeCoordinates to check.
	 * @return 	True if and only if this Unit can have the nextCubeCoordinates as cubeCoordinates 
	 * 			and if the given nextCubeCoordinates are different from the current cube coordinates.
	 *       	| result == (canHaveAsCubeCoordinates(nextCubeCoordinates) && !Arrays.equals(getCubeCoordinates(), nextCubeCoordinates))
	*/
	public boolean canHaveAsNextCubeCoordinates(int[] nextCubeCoordinates) 
	{
		return (canHaveAsCubeCoordinates(nextCubeCoordinates) && !Arrays.equals(getCubeCoordinates(), nextCubeCoordinates));
	}
	
	/**
	 * Set the nextCubeCoordinates of this Unit to the given nextCubeCoordinates.
	 * 
	 * @param  	nextCubeCoordinates
	 *         	The new nextCubeCoordinates for this Unit.
	 *         
	 * @post   	The nextCubeCoordinates of this new Unit is equal to the given nextCubeCoordinates.
	 *       	| new.getNextCubeCoordinates() == nextCubeCoordinates
	 *       
	 * @throws 	ModelException
	 *         	This Unit cannot have the given nextCubeCoordinates as its nextCubeCoordinates.
	 *       	| ! canHaveAsNextCubeCoordinates(getNextCubeCoordinates())
	 *       	There isn't a solid cube neighbouring the destination cube and the Unit isn't currently falling.
	 *       	| !getWorld().hasSolidNeighbouringCube(nextCubeCoordinates[0], nextCubeCoordinates[1], nextCubeCoordinates[2])
	 *      
	 */
	@Raw
	public void setNextCubeCoordinates(int[] nextCubeCoordinates) throws ModelException {
		if (! canHaveAsNextCubeCoordinates(nextCubeCoordinates))
			throw new ModelException("The nextCubeCoordinates are invalid for this Unit.");
		
		// Check whether the Unit is falling, if so it can have the given nextCubeCoordinates as its nextCubeCoordinates:
		if (isFalling())
			this.nextCubeCoordinates = nextCubeCoordinates;
		
		// Else: check whether the nextCubeCoordinates has a solid neigbouring cube.
		else
		{
			if (getWorld().hasSolidNeighbouringCube(nextCubeCoordinates[0], nextCubeCoordinates[1], nextCubeCoordinates[2]))
				this.nextCubeCoordinates = nextCubeCoordinates;
			else
				throw new ModelException("There isn't a solid cube neighbouring the destination cube.");
		}
	}
		
	/**
	 * Variable registering the nextCubeCoordinates of this Unit.
	 */
	private int[] nextCubeCoordinates = new int[3];
	
	// ----------------------
	// |					|
	// |					|
	// |    UNIT POSITION	|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the unitPosition of this Unit.
	 */
	@Basic @Raw
	public double[] getUnitPosition() 
	{
		return this.unitPosition;
	}
	
	/**
	 * Set the unitPosition of this Unit to the given unitPosition.
	 * 
	 * @param  	unitPosition
	 *         	The new unitPosition for this Unit.
	 *         
	 * @post   	The unitPosition of this new Unit is equal to the given coordinates added with half a cube length.
	 *       	| new.getUnitPosition() == coordinates + (getWorld().getCubeLength() / 2.0)
	 *       
	 * @throws 	ModelException
	 *         	This Unit cannot have the given coordinates as its cubeCoordinates.
	 *       	| ! canHaveAsCubeCoordinates(coordinates)
	 * 
	 * @note	We could specify that each component of the Unit's position consists out of the sum between that coordinate component
	 * 			and half a cube length, but current notation is also clear. 
	 */
	@Raw
	public void setUnitPosition(int[] coordinates) throws ModelException 
	{
		if (! canHaveAsCubeCoordinates(coordinates))
			throw new ModelException("The given cube coordinates to calculate the Unit's position is not valid.");
		
		for(int i=0; i<coordinates.length; i++)
			this.unitPosition[i] = coordinates[i] + (getWorld().getCubeLength() / 2.0);
	}
	
	/**
	 * Check whether this Unit can have the given position value as its position value.
	 * 
	 * @param 	value
	 * 			The value to be checked.
	 * @return	True if and only if the given value is between the lower boundary of this World
	 * 			and either the amount of x cubes, y cubes or z cubes of this World depending on the given index of the position.
	 * 			| if (index == 0)
	 * 			| 	then result == ((value >= getWorld().getLowerBoundary()) && (value < getWorld().getNbCubesX()))
	 * 			| else if (index == 1)
	 * 			|	then result == ((value >= getWorld().getLowerBoundary()) && (value < getWorld().getNbCubesY()))
	 * 			| else if (index == 2)
	 * 			|	then result == ((value >= getWorld().getLowerBoundary()) && (value < getWorld().getNbCubesZ()))
	 */
	public boolean canHaveAsUnitPositionValue(int index, double value)
	{
		/* 
		 * We could have used isValidCubeCoordinates but this would interfere the function of setCubeCoordinates.
		 * If we used a for loop to set the values in setCubeCoordinates, we would have to check if each component was valid,
		 * using this checker. This seems feasible but according to the programming rules of this course this would allow the setter
		 * to change some properties without changing it entirely. Example: the x component is valid, thus it is changed, BUT the y component
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
	 * Set a certain element of the unitPosition of this Unit to the given value.
	 * 
	 * @param 	index
	 * 			The index of the element to be changed.
	 * @param 	value
	 * 			The new value for this element.
	 * 
	 * @throws 	ModelException
	 * 			This Unit cannot have the given value as its value for one of its position components.
	 * 			| ! canHaveAsUnitPositionValue(value)
	 * 
	 * @post	The value of the element of the unitPosition with the given index is equal to the given value.
	 * 			| new.getUnitPosition()[index] == value
	 */
	public void setUnitPosition(int index, double value) throws ModelException 
	{
		if (!canHaveAsUnitPositionValue(index, value))
			throw new ModelException("Value is invalid for this Unit position. " + value);
		this.unitPosition[index] = value;
	}
	
	/**
	 * Variable registering the unitPosition of this Unit.
	 */
	private double[] unitPosition = new double[3];
	
	// ----------------------
	// |					|
	// |		DELTA		|
	// |    NEW POSITIONS	|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the deltaNewPositions of this Unit.
	 */
	@Basic @Raw
	private int[] getDeltaNewPositions() 
	{
		return this.deltaNewPositions;
	}
	
	/**
	 * Check whether the given deltaNewPositions is a valid deltaNewPositions for any Unit.
	 *  
	 * @param  	deltaNewPositions
	 *         	The deltaNewPositions to check.
	 * @return 	True if and only if every delta coordinate in the array is either -1, 0 or 1.
	 *       	| for (int i = 0; i < deltaNewPositions.length; i++)
	 *       	| 	if (!(deltaNewPositions[i] == 0 || deltaNewPositions[i] == 1 || deltaNewPositions[i] == -1))
	 *       	|	then result == false
	 *       	| result == true
	 */
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
	 * Set the deltaNewPositions of this Unit to the given deltaNewPositions.
	 * 
	 * @param  	deltaNewPositions
	 *         	The new deltaNewPositions for this Unit.
	 *         
	 * @post   	The deltaNewPositions of this new Unit is equal to the given deltaNewPositions.
	 *       	| new.getDeltaNewPositions() == deltaNewPositions
	 *       
	 * @throws 	ModelException
	 *         	The given deltaNewPositions is not a valid deltaNewPositions for any Unit.
	 *       	| ! isValidDeltaNewPositions(getDeltaNewPositions())
	 */
	@Raw
	private void setDeltaNewPositions(int[] deltaNewPositions) throws ModelException {
		if (! isValidDeltaNewPositions(deltaNewPositions))
			throw new ModelException("The given delta new positions array is invalid for this Unit.");
		this.deltaNewPositions = Arrays.copyOf(deltaNewPositions, 3);
	}
	
	/**
	 * Variable registering the deltaNewPositions of this Unit.
	 */
	private int[] deltaNewPositions = new int[3];
	
	// ----------------------
	// |					|
	// |					|
	// |     BASE SPEED		|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the baseSpeed of this Unit.
	 */
	@Basic @Raw
	public double getBaseSpeed() 
	{
		return this.baseSpeed;
	}
	
	/**
	 * Set the baseSpeed of this Unit to the given baseSpeed.
	 * 
	 * @param  	baseSpeed
	 *         	The new baseSpeed for this Unit.
	 * @post   	The baseSpeed of this new Unit is equal to the given baseSpeed.
	 *       	| new.getBaseSpeed() == baseSpeed
	 */
	@Raw
	public void setBaseSpeed(double baseSpeed) 
	{
		/*
		 * A checker isn't needed here, we assume that this value is always somewhere between max and min integer value.
		 * There is no further restriction on this value.
		 */
		this.baseSpeed = baseSpeed;
	}
	
	/**
	 * Variable registering the baseSpeed of this Unit.
	 */
	private double baseSpeed;
	
	// ----------------------
	// |					|
	// |					|
	// |    WALKING SPEED	|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the walking speed of this Unit.
	 */
	@Basic @Raw
	public double getWalkingSpeed() 
	{
		return this.walkingSpeed;
	}
	
	/**
	 * Check whether this Unit can have the given walkingSpeed as its walkingSpeed.
	 *  
	 * @param  	walking speed
	 *         	The walking speed to check.
	 * @return 	True if and only if the given walking speed is equal to either the base speed of this Unit itself,
	 * 			1.2 times the base speed of this Unit or half the base speed of this Unit.
	 *       	| result == ((walkingSpeed == 1.2*getBaseSpeed()) || (walkingSpeed == getBaseSpeed()) || walkingSpeed == getBaseSpeed() / 2.0)
	*/
	public boolean canHaveAsWalkingSpeed(double walkingSpeed) 
	{
		return ((walkingSpeed == 1.2*getBaseSpeed()) || (walkingSpeed == getBaseSpeed()) || walkingSpeed == getBaseSpeed() / 2.0);
	}
	
	/**
	 * Set the walking speed of this Unit to the given walking speed.
	 * 
	 * @param  	walkingSpeed
	 *         	The new walking speed for this Unit.
	 *         
	 * @post   	The walking speed of this new Unit is equal to the given walking speed.
	 *       	| new.getWalkingSpeed() == walkingSpeed
	 *       
	 * @throws 	ModelException
	 *         	This Unit cannot have the given walkingSpeed as its walkingSpeed.
	 *       	| ! canHaveAsWalkingSpeed(getWalkingSpeed())
	 */
	@Raw
	public void setWalkingSpeed(double walkingSpeed) throws ModelException 
	{
		if (! canHaveAsWalkingSpeed(walkingSpeed))
			throw new ModelException("The given walking speed is invalid for this Unit.");
		this.walkingSpeed = walkingSpeed;
	}
	
	/**
	 * Variable registering the walking speed of this Unit.
	 */
	private double walkingSpeed;
	
	// ----------------------
	// |					|
	// |					|
	// |    SPRINT SPEED	|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the sprinting speed of this Unit.
	 */
	@Basic @Raw
	public double getSprintSpeed() 
	{
		return this.sprintSpeed;
	}
	
	/**
	 * Check whether this Unit can have the given sprintSpeed as its sprintSpeed.
	 *  
	 * @param  	sprinting speed
	 *         	The sprinting speed to check.
	 * @return	True if and only if the given sprinting speed is 2 times this Unit's walking speed.
	 *       	| result == (sprintSpeed == (2 * getWalkingSpeed()))
	*/
	public boolean canHaveAsSprintSpeed(double sprintSpeed) 
	{
		return sprintSpeed == (2 * getWalkingSpeed());
	}
	
	/**
	 * Set the sprinting speed of this Unit to the given sprinting speed.
	 * 
	 * @param  	sprintSpeed
	 *         	The new sprinting speed for this Unit.
	 *         
	 * @post   	The sprinting speed of this new Unit is equal to the given sprinting speed.
	 *       	| new.getSprintSpeed() == sprintSpeed
	 *       
	 * @throws 	ModelException
	 *         	This Unit cannot have the given sprintSpeed as its sprintSpeed.
	 *       	| ! canHaveAsSprintSpeed(getSprintSpeed())
	 */
	@Raw
	public void setSprintSpeed(double sprintSpeed) throws ModelException 
	{
		if (! canHaveAsSprintSpeed(sprintSpeed))
			throw new ModelException("The given sprint speed is invalid for this Unit.");
		this.sprintSpeed = sprintSpeed;
	}
	
	/**
	 * Variable registering the sprinting speed of this Unit.
	 */
	private double sprintSpeed;
	
	// ----------------------
	// |					|
	// |					|
	// |      VELOCITY		|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the velocity of this Unit.
	 */
	@Basic @Raw
	public double[] getVelocity() 
	{
		return this.velocity;
	}
	
	/**
	 * Set the velocity of this Unit to the given velocity.
	 * 
	 * @param  	coordinates
	 *         	The coordinates to calculate the velocity.
	 *         
	 * @post   	The velocity of this new Unit is equal to the multiplication of the sprint speed of this Unit and the difference between
	 * 			the given coordinate components and the current coordinate components divided by the square root of the sum of 
	 * 			the power of these differences or it is equal to the multiplication of the walking speed of this Unit 
	 * 			and the difference between the given coordinate components and the current coordinate components divided by the square root
	 * 			of the sum of the power of these differences.
	 * 			| double[] components = {coordinates[0]-getCubeCoordinates()[0], coordinates[1]-getCubeCoordinates()[1],
	 *			|			coordinates[2]-getCubeCoordinates()[2]};
	 *			| double d = Math.sqrt(Math.pow(components[0], 2) + Math.pow(components[1], 2) + Math.pow(components[2], 2));
	 *			| for(int i=0; i<getVelocity().length; i++)
	 *			| if (isSprinting())
	 *			|	then this.velocity[i] = getSprintSpeed()*(components[i]/d);
	 *			| else
	 *			|  	this.velocity[i] = getWalkingSpeed()*(components[i]/d);
	 *       
	 * @throws 	ModelException
	 *         	This Unit cannot have the given coordinates as its cubeCoordinates.
	 *      	| ! canHaveAsCubeCoordinates(coordinates)
	 *      
	 * @note	[FIXME] Not entirly sure of the formal specification of the post condition.
	 */
	@Raw
	public void setVelocity(int[] coordinates) throws ModelException 
	{
		if (! canHaveAsCubeCoordinates(coordinates))
			throw new ModelException("The given cube coordinates aren't valid for this Unit.");
		
		double[] components = {coordinates[0]-getCubeCoordinates()[0], coordinates[1]-getCubeCoordinates()[1],
				coordinates[2]-getCubeCoordinates()[2]};
		double d = Math.sqrt(Math.pow(components[0], 2) + Math.pow(components[1], 2) + Math.pow(components[2], 2));
		
		for(int i=0; i<getVelocity().length; i++)
		{
			if (isSprinting())
			{
				this.velocity[i] = getSprintSpeed()*(components[i]/d);
			}
			else
			{
				this.velocity[i] = getWalkingSpeed()*(components[i]/d);
			}
			
		}
	}
	
	/**
	 * Set the velocity of this Unit to the given x, y and z velocity component.
	 * 
	 * @param 	x
	 * 			The given x component of the new velocity.
	 * @param 	y
	 * 			The given y component of the new velocity.
	 * @param 	z
	 * 			The given z component of the new velocity.
	 * 
	 * @post	Each velocity component of this new Unit's velocity is set to the given velocity component.
	 * 			| new.getVelocity() == {x, y, z}	
	 * 
	 * @note	There is no checker to check these values. This method is only called upon if the Unit is falling. This will happen with
	 * 			a constant velocity of (0, 0, 3).
	 */
	public void setVelocity(double x, double y, double z)
	{
		this.velocity[0] = x;
		this.velocity[1] = y;
		this.velocity[2] = z;
	}
	
	/**
	 * Variable registering the velocity of this Unit.
	 */
	private double[] velocity = new double[3];

	// ----------------------
	// |					|
	// |					|
	// |    CURRENT SPEED	|
	// |					|
	// |					|
	// ----------------------
	
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
	 * @param  	current speed
	 *         	The current speed to check.
	 * @return 	True if and only if the current speed is either the Unit's walking speed or the Unit's sprinting speed.
	 *       	| result == ((currentSpeed == getWalkingSpeed()) || (currentSpeed == getSprintSpeed()))
	*/
	public boolean canHaveAsCurrentSpeed(double currentSpeed)
	{
		return ((currentSpeed == getWalkingSpeed()) || (currentSpeed == getSprintSpeed()));
	}
	
	/**
	 * Set the current speed of this Unit to the given current speed.
	 * 
	 * @param  	currentSpeed
	 *         	The new current speed for this Unit.
	 *         
	 * @post   	The current speed of this new Unit is equal to the given current speed.
	 *       	| new.getCurrentSpeed() == currentSpeed
	 *       
	 * @throws 	ModelException
	 *         	This Unit cannot have the given currentSpeed as its currentSpeed.
	 *       	| ! canHaveAsCurrentSpeed(getCurrentSpeed())
	 */
	@Raw
	public void setCurrentSpeed(double currentSpeed) throws ModelException 
	{
		if (! canHaveAsCurrentSpeed(currentSpeed))
			throw new ModelException("The given currentSpeed is invalid for this Unit.");
		this.currentSpeed = currentSpeed;
	}
	
	/**
	 * Variable registering the current speed of this Unit.
	 */
	private double currentSpeed;
	
	// ----------------------
	// |					|
	// |					|
	// |    IS SPRINTING	|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the sprinting indicator of this Unit.
	 */
	@Basic @Raw
	public boolean isSprinting() 
	{
		return this.isSprinting;
	}
	
	/**
	 * Check whether the given sprinting indicator is a valid sprinting indicator for any Unit.
	 *  
	 * @param  	isSprinting
	 *         	The sprinting indicator to check.
	 * @return 	True if and only if the given isSprinting indicator is true and the isMoving indicator of this Unit is true or if the given 
	 * 			isSprinting indicator is false.
	 *       	| result == ((isSprinting && isMoving()) || !isSprinting)
	*/
	public boolean canHaveAsIsSprinting(boolean isSprinting) 
	{
		return (isSprinting && isMoving()) || !isSprinting;
	}
	
	/**
	 * Set the sprinting indicator of this Unit to the given sprinting indicator.
	 * 
	 * @param  	isSprinting
	 *         	The new isSprinting indicator for this Unit.
	 *         
	 * @post   	The isSprinting indicator of this new Unit is equal to the given isSprinting indicator.
	 *       	| new.getIsSprinting() == isSprinting
	 *       
	 * @throws 	ModelException
	 *         	This Unit cannot have the given isSprinting indicator as its isSprinting indicator.
	 *       	| ! canHaveAsIsSprinting(getIsSprinting())
	 */
	@Raw
	public void setIsSprinting(boolean isSprinting) throws ModelException 
	{
		if (! canHaveAsIsSprinting(isSprinting))
			throw new ModelException("The given value isSprinting is invalid for this Unit.");
		this.isSprinting = isSprinting;
	}
	
	/**
	 * Start sprinting with this Unit.
	 * 
	 * @throws 	ModelException
	 * 			A condition was violated or an error was thrown.
	 * 
	 * @effect	The isSprinting of this Unit is enabled, if its currentStaminapoints is higher than 0 and the Unit is currently moving
	 * 			and if the Unit isn't falling.
	 * 			| if (getCurrentStaminapoints() > 0 && isMoving() && !isFalling())
	 * 			| 	then this.setIsSprinting(true)
	 * 
	 * @effect	The currentSpeed of this Unit is set to its sprintSpeed, if its currentStaminapoints is higher than 0 and the Unit is
	 * 			currently moving and if the Unit isn't falling.
	 * 			| if (getCurrentStaminapoints() > 0 && isMoving() && !isFalling())
	 * 			| 	then this.setCurrentSpeed(getSprintSpeed())
	 * 
	 * @effect	The velocity of this Unit is set to the calculated velocity using this Unit's cube coordinates, if its currentStaminapoints
	 * 			is higher than 0, if the Unit is currently moving, if the Unit isn't falling and if the isDefaultBehaviourEnabled indicator
	 * 			is deactivated.
	 * 			| if (getCurrentStaminapoints() > 0 && isMoving() && !isFalling() && !isDefaultBehaviourEnabled())
	 * 			| 	then this.setVelocity(getNextCubeCoordinates())
	 */
	public void startSprinting() throws ModelException
	{
		if (getCurrentStaminapoints() > 0 && isMoving() && !isFalling())
		{
			setIsSprinting(true);
			setCurrentSpeed(getSprintSpeed());
			
			// Only set the velocity if this method isn't called by the default behaviour. If the default behaviour decides to sprint, the correct 
			// velocity will be calculated in the MoveToAdjacent method.
			if (!isDefaultBehaviourEnabled())
				setVelocity(getNextCubeCoordinates());
		}
	}
	
	/**
	 * Stop sprinting with this Unit.
	 * 
	 * @throws 	ModelException
	 * 			A condition was violated or an error was thrown.
	 * 
	 * @effect	The isSprinting indicator of this Unit is disabled.
	 * 			| this.setIsSprinting(false)
	 * 
	 * @effect	The current speed of this Unit is set to its walking speed.
	 * 			| this.setCurrentSpeed(getWalkingSpeed())
	 */
	public void stopSprinting() throws ModelException
	{
		setIsSprinting(false);
		setCurrentSpeed(getWalkingSpeed());
	}
	
	/**
	 * Variable registering the sprinting indicator of this Unit.
	 */
	private boolean isSprinting;

	// ----------------------
	// |					|
	// |					|
	// | SPRINTING DURATION	|
	// |					|
	// |					|
	// ----------------------
	
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
	 * @throws 	ModelException
	 *         	The given sprinting duration is not a valid sprinting duration for any Unit.
	 *       	| ! isValidSprintingDuration(getSprintingDuration())
	 */
	@Raw
	private void setSprintingDuration(double sprintingDuration) throws ModelException 
	{
		if (! isValidSprintingDuration(sprintingDuration))
			throw new ModelException("The sprinting duration is invalid for any Unit.");
		this.sprintingDuration = sprintingDuration;
	}
	
	/**
	 * Variable registering the sprinting duration of this Unit.
	 */
	private double sprintingDuration;
	
	// ----------------------
	// |					|
	// |					|
	// |      IS MOVING		|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the moving indicator of this Unit.
	 */
	@Basic @Raw
	public boolean isMoving() 
	{
		return this.isMoving;
	}
	
	/**
	 * Check whether thisUnit can have the given isMoving indicator as its isMoving indicator.
	 *  
	 * @param  	isMoving
	 *         	The moving indicator to check.
	 * @return 	True if and only if the given isMoving indicator is true and the Unit isn't currently attacking or if the given 
	 * 			isMoving indicator is false.
	 *       	| result == ((isMoving && !isAttacking()) || !isMoving)
	 */
	public boolean canHaveAsIsMoving(boolean isMoving) 
	{
		return ((isMoving && !isAttacking()) || !isMoving);
	}
	
	/**
	 * Set the isMoving indicator of this Unit to the given isMoving indicator.
	 * 
	 * @param  	isMoving
	 *         	The new isMoving indicator for this Unit.
	 *         
	 * @post   	The moving indicator of this new Unit is equal to the given moving indicator.
	 *       	| new.getIsMoving() == isMoving
	 *       
	 * @throws 	ModelException
	 *         	This Unit cannot have the given isMoving indicator as its isMoving indicator.
	 *       	| ! canHaveAsIsMoving(getIsMoving())
	 */
	@Raw
	public void setIsMoving(boolean isMoving) throws ModelException 
	{
		if (! canHaveAsIsMoving(isMoving))
			throw new ModelException("The given value isMoving is invalid for this Unit.");
		this.isMoving = isMoving;
	}
	
	/**
	 * Variable registering the moving indicator of this Unit.
	 */
	private boolean isMoving;

	// ----------------------
	// |					|
	// |					|
	// |     WAS MOVING		|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the wasMoving indicator of this Unit.
	 */
	@Basic @Raw
	private boolean wasMoving() 
	{
		return this.wasMoving;
	}
	
	/**
	 * Check whether this Unit can have the given wasMoving indicator as its wasMoving indicator.
	 *  
	 * @param  	wasMoving
	 *         	The wasMoving indicator to check.
	 * @return 	True if and only if the given wasMoving is true and the Unit isn't moving currently or if the Unit is currently Moving and the given
	 * 			wasMoving is false.
	 *       	| result == ((wasMoving && !isMoving()) || (!wasMoving && isMoving()))
	*/
	private boolean canHaveAsWasMoving(boolean wasMoving) 
	{
		return ((wasMoving && !isMoving()) || (!wasMoving && isMoving()));
	}
	
	/**
	 * Set the wasMoving indicator of this Unit to the given wasMoving indicator.
	 * 
	 * @param  	wasMoving
	 *         	The new wasMoving indicator for this Unit.
	 *         
	 * @post   	The wasMoving indicator of this new Unit is equal to the given wasMoving indicator.
	 *       	| new.getWasMoving() == wasMoving
	 *       
	 * @throws 	ModelException
	 *         	This Unit cannot have the given wasMoving indicator as its wasMoving indicator.
	 *       	| ! canHaveAsWasMoving(getWasMoving())
	 */
	@Raw
	private void setWasMoving(boolean wasMoving) throws ModelException 
	{
		if (! canHaveAsWasMoving(wasMoving))
			throw new ModelException("The given value wasMoving is invalid for this Unit.");
		this.wasMoving = wasMoving;
	}
	
	/**
	 * Variable registering the wasMoving indicator of this Unit.
	 */
	private boolean wasMoving;
	
	// ----------------------
	// |					|
	// |					|
	// |    IS MOVING TO	|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the moving to indicator of this Unit.
	 */
	@Basic @Raw
	private boolean isMovingTo() 
	{
		return this.isMovingTo;
	}
	
	/**
	 * Check whether this Unit can have the given isMovingTo indicator as its isMovingTo indicator.
	 *  
	 * @param  	isMovingTo
	 *         	The moving to indicator to check.
	 * @return 	True if and only if the Unit is currently moving.
	 *       	| result == (isMoving ())
	 *      
	 * @note	The isMovingTo indicator must be enabled after the isMoving indicator is enabled and it must be disabled before the isMoving
	 * 			indicator is disabled. This means at all time the isMoving indicator must be true.
	 */
	private boolean canHaveAsIsMovingTo(boolean isMovingTo) 
	{
		return (isMoving());
	}
	
	/**
	 * Set the moving to indicator of this Unit to the given moving to indicator.
	 * 
	 * @param  	isMovingTo
	 *         	The new moving to indicator for this Unit.
	 *         
	 * @post   	The moving to indicator of this new Unit is equal to the given moving to indicator.
	 *       	| new.getIsMovingTo() == isMovingTo
	 *       
	 * @throws 	ModelException
	 *         	This Unit cannot have the given isMovingTo indicator as its isMovingTo indicator.
	 *       	| ! canHaveAsIsMovingTo(getIsMovingTo())
	 */
	@Raw
	private void setIsMovingTo(boolean isMovingTo) throws ModelException 
	{
		if (! canHaveAsIsMovingTo(isMovingTo))
			throw new ModelException("The given value isMovingTo is invalid for this Unit.");
		this.isMovingTo = isMovingTo;
	}
	
	/**
	 * Variable registering the moving to indicator of this Unit.
	 */
	private boolean isMovingTo;

	// ----------------------
	// |					|
	// |					|
	// |    IS MOVING TO 	|
	// |	  ADJACENT		|
	// |					|
	// ----------------------
	
	/**
	 * Return the moving to adjacent cube indicator of this Unit.
	 */
	@Basic @Raw
	private boolean isMovingToAdjacent() 
	{
		return this.isMovingToAdjacent;
	}
	
	/**
	 * Check whether this Unit can have the given isMovingToAdjacent indicator as its isMovingToAdjacent indicator.
	 *  
	 * @param  	isMovingToAdjacent
	 *         	The moving to adjacent cube indicator to check.
	 * @return 	True if and only if the Unit is currently moving.
	 *       	| result == (isMoving())
	 * 
	 * @note	The isMovingToAdjacent indicator must be enabled after the isMoving indicator is enabled and it must be disabled before the
	 * 			isMoving indicator is disabled. This means at all time the isMoving indicator must be true.	
	 */
	private boolean canHaveAsIsMovingToAdjacent(boolean isMovingToAdjacent) 
	{
		return (isMoving());
	}
	
	/**
	 * Set the moving to adjacent cube indicator of this Unit to the given moving to adjacent cube indicator.
	 * 
	 * @param  	isMovingToAdjacent
	 *         	The new moving to adjacent cube indicator for this Unit.
	 *         
	 * @post   	The moving to adjacent cube indicator of this new Unit is equal to the given moving to adjacent cube indicator.
	 *       	| new.getIsMovingToAdjacent() == isMovingToAdjacent
	 *       
	 * @throws 	ModelException
	 *         	This Unit cannot have the given isMovingToAdjacent indicator as its isMovingToAdjacent indicator.
	 *       	| ! canHaveAsIsMovingToAdjacent(getIsMovingToAdjacent())
	 */
	@Raw
	private void setIsMovingToAdjacent(boolean isMovingToAdjacent) throws ModelException 
	{
		if (! canHaveAsIsMovingToAdjacent(isMovingToAdjacent))
			throw new ModelException("The given value isMovingToAdjacent is invalid for this Unit.");
		this.isMovingToAdjacent = isMovingToAdjacent;
	}
	
	/**
	 * Variable registering the moving to adjacent cube indicator of this Unit.
	 */
	private boolean isMovingToAdjacent;
	
	// ----------------------
	// |					|
	// |					|
	// |  DESTINATION CUBE	|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the destination cube of this Unit.
	 */
	@Basic @Raw
	private int[] getDestinationCube() {
		return this.destinationCube;
	}
	
	/**
	 * Set the destination cube of this Unit to the given destination cube.
	 * 
	 * @param  	destinationCube
	 *         	The new destination cube for this Unit.
	 *         
	 * @post   	The destination cube of this new Unit is equal to the given destination cube.
	 *       	| new.getDestinationCube() == destinationCube
	 *       
	 * @throws 	ModelException
	 *         	This Unit cannot have the given destinationCube as its cubeCoordinates.
	 *       	| ! canHaveAsCubeCoordinates(destinationCube)
	 */
	@Raw
	private void setDestinationCube(int[] destinationCube) throws ModelException 
	{
		if (! canHaveAsCubeCoordinates(destinationCube))
			throw new ModelException("The given destinationCube is invalid for this Unit.");
		this.destinationCube = Arrays.copyOf(destinationCube, 3);
	}
	
	/**
	 * Variable registering the destination cube of this Unit.
	 */
	private int[] destinationCube = new int[3];
	
	// ----------------------
	// |					|
	// |					|
	// |     IS WORKING		|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the working indicator of this Unit.
	 */
	@Basic @Raw
	public boolean isWorking() 
	{
		return this.isWorking;
	}
	
	/**
	 * Check whether this Unit can have the given isWorking indicator as its isWorking indicator.
	 *  
	 * @param  	isWorking
	 *         	The working indicator to check.
	 * @return 	True if and only if the Unit isn't currently moving or attacking or defending.
	 *       	| result == ((!isMoving() || !isAttacking() || !isDefending)
	*/
	public boolean canHaveAsIsWorking(boolean isWorking) {
		return (!isMoving() || !isAttacking() || !isDefending());
	}
	
	/**
	 * Set the working indicator of this Unit to the given working indicator.
	 * 
	 * @param  	isWorking
	 *         	The new working indicator for this Unit.
	 *         
	 * @post   	The working indicator of this new Unit is equal to the given working indicator.
	 *       	| new.getIsWorking() == isWorking
	 *       
	 * @throws 	ModelException
	 *         	This Unit cannot have the given isWorking indicator as its isWorking indicator.
	 *       	| ! canHaveAsIsWorking(getIsWorking())
	 */
	@Raw
	public void setIsWorking(boolean isWorking) throws ModelException {
		if (! canHaveAsIsWorking(isWorking))
			throw new ModelException("The given value isWorking is invalid for this Unit.");
		this.isWorking = isWorking;
	}
	
	/**
	 * Make this Unit work.
	 * 
	 * @throws 	ModelException
	 * 			A condition was violated or an error was thrown.
	 * 
	 * @effect	The resting indicator of this Unit is disabled, only if the Unit isn't moving, attacking or already working and if the Unit
	 * 			has completed the initial resting period and if the Unit is currently resting.
	 * 			| if ((!(isMoving() || isAttacking()) && !isWorking()) && hasRestedOnePoint() && isResting())
	 * 			| then this.setIsResting(false)
	 * 
	 * @effect	The working indicator of this Unit is enabled, only if the Unit isn't moving, attacking or already working and if the Unit
	 * 			has completed the initial resting period.
	 * 			| if (!(isMoving() || isAttacking()) && !isWorking() && hasRestedOnePoint()) 
	 * 			| then this.setIsWorking(true)
	 * 
	 * @effect	The working duration of this Unit is set to 500 divided by its strength, only if the Unit isn't moving, attacking
	 * 			or already working and if the Unit has completed the initial restin)g period.
	 * 			| if (!(isMoving() || isAttacking() && !isWorking() && hasRestedOnePoint())
	 * 			| then this.setWorkingDuration(500/getStrength())
	 */
	public void work() throws ModelException
	{
		if (!(isMoving() || isAttacking()) && !isWorking() && hasRestedOnePoint())
		{
			if (isResting())
				setIsResting(false);
			
			setIsWorking(true);
			setWorkingDuration(500/getStrength());
		}
		else
			throw new ModelException("Unable to work right now.");
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
	 * 
	 * @throws 	ModelException
	 * 			A condition was violated or an error was thrown.
	 * 
	 * @effect	Set the target cube to the given cube coordinates, if the Unit isn't already working.
	 * 			| if (!isWorking())
	 * 			| 	then
	 * 			|		 targetCube = {x, y, z}	 
	 * 			|		 this.setTargetCube(targetCube)
	 * 
	 * @effect	The orientation is set to the arctangens of the difference between the given y coordinate and the current y coordinate
	 * 			and the difference between the given x coordinate and the current x coordinate, if the Unit isn't already working.
	 * 			| if (!isWorking())
	 * 			|	then this.setOrientation((y - getCubeCoordinates()[1]), (x - getCubeCoordinates[0]))
	 * 
	 * @effect	If the Unit isn't already working, let the Unit work.
	 * 			| if (!isWorking())
	 * 			|	then this.work()
	 */
	public void workAt(int x, int y, int z) throws ModelException
	{
		if (!isWorking())
		{
			try
			{
				// Target cube:
				int[] targetCube = {x, y, z};
				setTargetCube(targetCube);
				
				// A little extra: let the Unit face the cube he's working at. This clears up any confusion for the user.
				double arg0 = y - getCubeCoordinates()[1];
				double arg1 = x - getCubeCoordinates()[0];
				setOrientation(Math.atan2(arg0, arg1));
				
				// Start working:
				work();
			}
			catch (ModelException e)
			{
				//e.printStackTrace();
				
				// Disable working:
				setIsWorking(false);
			}
		}
	}
	
	/**
	 * Variable registering the working indicator of this Unit.
	 */
	private boolean isWorking;
	
	// ----------------------
	// |					|
	// |					|
	// |    TARGET CUBE		|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the targetCube of this Unit.
	 */
	@Basic @Raw
	public int[] getTargetCube() 
	{
		return this.targetCube;
	}
	
	/**
	 * Check whether this Unit can have the given targetCube as its targetCube.
	 *  
	 * @param  	targetCube
	 *         	The targetCube to check.
	 * @return 	True if and only if the targetCube coordinates are between this World's boundaries
	 * 			and the targetCube is a neighbouring cube of the current Unit's cube or the current cube itself.
	 *       	| result == getWorld().isBetweenBoundaries(targetCube[0], targetCube[1], targetCube[2]) && isTargetCube(targetCube)
	*/
	public boolean canHaveAsTargetCube(int[] targetCube) 
	{
		return getWorld().isBetweenBoundaries(targetCube[0], targetCube[1], targetCube[2]) && isNeighbouringCube(targetCube);
	}
	
	/**
	 * Check whether the given cube is a neighbouring cube of this Unit's cube.
	 * 
	 * @param 	cube
	 * 			The cube to check.
	 * @return	True if and only if the cube is a neighbouring cube. This means the coordinates components are either the Unit's cubeCoordinates
	 * 			components +1, -1 or if the cube components are equal to the Unit's cubeCoordinates components.
	 * 			| result ==  ((getCubeCoordinates()[0] == cube[0] || getCubeCoordinates()[0] + 1 == cube[0] || getCubeCoordinates()[0] - 1 == cube[0]) 
	 *			| && (getCubeCoordinates()[1] == cube[1] || getCubeCoordinates()[1] + 1 == cube[1] || getCubeCoordinates()[1] - 1 == cube[1])
	 *			| && (getCubeCoordinates()[2] == cube[2] || getCubeCoordinates()[2] + 1 == cube[2] || getCubeCoordinates()[2] - 1 == cube[2]))
	 */
	private boolean isNeighbouringCube(int [] cube)
	{
		return ((getCubeCoordinates()[0] == cube[0] || getCubeCoordinates()[0] + 1 == cube[0] || getCubeCoordinates()[0] - 1 == cube[0]) 
				&& (getCubeCoordinates()[1] == cube[1] || getCubeCoordinates()[1] + 1 == cube[1] || getCubeCoordinates()[1] - 1 == cube[1])
				&& (getCubeCoordinates()[2] == cube[2] || getCubeCoordinates()[2] + 1 == cube[2] || getCubeCoordinates()[2] - 1 == cube[2]));
	}
	
	/**
	 * Set the targetCube of this Unit to the given targetCube.
	 * 
	 * @param  	targetCube
	 *         	The new targetCube for this Unit.
	 *         
	 * @post   	The targetCube of this new Unit is equal to the given targetCube.
	 *       	| new.getTargetCube() == targetCube
	 *       
	 * @throws 	ModelException
	 *         	This Unit cannot have the given targetCube as its targetCube.
	 *       	| ! canHaveAsTargetCube(getTargetCube())
	 */
	@Raw
	public void setTargetCube(int[] targetCube) throws ModelException 
	{
		if (! canHaveAsTargetCube(targetCube))
			throw new ModelException("The given targetCube to work at is invalid.");
		this.targetCube = targetCube;
	}
	
	/**
	 * Variable registering the targetCube of this Unit.
	 */
	private int[] targetCube;
		
	// ----------------------
	// |					|
	// |					|
	// |  WORKING DURATION	|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the working duration of this Unit.
	 */
	@Basic @Raw
	private double getWorkingDuration() 
	{
		return this.workingDuration;
	}
	
	/**
	 * Check whether this Unit can have the given workingDuration as its workingDuration.
	 *  
	 * @param  	workingDuration
	 *         	The working duration to check.
	 * @return 	True if and only if the given working duration is between -0.2 and (500/getStrength())
	 *       	| result == ((workingDuration >= -0.2) && (workingDuration <= (500/getStrength())))
	 *       
	 * @note	We use the value -0.2 because we need to account for the fact that an advanceTime call could have 0.2 as duration,
	 * 			which could make the variable -0.2 (if it was already close to 0). This is rather rare, but still.
	*/
	private boolean canHaveAsWorkingDuration(double workingDuration)
	{
		return ((workingDuration >= -0.2) && (workingDuration <= (double)(500/getStrength())));
	}
	
	/**
	 * Set the working duration of this Unit to the given working duration.
	 * 
	 * @param  	workingDuration
	 *         	The new working duration for this Unit.
	 *         
	 * @post   	The working duration of this new Unit is equal to the given working duration.
	 *       	| new.getWorkingDuration() == workingDuration
	 *       
	 * @throws 	ModelException
	 *         	This Unit cannot have the given workingDuration as its workingDuration.
	 *       	| ! canHaveAsWorkingDuration(getWorkingDuration())
	 */
	@Raw
	private void setWorkingDuration(double workingDuration) throws ModelException 
	{
		if (! canHaveAsWorkingDuration(workingDuration))
			throw new ModelException("The given workingDuration is invalid for this Unit.");
		this.workingDuration = workingDuration;
	}
	
	/**
	 * Variable registering the working duration of this Unit.
	 */
	private double workingDuration;

	// ----------------------
	// |					|
	// |					|
	// |   IS CARRYING LOG	|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the isCarryingLog of this Unit.
	 */
	@Basic @Raw
	public boolean isCarryingLog() 
	{
		return this.isCarryingLog;
	}
	
	/**
	 * Check whether this Unit can have the given isCarryingLog indicator as its isCarryingLog indicator.
	 *  
	 * @param  	isCarryingLog
	 *         	The isCarryingLog indicator to check.
	 * @return 	True if and only if the given isCarryingLog indicator is true and the Unit currently has a log object 
	 * 			or if the isCarryingLog indicator is false and the Unit currently has no log object.
	 *       	| result == ((isCarryingLog && getLog() != null) || (!isCarryingLog && getLog() == null))
	*/
	public boolean canHaveAsIsCarryingLog(boolean isCarryingLog) 
	{
		return ((isCarryingLog && getLog() != null) || (!isCarryingLog && getLog() == null));
	}
	
	/**
	 * Set the isCarryingLog of this Unit to the given isCarryingLog.
	 * 
	 * @param  	isCarryingLog
	 *         	The new isCarryingLog for this Unit.
	 *         
	 * @post   	The isCarryingLog of this new Unit is equal to the given isCarryingLog.
	 *       	| new.getIsCarryingLog() == isCarryingLog
	 *       
	 * @throws 	ModelException
	 *         	This Unit cannot have the given isCarryingLog indicator as its isCarryingLog indicator.
	 *       	| ! canHaveAsIsCarryingLog(getIsCarryingLog())
	 */
	@Raw
	public void setIsCarryingLog(boolean isCarryingLog) throws ModelException 
	{
		if (! canHaveAsIsCarryingLog(isCarryingLog))
			throw new ModelException("The given isCarryingLog indicator is invalid for this Unit.");
		this.isCarryingLog = isCarryingLog;
	}
	
	/**
	 * Variable registering the isCarryingLog of this Unit.
	 */
	private boolean isCarryingLog;

	// ----------------------
	// |					|
	// |					|
	// |        LOG			|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the Log of this Unit.
	 */
	@Basic @Raw
	public Log getLog()
	{
		return this.log;
	}
	
	/**
	 * Check whether this Unit can have the given Log as its Log.
	 *  
	 * @param  	log
	 *         	The Log to check.
	 * @return 	True if and only if the given Log is part of this Unit's world or if the given Log is equal to null and if this Unit isn't 
	 * 			already carrying a boulder.
	 *       	| if (log == null && !isCarryingBoulder())
	 *       	| 	then result == true
	 *       	| else
	 *       	|	Iterator<Log> iterator = getWorld().getLogs().iterator();
	 *       	| 	while (iterator.hasNext())
	 *       	|		if (iterator.next() == log && !isCarryingBoulder())
	 *       	|			then result == true
	 */
	public boolean canHaveAsLog(Log log) 
	{
		if (log == null && !isCarryingBoulder())
			return true;
		else
		{
			Iterator<Log> iterator = getWorld().getAllLogs().iterator();
			while (iterator.hasNext())
				if (iterator.next() == log && !isCarryingBoulder())
					return true;
		}
		return false;
	}
	
	/**
	 * Set the Log of this Unit to the given Log.
	 * 
	 * @param  	log
	 *         	The new Log for this Unit.
	 *         
	 * @effect	If the log is a null object, the weight of this Unit is set to the difference between the current weight 
	 * 			and the current Log's weight.
	 * 			| if (log == null)
	 * 			| 	then this.setWeight(getWeight() - getLog().getWeight())
	 *         
	 * @post   	The Log of this new Unit is equal to the given Log.
	 *       	| new.getLog() == log
	 *       
	 * @post	The weight of this new Unit is equal to the sum of the Unit's current weight and the given Log's weight if the Log isn't null.
	 * 			| if (log != null)
	 * 			| 	then new.getWeight() == old.getWeight() + log.getWeight()
	 * 
	 * @throws 	ModelException
	 *         	This Unit can't have the given Log as its Log.
	 *       	| ! canHaveAsLog(getLog())
	 *       
	 * @note	If the Log is effective, the weight is manually set to the current weight + the weight of the log. This is done without
	 * 			the setter, because this temporary weight may exceed the checker.
	 */
	@Raw
	public void setLog(Log log) throws ModelException 
	{
		if (! canHaveAsLog(log))
			throw new ModelException("The given log is invalid for this Unit.");
		
		// Set weight:
		if (log != null)
			this.weight = getWeight() + log.getWeight();
		else
			setWeight(getWeight() - getLog().getWeight());
		
		// Set Log:
		this.log = log;
	}
	
	/**
	 * Drop this Unit's Log at the given target cube.
	 * 
	 * @param 	target
	 * 			The given target cube to drop the Log on.
	 * 
	 * @throws 	ModelException
	 * 			A condition was violated or an error was thrown.
	 * 
	 * @effect	Set the position of this Unit's Log to the given target position.
	 * 			| this.getLog().setPosition(target)
	 * 
	 * @effect	Set this Unit's Log ineffective.
	 * 			| this.setLog()
	 * 
	 * @effect	Disable the isCarryingLog indicator of this Unit.
	 * 			| this.setIsCarryingLog(false)
	 */
	public void dropLog(double[] target) throws ModelException
	{
		getLog().setPosition(target);
		setLog(null);
		setIsCarryingLog(false);
	}
	
	/**
	 * Variable registering the log of this Unit.
	 */
	private Log log;

	// ----------------------
	// |					|
	// |					|
	// | IS CARRYING BOULDER|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the isCarryingBoulder of this Unit.
	 */
	@Basic @Raw
	public boolean isCarryingBoulder() 
	{
		return this.isCarryingBoulder;
	}
	
	/**
	 * Check whether this Unit can have the given isCarryingBoulder indicator as its isCarryingBoulder indicator.
	 *  
	 * @param  	isCarryingBoulder
	 *         	The isCarryingBoulder to check.
	 * @return 	True if and only if the given isCarrying indicator is true and this Unit's Boulder isn't null 
	 * 			or if the given isCarrying indicator is false and this Unit's Boulder is null.
	 *       	| result == ((isCarryingBoulder && getBoulder() != null) || (!isCarryingBoulder && getBoulder() == null))
	*/
	public boolean canHaveAsIsCarryingBoulder(boolean isCarryingBoulder) 
	{
		return ((isCarryingBoulder && getBoulder() != null) || (!isCarryingBoulder && getBoulder() == null));
	}
	
	/**
	 * Set the isCarryingBoulder of this Unit to the given isCarryingBoulder.
	 * 
	 * @param  	isCarryingBoulder
	 *         	The new isCarryingBoulder for this Unit.
	 *         
	 * @post   	The isCarryingBoulder of this new Unit is equal to the given isCarryingBoulder.
	 *       	| new.getIsCarryingBoulder() == isCarryingBoulder
	 *       
	 * @throws 	ModelException
	 *         	This Unit can't have the given isCarryingBoulder indicator as its isCarryingBoulder indicator.
	 *       	| ! canHaveAsIsCarryingBoulder(getIsCarryingBoulder())
	 */
	@Raw
	public void setIsCarryingBoulder(boolean isCarryingBoulder) throws ModelException
	{
		if (! canHaveAsIsCarryingBoulder(isCarryingBoulder))
			throw new ModelException("The given isCarryingBoulder indicator is invalid for this Unit.");
		this.isCarryingBoulder = isCarryingBoulder;
	}
	
	/**
	 * Variable registering the isCarryingBoulder of this Unit.
	 */
	private boolean isCarryingBoulder;

	// ----------------------
	// |					|
	// |					|
	// |      BOULDER		|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the boulder of this Unit.
	 */
	@Basic @Raw
	public Boulder getBoulder() 
	{
		return this.boulder;
	}
	
	/**
	 * Check whether this Unit can have the given Boulder as its Boulder.
	 *  
	 * @param  	boulder
	 *         	The Boulder to check.
	 * @return 	True if and only if the given Boulder is either null or in this Unit's World and if this Unit isn't already carrying a Log.
	 *       	| if (boulder == null && !isCarryingLog())
	 *       	|	then result == true
	 *       	| else
	 *       	|	Iterator<Boulder> iterator = getWorld.getBoulders().iterator();
	 *       	|	while (iterator.hasNext())
	 *       	|		if (iterator.next() == boulder && !isCarryingLog())
	 *       	|			then result == true; 
	*/
	public boolean canHaveAsBoulder(Boulder boulder) 
	{
		if (boulder == null && !isCarryingLog())
			return true;
		else
		{
			Iterator<Boulder> iterator = getWorld().getAllBoulders().iterator();
			while (iterator.hasNext())
				if (iterator.next() == boulder && !isCarryingLog())
					return true;
		}
		return false;
	}
	
	/**
	 * Set the Boulder of this Unit to the given Boulder.
	 * 
	 * @param  	boulder
	 *         	The new Boulder for this Unit.
	 *       
	 * @effect	If the given Boulder is null, the weight of this unit is set to the difference between the current Unit's weight 
	 * 			and the current Boulder's weight.
	 *         	| if (boulder == null)
	 *         	| 	then this.setWeight(getWeight() - getBoulder().getWeight())
	 *         
	 * @post   	The Boulder of this new Unit is equal to the given Boulder.
	 *       	| new.getBoulder() == boulder
	 *       
	 * @post	The weight of this new Unit is equal to the sum of the current weight of this Unit and the weight of the given Boulder if the 
	 * 			given Boulder isn't null.
	 * 			| if (boulder != null)
	 * 			| 	then new.getWeight() == old.getWeight() + boulder.getWeight()
	 *       
	 * @throws 	ModelException
	 *         	This Unit can't have the given Boulder as its Boulder.
	 *       	| ! canHaveAsBoulder(getBoulder())
	 */
	@Raw
	public void setBoulder(Boulder boulder) throws ModelException 
	{
		if (! canHaveAsBoulder(boulder))
			throw new ModelException("The given boulder is invalid for this Unit.");
		
		// Set the weight:
		if (boulder != null)
			this.weight = getWeight() + boulder.getWeight();
		else
			setWeight(getWeight() - getBoulder().getWeight());
		
		// Set the Boulder:
		this.boulder = boulder;
		
	}
	
	/**
	 * Drop this Unit's Boulder at the given target position.
	 * 
	 * @param 	target
	 * 			The given target position
	 * 
	 * @throws 	ModelException
	 * 			A condition was violated or an error was thrown.
	 * 
	 * @effect	The position of this Unit's Boulder is set to the given target position.
	 * 			| this.getBoulder().setPosition(target)
	 * 
	 * @effect	This Unit's Boulder is set ineffective.
	 * 			| this.setBoulder(null)
	 * 
	 * @effect	This Unit's isCarryingBoulder indicator is disabled.
	 * 			| this.setIsCarryingBoulder(false)
	 */
	public void dropBoulder(double[] target) throws ModelException
	{
		getBoulder().setPosition(target);
		setBoulder(null);
		setIsCarryingBoulder(false);
	}
	
	/**
	 * Variable registering the boulder of this Unit.
	 */
	private Boulder boulder;
	
	// ----------------------
	// |					|
	// |					|
	// |    IS ATTACKING	|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the attacking indicator of this Unit.
	 */
	@Basic @Raw
	public boolean isAttacking() 
	{
		return this.isAttacking;
	}
	
	/**
	 * Set the isAttacking indicator of this Unit to the given isAttacking indicator.
	 * 
	 * @param  	isAttacking
	 *         	The new isAttacking indicator for this Unit.
	 *         
	 * @post   	The attacking indicator of this new Unit is equal to the given attacking indicator.
	 *       	| new.getIsAttacking() == isAttacking
	 */
	@Raw
	public void setIsAttacking(boolean isAttacking)
	{
		this.isAttacking = isAttacking;
	}
	
	/**
	 * Variable registering the attacking indicator of this Unit.
	 */
	private boolean isAttacking;

	// ----------------------
	// |					|
	// |					|
	// |    IS DEFENDING	|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the defending indicator of this Unit.
	 */
	@Basic @Raw
	public boolean isDefending() 
	{
		return this.isDefending;
	}
	
	/**
	 * Set the isDefending indicator of this Unit to the given isDefending indicator.
	 * 
	 * @param  	isDefending
	 *         	The new defending indicator for this Unit.
	 *         
	 * @post   	The isDefending indicator of this new Unit is equal to the given isDefending indicator.
	 *       	| new.getIsDefending() == isDefending
	 */
	@Raw
	public void setIsDefending(boolean isDefending)
	{
		this.isDefending = isDefending;
	}
	
	/**
	 * Variable registering the defending indicator of this Unit.
	 */
	private boolean isDefending;
	
	// ----------------------
	// |					|
	// |					|
	// |  FIGHTING DURATION	|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the fighting duration of this Unit.
	 */
	@Basic @Raw
	private double getFightingDuration() 
	{
		return this.fightingDuration;
	}
	
	/**
	 * Check whether the given fighting duration is a valid fighting duration for any Unit.
	 *  
	 * @param  	fightingDuration
	 *         	The fightingDuration to check.
	 * @return 	True if and only if the given fightingDuration is between 0 and 1.2.
	 *       	| result == ((fightingDuration >= 0) && (fightingDuration < 1.2))
	 *       
	 * @note	Fighting lasts for 1 second. AdvanceTime can only accept durations between 0 and 0.2, thus it is possible that the 
     * 			fightingDuration can be just less than 1.2 (e.g. 0.99 + 0.19, than a check which shows it is above 1)
	 */
	private static boolean isValidFightingDuration(double fightingDuration) 
	{
		return ((fightingDuration >= 0) && (fightingDuration < 1.2));
	}
	
	/**
	 * Set the fightingDuration of this Unit to the given fightingDuration.
	 * 
	 * @param  	fightingDuration
	 *         	The new fightingDuration for this Unit.
	 *         
	 * @post   	The fightingDuration of this new Unit is equal to the given fightingDuration.
	 *       	| new.getFightingDuration() == fightingDuration
	 *       
	 * @throws 	ModelException
	 *         	The given fightingDuration is not a valid fightingDuration for any Unit.
	 *       	| ! isValidFightingDuration(getFightingDuration())
	 */
	@Raw
	private void setFightingDuration(double fightingDuration) throws ModelException 
	{
		if (! isValidFightingDuration(fightingDuration))
			throw new ModelException("The given fightingDuration is too large.");
		this.fightingDuration = fightingDuration;
	}
	
	/**
	 * Variable registering the fighting duration of this Unit.
	 */
	private double fightingDuration;
	
	// ----------------------
	// |					|
	// |					|
	// |  	   FIGHT		|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Check whether both Units are adjacent to each other.
	 * 
	 * @param 	other
	 * 			The other Unit to check positions with.
	 * @return	True if and only if each cube coordinate of this Unit is equal, plus one or minus one to each cube coordinate
	 * 			of the other Unit .
	 * 			| for (int i = 0; i < getUnitPosition().length; i++)
	 * 			| 	if (!(getCubeCoordinates()[i] == other.getCubeCoordinates()[i] || getCubeCoordinates()[i] == other.getCubeCoordinates()[i] - 1
	 *			|		|| getCubeCoordinates()[i] == other.getCubeCoordinates()[i] + 1))
	 *			| 		then result == false
	 *			| result == true
	 */
	private boolean checkPositions(Unit other)
	{
		for (int i = 0; i < getUnitPosition().length; i++)
		{
			if (!(getCubeCoordinates()[i] == other.getCubeCoordinates()[i] || getCubeCoordinates()[i] == other.getCubeCoordinates()[i] - 1
					|| getCubeCoordinates()[i] == other.getCubeCoordinates()[i] + 1))
				return false;
		}
		return true;
	}
	
	/**
	 * Attack another Unit.
	 * 
	 * @param 	defender
	 * 			The Unit that's being attacked.
	 * 
	 * @throws 	ModelException
	 * 			A condition was violated or an error was thrown.
	 * 			Both Units aren't next to each other or they are in the same faction, thus they can't attack each other.
	 * 			| if (!checkPositions(defender) && defender.getFaction() == getFaction())
	 * 
	 * @effect	The isWorking indicator of this Unit is disabled, only if both Units are next to each other, if they are from different Factions
	 * 			and if the given Unit doesn't equal this Unit and if the isWorking indicator is enabled.
	 * 			| if (checkPositions(defender) && defender.getFaction() != getFaction() && defender != this && isWorking())
	 * 			| 	then this.setIsWorking(false)
	 * 
	 * @effect	The isResting indicator of this Unit is disabled, only if both Units are next to each other, if they are from different Factions
	 * 			and if the given Unit doesn't equal this Unit and if the isResting indicator is enabled.
	 * 			| if (checkPositions(defender) && defender.getFaction() != getFaction() && defender != this && isResting())
	 * 			| 	then this.setIsResting(false)
	 * 
	 * @effect	The orientation of this Unit is set to the arctangent of the difference
	 * 			of the y-component of the other Unit and the y-component of this Unit
	 * 			and the difference of the x-component of the other Unit and the x-component
	 * 			of this Unit, only if both Units are next to each other and if they are from different Factions.
	 * 			| if (checkPositions(defender) && defender.getFaction() != getFaction())
	 * 			| 	then this.setOrientation(Math.atan2(defender.getUnitPosition()[1] - getUnitPosition()[1],
	 * 			| 		defender.getUnitPosition()[0] - getUnitPosition()[0]))
	 * 
	 * @effect	The isAttacking indicator of this Unit is enabled, only if both Units are
	 * 			next to each other and if they are from different Factions.
	 * 			| if (checkPositions(defender) && defender.getFaction() != getFaction())
	 * 			| 	then this.setIsAttacking(true)
	 * 
	 * @effect	The isMoving indicator of this Unit is disabled, only if both Units are next to each other and if they are from
	 * 			different Factions and if this Unit is currently moving.
	 * 			| if (checkPositions(defender) && defender.getFaction() != getFaction() && isMoving())
	 * 			| 	then this.setIsMoving(false)
	 * 
	 * @effect	The wasMoving indicator of this Unit is enabled, only if both Units are next to each other and if they are from  
	 * 			different Factions and if this Unit is currently moving.
	 * 			| if (checkPositions(defender) && defender.getFaction() != getFaction() && isMoving())
	 * 			| 	then this.setWasMoving(true)
	 */
	public void attack(Unit defender) throws ModelException
	{
		if (checkPositions(defender) && defender.getFaction() != getFaction())
		{
				if (isWorking())
					setIsWorking(false);
				if (isResting())
					setIsResting(false);
				if (isMoving())
				{
					setIsMoving(false);
					setWasMoving(true);
				}
				
				double arg0 = defender.getUnitPosition()[1] - getUnitPosition()[1];
				double arg1 = defender.getUnitPosition()[0] - getUnitPosition()[0];
				setOrientation(Math.atan2(arg0, arg1));
				
				setIsAttacking(true);
		}
		else
			throw new ModelException("The units must be next to each other.");
	}
	
	/**
	 * Defend from the attack of another Unit.
	 * 
	 * @param 	attacker
	 * 			The Unit that is attacking this Unit.
	 * 
	 * @throws 	ModelException
	 * 			A condition was violated or an eeror was thrown.
	 * 			Both Units aren't next to each other or are from the same Faction, thus they can't attack each other.
	 * 			| if (!checkPositions(attacker) && attacker.getFaction() != getFaction())
	 * 
	 * @effect	The isWorking indicator of this Unit is disabled, only if both Units are
	 * 			next to each other, if they are from different Factions and if the isWorking indicator is enabled.
	 * 			| if (checkPositions(attacker) && attacker.getFaction() != getFaction() && isWorking())
	 * 			| 	then this.setIsWorking(false)
	 * 
	 * @effect	The isResting indicator of this Unit is disabled, only if both Units are
	 * 			next to each other, if they are from different Factions and if the isResting indicator is enabled.
	 * 			| if (checkPositions(defender) && attacker.getFaction() != getFaction() && isResting())
	 * 			| 	then this.setIsResting(false)
	 * 
	 * @effect	The isDefending indicator of this Unit is enabled, only if both Units are
	 * 			next to each other and if they are from different Factions.
	 * 			| if (checkPositions(defender) && attacker.getFaction() != getFaction())
	 * 			| 	then this.setIsDefending(true)
	 * 
	 * @effect	The orientation of this Unit is set to the arctangent of the difference
	 * 			of the y-component of the other Unit and the y-component of this Unit
	 * 			and the difference of the x-component of the other Unit and the x-component
	 * 			of this Unit, only if both Units are next to each other and if they are from different Factions.
	 * 			| if (checkPositions(defender) && attacker.getFaction() != getFaction())
	 * 			| 	then this.setOrientation(Math.atan2(attacker.getUnitPosition()[1] - getUnitPosition()[1],
	 * 			| 		attacker.getUnitPosition()[0] - getUnitPosition()[0]))
	 * 
	 * @effect	The current hitpoints of this Unit is set to the difference between its current hitpoints
	 * 			and the other Unit's strength divided by 10, only if both Units are next to each other,
	 * 			if they are from differen Factions and if this Unit didn't block or dodge the attack.
	 * 			| if (checkPositions(attacker) && attacker.getFaction() != getFaction() && !Dodged(attacker) && !Blocked(attacker))
	 * 			| 	then this.setCurrentHitpoints(getCurrentHitpoints() - (attacker.getStrength() / 10.0))
	 * 
	 * @effect	The isMoving indicator of this Unit is disabled, only if both Units are next to each other, if they are from 
	 * 			differen Factions and if this Unit is currently moving.
	 * 			| if (checkPositions(attacker) && attacker.getFaction() != getFaction() && isMoving())
	 * 			| 	then this.setIsMoving(false)
	 * 
	 * @effect	The wasMoving indicator of this Unit is enabled, only if both Units are next to each other, if they are from
	 * 			different Factions and if this Unit is currently moving.
	 * 			| if (checkPositions(attacker) && attacker.getFaction() != getFaction() && isMoving())
	 * 			| 	then this.setWasMoving(true)
	 * 
	 * @effect	The experience of this Unit is set to the sum of the current experience and 20, only if both Units are next to each
	 * 			other, if they are from different Factions and if this Unit has either dogded or blocked the incoming attack.
	 * 			| if (checkPositions(attacker) && attacker.getFaction() != getFaction() && (hasDogded() || hasBlocked()))
	 * 			|	then this.setExperience(getExperience() + 20)
	 */
	public void defend(Unit attacker) throws ModelException
	{
		if (checkPositions(attacker) && attacker.getFaction() != getFaction())
		{
				if (isWorking())
					setIsWorking(false);
				if (isResting())
					setIsResting(false);
				if (isMoving())
				{
					setIsMoving(false);
					setWasMoving(true);
				}
				
				setIsDefending(true);
				boolean hasEarnedExp = false;
				double arg0 = attacker.getUnitPosition()[1] - getUnitPosition()[1];
				double arg1 = attacker.getUnitPosition()[0] - getUnitPosition()[0];
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
			throw new ModelException("The units must be next to each other.");
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
	 * 			| result == new Random().nextDouble() <= 0.25*((getStrength() + getAgility()) / 
	 * 			| (attacker.getStrength() + attacker.getAgility()))
	 */
	private boolean hasBlocked(Unit attacker)
	{
		double propability = 0.25*((getStrength() + getAgility()) / (attacker.getStrength() + attacker.getAgility()));
		boolean hasBlocked = new Random().nextDouble() <= propability;
		return hasBlocked;
	}
	
	// ----------------------
	// |					|
	// |					|
	// |     IS RESTING		|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the resting indicator of this Unit.
	 */
	@Basic @Raw
	public boolean isResting()
	{
		return this.isResting;
	}
	
	/**
	 * Check whether this Unit can have the given isResting indicator as its inResting indicator.
	 * 
	 * @param  	isResting
	 *         	The resting indicator to check.
	 * @return 	True if and only if the given isResting indicator is enabled and this Unit isn't currently attacking or defending or if
	 * 			the given isResting indicator is false.
	 *       	| result == (isResting && (!isAttacking() || !isDefending()) || !isResting)
	 */
	public boolean canHaveAsIsResting(boolean isResting)
	{
		return (isResting && (!isAttacking() || !isDefending()) || !isResting);
	}
	
	/**
	 * Set the resting indicator of this Unit to the given resting indicator.
	 * 
	 * @param  	isResting
	 *         	The new resting indicator for this Unit.
	 *         
	 * @post   	The resting indicator of this new Unit is equal to the given resting indicator.
	 *       	| new.getIsResting() == isResting
	 *       
	 * @throws 	ModelException
	 *         	This Unit cannot have the given isResting indicator as its isResting indicator.
	 *       	| ! canHaveAsIsResting(getIsResting())
	 */
	@Raw
	public void setIsResting(boolean isResting) throws ModelException 
	{
		if (! canHaveAsIsResting(isResting))
			throw new ModelException("The given value isResting is invalid for this Unit.");
		this.isResting = isResting;
	}
	
	/**
	 * Make this Unit rest.
	 * 
	 * @throws 	ModelException
	 * 			A condition was violated or an error was thrown.
	 * 
	 * @effect	The isWorking indicator of this Unit is disabled, only if this Unit isn't attacking and if this Unit's current hitpoints
	 * 			are lower than the max hitpoints and if this Unit's current staminapoints is lower than the max staminapoints and if the
	 * 			Unit isn't already Resting and if the Unit is working.
	 * 			| if (!isAttacking() && (getCurrentHitpoints() < getMaxHitpoints() || getCurrentStaminapoints() < getMaxStaminapoints()) 
	 * 			| 	&& !isResting() && isWorking())
	 * 			| 	then this.setIsWorking(false)
	 * 
	 * @effect	The isResting indicator of this Unit is enabled, only if this Unit isn't attacking and if this Unit's current hitpoints 
	 * 			are lower than the max hitpoints or if this Unit's current stamina points is lower than the max staminapoints and if the 
	 * 			Unit isn't already resting.
	 * 			| if (!isAttacking() && (getCurrentHitpoints() < getMaxHitpoints() || getCurrentStaminapoints() < getMaxStaminapoints())
	 * 			| 	&& !isResting())
	 * 			| 	then this.setIsResting(true)
	 * 
	 * @effect	The restingDuration of this Unit is set to 0, only if this Unit isn't attacking and if this Unit's current hitpoints
	 * 			are lower than the max hitpoints or if this Unit's current stamina points is lower than the max staminapoints and if the
	 * 			Unit isn't already resting.
	 *  		| if (!isAttacking() && (getCurrentHitpoints() < getMaxHitpoints() || getCurrentStaminapoints() < getMaxStaminapoints())
	 *  		| 	&& !isResting())
	 * 			| 	then this.setRestingDuration(0)
	 * 
	 * @effect	The tempHitpoint of this Unit is set to 0, only if this Unit isn't attacking and if this Unit's current hitpoints
	 * 			are lower than the max hitpoints or if this Unit's current stamina points is lower than the max staminapoints and if the
	 * 			Unit isn't already resting.
	 * 			| if (!isAttacking() && (getCurrentHitpoints() < getMaxHitpoints() || getCurrentStaminapoints() < getMaxStaminapoints())
	 * 			| 	&& !isResting())
	 * 			| 	then this.setTempHitpoint(0)
	 * 
	 * @effect	The tempStaminapoint of this Unit is set to 0, only if this Unit isn't attacking and if this Unit's current hitpoints 
	 * 			are lower than the max hitpoints or if this Unit's current stamina points is lower than the max staminapoints and if the
	 * 			Unit isn't already resting.
	 * 	 		| if (!isAttacking() && (getCurrentHitpoints() < getMaxHitpoints() || getCurrentStaminapoints() < getMaxStaminapoints())
	 * 			|	&& !isResting())
	 * 			| 	then this.setTempStaminapoint(0)
	 * 
	 * @effect	The isMoving indicator is disabled, only if this Unit isn't attacking and if this Unit's current hitpoits are lower
	 * 			than the max hitpoints or if this Unit's current staminapoints are lower than the max staminapoints and if the Unit isn't
	 * 			already resting and if the Unit is currently moving.
	 * 			| if (!isAttacking() && (getCurrentHitpoints() < getMaxHitpoints() || getCurrentStaminapoints() < getMaxStaminapoints())
	 * 			| 	&& !isResting() && isMoving())
	 * 			| 	then this.setIsMoving(false)
	 * 
	 * @effect	The wasMoving indicator is enabled, only if this Unit isn't attacking and if this Unit's current hitpoints are kiwer 
	 * 			than the max hitpoints or if this Unit's current staminapoints are lower than the max staminapoints and if the Unit isn't
	 * 			already resting and if the Unit is currently moving.
	 * 			| if (!isAttacking() && (getCurrentHitpoints() < getMaxHitpoints() || getCurrentStaminapoints() < getMaxStaminapoints())
	 * 			| 	&& !isResting() && isMoving())
	 * 			| 	then this.setWasMoving(true)
	 */
	public void rest() throws ModelException
	{
		if (!isAttacking() && (getCurrentHitpoints() < getMaxHitpoints() || getCurrentStaminapoints() < getMaxStaminapoints()) && !isResting())
		{
			if (isWorking())
				setIsWorking(false);
			if (isMoving())
			{
				setIsMoving(false);
				setWasMoving(true);
			}
			if (hasRestedOnePoint())
				setHasRestedOnePoint(false);
			setIsResting(true);
			setRestingDuration(0);
			setTempHitpoint(0);
			setTempStaminapoint(0);
		}
		/*else
			throw new ModelException("Unable to rest right now.");*/
	}
	
	/**
	 * Variable registering the resting indicator of this Unit.
	 */
	private boolean isResting;

	// ----------------------
	// |					|
	// |					|
	// |  CURRENT RESTING	|
	// |	   PERIOD		|
	// |					|
	// ----------------------
	
	/**
	 * Return the resting period of this Unit.
	 */
	@Basic @Raw
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
	 *       	| result == ((currentRestingPeriod >= 0) && (currentRestingPeriod <= RESTING_PERIOD + 0.2))
	 *       
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
	 *         
	 * @post  	The resting period of this new Unit is equal to the given resting period.
	 *       	| new.getRestingPeriod() == restingPeriod
	 *       
	 * @throws 	ModelException
	 *         	The given resting period is not a valid resting period for any Unit.
	 *       	| ! isValidRestingPeriod(getRestingPeriod())
	 */
	@Raw
	private void setCurrentRestingPeriod(double currentRestingPeriod) throws ModelException 
	{
		if (! isValidRestingPeriod(currentRestingPeriod))
			throw new ModelException("The current resting period is too large.");
		this.currentRestingPeriod = currentRestingPeriod;
	}
	
	/**
	 * Variable registering the resting period of this Unit.
	 */
	private double currentRestingPeriod;
	
	/**
	 * Constant registering the period whenever a unit must rest.
	 */
	private final static int RESTING_PERIOD = 180;
	
	// ----------------------
	// |					|
	// |					|
	// |  RESTING DURATION	|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the resting duration of this Unit.
	 */
	@Basic @Raw
	private double getRestingDuration() 
	{
		return this.restingDuration;
	}
	
	/**
	 * Check whether the given resting duration is a valid resting duration for any Unit.
	 *  
	 * @param  	resting duration
	 *         	The resting duration to check.
	 * @return	True if and only if the given restingDuration is between 0 (inclusive) and 0.4 (exclusive)
	 *       	| result == ((restingDuration >= 0) && (restingDuration < 0.4))
	 *       
	 * @note	Normally, the Unit rests each 0.2 seconds, but each advanceTime can have a 0.2 value maximum. Thus, we extend this 
	 * 			invariant with 0.2, creating the boundary of 0.4.
	 */
	private static boolean isValidRestingDuration(double restingDuration) 
	{
		return ((restingDuration >= 0) && (restingDuration < 0.4));
	}
	
	/**
	 * Set the resting duration of this Unit to the given resting duration.
	 * 
	 * @param  	restingDuration
	 *         	The new resting duration for this Unit.
	 *         
	 * @post   	The resting duration of this new Unit is equal to the given resting duration.
	 *       	| new.getRestingDuration() == restingDuration
	 *       
	 * @throws 	ModelException
	 *         	The given resting duration is not a valid resting duration for any Unit.
	 *       	| ! isValidRestingDuration(getRestingDuration())
	 */
	@Raw
	private void setRestingDuration(double restingDuration) throws ModelException 
	{
		if (! isValidRestingDuration(restingDuration))
			throw new ModelException("The given restingDuration is invalid for this Unit.");
		this.restingDuration = restingDuration;
	}
	
	/**
	 * Variable registering the resting duration of this Unit.
	 */
	private double restingDuration;

	// ----------------------
	// |					|
	// |					|
	// |HAS RESTED ONE POINT|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the hasRestedOnePoint of this Unit.
	 */
	@Basic @Raw
	private boolean hasRestedOnePoint() 
	{
		return this.hasRestedOnePoint;
	}
	
	/**
	 * Set the hasRestedOnePoint of this Unit to the given hasRestedOnePoint.
	 * 
	 * @param  	hasRestedOnePoint
	 *         	The new hasRestedOnePoint for this Unit.
	 *         
	 * @post   	The hasRestedOnePoint of this new Unit is equal to the given hasRestedOnePoint.
	 *       	| new.getHasRestedOnePoint() == hasRestedOnePoint
	 */
	@Raw
	private void setHasRestedOnePoint(boolean hasRestedOnePoint) 
	{
		this.hasRestedOnePoint = hasRestedOnePoint;
	}
	
	/**
	 * Variable registering the hasRestedOnePoint of this Unit.
	 */
	private boolean hasRestedOnePoint;
	
	// ----------------------
	// |					|
	// |					|
	// |IS DEFAULT BEHAVIOUR|
	// |	  ENABLED		|
	// |					|
	// ----------------------
	
	/**
	 * Return the default behaviour indicator of this Unit.
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
	public boolean canHaveAsIsDefaultBehaviour(boolean isDefaultBehaviourEnabled) 
	{
		return (!isAttacking() || !isDefending());
	}
	
	/**
	 * Set the default behaviour indicator of this Unit to the given default behaviour indicator.
	 * 
	 * @param  	isDefaultBehaviourEnabled
	 *         	The new default behaviour indicator for this Unit.
	 *         
	 * @post   	The default behaviour indicator of this new Unit is equal to the given default behaviour indicator.
	 *       	| new.getIsDefaulBehaviour() == isDefaultBehaviourEnabled
	 *       
	 * @throws 	ModelException
	 *         	This Unit cannot have the given isDefaultBehaviour indicator as its isDefaultBehaviour indicator.
	 *       	| ! canHaveAsIsDefaulBehaviour(getIsDefaulBehaviour())
	 */
	@Raw
	public void setIsDefaultBehaviour(boolean isDefaultBehaviourEnabled) throws ModelException 
	{
		if (! canHaveAsIsDefaultBehaviour(isDefaultBehaviourEnabled))
			throw new ModelException("The given value isDefaultBehaviourEnabled is invalid for this Unit.");
		this.isDefaultBehaviourEnabled = isDefaultBehaviourEnabled;
	}
	
	/**
	 * Variable registering the default behaviour indicator of this Unit.
	 */
	private boolean isDefaultBehaviourEnabled;

	// ----------------------
	// |					|
	// |					|
	// |      FACTION		|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the faction of this unit.
	 */
	@Basic @Raw
	public Faction getFaction() 
	{
		return this.faction;
	}
	
	/**
	 * Check whether this Unit can have the given Faction as one of its Factions.
	 *  
	 * @param  	faction
	 *         	The Faction to check.
	 * @return 	True if and only if the given Faction is either one of the Factions of this Unit's World or if it is ineffective.
	 *       	| if (faction == null)
	 *       	|	then result == true
	 *       	| while (getWorld().getAllFactions().iterator().hasNext())
	 *       	| 	if (getWorld().getAllFactions().iterator().next() == faction)
	 *       	|		then result == true
	*/
	public boolean canHaveAsFaction(Faction faction)
	{
		if (faction == null)
			return true;
		for (Faction factionWorld : getWorld().getAllFactions())
		{
			if (factionWorld == faction && faction.hasAsUnit(this))
				return true;
		}
		return false;
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
	 * @throws 	ModelException
	 *         	This Unit cannot have the given Faction as one of its Factions.
	 *       	| ! canHaveAsFaction(getFaction())
	 */
	@Raw
	public void setFaction(Faction faction) throws ModelException 
	{
		if (! canHaveAsFaction(faction))
			throw new ModelException("The given faction is invalid for any Unit.");
		this.faction = faction;
	}
	
	/**
	 * Variable registering the faction of this unit.
	 */
	private Faction faction;

	// ----------------------
	// |					|
	// |					|
	// |     EXPERIENCE		|
	// |					|
	// |					|
	// ----------------------
	
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
	 * @return 	True if and only if the given experience is between 0 and 30.
	 *       	| result == (experience >= 0) && (experience < 30)
	 *       
	 * @note	The upper boundary of this checker is 30, because each time the experience counter reaches 10, the counter is resetted
	 * 			to 0. The max amount of experience a Unit can get at once is 20 (from a successfull attack or defence), thus the maximum
	 * 			experience one Unit can have at any given time is 30.
	 */
	public static boolean isValidExperience(int experience) 
	{
		return (experience >= 0) && (experience <= 30);
	}
	
	/**
	 * Set the experience of this Unit to the given experience.
	 * 
	 * @param 	experience
	 *        	The new experience for this Unit.
	 *        
	 * @post   	The experience of this new Unit is equal to the given experience.
	 *       	| new.getExperience() == experience
	 *       
	 * @throws 	ModelException
	 *         	The given experience is not a valid experience for any Unit.
	 *       	| ! isValidExperience(getExperience())
	 */
	@Raw
	public void setExperience(int experience) throws ModelException 
	{
		if (! isValidExperience(experience))
			throw new ModelException("The given experience is invalid for any Unit.");
		this.experience = experience;
	}
	
	/**
	 * Variable registering the experience of this Unit.
	 */
	private int experience;
	
	// ----------------------
	// |					|
	// |					|
	// |      IS ALIVE		|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the alive indicator of this Unit.
	 */
	@Basic @Raw
	public boolean isAlive() 
	{
		return this.isAlive;
	}
	
	/**
	 * Check whether this Unit can have the given isAlive indicator as its isAlive indicator.
	 * 
	 * @param  	isAlive
	 *         	The alive indicator to check.
	 * @return 	True if and only if all behaviours (moving, working, resting, defaultBehaviour, defending and attacking) are disabled.
	 *       	| result == (!isMoving() && !isWorking() && !isResting() && !isDefaultBehaviourEnabled() && !isDefending() && !isAttacking())
	*/
	public boolean canHaveAsIsAlive(boolean isAlive) 
	{
		return (!isMoving() && !isWorking() && !isResting() && !isDefaultBehaviourEnabled() && !isDefending() && !isAttacking());
	}
	
	/**
	 * Set the alive indicator of this Unit to the given alive indicator.
	 * 
	 * @param  	isAlive
	 *         	The new isAlive indicator for this Unit.
	 *         
	 * @post   	The isAlive indicator of this new Unit is equal to the given isAlive indicator.
	 *       	| new.getIsAlive() == isAlive
	 *       
	 * @throws 	ModelException
	 *         	This Unit cannot have the given isAlive indicator as its isAlive indicator.
	 *       	| ! canHaveAsIsAlive(getIsAlive())
	 */
	@Raw
	public void setIsAlive(boolean isAlive) throws ModelException 
	{
		if (! canHaveAsIsAlive(isAlive))
			throw new ModelException("This Unit cannot have the given isAlive indicator as its isAlive indicator.");
		this.isAlive = isAlive;
	}
	
	/**
	 * Variable registering the alive indicator of this Unit.
	 */
	private boolean isAlive;
	
	// ----------------------
	// |					|
	// |					|
	// |       WORLD		|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the world of this Unit.
	 */
	public World getWorld()
	{
		return this.world;
	}
	
	/**
	 * Set the world of this Unit to the given world.
	 * 
	 * @param 	world
	 * 			The new world for this Unit.
	 * @throws ModelException 
	 * 
	 * @post	The world of this new Unit is equal to the given world.
	 * 			| new.getWorld() == world
	 */
	public void setWorld(World world) throws ModelException
	{
		this.world = world;
	}
	
	/**
	 * Variable registering the world of this Unit.
	 */
	private World world;
	
	// ----------------------
	// |					|
	// |					|
	// |        TASK		|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the Task assigned to this Unit.
	 */
	@Basic @Raw
	public Task getTask()
	{
		return this.task;
	}
	
	/**
	 * Check whether this Unit can have the given Task as its Task.
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
	 * @throws	ModelException
	 * 			This Unit cannot have the given Task as its assigned Task
	 * 			| !canHaveAsTask(task)
	 */
	public void setTask(Task task) throws ModelException
	{
		if (!canHaveAsTask(task))
			throw new ModelException("This Unit cannot assign the given Task as its Task.");
		this.task = task;
	}
	
	/**
	 * Variable referencing the Task assigned to this Unit.
	 */
	private Task task;
}