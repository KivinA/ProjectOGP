package hillbillies.model.expressions;

import hillbillies.model.*;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public abstract class UnitExpression implements Expression {

	@Override
	public abstract Boolean evaluate(Task task);
}
