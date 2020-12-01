
public class Node {

	private Readers element;
	private Node next;
	
	public Node(Readers element) {
		this.element = element;
		this.next = null;
	}

	public Readers getElement() {
		return element;
	}

	public void setElement(Readers element) {
		this.element = element;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
	
	@Override 
	public String toString() {
		return this.element.toString();
	}
	
}
