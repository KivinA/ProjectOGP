package hillbillies.model.statements;

import java.util.ArrayList;
import java.util.List;

import hillbillies.model.Task;

public class Sequence implements Statement{

	public Sequence(List<Statement> statements) {
		this.statements = new ArrayList<Statement>(statements);
	}
	
	public boolean isExecuted()
	{
		return executed;
	}
	
	private boolean executed = false;
	
	public boolean isEmpty()
	{
		return statements.isEmpty();
	}
	
	private final List<Statement> statements;
	
	@Override
	public void execute(Task task) {
		if (!isEmpty())
		{
			Statement nextStatement = statements.get(0);
			nextStatement.execute(task);
			System.out.println("Executing " + statements.get(0).getClass().getSimpleName() + " statement...");
			if (nextStatement.isExecuted())
				statements.remove(0);
		} 	
		else
			this.executed = true;
	}
}
