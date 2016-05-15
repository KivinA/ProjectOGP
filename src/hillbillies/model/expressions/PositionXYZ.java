package hillbillies.model.expressions;

import hillbillies.model.Task;
import hillbillies.part3.programs.SourceLocation;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class PositionXYZ extends PositionExpression {
	private int x;
	private int y;
	private int z;
	private SourceLocation sourceLocation;

	public PositionXYZ(int x, int y, int z, SourceLocation sourceLocation) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.sourceLocation = sourceLocation;
	}

	@Override
	public Integer[] evaluate(Task task) {
		return new Integer[] {x, y, z};
	}

}
