package hillbillies.model.statements;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public abstract class Action implements Statement {

	@Override
	public abstract void execute();
}
