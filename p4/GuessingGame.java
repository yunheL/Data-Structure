///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Program 4
// Files:            GuessingGame.java
//					 BinaryTree.java
//					 BinaryTreenode.java
//					 IllegalBinaryTreeOpException.java
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
import java.util.Scanner;

/**
 * Plays the 20Q game. The program uses a BST to store the information. If the 
 * program makes a wrong guess, it "learns" from the user by prompting the user 
 * for input. 
 *
 * <p>Bugs: All known bugs have been resolved.
 *
 * @author Shu Wen Loo, Yunhe Liu
 */

public class GuessingGame {

	/**
	 * Prompts user for commands to play the game.
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*************************input file reading*************************/
		//Scanner variable. Will either be initialized with a file, or 
		//initialized to be used with the console. 
		Scanner scnr;
		
		//if no input file given, initialize scnr to be used with the console.
		if (args.length != 1){
			scnr = new Scanner(System.in);
		}
		//if input file provided,
		else{
			
			//make sure file does exist and accessible
			try{
				//check if file is a .txt file.
				if( !(args[0].substring(args[0].length()- 4)).equals(".txt") ){
					throw new IOException();
				}
				
				//if file is a txt, then initialize scnr with the file.
				scnr = new Scanner(new File(args[0]));
			}
			//if input file cannot be found, continue to console.
			catch(FileNotFoundException e){
				System.out.println("Cannot find the specified file");
				scnr = new Scanner(System.in);
			}
			//if input file cannot be read, or invalid format, continue to 
			//console.
			catch(IOException e){
				System.out.println("Cannot find the specified file");
				scnr = new Scanner(System.in);
			}		
		}
		
		/***************************Game section***************************/
		boolean done = false; //Flag to indicate if game is over.
		//Binary tree to store information.
		BinaryTree<String> dataTree = new BinaryTree<String>();

		//play the game
		while (!done){
			//prompt user for input.
			System.out.println("Please enter a command (o, p, q, r):");
			//get user input
			String input = scnr.nextLine();
			//convert user input to lower case.
			input = input.trim().toLowerCase();

			//switch statement for user options.
			switch(input){

			case "o":
				//print dataTree.
				dataTree.print();
				break;

			case "p":
				//play the game. Reset current node to the root.
				dataTree.start();
				
				try{
					
					//if the root is empty, reset the tree.
					if (dataTree.isLeaf()){
						dataTree = resetTree(dataTree, scnr);
					}
					
					//private method to play the game.
					playGame(dataTree, scnr);
					
				}
				catch (IllegalBinaryTreeOpException e){
					System.out.print("Current node is null.");
				}
				break;

			case "q":
				scnr.close();
				done = true;
				break;

			case "r":
				dataTree = resetTree(dataTree, scnr);
				break;
			}//end of switch statement

		}//end of option while loop
	}//end of main method

	/**
	 * Resets the binary tree. Constructs a binary tree with only two children.
	 *
	 * @param (dataTree) a Binary Tree containing all the information.
	 * @param (scnr) a Scanner object to read in user input.
	 * @return (BinaryTree<String>) new binary tree.
	 */
	private static BinaryTree<String> resetTree(BinaryTree<String> dataTree, 
			Scanner scnr){
		//variable to store input.
		String input;

		//Prompt user enter question for the root node
		System.out.println("Please enter a question.");
		input  = scnr.nextLine().trim();
		//set root to the new binary tree.
		dataTree = new BinaryTree<String> (input);
		//set the current node to the root.
		dataTree.start();

		//initialize left branch
		try {
			//Prompt user enter question for the left child
			System.out.println("Please enter something that is "
					+ "true for that question.");
			input  = scnr.nextLine().trim();
			//add user input to left child.
			dataTree.addLeftChild(input);
		}
		catch (IllegalBinaryTreeOpException e){
			//catch exception if root already has a left child.
			System.out.println("Current node already has left child.");
		}

		//initialize right branch
		try {
			//Prompt user enter question for the right child
			System.out.println("Please enter something that is false "
					+ "for that question.");
			input  = scnr.nextLine().trim();
			//add user input to right child.
			dataTree.addRightChild(input);
		}
		catch (IllegalBinaryTreeOpException e){
			//catch exception if root already has a right child.
			System.out.println("Current node already has right child.");
		}

		//return reference to the new tree.
		return dataTree;
	}

	/**
	 * Plays the 20Q game. Will keep traversing the tree until a leaf is found.
	 * If the user replies y, will traverse left, if false, will traverse left.
	 * If a leaf is encountered, it will display the leaf's data as the answer.
	 * Will prompt the user for input if the guess was wrong.
	 *
	 * @param (dataTree) a Binary Tree containing all the information.
	 * @param (scnr) a Scanner object to read in user input.
	 * @return void.
	 */
	private static void playGame(BinaryTree<String> dataTree, Scanner scnr){
		
		try{
			//while a leaf is not null, keep traversing through the tree.
			while (!dataTree.isLeaf()){
				//print out the current node.
				System.out.println(dataTree.getCurrent());
				//get nd trim input
				String input = scnr.nextLine();
				input = input.trim().toLowerCase();

				//traverse left if input if "y", else, go right.
				if (input.equals("y")){
					dataTree.goLeft();
				}
				else{
					dataTree.goRight();
				}			

			}//end while leaf is not reached.

			//Make a guess.
			System.out.println("I guess: " + dataTree.getCurrent() +
					". Was I right?");
			//get and format user input.
			String input = scnr.nextLine();
			input = input.trim().toLowerCase();

			//if computer guessed correctly, display "I win!".
			if (input.equals("y")){
				System.out.println("I win!");
			}
			//if not, make the computer learn.
			else{
				//Get new question from user.
				System.out.println("Darn. Ok, tell me a question that is true"
						+ " for your answer, but false for my guess.");
				input = scnr.nextLine();
				input = input.trim();

				//store old answer in temporary variable.
				String temp = dataTree.getCurrent();
				//change current node to new question.
				dataTree.changeCurrent(input);

				//get user's answer.
				System.out.println("Thanks! And what were you thinking of?");
				input = scnr.nextLine();
				input = input.trim();

				//create more branches on the tree.
				dataTree.addLeftChild(input);
				dataTree.addRightChild(temp);
			}	


		}//end try
		catch (IllegalBinaryTreeOpException e){
			System.out.print("Current node is null.");
		}//end catch

	}//end of playGame method
}//end of main class
