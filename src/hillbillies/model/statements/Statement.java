package hillbillies.model.statements;

import hillbillies.model.Task;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public interface Statement {
	public void execute(Task task);
}
