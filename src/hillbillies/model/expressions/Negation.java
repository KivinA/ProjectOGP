package hillbillies.model.expressions;

import hillbillies.model.Task;
import hillbillies.part3.programs.SourceLocation;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class Negation extends BooleanExpression {
	private Expression expression;
	private SourceLocation sourceLocation;

	public Negation(Expression expression, SourceLocation sourceLocation) {
		this.expression = expression;
		this.sourceLocation = sourceLocation;
	}

	@Override
	public Boolean evaluate(Task task) {
		boolean expressionBoolean = (boolean) expression.evaluate(task);
		return !expressionBoolean;
	}
}
