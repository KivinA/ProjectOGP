package hillbillies.model.expressions;

import hillbillies.model.Task;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class IsSolid<T extends PositionExpression> extends BooleanExpression {
	public IsSolid(T positionE) {
		this.positionE = positionE;
	}
	
	private T positionE;
	
	@Override
	public Boolean evaluate(Task task) {
		Integer[] pos = positionE.evaluate(task);
		return task.getUnit().getWorld().isSolidCube(pos[0], pos[1], pos[2]);
	}
}
