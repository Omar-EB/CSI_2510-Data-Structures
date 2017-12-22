import java.util.LinkedList;

public class HeapNode{
	private Graph.Vertex[] listOfVertices = new Graph.Vertex[10];
	private int MAX_SIZE=10;
	private String name;
	private boolean isSingly=true;
	private int size = 0;
	
	
	public HeapNode(String name){
		this.name=name;
	}
	
	public void addVertex(Graph.Vertex v){
		if(size==MAX_SIZE) {
			MAX_SIZE=MAX_SIZE*2;
			Graph.Vertex[] temp = new Graph.Vertex[MAX_SIZE];
			for(int i=0; i<size;i++){
				temp[i]=listOfVertices[i];
			}
			listOfVertices = temp;
		}
		if(isSingly){
			listOfVertices[size++]=v;
			isSingly=false;
		} else {
			listOfVertices[size++]=v;
		}
	}
	
	public Graph.Vertex[] getVertices(){
		return listOfVertices;
	}
	
}