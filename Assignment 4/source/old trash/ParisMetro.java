import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ParisMetro{
	
	private static Graph g ;

	static {
		try{
			readMetro("metro.txt");
		} catch (FileNotFoundException filexptn){
			filexptn.printStackTrace();
		} catch(IOException ioxptn){
			ioxptn.printStackTrace();
		}
	} 
	
	public static void main (String[] args) {
		if(args.length==1){
			metroLine(args[0]);
		} else if(args.length==2){
			shortestPath(g.getVertex(Integer.parseInt(args[0])),g.getVertex(Integer.parseInt(args[1])),null);
		} else {
			shortestPath(g.getVertex(Integer.parseInt(args[0])),g.getVertex(Integer.parseInt(args[1])),args[2]);
		}
	}
	
	static LinkedList<Graph.Vertex> metroLine(String stationNum) {
		Graph.Vertex current = g.getVertex(Integer.parseInt(stationNum));
		LinkedList<Graph.Vertex> line = new LinkedList<Graph.Vertex>();
		line.add(current);
		String path = " |"+current.getName()+"-"+current.getNumber()+"| ";
		Iterator<Graph.Edge> itr = current.outgoingEdges();
		Graph.Edge e1=null ;
		Graph.Edge e2 = null;
		
		while(itr.hasNext()){
			Graph.Edge currentEdge = itr.next();
			if(currentEdge.getValue()!=-1){
				if(e1==null){
					e1=currentEdge;
				} else if(e2 == null){
					e2=currentEdge;
				} else {
					//shouldn't happen
					System.out.println("-!!!-SKIPPED EDGE : "+currentEdge.getStart().getNumber()+"TO"+currentEdge.getOpposite().getNumber());
				}
			}
		}
		
		
		boolean done1 = (e1==null);
		Graph.Vertex previous1=null;
		Graph.Edge continuousEdge1=null;
		Graph.Vertex next1=null;
		Iterator<Graph.Edge> continuousItr1;
		
		boolean done2 = (e2==null);
		Graph.Vertex previous2=null;
		Graph.Edge continuousEdge2=null;
		Graph.Vertex next2=null;
		Iterator<Graph.Edge> continuousItr2;
		
		
		if(!done1){
			previous1=current;
			continuousEdge1=e1;
			next1= e1.getOpposite();
		} 
		if(!done2){
			previous2=current;
			continuousEdge2=e2;
			next2= e2.getOpposite();
		}
		
		
		while(continuousEdge1!=null  && (!line.contains(next1))) {
			continuousEdge1=null;
			line.addFirst(next1);
			path=" |"+next1.getName()+"-"+next1.getNumber()+"|"+path;
			continuousItr1 = next1.outgoingEdges();
			
			Graph.Edge TestEdge1;
			while(continuousItr1.hasNext()){
				TestEdge1 = continuousItr1.next() ;
				if((TestEdge1.getValue()!=-1)&&(TestEdge1.getOpposite()!=previous1)){
					previous1=next1;
					next1=TestEdge1.getOpposite();
					continuousEdge1=TestEdge1;
					break; //might cause issues--> first suspect
				} 
			}
		}
		
		while (continuousEdge2!=null && (!line.contains(next2))) {
			continuousEdge2=null;
			line.add(next2);
			path=path+"|"+next2.getName()+"-"+next2.getNumber()+"| ";
			continuousItr2 = next2.outgoingEdges();
			
			Graph.Edge TestEdge2;
			while(continuousItr2.hasNext()){
				TestEdge2 = continuousItr2.next() ;
				if((TestEdge2.getValue()!=-1)&&(TestEdge2.getOpposite()!=previous2)){
					previous2=next2;
					next2=TestEdge2.getOpposite();
					continuousEdge2=TestEdge2;
					break; //might cause issues--> first suspect
				} 
			}
		}
		
		System.out.println(path);
		return line;
	}
	
	static void shortestPath(Graph.Vertex v1, Graph.Vertex v2, String v3){
		String path="";
		if (v3!=null){
			System.out.println("Closed Line:");
			LinkedList<Graph.Vertex> closedLine = metroLine(v3) ;
			if(closedLine.contains(v1) || closedLine.contains(v2)){
				throw new UnsupportedOperationException("!!One of the stations is in the closed line!!");
			}
			System.out.println(System.lineSeparator());
			Iterator<Graph.Vertex> closedLineIterator = closedLine.listIterator(0);
			while(closedLineIterator.hasNext()){
				closedLineIterator.next().setAvailable(false);
			}
		}
		v1.setPathLength(0);
		v1.setPrevious(-1);
		
		// build heap :
		// !FIRST ENTRY IS NULL!
		Graph.Vertex[] heap = new Graph.Vertex[g.getNumberV()+1];
		int N=1;
		heap[N] = g.getVertex(0);
		for (int i=1; i<g.getNumberV();i++) { 
			heap[++N] = g.getVertex(i);
		}
		HeapManipulator.buildHeap(heap,N);
		
		//The shortes path process:
		Graph.Vertex u;
		Graph.Vertex z=null;
		while(N>0) {
			u = HeapManipulator.removeMin(heap,N);
			if(u==v2){
				break;
			}
			u.setInCloud(true);
			N--;
		
			Iterator <Graph.Edge> itr = u.outgoingEdges();
			while(itr.hasNext()) {
				Graph.Edge edge = itr.next();
				z = edge.getOpposite();
				if((!z.inCloud()) && z.available()){
					int value = edge.getValue();
					if(value==-1){
						value=90;
					}
					if((value+u.getPathLength()) < z.getPathLength()){
						z.setPathLength(value+u.getPathLength());
						z.setPrevious(u.getNumber());
					}
					HeapManipulator.buildHeap(heap,N);
				}
				if (z==v2){
					break;
				}
			}
			if(z==v2){
				break;
			}
		}
		path = z.getName()+"_"+z.getNumber();
		Graph.Vertex station  =  z;
		while(station.getPrevious()!=-1){
			station = g.getVertex(station.getPrevious());
			path= station.getName()+"_"+station.getNumber()+"---->"+path;
		}
		double minutes = (z.getPathLength()/60);
		path+=" ||||||||| Time: "+minutes+" minutes";
		System.out.println("Path :");
		System.out.println(path);
		g.restore();
	}
	
	static void readMetro(String fileName) throws FileNotFoundException, IOException {
		BufferedReader metroFile =new BufferedReader( new FileReader(fileName));
		String line = metroFile.readLine();
		int counter = 0;
		String nV = "";
		String nE = "";
		
		while(!(Character.isDigit(line.charAt(counter)))){
			counter++;
		}
		while(Character.isDigit(line.charAt(counter))){
			nV+=line.charAt(counter);
			counter++;
		}
		while(!(Character.isDigit(line.charAt(counter)))){
			counter++;
		}
		while(Character.isDigit(line.charAt(counter))){
			nE+=line.charAt(counter);
			if(counter < line.length()-1){
				counter++;
			} else { break;}
		}
		
		int nV_int = Integer.parseInt(nV);
		int nE_int =  Integer.parseInt(nE);
		
		
		StringTokenizer st ;
		g = new Graph(nV_int,nE_int);
		while(!((line=metroFile.readLine()).equals("$"))){
			st = new StringTokenizer(line);
			int num = Integer.parseInt(st.nextToken());
			String name = "";
			while(st.hasMoreTokens()){
				name+=st.nextToken()+" ";
			}
			name=name.substring(0,name.length()-1);
			g.addVertex(num,name);
		}
		while((line=metroFile.readLine())!=null){
			st = new StringTokenizer(line);
			Graph.Vertex start = g.getVertex(Integer.parseInt(st.nextToken()));
			Graph.Vertex destination = g.getVertex(Integer.parseInt(st.nextToken()));
			int value = Integer.parseInt(st.nextToken());
			start.addEdge(new Graph.Edge(start,destination,value));
		}
	}
}