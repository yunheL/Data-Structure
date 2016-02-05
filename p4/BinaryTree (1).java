
public class BinaryTree<E> {
	
	private BinaryTreenode<E> root;
	private BinaryTreenode<E> curr;
	
	public BinaryTree(){
		curr = null;
		root = new BinaryTreenode<E>();
	}
	
	public BinaryTree(E data){
		curr = null;
		root = new BinaryTreenode<E>(data);
	}
	
	public void start(){
		curr = root;
	}
	
	public E getCurrent() throws IllegalBinaryTreeOpException{
		if (curr == null){
			throw new IllegalBinaryTreeOpException("Current node is null. No data can be accessed");
		}
		return curr.getData();
	}
	
	public void goLeft() throws IllegalBinaryTreeOpException{
		if (curr.getLeft() == null){
			throw new IllegalBinaryTreeOpException("Cannot go left");
		}
		curr = curr.getLeft();
	}
	
	public void goRight() throws IllegalBinaryTreeOpException{
		if (curr.getRight() == null){
			throw new IllegalBinaryTreeOpException("Cannot go right");
		}
		curr = curr.getRight();
	}
	
	public boolean isLeaf() throws IllegalBinaryTreeOpException{
		if (curr == null){
			throw new IllegalBinaryTreeOpException("Curr is null");
		}
		return (curr.getLeft() == null && curr.getRight() == null);		
	}

	public void changeCurrent (E data) throws IllegalBinaryTreeOpException{
		if (curr == null){
			throw new IllegalBinaryTreeOpException("Curr is null");
		}
		curr.setData(data);
	}
	
	public void addRightChild(E data) throws IllegalBinaryTreeOpException{
		if(curr.getRight() == null){
			curr.setRight(data);
		}
		else{
			throw new IllegalBinaryTreeOpException("Current node already has right child.");
		}
	}
	
	public void addLeftChild(E data) throws IllegalBinaryTreeOpException{
		if(curr.getLeft() == null){
			curr.setLeft(data);
		}
		else{
			throw new IllegalBinaryTreeOpException("Current node already has left child.");
		}
	}
	
	public void print(){
		//call companion method to pass in parameter
		if(root == null || (root.getLeft() == null && root.getRight() == null)){
			System.out.println("Empty Tree");
			return;
		}
			
		print(root, "a");
	}
	
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
