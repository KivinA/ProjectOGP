package hillbillies.model.expressions;

import hillbillies.model.*;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class IsEnemy<T extends UnitExpression> extends BooleanExpression {

	public IsEnemy(T unitE) {
		this.unitE = unitE;
	}
	
	T unitE;
	
	@Override
	public Boolean evaluate(Task task) {
		Unit enemy = unitE.evaluate(task);
		Unit thisUnit = task.getUnit();
		return (enemy.getFaction() != thisUnit.getFaction());
	}
}
