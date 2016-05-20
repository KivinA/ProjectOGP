package hillbillies.model;

import java.util.*;
import be.kuleuven.cs.som.annotate.*;
import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.util.ConnectedToBorder;
import ogp.framework.util.ModelException;

/**
 * A class describing the World in which the game is played, containing a {@link TerrainChangeListener}, a 3D array which
 * describes the terrain, a {@link ConnectedToBorder} object, a set of {@link Unit}s, a set of {@link Faction}s,
 * a set of {@link Boulder}s and a set of {@link Log}s.
 * 
 * @version 0.9
 * @author 	Kevin Algoet & Jeroen Depuydt
 *
 * @invar	The TerrainChangeListener of each World must be a valid TerrainChangeListener for any World.
 * @invar	Each World must have proper Units.
 * @invar	Each World must have proper Factions.
 * @invar	Each World must have proper Logs.
 * @invar	Each World must have proper Boulders.
 * @invar	The terrain of each World must be a valid terrain for any World.
 */
public class World {
	
	/**
	 * Initialize this new World with the given terrainTypes and {@link TerrainChangeListener}.
	 * 
	 * @param 	terrainTypes
	 * 			The terrainTypes for this new World.
	 * @param 	modelListener
	 * 			The {@link TerrainChangeListener} for this new World.
	 * @throws	IllegalArgumentException
	 * 			The given TerrainChangeListener is invalid for any World.
	 * @throws	IllegalArgumentException
	 * 			The given terrain is invalid for any World.
	 * @throws 	IllegalArgumentException
	 * 			A condition was violated or an exception was thrown.
	 * @post	The TerrainChangeListener of this new World is equal to the given modelListener.
	 * @post	The terrain of this World is initialized as a new 3D array with the corresponding lengths of the given 3D array terrain.
	 * @post	The connectedToBorderObject is initialized as a new ConnectedToBorder with the number of x, y and z cubes.
	 * @effect	Initialize the terrain of this World according to the given terrain.
	 */
	public World(int[][][] terrain, TerrainChangeListener modelListener) throws IllegalArgumentException
	{
		if (!isValidTerrainChangeListerener(modelListener))
			throw new IllegalArgumentException("The given TerrainChangeListener is invalid for any World");
		if (!isValidTerrain(terrain))
			throw new IllegalArgumentException("The given terrain is invalid for any World!");
		this.modelListener = modelListener;
		this.terrain = new int[terrain.length][terrain[0].length][terrain[0][0].length];
		this.connectedToBorderObject = new ConnectedToBorder(getNbCubesX(), getNbCubesY(), getNbCubesZ());
		initializeTerrain(terrain);
	}
	
	/**
	 * Return the {@link TerrainChangeListener} of this World.
	 */
	@Basic @Raw @Immutable
	public TerrainChangeListener getModelListener()
	{
		return this.modelListener;
	}
	
	/**
	 * Check whether the given {@link TerrainChangeListener} is a valid {@link TerrainChangeListener} for any World.
	 * 
	 * @param 	modelListener
	 * 			The {@link TerrainChangeListener} to check.
	 * @return	True if and only if the given TerrainChangeListener is effective.
	 */
	public static boolean isValidTerrainChangeListerener(TerrainChangeListener modelListener)
	{
		return (modelListener != null);
	}
	
	/**
	 * Variable referencing the {@link TerrainChangeListener} of this World.
	 */
	private final TerrainChangeListener modelListener;
	
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
		if (!getAllNotConnected().isEmpty())
		{
			int[] cube = getNotConnectedCubeAt(0);
			int oldCubeType = getCubeType(cube[0], cube[1], cube[2]);
			
			// Set the cube type to air:
			setCubeType(cube[0], cube[1], cube[2], DEFAULT_CUBE_TYPE);
			modelListener.notifyTerrainChanged(cube[0], cube[1], cube[2]);
			
			// Check if a rock or log has to be spawned.
			if (spawnLogOrBoulder())
			{
				double[] coordinates = {cube[0] + (getCubeLength() / 2.0), cube[0] + (getCubeLength() / 2.0), cube[0] + (getCubeLength() / 2.0)};
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
			removeNotConnected(cube);
		}
		
		// Units advance time:
		if (!getAllUnits().isEmpty())
		{
			for (Unit unit : getAllUnits())
				unit.advanceTime(duration);
		}
		
		// Logs advance time:
		if (!getAllLogs().isEmpty())
		{
			for (Log log : getAllLogs())
				log.advanceTime();
		}
		
		// Boulders advance time:
		if (!getAllBoulders().isEmpty())
		{
			for (Boulder boulder : getAllBoulders())
				boulder.advanceTime();
		}
	}
	
	/**
	 * Check whether a {@link Boulder} or {@link Log} must be spawned.
	 * 
	 * @return	True if and only if the newly made random double is lower or equal than 0.25.
	 */
	@Model @Raw
	private boolean spawnLogOrBoulder()
	{
		return new Random().nextDouble() <= 0.25;
	}
	
	/**
	 * Check whether the given cube is attached to the borders of this World.
	 * 
	 * @param 	x
	 * 			The x coordinate of the cube to check.
	 * @param 	y
	 * 			The y coordinate of the cube to check.
	 * @param 	z
	 * 			The z coordinate of the cube to check.
	 * @return	True if and only if the given cube is indeed connected to one of the borders of this World.
	 */
	@Raw
	public boolean isConnectedToBorder(int x, int y, int z)
	{
		return connectedToBorderObject.isSolidConnectedToBorder(x, y, z);
	}
	
	/**
	 * Check whether the given cube has a solid neighbouring cube.
	 * 
	 * @param 	x
	 * 			The x coordinate of the cube to check.
	 * @param 	y
	 * 			The y coordinate of the cube to check.
	 * @param 	z
	 * 			The z coordinate of the cube to check.
	 * @return	True if and only if one of the neigbouring cubes (X+- 0..1, Y+- 0..1, Z+- 0..1) is solid.
	 */
	@Raw
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
	 * 			The x coordinate of the cube to check.
	 * @param 	y
	 * 			The y coordinate of the cube to check.
	 * @param 	z
	 * 			The z coordinate of the cube to check.
	 * @return	True if and only if each coordinate is between the lower boundary and amount of cubes of this dimension 
	 * 			(which is the upper boundary).
	 */
	@Raw
	public boolean isBetweenBoundaries(int x, int y, int z)
	{
		return ((x >= LOWER_BOUNDARY) && (y >= LOWER_BOUNDARY) && (z >= LOWER_BOUNDARY)) 
				&& ((x < getNbCubesX()) && (y < getNbCubesY()) && (z < getNbCubesZ()));
	}
	
	/**
	 * Check if the given cube is passable.
	 * 
	 * @param	x
	 * 			The x coordinate of the cube to check.
	 * @param 	y
	 * 			The y coordinate of the cube to check.
	 * @param 	z
	 * 			The z coordinate of the cube to check.
	 * @return	True if and only if the cube type of the given cube type is equal to 0 (air) or 3 (workshop).
	 */
	@Raw
	public boolean isPassableCube(int x, int y, int z)
	{
		return (getCubeType(x, y, z) == 0) || (getCubeType(x, y, z) == 3);
	}
	
	/**
	 * Check if the given cube is solid.
	 * 
	 * @param 	x
	 * 			The x coordinate of the cube to check.
	 * @param 	y
	 * 			The y coordinate of the cube to check.
	 * @param 	z
	 * 			The z coordinate of the cube to check.
	 * @return	True if and only if the cube type of the given cube is either equal to 1 (rock) or 2 (tree).
	 */
	public boolean isSolidCube(int x, int y, int z)
	{
		return (getCubeType(x, y, z) == 1) || (getCubeType(x, y, z) == 2);
	}
	
	/**
	 * Convert the given precise double coordinates to integer cube coordinates.
	 * 
	 * @param	coordinates
	 * 			The double coordinates to convert.
	 * @return	An ineffective array if the given array is ineffective.
	 * @return	An integer array containing the x, y, z cube coordinates according to the given double precise coordinates.
	 */
	public static int[] getCubeCoordinates(double[] coordinates)
	{
		if (coordinates == null)
			return null;
		int x = round(coordinates[0]);
		int y = round(coordinates[1]);
		int z = round(coordinates[2]);
		return new int[]{x, y, z};
	}
	
	/**
	 * Convert the given integer cube coordinates to precise double coordinates.
	 * 
	 * @param 	coordinates
	 * 			The integer coordinates to convert.
	 * @return	An ineffective array if the given array is ineffective.
	 * @return	A double array containing the precise x, y, z coordinates according to the given integer coordinates.
	 */
	public static double[] getPreciseCoordinates(int[] coordinates)
	{
		if (coordinates == null)
			return null;
		double x = coordinates[0] + 0.5;
		double y = coordinates[1] + 0.5;
		double z = coordinates[2] + 0.5;	
		return new double[]{x, y, z};
	}
	
	/**
	 * Check whether the given cube is a neighbour of a given original cube.
	 * 
	 * @param 	originalCube
	 * 			The original cube to check the neighbour cube with.
	 * @param 	neighbourCube
	 * 			The cube to check if its a neighbouring cube of the original cube.
	 * @return	True if and only if the given neighbour cube is a neighbouring cube of the original cube. This means the coordinates components
	 * 			of the neighbouring cube are either the original cube components +1, -1 or 
	 * 			if the neighbouring cube components are equal to the original cube components.
	 */
	public static boolean isNeighbouringCube(int[] originalCube, int[] neighbourCube)
	{
		return ( ( (originalCube[0] == neighbourCube[0]) || ((originalCube[0] + 1) == neighbourCube[0]) || ((originalCube[0] - 1) == neighbourCube[0]) )
				&& ( (originalCube[1] == neighbourCube[1]) || ((originalCube[1] + 1) == neighbourCube[1]) || ((originalCube[1] - 1) == neighbourCube[1]) )
				&& ( (originalCube[2] == neighbourCube[2]) || ((originalCube[2] + 1) == neighbourCube[2]) || ((originalCube[2] - 1) == neighbourCube[2]) ) );
	}
	
	/**
	 * An auxiliary method to help round the given double number to an integer number. 
	 * 
	 * @param 	number
	 * 			The number to round.
	 * @return	The rounded integer number: 
	 * 			Rounded down if the decimal was lower or equal to 0.5.
	 * 			Rounded up if the decimal was higher than 0.5.
	 * @note	We have created this method because Math.round() rounds the given double up starting from 0.5 and up.
	 * 			We need a rounding method that can provide us with rounding a given number down, even when the decimal point is 0.5.
	 */
	@Model
	private static int round(double number)
	{
		double numberAbs = Math.abs(number);
		int intNumber = (int) numberAbs;
		double result = numberAbs - (double) intNumber;
		if (result < 0.5)
			return number < 0 ? -intNumber: intNumber - 1;
		else
			return number < 0 ? -(intNumber + 1) : intNumber;
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
			if (terrain.length > 0) 
				if (terrain[0].length > 0) 
					if (terrain[0][0].length > 0)
						return true;
		return false;
	}
	
	/**
	 * Initialize each cube in the terrain of this World.
	 * 
	 * @param 	terrain
	 * 			The terrain which contains the values for the cubes.
	 * @throws	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 * @effect	Set the cube type of each cube to the given value contained in terrain.
	 * @effect	If the selected cube is passable, change the given cube from solid to passable in the ConnectedToBorderObject and add
	 * 			the changed cubes to the notConnected list.
	 */
	@Model
	private void initializeTerrain(int[][][] terrain)
	{		
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
						addAllNotConnected(connectedToBorderObject.changeSolidToPassable(x, y, z));
					
					if (terrain[x][y][z] == 3)
						addWorkshop(new int[]{x, y, z});
				}
			}
		}
	}
	
	/**
	 * Variable registering the terrain of this World.
	 */
	private final int[][][] terrain;
	
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
	 * Variable referencing the {@link ConnectedToBorder} object of this World.
	 */
	private final ConnectedToBorder connectedToBorderObject;
	
	/**
	 * Return the cube length of any World.
	 */
	@Basic @Immutable
	public static int getCubeLength()
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
	 * 			The x coordinate of the cube.
	 * @param 	y
	 * 			The y coordinate of the cube.
	 * @param 	z
	 * 			The z coordinate of the cube.
	 */
	public int getCubeType(int x, int y, int z)
	{
		return getTerrain()[x][y][z];
	}
	
	/**
	 * Check whether the given value is a valid value for any cube.
	 * 
	 * @param 	value
	 * 			The value to check.
	 * @return	True if and only if the given value is between 0 and 3 (inclusive).
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
	 * @throws	IllegalArgumentException
	 * 			The given cube is out of bounds.
	 * @throws	IllegalArgumentException
	 * 			The given value is invalid for any cube.
	 * @post	The cubeType of the given cube is set to the given value.
	 */
	public void setCubeType(int x, int y, int z, int value) throws IllegalArgumentException
	{
		if (!isBetweenBoundaries(x, y, z))
			throw new IllegalArgumentException("The given coordinates are out of bounds.");
		if (!isValidCubeType(value))
			throw new IllegalArgumentException("The given value is invalid for any cube.");
		this.terrain[x][y][z] = value;
	}
	
	/**
	 * Constant registering the default cube type, which is air.
	 */
	private static final int DEFAULT_CUBE_TYPE = 0;

	/**
	 * Get all workshops that are in this World.
	 * 
	 * @return	The resulting list is effective.
	 * @return	The number of elements in the resulting list is equal to the number of workshops in this World.
	 * @return	Each element of the resulting set equals a position of a workshop in this World.
	 */
	public List<int[]> getAllWorkshops()
	{
		return new ArrayList<int[]>(workshops);
	}
	
	/**
	 * Check whether this World can add the given position of a workshops to its list of workshops.
	 * 
	 * @param 	workshop
	 * 			The position to check.
	 * @return	True if and only if the cube type of the given position (cube) is equal to 3.
	 */
	@Raw
	private boolean canAddAsWorkshop(int[] workshop)
	{
		return getCubeType(workshop[0], workshop[1], workshop[2]) == 3;
	}
	
	/**
	 * Add the given workshop position to the list of workshops.
	 * 
	 * @param	workshop
	 * 			The workshop position to add.
	 * @post	The number of workshop positions in this World is incremented by 1.
	 * @post	This World has the given workshop position as one of its workshop positions.
	 * @throws	IllegalArgumentException
	 * 			This World cannot add the given position to its workshop positions.
	 */
	@Raw
	private void addWorkshop(int[] workshop) throws IllegalArgumentException
	{
		if (!canAddAsWorkshop(workshop))
			throw new IllegalArgumentException("Given position isn't a workshop!");
		workshops.add(workshop);
	}
	
	/**
	 * A variable referencing the workshop in this World.
	 */
	private final List<int[]> workshops = new ArrayList<int[]>();
	
	/**
	 * Return a list containing all cubes that aren't connected to a border of this World.
	 * 
	 * @note	We are currently using the reference to the notConnected list, because we have no use of a copy list.
	 * 			If we would iterate over the list, we would be obligated to return a copy.
	 */
	@Model @Raw @Basic
	private List<int[]> getAllNotConnected()
	{
		return this.notConnected;
	}
	
	/**
	 * Get the cube at the given index from the list of cubes that aren't connected to a border of this World.
	 * 
	 * @param 	index
	 * 			The index for the cube to be retrieved.
	 */
	@Model @Raw @Basic
	private int[] getNotConnectedCubeAt(int index)
	{
		return this.notConnected.get(index);
	}
	
	/**
	 * Add the given collection to the list of cubes that aren't connected to a border.
	 * 
	 * @param 	collection
	 * 			The collection to add.
	 * @post	Each element that is an element of the given collection is an element of the list of cubes that aren't
	 * 			connected to a border.
	 */
	@Model @Raw
	private void addAllNotConnected(Collection<? extends int[]> collection)
	{
		notConnected.addAll(collection);
	}
	
	/**
	 * Remove the given cube from the list of cubes that aren't connected to a border.
	 * 
	 * @param 	cube
	 * 			The cube to remove.
	 * @post	The list of cubes no longer contains the given cube as one of its cubes.
	 */
	@Model @Raw
	private void removeNotConnected(int[] cube)
	{
		notConnected.remove(cube);
	}
	
	/**
	 * Variable referencing a list collecting all cubes that aren't connected to a border.
	 * 
	 * @invar	The referenced list is effective.
	 * @invar	Each cube referenced in the list is effective.
	 */
	private final List<int[]> notConnected = new ArrayList<>();
	
	/**
	 * Return the amount of {@link Unit}s of this World.
	 */
	@Basic @Raw
	public int getNbOfUnits() 
	{
		return units.size();
	}	
	
	/**
	 * Check whether this World has the given {@link Unit} as one of its {@link Unit}s.
	 * 
	 * @param 	unit
	 * 			The {@link Unit} to check.
	 * @return	True if and only if the given Unit is registered as one of the Units of this World.
	 */
	@Raw
	public boolean hasAsUnit(@Raw Unit unit)
	{
		return units.contains(unit);
	}
	
	/**
	 * Check whether this World can have the given {@link Unit} as one of its {@link Unit}s.
	 * 
	 * @param 	unit
	 * 			The {@link Unit} to check.
	 * @return	True if and only if the given Unit is effective and the current amount of Units is lower 
	 * 			than the maximum amount of Units.
	 */
	@Raw
	public boolean canHaveAsUnit(@Raw Unit unit)
	{
		return (unit != null) && unit.isAlive();
	}
	
	/**
	 * Add the given {@link Unit} to this World.
	 * 
	 * @param 	unit
	 * 			The {@link Unit} to add to this World.
	 * @throws	IllegalArgumentException
	 * 			This World cannot have the given Unit as one of its Units.
	 * @throws	IllegalStateException
	 * 			The maximum amount of Units has been reached.
	 * @post	The number of Units of this World is incremented by 1.
	 * @post	This World has the given Unit as one of its Units.
	 */
	@Raw
	public void addUnit(@Raw Unit unit) throws IllegalArgumentException, IllegalStateException
	{
		if (!canHaveAsUnit(unit))
			throw new IllegalArgumentException("This World cannot have the given Unit as one of its Units.");
		if ((getNbOfUnits() >= MAX_AMOUNT_OF_UNITS))
			throw new IllegalStateException("The maximum amount of Units has been reached.");
		units.add(unit);
	}
	
	/**
	 * Check whether this World can remove the given {@link Unit} from its {@link Unit}s.
	 * 
	 * @param 	unit
	 * 			The {@link Unit} to check.
	 * @return	True if and only if the given Unit is effective, if this World has the given Unit as one of its Units and if the 
	 * 			given Unit is dead.
	 */
	@Raw
	public boolean canRemoveAsUnit(@Raw Unit unit)
	{
		return (unit != null) && hasAsUnit(unit) && (!unit.isAlive());
	}
	
	/**
	 * Remove the given {@link Unit} from this World.
	 * 
	 * @param 	unit
	 * 			The {@link Unit} to remove.		
	 * @throws	IllegalArgumentException
	 * 			This World cannot remove the given Unit from its Units.
	 * @post	The number of Units of this World is decremented by 1.
	 * @post	This World no longer has the given Unit as one of its Units.
	 */
	@Raw
	public void removeUnit(@Raw Unit unit) throws IllegalArgumentException
	{
		if (!canRemoveAsUnit(unit))
			throw new IllegalArgumentException("The given Unit cannot be removed from this World's Units.");
		units.remove(unit);
	}
	
	/**
	 * Check whether this World has proper {@link Unit}s attached to it.
	 * 
	 * @return	True if and only if this World can have each Unit as one of its Units and if each Unit
	 * 			references this World as the World to which they are attached.
	 */
	public boolean hasProperUnits()
	{
		for (Unit unit : getAllUnits())
			if (!canHaveAsUnit(unit) || unit.getWorld() != this)
				return false;
		return true;
	}
	
	/**
	 * Return a set containing all the {@link Unit}s of this World.
	 * 
	 * @return	The resulting set is effective.
	 * @return	The number of elements in the resulting set is equal to the number of Units attached to this World.
	 * @return	Each element of the resulting set equals a Unit that is attached to this World.
	 */
	@Basic @Raw
	public Set<Unit> getAllUnits()
	{
		return new HashSet<Unit>(units);
	}
	
	/**
	 * Spawn a new Unit with random initial attributes at a random position of the World. Initial positions
	 * shall be chosen such that a new Unit is occupying a passable cube at (x, y, z), where (x, y, z - 1) must be
	 * a solid cube or z = 0.
	 * 
	 * @return 	The new Unit that has been created.
	 * @throws	IllegalStateException
	 * 			The maximum amount of Units has been reached.
	 * @throws	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 * @throws 	ModelException // TODO FUCKING DELETE THIS
	 * 			The amount of Units exceeds the maximum amount of Units.
	 * @effect	A new Unit is created with a random strength, agility, toughness, weight, valid position, name, a default behaviour
	 * 			state and this World as its World.
	 * @effect	The newly created Unit is added to this World's Units.
	 * @effect	If the amount of Factions is lower than the maximum amount of Factions, a new Faction is created and this 
	 * 			Faction is added to this World's Factions.
	 * @effect	If a new Faction is added, this new Unit is added to the created Faction.
	 * 			Otherwise, the Unit is added to the Faction with the least amount of Units.
	 * @effect	Set the faction of this newly created Unit to the Faction that has added this Unit.
	 */
	public Unit spawnUnit(boolean enableDefaultBehaviour) throws IllegalStateException, IllegalArgumentException, ModelException
	{
			if (getNbOfUnits() >= MAX_AMOUNT_OF_UNITS)
				throw new IllegalStateException("Cannot spawn Unit, maximum amount of Units has been reached!");
			
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
			Faction theFaction;
			if (getNbOfFactions() < MAX_AMOUNT_OF_FACTIONS)
			{
				theFaction = new Faction(this);
				addFaction(theFaction);
			}
			else
			{
				Faction smallestFaction = null;
				for (Faction faction : getAllFactions())
				{
					if (smallestFaction == null)
						smallestFaction = faction;
					else if (smallestFaction.getNbOfUnits() > faction.getNbOfUnits())
						smallestFaction = faction;
				}
				theFaction = smallestFaction;
			}
			theFaction.addUnit(newUnit);
			newUnit.setFaction(theFaction);
			return newUnit;
    }
	
	/**
	 * Variable referencing a set of {@link Unit}s attached to this World.
	 * 
	 * @invar	The referenced set is effective.
	 * @invar	Each Unit registered in the referenced set is effective.
	 * @invar	No Unit is registered more than once in the referenced set.
	 */
	private final Set<Unit> units = new HashSet<Unit>();
	
	/**
	 * Constant registering the maximal amount of {@link Unit}s of any World.
	 */
	private final static int MAX_AMOUNT_OF_UNITS = 100;

	/**
	 * Return the amount of {@link Faction}s of this World.
	 */
	@Basic @Raw
	public int getNbOfFactions() 
	{
		return factions.size();
	}
	
	/**
	 * Check whether this World has the given {@link Faction} as one of its {@link Faction}s.
	 * 
	 * @param 	faction
	 * 			The {@link Faction} to check.
	 * @return	True if and only if the given Faction is registered as one of the Factions of this World.
	 */
	public boolean hasAsFaction(Faction faction)
	{
		return factions.contains(faction);
	}
	
	/**
	 * Check whether this World can have the given {@link Faction} as one of its {@link Faction}s.
	 * 
	 * @param 	faction
	 * 			The {@link Faction} to check.
	 * @return	True if and only if the given Faction is effective and if the number of Factions 
	 * 			is lower than the maximum amount of factions.
	 */
	@Raw
	public boolean canHaveAsFaction(Faction faction)
	{
		return (faction != null) && (!faction.isTerminated());
	}
	
	/**
	 * Add the given {@link Faction} to a set of active {@link Faction}s.
	 * 
	 * @param 	faction
	 * 			The {@link Faction} to add.
	 * @throws 	IllegalArgumentException
	 * 			This World cannot have the given Faction as one its Factions.
	 * @throws	IllegalStateException
	 * 			The maximum amount of Factions has been reached.
	 * @post	The number of Factions of this World is incremented by 1.
	 * @post	This World has the given Faction as one of its Factions.
	 */
	@Raw
	public void addFaction(Faction faction) throws IllegalArgumentException, IllegalStateException
	{
		if (!canHaveAsFaction(faction))
			throw new IllegalArgumentException("This World cannot have the given Faction as one of its Factions.");
		if (getNbOfFactions() >= MAX_AMOUNT_OF_FACTIONS)
			throw new IllegalStateException("The maximum amount of Factions has been reached.");
		factions.add(faction);
	}
	
	/**
	 * Check whether this World can remove the given {@link Faction} from its {@link Faction}s.
	 * 
	 * @param 	faction
	 * 			The {@link Faction} to check.
	 * @return	True if and only if the given Faction is effective and if it is terminated.
	 */
	@Raw
	public boolean canRemoveAsFaction(Faction faction)
	{
		return (faction != null) && (faction.isTerminated()) && hasAsFaction(faction);
	}
	
	/**
	 * Remove the given Faction from this World
	 * 
	 * @param 	faction
	 * 			The given Faction to remove.
	 * @throws	IllegalArgumentException
	 * 			This World cannot remove the given Faction from its Factions.
	 * @post	The number of Factions of this World is decremented by 1.
	 * @post	This World no longer has the given Faction as one of its Factions.
	 */
	@Raw
	public void removeFaction(Faction faction) throws IllegalArgumentException
	{
		if (!canRemoveAsFaction(faction))
			throw new IllegalArgumentException("This World cannot remove the given Faction from its Factions");
		factions.remove(faction);
	}
	
	/**
	 * Return a reference to the set collecting all {@link Faction}s of this World.
	 */
	@Basic @Raw
	public Set<Faction> getAllFactions()
	{
		return this.factions;
	}
	
	/**
	 * Check whether this World has proper {@link Faction}s attached to it.
	 * 
	 * @return	True if and only if this World can have each Faction as one of its Factions and if each Faction refernces this World 
	 * 			as its World.
	 */
	public boolean hasProperFactions()
	{
		for (Faction faction : getAllFactions())
		{
			if (!canHaveAsFaction(faction))
				return false;
			if (faction.getWorld() != this)
				return false;
		}
		return true;
	}
	
	/**
	 * Variable referencing a set of {@link Faction}s of this World.
	 * 
	 * @invar	The referenced set is effective.
	 * @invar	Each Faction registered in the referenced set is effective.
	 * @invar	No Faction is registered more than once in the referenced set.
	 */
	private Set<Faction> factions = new HashSet<Faction>();
	
	/**
	 * Constant registering the maximum amount of {@link Faction}s of this World.
	 */
	private static final int MAX_AMOUNT_OF_FACTIONS = 5;

	/**
	 * Return a set collecting all {@link Log}s in this World.
	 */
	@Basic @Raw
	public Set<Log> getAllLogs()
	{
		return new HashSet<Log>(logs);
	}
	
	/**
	 * Return the amount of {@link Log}s in this World.
	 */
	@Basic @Raw
	public int getNbOfLogs()
	{
		return logs.size();
	}
	
	/**
	 * Check whether this World has the given {@link Log} as one of its {@link Log}s.
	 * 
	 * @param 	log
	 * 			The {@link Log} to check.
	 * @return	True if and only if the given Log is registered as one of the Logs of this World.
	 */
	public boolean hasAsLog(Log log)
	{
		return logs.contains(log);
	}
	
	/**
	 * Check whether the given {@link WorldObject} is a valid {@link WorldObject} for any World.
	 * 
	 * @param 	object
	 * 			The {@link WorldObject} to check.
	 * @return	True if and only if the given WorldObject is effective and if the given WorldObject isn't terminated
	 */
	public boolean canHaveAsWorldObject(WorldObject object)
	{
		return (object != null) && (!object.isTerminated());
	}
	
	/**
	 * Add the given {@link WorldObject} (which is either a {@link Log} or {@link Boulder}) to their corresponding sets of this World.
	 * 
	 * @param 	object
	 * 			The {@link WorldObject} to add.
	 * @throws	IllegalArgumentException
	 * 			This World cannot have the given WorldObject as one of its logs or boulders.
	 * @post	If the given object is an instance of the Log class, then the World has the given object as one of its Logs.
	 * @post	If the given object is an instance of the Log class, then the number of Logs in this World is incremented by 1.
	 * @post	If the given object is an instance of the Boulder class, then this World has the given object as one of its Boulders.
	 * @post	If the given object is an instance of the Bouldr class, then the number of Boulders in this World is incremented by 1.
	 */
	public void addWorldObject(WorldObject object)
	{
		if (!canHaveAsWorldObject(object))
			throw new IllegalArgumentException("This World cannot have the given WorldObject as one of its logs or boulders!");
		if (object instanceof Log)
			logs.add((Log) object);
		else if (object instanceof Boulder)
			boulders.add((Boulder) object);
	}
	
	/**
	 * Check whether this World can remove the given {@link Log} from its {@link Log}s.
	 * 
	 * @param 	log
	 * 			The {@link Log} to check.
	 * @return	True if and only if the given Log is valid, if the given Log is registered as one of the Logs of this World and if 
	 * 			the given Log is terminated.
	 */
	@Raw
	public boolean canRemoveAsLog(Log log)
	{
		return (log != null) && hasAsLog(log) && log.isTerminated();
	}
	
	/**
	 * Remove the given {@link Log} from this World's collection of {@link Log}s.
	 * 
	 * @param 	log
	 * 			The {@link Log} to remove.
	 * @throws	IllegalArgumentException
	 * 			This World cannot remove the given Log as one of its Logs.
	 * @post	The number of Logs in this World is decremented by 1.
	 * @post 	This World no longer has the given Log as one of its Logs.
	 */
	@Raw
	public void removeLog(Log log) throws IllegalArgumentException
	{
		if (!canRemoveAsLog(log))
			throw new IllegalArgumentException("This World cannot remove the given Log from its Logs.");
		logs.remove(log);
	}
	
	/**
	 * Check whether this World has proper {@link Log}s attached to it.
	 * 
	 * @return	True if and only if each Log of this World is a valid Log for any World and if each Log references this World 
	 * 			as the World to which they are attached.
	 */
	public boolean hasProperLogs()
	{
		for (Log log : getAllLogs())
		{
			if (!canHaveAsWorldObject(log))
				return false;
			if (log.getWorld() != this)
				return false;
		}
		return true;
	}
	
	/**
	 * Spawn a new {@link Log} with a random weight in a given cube in this World.
	 * 
	 * @param 	targetCube
	 * 			The given cube in which the new {@link Log} spawns.
	 * @throws 	IllegalArgumentException
	 * 			The given targetCube cannot be null.
	 * @throws	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 * @effect	If the given targetCube isn't passable, change the cube type to air.
	 * @effect	Initialize a new Log with the given targetCube, a random weight and this World as its World.
	 * @effect	Add this newly created Log to this World's collection of Logs.
	 */
	@Raw
	public void spawnLog(double[] targetCube) throws IllegalArgumentException
	{
		if (targetCube == null)
			throw new IllegalArgumentException("The targetCube cannot be null.");
		if (!isPassableCube((int) (targetCube[0] - getCubeLength() / 2.0), (int) (targetCube[1] - getCubeLength() / 2.0), (int) (targetCube[2] - getCubeLength() / 2.0)))
			changeCubeTypeAir(targetCube);
		Log log = new Log(targetCube, calculateRandomWeight(), this);
		addWorldObject(log);
	}
	
	/**
	 * Variable referencing a set of {@link Log}s of this World.
	 * 
	 * @invar	The referenced set is effective.
	 * @invar	Each Log registered in the referenced set is effective.
	 * @invar	No Log is registered more than once in the referenced set.
	 */
	private Set<Log> logs = new HashSet<Log>();
	
	/**
	 * Return a set collecting all {@link Boulder}s in this World.
	 */
	@Basic @Raw
	public Set<Boulder> getAllBoulders()
	{
		return new HashSet<Boulder>(boulders);
	}
	
	/**
	 * Return the amount of {@link Boulder}s in this World.
	 */
	@Basic @Raw
	public int getNbOfBoulders()
	{
		return boulders.size();
	}
	
	/**
	 * Check whether this World has the given {@link Boulder} as one of its {@link Boulder}s.
	 * 
	 * @param 	boulder
	 * 			The {@link Boulder} to check.
	 * @return	True if and only if the given Boulder is registered as on of the Boulders of this World.
	 */
	public boolean hasAsBoulder(Boulder boulder)
	{
		return boulders.contains(boulder);
	}
	
	/**
	 * Check whether this World can remove the given {@link Boulder} from its {@link Boulder}s.
	 * 
	 * @param 	boulder
	 * 			The {@link Boulder} to check.
	 * @return	True if and only if the given Boulder is valid, if this World has the given Boulder as one of its Boulders and
	 * 			if the given Boulder is terminated.
	 */
	@Raw
	public boolean canRemoveAsBoulder(Boulder boulder)
	{
		return (boulder != null) && hasAsBoulder(boulder) && boulder.isTerminated();
	}
	
	/**
	 * Remove the given {@link Boulder} from this World's collection of {@link Boulder}s.
	 * 
	 * @param 	boulder
	 * 			The {@link Boulder} to remove.
	 * @throws	IllegalArgumentException
	 * 			This World cannot remove the given Boulder from its Boulders.
	 * @post	The number of Boulders in this World is decremented by 1.
	 * @post 	This World no longer has the given Boulder as one of its Boulders.
	 */
	@Raw
	public void removeBoulder(Boulder boulder) throws IllegalArgumentException
	{
		if (!canRemoveAsBoulder(boulder))
			throw new IllegalArgumentException("This World cannot remove the given Boulder from its Boulders.");
		boulders.remove(boulder);
	}
	
	/**
	 * Check whether this World has proper {@link Boulder}s attached to it.
	 * 
	 * @return	True if and only if each Boulder of this World is a valid Boulder for any World and if each Boulder references this World
	 * 			as the World to which they are attached.
	 */
	public boolean hasProperBoulders()
	{
		for (Boulder boulder : getAllBoulders())
		{
			if (!canHaveAsWorldObject(boulder))
				return false;
			if (boulder.getWorld() != this)
				return false;
		}
		return true;
	}
	
	/**
	 * Spawn a {@link Boulder} in a given cube in this World.
	 * 
	 * @param 	targetCube
	 * 			The given cube in which the new {@link Boulder} spawns.	
	 * @throws 	IllegalArgumentException
	 * 			The given targetCube is ineffective.
	 * @throws	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 * @effect	If the given targetCube isn't passable, change the cube type to air.
	 * @effect	Initialize a new Boulder with the given targetCube, a random weight and this World as its World.
	 * @effect	Add this newly created Boulder to this World's collection of Boulders.
	 */
	public void spawnBoulder(double[] targetCube) throws IllegalArgumentException
	{
		if (targetCube == null)
			throw new IllegalArgumentException("Target cube cannot be ineffective!");
		if (!isPassableCube((int) (targetCube[0] - getCubeLength() / 2.0), (int) (targetCube[1] - getCubeLength() / 2.0), (int) (targetCube[2] - getCubeLength() / 2.0)))
			changeCubeTypeAir(targetCube);
		Boulder boulder = new Boulder(targetCube, calculateRandomWeight(), this);
		addWorldObject(boulder);
	}
	
	/**
	 * Variable referencing a set of {@link Boulder}s of this World.
	 * 
	 * @invar	The referenced set is effective.
	 * @invar	Each Boulder registered in the referenced set is effective.
	 * @invar	No Boulder is registered more than once in the referenced set.
	 */
	private Set<Boulder> boulders = new HashSet<Boulder>();
	
	/**
	 * Calculate a random weight.
	 * 
	 * @return	A random weight between 10 and 50.
	 */
	@Model @Raw
	private int calculateRandomWeight()
	{
		int min_weight = 10;
		return new Random().nextInt(51 - min_weight) + min_weight;
	}
	
	/**
	 * Change the cube type of the given targetCube to air.
	 * 
	 * @param 	targetCube
	 * 			The cube to change.
	 * @throws 	IllegalArgumentException
	 * 			A condition was violated or an error was thrown.
	 * @effect	Set the cube type of the given cube to 0 (air).
	 * @effect	Add all positions that aren't connected to any border thanks to the change of the cube to this World's collection of 
	 * 			cubes that aren't connected.
	 * @effect	Notify the model listener that the targetCube has changed in the terrain.
	 */
	@Model @Raw
	private void changeCubeTypeAir(double[] targetCube) throws IllegalArgumentException
	{
		int x = (int) (targetCube[0] - 0.5);
		int y = (int) (targetCube[1] - 0.5);
		int z = (int) (targetCube[2] - 0.5);
		
		setCubeType(x, y, z, 0);
		addAllNotConnected(connectedToBorderObject.changeSolidToPassable(x, y, z));
		getModelListener().notifyTerrainChanged(x, y, z);
	}
	
}
