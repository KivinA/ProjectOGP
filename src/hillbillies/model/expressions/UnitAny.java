package hillbillies.model.expressions;

import hillbillies.model.*;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class UnitAny extends UnitExpression {
	
	public UnitAny() {
		
	}
	
	@Override
	public Unit evaluate(Task task) {
		Unit result = null;
		World world = task.getUnit().getWorld();
		int[] thisCoordinates = task.getUnit().getCubeCoordinates();
		int dsum = world.getNbCubesX() + world.getNbCubesY();
		int resultdz = world.getNbCubesZ();
		
		for (Unit unit : world.getAllUnits())
		{
			if (unit != task.getUnit())
			{
				int[] coordinates = unit.getCubeCoordinates();
				int dz = Math.abs(thisCoordinates[2] - coordinates[2]);
				if (dz < resultdz)
				{
					result = unit;
					dsum = sumXY(thisCoordinates, coordinates);
					resultdz = dz;
				}
				else if (dz == resultdz)
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
