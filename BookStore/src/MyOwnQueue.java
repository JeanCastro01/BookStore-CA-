
public class MyOwnQueue implements ArrayQueue {

	private Readers[] data;
	private int size;
	private int front;
	private int rear;
	private int capacity;
	
	public MyOwnQueue(Readers[] data, int size, int front, int rear, int capacity) {
		super();
		this.data = data;
		this.size = size;
		this.front = front;
		this.rear = rear;
		this.capacity = capacity;
	}


	

	@Override
	public boolean Enqueue(Readers newElement) {
		
		if(rear >= capacity - 1) {
			return false;
		}
		
		if(front == -1) {
			front++;
		}
		rear++;
		data[rear] = newElement;
		size++;		
		return true;
	}

	@Override
	public Readers Dequeue() {
		
		if(size == 0) {
			return null;
		}
		Readers toReturn = data[front];
		data[front] = null;
		front++;
		size--;
		return toReturn;
	}

	@Override
	public Readers First() {
		
		if(size == 0) {
			return null;
		}
		return data[front];
	}

	@Override
	public Readers Last() {
		if(size == 0) {
			return null;
		}
		return data[rear];
	}

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public boolean isEmpty() {

		if(size == 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		
		String toReturn = "[ ";
		
		for(int i = front; i <= rear; i++) {
			toReturn += data[i] + " ";
		}
		
		toReturn += "]";
		
		return toReturn;
	}

}
