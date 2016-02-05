//////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  TrainSimulator.java
// File:             Platform.java
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
 * This platform class used StackADT to construct the platform object
 * and implements its methods.
 * 
 * <p>Bugs: All detected bugs are resolved.
 *
 * @author Shu Wen Loo, Yunhe Liu
 */
public class Platform implements PlatformADT {

	//private fields
	//capacity of the platform
	private int capacity;
	//stack used to contain trains in the platform
	private StackADT<Train> platform;
	
	/**
	 * Constructs an empty platform
	 */
	public Platform (int capacity) {
		this.capacity = capacity;
		platform = new SimpleStack<Train>(capacity);
	}

	/**
	 * Add a train to the top of the stack of the platform
	 * 
	 * @param (Train item) train to be added
	 * @throws FullPlatformException
	 */
	public void put(Train item) throws FullPlatformException {
		//input validation
		if (item == null)
		{
			throw new IllegalArgumentException();
		}
		if (platform.isFull()){
			throw new FullPlatformException();
		}
		
		//add the item to the platform
		try {
			platform.push(item);
		}
		catch (FullStackException ex){
			System.out.println("FullStackException Caught.");
		}
	}

	/**
	 * Removes a Train from the top of the stack of
	 * the platform and returns it.
	 * 
	 * @throws EmptyPlatformException
	 * @return the train that has been removed, returns null
	 * if the platform is empty
	 */
	public Train get() throws EmptyPlatformException {
		//input validation
		if (platform.isEmpty()){
			throw new EmptyPlatformException();
		}
		
		//create the return object
		Train toRet = null;
		
		//remove the train at the top of the stack
		//of the platform
		try {
			toRet = platform.pop();
		}
		catch (EmptyStackException ex){
			System.out.println("EmptyStackException Caught.");
		}
		
		return toRet;
	}
	
	/**
	 * Returns a Train from the top of the stack of
	 * the platform without removing it.
	 * 
	 * @throws EmptyPlatformException
	 * @return the train at the top of the stack of the
	 * platform, returns null if the platform is empty
	 */
	@Override
	public Train check() throws EmptyPlatformException {
		if (platform.isEmpty()){
			throw new EmptyPlatformException();
		}
		//create the return object
		Train toRet = null;
		//get the train at the top of the stack of the
		//platform without removing it
		try {
			toRet = platform.peek();
		}
		catch (EmptyStackException ex){
			System.out.println("EmptyStackException Caught.");
		}
		
		return toRet;

	}

	/**
	 * Returns true iff the platform is empty.
	 * 
	 * @return Returns true iff the platform is empty.
	 */
	@Override
	public boolean isEmpty() {
		return platform.isEmpty();
	}

	/**
	 * Returns true iff the platform is full.
	 * 
	 * @return Returns true iff the platform is full.
	 */
	@Override
	public boolean isFull() {
		return platform.isFull();
	}
	
}
