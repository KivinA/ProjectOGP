package hillbillies.model.expressions;

import hillbillies.model.*;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class CarriesItem<T extends UnitExpression> extends BooleanExpression {

	public CarriesItem(T unit) {
		this.unitE = unit;
	}
	
	private T unitE;
	
	@Override
	public Boolean evaluate(Task task) {
		Unit unit = unitE.evaluate(task);
		return (unit.isCarryingBoulder()) || (unit.isCarryingLog());
	}

}
