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
public class Work extends Action {	
	private PositionExpression position;
	private SourceLocation sourceLocation;

	public Work(Expression position, SourceLocation sourceLocation) {
		this.position = (PositionExpression)position;
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public void execute(Task task) {
		Integer[] pos = position.evaluate(task);
		task.getUnit().workAt(pos[0], pos[1], pos[2]);
	}

}
