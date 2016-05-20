package hillbillies.model.expressions;

import hillbillies.model.Task;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class BooleanTrue extends BooleanExpression {

	public BooleanTrue() {
		
	}
	
	@Override
	public Boolean evaluate(Task task) {
		return true;
	}

}
