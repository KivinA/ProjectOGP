package hillbillies.model.expressions;

import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class UnitThis extends UnitExpression {
	private SourceLocation sourceLocation;

	public UnitThis(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public Unit evaluate(Task task) {
		return task.getUnit();
	}

}
