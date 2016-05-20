package hillbillies.model.statements;

import hillbillies.model.Task;
import hillbillies.model.expressions.PositionExpression;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class Work<T extends PositionExpression> extends Action {	
	public Work(T positionE) {
		this.positionE = positionE;
	}
	
	T positionE;
	
	@Override
	public void execute(Task task) {
		Integer[] pos = positionE.evaluate(task);
		task.getUnit().workAt(pos[0], pos[1], pos[2]);
	}

}
