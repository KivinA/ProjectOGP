package hillbillies.model.expressions;

import hillbillies.model.Task;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class Disjunction extends BooleanExpression {

	public Disjunction(BooleanExpression left, BooleanExpression right) {
		this.left = left;
		this.right = right;
	}
	
	private BooleanExpression left, right;
	
	@Override
	public Boolean evaluate(Task task) {
		return (left.evaluate(task) || right.evaluate(task));
	}
}
