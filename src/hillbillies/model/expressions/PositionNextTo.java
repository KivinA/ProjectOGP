package hillbillies.model.expressions;

import java.util.Arrays;
import java.util.Random;
import hillbillies.model.Task;
import hillbillies.model.World;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class PositionNextTo<T extends PositionExpression> extends PositionExpression {
	public PositionNextTo(T positionE) {
		this.positionE = positionE;
	}
	
	private T positionE;
	
	@Override
	public Integer[] evaluate(Task task) {
		Integer[] coord = positionE.evaluate(task);
		Integer[] neighbour = Arrays.copyOf(coord, coord.length);
		World world = task.getUnit().getWorld();
		
		while (true) 
		{
			for (int i = 0; i < neighbour.length; i++)
				neighbour[i] += (new Random().nextInt(3) - 1);
			if (world.isBetweenBoundaries(neighbour[0], neighbour[1], neighbour[2]) && ! (Arrays.equals(neighbour, coord)))
				break;
		}
		return neighbour;
	}

}
