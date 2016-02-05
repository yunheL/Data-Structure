///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  TrainSimulator.java
// File:             FullQueueException.java
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
 * This class throws a FullQueueException to signal that a queue is full 
 * and no objects may be added to it.
 *
 * <p>Bugs: All detected bugs are resolved.
 *
 * @author Shu Wen Loo, Yunhe Liu
 */
public class FullQueueException extends Exception {
	
	/**
	 * Override default constructor. Creates a new FullQueueException object.
	 * */
	public FullQueueException(){
		super();
	}
	
	/**
	 * Creates a new FullQueueException object with a message.
	 * */
	public FullQueueException(String msg){
		super(msg);
	}
}
