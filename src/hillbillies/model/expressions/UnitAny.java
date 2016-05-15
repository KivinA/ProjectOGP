package hillbillies.model.expressions;

import hillbillies.model.*;
import hillbillies.part3.programs.SourceLocation;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class UnitAny extends UnitExpression {
	private SourceLocation sourceLocation;
	
	public UnitAny(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public Unit evaluate(Task task) {
		return null;
	}

}
