package hillbillies.model.expressions;

import java.util.Arrays;
import java.util.Random;
import hillbillies.model.Task;
import hillbillies.model.Unit;

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
		int[] neighbour = {coord[0], coord[1], coord[2]};
		Integer[] neighbourInt = {neighbour[0], neighbour[1], neighbour[2]};
		Unit unit = task.getUnit();
		while (true) 
		{
			for (int i = 0; i < neighbour.length; i++)
			{
				neighbour[i] += (new Random().nextInt(3) - 1);
				neighbourInt[i] = neighbour[i];
			}
			if ( unit.canHaveAsCubeCoordinates(neighbour) && ! (Arrays.equals(neighbourInt, coord)))
				break;
		}
		return neighbourInt;
	}

}
