package hillbillies.model.statements;

import hillbillies.model.expressions.BooleanExpression;
import hillbillies.model.expressions.Expression;
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
	public void execute() {
		while (condition.evaluate()) {
			body.execute();
		}
	}

}
