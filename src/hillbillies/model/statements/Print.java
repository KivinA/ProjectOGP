package hillbillies.model.statements;

import hillbillies.model.expressions.Expression;
import hillbillies.model.Task;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class Print implements Statement {
	private Expression value;
	
	public Print(Expression value) {
		this.value = value;
	}
	
	@Override
	public void execute(Task task) {
		System.out.println(value);
	}

}
