
/**
 * this is my Node clas for the linkedlist
 * 
 * @author Amilcar
 *
 */
public class Node {

	private Readers element;
	private Node next;

	public Node(Readers element) {
		this.element = element;
		this.next = null;
	}

	/**
	 * this method return the reader element ( node ) in the linkedlist
	 * 
	 * @return
	 */
	public Readers getElement() {
		return element;
	}

	/**
	 * this method set the reader element in the linkedlist
	 * 
	 * @param element
	 */
	public void setElement(Readers element) {
		this.element = element;
	}

	/**
	 * this method is to get the next node element in the linkedlist
	 * 
	 * @return this next
	 */

	public Node getNext() {
		return next;
	}

	/**
	 * this method set the next node in the linkedlist
	 * 
	 * @param this next
	 */

	public void setNext(Node next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return this.element.toString();
	}

}
