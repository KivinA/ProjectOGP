package hillbillies.model.expressions;

import hillbillies.model.Task;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class IsPassable<T extends PositionExpression> extends BooleanExpression {

	public IsPassable(T positionE) {
		this.positionE = positionE;
	}
	
	private T positionE;
	@Override
	public Boolean evaluate(Task task) {
		Integer[] pos = positionE.evaluate(task);
		return task.getUnit().getWorld().isPassableCube(pos[0], pos[1], pos[2]);
	}

}
