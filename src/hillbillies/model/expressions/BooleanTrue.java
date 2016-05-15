package hillbillies.model.expressions;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class BooleanTrue extends BooleanExpression {

	public BooleanTrue() {
		
	}
	
	@Override
	public Boolean evaluate() {
		return true;
	}

}
