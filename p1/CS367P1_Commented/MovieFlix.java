///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Program 1
// Files:            MovieFlix.java, MovieDatabate.java, Actor.java, 
//					 MovieIterator.java.
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
import java.io.*;
import java.util.*;

/**
 * Creates and uses a MovieDatabase to represent and process information about 
 * movies. Uses the console to input commands to display information and modify
 * the database. The database is populated from a txt file containing actor 
 * information and movie info. 
 *
 * <p>Bugs: none known
 *
 * @author Shu Wen Loo, Yunhe Liu
 */
public class MovieFlix {

	/**
	 * Main method reads a file containing actor and movie info, creates a 
	 * database and modifies the database using user commands. Is implemented 
	 * using the command line.
	 *
	 * @param String[] args: Command line arguments.
	 * @return void
	 */
	public static void main(String[] args) throws FileNotFoundException {

		/* Task 1*/
		//checks if valid input argument.
		if (args.length != 3){
			System.out.println("Usage: java MovieFlix FileName");
		}
		else{

			/*Task 2 and Task 3*/
			MovieDatabase dataBase = new MovieDatabase();
			//List to keep track of unique actors and their respective movies.
			ArrayList<Actor> actorList = new ArrayList<Actor>(); 

			try
			{
				//Use a scanner to read the file.
				Scanner scnr = new Scanner(new File(args[2]));

				//reads information in the file line by line.
				while(scnr.hasNextLine())
				{
					String nxLineInit = scnr.nextLine();

					//End of line does weird stuff to scanner. To prevent 
					//errors, we only process  strings which have more than 
					//one char.
					if (nxLineInit.length() > 0){
						String[] temp = nxLineInit.split(",");

						//Add movies to dataBase
						for (int i = 1; i<temp.length; i++)
						{
							dataBase.addMovie(temp[i]);
						}
						//Add actors to movies in dataBase.
						for (int i = 1; i<temp.length; i++)
						{
							dataBase.addActor(temp[0], temp[i]);
						}

						//Add actors to actorList.
						actorList.add(new Actor(formatString(temp[0])));
					}
				}//end while loop to read file.

				//adding movies to each actor in actorList.
				for (int i = 0; i < actorList.size(); i++){

					String name = actorList.get(i).getName();
					//Use get movies method to populate actorList.
					List<String> tempMovies = dataBase.getMovies(name);
					actorList.get(i).addMovie(tempMovies);

				}
				
				scnr.close();

				/*
				System.out.println(dataBase);
				System.out.println(actorList);
				*/
				
				Scanner stdin = new Scanner(System.in);  //for console input

				boolean done = false;

				while (!done) {
					System.out.print("Enter option (cdprswx): ");
					String input = stdin.nextLine();
					//prevent program from crashing if there is trailing 
					//whitespace.
					input = input.trim();

					//only do something if the user enters at least one char.
					if (input.length() > 0) {
						//strip off option character
						char choice = input.charAt(0);
						//will hold the remaining input
						String remainder = "";

						//if there is an argument
						if (input.length() > 1) {
							//trim off any leading or trailing spaces
							remainder = input.substring(1).trim(); 

							switch (choice) {
							//the commands that have arguments

							case 'c':
								if (dataBase.containsMovie(remainder)){
									//gets cast for the movie if movie exists.
									List<String> actorName = 
											dataBase.getCast(remainder);

									//print out actors in specified format.
									if(actorName.size() > 0){
										for (int i = 0; i < actorName.size()-1;
												i++){
											System.out.print(actorName.get(i) 
													+ ", ");
										}
										System.out.println(
												actorName.get(actorName.size()
														-1));
									}
									else{
										//print this if no cast.
										System.out.println("none");
									}

								}
								//print message if movie not found.
								else{
									System.out.println("movie not found");
								}
								break;

							case 'p':
								//check if database contains actor.
								if (dataBase.containsActor(remainder)){
									//get the list of movies.
									List<String> movieName = 
											dataBase.getMovies(remainder);
									//print the movies in a specified format.
									for (int i = 0; i < movieName.size()-1; 
											i++){
										System.out.print(movieName.get(i) 
												+ ", ");
									}
									System.out.println(
											movieName.get(movieName.size()-1));
								}
								//if actor !in database, print this:
								else{
									System.out.println("actor not found");
								}
								break;

							case 'r':
								//flag to determine if movie was removed.
								boolean movieRemoved = 
								dataBase.removeMovie(remainder);

								if(movieRemoved){
									//updating actorList.
									updateActorListRemoveMovie(
											formatString(remainder), 
											actorList);
									//print successful msg.
									System.out.println("movie removed");
								}
								else{
									System.out.println("movie not found");
								}

								break;

							case 's':
								/* The following code reads in a comma-
								 * separated sequence of strings.  If there 
								 * are exactly two strings in the sequence, 
								 * the strings are assigned to name1 and name2.
								 * Otherwise, an error message is printed.*/
								//holds the two names.
								String[] tokens = remainder.split("[,]+");

								if (tokens.length != 2) {

									System.out.println("need to provide "
											+ "exactly two names");

								}
								else {
									//prep the names. 
									String name1 = tokens[0].trim();
									String name2 = tokens[1].trim();
									//prints the result for this option.
									System.out.println(getSResult(name1, 
											name2, dataBase));

								}
								break;

							case 'w':
								//Format remainder string.
								remainder = formatString(remainder);
								//Check if actor was successfully removed.
								boolean actorRemoved = 
								dataBase.removeActor(remainder);

								//execute the following if actor successfully 
								//removed.
								if(actorRemoved){
									System.out.println(remainder + 
											" withdrawn from all movies");
									//Update actorList .
									updateActorListWithdrawActor(remainder, 
											actorList);
//									System.out.println(actorList);
//									System.out.println(dataBase);

								}
								else{
									//print message if actor not found.
									System.out.println("actor not found");
								}

								break;

							default: //ignore invalid commands
								System.out.println("Incorrect command.");
								break;

							} // end switch
						} // end if
						else { //if there is no argument
							switch (choice) { //the commands without arguments

							//implement option d.
							case 'd': 
								//firstLine
								System.out.println("Movies: " + dataBase.size() 
										+ ", " + "Actors: " + actorList.size());

								//SecondLine
								//getStatMovies gets information needed for the 
								//second, fourth and fifth line. the 
								//information is stored in statsMovies.
								String[] statsMovies = getStatMovies(dataBase);
								System.out.println("# of actors/movie: most " +
								statsMovies[0] + ", least " + statsMovies[1]
								+ ", average " + statsMovies[2]);

								//thirdLine
								//getStatActors gets information needed for 
								//this line. 
								int[] stats = getStatActors(actorList);
								System.out.println("# of movies/actor: most " + 
								stats[0] + ", least " + stats[1] 
								+ ", average " + stats[2]);

								//fourthLine
								System.out.println("Largest Cast: " 
										+ statsMovies[3]);

								//fifthLine
								System.out.println("Smallest Cast: " 
										+ statsMovies[4]);

								break;

							case 'x':
								done = true;
								//exit program.
								System.out.println("exit");
								break;

							default:  //a command with no argument
								System.out.println("Incorrect command.");
								break;
							} //end switch
						} //end else  
					} //end if
				} //end while
				stdin.close();
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
				
			}//end try-catch block.
		}//end else for command line validation
	} //end main
	
	/**
	 * Formats strings so that the first letter of each word is upper case and 
	 * the rest of the word is lower case.
	 *
	 * @param String s: String to be formatted.
	 * @return String formatted: Formatted string.
	 */	
	private static String formatString(String s)
	{
		//Changing all text to lower case.
		s = s.toLowerCase();
		//Holding each word in a string array.
		String[] temp = s.split(" ");
		
		
		for (int i = 0; i < temp.length; i++)
		{
			//Convert first lttr to upper case.
			String firstLttr = temp[i].substring(0,1).toUpperCase();
			//holds the rest of the word.
			String endOfWord = temp[i].substring(1, temp[i].length());
			//combining first letter with end of word.
			temp[i] = firstLttr + endOfWord;
		
		}
		
		//concatenate separate words into formatted string.
		String formatted = temp[0];
		for (int i = 1; i < temp.length; i++)
		{
			formatted = formatted + " " + temp[i];
		}
		
		return formatted;
		
	}
	
	/**
	 * finds the most movies/actor, least movies/actor and average 
	 * movies/actor.
	 *
	 * @param ArrayList<Actor> actorDB: arraylist of actors.
	 * @return int[]: information regarding most, least, avg
	 */	
	private static int[] getStatActors(ArrayList<Actor> actorDB){
		
		//check if actorDB is null.
		if (actorDB == null)
		{
			throw new NullPointerException();
		}
		//if actor list is empty, return all zeros for stats.
		if (actorDB.size() < 1){
			int[] statInfo = {0, 0, 0};
			return statInfo;
		}

		float sum = 0;
		int most = 0;
		int least = actorDB.get(0).getMoviesActed().size();
		float averagePre = 0;
		int average = 0;

		for (int i = 0; i < actorDB.size(); i++){

			//finding sum
			sum = sum + actorDB.get(i).getMoviesActed().size();

			//finding most
			if (actorDB.get(i).getMoviesActed().size() > most){
				most = actorDB.get(i).getMoviesActed().size();
			}

			//finding least
			if (actorDB.get(i).getMoviesActed().size() < least){
				least = actorDB.get(i).getMoviesActed().size();
			}
		}

		//getting average
		averagePre = sum/((float) actorDB.size());
		average = Math.round(averagePre);

		//returning
		int[] statInfo = {most, least, average};
		return statInfo;

	}//end getStatActors body

	/**
	 * Implements option s. Loops through the dataBase to find movies 
	 * whose cast contains both actors.
	 *
	 * @param String name1: first actor
	 * @param String name2: second actor
	 * @param MovieDatabase movieDB: database to loop through.
	 * 
	 * @return String sResult: movies with both actors or none.
	 */	
	private static String getSResult(String name1, String name2, 
			MovieDatabase movieDB){

		//iterator to iterate through the database.
		Iterator<Movie> itr = movieDB.iterator();
		//keep track of current movie.
		Movie currMovie;
		//initialize sResult to none.
		String sResult = "none";

		//loop through database
		while(itr.hasNext()){
			//hold on to current movie.
			currMovie = itr.next();
			//if both actors are cast, add to sResult.
			if(movieDB.isCast(name1, currMovie.getTitle())&& 
					movieDB.isCast(name2, currMovie.getTitle())){
				
				//for first movie that contains both actors.
				if (sResult.equals("none"))
					//replace "none" with movie title.
					sResult = currMovie.getTitle();
				else
				{
					//else append current title to sResult.
					sResult = sResult + ", " + currMovie.getTitle();
				}//end assisgnment
			}//end comparison
		}//end while

		return sResult;

	}

	/**
	 * Updates actorList to reflect changes in database. Called whenever 'r' 
	 * is chosen. Deletes movies from actorlist and remove actors with 
	 * no movies.
	 * 
	 * @param String title: movie title to be removed.
	 * @param ArrayList<Actor> actorList: list to be updated.
	 * @return void
	 * 
	 */
	private static void updateActorListRemoveMovie(String title, 
			ArrayList<Actor> actorList){
		
		//loop though ArrayList and remove movies. From each actor.
		for (int i = 0; i < actorList.size(); i++){
			actorList.get(i).removeMovie(title);
		}
		
		//loop through ArayList and delete actors with no movies.
		for (int i = 0; i < actorList.size(); i++){
			if (actorList.get(i).getMoviesActed().size() == 0)
			{
				actorList.remove(i);
				//since actorList.size() is changing, decrement i to 
				//counteract change in size.
				i--;
			}
		}
	}

	/**
	 * Updates actorList to reflect changes in database. Called whenever "w" 
	 * is chosen. Deletes the actor from actorList. 
	 * 
	 * @param String title: actor name to be removed.
	 * @param ArrayList<Actor> actorList: list to be updated.
	 * @return void
	 * 
	 */
	private static void updateActorListWithdrawActor(String name, 
			ArrayList<Actor> actorList){
		
		//Loop through to remove actors.
		for (int i = 0; i < actorList.size(); i++){
			
			//check if actor to be removed is contained in actorList.
			if(name.toLowerCase().equals(
					actorList.get(i).getName().toLowerCase())){
				
				//if yes, remove, and break out of loop.
				actorList.remove(i);
				break;
			}
		}

	}

	/**
	 * Returns information on least actors/movie, most actors/movie and 
	 * avg actors/movie. Also creates a list of movies with the largest and 
	 * smallest cast.
	 * 
	 * @param MovieDatabase movieDB: database to loop through.
	 * @param String[] statInfo: stats for the movie in string form.
	 * @return void
	 * 
	 */
	private static String[] getStatMovies(MovieDatabase movieDB){
		//check if database is null.
		if (movieDB == null)
		{
			throw new NullPointerException();
		}

		//if database has no movies, return zero for most of the arguments.
		if (movieDB.size() < 1){
			String[] statInfo = {"0", "0", "0",
					"No movies [0]", "No movies [0]"};
			//return without executing more code.
			return statInfo;
		}

		//iterator to loop though database.
		Iterator<Movie> itr = movieDB.iterator();

		//keep track of current movie.
		Movie currMovie = itr.next();
		//initialize string of largest and smallest cast with first movie.
		String largestCaseMovie = currMovie.getTitle();
		String smallestCaseMovie = currMovie.getTitle();
		//holds num cast of current movie.
		int temp = currMovie.getCast().size();
		//initialize smallest, largest cast and average cast.
		float sum = temp;
		int most = temp;
		int least = temp;
		float averagePre = 0;
		int average = 0;

		//loop through the database.
		while(itr.hasNext()){
			//update current movie
			currMovie = itr.next();
			temp = currMovie.getCast().size();

			//getting sum
			sum = sum + temp;

			//find most
			if (temp > most){
				most = temp;
				//if current cast is larger than previous ones, 
				//update largest movie list.
				largestCaseMovie =  currMovie.getTitle();

			} 
			else if (temp == most){
				//if current cast is equal to most cast, append movie name to 
				//largest cast list.
				largestCaseMovie =  largestCaseMovie + 
						", " + currMovie.getTitle();
			}

			//find least
			if(temp < least){
				least = temp;
				//if current cast is smaller than previous ones, 
				//update largest movie list.
				smallestCaseMovie =  currMovie.getTitle();
			}
			else if (temp == least){
				//if current cast is equal to most cast, append movie name to 
				//smallest cast list.
				smallestCaseMovie =  smallestCaseMovie + 
						", " + currMovie.getTitle();
			}
		}

		//find averagePre.
		averagePre = sum/ ((float) movieDB.size());
		//rounds to nearest int.
		average = Math.round(averagePre);

		//convert ints to strings and format it.
		String mostString = Integer.toString(most);
		String leastString = Integer.toString(least);
		String averageString = Integer.toString(average);
		largestCaseMovie = largestCaseMovie + " [" + mostString + "]";
		smallestCaseMovie = smallestCaseMovie + " [" + leastString + "]";

		//combine all info in a string array and return
		String[] statInfo = {mostString, leastString, averageString, 
				largestCaseMovie, smallestCaseMovie};
		return statInfo;

	}

}//end class body


