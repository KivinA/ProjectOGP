package hillbillies.model.expressions;

import hillbillies.model.Task;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class PositionXYZ extends PositionExpression {
	private int x;
	private int y;
	private int z;
	public PositionXYZ(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public Integer[] evaluate(Task task) {
		return new Integer[] {x, y, z};
	}

}
