import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public class tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//long[] testLong = {4,8,7,5};
		//System.out.println("Max =" + MapBenchmark.getMax(testLong));
		//System.out.println("Min =" +MapBenchmark.getMin(testLong));
		//System.out.println("SD =" +MapBenchmark.getSD(testLong));
		

		SimpleHashMap myHash = new SimpleHashMap<Integer, String>();

		
		SimpleTreeMap myTree = new SimpleTreeMap<Integer, String>();
//		myTree.put(3, "Three");
//		myTree.put(2, "Two");
//		myTree.put(11, "Eleven");
//		myTree.put(22, "Twenty-2");
//		myTree.put(33, "Thirty-3");
//		myTree.put(44, "Forty-4");
//		//System.out.println(myTree.remove(3));
//		//System.out.print(myTree);
//		System.out.println(myTree.floorKey(33));
		
		/*Integer int2 = new Integer(2);
		Integer int1 = new Integer(1);
		
		System.out.println(int2.hashCode());
		int2--;
		System.out.println(int2.hashCode());
		System.out.println(int1.hashCode());*/
			
//		myHash.put(1, "one");
//		myHash.put(2, "two");
//		myHash.put(3, "three");
//		myHash.put(4, "four");
//		myHash.put(66, "66");
//		myHash.put(55, "55");
//		myHash.put(11, "eleven");
//		myHash.put(22, "Twenty-2");
//		myHash.put(330, "threeHun-thirty");
		//myHash.put(2794, "2794");
//		myHash.put(440, "fourHun-Forty");
		
		
//		myHash.print();
		
		System.out.println("-----------------------------");
		System.out.println(myHash.floorKey(-50));
//		System.out.println("======================");
//		myHash.print();
		
//		
//		System.out.println(myHash.floorKey(330));
//		System.out.println(myHash.get(22));
		
		
		/***************testing for aliasing****************/
//		ArrayList<Integer> allKeys = new ArrayList<Integer>();
//		ArrayList<String> allValues = new ArrayList<String>();
//		
//		allKeys.add(new Integer(1));
//		allKeys.add(new Integer(2));
//		allValues.add("One");
//		allValues.add("Two");
//		
//		myTree.put(allKeys.get(0), allValues.get(0));
//		myHash.put(allKeys.get(0), allValues.get(0));
//		myHash.put(allKeys.get(1), allValues.get(1));
//		myTree.put(allKeys.get(1), allValues.get(1));
//		myHash.put(allKeys.get(0), "1");
//		myHash.print();
//		System.out.println(myTree);
		//System.out.println(myHash.get(771));
		/***************end testing for aliasing****************/
		
		
		
		
		/*LinkedList<Entry<Integer, String>> lst = new LinkedList<Entry<Integer, String>>();
		lst.add(new Entry<Integer, String>(2, "Not"));
		lst.add(new Entry<Integer, String>(0, "Hash"));
		lst.add(new Entry<Integer, String>(7, "duplicates"));
		lst.add(new Entry<Integer, String>(1, "fish"));*/
		
		//System.out.println(localFloor(lst, -4646));
		
	}
	
	
	
//	public static Integer localFloor(LinkedList<Entry<Integer, String>> list, Integer key){
//		
//		Integer currKey = null;
//		Integer localFloor = null;
//		
//		//compute the local key
//		Iterator<Entry<Integer, String>> itr = list.iterator();
//		Entry<Integer, String> currEntry;
//				
//		while(itr.hasNext()){
//			
//			currEntry = itr.next();
//			currKey = currEntry.getKey();
//			
//			if(currKey.compareTo(key) == 0){
//				return currKey;
//			}
//			
//			if(localFloor == null && currKey.compareTo(key) <= 0){
//				localFloor = currKey;
//			}			
//			else if (currKey.compareTo(key) <= 0 && currKey.compareTo(localFloor) >= 0){
//				localFloor = currKey;
//			}
//			
//		}
//		
//		return localFloor;
//				
//	}
}
