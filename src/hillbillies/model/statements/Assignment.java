package hillbillies.model.statements;

import hillbillies.model.Task;
import hillbillies.model.expressions.Expression;
import hillbillies.part3.programs.SourceLocation;

public class Assignment implements Statement {
	private String variableName;
	private Expression value;
	private SourceLocation sourceLocation;
	
	public Assignment(String variableName, Expression value, SourceLocation sourceLocation) {
		this.variableName = variableName;
		this.value = value;
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public void execute(Task task) {	
		
		if (!task.getTasks().containsKey(variableName))
			task.getTasks().put(variableName, value);
		else
			task.getTasks().replace(variableName, value);			
	}

}
