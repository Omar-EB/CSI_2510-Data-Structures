	private void addNode(TreeNode node, String value,TreeNodeWithData parent){
		if(node!=null){
			String bits = value ;
			if((node.getRightChild()==null) && (node.getLeftChild()==null)){
				TreeNodeWithData unchangedNode = new TreeNodeWithData(parent,null,null,node.getIsUsed(),value);
				unchangedNode.setParent(parent) ;
				if(value.charAt(value.length()-1)=='1'){
					parent.setRightChild(unchangedNode);
				} else {
					parent.setLeftChild(unchangedNode);
				}
				numNodes++;
			} 
			 else if((node.getRightChild()==null) || (node.getLeftChild()==null)){
				TreeNode current = node ;
				//if(node.getRightChild()==null){
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
						addNode(current.getRightChild(), bits+"1", newNode) ;
						addNode(current.getLeftChild(), bits+"0", newNode) ;
				//} else {
					/*while(((current.getLeftChild()==null && current.getRightChild()!=null) || (current.getLeftChild()!=null && current.getRightChild()==null)) && !current.getIsUsed()){
						if(current.getLeftChild()==null && current.getRightChild()!=null){
							current = current.getRightChild();
							bits+="1";
						} else {
							current = current.getLeftChild();
							bits+="0";
						}
					}
					TreeNodeWithData newNode = new TreeNodeWithData(parent,null,null,current.getIsUsed(),bits);
					parent.setRightChild(newNode);
					numNodes++;
						addNode(current.getRightChild(), bits+"1", newNode) ;
						addNode(current.getLeftChild(), bits+"0", newNode) ;
				}*/
			} else {
				TreeNodeWithData unchangedNode = new TreeNodeWithData(parent,null,null,node.getIsUsed(),value);
				unchangedNode.setParent(parent) ;
				if(value.charAt(value.length()-1)=='1'){
					parent.setRightChild(unchangedNode);
				} else {
					parent.setLeftChild(unchangedNode);
				}
				numNodes++;
				addNode(node.getLeftChild(), value+"0",unchangedNode) ;
				addNode(node.getRightChild(), value+"1",unchangedNode) ;
			}
		}
	}
	
	public void printStringsInLexicoOrder() {
		// ***** method code to be added in this class *****
		// now we just have a dummy method that prints a message.
		//System.out.println("printStringsInLexicoOrder() not implemented!");
		printStringsInLexicoOrder(root);
	}
	
	private void printStringsInLexicoOrder(TreeNodeWithData N){
		if (N!=null) {
			if (N.getIsUsed()){
				System.out.print(N.getData()+"," );
			}
			printStringsInLexicoOrder((TreeNodeWithData)N.getLeftChild());
			printStringsInLexicoOrder((TreeNodeWithData)N.getRightChild());
		}
	}