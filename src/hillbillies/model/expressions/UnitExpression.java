package hillbillies.model.expressions;

import hillbillies.model.*;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public abstract class UnitExpression implements Expression {

	@Override
	public abstract Unit evaluate(Task task);
	
	protected int sumXY(int[] coordinates1, int[] coordinates2)
	{
		int dx = Math.abs(coordinates1[0] - coordinates2[0]);
		int dy = Math.abs(coordinates1[1] - coordinates2[1]);
		return (dx + dy);
	}
}
