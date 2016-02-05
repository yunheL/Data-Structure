///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  MapBenchMark.java
// File:             Entry.java
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
 * A map entry (key-value pair).
 * 
 * <p>Bugs: All detected bugs are resolved.
 *
 * @author Shu Wen Loo, Yunhe Liu
 */
public class Entry<K, V> {
	
	private K key; //holds the key
	private V value; //holds the value

	/**
	 * Constructs the map entry with the specified key and value.
	 */
	public Entry(K k, V v) {
		if(k == null || v == null){
			throw new NullPointerException();
		}

		key = k;
		value = v;
	}

	/**
	 * Returns the key corresponding to this entry.
	 *
	 * @return the key corresponding to this entry
	 */
	public K getKey() {
		return key;
	}

	/**
	 * Returns the value corresponding to this entry.
	 *
	 * @return the value corresponding to this entry
	 */
	public V getValue() {
		return value;
	}

	/**
	 * Replaces the value corresponding to this entry with the specified
	 * value.
	 *
	 * @param value new value to be stored in this entry
	 * @return old value corresponding to the entry
	 */
	public V setValue(V value) {
		if(value == null)
			throw new NullPointerException();
		
		//temporary variable to store old value.
		V temp = this.value;
		this.value = value;
		return temp; //return value.
	}

	//TODO: REMOVE
	public String toString(){
		return " Key: " + key.toString() + " Value: " + value.toString() + " ";
	}
}
