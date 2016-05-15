package hillbillies.model.statements;

import hillbillies.model.Task;
import hillbillies.model.expressions.Expression;
import hillbillies.model.expressions.PositionExpression;
import hillbillies.part3.programs.SourceLocation;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class MoveTo extends Action {
	private PositionExpression position;
	
	public MoveTo(Expression position, SourceLocation sourceLocation) {
		this.position = (PositionExpression) position;
	}
	
	@Override
	public void execute(Task task) {
		Integer[] pos = position.evaluate(task);
		int[] cube = {pos[0], pos[1], pos[2]};
		task.getUnit().moveTo(cube);
	}

}
