package hillbillies.model.expressions;

import hillbillies.model.Task;

public class PositionSelected extends PositionExpression {
	
	public PositionSelected()  {

	}
	
	@Override
	public Integer[] evaluate(Task task) {
		int[] selected = task.getSelected();
		Integer[] result = {selected[0], selected[1], selected[2]};
		return result;
	}

}
