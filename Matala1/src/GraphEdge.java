
public class GraphEdge {
	private int from, to;
	private double weight;

	
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
	public GraphEdge(int from, int to) {
		this.from = from;
		this.to = to;
		//this.weight = weight;
	}
	
	/*public boolean isValid(){// check if a vertex is valid
		
	}
	
	public void setValid(boolean set){// pass with for over the edges and set to valid
		
	}*/

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
