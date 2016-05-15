package hillbillies.model.statements;

import hillbillies.model.Task;
import hillbillies.model.expressions.Expression;
import hillbillies.model.expressions.UnitExpression;
import hillbillies.part3.programs.SourceLocation;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class Attack extends Action {
	private UnitExpression unit;
	private SourceLocation sourceLocation;
	
	public Attack(Expression unit, SourceLocation sourceLocation) {
		this.unit = (UnitExpression) unit;
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public void execute(Task task) {
		task.getUnit().attack(unit.evaluate(task));
	}

}
