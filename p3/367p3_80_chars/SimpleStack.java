import java.util.EmptyStackException;
import java.util.*;
/**
 * @author Loo Shu Wen
 *
 */
public class SimpleStack<E> implements StackADT<E> {

	private E[] items;
	private int numItems;
	private int capacity;
	
	
	public SimpleStack(int capacity){
		
		items = (E[]) (new Object[capacity]);
		this.capacity = capacity;
		numItems = 0;
		
	}
	
	/* (non-Javadoc)
	 * @see StackADT#push(java.lang.Object)
	 */
	@Override
	public void push(E item) throws FullStackException {
		//input validation
		if (item == null)
		{
			throw new IllegalArgumentException();
		}
		if (numItems == capacity)
		{
			throw new FullStackException();
		}
		
		//add at the end
		items[numItems] = item;
		
		//increment numItems
		numItems++;
	}

	/* (non-Javadoc)
	 * @see StackADT#pop()
	 */
	@Override
	public E pop() throws EmptyStackException {
		if (numItems == 0){
			throw new EmptyStackException();
		}
		
		//remove at the end
		E toRet = items[numItems-1];
		items[numItems-1] = null;
		
		//decrement numItems
		numItems--;
		return toRet;
	}

	/* (non-Javadoc)
	 * @see StackADT#peek()
	 */
	@Override
	public E peek() throws EmptyStackException {
		if (numItems == 0)
		{
			throw new EmptyStackException();
		}
		
		//return the 
		E toRet = items[numItems-1];
		
		return toRet;
	}

	/* (non-Javadoc)
	 * @see StackADT#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		if (numItems != 0){
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see StackADT#isFull()
	 */
	@Override
	public boolean isFull() {
		if (numItems != capacity){
			return false;
		}
		return true;
	}
}
