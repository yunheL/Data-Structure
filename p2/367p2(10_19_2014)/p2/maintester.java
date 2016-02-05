/**
 * This class consists of 3 parts, a coin toss simulator, a grade estimator
 * and a color challenge. The user selects one of these options from a menu. To
 * quit, the user selects the 'q' key.
 *
 * <p>Bugs: (no known bugs)
 *
 * @Shu Wen Loo
 */

/**
 * @author Loo Shu Wen
 *
 */
public class maintester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//throw new TweetTooLongException();
		SimpleLinkedList<String> testList = new SimpleLinkedList<String>();
		SimpleLinkedList<String> testListEmpty = new SimpleLinkedList<String>();
//		Tweet t1 = new Tweet(1, "hello world", "Jim");
//		Tweet t2 = new Tweet(1, "hello world", "Jim");
//		
//		System.out.println(t1);
//		System.out.println("tweets are equal " + t1.equals(t2));
//		
//		SimpleLinkedList<Tweet> tweetList = new SimpleLinkedList<Tweet>();
//		tweetList.add(t2);
//		
//		//System.out.println(tweetList.contains(t2));
//		System.out.println(t1.equals(t2));
		
		//initializing
		for (int i = 0; i < 5; i++)
			testList.add(Integer.toString(i));
		
		//test initializing -- passed
//		System.out.print(testList);
//		System.out.println(testList.size());
		
		//test remove
		//test IndexOutOfbounds --passed
//		testList.remove(testList.size());
		
		//test remove all cases --passed
//		for (int i = 0; i < 5; i++)
//		{	
//			System.out.println(testList);
//			testList.remove(2);
//			
//		}
//		System.out.println(testList);
		
		//test add
//		System.out.println(testList);
//		for (int i = 0; i < 3; i++)
//		{
//			testList.add(testList.size(), Integer.toString(i+10));
//			System.out.println(testList);
//		}
		
		
		//test contains --can compare primitive type but not tweets
//		System.out.println(testList.contains("2"));
//		System.out.println(testList.contains("20"));
		
		//test get-- passed
//		System.out.println(testList.get(testList.size()-1));
//		System.out.println(testList.get(30));
		
		//test isEmpty --passed
//		System.out.println(testList.isEmpty());
//		System.out.println(testListEmpty.isEmpty());
		
		//test .size() --passed
//		System.out.println(testList);
//		System.out.println(testList.size());
//		System.out.println(testListEmpty.size());
		
		
	}

}
