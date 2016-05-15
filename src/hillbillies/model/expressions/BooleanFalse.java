package hillbillies.model.expressions;

import hillbillies.model.Task;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class BooleanFalse extends BooleanExpression {

	public BooleanFalse() {
	}
	
	@Override
	public Boolean evaluate(Task task) {
		return false;
	}

}
