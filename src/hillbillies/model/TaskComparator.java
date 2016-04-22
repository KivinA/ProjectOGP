package hillbillies.model;

import java.util.Comparator;

public class TaskComparator implements Comparator<Task>{
	public TaskComparator() 
	{
		
	}
	
	/**
	 * This compare method will make sure we can sort Tasks according to their priority in a descending order.
	 */
	@Override
	public int compare(Task t1, Task t2) 
	{
		if (t1.getPriority() > t2.getPriority())
			return -1;
		if (t1.getPriority() < t2.getPriority())
			return 1;
		return 0;
	}
}
