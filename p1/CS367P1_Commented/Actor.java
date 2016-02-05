///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  MovieFlix.java
// File:             Actor.java
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
 * The class contruct the Actor (database) and contains its public methods
 * for implementing MovieFlix and private methods for this class.
 *
 * <p>Bugs: All detected bugs are resolved.
 *
 * @author Shu Wen Loo, Yunhe Liu
 */

import java.util.*;

public class Actor {

	//String contains the name of the actor
	private String name;
	//An arraylist that contains that movies the
	//actor starred in
	private ArrayList<String> moviesActed;

	//constrct actor object
	public Actor(String name){

		this.name = name;
		moviesActed = new ArrayList<String>();

	}

	/**
	 * add a list of movie to the movieActed arrayList
	 *
	 * @param (List<String> m) (a list of name of the movie to be added)
	 */
	public void addMovie(List<String> m){

		//No need to format m because the only way we are getting m is from the
		//MovieDatabase. This is already formatted. And we also do not have an 
		//"add actor or add movie option" in MovieFlix.
		
		//input validation
		if (m == null){
			throw new NullPointerException();
		}
		else{
			
			//add the list of movies
			for (int i = 0; i<m.size(); i++){
				moviesActed.add(m.get(i));
			}
		}

	}

	/**
	 * Get the name of an actor object
	 * @return (return a String that contains the actors name)
	 */
	public String getName(){
		return name;
	}

	/**
	 * Get the movieActed of an actor object
	 * @return (returns an arrayList of the moviesActed of an actor object)
	 */
	public ArrayList<String> getMoviesActed(){
		return moviesActed;
	}

	/**
	 * remove the movie m from the movieActed arrayList
	 *
	 * @param (String m) (name of the movie to be removed)
	 * @return (return true if the removal is successful.
	 * otherwise, return false, for example the movie is
	 * not in the movieActed arrayList)
	 */
	public boolean removeMovie(String m){

		//Need to format string because this relies on user input. And users 
		//are dumb.
		
		//input validation
		if (m == null){
			throw new NullPointerException();
		}
		//format the String to desired format
		m = formatString(m);

		//removes the the movie if it is in the movieActed arrayList
		if(moviesActed.contains(m)){
			moviesActed.remove(m);
			return true;
		}
		else{
			return false;
		}

	}

	public String toString(){
		return name;
	}
	
	private String formatString(String s)
	{
		s = s.toLowerCase();
		String[] temp = s.split(" ");
		for (int i = 0; i < temp.length; i++)
		{
			String firstLttr = temp[i].substring(0,1).toUpperCase();
			String endOfWord = temp[i].substring(1, temp[i].length());
			temp[i] = firstLttr + endOfWord;
		}
		String formatted = temp[0];
		for (int i = 1; i < temp.length; i++)
		{
			formatted = formatted + " " + temp[i];
		}
		return formatted;
	}
}
