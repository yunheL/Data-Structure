///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  MapBenchMark.java
// File:             SimpleTreeMap.java
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
import java.util.List;
import java.util.TreeMap;

/**
 *
 * A map is a data structure that creates a key-value mapping. Keys are
 * unique in the map. That is, there cannot be more than one value associated
 * with a same key. However, two keys can map to a same value.
 *
 * The SimpleTreeMap takes two generic parameters, K
 * and V, standing for the types of keys and values respectively.
 * 
 * <p>Bugs: All detected bugs are resolved.
 *
 * @author Shu Wen Loo, Yunhe Liu
 *
 */
public class SimpleTreeMap<K extends Comparable<K> ,V> implements 
SimpleMapADT<K, V> {

	//Use Java's TreeMap as the underlying data structure. 
    private TreeMap<K,V> map;

    /**
     * Constructor for SimpleTreeMap. Constructs an empty RBT.
     * */
    SimpleTreeMap(){
		 map = new TreeMap<K,V>();
	}

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null
     * if this map contains no mapping for the key
     * @throws NullPointerException if the specified key is null
     */
    public V get(K key){
    	//throw exception is key is null.
    	if (key == null){
    		throw new NullPointerException();
    	}
    	//call Java's get method for the map.
    	return map.get(key);
    }

    /**
     * Associates the specified value with the specified key in this map.
     * Neither the key nor the value can be null. If the map
     * previously contained a mapping for the key, the old value is replaced.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with key, or
     * null if there was no mapping for key.
     * @throws NullPointerException if the key or value is null
     */
    public V put(K key, V value){
    	//truow exception if key or value is null.
    	if (key == null || value == null){
    		throw new NullPointerException();
    	}
    	
    	//call Java's put method for the map.
    	return map.put(key, value);
}
    /**
     * Removes the mapping for the specified key from this map if present. This
     * method does nothing if the key is not in the map.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with key, or null
     * if there was no mapping for key.
     * @throws NullPointerException if key is null
     */
    public V remove(K key){
    	//throw exception if key is null
    	if (key == null){
    		throw new NullPointerException();
    	}
    	
    	//Call Java's remove method for TreeMap.
    	return map.remove(key);

}
   /**
     * Returns the greatest key less than or equal to the given key, or null if there is no such key. 
	 * Throws NullPointerException if key is null. 
     * @param key key whose floor should be found
     * @return the largest key smaller than the one passed to it
     * @throws NullPointerException if key is null
     */
    public K floorKey(K key) {
    	//throw exception if key is null.
    	if (key == null){
    		throw new NullPointerException();
    	}
    	
    	//call Java's floorKey method.
    	return map.floorKey(key);
    }
    
    //TODO: remove toString method
    public String toString(){
    	return map.toString();
    }
}
