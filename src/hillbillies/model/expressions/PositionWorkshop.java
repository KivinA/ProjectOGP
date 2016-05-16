package hillbillies.model.expressions;

import hillbillies.model.Task;
import hillbillies.model.World;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class PositionWorkshop extends PositionExpression {

	public PositionWorkshop() {
		
	}
	
	@Override
	public Integer[] evaluate(Task task) {
		World world = task.getUnit().getWorld();
		int[] coordinatesUnit = task.getUnit().getCubeCoordinates();
		int resultdz = world.getNbCubesZ();
		int dsum = world.getNbCubesX() + world.getNbCubesY();
		Integer[] result = new Integer[3];
		
		for (int[] position : world.getAllWorkshops())
		{
			int dz = Math.abs(coordinatesUnit[2] - position[2]);
			if (dz < resultdz)
			{
				for (int i = 0; i < result.length; i++)
					result[i] = position[i];
				dsum = sumXY(coordinatesUnit, position);
				resultdz = dz;
			}
			else if (dz == resultdz)
			{
				int sum = sumXY(coordinatesUnit, position);
				if (sum < dsum)
				{
					for (int i = 0; i < result.length; i++)
						result[i] = position[i];
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
