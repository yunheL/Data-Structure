import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class TrainSimulator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// *** steps 1 - 3 of the main method ***

		//ArrayList to store all stations
		ArrayList<Station> allStations = new ArrayList<Station>();

		//ArrayList to store all trains
		ArrayList<Train> allTrains = new ArrayList<Train>();

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
				if(commandOption > 2 || commandOption < 0)
					throw new NumberFormatException();

				//total stations
				int totalStn = 0;

				//total trains
				int totalTrain = 0;

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
						totalStn = Integer.parseInt(scnr.nextLine());
					}
					else{
						totalTrain = Integer.parseInt(scnr.nextLine());
					}

					//String nxLine = scnr.nextLine();

					while(scnr.hasNextLine()){

						String input = scnr.nextLine();

						if (input.length()>0){
							if(i == 1){

								Station tempStn = assignStations(input);

								if(tempStn != null)
									allStations.add(tempStn);

							}
							else{

								Train tempTrn = assignTrains(input, totalStn);

								if(tempTrn != null)
									allTrains.add(tempTrn);

							}
						}
					}// end of while loop for reading each file
					scnr.close();
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

			//create tracks
			for (int i = 0 ; i < allStations.size()-1 ; i++){
				allTracks.add(new SimpleQueue<Train>(allTrains.size()));
			}

			//put all trains in the first station
			for (int i = allTrains.size() - 1; i >= 0; i--)
			{
				try{
					//set the initial ATA to 0
					allTrains.get(i).getATA().add(0);
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
			int numLastStnTrn = 0; 
			//counter for number of trains in last station
			Platform lastPlf = null;
			if (allStations.size() != 0)
				lastPlf = allStations.get(allStations.size()-1).getPlatform();

			while((allStations.size() > 0) && !lastPlf.isFull()){
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

						boolean isFirstStn = (i == 0);

						//pop from station and enqueue into the track
						while (time >= currTrnEtd && (time > currTrnATA 
								|| isFirstStn)){

							Train poppedTrn = currPlf.get();
							// reference to the train that 
							//was popped onto the track

							//printout for commandOption 0.
							if(commandOption == 0)
								System.out.println(time + ":	" 
										+ "Train " + currTrn.getId() + 
										" has exited from station " + currStnId + ".");

							poppedTrn.getATD().add(time); 
							//update the ATD
							nextTrack.enqueue(poppedTrn); 
							//enqueue into the track
							currTrn = currPlf.check(); 
							//update the currTrain
							currTrnEtd = currTrn.getETD().get(i); 
							//update the currTrnETD
							currTrnATA = currTrn.getATA().get(i); 
							//update the currTrnATA

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
				//=========================================

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

							Train dequeuedTrain = currTrack.dequeue();

							dequeuedTrain.getATA().add(time);

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
								if (numLastStnTrn != allTrains.size()){

									System.out.println(time + ":	" 
											+ "Train " + currTrn.getId() + 
											" has been parked at station " + 
											currStn.getId() + ".");

								}
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
				System.out.print(allTrains.get(allTrains.size()-1).getATD());
			}

			//commandOption 2: print out a list of ATAs ordered by Train Id
			else if (commandOption == 2 && allTrains.size() > 0)
			{
				for (int i = 0; i < allTrains.size()-1; i++)
				{
					System.out.println(allTrains.get(i).getATA());
				}
				System.out.print(allTrains.get(allTrains.size()-1).getATA());
			}



		}//end if-else for number or args.
	}//end main

	/**
	 * returns null if bad input is provided.
	 * */
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
			toRet = new Station(stationInfo[0], stationInfo[1]);			
		}
		catch(NumberFormatException e){
			System.out.println("Invalid input in station file.");
		}

		return toRet;

	}

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
		catch(NumberFormatException e){
			System.out.println("Invalid input in Train file.");
		}

		return toRet;

	}	
}
