package hillbillies.model.expressions;

import hillbillies.model.Task;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public interface Expression {
	public Object evaluate(Task task);
}