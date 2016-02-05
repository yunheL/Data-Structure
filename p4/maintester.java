
public class maintester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTree<String> test = new BinaryTree<String>("a");
		test.start();
		
		try{
			System.out.println("In try.");
			test.addLeftChild("b");
			test.addRightChild("c");
			test.goLeft();
			test.addLeftChild("d");
			test.addRightChild("e");
			
			System.out.println("==============Test BinaryTree class================");
			

			//test start();--passed
			System.out.println("Current is the node containing: " + test.getCurrent());
			test.start();
			System.out.println("Test Start(). Curr is " + test.getCurrent());
			test.print();
			System.out.println("Going right.");
			test.goRight();
			System.out.println("Current is " + test.getCurrent() + ". Current is a leaf: " + test.isLeaf());
			System.out.println("Adding left and right to current node.");
			test.addLeftChild("f");
			test.addRightChild("g");
			test.print();
			test.goLeft();
			System.out.println("Current is " + test.getCurrent() + ". Current is a leaf: " + test.isLeaf());
			test.changeCurrent("changed");
			System.out.println("changed right node");
			test.print();
			
			
		}
		catch(IllegalBinaryTreeOpException e){
			System.out.println("In catch.");
		}
	}

}
