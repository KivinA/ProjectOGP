package hillbillies.model.expressions;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public abstract class PositionExpression implements Expression {

	@Override
	public abstract Integer[] evaluate();

}
