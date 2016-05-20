package hillbillies.model.expressions;

import hillbillies.model.Task;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class PositionPositionOf<T extends UnitExpression> extends PositionExpression {
	
	public PositionPositionOf(T unitE) {
		this.unitE = unitE;
	}
	
	private T unitE;

	@Override
	public Integer[] evaluate(Task task) {
		int[] coord = unitE.evaluate(task).getCubeCoordinates();
		Integer[] coordinates = {coord[0], coord[1], coord[2]};
		return coordinates;
	}

}
