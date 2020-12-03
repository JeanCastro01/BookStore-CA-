
/**
 * this is mylikedlist class, responsiable to creat my queue for the book's
 * 
 * @author Amilcar
 *
 */

public class MyLinkedList {

	private Node first;
	private Node last;
	private int size;

	public MyLinkedList() {
		this.first = null;
		this.last = null;
		this.size = 0;
	}

	/**
	 * This methos is to add the first Reader in the book's queue
	 * 
	 * @param newElement
	 */

	public void addFirst(Readers newElement) {
		Node node = new Node(newElement);

		if (size == 0) {
			last = node;
		}
		node.setNext(first);
		first = node;
		size++;
	}

	/**
	 * this methos is to add the last reader in the book's queue
	 * 
	 * @param newElement
	 */

	public void addLast(Readers newElement) {

		Node node2 = new Node(newElement);
		if (size == 0) {
			first = node2;
			last = first;
		} else {
			last.setNext(node2);
			last = node2;
		}
		size++;

	}

	/**
	 * this method is to remove the first reader in the queue
	 * 
	 * @return this toreturn
	 */

	public Node removeFirst() {
		if (size == 0) {
			return null;
		}
		if (size == 1) {
			last = null;
		}
		Node toReturn = first;
		first = first.getNext();
		size--;
		return toReturn;
	}

	/**
	 * this method is to find any reader by any position in the queue
	 * 
	 * @param position
	 * @return this current position
	 */

	public Node findElementByPosition(int position) {
		if (size == 0 || position > size - 1) {
			return null;
		}
		Node current = first;
		int counter = 0;
		while (counter < position) {
			current = current.getNext();
			counter++;
		}
		return current;
	}

	/**
	 * this method is to remove the last reader from the book's queue
	 * 
	 * @return this toReturn
	 */

	public Node removeLast() {

		Node toReturn = last;
		if (size == 0) {
			return toReturn;
		}
		if (size == 1) {
			first = null;
			last = null;
			size--;
			return toReturn;
		}

		Node secondLast = findElementByPosition(size - 2);
		secondLast.setNext(null);
		last = secondLast;
		size--;
		return toReturn;

	}

	/**
	 * this method return the size of the queue
	 * 
	 * @return
	 */

	public int size() {
		return size;
	}

	/**
	 * this method return the node if is empty or not
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * this method
	 */
	@Override
	public String toString() {

		String toReturn = "[ ";
		Node current = first;
		while (current != null) {
			toReturn += current.toString() + " ";
			current = current.getNext();
		}
		toReturn += "]";
		return toReturn;

	}

}
