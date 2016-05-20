package hillbillies.model.statements;

import hillbillies.model.expressions.BooleanExpression;
import hillbillies.model.expressions.Expression;
import hillbillies.model.Task;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class While implements Statement {
	private BooleanExpression condition;
	private Statement body;
	
	public While(Expression condition, Statement body){
		this.condition = (BooleanExpression) condition;
		this.body = body;
	}
	
	@Override
	public void execute(Task task) {
		while (condition.evaluate(task)) {
			body.execute(task);
		}
		this.executed = true;
	}

	@Override
	public boolean isExecuted() {
		return this.executed;
	}
	
	private boolean executed = false;

}
