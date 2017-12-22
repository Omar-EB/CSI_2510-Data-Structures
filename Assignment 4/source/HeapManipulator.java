//class enabling the manipulation of the heap (to build and to remove minimum)

public class HeapManipulator{
	
	public static void downheap(Graph.Vertex[] heap, int N, int k) {
		int j;
		Graph.Vertex tempo = heap[k];
	
		while (k <= N/2) {
			j =k*2; 
			if ( (j < N) && (heap[j+1].getPathLength() < heap[j].getPathLength()) )
				j++;
			if (tempo.getPathLength() <= heap[j].getPathLength()) break;
			heap[k] = heap[j];
			k = j;
		}
		heap[k] = tempo; 
	}
	public static void buildHeap(Graph.Vertex[] heap,  int N) {
		for (int k=N/2; k>=1; k-- ) {
			downheap(heap, N, k);
		}
	}
	
	public static Graph.Vertex removeMin(Graph.Vertex[] heap,int N){
		Graph.Vertex min = heap[1];
		heap[1]=heap[N];
		heap[N]=null;
		downheap(heap, --N, 1);
		return min;
	}
	
}