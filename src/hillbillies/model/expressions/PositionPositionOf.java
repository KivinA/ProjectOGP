package hillbillies.model.expressions;

import hillbillies.model.Task;
import hillbillies.part3.programs.SourceLocation;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class PositionPositionOf extends PositionExpression {

	private Expression unit;
	private SourceLocation sourceLocation;

	public PositionPositionOf(Expression unit, SourceLocation sourceLocation) {
		this.unit = unit;
		this.sourceLocation = sourceLocation;
	}

	@Override
	public Integer[] evaluate(Task task) {
		int[] coord = task.getUnit().getCubeCoordinates();
		Integer[] coordinates = {coord[0], coord[1], coord[2]};
		return coordinates;
	}

}
