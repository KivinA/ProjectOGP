package hillbillies.model.expressions;

import hillbillies.model.Task;
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
		int[] coordinates = {coord[0], coord[1], coord[2]};
		Integer[] neighbour = {coord[0], coord[1], coord[2]};
		
		if(coordinates[0] == task.getUnit().getWorld().getNbCubesX())
			neighbour[0] -= 1;
		else
			neighbour[0] += 1;
		
		return neighbour;
	}

}
