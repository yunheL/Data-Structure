///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Program 2
// Files:            Dblistnode.java ListADT.java Tweet.java Timeline.java
//					 Twitter.java (TweetTooLongException.java)
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
/**
 * Uses the console to input commands to display information and modify
 * the Timeline and User List. The database is populated from a txt file
 * containing user's tweets and time.
 *
 * <p>Bugs: All detected bugs are resolved.
 *
 * @author Shu Wen Loo, Yunhe Liu
 */

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
		ArrayList<ArrayList<Tweet>> allTweets = 
				new ArrayList<ArrayList<Tweet>>();

		//list of tweets of the user's followed
		Timeline myTimeline = new Timeline();

		//Check if at least one input file is given
		if (args.length < 0){
			//TODO: verify if this is the right output.
			System.out.println("Usage: java Twitter FileName + "
					+ "(multiple file name allowed)");
		}
		//number of arguments is valid
		else{
			for(int i = 0 ; i < args.length; i++){
				//TODO: Read data from file and assign. Done!
				
				
				//find the name of the user from the file name
				String name = args[i].substring(0, args[i].length()-4);
				//list containing all the tweets from one user. 
				//Used to construct the list of lists of all tweets.
				ArrayList<Tweet> userTweets = new ArrayList<Tweet>();

				try
				{
					//if not a txt, Throw IOexception.
					if( !(args[i].substring(args[i].length() - 
							4)).equals(".txt") ){
						throw new IOException();
					}

					//Use a scanner to read the file.
					Scanner scnr = new Scanner(new File(args[i]));
					
					//add the user names to the name lists
					allUsers.add(name);
					followedUsers.add(name);

					//reads information in the file line by line.
					while(scnr.hasNextLine())
					{
						String nxLineInit = scnr.nextLine();

						//end of line causes scnr to read null values.
						//this condition handles this case.
						if (nxLineInit.length() > 0){
							String[] temp = nxLineInit.split(":");

							//if the user input tweet is no more than 1 line
							if (temp.length == 2){
								temp[0] = temp[0].trim();
								temp[1] = temp[1].trim();
								try{
									Tweet tempTweet = new Tweet(
											Integer.parseInt(temp[0]), temp[1], 
											name);
									userTweets.add(tempTweet);
								}
								catch(TweetTooLongException e){

								}
							}
							/*
							 * if user input is more than one line, 
							 * we will destroy the old tweet and replace it 
							 * with a new one.
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
								String tmpMsg = userTweets.get(
										currSize-1).getMessage();
								//append new lines to the message.
								tmpMsg = tmpMsg + "\n" + temp[0];
								//store current time
								int tmpTime = userTweets.get(
										currSize-1).getTime();
								//destroy old tweet
								userTweets.remove(currSize-1);
								//replace old tweet with appended message.
								try{
									userTweets.add(new Tweet(
											tmpTime, tmpMsg, name));
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

			//Test code
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
			/***********File reading and list creating finished**********/


			Scanner stdin = new Scanner(System.in);  //for console input

			boolean done = false;
			while (!done) {
				//prompt user input
				System.out.print("Enter option : ");
				//read in user input
				String input = stdin.nextLine();

				//only do something if the user enters at least one character
				if (input.length() > 0) {
					String[] commands = input.split(" ");//split on space
					switch(commands[0]){
					case "list":
						//option for list users
						//input validation
						if((commands.length==2) && 
								commands[1].trim().equals("users")){
							//loop through the list and print all usernames
							for (int i = 0; i < allUsers.size(); i++){
								System.out.println(allUsers.get(i));
							}
						}
						//option for list following
						//input validation
						else if((commands.length==2) && 
								commands[1].trim().contains("following")){
							//loop through the list and print all followed users
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
						//input validation
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
								System.out.println("Warning: "
										+ "User already followed");
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
						//input validation
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
								System.out.println("Warning: "
										+ "User not followed");
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
						//input validation
						if (commands.length < 2){
							System.out.println("Invalid Command");
						}
						else {
							String keyWord = commands[1].trim() + " ";
							
							//concatenate the keyWords if there are more than
							//one word in keyWord
							for(int i = 2; i < commands.length ; i++){
								keyWord = keyWord + commands[i] + " ";
							}

							keyWord = keyWord.trim();
							
							//create a new Timeline with only the keyWord in it
							Timeline hasKeyword = myTimeline.search(keyWord);
							hasKeyword.print();
						}

						break;
					case "print":
						//option for print
						//input validation
						if (commands.length == 1)
						{
							myTimeline.print();
						}
						//option for print int
						//input validation
						else if (commands.length == 2)
						{
							int time = -1;
							//extract time parameter from the input command
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
						//quit from the loop, exit the program
						done = true;
						System.out.println("exit");
						break;
					
					default:
						System.out.println("Invalid Command");
						break;
					}//end switch block
				} //end if
			} //end while for user commands

			/***************************************************************/
		}//end if-else for input args
	} //end main
}//end class body
