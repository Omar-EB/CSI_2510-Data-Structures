/*** 
 * This is class implements a trie that holds a set of strings.
 * MyTrie stores stores nodes using class TreeNode
 * 
 * Name:   
 * Student Number: 
 * Uottawa Email: 
 * 
 *
 */

public class MyTrie {
	
	private TreeNode root = null;

	private int numNodes;

    // Constructor. Note that an empty trie (no strings added) contains the root node 
	public MyTrie() {
		root = new TreeNode(null, null, null,false); 
		numNodes=1;
	}

	// Method to be implemented by you. See handout part 1A
	public boolean insert(String s) {
	
		if (s==null){
			throw new NullPointerException("argument is null");
		}
		
		int len = s.length() ;
		
		for(int i = 0; i<len; i++){
			if (!(s.charAt(i)=='0' || s.charAt(i)=='1')){
				throw new UnsupportedOperationException("Invalid argument (binary (0,1) only): " + s);
			}
		}
		
		char current ;
		TreeNode currentNode = root ;
		for(int i = 0; i<len; i++){
			current = s.charAt(i) ;
			if(i!=len-1) {
				if(current == '1'){
					if (currentNode.getRightChild()!=null){
						currentNode = currentNode.getRightChild() ;
					} else {
						currentNode.setRightChild(new TreeNode(currentNode,null,null,false)) ;
						currentNode=currentNode.getRightChild() ;
						numNodes++ ;
					}
				} else {
					if (currentNode.getLeftChild()!=null){
						currentNode = currentNode.getLeftChild() ;
					} else {
						currentNode.setLeftChild(new TreeNode(currentNode,null,null,false)) ;
						currentNode=currentNode.getLeftChild() ;
						numNodes++ ;
					}
				}
			} else {
				if(current == '1'){
					if (currentNode.getRightChild()!=null){
						if (currentNode.getRightChild().getIsUsed()){
							return false ;
						} 
						currentNode.getRightChild().setIsUsed(true);
						return true ;
						
					} else {
						currentNode.setRightChild(new TreeNode(currentNode,null,null,true)) ;
						numNodes++ ;
						return true ;
					}
				} else {
					if (currentNode.getLeftChild()!=null){
						if (currentNode.getLeftChild().getIsUsed()){
							return false ;
						} 
						currentNode.getLeftChild().setIsUsed(true);
						return true ;
						
					} else {
						currentNode.setLeftChild(new TreeNode(currentNode,null,null,true)) ;
						numNodes++ ;
						return true ;
					}
				}
			}
				
		}
		
	
		// ***** method code to be added in this class *****
		// now we just have a dummy that prints  message and returns true.
		//System.out.println("insert(String) not implemented!");
		return true; //sentinential return value

	}
	
	// Method to be implemented by you. See handout part 1A
	public boolean search(String s) {
		
		if (s==null){
			throw new NullPointerException("argument is null");
		}
		
		int len = s.length() ;
		
		for(int i = 0; i<len; i++){
			if (!(s.charAt(i)=='0' || s.charAt(i)=='1')){
				throw new UnsupportedOperationException("Invalid argument (binary (0,1) only): " + s);
			}
		}
		
		char current ;
		TreeNode currentNode = root ;
		
		for(int i = 0; i<len; i++){
			current = s.charAt(i) ;
			if(i!=len-1) {
				if(current == '1'){
					if (currentNode.getRightChild()!=null){
						currentNode = currentNode.getRightChild() ;
					} else {
						return false ;
					}
				} else {
					if (currentNode.getLeftChild()!=null){
						currentNode = currentNode.getLeftChild() ;
					} else {
						return false ;
					}
				}
			} else {
				if(current == '1'){
					if (currentNode.getRightChild()!=null){
						return currentNode.getRightChild().getIsUsed() ;
					} else {
						return false ;
					}
				} else {
					if (currentNode.getLeftChild()!=null){
						return currentNode.getLeftChild().getIsUsed() ;
					} else {
						return false ;
					}
				}
			}
		}
		
		
		
		// **** method code to be added in this class *****
		// now we just have a dummy that prints  message and returns true.
		//System.out.println("search(String) not implemented!");
		return false;	
	    
	}

	

	// Method to be implemented by you. See handout part 1A	
	public void printStringsInLexicoOrder() {
		// ***** method code to be added in this class *****
		// now we just have a dummy method that prints a message.
		//System.out.println("printStringsInLexicoOrder() not implemented!");
		printStringsInLexicoOrder(root,"") ;
	}
	
	//recursive part 
	private void printStringsInLexicoOrder(TreeNode N, String path) { 
		if (N!=null) {
			if (N.getIsUsed()){
				System.out.print(path+"," );
			}
			String rout = path ;
			printStringsInLexicoOrder(N.getLeftChild(), rout+"0");
			printStringsInLexicoOrder(N.getRightChild(), rout+"1");
		}
	}
	
	
	public void printInOrder() { // not to be changed
		printInOrder(root);
	}
	private void printInOrder(TreeNode N) { // not to be changed
		System.out.print("(");
		if (N!=null) {
			printInOrder(N.getLeftChild());
			System.out.print(N.getIsUsed());
			printInOrder(N.getRightChild());

		}
		System.out.print(")");
	}
	

	
	public TreeNode root() { // not to be changed
		return root;
	}
	
	public int numNodes() { // not to be changed
		return numNodes;
	}


}
