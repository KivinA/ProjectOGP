package hillbillies.model.expressions;

public abstract class BooleanExpression implements Expression {

	@Override
	public abstract Boolean evaluate();

}
