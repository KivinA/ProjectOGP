package hillbillies.model.expressions;

import hillbillies.model.Task;
import hillbillies.part3.programs.SourceLocation;

/**
 * @author Kevin Algoet & Jeroen Depuydt
 * @version 0.1
 *
 */
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
