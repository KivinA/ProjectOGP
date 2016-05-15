package hillbillies.model.expressions;

import hillbillies.model.*;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class IsEnemy extends BooleanExpression {

	public IsEnemy(UnitExpression unitE) {
		this.unitE = unitE;
	}
	
	private UnitExpression unitE;
	
	@Override
	public Boolean evaluate(Task task) {
		Unit enemy = unitE.evaluate(task);
		Unit thisUnit = task.getUnit();
		return (enemy.getFaction() != thisUnit.getFaction());
	}
}
