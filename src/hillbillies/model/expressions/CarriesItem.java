package hillbillies.model.expressions;

import hillbillies.model.*;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class CarriesItem extends BooleanExpression {

	public CarriesItem(UnitExpression unit) {
		this.unitE = unit;
	}
	
	private UnitExpression unitE;
	@Override
	public Boolean evaluate(Task task) {
		Unit unit = unitE.evaluate(task);
		return (unit.isCarryingBoulder()) || (unit.isCarryingLog());
	}

}
