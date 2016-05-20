package hillbillies.model.expressions;

import hillbillies.model.*;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class IsFriend<T extends UnitExpression> extends BooleanExpression { // Makes sure that IsFriend can only be used with subclasses of UnitExpression 
																			// (or UnitExpression itself, but that won't work since it's an abstract class).

	public IsFriend(T unitE) {
		this.unitE = unitE;
	}
	
	private T unitE;
	
	@Override
	public Boolean evaluate(Task task) {
		Unit friend = unitE.evaluate(task);
		Unit unit = task.getUnit();
		return (friend.getFaction() == unit.getFaction());
	}

}
