// TODO *** add comments as specified in the commenting guide ***

import java.util.*;
import java.io.*;
import java.io.IOException;

public class  Twitter{

	public static void main(String[] args) throws FileNotFoundException{

		// TODO *** steps 1 - 3 of the main method ***
		//list contains the name of all users
		ArrayList<String> allUsers = new ArrayList<String>();

		//list of usersname being followed
		ArrayList<String> followedUsers = new ArrayList<String>();

		//list of list of pointers of all user's tweets
		ArrayList<ArrayList<Tweet>> allTweets = new ArrayList<ArrayList<Tweet>>();

		//list of tweets of the user's followed
		Timeline myTimeline = new Timeline();

		//Check if at least one input file is given
		if (args.length < 0){
			//TODO: verify if this is the right output.
			System.out.println("Usage: java MovieFlix FileName1 Filename2 Filename3");
		}
		//number of arguments is valid
		else{
			for(int i = 0 ; i < args.length; i++){
				//TODO: Read data from file and assign. Done!

				String name = args[i].substring(0, args[i].length()-4);
				ArrayList<Tweet> userTweets = new ArrayList<Tweet>();

				try
				{
					//if not a txt, Throw IOexception.
					if( !(args[i].substring(args[i].length() - 4)).equals(".txt") ){
						throw new IOException();
					}

					//Use a scanner to read the file.
					Scanner scnr = new Scanner(new File(args[i]));

					allUsers.add(name);
					followedUsers.add(name);

					//reads information in the file line by line.
					while(scnr.hasNextLine())
					{
						String nxLineInit = scnr.nextLine();

						//end of line causes scnr to read null values. this condition handles this case.
						if (nxLineInit.length() > 0){
							String[] temp = nxLineInit.split(":");

							//if the user input tweet is no more than 1 line
							if (temp.length == 2){
								temp[0] = temp[0].trim();
								temp[1] = temp[1].trim();
								try{
									Tweet tempTweet = new Tweet(Integer.parseInt(temp[0]), temp[1], name);
									userTweets.add(tempTweet);
								}
								catch(TweetTooLongException e){

								}
							}
							/*
							 * if user input is more than one line, we will destroy the old 
							 * tweet and replace it with a new one.
							 * ie,
							 * 2: 	I
							 * 		am
							 * 		hungry
							 * */
							else{
								//keep track of current size
								temp[0] = temp[0].trim();
								int currSize = userTweets.size();
								//store the current message.
								String tmpMsg = userTweets.get(currSize-1).getMessage();
								//append new lines to the message.
								tmpMsg = tmpMsg + "\n" + temp[0];
								//store current time
								int tmpTime = userTweets.get(currSize-1).getTime();
								//destroy old tweet
								userTweets.remove(currSize-1);
								//replace old tweet with appended message.
								try{
									userTweets.add(new Tweet(tmpTime, tmpMsg, name));
								}
								catch(TweetTooLongException e){

								}
							}
						}//end if to make sure that input is not null.
					}//end while for reading file

					//add the list of tweets to the database
					allTweets.add(userTweets);

					scnr.close();
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

			}//end for loop to read multiple user files

			/*//testing to see if database is populated correctly
			for(int i = 0; i < allTweets.size(); i ++){
				System.out.println(allTweets.get(i));
			}
			 */

			//add all tweets to timeline.

			for(int i = 0 ; i < allTweets.size(); i++){
				myTimeline.add(allTweets.get(i));
			}

			//test adding to timeline
			//myTimeline.print();
			/***************************************************************/


			Scanner stdin = new Scanner(System.in);  //for console input

			boolean done = false;
			while (!done) {
				System.out.print("Enter option : ");
				String input = stdin.nextLine();

				//only do something if the user enters at least one character
				if (input.length() > 0) {
					String[] commands = input.split(" ");//split on space
					switch(commands[0]){
					case "list":
						//option for list users
						if((commands.length==2) && commands[1].trim().equals("users")){
							for (int i = 0; i < allUsers.size(); i++){
								System.out.println(allUsers.get(i));
							}
						}
						//option for list following
						else if((commands.length==2) && commands[1].trim().contains("following")){
							for (int i = 0; i < followedUsers.size(); i++){
								System.out.println(followedUsers.get(i));
							}
						}
						//option for anything else
						else{
							System.out.println("Invalid Command");
						}

						break;

					case "follow":

						if (commands.length != 2){
							System.out.println("Invalid Command");
						}
						else {

							//check user valid
							String user = commands[1].trim();
							if (!allUsers.contains(user)){
								System.out.println("Invalid user");
							}
							//check whether user is already followed
							else if(followedUsers.contains(user)){
								System.out.println("Warning: User already followed");
							}else{
								//add user name to followedUsers
								followedUsers.add(user);
								int indexOfUser = allUsers.indexOf(user);

								//update the Timeline
								myTimeline.add(allTweets.get(indexOfUser));
							}
						}
						break;

					case "unfollow":
						if (commands.length != 2){
							System.out.println("Invalid Command");
						}
						else {
							String user = commands[1].trim();
							//check user is valid
							if (!allUsers.contains(user)){
								System.out.println("Invalid user");
							}
							//check whether user is already unfollowed
							else if(!followedUsers.contains(user)){
								System.out.println("Warning: User not followed");
							}
							else{
								//add user name to followedUsers
								followedUsers.remove(user);

								//update the Timeline
								myTimeline.remove(user);
							}
						}
						break;

					case "search":

						if (commands.length < 2){
							System.out.println("Invalid Command");
						}
						else {
							String keyWord = commands[1].trim() + " ";

							for(int i = 2; i < commands.length ; i++){
								keyWord = keyWord + commands[i] + " ";
							}

							keyWord = keyWord.trim();

							Timeline hasKeyword = myTimeline.search(keyWord);
							hasKeyword.print();
						}

						break;
					case "print":
						//option for print
						if (commands.length == 1)
						{
							myTimeline.print();
						}
						//option for print int
						else if (commands.length == 2)
						{
							int time = -1;
							try{
								time = Integer.parseInt(commands[1].trim());
								myTimeline.print(time);
							}
							catch(NumberFormatException e){
								System.out.println("Invalid Command");
							}
							catch(IllegalArgumentException e){
								System.out.println("Invalid Command");
							}
						}
						else{
							System.out.println("Invalid Command");
						}


						break;
					case "quit":
						done = true;
						System.out.println("exit");
						break;
					default:  //a command with no argument
						System.out.println("Invalid Command");
						break;
					}//end switch block
				} //end if
			} //end while for user commands

			/***************************************************************/
		}//end if-else for input args
	} //end main
}//end class body
