///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  TrainSimulator.java
// File:             EmptyPlatformException.java
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
 * This class throws a EmptyPlatformException to signal that a platform is empty 
 * and cannot return a Train object.
 *
 * <p>Bugs: All detected bugs are resolved.
 *
 * @author Shu Wen Loo, Yunhe Liu
 */

public class EmptyPlatformException extends Exception{

	/**
	 * Override default constructor. Takes no arguments.
	 * */
	
	public EmptyPlatformException(){
		super();
	}
	
	
	/**
	 * Constructs an exception object with a message.
	 * */
	public EmptyPlatformException(String msg){
		super(msg);
	}
	
}
