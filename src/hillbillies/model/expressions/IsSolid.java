package hillbillies.model.expressions;

import hillbillies.model.Task;
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
	public Boolean evaluate(Task task) {
		Integer[] pos = position.evaluate(task);
		return task.getUnit().getWorld().isSolidCube(pos[0], pos[1], pos[2]);
	}
}
