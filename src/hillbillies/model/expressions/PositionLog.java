package hillbillies.model.expressions;

import hillbillies.model.Task;
import hillbillies.part3.programs.SourceLocation;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class PositionLog extends PositionExpression {
	private SourceLocation sourceLocation;
	
	public PositionLog(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public Integer[] evaluate(Task task) {
		return null;
	}

}
