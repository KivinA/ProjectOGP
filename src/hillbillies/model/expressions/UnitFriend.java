package hillbillies.model.expressions;

import hillbillies.model.*;

/**
 * @author 	Kevin Algoet & Jeroen Depuydt
 * @version	0.1
 *
 */
public class UnitFriend extends UnitExpression {

	public UnitFriend() {
		
	}
	
	@Override
	public Unit evaluate(Task task) {
		Unit result = null;
		World world = task.getUnit().getWorld();
		int[] thisCoordinates = task.getUnit().getCubeCoordinates();
		int dsum = world.getNbCubesX() + world.getNbCubesY();
		int resultdz = world.getNbCubesZ();
		Faction faction = task.getUnit().getFaction();
		
		for (Unit unit : faction.getAllUnits())
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
}
