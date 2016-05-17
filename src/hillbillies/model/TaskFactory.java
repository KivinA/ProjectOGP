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
		for (int[] selected : selectedCubes)
		{
			Task task = new Task(name, priority, activity, selected);
			tasks.add(task);
		}
		return tasks;
	}

	@Override
	public Statement createAssignment(String variableName, Expression value, SourceLocation sourceLocation) {
		return new Assignment(variableName, value);
	}

	@Override
	public Statement createWhile(Expression condition, Statement body, SourceLocation sourceLocation) {
		return new While(condition, body, sourceLocation);
	}

	@Override
	public Statement createIf(Expression condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {
		return new If(condition, ifBody, elseBody, sourceLocation);
	}

	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		return new Break(sourceLocation);
	}

	@Override
	public Statement createPrint(Expression value, SourceLocation sourceLocation) {
		return new Print(value, sourceLocation);
	}

	@Override
	public Statement createSequence(List<Statement> Statement, SourceLocation sourceLocation) {
		return new Sequence(Statement);
	}

	@Override
	public Statement createMoveTo(Expression position, SourceLocation sourceLocation) {
		return new MoveTo(position, sourceLocation);
	}

	@Override
	public Statement createWork(Expression position, SourceLocation sourceLocation) {
		return new Work(position, sourceLocation);
	}

	@Override
	public Statement createFollow(Expression unit, SourceLocation sourceLocation) {
		return new Follow(unit, sourceLocation);
	}

	@Override
	public Statement createAttack(Expression unit, SourceLocation sourceLocation) {
		return new Attack(unit, sourceLocation);
	}

	@Override
	public Expression createReadVariable(String variableName, SourceLocation sourceLocation) {
		return new ReadVariable(variableName);
	}

	@Override
	public Expression createIsSolid(Expression position, SourceLocation sourceLocation) {
		return new IsSolid(position, sourceLocation);
	}

	@Override
	public Expression createIsPassable(Expression position, SourceLocation sourceLocation) {
		return new IsPassable(position, sourceLocation);
	}

	@Override
	public Expression createIsFriend(Expression unit, SourceLocation sourceLocation) {
		if (!(unit instanceof UnitExpression))
			throw new IllegalArgumentException("Wrong expression, a Unit expression is expected at line" + sourceLocation.getLine()
			+ "and column " + sourceLocation.getColumn()+ "!");
		return new IsFriend<UnitExpression>((UnitExpression) unit);
	}

	@Override
	public Expression createIsEnemy(Expression unit, SourceLocation sourceLocation) {
		return new IsEnemy((UnitExpression) unit);
	}

	@Override
	public Expression createIsAlive(Expression unit, SourceLocation sourceLocation) {
		return new IsAlive((UnitExpression) unit);
	}

	@Override
	public Expression createCarriesItem(Expression unit, SourceLocation sourceLocation) {
		return new CarriesItem((UnitExpression) unit);
	}

	@Override
	public Expression createNot(Expression expression, SourceLocation sourceLocation) {
		return new Negation(expression, sourceLocation);
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
		return new PositionHere(sourceLocation);
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
		return new PositionNextTo(position);
	}

	@Override
	public Expression createLiteralPosition(int x, int y, int z, SourceLocation sourceLocation) {
		return new PositionXYZ(x, y, z, sourceLocation);
	}

	@Override
	public Expression createThis(SourceLocation sourceLocation) {
		return new UnitThis(sourceLocation);
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
		return new PositionPositionOf(unit, sourceLocation);
	}
	
}