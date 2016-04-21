package hillbillies.model;

/**
 * A class of Tuples which can hold two different (or the same) data types.
 * @version	1.0
 * @author 	Kevin Algoet & Jeroen Depuydt
 *
 * @param 	<V1>
 * 			The data type of the first element.
 * @param 	<V2>
 * 			the data type of the second element.
 * 
 * @note	This class is based on the given code from this stackoverflow answer: 
 * 			http://stackoverflow.com/questions/521171/a-java-collection-of-value-pairs-tuples
 */
public class Tuple<V1, V2> {
	/**
	 * 
	 * @param value1
	 * @param value2
	 */
	public Tuple(V1 value1, V2 value2)
	{
		this.value1 = value1;
		this.value2 = value2;
	}
	
	/**
	 * 
	 * @return
	 */
	public V1 getFirstValue()
	{
		return this.value1;
	}
	
	/**
	 * 
	 * @return
	 */
	public V2 getSecondValue()
	{
		return this.value2;
	}
	
	/**
	 * 
	 */
	private final V1 value1;
	
	/**
	 * 
	 */
	private final V2 value2;
	
	
	@Override
	public int hashCode() { return value1.hashCode() ^ value2.hashCode(); }

	@Override
	public boolean equals(Object o) {
	    if (!(o instanceof Tuple)) return false;
	    @SuppressWarnings("unchecked")
		Tuple<V1,V2> pairo = (Tuple<V1,V2>) o;
	    return this.value1.equals(pairo.getFirstValue()) &&
	           this.value2.equals(pairo.getSecondValue());
	  }
}
