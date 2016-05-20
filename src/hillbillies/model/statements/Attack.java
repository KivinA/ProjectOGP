package hillbillies.model.statements;

import hillbillies.model.Task;
import hillbillies.model.expressions.UnitExpression;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class Attack<T extends UnitExpression> extends Action {

	public Attack(T unitE) {
		this.unitE = unitE;
	}
	
	private T unitE;
	
	@Override
	public void execute(Task task) {
		task.getUnit().attack(unitE.evaluate(task));
	}

}
