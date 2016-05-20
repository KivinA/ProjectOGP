package hillbillies.model.statements;

import hillbillies.model.expressions.Expression;
import hillbillies.model.Task;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class Print implements Statement {	
	public Print(Expression value) {
		this.value = value;
	}
	
	private Expression value;
	
	public boolean isExecuted()
	{
		return this.executed;
	}
	
	private boolean executed = false;
	
	@Override
	public void execute(Task task) {
		System.out.println(value);
		this.executed = true;
	}

}
