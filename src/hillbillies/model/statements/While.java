package hillbillies.model.statements;

import hillbillies.model.expressions.BooleanExpression;
import hillbillies.model.expressions.Expression;
import hillbillies.model.Task;
import hillbillies.part3.programs.SourceLocation;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class While implements Statement {
	private BooleanExpression condition;
	private Statement body;
	private SourceLocation sourceLocation;
	
	public While(Expression condition, Statement body, SourceLocation sourceLocation){
		this.condition = (BooleanExpression) condition;
		this.body = body;
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public void execute(Task task) {
		while (condition.evaluate(task)) {
			body.execute(task);
		}
	}

}
