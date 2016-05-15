package hillbillies.model.expressions;

import hillbillies.part3.programs.SourceLocation;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class IsSolid extends BooleanExpression {
	private PositionExpression position;
	private SourceLocation sourceLocation;
	
	public IsSolid(Expression position, SourceLocation sourceLocation) {
		this.position = (PositionExpression) position;
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public Boolean evaluate() {
		return null;
	}

}
