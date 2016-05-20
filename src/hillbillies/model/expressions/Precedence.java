package hillbillies.model.expressions;

import hillbillies.model.Task;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class Precedence extends BooleanExpression {	// There is no create-method in TaskFactory, so we have no idea what to implement here.

	@Override
	public Boolean evaluate(Task task) {
		return null;
	}
}
