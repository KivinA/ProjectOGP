package hillbillies.model.expressions;

import hillbillies.model.*;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 * @note	Implemented deterministic (instead of non-deterministic, as suggested by the assignment).
 */
public class PositionBoulder extends PositionExpression {

	public PositionBoulder() {
		
	}
	
	@Override
	public Integer[] evaluate(Task task) {
		World world = task.getUnit().getWorld();
		int[] coordinatesUnit = task.getUnit().getCubeCoordinates();
		int resultdz = world.getNbCubesZ();
		int dsum = world.getNbCubesX() + world.getNbCubesY();
		Integer[] result = new Integer[3];
		
		for (Boulder boulder : world.getAllBoulders())
		{
			int[] cubeCoordinatesBoulder = World.getCubeCoordinates(boulder.getPosition());
			int dz = Math.abs(coordinatesUnit[2] - cubeCoordinatesBoulder[2]);
			if (dz < resultdz)
			{
				for (int i = 0; i < result.length; i++)
					result[i] = cubeCoordinatesBoulder[i];
				dsum = sumXY(coordinatesUnit, cubeCoordinatesBoulder);
				resultdz = dz;
			}
			else if (dz == resultdz)
			{
				int sum = sumXY(coordinatesUnit, cubeCoordinatesBoulder);
				if (sum < dsum)
				{
					for (int i = 0; i < result.length; i++)
						result[i] = cubeCoordinatesBoulder[i];
					dsum = sum;
					resultdz = dz;
				}
			}
		}
		return result;
	}
}
