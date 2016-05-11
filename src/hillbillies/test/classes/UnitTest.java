package hillbillies.test.classes;

import static org.junit.Assert.*;
import org.junit.*;
import hillbillies.model.*;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class UnitTest {

	private static World world;
	private Unit unit;
	private final static int TYPE_ROCK = 1;
	private final static int TYPE_LOG = 2;
	private final static int TYPE_WORKSHOP = 3;
	private final static int DEFAULT_PROPERTY = 30;
	private final static int[] POSITION = { 1, 1, 1 };

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		int[][][] terrain = new int[3][3][3];
		terrain[1][1][0] = TYPE_ROCK;
		terrain[0][0][0] = TYPE_LOG;
		terrain[1][0][2] = TYPE_ROCK;
		terrain[2][2][2] = TYPE_WORKSHOP;
		terrain[2][2][1] = TYPE_LOG;

		world = new World(terrain, new DefaultTerrainChangeListener());
	}

	@Before
	public void setUp() throws Exception {
		unit = new Unit("Hillbilly", DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, POSITION,
				false, world);
		world.addUnit(unit);
	}

	@After
	public void tearDown() throws Exception {
		if (unit.isAlive())
			unit.die();
		world.removeUnit(unit);
	}

	@Test
	public void isValidInitialValue_TrueCase() {
		assertTrue(Unit.isValidInitialValue(25));
		assertTrue(Unit.isValidInitialValue(50));
		assertTrue(Unit.isValidInitialValue(75));
		assertTrue(Unit.isValidInitialValue(100));
	}

	@Test
	public void isValidInitialValue_FalseCase() {
		assertFalse(Unit.isValidInitialValue(10));
		assertFalse(Unit.isValidInitialValue(110));
	}

	@Test
	public void isValidPropertyValue_TrueCase() {
		assertTrue(Unit.isValidPropertyValue(1));
		assertTrue(Unit.isValidPropertyValue(15));
		assertTrue(Unit.isValidPropertyValue(125));
		assertTrue(Unit.isValidPropertyValue(150));
		assertTrue(Unit.isValidPropertyValue(175));
		assertTrue(Unit.isValidPropertyValue(200));
	}

	@Test
	public void isValidPropertyValue_FalseCase() {
		assertFalse(Unit.isValidPropertyValue(-10));
		assertFalse(Unit.isValidPropertyValue(0));
		assertFalse(Unit.isValidPropertyValue(201));
		assertFalse(Unit.isValidPropertyValue(215));
	}

	@Test
	public void isValidName_TrueCase() {
		assertTrue(Unit.isValidName("Hillbilly"));
		assertTrue(Unit.isValidName("Kevin"));
		assertTrue(Unit.isValidName("Jeroen Depuydt"));
		assertTrue(Unit.isValidName("James O' Haraha"));
		assertTrue(Unit.isValidName("HILLBILLY\""));
	}

	@Test
	public void isValidName_TooShortName() {
		assertFalse(Unit.isValidName("I"));
		assertFalse(Unit.isValidName(" "));
		assertFalse(Unit.isValidName(""));
	}

	@Test
	public void isValidName_NoUpperCase() {
		assertFalse(Unit.isValidName("hillbilly"));
		assertFalse(Unit.isValidName("\"hillbilly\""));
		assertFalse(Unit.isValidName("'Hillbilly'"));
	}

	@Test
	public void isValidName_IllegalCharacters() {
		assertFalse(Unit.isValidName("Hillbilly_"));
		assertFalse(Unit.isValidName("Hillbilly10"));
		assertFalse(Unit.isValidName("Hillbilly-Numb"));
		assertFalse(Unit.isValidName("Hillybilly*"));
	}

	@Test
	public void setName_LegalCase() {
		unit.setName("Kevin Algoet");
		assertEquals("Kevin Algoet", unit.getName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setName_IllegalCase() throws Exception {
		unit.setName("Hillbilly_1");
	}

	@Test
	public void setStrength_LegalCase() {
		unit.setStrength(100);
		assertEquals(100, unit.getStrength());
	}

	@Test
	public void setStrength_IllegalCase() {
		unit.setStrength(-10);
		assertEquals(30, unit.getStrength());
	}

	@Test
	public void setAgility_LegalCase() {
		unit.setAgility(150);
		assertEquals(150, unit.getAgility());
	}

	@Test
	public void setAgility_IllegalCase() {
		unit.setAgility(220);
		assertEquals(30, unit.getAgility());
	}

	@Test
	public void setToughness_LegalCase() {
		unit.setToughness(75);
		assertEquals(75, unit.getToughness());
	}

	@Test
	public void setToughness_IllegalCase() {
		unit.setToughness(300);
		assertEquals(30, unit.getToughness());
	}

	@Test
	public void canHaveAsWeight_TrueCase() {
		assertTrue(unit.canHaveAsWeight(150));
		assertTrue(unit.canHaveAsWeight((unit.getStrength() + unit.getAgility()) / 2));
	}

	@Test
	public void canHaveAsWeight_NonValidPropertyValue() {
		assertFalse(unit.canHaveAsWeight(300));
		assertFalse(unit.canHaveAsWeight(-10));
	}

	@Test
	public void canHaveAsWeight_PropertyValueBelowDefaultWeight() {
		assertFalse(unit.canHaveAsWeight(1));
		assertFalse(unit.canHaveAsWeight(25));
	}

	@Test
	public void setWeight_LegalCase() {
		unit.setWeight(175);
		assertEquals(175, unit.getWeight());
	}

	@Test
	public void setWeight_IllegalCase() {
		unit.setWeight(25);
		assertEquals(((unit.getStrength() + unit.getAgility()) / 2), unit.getWeight());
	}

	@Test
	public void isValidPoints_TrueCase() {
		assertTrue(Unit.isValidPoints(300));
		assertTrue(Unit.isValidPoints(10000000));
	}

	@Test
	public void isValidPoints_NegativeHitpoints() {
		assertFalse(Unit.isValidPoints(-50));
		assertFalse(Unit.isValidPoints(0));
	}

	@Test
	public void canHaveAsCurrentHitpoints_TrueCase() {
		int maxHitpoints = unit.getMaxHitpoints();
		assertTrue(unit.canHaveAsCurrentHitpoints(10));
		assertTrue(unit.canHaveAsCurrentHitpoints(maxHitpoints));
	}

	@Test
	public void canHaveAsCurrentHitpoints_NonValidHitpoints() {
		assertFalse(unit.canHaveAsCurrentHitpoints(-20));
		assertFalse(unit.canHaveAsCurrentHitpoints(0));
	}

	@Test
	public void canHaveAsCurrentHitpoints_AboveMaxHitpoints() {
		assertFalse(unit.canHaveAsCurrentHitpoints(unit.getMaxHitpoints() + 1));
	}

	@Test
	public void setCurrentHitpoints_LegalCase() {
		unit.setCurrentHitpoints(unit.getMaxHitpoints() - 1);
		assertEquals(unit.getMaxHitpoints() - 1, unit.getCurrentHitpoints());
	}

	@Test
	public void setCurrentHitpoints_NegativeHitpoints() {
		unit.setCurrentHitpoints(-10);
		assertEquals(0, unit.getCurrentHitpoints());
		assertFalse(unit.isAlive());
	}

	@Test(expected = AssertionError.class)
	public void setCurrentHitpoints_HitpointsAboveMax() throws Exception {
		unit.setCurrentHitpoints(unit.getMaxHitpoints() + 1);
	}

	@Test
	public void canHaveAsCurrentStaminapoints_TrueCase() {
		assertTrue(unit.canHaveAsCurrentStaminapoints(0));
		assertTrue(unit.canHaveAsCurrentStaminapoints(15));
		assertTrue(unit.canHaveAsCurrentStaminapoints(unit.getMaxStaminapoints()));
	}

	@Test
	public void canHaveAsCurrentStaminapoints_NegativePoints() {
		assertFalse(unit.canHaveAsCurrentStaminapoints(-10));
	}

	@Test
	public void canHaveAsCurrentStaminapoints_PointsAboveMaximum() {
		assertFalse(unit.canHaveAsCurrentStaminapoints(unit.getMaxStaminapoints() + 1));
	}

	@Test
	public void setCurrentStaminapoints_LegalCase() {
		unit.setCurrentStaminapoints(0);
		assertEquals(0, unit.getCurrentStaminapoints());
	}

	@Test(expected = AssertionError.class)
	public void setCurrentStaminapoints_IllegalCase() throws Exception {
		unit.setCurrentStaminapoints(-10);
	}

	@Test
	public void isValidOrientation_TrueCase() {
		assertTrue(Unit.isValidOrientation(Math.PI / 6));
		assertTrue(Unit.isValidOrientation(-Math.PI / 6));
	}

	@Test
	public void isValidOrientation_FalseCase() {
		assertFalse(Unit.isValidOrientation(2 * Math.PI));
		assertFalse(Unit.isValidOrientation(2 * -Math.PI));
	}

	@Test
	public void setOrientation_LegalCase() {
		unit.setOrientation(Math.PI);
		assertEquals(Math.PI, unit.getOrientation(), 0.001);
	}

	@Test
	public void setOrientation_IllegalCase() {
		unit.setOrientation(4 * Math.PI / 3);
		assertEquals(Math.PI / 2, unit.getOrientation(), 0.001);
	}

	@Test
	public void getCubeCoordinates_SingleCase() {
		assertArrayEquals(POSITION, unit.getCubeCoordinates());
	}

	@Test
	public void canUseAsCubeCoordinates_TrueCase() {
		int[] position = { 0, 0, 1 };
		assertTrue(unit.canUseAsCubeCoordinates(position));
	}

	@Test
	public void canUseAsCubeCoordinates_OutOfBoundaries() {
		int[] position = { 3, 1, 1 };
		assertFalse(unit.canUseAsCubeCoordinates(position));
	}

	@Test
	public void canUseAsCubeCoordinates_SolidCube() {
		int[] position = { 1, 1, 0 };
		assertFalse(unit.canUseAsCubeCoordinates(position));
	}

	@Test
	public void canHaveAsFallingState_TrueCase() {
		int[][][] terrain = new int[3][3][3];
		World theWorld = new World(terrain, new DefaultTerrainChangeListener());
		Unit theUnit = new Unit("Hillbiilly", DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY,
				POSITION, false, theWorld);
		assertTrue(theUnit.canHaveAsFallingState(true));
		assertTrue(unit.canHaveAsFallingState(false));
	}

	@Test
	public void canHaveAsFallingState_DeadUnit() {
		unit.die();
		assertTrue(unit.canHaveAsFallingState(false));
		assertFalse(unit.canHaveAsFallingState(true));
	}

	@Test
	public void canHaveAsFallingState_TrueButSolidCubeAvailable() {
		assertFalse(unit.canHaveAsFallingState(true));
	}

	@Test
	public void canHaveAsFallingState_FalseButNoSolidCubeAvailable() {
		int[][][] terrain = new int[3][3][3];
		World theWorld = new World(terrain, new DefaultTerrainChangeListener());
		Unit theUnit = new Unit("Hillbiilly", DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY,
				POSITION, false, theWorld);
		assertFalse(theUnit.canHaveAsFallingState(false));
	}

	@Test
	public void setFallingState_LegalCase() {
		unit.setFallingState(false);
		assertFalse(unit.isFalling());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setFallingState_llegalCase() throws Exception {
		unit.setFallingState(true);
	}

	@Test
	public void setPosition_NonFallingToNonFalling() {
		int[] coordinates = { 1, 1, 1 };
		double[] expectedPos = { 1.5, 1.5, 1.5 };
		unit.setPosition(coordinates);
		assertArrayEquals(expectedPos, unit.getPosition(), 0);
		assertFalse(unit.isFalling());
	}

	@Test
	public void setPosition_NonFallingToFalling() {
		int[][][] terrain = new int[3][3][3];
		World theWorld = new World(terrain, new DefaultTerrainChangeListener());
		Unit theUnit = new Unit("Hillbiilly", DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY,
				new int[] { 0, 0, 0 }, false, theWorld);
		int[] coordinates = { 0, 0, 2 };
		double[] expectedPos = { 0.5, 0.5, 2.5 };
		theUnit.setPosition(coordinates);
		assertArrayEquals(expectedPos, theUnit.getPosition(), 0);
		assertTrue(theUnit.isFalling());
	}

	@Test
	public void setPosition_AlreadyFallingToNonFalling() {
		int[][][] terrain = new int[3][3][3];
		World theWorld = new World(terrain, new DefaultTerrainChangeListener());
		Unit theUnit = new Unit("Hillbiilly", DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY,
				new int[] { 0, 0, 1 }, false, theWorld);
		int[] coordinates = {0, 0, 0};
		assertTrue(theUnit.isFalling());
		theUnit.setPosition(coordinates);
		assertFalse(theUnit.isFalling());
	}
	
	@Test
	public void setPosition_AlreadyFallingToFalling()
	{
		int[][][] terrain = new int[3][3][3];
		World theWorld = new World(terrain, new DefaultTerrainChangeListener());
		Unit theUnit = new Unit("Hillbiilly", DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY, DEFAULT_PROPERTY,
				new int[] { 0, 0, 1 }, false, theWorld);
		int[] coordinates = {0, 0, 1};
		assertTrue(theUnit.isFalling());
		theUnit.setPosition(coordinates);
		assertTrue(theUnit.isFalling());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setPosition_IllegalPosition() throws Exception {
		int[] coordinates = { 5, 5, 3 };
		unit.setPosition(coordinates);
	}

	@Test
	public void canHaveAsPositionAt_LegalCases()
	{
		assertTrue(unit.canHaveAsPositionAt(0, 2.5));
		assertTrue(unit.canHaveAsPositionAt(1, 2.5));
		assertTrue(unit.canHaveAsPositionAt(2, 2.5));
	}
	
	@Test
	public void canHaveAsPositionAt_InvalidIndex()
	{
		assertFalse(unit.canHaveAsPositionAt(-1, 1));
		assertFalse(unit.canHaveAsPositionAt(5, 2.5));
	}
	
	@Test
	public void canHaveAsPositionAt_InvalidValue()
	{
		assertFalse(unit.canHaveAsPositionAt(1, 5));
		assertFalse(unit.canHaveAsPositionAt(2, -1));
	}
	
	@Test
	public void setPositionAt_LegalCase()
	{
		double[] expectedPos = {2.5, 1.7, 0.7};
		unit.setPositionAt(0, 2.5);
		unit.setPositionAt(1, 1.7);
		unit.setPositionAt(2, 0.7);
		assertArrayEquals(expectedPos, unit.getPosition(), 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setPositionAt_IllegalCase() throws Exception
	{
		unit.setPositionAt(-1, 2.5);
	}
	
	@Test
	public void canHaveAsCurrentSpeed_TrueCase()
	{
		assertTrue(unit.canHaveAsCurrentSpeed(0));
		// In this case, both walking and sprint speed are zero, because the Unit isn't moving currently.
		// See later tests to see the current speed can also be equals to the walking and sprinting speed.
	}
	
	@Test
	public void canHaveAsCurrentSpeed_FalseCase()
	{
		assertFalse(unit.canHaveAsCurrentSpeed(103));
	}
	
	@Test
	public void setCurrentSpeed_LegalCase()
	{
		unit.setCurrentSpeed(0);
		assertEquals(0, unit.getCurrentSpeed(), 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setCurrentSpeed_IllegalCase() throws Exception
	{
		unit.setCurrentSpeed(-10);
	}
}
