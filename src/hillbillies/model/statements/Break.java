package hillbillies.model.statements;

import hillbillies.model.Task;
import hillbillies.part3.programs.SourceLocation;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
public class Break implements Statement {
	private SourceLocation sourceLocation;
	
	public Break(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	@Override
	public void execute(Task task) {
		
	}

}
