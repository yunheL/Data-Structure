///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  MovieFlix
// File:             MovieIterator.java
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
// Credits:          Jim's notes :D
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.*;

/**
 * Iterator class for the MovieDatabase class.
 *
 * <p>Bugs: none known
 *
 * @author Shu Wen Loo, Yunhe Liu
 */
public class MovieIterator<E> implements Iterator<E>{
	
	//reference to the ArrayList object.
	private ArrayList<E> myList;
	//keeps the current position of the iterator.
	private int currPos;
	
	/**
	 * Constructor
	 * */
	public MovieIterator(ArrayList<E> list){
		
		currPos = 0;
		myList = list;
		
	}

	/**
	 * returns true if pointing to an object.
	 * 
	 * @param 
	 * @return boolean
	 * */
	public boolean hasNext() {
		return currPos < myList.size();
	}

	/**
	 * returns the current object and advances the pointer to the next object.
	 * 
	 * @param 
	 * @return E: a generic object
	 * 
	 * */
	public E next() {
		
		if (!hasNext()) 
			throw new NoSuchElementException();
		
		E result = myList.get(currPos);
		currPos++;
		return result;
	}

	/** 
	 * Overridden method.
	 * 
	 * @param
	 * @return
	 * 
	 */
	public void remove() {
		throw new UnsupportedOperationException();
		
	}
	
	
}
