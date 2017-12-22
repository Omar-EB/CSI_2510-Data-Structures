import java.util.LinkedList;
import java.util.Iterator;

public class Graph{
	
	//The vertex class
	public static class Vertex{
		private String name;
		private int number;
		private int previousVertexNumber=-1;
		private LinkedList<Edge> outgoingEdges;
		private int pathLength = Integer.MAX_VALUE;
		private boolean available = true;
		private boolean inCloud = false;
		public Vertex(String name, int number){
			this.name=name;
			this.number=number;
			outgoingEdges = new LinkedList<Edge>();
		}
		public void addEdge(Edge e){
			outgoingEdges.add(e);
		}
		public String getName(){
			return name;
		}
		public int getNumber(){
			return number;
		}
		public Iterator<Edge> outgoingEdges(){
			return outgoingEdges.listIterator(0);
		}
		public void setPathLength(int value){
			pathLength = value;
		}
		public void setPrevious(int number){
			previousVertexNumber = number;
		}
		public int getPrevious(){
			return previousVertexNumber;
		}
		public int getPathLength(){
			return pathLength;
		}
		public void setAvailable(boolean availability){
			available=availability;
		}
		public void setInCloud(boolean inCloud){
			this.inCloud=inCloud;
		}
		public boolean inCloud(){
			return inCloud;
		}
		public boolean available(){
			return available;
		}
	}
	
	//The edge class
	public static class Edge{
		private Vertex start;
		private Vertex destination;
		private int value;
		public Edge(Vertex start, Vertex destination, int value){
			this.start=start;
			this.destination=destination;
			this.value=value;
		}
		
		public Vertex getOpposite(){
			return destination;
		}
		public int getValue(){
			return value;
		}
		public Vertex getStart(){
			return start;
		}
		
	}
	
	private int numV;
	private int numE;
	private Vertex[] vertices;
	
	public Graph(int numV, int numE){
		this.numV=numV;
		this.numE=numE;
		vertices = new Vertex[numV];
	}
	
	public Vertex getVertex(int pos){
		return vertices[pos];
	}
	
	public void addVertex(int pos, String name){
		vertices[pos] = new Vertex(name,pos);
	}
	
	public int getNumberV(){
		return numV;
	}
	
	public void restore(){
		for (Vertex v : vertices){
			v.setPathLength(Integer.MAX_VALUE);
			v.setAvailable(true);
			v.setInCloud(false);
			v.previousVertexNumber=-1;
		}
	}
}