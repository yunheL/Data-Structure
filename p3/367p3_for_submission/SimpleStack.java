//////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  TrainSimulator.java
// File:             SimpleStack.java
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

import java.util.EmptyStackException;
import java.util.*;

/**
 * This SimpleStack class used array to construct the SimpleStack object
 * and implements its methods.
 * 
 * <p>Bugs: All detected bugs are resolved.
 *
 * @author Shu Wen Loo, Yunhe Liu
 */

public class SimpleStack<E> implements StackADT<E> {

	//private fields
	//int indicate the capacity of the SimpleStack
	private E[] items;
	//int indicate the capacity of the SimpleStack
	private int numItems;
	//int indicate the capacity of the SimpleStack
	private int capacity;
	
	/**
	 * Constructs an empty SimpleStack
	 */
	public SimpleStack(int capacity){
		
		items = (E[]) (new Object[capacity]);
		this.capacity = capacity;
		numItems = 0;
		
	}
	
	/**
	 * Add a item to the top of SimpleStack
	 * 
	 * @param (E item) item to be added
	 * @throws FullStackException
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

	/**
	 * Removes an item from the top of the SimpleStack and
	 * returns it.
	 * 
	 * @throws EmptyStackException
	 * @return the item that has been removed, returns null
	 * if the SimpleStack is empty
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

	/**
	 * Returns a item from the top of the 
	 * SimepleStack without removing it.
	 * 
	 * @throws EmptyStackException
	 * @return the item at the top of the SimpleStack
	 * returns null if the SimpleStack is empty
	 */
	@Override
	public E peek() throws EmptyStackException {
		if (numItems == 0)
		{
			throw new EmptyStackException();
		}
		
		//return the item at the top of the
		//SimpleStack
		E toRet = items[numItems-1];
		
		return toRet;
	}

	/**
	 * Returns true iff the SimpleStack is empty.
	 * 
	 * @return Returns true iff the SimpleStack is empty.
	 */
	@Override
	public boolean isEmpty() {
		if (numItems != 0){
			return false;
		}
		return true;
	}

	/**
	 * Returns true iff the SimpleStack is full.
	 * 
	 * @return Returns true iff the SimpleStack is full.
	 */
	@Override
	public boolean isFull() {
		if (numItems != capacity){
			return false;
		}
		return true;
	}
}
