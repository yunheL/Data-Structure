//////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Twitter.java
// File:             Timeline.java
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
 * The Timeline class uses SimpleLinkedList to build a time ordered list of 
 * following tweets. Tweets with smaller Time fields should come earlier in the 
 * list this is a stack with front after the header node.
 * 
 * <p>Bugs: All detected bugs are resolved.
 *
 * @author Shu Wen Loo, Yunhe Liu
 */
import java.util.List;

class Timeline{

	//linked list to store all followed tweets
	private SimpleLinkedList<Tweet> list;

	/**
	 * Constructs an empty timeline
	 */
	public Timeline(){
		list = new SimpleLinkedList<Tweet>();
	}

	/**
	 * Adds a single tweet to the Timeline
	 * 
	 * @param tweet the tweet to add
	 */
	public void add(Tweet tweet){
		//if tweet is null, throw exception
		if( tweet == null){
			throw new NullPointerException();
		}
		//add to non-empty list. if not, java will scream at you
		if(list.size() != 0){

			//search for the index to add the tweet to.
			int indToAdd = searchIndex(tweet.getTime());
			//add tweet to the right index
			list.add(indToAdd, tweet);
		}
		//when list is empty, just add to the end.
		else{
			list.add(tweet);
		}
	}

	/**
	 * Adds an ordered list of tweets to the Timeline
	 * @param tweets the list of tweets to add
	 */
	public void add(List<Tweet> tweets){
		//check if parameter is null
		if( tweets == null){
			throw new NullPointerException();
		}
		//loop through list of tweets and add each one to the timeline
		for(int i = 0 ; i < tweets.size(); i++){
			//add to non-empty list. if not, java will scream at you
			if(list.size() != 0){

				//search for the index to add the tweet to.
				int indToAdd = searchIndex(tweets.get(i).getTime());
				//add tweet to the right index
				list.add(indToAdd, tweets.get(i));

			}
			//when list is empty, just add to the end.
			else{
				list.add(tweets.get(i));
			}
		}
	}

	/**
	 * Removes all tweets by user from the Timeline
	 * 
	 * @param user the user whose tweets should be removed
	 */
	public void remove(String user){
		//check if parameter is null
		if( user == null){
			throw new NullPointerException();
		}

		//loop through the timeline and remove tweets from a certain user
		for (int i = 0; i < list.size(); i ++)
		{
			///if the tweet is by the secified user, remove it.
			if(list.get(i).getUser().equals(user))
			{
				list.remove(i);
				i--;//size changes so need to decrement counter.
			}
		}
	}

	/**
	 * Create a new Timeline containing only the tweets containing keyword
	 * 
	 * @param keyword the keyword to search for
	 * @return a Timeline of tweets containing keyword
	 */
	public Timeline search(String keyword){
		//check for null arguments
		if( keyword == null){
			throw new NullPointerException();
		}

		//strip keyword of trailing whitespace.
		keyword = keyword.trim();
		//create timeline to be returned
		Timeline withKeys = new Timeline();

		//loop through timeline and add tweets containing keyword to new 
		//timeline
		for(int i = 0 ; i < list.size() ; i++){
			
			if(list.get(i).getMessage().contains(keyword)){
				withKeys.add(list.get(i));
			}
			
		}
		return withKeys; //return new timeline
	}

	/**
	 * Print each tweet in the timeline
	 */
	public void print(){
		//loop through timeline and print each tweet
		for(int i = 0; i < list.size(); i ++)
		{
			list.get(i).print();
		}
	}   

	/**
	 * Print each tweet in the timeline since time
	 * 
	 * @param time the largest time to print tweets
	 */
	public void print(int time){

		//check for invalid time
		if (time < 0)
		{
			throw new IllegalArgumentException();
		}

		//if time is valid, loop through timeline and print each tweet
		//since that time.
		for(int i = 0; i < list.size(); i ++)
		{
			//print tweet if the time is > time specified
			if (list.get(i).getTime()<time){
				list.get(i).print();
			}

		}
	}// end of print method


	/**
	 * Searches for the index of the next tweet which id bigger than the 
	 * given time. Used when adding new tweets to the list.
	 * 
	 * @param time : the time of the tweet.
	 * @return int : index where the new tweet should be inserted.
	 * */
	private int searchIndex(int time){
		//start at zero.
		int index = 0;

		//while haven't reached the second last element, and time of new tweet 
		//less than time of the current tweet
		while(time > list.get(index).getTime() && (index < list.size()-1) ){
			//increment index.
			index++;
		}
		//check last element of the list
		if(index == (list.size()-1)){
			//set index to the last index if time > last tweet time.
			if(time > list.get(index).getTime()) 
				index++;
		}
		return index;
	}

}// end of the class

