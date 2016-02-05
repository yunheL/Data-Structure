///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Program 3
// Files:            TrainSimulator.java
//					 Platform.java
//					 SimpleStack.java
//					 SimpleQueue.java
//					 EmptyPlatformException.java
//					 EmptyQueueException.java
//					 EmptyStackException.java
//					 FullPlatformException.java
//					 FullQueueException.java
//					 FullStackException.java
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Simulate the actually arrival, departure time based on estimated
 * departure time and the way that train move around different
 * stations.
 * Read in command line arguments and print out information accordingly.
 *
 * <p>Bugs: All known bugs have been resolved.
 *
 * @author Shu Wen Loo, Yunhe Liu
 */

public class TrainSimulator {

	/**
	 * Main method read in the train txt file and station
	 * txt file then use the information to simulate the actual 
	 * departure and arrival time. Print out information
	 * according to command line arguments.
	 *
	 * @param String[] args: Command line arguments.
	 * @return void
	 */
	public static void main(String[] args) {

		// *** steps 1 - 3 of the main method ***

		//ArrayList to store all stations
		ArrayList<Station> allStations = new ArrayList<Station>();

		//ArrayList to store all trains
		ArrayList<Train> allTrains = new ArrayList<Train>();

		//create the variable contains the first argument
		//contained in the command line
		int commandOption = -1;

		//make sure the argument length is 3
		if (args.length != 3){
			//Display the correct usage.
			System.out.println("Usage: java TrainSimulator N "
					+ "Stations_File_Name Trains_File_Name");
		}
		//number of arguments is 3
		else{
			try
			{
				//input command validation
				//variable used to record the comand option (0, 1, 2)
				commandOption = Integer.parseInt(args[0]);
				
				//if the command is not (0, 1, 2), treat it as
				//invalid command
				if(commandOption > 2 || commandOption < 0)
					throw new NumberFormatException();

				//total stations
				int totalStn = 0;

				//total trains
				int totalTrain = 0;

				//read in the Station and Train txt file
				for(int i = 1; i<=2; i++){
					//2 and 3 argument, if not a txt, Throw IOexception.
					if( !(args[i].substring(args[i].length()
							- 4)).equals(".txt") ){

						throw new IOException();

					}

					//Use a scanner to read the file.
					Scanner scnr = new Scanner(new File(args[i]));

					//get the first line of the input file
					if(i == 1){
						//file is the stations file
						totalStn = Integer.parseInt(scnr.nextLine());
					}
					else{
						//file is tha train file
						totalTrain = Integer.parseInt(scnr.nextLine());
					}

					//get the remaining lines of the input file
					while(scnr.hasNextLine()){
						//create a string containing the read in info
						String input = scnr.nextLine();
						//make sure the read in info is not null
						if (input.length()>0){
							//if the read in info is from the station file
							if(i == 1){
								
								//call private method assignStations to
								//create the station object
								Station tempStn = assignStations(input);
								
								//if the station object is created
								//successfully with good input
								if(tempStn != null)
								
									//add the station to the list
									//of stations
									allStations.add(tempStn);
							}
							//if the read in info is from the train file
							else{
								//call private method assignTrains to
								//create the train object
								Train tempTrn = assignTrains(input, totalStn);
								
								//if the train object is created
								//successfully with good input
								if(tempTrn != null)
									
									//add the station to the list
									//of stations
									allTrains.add(tempTrn);
							}//end of else
						}//end of if readin file is not null
					}// end of while loop for reading each file
					
					scnr.close();//close scanner
				
				}//end of the assignment for loop

			}//end try for reading the file.
			//Catch exception if file not found.
			catch (FileNotFoundException e)
			{
				System.out.println("Error: Cannot access input file");	
			}
			//Catch exception if file can't be read.
			catch (IOException e)
			{
				System.out.println("Error: Cannot access input file");	
			}
			//Just in case :D
			catch (NullPointerException e){

			}
			catch (UnsupportedOperationException e){

			}
			catch (NumberFormatException e)
			{
				System.out.println("Error: First input "
						+ "command argumant is not integer within range.");	
			}//end try-catch block for reading files

			/**================end of reading file section ==================*/

			//an Arraylist of SimpleQueue holds Trains on the Track
			ArrayList<SimpleQueue<Train>> allTracks = 
					new ArrayList<SimpleQueue<Train>>();

			//create tracks and put them in the list of tracks
			for (int i = 0 ; i < allStations.size()-1 ; i++){
				allTracks.add(new SimpleQueue<Train>(allTrains.size()));
			}

			//put all trains in the first station
			for (int i = allTrains.size() - 1; i >= 0; i--)
			{
				try{
					
					//set the initial ATA to 0
					allTrains.get(i).getATA().add(0);
					
					//put the train in the platform of the first station
					allStations.get(0).getPlatform().put(allTrains.get(i));
				}
				catch(FullPlatformException e){
					System.out.println("The station is full! Don't"
							+ " add anymore Trains!");
				}
			}

			/********************Simulate Train Movements*********************/

			//variable used to keep track of time
			int time  = 0;
			
			//counter for number of trains in last station
			int numLastStnTrn = 0; 
			
			//lastPlf is the platform in the last station
			Platform lastPlf = null;
			if (allStations.size() != 0)
				lastPlf = allStations.get(allStations.size()-1).getPlatform();

			//while loop will keep running until the platform in the last
			//station is full. Condition based on the program description:
			//1.Move each train from the start station to the final station.
			//2.First and last station has same capcity as the number
			//of trains.
			while((allTrains.size() > 0) && (allStations.size() > 0) 
					&& !lastPlf.isFull()){
				// simulates every time cycle

				//pop and enqueue
				for(int i = allStations.size()-2; i >= 0; i--){ 
					// check and update every station and 
					//track, i is station counter

					//get the top Train of the ith station 
					try{
						//get the platform from the ith station
						Platform currPlf = allStations.get(i).getPlatform();

						//get the track after the ith station
						SimpleQueue<Train> nextTrack = allTracks.get(i);

						//get the top train from the currPlf
						Train currTrn = currPlf.check();

						//System.out.println(currTrn);

						//get the ETD of the ith station of the currTrn
						int currTrnEtd = currTrn.getETD().get(i);

						//get the ATA of the ith station of the currTrn
						int currTrnATA = currTrn.getATA().get(i);

						//ID of currStation
						int currStnId = allStations.get(i).getId();

						//boolean flag used to see whether the current
						//station is the first station
						boolean isFirstStn = (i == 0);

						//pop from station and enqueue into the track
						while (time >= currTrnEtd && (time > currTrnATA 
								|| isFirstStn)){

							// reference to the train that 
							//was popped onto the track
							Train poppedTrn = currPlf.get();

							//printout for commandOption 0.
							if(commandOption == 0)
								System.out.println(time + ":	" 
										+ "Train " + currTrn.getId() + 
										" has exited from station " + 
										currStnId + ".");

							//update the ATD
							poppedTrn.getATD().add(time); 
							//enqueue into the track
							nextTrack.enqueue(poppedTrn); 
							//update the currTrain
							currTrn = currPlf.check(); 
							//update the currTrnETD
							currTrnEtd = currTrn.getETD().get(i); 
							//update the currTrnATA
							currTrnATA = currTrn.getATA().get(i); 

						}//end of the while loop checking 
						//whether platform is empty


					}//end try for pop and enqueue
					catch(EmptyPlatformException e){

					}
					catch(FullQueueException e){

					}
					//catch(EmptyQueueException e){

					//}

				}	//end of the for loop for pop and enqueue
				
				//============dequeue and push process starts here=======

				//dequeue train from track and push into the next station
				for (int i = allTracks.size()-1 ; i >= 0 ; i--){

					//get the track after the ith station
					SimpleQueue<Train> currTrack = allTracks.get(i);

					try {
						//get the top train from the currTrack
						Train currTrn = currTrack.peek();

						//get the current station
						Station currStn = allStations.get(i+1);

						//get the platform after the track
						Platform nextPlf = currStn.getPlatform();

						//update currTrain
						currTrn = currTrack.peek();

						//update currTrainATD
						int currTrnATD = currTrn.getATD().get(i);

						//see whether dequeue requirements are satisfied
						//check whether the trains has been on the 
						//track for more than 10 time units
						while (!nextPlf.isFull() && currTrnATD + 10 <= time){ 

							//refernce to the dequeued train, i.e, train
							//ready to enter a station
							Train dequeuedTrain = currTrack.dequeue();

							//update the ATA of the dequeued train
							dequeuedTrain.getATA().add(time);
							
							//train enters station
							nextPlf.put(dequeuedTrain);
							
							// if the current station is the last station, 
							//update number of Trains in the last station.
							if (allStations.get(i+1).getId() == 
									allStations.get(
											allStations.size()-1).getId()){
								numLastStnTrn++;
							}

							//printout for commandOption 0.
							if(commandOption == 0){
								//if the this is not the last line of
								//print statement, print with println
								if (numLastStnTrn != allTrains.size()){

									System.out.println(time + ":	" 
											+ "Train " + currTrn.getId() + 
											" has been parked at station " + 
											currStn.getId() + ".");
								}
								//if the this is the last line of
								//print statement, print with print
								else
								{
									System.out.print(time + ":	" + 
											"Train " + currTrn.getId() + 
											" has been parked at station " + 
											currStn.getId() + ".");
								}
							}

							currTrn = currTrack.peek(); 
							//update the currTrain
							currTrnATD = currTrn.getATD().get(i); 
							//update the currTrnATA

						}//end while for dequeue and push
					}
					catch (EmptyQueueException ex){

					}
					catch(FullPlatformException e){

					}			

				}//end of the for loop for push
				time ++;
			}//end while loop to simulate time.
			
			//remove 0 from the ATA's for all Trains
			for (int i = 0; i< allTrains.size(); i++)
			{
				allTrains.get(i).getATA().remove(0);
			}

			//commandOption 1: print out a list of ATDs ordered by Train Id
			if (commandOption == 1 && allTrains.size() > 0)
			{
				for (int i = 0; i < allTrains.size()-1; i++)
				{
					System.out.println(allTrains.get(i).getATD());
				}
				//print the last line without extra line
				System.out.print(allTrains.get(allTrains.size()-1).getATD());
			}

			//commandOption 2: print out a list of ATAs ordered by Train Id
			else if (commandOption == 2 && allTrains.size() > 0)
			{
				for (int i = 0; i < allTrains.size()-1; i++)
				{
					System.out.println(allTrains.get(i).getATA());
				}
				//print the last line without extra line
				System.out.print(allTrains.get(allTrains.size()-1).getATA());
			}
			
		}//end if-else for number or args.
	}//end main

	/**
	 * take the information extracted from input file. Parse String
	 * input to integer and create a station object accordingly.
	 *
	 * @param (String input) one line of String extracted from
	 * station txt file.
	 * @return return the station object created accordingly, 
	 * return null if bad input is provided
	 */
	private static Station assignStations(String input){
		//create the station to be returned
		Station toRet = null;

		//split the ID and the capacity
		String[] temp = input.split(",");

		//temp[0] is ID and temp[1] is capacity
		temp[0] = temp[0].trim();
		temp[1] = temp[1].trim();
		int[] stationInfo = new int[2];

		//parse the information to integers
		try{
			stationInfo[0] = Integer.parseInt(temp[0]);
			stationInfo[1] = Integer.parseInt(temp[1]);
			//create the station object according to info read in
			toRet = new Station(stationInfo[0], stationInfo[1]);			
		}
		//handle bad input file
		catch(NumberFormatException e){
			System.out.println("Invalid input in station file.");
		}

		return toRet;

	}//end of the assignStations private method

	/**
	 * take the information extracted from input file. Parse String
	 * input to integer and create a train object accordingly.
	 *
	 * @param (String input) one line of String extracted from
	 * station txt file.
	 * @param (input totalStn) total number of stations
	 * @return return the train object created accordingly, 
	 * return null if bad input is provided
	 */
	private static Train assignTrains(String input, int totalStn){
		//create the station to be returned
		Train toRet = null;

		//split the ID and the capacity
		String[] temp = input.split(",");

		//temp[0] is ID and temp[1] is capacity
		for (int i = 0; i < temp.length; i++){
			temp[i] = temp[i].trim();
		}

		int[] TrainInfo = new int[temp.length];

		//parse the information to integers
		try{
			for (int i = 0;  i < temp.length; i++){
				TrainInfo[i] = Integer.parseInt(temp[i]);
			}

			//create a new Train object
			toRet = new Train(TrainInfo[0]);

			//assgin the ETD values
			for (int i = 1; i< temp.length; i++){
				toRet.getETD().add(TrainInfo[i]);
			}
		}
		//handle bad input file
		catch(NumberFormatException e){
			System.out.println("Invalid input in Train file.");
		}

		return toRet;

	}//end of the assignTrains private method	
}//end of the class
