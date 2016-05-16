package hillbillies.model.expressions;

import java.util.Arrays;
import java.util.Random;
import hillbillies.model.Task;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class PositionNextTo extends PositionExpression {
	private PositionExpression position;
	private SourceLocation sourceLocation;

	public PositionNextTo(Expression position, SourceLocation sourceLocation) {
		this.position = (PositionExpression) position;
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public Integer[] evaluate(Task task) {
		Integer[] coord = position.evaluate(task);
//		int[] coordinates = {coord[0], coord[1], coord[2]};
		Integer[] neighbour = {coord[0], coord[1], coord[2]};
		
		/*if(coordinates[0] == task.getUnit().getWorld().getNbCubesX())
			neighbour[0] -= 1;
		else
			neighbour[0] += 1;
		
		return neighbour;*/
		World world = task.getUnit().getWorld();
//		Random random = new Random();
		
		while (true) {
//			int randomValue1 = random.nextInt(3) - 1;
//			int randomValue2 = random.nextInt(3) - 1;
//			int randomValue3 = random.nextInt(3) - 1;
			
			for (int i = 0; i < neighbour.length; i++)
			{
				neighbour[i] += (new Random().nextInt(3) - 1);
			}
			
			if (world.isBetweenBoundaries(neighbour[0], neighbour[1], neighbour[2]) && ! (Arrays.equals(neighbour, coord)))
				break;
//			
//			if(coordinates[0] != task.getUnit().getWorld().getNbCubesX() && coordinates[1] != task.getUnit().getWorld().getNbCubesY() && coordinates[2] != task.getUnit().getWorld().getNbCubesZ()) {
//				neighbour[0] += randomValue1;
//				neighbour[1] += randomValue2;
//				neighbour[2] += randomValue3;
//			}
//			
//			if (neighbour != coord)
//				break;
		}
		
		return neighbour;
	}

}
