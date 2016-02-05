import java.util.List;

/**
 * The Timeline class uses SimpleLinkedList to build a time ordered list of 
 * following tweets. Tweets with smaller Time fields should come earlier in the list.
 * this is a stack with front after the header node.
 */
class Timeline{

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
		if( tweet == null){
			throw new NullPointerException();
		}
		//add to non-empty list. if not, java will scream at you
		if(list.size() != 0){
			int indToAdd = searchIndex(tweet.getTime());
			list.add(indToAdd, tweet);
		}
		else{
			list.add(tweet);
		}
	}

	/**
	 * Adds an ordered list of tweets to the Timeline
	 * @param tweets the list of tweets to add
	 */
	public void add(List<Tweet> tweets){
		if( tweets == null){
			throw new NullPointerException();
		}
		for(int i = 0 ; i < tweets.size(); i++){
			
			if(list.size() != 0){
				
			int indToAdd = searchIndex(tweets.get(i).getTime());
			list.add(indToAdd, tweets.get(i));
			
			}
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
		if( user == null){
			throw new NullPointerException();
		}

		for (int i = 0; i < list.size(); i ++)
		{
			if(list.get(i).getUser().equals(user))
			{
				list.remove(i);
				i--;//size changes so need to decrement counter.
				//System.out.println("User Removed: " + tempTweet.getUser());
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
		if( keyword == null){
			throw new NullPointerException();
		}
		
		keyword = keyword.trim();
		Timeline withKeys = new Timeline();

		for(int i = 0 ; i < list.size() ; i++){
			if(list.get(i).getMessage().contains(keyword)){
				withKeys.add(list.get(i));
			}
		}
		return withKeys;
	}

	/**
	 * Print each tweet in the timeline
	 */
	public void print(){
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

		if (time < 0)
		{
			throw new IllegalArgumentException();
		}

		for(int i = 0; i < list.size(); i ++)
		{
			if (list.get(i).getTime()<time){
				list.get(i).print();
			}

		}
	}// end of print method


	private int searchIndex(int time){
		int index = 0;
		
		while(time > list.get(index).getTime() && (index < list.size()-1) ){
			index++;
		}
		if(index == (list.size()-1)){
			index++;
		}
		return index;
	}

}// end of the class

