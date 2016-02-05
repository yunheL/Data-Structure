//////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  TrainSimulator.java
// File:             SimpleQueue.java
// Semester:         CS367 Fall 2014
//
// Author:           Shu Wen Loo
// CS Login:         loo
// Lecturer's Name:  Jim Skrentny
// Lab Section:      N/A
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If allowed, learn what PAIR-PROGRAMMING IS, 
//                   choose a partner wisely, and complete this section.
//
// Pair Partner:     Yunhe Liu
// CS Login:         yunhe
// Lecturer's Name:  Jim Skrentny
// Lab Section:      N/A
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          (list anyone who helped you write your program)
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This SimpleQueue class used array to construct the SimpleQueue object and
 * implements its methods.
 * 
 * <p>
 * Bugs: All detected bugs are resolved.
 * 
 * @author Shu Wen Loo, Yunhe Liu
 */

public class SimpleQueue<E> implements QueueADT<E> {

	// private fields
	// array that holds the items in the SimpleQueue
	private E[] items;
	// int indicate the number of items in the SimpleQueue
	private int numItems;
	// int indicate the capacity of the SimpleQueue
	private int capacity;
	// int indicate the first used position SimpleQueue
	private int front;
	// int indicate the first empty position SimpleQueue
	private int rear;

	/**
	 * Constructs an empty SimpleQueue
	 */
	public SimpleQueue(int capacity) {

		items = (E[]) (new Object[capacity]);
		this.capacity = capacity;
		numItems = 0;
		front = -1;
		rear = 0;
	}

	/**
	 * Add a item to the top of SimpleQueue
	 * 
	 * @param (E item) item to be added
	 * @throws FullQueueException
	 */
	@Override
	public void enqueue(E item) throws FullQueueException {
		// input validation
		if (item == null) {
			throw new IllegalArgumentException();
		}
		if (numItems == capacity) {
			throw new FullQueueException();
		}

		items[rear] = item;
		// add at rear
		// if the list was empty
		if (front == -1) {
			front = front + 1;
			rear = rear + 1;
		}
		// rear hasn't reach the end
		else if (rear < capacity - 1) {
			rear = rear + 1;
		}
		// rear at the end
		else {
			rear = 0;
		}

		// increment numItems
		numItems++;
	}

	/**
	 * Removes an item from the top of the SimpleQueue and returns it.
	 * 
	 * @throws EmptyQueueException
	 * @return the item that has been removed, returns
	 *  null if the SimpleQueue
	 *  is empty
	 */
	@Override
	public E dequeue() throws EmptyQueueException {
		// input validation
		if (numItems == 0) {
			throw new EmptyQueueException();
		}
		E toRet = items[front];
		items[front] = null;

		// dequeue at front
		// if only one item in list
		if (numItems == 1) {
			front = -1;
			rear = 0;
		}
		// front is not at the end
		else if (front < capacity - 1) {
			front = front + 1;
		}
		// front at the end
		else {
			front = 0;
		}

		// decrement numItems
		numItems--;
		return toRet;
	}

	/**
	 * Returns a item from the top of the SimepleQueue without removing it.
	 * 
	 * @throws EmptyQueueException
	 * @return the item at the top of the SimpleQueue returns null if the
	 * SimpleQueue is empty
	 */
	@Override
	public E peek() throws EmptyQueueException {
		// input validation
		if (numItems == 0) {
			throw new EmptyQueueException();
		}
		// returns items at front
		return items[front];
	}

	/**
	 * Returns true iff the SimpleQueue is empty.
	 * 
	 * @return Returns true iff the SimpleQueue is empty.
	 */
	@Override
	public boolean isEmpty() {
		return numItems == 0;
	}

	/**
	 * Returns true iff the SimpleQueue is full.
	 * 
	 * @return Returns true iff the SimpleQueue is full.
	 */
	@Override
	public boolean isFull() {
		return numItems == capacity;
	}

}
