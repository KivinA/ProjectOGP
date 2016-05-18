package hillbillies.model.statements;

import hillbillies.model.Task;
import hillbillies.model.expressions.Expression;

public class Assignment implements Statement {
	private String variableName;
	private Expression value;
	
	public Assignment(String variableName, Expression value) {
		this.variableName = variableName;
		this.value = value;
	}
	
	@Override
	public void execute(Task task) {	
		
		if (!task.hasAsVariable(variableName))
			task.addVariable(variableName, value);
		else
			task.replaceValue(variableName, value);			
	}

}
