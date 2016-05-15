package hillbillies.model.expressions;

import hillbillies.model.Task;
import hillbillies.model.Unit;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class IsAlive extends BooleanExpression {
	
	public IsAlive(UnitExpression unitE) {
		this.unitE = unitE;
	}
	
	private UnitExpression unitE;
	
	@Override
	public Boolean evaluate(Task task) {
		Unit unit = unitE.evaluate(task);
		return unit.isAlive();
	}

}
