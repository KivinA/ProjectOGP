package hillbillies.model.expressions;

import hillbillies.model.Task;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public abstract class PositionExpression implements Expression {

	@Override
	public abstract Integer[] evaluate(Task task);

}
