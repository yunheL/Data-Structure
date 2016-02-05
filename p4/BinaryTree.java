///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  GuessingGame
// File:             BinaryTree.java
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
 * This class implement the constructors and methods that a Binary Tree
 * data structure has which will be used to implement the GuessingGame
 * Class.
 *
 * <p>Bugs: All detected bugs are resolved.
 *
 * @author Shu Wen Loo, Yunhe Liu
 */

public class BinaryTree<E> {
	
	//the root node of the Binary Tree data structure
	private BinaryTreenode<E> root;
	
	//the node in the binary tree that we are currently looking at
	private BinaryTreenode<E> curr;
	
	/**
 	* Constructs an empty BinaryTree with a null root.
 	*/
	public BinaryTree(){
		curr = null;
		root = new BinaryTreenode<E>();
	}
	
	/**
 	* Constructs a BinaryTree with data stored in its root.
 	*/
	public BinaryTree(E data){
		curr = null;
		root = new BinaryTreenode<E>(data);
	}
    
    /**
	* Starts the current reference at the root of the tree to 
	* begin navigation of the tree.
 	*/                                                                       	
	public void start(){
		//put the curr node reference at the root node
		curr = root;
	}
	
	/**
	* Returns the data stored in the current node in navigation. 
	* Throws an IllegalBinaryTreeOpException if there is no 
	* current node in navigation.
 	*
 	* @return the data in the curr node
 	* @throws IllegalBinaryTreeOpException
 	*/
	public E getCurrent() throws IllegalBinaryTreeOpException{
		//if curr is null, throws IllegalBinaryTreeOpException
		if (curr == null){
			throw new IllegalBinaryTreeOpException("Current node is null. No data can be accessed");
		}
		
		//returns the data in the current node
		return curr.getData();
	}
	
	/**
	* Moves the current reference to the left child of the current node in
	* navigation. Throws an IllegalBinaryTreeOpException if the current node
	* does not have a left child.
 	*
 	* @throws IllegalBinaryTreeOpException
 	*/
	public void goLeft() throws IllegalBinaryTreeOpException{
	
		//throw exception if curr is null
		if (curr == null){
			throw new IllegalBinaryTreeOpException("Curr is null");
		}
		
		//throw exception if there the current node has no left child
		if (curr.getLeft() == null){
			throw new IllegalBinaryTreeOpException("Cannot go left");
		}
		
		//set the curr node to its left child
		curr = curr.getLeft();
	}
	
	/**
	* Moves the current reference to the right child of the current node in 
	* navigation. Throws an IllegalBinaryTreeOpException if the current node 
	* does not have a right child.
 	*
 	* @throws IllegalBinaryTreeOpException
 	*/
	public void goRight() throws IllegalBinaryTreeOpException{
	
		//throw exception if curr is null
		if (curr == null){
			throw new IllegalBinaryTreeOpException("Curr is null");
		}
	
		//throw exception if there the current node has no right child
		if (curr.getRight() == null){
			throw new IllegalBinaryTreeOpException("Cannot go right");
		}
		
		//set the curr node to its right child
		curr = curr.getRight();
	}
	
	/**
	* Returns true if the current node in navigation is a leaf 
	* (i.e., has no children).
 	*
 	* @return (returns a boolean that is true if and only if 
 	* current node has no child)
 	* @throws IllegalBinaryTreeOpException
 	*/
	public boolean isLeaf() throws IllegalBinaryTreeOpException{
		
		//throw exception if curr is null
		if (curr == null){
			throw new IllegalBinaryTreeOpException("Curr is null");
		}
		
		//return true if curr has no children
		return (curr.getLeft() == null && curr.getRight() == null);		
	}

	/**
	* Changes the data held by the current node in navigation to the
	* specified data.
 	*
 	* @param (E data) (data to be inserted in the curr node)
 	* @throws IllegalBinaryTreeOpException
 	*/
	public void changeCurrent (E data) throws IllegalBinaryTreeOpException{
		//throw exception if curr is null
		if (curr == null){
			throw new IllegalBinaryTreeOpException("Curr is null");
		}
		//set data in the curr node to the data passed in
		curr.setData(data);
	}
	
	
	/**
	* Adds a node with the specified child as the right child of the current 
	* node in navigation. Throws an IllegalBinaryTreeOpException if the current
	* node already has a right child.
 	*
 	* @param (E data) (data to be inserted in the new right child node)
 	* @throws IllegalBinaryTreeOpException
 	*/
	public void addRightChild(E data) throws IllegalBinaryTreeOpException{
		
		//throw exception if curr is null
		if (curr == null){
			throw new IllegalBinaryTreeOpException("Curr is null");
		}
		
		//if the curr node does not have a right child
		if(curr.getRight() == null){
			//add a node at right child of curr node with specified data
			curr.setRight(data);
		}
		//throw exception if curr is has right child already
		else{
			throw new IllegalBinaryTreeOpException("Current node already has right child.");
		}
	}
	
	
	/**
	* Adds a node with the specified child as the left child of the current 
	* node in navigation. Throws an IllegalBinaryTreeOpException if the current
	* node already has a left child.
 	*
 	* @param (E data) (data to be inserted in the new left child node)
 	* @throws IllegalBinaryTreeOpException
 	*/
	public void addLeftChild(E data) throws IllegalBinaryTreeOpException{
		
		//throw exception if curr is null
		if (curr == null){
			throw new IllegalBinaryTreeOpException("Curr is null");
		}
		
		//if the curr node does not have a left child
		if(curr.getLeft() == null){
			curr.setLeft(data);
		}
		//throw exception if curr is has right child already
		else{
			throw new IllegalBinaryTreeOpException("Current node already has left child.");
		}
	}
	
	/**
	* Pre-order prints the tree, starting at the root. Each additional level
	* of the tree should be incremented by three spaces. Method implemented using
	* recursion.
 	*/
	public void print(){
		//call companion method to pass in parameter
		
		//see whether the root has no data in it and has no child
		if(root.getData() == null && (root.getLeft() == null && root.getRight() == null)){
			System.out.println("Empty Tree");
			return;
		}
			
		print(root, "a");
	}
	
	/**
	* Adds a node with the specified child as the left child of the current 
	* node in navigation. Throws an IllegalBinaryTreeOpException if the current
	* node already has a left child.
 	*
 	* @param (BinaryTreenode<E> n) (n refers the the current node we are looking at)
 	* @param (String append) (String containing the data in the node and correct length
 	* of space in front of it to be printed)
 	*/
	private void print(BinaryTreenode<E> n, String append){
		//base case: if n is leaf, then return
		if (n == null) return;
		
		//print data in n before go left or right
		String toPrint = append + n.getData();
		System.out.println(toPrint.substring(1));
		
		//update append
		append = append + "   ";
		
		//traversal left child recursively
		print(n.getLeft(), append);
		
		//traversal right child recursively
		print(n.getRight(), append);
		
	}
	
}
