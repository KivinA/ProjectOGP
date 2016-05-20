package hillbillies.model.statements;

import hillbillies.model.expressions.BooleanExpression;
import hillbillies.model.expressions.Expression;
import hillbillies.model.Task;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class If implements Statement {
	private BooleanExpression condition;
	private Statement ifBody;
	private Statement elseBody;
	
	public If(Expression condition, Statement ifBody, Statement elseBody) {
		this.condition = (BooleanExpression) condition;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}

	@Override
	public void execute(Task task) {
		if (condition.evaluate(task))
			ifBody.execute(task);
		else if (elseBody != null)
			elseBody.execute(task);
		
		if ( (ifBody.isExecuted()) || ( (elseBody != null) && elseBody.isExecuted() ) || ( (elseBody == null) && !condition.evaluate(task) ) )
			this.executed = true;
	}

	@Override
	public boolean isExecuted() {
		return this.executed;
	}

	private boolean executed = false;
}
