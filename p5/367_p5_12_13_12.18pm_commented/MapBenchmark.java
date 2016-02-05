///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Program 5
// Files:            MapBenchmark.java
//					 Entry.java
//					 SimpleTreeMap.java
//					 SimpleHashMap.java
//					 
// Semester:         CS367 Fall 2014
//
// Author:           Shu Wen Loo
// Email:            loo@cs.wisc.edu
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
// Email:            yunhe@cs.wisc.edu
// CS Login:         yunhe
// Lecturer's Name:  Jim Skrentny
// Lab Section:      N/A
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          N/A
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Tests the performances of SimpleHashMap against SimpleTreeMap. Tests the 
 * speed of get, remove, put and floorkey. Outputs the mean, min, max and 
 * standard deviation for all tests.
 *
 * <p>Bugs: All known bugs have been resolved.
 *
 * @author Shu Wen Loo, Yunhe Liu
 */

public class MapBenchmark{

	public static void main(String[] args) {


		//initialize and declare variables
		Scanner scnr; //scanner to read input file.
		int numIter; //number of iterations to run
		ArrayList<Integer> allKeys = new ArrayList<Integer>(); //store keys
		ArrayList<String> allValues = new ArrayList<String>(); //store values

		//create new SimplHashMap
		SimpleHashMap myHash = new SimpleHashMap<Integer, String>(); 
		//create new SimpleTreeMap
		SimpleTreeMap myTree = new SimpleTreeMap<Integer, String>();

		//check whether command line has correct number of input parameters
		if (args.length != 2){
			System.out.println("Wrong number of command line inputs.");
		}
		else{

			/***********************input file reading***********************/

			try{
				//see whether first argument is an txt. file
				if( !(args[0].substring(args[0].length()- 4)).equals(".txt") ){
					throw new IOException();
				}

				//initialize scanner with file.
				scnr = new Scanner(new File(args[0]));

				numIter = Integer.parseInt(args[1].trim()); //initialize numIter
			}

			//if input file cannot be read
			catch(FileNotFoundException e){
				System.out.println("Cannot find or read the specified file");
				return;
			}
			catch(IOException e){
				System.out.println("Cannot find or read the specified file");
				return;
			}
			//if numIter is invalid
			catch(NumberFormatException e){
				System.out.println("NumIter is not a valid integer.");
				return;
			}

			String currLine; //variable to store current line temporarily

			while(scnr.hasNextLine()){
				currLine = scnr.nextLine();

				//End of line does weird stuff to scanner. To prevent 
				//errors, we only process  strings which have more than 
				//one char.
				if (currLine.length() > 0){
					String[] temp = currLine.split(" ");

					//only consider this valid input if there is a key and a 
					//value.
					if(temp.length == 2){

						//get key and value.
						Integer currKey = Integer.parseInt(temp[0]);
						String currValue = temp[1];

						//add to ArrayList of keys and values.
						allKeys.add(currKey);
						allValues.add(currValue);
					}
				}

			}

			scnr.close(); // close the scanner


			/**********************input file reading ends*********************/
			//TODO: On 12/8, finished creating array of input keys and values. 
			//next thing to do is to use the arrays to populate maps. Then, we
			//will finish the rest. 

			//record all iterations here. Each row corresponds to one method, 
			//ie, get, put, remove etc.. Each column is one iteration.
			long[][] allTimes = new long[8][numIter];


			for(int ndx = 0;ndx < numIter;ndx++){

				/********************0. populating HashMaps********************/
				//start clock
				long startPopHash = System.currentTimeMillis();

				//populate hashMap using items stored in allKeys and allValues.
				for(int i = 0; i < allKeys.size(); i++){
					myHash.put(allKeys.get(i), allValues.get(i));
				}

				//get time
				allTimes[0][ndx] = System.currentTimeMillis() - startPopHash;

				/*********************1. populating treeMaps*******************/

				//start clock
				long startPopTree = System.currentTimeMillis();

				//populate TreehMap using items stored in allKeys and allValues.
				for(int i = 0; i < allKeys.size(); i++){
					myTree.put(allKeys.get(i), allValues.get(i));
				}

				//get time.
				allTimes[1][ndx] = System.currentTimeMillis() - startPopTree;

				/*************************2. get (Hash)************************/

				//start clock
				long startGetHash = System.currentTimeMillis();

				//get items in hashMap
				for(int i = 0; i < allKeys.size(); i++){
					myHash.get(allKeys.get(i));
				}

				//get time.
				allTimes[2][ndx] = System.currentTimeMillis() - startGetHash;

				/*************************3. get (Tree)************************/

				//start clock
				long startGetTree = System.currentTimeMillis();

				//get items in treeMap
				for(int i = 0; i < allKeys.size(); i++){
					myTree.get(allKeys.get(i));
				}

				//get time
				allTimes[3][ndx] = System.currentTimeMillis() - startGetTree;

				/***********************4. floorKey (Hash)*********************/

				long startFloorHash = System.currentTimeMillis();

				for(int i = 0; i < allKeys.size(); i++){
					myHash.floorKey(allKeys.get(i));
				}

				allTimes[4][ndx] = System.currentTimeMillis() - startFloorHash;

				/************************5. FloorKey Tree**********************/

				long startFloorTree = System.currentTimeMillis();

				for(int i = 0; i < allKeys.size(); i++){
					myTree.floorKey(allKeys.get(i));
				}

				allTimes[5][ndx] = System.currentTimeMillis() - startFloorTree;

				/*************************6. Remove Hash***********************/

				//start clock
				long startRemoveHash = System.currentTimeMillis();

				//remove all items from hashMap
				for(int i = 0; i < allKeys.size(); i++){
					myHash.remove(allKeys.get(i));
				}

				//get time.
				allTimes[6][ndx] = System.currentTimeMillis() - startRemoveHash;

				/*************************7. Remove Tree*************************/

				//start clock
				long startRemoveTree = System.currentTimeMillis();

				//remove all items from treeMap
				for(int i = 0; i < allKeys.size(); i++){
					myTree.remove(allKeys.get(i));
				}

				//get time
				allTimes[7][ndx] = System.currentTimeMillis() - startRemoveTree;

				myHash = new SimpleHashMap<Integer, String>();
				myTree = new SimpleTreeMap<Integer, String>();

//				System.out.print(String.format("%.2f",100* ndx/(float)numIter) 
//						+"% done \r"); 

			}//end for loop

			/*************************Generating Stats*************************/
			//TODO: if numIter = 0, don't calculate anything, protect the
			//stats methods, avoid times[] with only one item

			/* Array to hold the results.
			 * 
			 * Col 0: min
			 * Col 1: max
			 * Col 2: mean
			 * Col 3: standard deviation
			 *  
			 * */
			double[][] results = new double[8][4];

			if(numIter > 0){

				for(int i = 0; i < 8 ; i++){
					//row sequence:
					//0: Hash put
					//1: Tree put
					//2: Hash get
					//3: Tree get
					//4: Hash floor
					//5: Tree floor
					//6: Hash remove
					//7: Tree remove

					results[i][0] = getMin(allTimes[i]); 
					results[i][1] = getMax(allTimes[i]); 
					results[i][2] = getMean(allTimes[i]); 
					results[i][3] = getSD(allTimes[i]); 

				}

			}

			//print out the output
			System.out.println("Results from " +numIter+ " runs of ../files/" 
					+ args[0] + "\n");

			/*************************2: HashMap: get**************************/

			System.out.println("HashMap: get\n--------------------\nMin : " + 
					((long) results[2][0]));
			System.out.println("Max : " + ((long) results[2][1]));
			System.out.println("Mean : " + String.format("%.3f",results[2][2]));
			System.out.println("Std Dev : " + 
					String.format("%.3f",results[2][3]) + "\n");

			/****************************0: Hash put***************************/

			System.out.println("HashMap: put\n--------------------\nMin : " + 
					((long) results[0][0]));
			System.out.println("Max : " + ((long) results[0][1]));
			System.out.println("Mean : " + String.format("%.3f",results[0][2]));
			System.out.println("Std Dev : " + 
					String.format("%.3f",results[0][3])+ "\n");

			/***********************4. HashMap: floorKey***********************/

			System.out.println("HashMap: floorKey\n--------------------\nMin : " 
					+ ((long) results[4][0]));
			System.out.println("Max : " + ((long) results[4][1]));
			System.out.println("Mean : " + String.format("%.3f",results[4][2]));
			System.out.println("Std Dev : " + 
					String.format("%.3f",results[4][3])+ "\n");

			/*************************6. HashMap: remove***********************/

			System.out.println("HashMap: remove\n--------------------\nMin : " 
					+ ((long) results[6][0]));
			System.out.println("Max : " + ((long) results[6][1]));
			System.out.println("Mean : " + String.format("%.3f",results[6][2]));
			System.out.println("Std Dev : " + 
					String.format("%.3f",results[6][3])+ "\n");

			/*************************3. TreeMap: get*************************/

			System.out.println("TreeMap: get\n--------------------\nMin : " 
					+ ((long) results[3][0]));
			System.out.println("Max : " + ((long) results[3][1]));
			System.out.println("Mean : " + String.format("%.3f",results[3][2]));
			System.out.println("Std Dev : " + 
					String.format("%.3f",results[3][3])+ "\n");

			/*************************1. TreeMap: put*************************/

			System.out.println("TreeMap: put\n--------------------\nMin : " + 
					((long) results[1][0]));
			System.out.println("Max : " + ((long) results[1][1]));
			System.out.println("Mean : " + String.format("%.3f",results[1][2]));
			System.out.println("Std Dev : " + 
					String.format("%.3f",results[1][3])+ "\n");

			/***********************5. TreeMap: floorKey***********************/

			System.out.println("TreeMap: floorKey\n--------------------\nMin : " 
					+ ((long) results[5][0]));
			System.out.println("Max : " + ((long) results[5][1]));
			System.out.println("Mean : " + String.format("%.3f",results[5][2]));
			System.out.println("Std Dev : " + 
					String.format("%.3f",results[5][3])+ "\n");

			/*************************7. TreeMap: remove*************************/

			System.out.println("TreeMap: remove\n--------------------\nMin : " 
					+ ((long) results[7][0]));
			System.out.println("Max : " + ((long) results[7][1]));
			System.out.println("Mean : " + String.format("%.3f",results[7][2]));
			System.out.println("Std Dev : " + 
					String.format("%.3f",results[7][3])+ "\n");

		}//end else branch
		//TODO: Close the scanner

	}//end of main method

	/**
	 * calculate the max from a list of values
	 * @param a long array of all times.
	 * @return the maximum of all the values.
	 */
	private static long getMax(long[] times){

		//if times only has one value, then return that value.
		if(times.length == 1){
			return times[0];
		}

		//initialize current maximum to the first value.
		long currMax = times[0];
		//keep looking for the next maximum by comparing with the next value.
		for (int i = 0; i < times.length-1 ; i++){
			currMax = Math.max(currMax, times[i+1]);
		}
		
		return currMax;
	}

	/**
	 * calculate the minimum from a list of values
	 * @param a long array of all times.
	 * @return the minimum of all the values.
	 */
	private static long getMin(long[] times){

		//if times only has one value, then return that value.
		if(times.length == 1){
			return times[0];
		}

		//initialize current maximum to the first value.
		long currMin = times[0];
		//keep looking for the next minimum by comparing with the next value.
		for (int i = 0; i < times.length - 1 ; i++){
			currMin = Math.min(currMin, times[i+1]);
		}
		
		return currMin;
	}

	/**
	 * calculate the mean from a list of values
	 * @param a long array of all times.
	 * @return the mean of all the values.
	 */
	private static double getMean(long[] times){
		//initialize mean to zero
		double mean = 0;

		//sum all the values in the array
		for (int i = 0; i < times.length ; i++){
			mean += times[i];
		}

		//return the mean which is the sum of all values over the size.
		return mean/times.length;

	}

	/**
	 * calculate the standard deviation from a list of values
	 * @param a long array of all times.
	 * @return the standard deviation of all the values.
	 */
	public static double getSD(long[] times){
		//initialize variance to zero.
		double var = 0;
		//calculate the mean.
		double mean = getMean(times);

		//calculate the variance.
		for (int i = 0; i < times.length ; i++){
			var = var + (times[i] - mean)*(times[i] - mean);
		}

		//return the standard deviation.
		return Math.sqrt(var/(times.length));

	}





}//end of main class