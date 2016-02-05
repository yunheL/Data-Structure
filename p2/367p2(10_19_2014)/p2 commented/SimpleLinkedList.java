///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Twitter.java
// File:             SimpleLinkedList.java
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
 * This class including the constructor and methods for SimpleLinkedList
 * which is a double-linked linkedlist with tail reference.
 *
 * <p>Bugs: All bugs has been resolved so far.
 *
 * @author Shu Wen Loo, Yunhe Liu
 */

public class SimpleLinkedList<E> implements ListADT<E>{

	//pointer referencing to the beginning of the list
	private DblListnode<E> head;

	//pointer referencing to the end of the node
	private DblListnode<E> tail;

	//number of the nodes in the list
	private int numItems;

	//constructor
	public SimpleLinkedList(){
		this.head = new DblListnode<E>(null);
		this.numItems = 0;
		this.tail = head;
	}


	/**
	 * Add a node with item as its data at the end of the list.
	 *
	 * @param (item) data of the node to be add in
	 */
	@Override
	public void add(E item) {
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

	/**
	 * Add a node with item as its data at the end of the list at specified
	 * postion pos.
	 *
	 * @param (int pos) (postion the node to be add at)
	 * @param (E item) (data of the node to be added in)
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

		//put curr reference at the beginning of the node
		DblListnode<E> curr = head;
		//create a new node with item as its data
		DblListnode<E> temp = new DblListnode<E>(item);
		//create the reference used to point the original item
		//at position pos
		DblListnode<E> after;

		//not adding at the end of the list
		if (pos != numItems){
			//find the node before the pos
			for (int i = 0; i < pos; i++)
			{
				curr = curr.getNext();
			}
			//point to the node originally at position pos before
			//linking
			after = curr.getNext();
			
			//linking
			curr.setNext(temp);
			temp.setNext(after);
			temp.setPrev(curr);
			//if not adding at the end
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

	/**
	 * returns true if at least one of the nodes in the list has
	 * item as its data
	 *
	 * @param (item) (the information to be checked whether it is data of
	 * at least one node)
	 * @return (returns true if at least one of the nodes in the list has
	 * item as its data, otherwise return false)
	 */
	@Override
	public boolean contains(E item) {
		//input validation
		if (item == null)
		{
			throw new IllegalArgumentException();
		}
		
		//put curr reference at the beginning of the node
		DblListnode<E> curr = head;
		//loop through the list to see look for node with item as its data
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

	/**
	 * return the data of the node at position pos
	 *
	 * @param (pos) (the postion of the node whose data need to be returned)
	 * @return (data of the node at position pos)
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

	/**
	 * returns true if the list is empty
	 *
	 * @return (return true if the list is empty, otherwise
	 * return false)
	 */
	@Override
	public boolean isEmpty() {
		//if numItems is 0, return true
		if (numItems == 0)
		{
			return true;
		}
		return false;
	}

	/**
	 * remove the node at postion pos	
	 *
	 * @param (pos) (position of the node to be removed)
	 * @return (data of the item within the removed node)
	 */
	@Override
	public E remove(int pos) {
		//input validation
		if (pos < 0 || pos >= numItems)
		{
			throw new IndexOutOfBoundsException();
		}
		
		//put curr reference at the beginning of the node
		DblListnode<E> curr = head;
		
		//create a new node with item as its data
		DblListnode<E> temp;
		
		//create the reference used to point the original item
		//at position pos
		DblListnode<E> after;

		//removing at the end of the list
		if (pos == numItems -1 )
		{
			temp = tail;
			//linking
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

	/**
	 * returns the size of the list
	 *
	 * @return (return the size of the list)
	 */
	@Override
	public int size() {
		return numItems;
	}

//	//TESTING METHOD (Commented, not submitted as a extra method)
//	public String toString(){
//
//		if(numItems <= 0){
//			return "[Nothing is here hahaha]";
//		}
//
//		DblListnode<E> curr = head;
//		curr = curr.getNext();
//		//get first item
//		String data = "[" + (String) curr.getData() + "]"+ " ";
//		while(curr.getNext() != null){
//			curr = curr.getNext();
//			data = data + "[" + (String) curr.getData() + "]" + " ";
//		}
//
//		return data;
//	}
}
