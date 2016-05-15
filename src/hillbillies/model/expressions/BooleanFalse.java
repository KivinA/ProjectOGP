package hillbillies.model.expressions;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class BooleanFalse extends BooleanExpression {

	public BooleanFalse() {
	}
	
	@Override
	public Boolean evaluate() {
		return false;
	}

}
