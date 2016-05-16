package hillbillies.model.expressions;

import hillbillies.model.*;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
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
		//int dsum = world.getNbCubesX() + world.getNbCubesY() + world.getNbCubesZ();
		int dsum = world.getNbCubesX() + world.getNbCubesY();
		Faction faction = task.getUnit().getFaction();
		
		for (Unit unit : faction.getAllUnits())
		{
			if (unit != task.getUnit())
			{
				int[] coordinates = unit.getCubeCoordinates();
				int dx = Math.abs(coordinates[0] - thisCoordinates[0]);
				int dy = Math.abs(coordinates[1] - thisCoordinates[1]);
				//int dz = Math.abs(coordinates[2] - thisCoordinates[2]);
				//int sum = dx + dy + dz;
				int sum = dx + dy;
			
				if (sum < dsum)
				{
					result = unit;
					dsum = sum;
				}
			}
		}
		
		for (Unit unit: faction.getAllUnits())
		{
			if (unit != task.getUnit() && unit != result)
			{
				int[] coordinates = unit.getCubeCoordinates();
				int dx = Math.abs(coordinates[0] - thisCoordinates[0]);
				int dy = Math.abs(coordinates[1] - thisCoordinates[1]);
				int sum = dx + dy;
				
				if (sum == dsum)
				{
					if (coordinates[2] < result.getCubeCoordinates()[2])
						result = unit;
				}
			}
		}
		
		return result;
	}

}
