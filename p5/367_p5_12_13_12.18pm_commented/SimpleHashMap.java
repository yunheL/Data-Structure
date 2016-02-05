import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
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


	private int[] tableSizes = {11, 23, 47, 97, 197, 397, 797, 1597, 3203, 6421, 12853, 25717, 51437, 102877,
			205759, 411527, 823117, 1646237, 3292489, 6584983, 13169977, 26339969, 52679969, 105359939, 210719881, 
			421439783, 842879579, 1685759167};
	private double lf = 0.75;
	private LinkedList<Entry<K, V>>[] table;
	private int currSize;
	private int numEntries;
	//TODO: Decided which implement to use
	private ArrayList<Integer> usedHashInds;

	public SimpleHashMap()
	{
		currSize = 0;
		numEntries = 0;
		table = (LinkedList<Entry<K, V>>[]) (new LinkedList[tableSizes[currSize]]) ;//(new Object[tableSizes[currSize]]);
		//table = new LinkedList<(Entry) (Object)>[];
		//table = (LinkedList<J>[]) (new LinkedList<Object>[tableSizes[currSize]]);

	} 

	private int hash(K k) {
		int ret = k.hashCode() % tableSizes[currSize];
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
		if (key == null)
			throw new NullPointerException(); 

		int hashInd = hash(key);

		if(table[hashInd] == null){
			return null;
		}

		Iterator<Entry<K, V>> itr = table[hashInd].iterator();
		Entry<K, V> currEntry;		
		while(itr.hasNext()){
			currEntry = itr.next();

			if(currEntry.getKey().equals(key))
				return (V) (currEntry.getValue());
		}

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
		if (key == null || value == null)
			throw new NullPointerException(); 

		double currlf = (numEntries+1)/table.length;

		if(currlf > 0.75){

			//TODO: Possible bug spot
			currSize++; // increase the table size so that the hash function is modified.
			table = expand(table);

		}

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
			return null;	
		}

		//if there is a lisrt at the computed hashindex, iterate through the list
		//to see if the list contains the key.

		//System.out.println("Hash index not null.");
		Iterator<Entry<K, V>> itr = table[hashInd].iterator();
		Entry<K, V> currEntry;
		//replace the old key with a new key if the key is in the hash index.
		while(itr.hasNext()){
			currEntry = itr.next();

			//TODO: changed the condition from '==' to .equals()
			//TODO: commented out numEntries++;
			if(currEntry.getKey().equals(key)){
				//hold the value to be returned
				V ret = (V) (currEntry.getValue());
				//replace the value at the current entry
				currEntry.setValue(value);
				//return the value to be returned
				//numEntries++;
				return ret;
			}
		}

		//else, add the new key to the end of the list.
		table[hashInd].add(new Entry<K, V>(key, value));
		numEntries++;
		return null;
	}   

	private LinkedList<Entry<K, V>>[] expand(LinkedList<Entry<K, V>>[] oldTable){

		//create a new table with the correct size
		LinkedList<Entry<K, V>>[] newTable = (LinkedList<Entry<K, V>>[]) (new LinkedList[tableSizes[currSize]]);
		int newHash;

		//re-hash
		//loop through each bucket
		for(int i = 0 ; i < oldTable.length; i++){

			Entry<K, V> currEntry;

			//if the bucket is not null
			if(oldTable[i] != null){
				//iterate through each entry
				Iterator<Entry<K, V>> itr = oldTable[i].iterator();

				while(itr.hasNext()){
					//get the entry's key
					currEntry = itr.next();
					//re-hash the key to get new hash code
					newHash = hash((K) (currEntry.getKey()));
					//put the entry in the new table
					putIntoNewTable(newHash, newTable, currEntry);
				}//end while
			}//end if
		}//end for

		return newTable;

	}

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
		if (key == null)
			throw new NullPointerException(); 

		int hashInd = hash(key);

		//no list in the correspond hashIndex
		if(table[hashInd] == null){
			return null;
		}

		//hashIndex is not empty
		Iterator<Entry<K, V>> itr = table[hashInd].iterator();
		Entry<K, V> currEntry;
		int curr = 0;

		while(itr.hasNext()){
			currEntry = itr.next();

			if(currEntry.getKey().equals(key)){
				V temp = table[hashInd].remove(curr).getValue();
				
				//decrease the numEntries
				numEntries--;

				//Be careful not to make the size of the list 0, if that happens
				//just delete the list.
				if(table[hashInd].size() == 0){
					table[hashInd] = null;
				}

				return temp;
			}

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

		Integer intKey = 0;
		
		if(key instanceof Integer){
			intKey = (Integer) key;
		}
		
		boolean[] visited = new boolean[tableSizes[currSize]];
		int countVisited = 0;
		K floorKey = null;
		K localFloor = null;
		int hashInd = hash(key);

		//if all bucket visited, end the loop
		while(countVisited < numEntries){
			hashInd = hash(key);
			
			//check boolean array before doing ANYTHING!!!!
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
				if (localFloor != null){
					if(floorKey == null){
						floorKey = localFloor;
					}
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
			key = (K) intKey;
		}//end while
		return floorKey;

	}//end FloorKey public method

	//TODO: change visibility to private
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

		return localFloor;

	}



	//TODO: remove!!!!
	public void print(){
		for (int i = 0; i < table.length ;i++){
			System.out.println("hash ind " + i + " is => " + table[i]);
		}
	}

}
