package hillbillies.model.expressions;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public abstract class UnitExpression implements Expression {

	@Override
	public abstract Boolean evaluate();
}
