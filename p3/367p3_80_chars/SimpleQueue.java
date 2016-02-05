/**
 * This class consists of 3 parts, a coin toss simulator, a grade estimator
 * and a color challenge. The user selects one of these options from a menu. To
 * quit, the user selects the 'q' key.
 *
 * <p>Bugs: (no known bugs)
 *
 * @Shu Wen Loo
 */

/**
 * @author Loo Shu Wen
 *
 */
public class SimpleQueue<E> implements QueueADT<E> {

	private E[] items;
	private int numItems;
	private int capacity;
	private int front;
	private int rear;

	public SimpleQueue (int capacity){

		items = (E[]) (new Object[capacity]);
		this.capacity = capacity;
		numItems = 0;
		front = -1;
		rear = 0;
	}

	/* (non-Javadoc)
	 * @see QueueADT#enqueue(java.lang.Object)
	 */
	@Override
	public void enqueue(E item) throws FullQueueException {
		//input validation
		if (item == null)
		{
			throw new IllegalArgumentException();
		}
		if (numItems == capacity)
		{
			throw new FullQueueException();
		}
		
		items[rear] = item;
		//add at rear
		//if the list was empty
		if (front == -1){
			front = front + 1;
			rear = rear + 1;
		}
		//rear hasn't reach the end
		else if (rear < capacity -1 ) {
			rear = rear + 1;
		}
		//rear at the end
		else{
			rear = 0;
		}

		//increment numItems
		numItems++;
	}


	/* (non-Javadoc)
	 * @see QueueADT#dequeue()
	 */
	@Override
	public E dequeue() throws EmptyQueueException {
		//input validation
		if (numItems == 0)
		{
			throw new EmptyQueueException();
		}
		E toRet = items[front];
		items[front] = null;
		
		//dequeue at front
		//if only one item in list
		if (numItems == 1){
			front = -1;
			rear = 0;
		}
		//front is not at the end
		else if (front < capacity - 1) {
			front = front + 1;
		}
		//front at the end
		else{
			front = 0;
		}

		//decrement numItems
		numItems--;
		return toRet;
	}

	/* (non-Javadoc)
	 * @see QueueADT#peek()
	 */
	@Override
	public E peek() throws EmptyQueueException {
		//input validation
		if (numItems == 0)
		{
			throw new EmptyQueueException();
		}
		//returns items at front
		return items[front];
	}

	/* (non-Javadoc)
	 * @see QueueADT#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return numItems == 0;
	}

	/* (non-Javadoc)
	 * @see QueueADT#isFull()
	 */
	@Override
	public boolean isFull() {
		return numItems == capacity;
	}

}
