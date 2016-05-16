package hillbillies.model.expressions;

import hillbillies.model.*;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class PositionLog extends PositionExpression {
	
	public PositionLog() {
		
	}
	
	@Override
	public Integer[] evaluate(Task task) {
		World world = task.getUnit().getWorld();
		int[] coordinatesUnit = task.getUnit().getCubeCoordinates();
		int resultdz = world.getNbCubesZ();
		int dsum = world.getNbCubesX() + world.getNbCubesY();
		Integer[] result = new Integer[3];
		
		for (Log log : world.getAllLogs())
		{
			int[] cubeCoordinatesBoulder = World.getCubeCoordinates(log.getPosition());
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

	private int sumXY(int[] coordinates1, int[] coordinates2)
	{
		int dx = Math.abs(coordinates1[0] - coordinates2[0]);
		int dy = Math.abs(coordinates1[1] - coordinates2[1]);
		return (dx + dy);
	}
}
