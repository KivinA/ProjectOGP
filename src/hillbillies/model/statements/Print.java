package hillbillies.model.statements;

import hillbillies.model.expressions.Expression;
import hillbillies.model.Task;
import hillbillies.part3.programs.SourceLocation;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class Print implements Statement {
	private Expression value;
	private SourceLocation sourceLocation;
	
	public Print(Expression value, SourceLocation sourceLocation) {
		this.value = value;
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public void execute(Task task) {
		System.out.println(value);
	}

}
