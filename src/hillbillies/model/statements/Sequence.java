package hillbillies.model.statements;

import java.util.ArrayList;
import java.util.List;

import hillbillies.model.Task;

public class Sequence implements Statement{

	public Sequence(List<Statement> statements) {
		this.statements = new ArrayList<Statement>(statements);
	}
	
	private final List<Statement> statements;
	
	@Override
	public void execute(Task task) {
		for (Statement statement : statements)
		{
			statement.execute(task);
		}
	}

}
