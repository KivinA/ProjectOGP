package hillbillies.model.expressions;

import hillbillies.model.Task;
import hillbillies.model.Unit;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class UnitThis extends UnitExpression {

	public UnitThis() {

	}
	
	@Override
	public Unit evaluate(Task task) {
		return task.getUnit();
	}

}
