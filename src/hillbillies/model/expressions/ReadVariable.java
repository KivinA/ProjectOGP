package hillbillies.model.expressions;

import hillbillies.model.Task;

public class ReadVariable implements Expression{

	public ReadVariable(String variableName) {
		name = variableName;
	}
	
	private final String name;
	
	@Override
	public Expression evaluate(Task task) {
		return task.getVariableValue(name);
	}

	
}
