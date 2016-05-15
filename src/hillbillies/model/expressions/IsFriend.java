package hillbillies.model.expressions;

import hillbillies.model.*;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class IsFriend<T extends UnitExpression> extends BooleanExpression {

	public IsFriend(T unitE) {
		this.unitE = unitE;
	}
	
	T unitE;
	@Override
	public Boolean evaluate(Task task) {
		Unit friend = unitE.evaluate(task);
		Unit unit = task.getUnit();
		return (friend.getFaction() == unit.getFaction());
	}

}
