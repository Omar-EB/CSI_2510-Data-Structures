
/*** 
 * This is class implements a compressed trie that holds a set of strings.
 * MyCompressedTrie stores nodes using class TreeNodeWithData
 */
public class MyCompressedTrie {
	
	private TreeNodeWithData root;
	
	private int numNodes;
	
	public MyCompressedTrie() { // simple constructor (empty trie consisting of root only)
		root = new TreeNodeWithData(null, null, null,false,null);
		numNodes=1;
	}
	
	// Constructor that receives a regular trie and creates this
	// instance that is a compressed trie
	public MyCompressedTrie(MyTrie trie) { 
		this(); // call to the simple constructor above (empty trie consisting of root only)
		
		addNode(trie.root().getLeftChild(),"0",root);
		addNode(trie.root().getRightChild(),"1",root);
	}
	
	private void addNode(TreeNode node, String value,TreeNodeWithData parent){
		if(node!=null){
			String bits = value ;
			if(((node.getRightChild()==null) && (node.getLeftChild()==null)) || ((node.getRightChild()!=null) && (node.getLeftChild()!=null))){ //Si le noeud a deux enfants ou bien n'a pas d'enfants
				TreeNodeWithData unchangedNode = new TreeNodeWithData(parent,null,null,node.getIsUsed(),value);
				unchangedNode.setParent(parent) ;
				if(value.charAt(value.length()-1)=='1'){
					parent.setRightChild(unchangedNode);
				} else {
					parent.setLeftChild(unchangedNode);
				}
				numNodes++;
				
				addNode(node.getLeftChild(),"0",unchangedNode) ;
				addNode(node.getRightChild(),"1",unchangedNode) ;
			} else if((node.getRightChild()==null) || (node.getLeftChild()==null)){ //Si le noeud a un seul enfant
				TreeNode current = node ;
				while(((current.getRightChild()==null && current.getLeftChild()!=null) || (current.getRightChild()!=null && current.getLeftChild()==null)) && !current.getIsUsed()){
					if(current.getRightChild()==null && current.getLeftChild()!=null){
						current = current.getLeftChild();
						bits+="0";
					} else {
						current=current.getRightChild();
						bits+="1";
					}
				}
				TreeNodeWithData newNode = new TreeNodeWithData(parent,null,null,current.getIsUsed(),bits);
				if(node.getParent().getRightChild()==node){
					parent.setRightChild(newNode);
				} else {
					parent.setLeftChild(newNode);
				}
				numNodes++;
					
				addNode(current.getRightChild(),"1", newNode) ;
				addNode(current.getLeftChild(),"0", newNode) ;
			}
		}
	}
	
	// the following method that calls its recursive counterpart
	// prints the tree and its data values at nodes in 
	// LexicoGrphical traversal order

	public void printStringsInLexicoOrder() {
		printStringsInLexicoOrder(root, "");
	}
	
	private void printStringsInLexicoOrder(TreeNodeWithData N, String parentValue){
		if (N!=null) {
			if (N.getIsUsed()){
				System.out.print(parentValue+N.getData()+"," );
			}
			if (N!=root){
				printStringsInLexicoOrder((TreeNodeWithData)N.getLeftChild(), parentValue+N.getData());
				printStringsInLexicoOrder((TreeNodeWithData)N.getRightChild(), parentValue+N.getData());
			} else {
				printStringsInLexicoOrder((TreeNodeWithData)N.getLeftChild(), parentValue);
				printStringsInLexicoOrder((TreeNodeWithData)N.getRightChild(), parentValue);	
			}
		}
	}

	// the following method that calls its recursive counterpart
	// prints the tree and its data values at nodes in 
	// in-order traversal order
	
	public void printInOrder() { 
		printInOrder(root);
	}
	
	private void printInOrder(TreeNode N) { 
		System.out.print("(");
		if (N!=null) {
			printInOrder(N.getLeftChild());
			System.out.print(((TreeNodeWithData)N).getData());
			printInOrder(N.getRightChild());

		}
		System.out.print(")");
	}
	

	
	public int numNodes() {
		return numNodes;	
	}
	

}
