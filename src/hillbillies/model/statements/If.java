package hillbillies.model.statements;

import hillbillies.model.expressions.BooleanExpression;
import hillbillies.model.expressions.Expression;
import hillbillies.part3.programs.SourceLocation;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class If implements Statement {
	private BooleanExpression condition;
	private Statement ifBody;
	private Statement elseBody;
	
	public If(Expression condition, Statement ifBody, Statement elseBody, SourceLocation sourceLocation) {
		
	}

	@Override
	public void execute() {
		if (condition.evaluate())
			ifBody.execute();
		else
			elseBody.execute();
			
		
	}

}
