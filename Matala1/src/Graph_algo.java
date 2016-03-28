 
import java.util.*;

public class Graph_algo {
	private int size;
	private HashMap<Integer, Double> weight; // store weights for each vertex
	private HashMap<Integer, Integer> previousNode; // store previous vertex
	private PriorityQueue<Integer> pq; // store vertices that need to be visited
	private Graph graph; // graph object

	/**
	 * Instantiate algorithm providing graph
	 * @param graph WeighedDigraph graph
	 */
	public Graph_algo(){}
	
	public Graph_algo(Graph graph) {
		this.graph = graph;
		size = graph.size();
	}

	/**
	 * 
	 * @param int a,b nodes 
	 * calculate the cheapest price from a to b
	 */
	public double CheapestPrice(int a , int b){
		double total = 0;
		if (weight.get(a) > weight.get(b)) {
			total += weight.get(b);
		} 
		else if (weight.get(a) < weight.get(b)) {
			total += weight.get(a);
		}
		total += weight.get(b);
		return total;
	}





	/**
	 * Calculate shortest path from A to B
	 * @param vertexA source vertex
	 * @param vertexB destination vertex
	 * @return list of vertices composing shortest path between A and B
	 */
	public ArrayList<Integer> shortestPath(int vertexA, int vertexB) {
		previousNode = new HashMap<Integer, Integer>();
		weight = new HashMap<Integer, Double>();
		pq = new PriorityQueue<Integer>(size, PQComparator);

		/* Set all distances to Infinity */
		for (int vertex : graph.vertices())
			weight.put(vertex, Double.POSITIVE_INFINITY);

		previousNode.put(vertexA, -1); // negative means no previous vertex
		weight.put(vertexA, 0.0); // weight to has to be 0
		pq.add(vertexA); // enqueue first vertex

		while (pq.size() > 0) {
			int currentNode = pq.poll();
			ArrayList<GraphEdge> neighbors = graph.edgesOf(currentNode);

			if (neighbors == null) continue;

			for (GraphEdge neighbor : neighbors) {
				int nextVertex = neighbor.to();

				double newDistance = weight.get(currentNode) + neighbor.weight();

				if (weight.get(nextVertex) == Double.POSITIVE_INFINITY) {
					previousNode.put(nextVertex, currentNode);
					weight.put(nextVertex, newDistance);
					pq.add(nextVertex);
				} 
				else {
					if (weight.get(nextVertex) > newDistance) {
						previousNode.put(nextVertex, currentNode);
						weight.put(nextVertex, newDistance);
					}
				}

			}

		}

		/* Path from A to B will be stored here */
		ArrayList<Integer> nodePath = new ArrayList<Integer>();

		/*
        We are reverse walking points to get to the beginning of the path.
        Using temporary stack to reverse the order of node keys stored in nodePath.
		 */
		Stack<Integer> nodePathTemp = new Stack<Integer>();
		nodePathTemp.push(vertexB);

		int v = vertexB;
		while (previousNode.containsKey(v) && previousNode.get(v) >= 0 && v > 0) {
			v = previousNode.get(v);
			nodePathTemp.push(v);
		}

		// Put node in ArrayList in reversed order
		while (nodePathTemp.size() > 0)
			nodePath.add(nodePathTemp.pop());
		System.out.println("Cheapest price from " + vertexA + " to " + vertexB + " is: " + CheapestPrice(vertexA,vertexB));
		return nodePath;
	}

	/**
	 * Comparator for priority queue
	 */
	public Comparator<Integer> PQComparator = new Comparator<Integer>() {

		public int compare(Integer a, Integer b) {
			if (weight.get(a) > weight.get(b)) {
				return 1;
			} else if (weight.get(a) < weight.get(b)) {
				return -1;
			}
			return 0;
		}
	};

/*	public double SP_with_BlackList(int vertexA, int vertexB, int[] BlackList){
		 return graph.BlackList(vertexA, vertexB, BlackList);

	}*/

 






}
