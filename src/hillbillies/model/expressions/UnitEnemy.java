package hillbillies.model.expressions;

import java.util.HashSet;
import java.util.Set;

import hillbillies.model.Faction;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.World;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class UnitEnemy extends UnitExpression {

	public UnitEnemy() {
		
	}
	
	@Override
	public Unit evaluate(Task task) {
		Unit result = null;
		World world = task.getUnit().getWorld();
		int[] thisCoordinates = task.getUnit().getCubeCoordinates();
		int dsum = world.getNbCubesX() + world.getNbCubesY();
		int resultdz = world.getNbCubesZ();
		Faction thisFaction = task.getUnit().getFaction();
		Set<Unit> allUnits = new HashSet<Unit>();
		
		for (Faction faction : world.getAllFactions())
		{
			if (faction != thisFaction)
				allUnits.addAll(faction.getAllUnits());
		}
		
		for (Unit unit : allUnits)
		{
			if (unit != task.getUnit())
			{
				int[] coordinates = unit.getCubeCoordinates();
				int dz = Math.abs(thisCoordinates[2] - coordinates[2]);
				System.out.println("The difference between z-lvls of original and current Unit: " + dz);
				System.out.println("The current difference from the result: " + resultdz);
				if (dz < resultdz)
				{
					result = unit;
					dsum = sumXY(thisCoordinates, coordinates);
					resultdz = dz;
				}
				else
				{
					int sum = sumXY(coordinates, thisCoordinates);
					if (sum < dsum)
					{
						result = unit;
						dsum = sum;
						resultdz = dz;
					}
				}
			}
		}
		return result;
	}

	private int sumXY(int[] coordinates1, int[] coordinates2)
	{
		int dx = Math.abs(coordinates1[0] - coordinates2[0]);
		int dy = Math.abs(coordinates1[1] - coordinates2[1]);
		return (dx + dy);
	}
}
