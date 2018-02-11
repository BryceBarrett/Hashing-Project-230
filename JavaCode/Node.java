 

/**
 * A node with only one pointer (i.e. to the next 
 * node in front of it) to be used in a singly 
 * linked list linear data structure
 * 
 * @author CSCI 230: Data Structures and Algorithms Fall 2016
 *
 * @param <AnyType>
 */
public class Node<AnyType extends Comparable> {
	
	// instance variables
	private AnyType data;
	private Node<AnyType> nextNode;
	
	/**
	 * 
	 * @param data
	 */
	public Node( AnyType data ) {
		
		setData( data );
		
	} // end constructor
	
	/**
	 * 
	 * @param data
	 */
	public void setData( AnyType data ) {
		
		this.data = data;
		
	} // end setData() method
	
	/**
	 * 
	 * @return
	 */
	public AnyType getData() {
		
		return data;
		
	} // end getData() method
	
	/**
	 * 
	 * @param nextNode
	 */
	public void setNextNode( Node<AnyType> nextNode ) {
		
		this.nextNode = nextNode;
		
	} // end setNextNode() method
	
	/**
	 * 
	 * @return
	 */
	public Node<AnyType> getNextNode() {
		
		return nextNode;
		
	} // end getNextNode() method
	
	
	/**
	 * 
	 */
	public String toString() {
		
		if ( getNextNode() != null ) {
		
			return String.format("%s -> %s\n", getData().toString(), getNextNode().getData().toString() );
		
		} else {
			
			return String.format( "%s -> NULL\n", getData().toString() );
			
		}
		
	} // end toString() method

} // end Node class definition
