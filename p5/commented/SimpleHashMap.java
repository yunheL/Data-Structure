//////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  MapBenchmark.java
// File:             SimpleHashMap.java
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
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

/**
 * This SimpleHashMap class used Chained buckets implement to construct SimpleHash
 * Map and its methods.
 * 
 * <p>Bugs: All detected bugs are resolved.
 *
 * @author Shu Wen Loo, Yunhe Liu
 */

/**
 *
 * A map is a data structure that creates a key-value mapping. Keys are
 * unique in the map. That is, there cannot be more than one value associated
 * with a same key. However, two keys can map to a same value.
 *
 * The SimpleHashMap takes two generic parameters, K
 * and V, standing for the types of keys and values respectively.
 *
 */
public class SimpleHashMap<K extends Comparable<K>,V> implements SimpleMapADT<K , V> {

	//int array containing hashtable
	private int[] tableSizes = {11, 23, 47, 97, 197, 397, 797, 1597, 3203, 6421, 12853, 25717, 51437, 102877,
			205759, 411527, 823117, 1646237, 3292489, 6584983, 13169977, 26339969, 52679969, 105359939, 210719881, 
			421439783, 842879579, 1685759167};

	//double indicating load factor
	private double lf = 0.75;
	
	//chain of Buckets
	private LinkedList<Entry<K, V>>[] table;
	
	//indicating current table size
	private int currSize;
	
	//number of entries in our hashtable
	private int numEntries;
	
	//TODO: Decided which implement to use
	private ArrayList<Integer> usedHashInds;

	//constructor of empty HashMap
	public SimpleHashMap()
	{
		currSize = 0;
		numEntries = 0;
		table = (LinkedList<Entry<K, V>>[]) (new LinkedList[tableSizes[currSize]]) ;//(new Object[tableSizes[currSize]]);
		//table = new LinkedList<(Entry) (Object)>[];
		//table = (LinkedList<J>[]) (new LinkedList<Object>[tableSizes[currSize]]);
	} 
	
	/**
	* Takes in Key value and return its correspond hash Index.
	*
	* @param (k) (the key value to be hashed)
	* @return (int)(hash index)
	*/
	private int hash(K k) {
		//using Java hashCode function
		//return the generated hashCode mode by table size
		int ret = k.hashCode() % tableSizes[currSize];
		
		//eliminate negative return values by adding table size
		if (ret < 0)
			ret += tableSizes[currSize];
		return ret;
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

	public V get(K key) {
	
		//input validation
		if (key == null)
			throw new NullPointerException(); 

		//int variable stores hash Index
		int hashInd = hash(key);

		//return null if the corresponding bucket is empty
		if(table[hashInd] == null){
			return null;
		}

		//place the iterator at the hash table
		Iterator<Entry<K, V>> itr = table[hashInd].iterator();
		
		//variable recording the current entry
		Entry<K, V> currEntry;		
		
		//loop through the bucket
		while(itr.hasNext()){
			//update currEntry
			currEntry = itr.next();
			
			//find the value associate to the key and return
			if(currEntry.getKey().equals(key))
				return (V) (currEntry.getValue());
		}
		
		//return null if the key is not in the table
		return null;
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

	public V put(K key, V value) {
		//input validation
		if (key == null || value == null)
			throw new NullPointerException(); 

		//calculate the load factor after the current add
		double currlf = (numEntries+1)/table.length;

		//if the load factor after the current add is larger than 0.75
		if(currlf > 0.75){

			//TODO: Possible bug spot
			// increase the table size so that the hash function is modified.
			currSize++; 
			//call private method to expand the table
			table = expand(table);

		}
		
		//int variable stores hash Index
		int hashInd = hash(key);

		//if there is an empty index in the hashtable, i.e., null
		//just add the current entry to the list. 
		if(table[hashInd] == null){
			//create new LinkedList
			table[hashInd] = new LinkedList<Entry<K, V>>();
			//add to new LinkedList
			table[hashInd].add(new Entry<K, V>(key, value));

			//usedHashInds.add(hashInd);
			numEntries++;
			
			//return null since there were no mapping of the key
			return null;	
		}

		//if there is a list at the computed hashindex, iterate through the list
		//to see if the list contains the key.

		//TODO: Delete debug statement
		//System.out.println("Hash index not null.");
		
		//place the iterator at the hash table
		Iterator<Entry<K, V>> itr = table[hashInd].iterator();
		
		//variable used to contain the current Entry
		Entry<K, V> currEntry;
		
		//replace the old key with a new key if the key is in the hash index.
		
		//loop through the bucket
		while(itr.hasNext()){
			//update current entry
			currEntry = itr.next();
			
			//find the entry to put the value
			if(currEntry.getKey() == key){
				//hold the value to be returned
				V ret = (V) (currEntry.getValue());
				//replace the value at the current entry
				currEntry.setValue(value);
				
				//TODO: remove, bug spot
				//return the value to be returned
				numEntries++;
				
				//return the original value in the entry
				return ret;
			}
		}

		//else, add the new key to the end of the list.
		table[hashInd].add(new Entry<K, V>(key, value));
		
		//increment numEntries
		numEntries++;
		
		//return null if the key was originally not in the table
		return null;
	}   

	/**
	* expand the size of the table move the entries from the old table to the new table
	*
	* @param (LinkedList<Entry<K, V>>[])(oldTable) (the old hash table to be expanded)
	* @return (LinkedList<Entry<K, V>>[])(the expanded new hash table)
	*/
	private LinkedList<Entry<K, V>>[] expand(LinkedList<Entry<K, V>>[] oldTable){

		//create a new table with the correct size
		LinkedList<Entry<K, V>>[] newTable = (LinkedList<Entry<K, V>>[]) (new LinkedList[tableSizes[currSize]]);
		
		//variable holds the new Hash Index
		int newHash;

		//re-hash
		//loop through each bucket
		for(int i = 0 ; i < oldTable.length; i++){
			
			//variable used to hold the current entry
			Entry<K, V> currEntry;

			//if the bucket is not null
			if(oldTable[i] != null){
				//place the iterator at the hashtable
				Iterator<Entry<K, V>> itr = oldTable[i].iterator();
				
				//iterate through each entry
				while(itr.hasNext()){
				
					//get the entry's key
					currEntry = itr.next();
					
					//re-hash the key to get new hash code
					newHash = hash((K) (currEntry.getKey()));
					//put the entry in the new table by calling private method
					putIntoNewTable(newHash, newTable, currEntry);
				}//end while
			}//end if
		}//end for
		
		//return the new table which has a expanded size
		return newTable;

	}

	/**
	* move entries from old table to the new table when expanding table
	*
	* @param (int newHash)(the new hash index the entry to be added in)
	* @param (LinkedList<Entry<K, V>>[] new table)(the expanded new hash table)
	* @param (Entry<K, V> currEntry)(the entry to be added to the new hash table)
	*/
	private void putIntoNewTable(int newHash, LinkedList<Entry<K, V>>[] newTable, Entry<K, V> currEntry){

		//if there is an empty index in the hashtable, i.e., null
		//just add the current entry to the list. 
		if(newTable[newHash] == null){
			//create new LinkedList
			newTable[newHash] = new LinkedList<Entry<K, V>>();
			//add to new LinkedList
			newTable[newHash].add(currEntry);
			return;	
		}

		//else, add the new key to the end of the list.
		newTable[newHash].add(currEntry);
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

	public V remove(K key) {
	
		//input validation
		if (key == null)
			throw new NullPointerException(); 

		//variable used to hold the hashIndex
		int hashInd = hash(key);

		//return null if no list in the correspond hashIndex
		if(table[hashInd] == null){
			return null;
		}

		//hashIndex is not empty
		//put the iterator in the hash table
		Iterator<Entry<K, V>> itr = table[hashInd].iterator();
		
		//variable used to hold the current entry
		Entry<K, V> currEntry;
		
		//the index of the value to be removed in the bucket
		int curr = 0;

		//loop through bucket
		while(itr.hasNext()){
		
			//update currEntry
			currEntry = itr.next();
			
			//remove the value associated to the key
			if(currEntry.getKey().equals(key)){
			
				//hold the original value to be returned
				V temp = table[hashInd].remove(curr).getValue();
				
				//decrease the numEntries
				numEntries--;

				//Be careful not to make the size of the list 0, if that happens
				//just delete the list.
				if(table[hashInd].size() == 0){
					table[hashInd] = null;
				}
				
				//return the original value
				return temp;
			}

			//update the index counter
			curr++;
		}
		//if the item is not in the bucket, return null
		return null;
	}   

	/**
	 * Returns the greatest key less than or equal to the given key, or null if there is no such key. 
	 * Throws NullPointerException if key is null. 
	 * @param key key whose floor should be found
	 * @return the largest key smaller than the one passed to it
	 * @throws NullPointerException if key is null
	 */
	public K floorKey(K key){
		//input validation
		if (key == null)
			throw new NullPointerException(); 

		//integer variable to hold the value of key
		Integer intKey = 0;
		
		//if key is instance of integer, cast key into integer
		if(key instanceof Integer){
			intKey = (Integer) key;
		}
		
		//boolean array used to keep track of which buckets has been visited
		boolean[] visited = new boolean[tableSizes[currSize]];
		
		//int used to count the number if buckets that has been visited
		int countVisited = 0;
		
		//variable used to hold floorKey from the table
		K floorKey = null;
		
		//variable used to hold floorkey of a bucket in the table
		K localFloor = null;
		
		//variable used to hold hash index of key
		int hashInd = hash(key);

		//if all bucket visited, end the loop
		while(countVisited < numEntries){
			
			//update hash Index to the hash index of the current key
			hashInd = hash(key);
			
			//check boolean array before go into the bucket
			if (!visited[hashInd]){

				//check if the bucket is null
				if (table[hashInd] != null){
					//compute the local key
					localFloor = localFloor(table[hashInd], key);

					//update the boolean array
					visited[hashInd] = true;

					//increment countVisited
					countVisited = countVisited + table[hashInd].size();
				}//end if loop

				//update floorKey by comparing with local floor. Be careful of null local floor
				//if floorKey is null, set it to the localFloor
				if (localFloor != null){
					if(floorKey == null){
						floorKey = localFloor;
					}
					//if the floorKey is null, reset it if the current localFloor is larger than floorKey
					else if (floorKey.compareTo(localFloor) < 0){
						floorKey = localFloor;
					}
				}

				//if key is found right away, break the loop
				if (floorKey != null && floorKey.compareTo(key) == 0){
					return key;
				}

				//TODO: Cases that K is not integer
				
			}//end of checking boolean array
			//decrement key if not found.
			intKey--;
			
			//update key
			key = (K) intKey;
		}//end while
		return floorKey;

	}//end FloorKey public method

	/**
	 * compute the local floorKey of a bucket
	 * 
	 * @param (LinkedList<Entry<K, V>> list)(the bucket whose floorKey need to be found)
	 * @param (K key)(the upper limit based on what floorKey is found)
	 * @return the floorKey of the current bucket
	 */
	private K localFloor(LinkedList<Entry<K, V>> list, K key){

		//the current key the iterator is pointing to
		K currKey = null;

		//the floorKey of this bucket
		K localFloor = null;

		//compute the local key
		Iterator<Entry<K, V>> itr = list.iterator();

		//store the current entry
		Entry<K, V> currEntry;

		//loop through all Entries
		while(itr.hasNext()){

			//update itr
			currEntry = itr.next();

			//update current key
			currKey = currEntry.getKey();

			//if the currKey = key, break the loop right away
			if(currKey.compareTo(key) == 0){
				return currKey;
			}

			//use the first valid element to initialized the localFloor
			if(localFloor == null && currKey.compareTo(key) <= 0){
				localFloor = currKey;
			}

			//update the localFloor if we find a larger valid element
			else if (currKey.compareTo(key) <= 0 && currKey.compareTo(localFloor) >= 0){
				localFloor = currKey;
			}
		}//end while

		//return the floorKey of this bucket
		return localFloor;

	}



	//TODO: remove!!!!
	public void print(){
		for (int i = 0; i < table.length ;i++){
			System.out.println("hash ind " + i + " is => " + table[i]);
		}
	}

}
