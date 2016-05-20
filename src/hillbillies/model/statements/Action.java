package hillbillies.model.statements;

import hillbillies.model.Task;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public abstract class Action implements Statement {

	@Override
	public abstract void execute(Task task);
	
	public boolean isExecuted()
	{
		return this.executed;
	}
	
	protected boolean executed = false;
}
