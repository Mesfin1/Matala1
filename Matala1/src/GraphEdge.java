import java.util.Queue;
import java.util.Vector;

public class GraphEdge {
	private int from, to;
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	private double weight;

	 private double[] distTo;               // distTo[v] = distance  of shortest s->v path
	    private GraphEdge edgeTo;         // edgeTo[v] = last edge on shortest s->v path
	    private boolean[] onQueue;             // onQueue[v] = is v currently on the queue?
	    private Queue<Integer> queue;          // queue of vertices to relax
	    private int cost;                      // number of calls to relax()
	   

	/**
	 * Construct graph edge
	 * @param from
	 * @param to
	 * @param weight
	 */
	public GraphEdge(int from, int to, double weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	public double getWeight() {
		return weight;
	}
	public GraphEdge(int from, int to) {
		this.from = from;
		this.to = to;
		//this.weight = weight;
	}
	
	/*public boolean isValid(){// check if a vertex is valid
		
	}
	
	public void setValid(boolean set){// pass with for over the edges and set to valid
		
	}*/
	public void BellmanFordSP(GraphEdge G, int s) {
        distTo  = new double[G.from()];
        edgeTo  = new GraphEdge(G.from(),G.to());
        
        for (int v = 0; v < G.from(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
          distTo[s] = 0.0;

        // Bellman-Ford algorithm
        queue = (Queue<Integer>) new Vector() ;
        queue.add(s);
        onQueue[s] = true;
        while (!queue.isEmpty() ) {
            int v = queue.remove();
            onQueue[v] = false;
           
        }
 
    }
 
	/**
	 * @return from vertex
	 */
	public int from() { return from; }

	/**
	 * @return to vertex
	 */
	public int to() { return to; }

	/**
	 * @return weight of edge between from() and to()
	 */
	public double weight() { return weight; }
	public void setWeight(double weight) {
		this.weight = weight;
	}
}
