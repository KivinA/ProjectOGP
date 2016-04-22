package hillbillies.model;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import be.kuleuven.cs.som.annotate.*;
import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.util.ConnectedToBorder;
import ogp.framework.util.ModelException;

/**
 * A class describing the World in which the game is played.
 * 
 * @version 0.7
 * @author 	Kevin Algoet & Jeroen Depuydt
 *
 * @invar	Each World must have proper Units.
 * 
 * @invar	Each World must have proper Factions.
 * 
 * @invar	Each World must have proper Logs.
 * 
 * @invar	Each World must have proper Boulders.
 * 
 * @invar	The terrain of each World must be a valid terrain for any World.
 */
public class World {
	// ----------------------
	// |					|
	// |					|
	// |	CONSTRUCTOR		|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Initialize this new World with the given terrainTypes and modelListener.
	 * 
	 * @param 	terrainTypes
	 * 			The terrainTypes for this new World.
	 * @param 	modelListener
	 * 			The modelListener for this new World.
	 * 
	 * @throws 	ModelException
	 * 			A condition was violated or an exception was thrown.
	 * 
	 * @effect	The terrain of this new World is set to the given terrainTypes.
	 * 
	 * @post	The modelListener of this new World is equal to the given modelListener.
	 */
	public World(int[][][] terrainTypes, TerrainChangeListener modelListener) throws ModelException
	{
		setTerrain(terrainTypes);
		this.modelListener = modelListener;
	}
	
	// ----------------------
	// |					|
	// |					|
	// |   MODELLISTENER	|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the modelListener of this World.
	 */
	@Basic @Raw @Immutable
	public TerrainChangeListener getModelListener()
	{
		return this.modelListener;
	}
	
	/**
	 * Variable registering the modelListener of this World.
	 */
	private final TerrainChangeListener modelListener;
	
	// ----------------------
	// |					|
	// |					|
	// |	ADVANCE TIME	|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Advance the time of this World.
	 * 
	 * @param	duration
	 * 			The given duration to advance the time.
	 * @throws	ModelException
	 * 			A condition was violated or an error was thrown.
	 */
	public void advanceTime(double duration) throws ModelException
	{
		if (!notConnected.isEmpty())
		{
			
			int x = notConnected.get(0)[0];
			int y = notConnected.get(0)[1];
			int z = notConnected.get(0)[2];
			int oldCubeType = getCubeType(x, y, z);
			
			// Set the cube type to air:
			setCubeType(x, y, z, DEFAULT_CUBE_TYPE);
			modelListener.notifyTerrainChanged(x, y, z);
			
			// Check if a rock or log has to be spawned.
			if (spawnLogOrBoulder())
			{
				double[] coordinates = {x + (getCubeLength() / 2.0), y + (getCubeLength() / 2.0), z + (getCubeLength() / 2.0)};
				
				if (oldCubeType == 1)
				{
					spawnBoulder(coordinates);
				}
				else if (oldCubeType == 2)
				{
					spawnLog(coordinates);
				}
			}
			
			// Remove this element from the list, since we have finished changing it:
			notConnected.remove(0);
		}
		
		// Units advance time:
		if (!getAllUnits().isEmpty())
		{
			Iterator<Unit> iterator = getAllUnits().iterator();
			while (iterator.hasNext())
			{
				// FIXME This causes ConcurrentModificationException (sometimes, not all times).
				iterator.next().advanceTime(duration);
			}
		}
		
		// Logs advance time:
		if (!getAllLogs().isEmpty())
		{
			Iterator<Log> iterator = getAllLogs().iterator();
			while (iterator.hasNext())
				iterator.next().advanceTime();
		}
		
		// Boulders advance time:
		if (!getAllBoulders().isEmpty())
		{
			Iterator<Boulder> iterator = getAllBoulders().iterator();
			while (iterator.hasNext())
				iterator.next().advanceTime();
		}
	}
	
	/**
	 * Check whether a Boulder or Log must be spawned.
	 * 
	 * @return	True if and only if the newly made random double is lower or equal than 0.25.
	 */
	private boolean spawnLogOrBoulder()
	{
		return new Random().nextDouble() <= 0.25;
	}
	
	// ----------------------
	// |					|
	// |					|
	// |	  TERRAIN		|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Check whether the given cube is attached to the borders of this World.
	 * 
	 * @param 	x
	 * 			The x coordinate of the given cube.
	 * @param 	y
	 * 			The y coordinate of the given cube.
	 * @param 	z
	 * 			The z coordinate of the given cube.
	 * 
	 * @return	True if and only if the given cube is indeed connected to one of the borders of this World.
	 */
	public boolean isConnectedToBorder(int x, int y, int z)
	{
		return connectedToBorderObject.isSolidConnectedToBorder(x, y, z);
	}
	
	/**
	 * Check whether the given cube has a solid neighbouring cube.
	 * 
	 * @param 	x
	 * 			The x coordinate of the given cube to check.
	 * @param 	y
	 * 			The y coordinate of the given cube to check.
	 * @param 	z
	 * 			The z coordinate of the given cube to check.
	 * 
	 * @return	True if and only if one of the neigbouring cubes (X+- 0..1, Y+- 0..1, Z+- 0..1) is solid.
	 */
	public boolean hasSolidNeighbouringCube(int x, int y, int z)
	{
		for (int i = -1; i <= 1; i++)
		{
			for (int j = -1; j <= 1; j++)
			{
				for (int k = -1; k <= 1; k++)
				{
					if ((i != 0 || j != 0 || k != 0) && isBetweenBoundaries(x + i, y + j, z + k))
						if (isSolidCube(x + i, y + j, z + k))
							return true;			
				}
			}
		}
		return false;
	}
	
	/**
	 * Check whether the given cube is between the boundaries of this World.
	 * 
	 * @param 	x
	 * 			The x coordinate of the given cube to check.
	 * @param 	y
	 * 			The y coordinate of the given cube to check.
	 * @param 	z
	 * 			The z coordinate of the given cube to check.
	 * 
	 * @return	True if and only if each coordinate is between the lower boundary and amount of cubes of this dimension (which is the upper
	 * 			boundary).
	 */
	public boolean isBetweenBoundaries(int x, int y, int z)
	{
		return ((x >= LOWER_BOUNDARY) && (y >= LOWER_BOUNDARY) && (z >= LOWER_BOUNDARY)) 
				&& ((x < getNbCubesX()) && (y < getNbCubesY()) && (z < getNbCubesZ()));
	}
	
	/**
	 * Check if the given cube is passable.
	 * 
	 * @param	x
	 * 			The x coordinate of the given cube to check.
	 * @param 	y
	 * 			The y coordinate of the given cube to check.
	 * @param 	z
	 * 			The z coordinate of the given cube to check.
	 * 
	 * @return	True if and only if the given cube type is equal to 0 (air) or 3 (workshop).
	 */
	public boolean isPassableCube(int x, int y, int z)
	{
		return (getCubeType(x, y, z) == 0) || (getCubeType(x, y, z) == 3);
	}
	
	/**
	 * Check if the given cube is solid.
	 * 
	 * @param 	x
	 * 			The x coordinate of the given cube to check.
	 * @param 	y
	 * 			The y coordinate of the given cube to check.
	 * @param 	z
	 * 			The z coordinate of the given cube to check.
	 * 
	 * @return	True if and only if the cube type of the given cube is either equal to 1 (rock) or 2 (tree).
	 */
	public boolean isSolidCube(int x, int y, int z)
	{
		return (getCubeType(x, y, z) == 1) || (getCubeType(x, y, z) == 2);
	}
	
	/**
	 * Return the terrain of this World.
	 */
	@Basic @Raw
	public int[][][] getTerrain() 
	{
		return this.terrain;
	}
	
	/**
	 * Check whether the given terrain is a valid terrain for any World.
	 * 
	 * @param 	terrain
	 * 			The given terrain to check.
	 * @return	True if and only if the given terrain isn't null and if all dimensions aren't empty.
	 */
	public static boolean isValidTerrain(int[][][] terrain)
	{
		if (terrain != null)
			if (terrain.length != 0) 
				if (terrain[0].length != 0) 
					if (terrain[0][0].length != 0)
						return true;
		return false;
	}
	
	/**
	 * Set the terrain of this World to the given terrain.
	 * 
	 * @param  	terrain
	 *         	The new terrain for this World.
	 * @throws 	ModelException 
	 *         	The given terrain is invalid for any World.
	 * @post   	The terrain of this new World is equal to the given terrain.
	 */
	@Raw
	public void setTerrain(int[][][] terrain) throws ModelException{
		if (!isValidTerrain(terrain))
			throw new ModelException("The given terrain is invalid for any World.");
		// Initialize new terrain:
		this.terrain = new int[terrain.length][terrain[0].length][terrain[0][0].length];
		
		// Initialize the connectedToBorder object:
		connectedToBorderObject = new ConnectedToBorder(getNbCubesX(), getNbCubesY(), getNbCubesZ());
		
		// Initialize each cube:
		for (int x = 0; x < terrain.length; x++)
		{
			for (int y = 0; y < terrain[x].length; y++)
			{
				for (int z = 0; z < terrain[x][y].length; z++)
				{
					setCubeType(x, y, z, terrain[x][y][z]);
					
					// Change solid to passable terrain where needed and add all cubes that changed to a list.
					if (isPassableCube(x, y, z))
						notConnected.addAll(connectedToBorderObject.changeSolidToPassable(x, y, z));
				}
			}
		}
	}
	
	/**
	 * Variable registering the terrain of this World.
	 */
	private int[][][] terrain;
	
	/**
	 * Return the lower boundary of this World.
	 */
	@Basic @Raw @Immutable
	public int getLowerBoundary()
	{
		return LOWER_BOUNDARY;
	}
	
	/**
	 * Constant registering the lower boundary of this world.
	 */
	private final int LOWER_BOUNDARY = 0;
	
	/**
	 * Variable registering the ConnectedToBorder object of this World.
	 */
	private ConnectedToBorder connectedToBorderObject;
	
	/**
	 * Return the cube length of any World.
	 */
	@Raw @Basic @Immutable
	public int getCubeLength()
	{
		return CUBE_LENGTH;
	}
	
	/**
	 * Constant registering the cube length of any World.
	 */
	private final static int CUBE_LENGTH = 1;
	
	/**
	 * Return the amount of cubes in the x dimension.
	 */
	public int getNbCubesX()
	{
		return getTerrain().length;
	}
	
	/**
	 * Return the amount of cubes in the y dimension.
	 */
	public int getNbCubesY()
	{
		return getTerrain()[0].length;
	}
	
	/**
	 * Return the amount of cubes in the z dimension.
	 */
	public int getNbCubesZ()
	{
		return getTerrain()[0][0].length;
	}
	
	/**
	 * Return the cube type of the given cube.
	 * 
	 * @param 	x
	 * 			The x coordinate of the given cube.
	 * @param 	y
	 * 			The y coordinate of the given cube.
	 * @param 	z
	 * 			The z coordinate of the given cube.
	 */
	public int getCubeType(int x, int y, int z)
	{
		return getTerrain()[x][y][z];
	}
	
	/**
	 * Check whether the given cube type value is a valid cube type value for any cube.
	 * 
	 * @param 	value
	 * 			The given cubeType value to check.
	 * @return	True if and only if the given value is between 0 and 3.
	 */
	public static boolean isValidCubeType(int value)
	{
		return (value >= 0) && (value <= 3);
	}
	
	/**
	 * Set the cube type of the given cube to the given value.
	 * 
	 * @param 	x
	 * 			The x coordinate of the given cube to set the cube type.
	 * @param 	y
	 * 			The y coordinate of the given cube to set the cube type.
	 * @param 	z
	 * 			The z coordinate of the given cube to set the cube type.
	 * @param 	value
	 * 			The given value to set as the cube type of the given cube.
	 * 
	 * @throws	ModelException
	 * 			The given cube is out of bounds or the given value is invalid for any cube.
	 * 
	 * @post	The cubeType of the given cube is set to the given value.
	 */
	public void setCubeType(int x, int y, int z, int value) throws ModelException
	{
		if (!isBetweenBoundaries(x, y, z))
			throw new ModelException("The given coordinates are out of bounds.");
		if (!isValidCubeType(value))
			throw new ModelException("The given value is invalid for any cube.");
		this.terrain[x][y][z] = value;
	}
	
	/**
	 * Constant registering the default cube type, which is air.
	 */
	private static final int DEFAULT_CUBE_TYPE = 0;

	/**
	 * Return a list containing all cubes that aren't connected to a border of this World.
	 */
	public List<int[]> getAllNotConnected()
	{
		return this.notConnected;
	}
	
	/**
	 * Variable referencing a list collecting all cubes that aren't connected to a border.
	 * 
	 * @invar	The referenced list is effective.
	 * @invar	Each cube referenced in the list is effective.
	 * 
	 * [FIXME] 	Add methods to add/remove elements from this list. There are possibly no real checkers? 
	 */
	private List<int[]> notConnected = new ArrayList<>();
	
	// ----------------------
	// |					|
	// |					|
	// |	   UNITS		|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return the amount of units of this World.
	 */
	@Basic @Raw
	public int getNbOfUnits() 
	{
		return units.size();
	}	
	
	/**
	 * Check whether this World has the given Unit as one of its Units.
	 * 
	 * @param 	unit
	 * 			The given Unit to check.
	 * @return	True if and only if the given Unit is registered as one of the Units of this World.
	 */
	public boolean hasAsUnit(Unit unit)
	{
		return units.contains(unit);
	}
	
	/**
	 * Check whether this World can have the given Unit as one of its Units.
	 * 
	 * @param 	unit
	 * 			The given Unit to check.
	 * @return	True if and only if the given Unit is effective and the current amount of Units is lower than the maximum amount of Units.
	 */
	@Raw
	public boolean canHaveAsUnit(Unit unit)
	{
		return (unit != null) && (getNbOfUnits() < MAX_AMOUNT_OF_UNITS);
	}
	
	/**
	 * Add a given Unit to this World.
	 * 
	 * @param 	unit
	 * 			The given Unit to add to this World.
	 * 
	 * @throws	ModelException
	 * 			This World cannot have the given Unit as one of its Units.
	 * 
	 * @post	The number of Units of this World is incremented by 1.
	 * @post	This World has the given Unit as one of its Units.
	 */
	public void addUnit(Unit unit) throws ModelException
	{
		if (!canHaveAsUnit(unit))
			throw new ModelException("This World cannot have the given Unit as one of its Units.");
		units.add(unit);
	}
	
	/**
	 * Check whether this World can remove the given Unit from its Units.
	 * 
	 * @param 	unit
	 * 			The given Unit to check.
	 * @return	True if and only if the given Unit is effective, if this World has the given Unit as one of its Units and if the 
	 * 			given Unit doesn't reference any World.
	 */
	@Raw
	public boolean canRemoveAsUnit(Unit unit)
	{
		return (unit != null) && hasAsUnit(unit) && (unit.getWorld() == null);
	}
	
	/**
	 * Remove the given Unit from this World.
	 * 
	 * @param 	unit
	 * 			The given Unit to remove.		
	 * 
	 * @throws	ModelException
	 * 			This World cannot remove the given Unit from its Units.
	 * 
	 * @post	The number of Units of this World is decremented by 1.
	 * @post	This World no longer has the given Unit as one of its Units.
	 */
	@Raw
	public void removeUnit(Unit unit) throws ModelException
	{
		unit.setWorld(null);
		if (!canRemoveAsUnit(unit))
			throw new ModelException("The given Unit cannot be removed from this World's Units.");
		units.remove(unit);
	}
	
	/**
	 * Check whether this World has proper Units attached to it.
	 * 
	 * @return	True if and only if this World can have each Unit as one of its Units and if each Unit
	 * 			references this World as the World to which they are attached.
	 */
	public boolean hasProperUnits()
	{
		Iterator<Unit> iterator = units.iterator();
		while (iterator.hasNext())
		{
			Unit unit = iterator.next();
			if (!canHaveAsUnit(unit))
				return false;
			if (unit.getWorld() != this)
				return false;
		}
		return true;
	}
	
	/**
	 * Return a set containing all the Units of this World.
	 */
	@Basic @Raw
	public Set<Unit> getAllUnits()
	{
		return this.units;
	}
	
	/**
	 * Spawn a new Unit with random initial attributes at a random position of the Game world. Initial positions
	 * shall be chosen such that a new Unit is occupying a passable cube at (x, y, z), where (x, y, z - 1) must be
	 * a solid cube or z = 0.
	 * 
	 * @return 	The new Unit that has been created.
	 * 
	 * @throws 	ModelException
	 * 			The amount of Units exceeds the maximum amount of Units.
	 * 
	 * @effect	The newly created Unit is added to this World's Units.
	 * 
	 * @effect	If the amount of Factions is lower than the maximum amount of Factions, create a new Faction and add this 
	 * 			Faction to this World's Factions.
	 * 
	 * @effect	If the amount of Factions is lower than the maximum amount of Factions, create a new Faction and set this
	 * 			Faction as the Faction of the newly created Unit.
	 * 
	 * @effect	If this World has the maximum amount of Factions, set the Faction with the lowest number of Units as the 
	 * 			Faction of the newly created Unit.
	 */
	public Unit spawnUnit(boolean enableDefaultBehaviour) throws ModelException
	{
		try
		{
			String name = "Hillbilly";
			int strength = new Random().nextInt(101);
			int agility = new Random().nextInt(101);
			int toughness = new Random().nextInt(101);
			int weight = new Random().nextInt(101);
			
			// Position: 
			int x;
			int y;
			int z;
			while (true)
			{
				x = new Random().nextInt(getNbCubesX());
				y = new Random().nextInt(getNbCubesY());
				z = new Random().nextInt(getNbCubesZ());
				
				if (isPassableCube(x, y, z))
				{
					if (z == 0 || isSolidCube(x, y, z - 1))
						break;
				}
			}
			
			int[] initialPosition = {x ,y ,z};

			Unit newUnit = new Unit(name, strength, agility, toughness, weight, initialPosition, enableDefaultBehaviour, this);
			
			addUnit(newUnit);
			
			// Factions:
			if (getNbOfFactions() < MAX_AMOUNT_OF_FACTIONS)
			{
				Faction newFaction = new Faction(this);
				addFaction(newFaction);
				
				newUnit.setFaction(newFaction);
			}
			else
			{
				Iterator<Faction> iterator = getAllFactions().iterator();
				Faction smallestFaction = iterator.next();
				
				while (iterator.hasNext())
				{
					Faction selectedFaction = iterator.next();
					if (smallestFaction.getNbOfUnits() > selectedFaction.getNbOfUnits())
						smallestFaction = selectedFaction;
				}
				
				newUnit.setFaction(smallestFaction);
			}
			
			return newUnit;
		}
		
		catch (ModelException e)
		{
			throw new ModelException("The maximum amount of Units has been reached.");
		}
	}
	
	/**
	 * Variable referencing a set of Units available in this World.
	 * 
	 * @invar	The referenced set is effective.
	 * @invar	Each Unit registered in the referenced set is effective.
	 * @invar	No Unit is registered more than once in the referenced set.
	 */
	private Set<Unit> units = new HashSet<Unit>();
	
	/**
	 * Constant registering the maximal amount of units of any World.
	 */
	private final static int MAX_AMOUNT_OF_UNITS = 100;
	
	// ----------------------
	// |					|
	// |					|
	// |	  FACTIONS		|
	// |					|
	// |					|
	// ----------------------

	/**
	 * Return the amount of factions of this World.
	 */
	@Basic @Raw
	public int getNbOfFactions() 
	{
		return factions.size();
	}
	
	/**
	 * Check whether this World has the given Faction as one of its Factions.
	 * 
	 * @param 	faction
	 * 			The given Faction to check.
	 * @return	True if and only if the given Faction is registered as one of the Factions of this World.
	 */
	public boolean hasAsFaction(Faction faction)
	{
		return factions.contains(faction);
	}
	
	/**
	 * Check whether this World can have the given Faction as one of its Factions.
	 * 
	 * @param 	faction
	 * 			The given Faction to check.
	 * @return	True if and only if the given Faction is effective and if the number of Factions is lower than the maximum amount of factions.
	 */
	@Raw
	public boolean canHaveAsFaction(Faction faction)
	{
		return (faction != null) && (getNbOfFactions() < MAX_AMOUNT_OF_FACTIONS);
	}
	
	/**
	 * Add the given Faction to a set of active factions and add 1 to the amountOfFactions.
	 * 
	 * @param 	faction
	 * 			The given faction to add to the set.
	 * @throws 	ModelException
	 * 			A condition was violated or an exception was thrown.
	 * 
	 * @post	The number of Factions of this World is incremented by 1.
	 * @post	This World has the given Faction as one of its Factions.
	 */
	public void addFaction(Faction faction) throws ModelException
	{
		if (!canHaveAsFaction(faction))
			throw new ModelException("This World cannot have the given Faction as one of its Factions.");
		factions.add(faction);
	}
	
	/**
	 * Check whether this World can remove the given Faction from its Factions.
	 * 
	 * @param 	faction
	 * 			The given Faction to check.
	 * @return	True if and only if the given Faction is effective, if it has no Units, if it has no World and Scheduler attached to it.
	 */
	@Raw
	public boolean canRemoveAsFaction(Faction faction)
	{
		return (faction != null) && (faction.getNbOfUnits() == 0) && (faction.getWorld() == null) && (faction.getScheduler() == null);
	}
	
	/**
	 * Remove the given Faction from this World
	 * 
	 * @param 	faction
	 * 			The given Faction to remove.
	 * 
	 * @throws	ModelException
	 * 			This World cannot remove the given Faction from its Factions.
	 * 
	 * @post	The number of Factions of this World is decremented by 1.
	 * @post	This World no longer has the given Faction as one of its Factions.
	 */
	@Raw
	public void removeFaction(Faction faction) throws ModelException
	{
		faction.setWorld(null);
		if (!canRemoveAsFaction(faction))
			throw new ModelException("This World cannot remove the given Faction from its Factions");
		factions.remove(faction);
	}
	
	/**
	 * Return a set collecting all Factions of this World.
	 */
	@Basic @Raw
	public Set<Faction> getAllFactions()
	{
		return this.factions;
	}
	
	/**
	 * Check whether this World has proper Factions attached to it.
	 * 
	 * @return	True if and only if this World can have each Faction as one of its Factions.
	 */
	public boolean hasProperFactions()
	{
		Iterator<Faction> iterator = factions.iterator();
		while (iterator.hasNext())
		{
			Faction faction = iterator.next();
			if (!canHaveAsFaction(faction))
				return false;
		}
		return true;
	}
	
	/**
	 * Variable referencing a set of Factions of this World.
	 * 
	 * @invar	The referenced set is effective.
	 * @invar	Each Faction registered in the referenced set is effective.
	 * @invar	No Faction is registered more than once in the referenced set.
	 */
	private Set<Faction> factions = new HashSet<Faction>();
	
	/**
	 * Constant registering the maximum amount of factions of this World.
	 */
	private static final int MAX_AMOUNT_OF_FACTIONS = 5;
	
	// ----------------------
	// |					|
	// |					|
	// |	   LOGS			|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return a set collecting all Logs in this World.
	 */
	@Basic @Raw
	public Set<Log> getAllLogs()
	{
		return this.logs;
	}
	
	/**
	 * Check whether this World has the given Log as one of its Logs.
	 * 
	 * @param 	log
	 * 			The given Log to check.
	 * @return	True if and only if the given Log is registered as one of the Logs of this World.
	 */
	public boolean hasAsLog(Log log)
	{
		return logs.contains(log);
	}
	
	/**
	 * Check whether the given Log is a valid Log for any World.
	 * 
	 * @param 	log
	 * 			The given Log to check.
	 * @return	True if and only if the given Log is effective.
	 */
	@Raw
	public static boolean isValidLog(Log log)
	{
		return (log != null);
	}
	
	/**
	 * Add a given Log to this World.
	 * 
	 * @param 	log
	 * 			The given Log to add to this World.
	 * 
	 * @throws	ModelException
	 * 			The given Log is invalid for any World.
	 * 
	 * @post	The number of Logs in this World is incremented by 1.
	 * @post	This World has the given Log as one of its Logs.
	 */
	public void addLog(Log log) throws ModelException
	{
		if (!isValidLog(log))
			throw new ModelException("The given Log is invalid for any World.");
		logs.add(log);
	}
	
	/**
	 * Check whether this World can remove the given Log from its Logs.
	 * 
	 * @param 	log
	 * 			The given Log to check.
	 * @return	True if and only if the given Log is valid, if the given Log is registered as one of the Logs of this World and if 
	 * 			the given Log doesn't reference any World.
	 */
	@Raw
	public boolean canRemoveAsLog(Log log)
	{
		return isValidLog(log) && hasAsLog(log) && (log.getWorld() == null);
	}
	
	/**
	 * Remove the given Log from this World.
	 * 
	 * @param 	log
	 * 			The given Log to remove.
	 * 
	 * @throws	ModelException
	 * 			This World cannot remove the given Log as one of its Logs.
	 * 
	 * @post	The number of Logs in this World is decremented by 1.
	 * @post 	This World no longer has the given Log as one of its Logs.
	 */
	@Raw
	public void removeLog(Log log) throws ModelException
	{
		if (!canRemoveAsLog(log))
			throw new ModelException("This World cannot remove the given Log from its Logs.");
		logs.remove(log);
	}
	
	/**
	 * Check whether this World has proper Logs attached to it.
	 * 
	 * @return	True if and only if each Log of this World is a valid Log for any World and if each Log references this World as the World
	 * 			to which they are attached.
	 */
	public boolean hasProperLogs()
	{
		Iterator<Log> iterator = logs.iterator();
		while (iterator.hasNext())
		{
			Log log = iterator.next();
			if (!isValidLog(log))
				return false;
			if (log.getWorld() != this)
				return false;
		}
		return true;
	}
	
	/**
	 * Spawn a new Log with a random weight in a given cube in this World.
	 * 
	 * @param 	targetCube
	 * 			The given cube in which the new Log spawns.
	 * 
	 * @throws 	ModelException
	 * 			A condition was violated or an error was thrown.
	 * 			The given targetCube cannot be null.
	 * 
	 * @effect	If the given targetCube isn't passable, change the cube type to air.
	 * 
	 * @effect	Initialize a new Log with the given targetCube, a random weight and this World as its World.
	 * 
	 * @effect	Add this newly created Log to this World's collection of Logs.
	 */
	public void spawnLog(double[] targetCube) throws ModelException
	{
		if (targetCube == null)
			throw new ModelException("The targetCube cannot be null.");
		if (!isPassableCube((int) (targetCube[0] - getCubeLength() / 2.0), (int) (targetCube[1] - getCubeLength() / 2.0), (int) (targetCube[2] - getCubeLength() / 2.0)))
			changeCubeTypeAir(targetCube);
		new Log(targetCube, calculateRandomWeight(), this);
	}
	
	/**
	 * Variable referencing a set of Logs of this World.
	 * 
	 * @invar	The referenced set is effective.
	 * @invar	Each Log registered in the referenced set is effective.
	 * @invar	No Log is registered more than once in the referenced set.
	 */
	private Set<Log> logs = new HashSet<Log>();
	
	// ----------------------
	// |					|
	// |					|
	// |	  BOULDERS		|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Return a set collecting all Boulders in this World.
	 */
	@Basic @Raw
	public Set<Boulder> getAllBoulders()
	{
		return this.boulders;
	}
	
	/**
	 * Check whether this World has the given Boulder as one of its Boulders.
	 * 
	 * @param 	boulder
	 * 			The given Boulder to check.
	 * @return	True if and only if the given Boulder is registered as on of the Boulders of this World.
	 */
	public boolean hasAsBoulder(Boulder boulder)
	{
		return boulders.contains(boulder);
	}
	
	/**
	 * Check whether the given Boulder is a valid Boulder for any World.
	 * 
	 * @param 	boulder
	 * 			The given Boulder to check.
	 * @return	True if and only if the given Boulder is effective.
	 */
	@Raw
	public static boolean isValidBoulder(Boulder boulder)
	{
		return boulder != null;
	}
	
	/**
	 * Add a given Boulder to this World.
	 * 
	 * @param 	boulder
	 * 			The given Boulder to add to this World.
	 * 
	 * @throws	ModelException
	 * 			The given Boulder is an invalid Boulder for any World.
	 * 
	 * @post 	The number of Boulders in this World is incremented by 1.
	 * @post	This World has the given Boulder as one of its Boulders.
	 */
	public void addBoulder(Boulder boulder) throws ModelException
	{
		if (!isValidBoulder(boulder))
			throw new ModelException("The given Boulder is an invalid Boulder for any World.");
		boulders.add(boulder);
	}
	
	/**
	 * Check whether this World can remove the given Boulder from its Boulders.
	 * 
	 * @param 	boulder
	 * 			The given Boulder to check.
	 * @return	True if and only if the given Boulder is valid, if this World has the given Boulder as one of its Boulders and
	 * 			if the given Boulder doesn't reference any World.
	 */
	@Raw
	public boolean canRemoveAsBoulder(Boulder boulder)
	{
		return isValidBoulder(boulder) && hasAsBoulder(boulder) && (boulder.getWorld() == null);
	}
	
	/**
	 * Remove the given Boulder from this World.
	 * 
	 * @param 	boulder
	 * 			The given Boulder to remove.
	 * 
	 * @throws	ModelException
	 * 			This World cannot remove the given Boulder from its Boulders.
	 * 
	 * @post	The number of Boulders in this World is decremented by 1.
	 * @post 	This World no longer has the given Boulder as one of its Boulders.
	 */
	@Raw
	public void removeBoulder(Boulder boulder) throws ModelException
	{
		if (!canRemoveAsBoulder(boulder))
			throw new ModelException("This World cannot remove the given Boulder from its Boulders.");
		boulders.remove(boulder);
	}
	
	/**
	 * Check whether this World has proper Boulders attached to it.
	 * 
	 * @return	True if and only if each Boulder of this World is a valid Boulder for any World and if each Boulder references this World
	 * 			as the World to which they are attached.
	 */
	public boolean hasProperBoulders()
	{
		Iterator<Boulder> iterator = boulders.iterator();
		while (iterator.hasNext())
		{
			Boulder boulder = iterator.next();
			if (!isValidBoulder(boulder))
				return false;
			if (boulder.getWorld() != this)
				return false;
		}
		return true;
	}
	
	/**
	 * Spawn a Boulder in a given cube in this World.
	 * 
	 * @param 	targetCube
	 * 			The given cube in which the new Boulder spawns.	
	 * 
	 * @throws 	ModelException
	 * 			A condition was violated or an error was thrown.
	 * 
	 * @effect	If the given targetCube isn't passable, change the cube type to air.
	 * 
	 * @effect	Initialize a new Boulder with the given targetCube, a random weight and this World as its World.
	 * 
	 * @effect	Add this newly created Boulder to this World's collection of Boulders.
	 */
	public void spawnBoulder(double[] targetCube) throws ModelException
	{
		if (!isPassableCube((int) (targetCube[0] - getCubeLength() / 2.0), (int) (targetCube[1] - getCubeLength() / 2.0), (int) (targetCube[2] - getCubeLength() / 2.0)))
			changeCubeTypeAir(targetCube);
		new Boulder(targetCube, calculateRandomWeight(), this);
	}
	
	/**
	 * Variable referencing a set of Boulders of this World.
	 * 
	 * @invar	The referenced set is effective.
	 * @invar	Each Boulder registered in the referenced set is effective.
	 * @invar	No Boulder is registered more than once in the referenced set.
	 */
	private Set<Boulder> boulders = new HashSet<Boulder>();
	
	// ----------------------
	// |					|
	// |					|
	// |  AUXILIARY METHODS	|
	// |					|
	// |					|
	// ----------------------
	
	/**
	 * Calculate a random weight.
	 * 
	 * @return	A random weight between 10 and 50.
	 */
	private int calculateRandomWeight()
	{
		int min_weight = 10;
		return new Random().nextInt(51 - min_weight) + min_weight;
	}
	
	/**
	 * Change the cube type of the given targetCube to air.
	 * 
	 * @param 	targetCube
	 * 			The given targetCube to change.
	 * 
	 * @throws 	ModelException
	 * 			A condition was violated or an error was thrown.
	 * 
	 * @effect	Set the cube type of the given targetCube to 0 (air).
	 * 
	 * @effect	Add all positions that aren't connected to any border thanks to the change of the cube to this World's collection of 
	 * 			cubes that aren't connected.
	 * 
	 * @effect	Notify the model listener that the targetCube has changed in the terrain.
	 */
	private void changeCubeTypeAir(double[] targetCube) throws ModelException
	{
		int x = (int) (targetCube[0] - 0.5);
		int y = (int) (targetCube[1] - 0.5);
		int z = (int) (targetCube[2] - 0.5);
		
		setCubeType(x, y, z, 0);
		getAllNotConnected().addAll(connectedToBorderObject.changeSolidToPassable(x, y, z));
		getModelListener().notifyTerrainChanged(x, y, z);
	}
	
}
