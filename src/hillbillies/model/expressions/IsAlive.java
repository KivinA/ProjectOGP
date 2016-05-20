package hillbillies.model.expressions;

import hillbillies.model.Task;
import hillbillies.model.Unit;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class IsAlive<T extends UnitExpression> extends BooleanExpression {
	
	public IsAlive(T unitE) {
		this.unitE = unitE;
	}
	
	private T unitE;
	
	@Override
	public Boolean evaluate(Task task) {
		Unit unit = unitE.evaluate(task);
		return unit.isAlive();
	}

}
