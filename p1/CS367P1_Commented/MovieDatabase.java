///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  MovieFlix.java
// File:             MovieDatabase.java
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
 * <p>The class contruct the MovieDatabase and contains its public methods
 * for implementing MovieFlix and private methods for this class.
 *
 * <p>Bugs: All detected bugs are resolved
 *
 * @author Shu Wen Loo, Yunhe Liu
 */

import java.util.*;


public class MovieDatabase implements Iterable<Movie>{
	
	//movieList holds a list of all the movies
	private ArrayList<Movie> movieList;
	
	//movieTitles holds a list of all the movie titles
	private ArrayList<String> movieTitles;

	//Constructs an empty database.
	public MovieDatabase()
	{
		movieList = new ArrayList<Movie>();
		movieTitles = new ArrayList<String>();
	}
	
	/**
	 * <p>Add a movie with the given title t to the end of the database.
	 *  If a movie with the title t is already in the database, just return.
	 *
	 * @param (t) (the title of the movie to be added in)
	 */
	public void addMovie(String t)
	{
		//input validation
		if (t == null)
		{
			throw new NullPointerException();
		}
		//make t to the specified format
		t = formatString(t);
		
		//add movie to the databse if it is not in there
		if(!movieTitles.contains(t)){
			movieList.add(new Movie(t));
			movieTitles.add(t);
		}
	}

	/**
	 * Add the actor with given name n to the movie with the given
	 *  title t in the database. If a movie with the title t is not
	 * in the database throw a java.lang.IllegalArgumentException.
	 *  If n is already in the cast of actors in the movie with 
	 *  title t, just return.
	 *
	 * @param (n) (the actor name to be added to a movie)
	 * @param (t) (the movie name in the database)
	 */
	//n refers to actorName; t refers to movieName
	public void addActor(String n, String t)
	{	
		//input validation
		if (t == null || n == null)
		{
			throw new NullPointerException();
		}
		
		//make input to the correct format
		t = formatString(t);
		n = formatString(n);

		//check whether movie with title t is int the database
		if (movieTitles.contains(t))
		{

			int index = movieTitles.indexOf(t);
			
			//add the actor name to the movie if it is not contained
			if (!movieList.get(index).getCast().contains(n)){
				movieList.get(index).getCast().add(n);
			}
		}
		else
		{
			System.out.println("t is not contained");
		}
	}

	/**
	 * <p>Remove the movie with the title t from the database.
	 *
	 * @param (t) (the name of the movie to be removed)
	 * @return (return false if the movie title t is not in the
	 * database, return true if the removal is successful)
	 */
	public boolean removeMovie(String t)
	{
		//input validation
		if (t == null){
			throw new NullPointerException();
		}
		//make t to the specified format
		t = formatString(t);
		
		int index = movieTitles.indexOf(t);
		
		//return false if the movie title t is not
		//in the database
		if (index < 0)
		{
			return false;
		} 
		//remove the movie with title t if it is
		//in the database
		else
		{
			movieList.remove(index);
			movieTitles.remove(index);
			return true;
		}
	}

	/**
	 * <p>Check whether the movie with titile t is in the database.
	 *
	 * @param (t) (titile of the movie to be checked)
	 * @return (Return true iff a movie with the title t is in the database.)
	 */
	public boolean containsMovie(String t){
		//input validation
		if (t == null){
			throw new NullPointerException();
		}
		//make t to the specified format
		t = formatString(t);
		return movieTitles.contains(t);

	}

	/**
	 * <p>Check whether the actor with name n is the cast for at
	 * least one movie in the database.
	 *
	 * @param (n) (name of the actor to be checked)
	 * @return (eturn true iff an actor with the name n appears in
	 *  the cast for at least one movie in the database.
	 */
	public boolean containsActor(String n){
		//input validation
		if (n == null){
			throw new NullPointerException();
		}
		//make the string formatted as specified
		n = formatString(n);
		
		//loop throught the movieList to see whether
		//the actor is in the database
		for (int i = 0 ; i < movieList.size(); i++){
			if (movieList.get(i).getCast().contains(n)){
				return true;
			}
		}
		return false;	
	}
	
	/**
	 * Checks whether the given actor n is a cast for the  given
	 * movie t.
	 *
	 * @param (n) (name of the actor to be checked)
	 * @param (t) (title of the movie to be checked)
	 * @return (Returns true iff the given actor n is cast in the movie
	 *  with the given title t. If a movie with the title t is
	 *  not in the database, return false.)
	 */
	public boolean isCast(String n, String t){
		//input validation
		if (n == null || t == null){
			throw new NullPointerException();
		}
		
		//format the Strings as specified
		t = formatString(t);
		n = formatString(n);
		
		//check whether actor n is a cast for movie t
		if (movieTitles.contains(t))
		{
			int index = movieTitles.indexOf(t);
			return movieList.get(index).getCast().contains(n);
		}

		return false;
	}

	/**
	 * Return the cast of actors for the movie with the given title t. 
	 * If a movie with the title t is not in the database, return null.
	 *
	 * @param (t) (title of the movie whose cast will be returned)
	 * @return (return a list contains the cast of the movie with title
	 * t will be returned)
	 */
	public List<String> getCast(String t){
		//input validation
		if (t == null){
			throw new NullPointerException();
		}
		
		//format String as specified
		t = formatString(t);
		
		//get the index of the movie
		int index = movieTitles.indexOf(t);
		
		//get the cast of the movie if the movie is in the
		//database
		if (index > -1){
			return movieList.get(index).getCast();
		}
		return null;		
	}

	/**
	 * Get the list of movie starred by a given actor named n.
	 *
	 * @param (n) (The name of the actor whose movies will be returned)
	 * @return ( Return the list of movies in which the actor with the given
	 * name n is cast. If an actor with the name n is not in the
	 * database, return null.)
	 */
	public List<String> getMovies(String n){
		//input validation
		if (n == null){
			throw new NullPointerException();
		}
		//format the String as specified
		n = formatString(n);
		
		List<String> isInMovie = new ArrayList<String>();
		
		//add the movie to the list if it is starred by the given actor
		for (int i = 0; i < movieList.size(); i++){

			if(movieList.get(i).getCast().contains(n)){
				isInMovie.add(movieList.get(i).getTitle());
			}

		}

		return isInMovie;

	}
	
	/**
	 * Get the number of movies in this database.
	 * @return (Return the number of movies in this database.)
	 */
	public int size(){
		return movieList.size();
	}

	/**
	 * Remove the actor with the given name n from the every movie
	 * in which they are cast.
	 * If a actor with the name n is not in the 
	 * database, return false; otherwise (i.e., the removal
	 * is successful) return true.
	 *
	 * @param (n) (name of the actor whose name need to be removed)
	 * @return (If a actor with the name n is not in the 
	 * database, return false; otherwise (i.e., the removal
	 * is successful) return true.)
	 */
	public boolean removeActor(String n){
		//input validation
		if (n == null){
			throw new NullPointerException();
		}
		
		//format String as specified
		n = formatString(n);
		
		//check whether the actor is in the database
		if (!actorInDatabase(n)){
			return false;
		}

		//remove the actor from all the movies him/her stars in.
		for (int i = 0 ; i < movieList.size(); i++){
			if(movieList.get(i).getCast().contains(n)){
				movieList.get(i).getCast().remove(n);
			}
		}	
		return true;
	}

	/**
	 * Check whether the actor is in the movie database.
	 *
	 * @param (n) (name of the actor to be checked)
	 * @return (return true if the actor is in the database,
	 * otherwise return false)
	 */
	private boolean actorInDatabase(String n){

		for (int i = 0 ; i < movieList.size(); i++){
			if (movieList.get(i).getCast().contains(n)){
				return true;
			}
		}

		return false;	

	}

	/*
	//toString method for debugging.
	public String toString(){
		String s = "";

		for(int i = 0; i<movieList.size(); i++){
			s = s + movieList.get(i).getTitle() + ": " + "\n" + 
					movieList.get(i).getCast().toString() + "\n";
			//System.out.println(movieList.get(i).getCast().toString());
		}

		return s;
	}
	*/

	/**
	 * Taken in a String and returned the String in the format that
	 * the first letter of each words capitalized and all other letters
	 * in the lowercase.
	 *
	 * @param (s) (the String to be formatted)
	 * @return (return a String with correct format)
	 */
	private String formatString(String s)
	{
		//change the string to lower case and create an array that
		//stores each word as an array element
		s = s.toLowerCase();
		String[] temp = s.split(" ");
		
		//Capitalize the first letter of each word
		for (int i = 0; i < temp.length; i++)
		{
			
			String firstLttr = temp[i].substring(0,1).toUpperCase();
			String endOfWord = temp[i].substring(1, temp[i].length());
			temp[i] = firstLttr + endOfWord;
		
		}
		
		//concatenate the elements of the array to get the formatted String
		String formatted = temp[0];
		for (int i = 1; i < temp.length; i++)
		{
			formatted = formatted + " " + temp[i];
		}
		return formatted;
	}

	/**
	 * Get an Iterator over the Movie objects in the database.
	 *
	 * @return (Return an Iterator over the Movie objects in the database.)
	 */
	public Iterator<Movie> iterator() {
		return new MovieIterator<Movie>(movieList);
	}
}
