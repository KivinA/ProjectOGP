package hillbillies.model.statements;

import hillbillies.model.Task;
import hillbillies.model.expressions.UnitExpression;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class Follow<T extends UnitExpression> extends Action {

	public Follow(T unitE) {
		this.unitE = unitE;
	}
	
	private T unitE;

	@Override
	public void execute(Task task) {
		int[] location = unitE.evaluate(task).getCubeCoordinates();
		task.getUnit().moveTo(location);
		
	}

}
