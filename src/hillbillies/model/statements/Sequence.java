package hillbillies.model.statements;

import java.util.ArrayList;
import java.util.List;

import hillbillies.model.Task;

public class Sequence implements Statement{

	public Sequence(List<Statement> statements) {
		this.statements = new ArrayList<Statement>(statements);
	}
	
	public boolean isEmpty()
	{
		return statements.isEmpty();
	}
	
	private final List<Statement> statements;
	
	@Override
	public void execute(Task task) {
		if (statements.size() > 0)
		{
			statements.get(0).execute(task);
			System.out.println("Executing " + statements.get(0).getClass().getSimpleName() + " statement...");
			
			statements.remove(0);
		}
		
//		for (Statement statement : statements)
//		{
//			statement.execute(task);
//		}
	}
}
