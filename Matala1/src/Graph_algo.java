import java.io.IOException;
import java.sql.Time;
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
	public Graph_algo(Graph graph) {
		this.graph = graph;
		size = graph.size();
	}

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

	public double SP_with_BlackList(int VertexA, int VertexB, ArrayList<GraphEdge> BlackList){

		if(BlackList.contains(VertexA) || BlackList.contains(VertexB)){
			System.out.println("one of the Vertex is in the BlackList");
		}

		for (GraphEdge edge : BlackList) {
			edge.setWeight(Double.MAX_VALUE);
		}














		return VertexB;

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

	public static void main(String args[]) {

		String path = "C:\\Users\\naor\\Desktop\\Graphs_small\\tinyEWG.txt";
		long start = System.currentTimeMillis();
		try {
			Graph graph = new Graph(path);
			//	System.out.print("Representation of WeighedDigraph\n");
			System.out.print(graph);
			Graph_algo finder = new Graph_algo(graph);
			//	System.out.println(finder.shortestPath(4,7));


		} 

		catch (IOException e) {}

		System.out.println(System.currentTimeMillis() - start + "ms");

	}






}
