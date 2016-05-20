package hillbillies.model.statements;

import hillbillies.model.Task;
import hillbillies.model.expressions.PositionExpression;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class MoveTo<T extends PositionExpression> extends Action {	
	public MoveTo(T positionE) {
		this.positionE = positionE;
	}
	
	private T positionE;
	
	@Override
	public void execute(Task task) {
		Integer[] pos = positionE.evaluate(task);
		int[] cube = {pos[0], pos[1], pos[2]};
		task.getUnit().moveTo(cube);
	}

}
