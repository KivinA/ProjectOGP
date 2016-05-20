package hillbillies.model.expressions;

import hillbillies.model.Task;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class PositionHere extends PositionExpression {

	public PositionHere() {
		
	}

	@Override
	public Integer[] evaluate(Task task) {
		int[] coord = task.getUnit().getCubeCoordinates();
		Integer[] coordinates = {coord[0], coord[1], coord[2]};
		return coordinates;
	}

}
