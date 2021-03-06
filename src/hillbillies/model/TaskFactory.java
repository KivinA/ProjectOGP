package hillbillies.model;

import java.util.ArrayList;
import java.util.List;
import hillbillies.model.expressions.*;
import hillbillies.model.statements.*;
import hillbillies.part3.programs.ITaskFactory;
import hillbillies.part3.programs.SourceLocation;

public class TaskFactory implements ITaskFactory<Expression, Statement, Task>
{

	@Override
	public List<Task> createTasks(String name, int priority, Statement activity, List<int[]> selectedCubes) {
		List<Task> tasks = new ArrayList<Task>();
		if (selectedCubes.size() > 0)
			for (int[] selected : selectedCubes)
			{
				Task task = new Task(name, priority, activity, selected);
				tasks.add(task);
			}
		else
		{
			Task onlyTask = new Task(name, priority, activity, null);
			tasks.add(onlyTask);
		}
		return tasks;
	}

	@Override
	public Statement createAssignment(String variableName, Expression value, SourceLocation sourceLocation) {
		return new Assignment(variableName, value);
	}

	@Override
	public Statement createWhile(Expression condition, Statement body, SourceLocation sourceLocation) {
		return new While(condition, body);
	}

	@Override
	public Statement createIf(Expression condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {
		return new If(condition, ifBody, elseBody);
	}

	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		return new Break();
	}

	@Override
	public Statement createPrint(Expression value, SourceLocation sourceLocation) {
		return new Print(value);
	}

	@Override
	public Statement createSequence(List<Statement> Statement, SourceLocation sourceLocation) {
		return new Sequence(Statement);
	}

	@Override
	public Statement createMoveTo(Expression position, SourceLocation sourceLocation) {
		if (!(position instanceof PositionExpression))
			throw new IllegalArgumentException("Wrong expression, a Position expression is expected at line" + sourceLocation.getLine()
			+ " and column " + sourceLocation.getColumn()+ "!");
		return new MoveTo<PositionExpression>((PositionExpression) position);
	}

	@Override
	public Statement createWork(Expression position, SourceLocation sourceLocation) {
		if (!(position instanceof PositionExpression))
			throw new IllegalArgumentException("Wrong expression, a Position expression is expected at line" + sourceLocation.getLine()
			+ " and column " + sourceLocation.getColumn()+ "!");
		return new Work<PositionExpression>((PositionExpression) position);
	}

	@Override
	public Statement createFollow(Expression unit, SourceLocation sourceLocation) {
		if (!(unit instanceof UnitExpression))
			throw new IllegalArgumentException("Wrong expression, a Unit expression is expected at line" + sourceLocation.getLine()
			+ " and column " + sourceLocation.getColumn()+ "!");
		return new Follow<UnitExpression>((UnitExpression) unit);
	}

	@Override
	public Statement createAttack(Expression unit, SourceLocation sourceLocation) {
		if (!(unit instanceof UnitExpression))
			throw new IllegalArgumentException("Wrong expression, a Unit expression is expected at line" + sourceLocation.getLine()
			+ " and column " + sourceLocation.getColumn()+ "!");
		return new Attack<UnitExpression>((UnitExpression) unit);
	}

	@Override
	public Expression createReadVariable(String variableName, SourceLocation sourceLocation) {
		return new ReadVariable(variableName);
	}

	@Override
	public Expression createIsSolid(Expression position, SourceLocation sourceLocation) {
		if (!(position instanceof PositionExpression))
			throw new IllegalArgumentException("Wrong expression, a Position expression is expected at line" + sourceLocation.getLine()
			+ " and column " + sourceLocation.getColumn()+ "!");
		return new IsSolid<PositionExpression>((PositionExpression) position);
	}

	@Override
	public Expression createIsPassable(Expression position, SourceLocation sourceLocation) {
		if (!(position instanceof PositionExpression))
			throw new IllegalArgumentException("Wrong expression, a Position expression is expected at line" + sourceLocation.getLine()
			+ " and column " + sourceLocation.getColumn()+ "!");
		return new IsPassable<PositionExpression>((PositionExpression) position);
	}

	@Override
	public Expression createIsFriend(Expression unit, SourceLocation sourceLocation) {
		if (!(unit instanceof UnitExpression))
			throw new IllegalArgumentException("Wrong expression, a Unit expression is expected at line" + sourceLocation.getLine()
			+ " and column " + sourceLocation.getColumn()+ "!");
		return new IsFriend<UnitExpression>((UnitExpression) unit);
	}

	@Override
	public Expression createIsEnemy(Expression unit, SourceLocation sourceLocation) {
		if(!(unit instanceof UnitExpression))
			throw new IllegalArgumentException("Wrong expression, a Unit expression is expected at line" + sourceLocation.getLine()
			+ " and column " + sourceLocation.getColumn()+ "!");
		return new IsEnemy<UnitExpression>((UnitExpression) unit);
	}

	@Override
	public Expression createIsAlive(Expression unit, SourceLocation sourceLocation) {
		if(!(unit instanceof UnitExpression))
			throw new IllegalArgumentException("Wrong expression, a Unit expression is expected at line" + sourceLocation.getLine()
			+ " and column " + sourceLocation.getColumn()+ "!");
		return new IsAlive<UnitExpression>((UnitExpression) unit);
	}

	@Override
	public Expression createCarriesItem(Expression unit, SourceLocation sourceLocation) {
		if(!(unit instanceof UnitExpression))
			throw new IllegalArgumentException("Wrong expression, a Unit expression is expected at line" + sourceLocation.getLine()
			+ " and column " + sourceLocation.getColumn()+ "!");
		return new CarriesItem<UnitExpression>((UnitExpression) unit);
	}

	@Override
	public Expression createNot(Expression expression, SourceLocation sourceLocation) {
		return new Negation(expression);
	}

	@Override
	public Expression createAnd(Expression left, Expression right, SourceLocation sourceLocation) {
		return new Conjunction((BooleanExpression) left, (BooleanExpression) right);
	}

	@Override
	public Expression createOr(Expression left, Expression right, SourceLocation sourceLocation) {
		return new Disjunction((BooleanExpression) left, (BooleanExpression) right);
	}

	@Override
	public Expression createHerePosition(SourceLocation sourceLocation) {
		return new PositionHere();
	}

	@Override
	public Expression createLogPosition(SourceLocation sourceLocation) {
		return new PositionLog();
	}

	@Override
	public Expression createBoulderPosition(SourceLocation sourceLocation) {
		return new PositionBoulder();
	}

	@Override
	public Expression createWorkshopPosition(SourceLocation sourceLocation) {
		return new PositionWorkshop();
	}

	@Override
	public Expression createSelectedPosition(SourceLocation sourceLocation) {
		return new PositionSelected();
	}

	@Override
	public Expression createNextToPosition(Expression position, SourceLocation sourceLocation) {
		return new PositionNextTo<PositionExpression>((PositionExpression) position);
	}

	@Override
	public Expression createLiteralPosition(int x, int y, int z, SourceLocation sourceLocation) {
		return new PositionXYZ(x, y, z);
	}

	@Override
	public Expression createThis(SourceLocation sourceLocation) {
		return new UnitThis();
	}

	@Override
	public Expression createFriend(SourceLocation sourceLocation) {
		return new UnitFriend();
	}

	@Override
	public Expression createEnemy(SourceLocation sourceLocation) {
		return new UnitEnemy();
	}

	@Override
	public Expression createAny(SourceLocation sourceLocation) {
		return new UnitAny();
	}

	@Override
	public Expression createTrue(SourceLocation sourceLocation) {
		return new BooleanTrue();
	}

	@Override
	public Expression createFalse(SourceLocation sourceLocation) {
		return new BooleanFalse();
	}

	@Override
	public Expression createPositionOf(Expression unit, SourceLocation sourceLocation) {
		if(!(unit instanceof UnitExpression))
			throw new IllegalArgumentException("Wrong expression, a Unit expression is expected at line" + sourceLocation.getLine()
			+ " and column " + sourceLocation.getColumn()+ "!");
		return new PositionPositionOf<UnitExpression>((UnitExpression) unit);
	}
	
}