/**
 * @author Loo Shu Wen
 *
 */
public class SimpleLinkedList<E> implements ListADT<E>{

	private DblListnode<E> head;
	private DblListnode<E> tail;
	private int numItems;

	public SimpleLinkedList(){
		this.head = new DblListnode<E>(null);
		this.numItems = 0;
		this.tail = head;
	}

	/* (non-Javadoc)
	 * @see ListADT#add(java.lang.Object)
	 */
	@Override
	public void add(E item) {
		// TODO Auto-generated method stub
		//input validation
		if (item == null)
		{
			throw new IllegalArgumentException();
		}

		//add the new node at the end
		DblListnode<E> temp = new DblListnode<E>(item, tail, null);

		//link the tail of the list to the new node
		tail.setNext(temp);

		//update tail
		tail = tail.getNext();

		//increment numItems
		numItems++;
	}

	/* (non-Javadoc)
	 * @see ListADT#add(int, java.lang.Object)
	 */
	@Override
	public void add(int pos, E item) {
		//input validation
		if (item == null)
		{
			throw new IllegalArgumentException();
		}
		if (pos < 0 || pos > numItems)
		{
			throw new IndexOutOfBoundsException();
		}

		DblListnode<E> curr = head;
		DblListnode<E> temp = new DblListnode<E>(item);
		DblListnode<E> after;

		//not adding at the end of the list
		if (pos != numItems){
			//find the node before the pos
			for (int i = 0; i < pos; i++)
			{
				curr = curr.getNext();
			}
			//linking
			after = curr.getNext();
			curr.setNext(temp);
			temp.setNext(after);
			temp.setPrev(curr);
			if(after != null){
				after.setPrev(temp);
			}
		}
		// adding the new node at the end of the list
		else
		{
			//linking
			tail.setNext(temp);
			temp.setPrev(tail);
			tail = tail.getNext();
		}

		//increment numItems
		numItems++;

	}

	/* (non-Javadoc)
	 * @see ListADT#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(E item) {
		//input validation
		if (item == null)
		{
			throw new IllegalArgumentException();
		}

		DblListnode<E> curr = head;
		for (int i = 0; i < numItems; i++)
		{
			curr = curr.getNext();
			if (curr.getData().equals(item))
			{
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see ListADT#get(int)
	 */
	@Override
	public E get(int pos) {
		//input validation
		if (pos < 0 || pos >= numItems)
		{
			throw new IndexOutOfBoundsException();
		}

		//create a curr reference
		DblListnode<E> curr = head;

		//find the node at position pos
		for (int i = 0; i < pos + 1; i ++)
		{
			curr = curr.getNext();
		}
		//return the data in the node
		return curr.getData();
	}

	/* (non-Javadoc)
	 * @see ListADT#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		if (numItems == 0)
		{
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see ListADT#remove(int)
	 */
	@Override
	public E remove(int pos) {
		//input validation
		if (pos < 0 || pos >= numItems)
		{
			throw new IndexOutOfBoundsException();
		}

		DblListnode<E> curr = head;
		DblListnode<E> temp;
		DblListnode<E> after;
		
		//removing at the end of the list
		if (pos == numItems -1 )
		{
			temp = tail;
			tail = tail.getPrev();
			tail.setNext(null);
			temp.setPrev(null);
		}
		//not removing at the end of the list
		else{
			//find the location before the node to be removed
			for (int i = 0; i < pos; i++)
			{
				curr = curr.getNext();
			}
			temp = curr.getNext();
			after = temp.getNext();
			
			//connect curr and after
			curr.setNext(after);
			after.setPrev(curr);
			
			//disconnect temp
			temp.setNext(null);
			temp.setPrev(null);
		}
		//decrement numItems
		numItems--;
		
		//return data from temp
		return temp.getData();
	}

	/* (non-Javadoc)
	 * @see ListADT#size()
	 */
	@Override
	public int size() {
		return numItems;
	}

//TODO: remove this
	public String toString(){
		
		if(numItems <= 0){
			return "[Nothing is here hahaha]";
		}
		
		DblListnode<E> curr = head;
		curr = curr.getNext();
		//get first item
		String data = "[" + (String) curr.getData() + "]"+ " ";
		while(curr.getNext() != null){
			curr = curr.getNext();
			data = data + "[" + (String) curr.getData() + "]" + " ";
		}
		
		return data;
	}

	private boolean compareTweet(){
		
		
		return true;
	}
}
