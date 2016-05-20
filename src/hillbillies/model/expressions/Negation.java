package hillbillies.model.expressions;

import hillbillies.model.Task;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class Negation extends BooleanExpression {
	private Expression expression;

	public Negation(Expression expression) {
		this.expression = expression;
	}

	@Override
	public Boolean evaluate(Task task) {
		boolean expressionBoolean = (boolean) expression.evaluate(task);
		return !expressionBoolean;
	}
}
