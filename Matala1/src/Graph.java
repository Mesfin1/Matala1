
/**
 * Implementation of directed, weighed graph using a HashMap to represent adjacency list.
 *
 * @author marcinkossakowski on 9/24/14.
 */

import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;

// For file reading
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class Graph {
	private HashMap<Integer, ArrayList<GraphEdge>> adj = new HashMap(); // adjacency-list

	public Graph() {}

	public void printGraph(String[] n) {
		for (int i = 0; i < n.length; i++) {
			System.out.print(n[i]+" ");
		}
		System.out.println();
	}
	
	//valid all// each for on nodes and set them to true
	//the target is to set all edged in the bl to notValid

	/**
	 * Instantiate graph from file with data
	 * @param file
	 * @throws IOException
	 */
	public Graph(String file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;

		int from,to,blVertex;
		double weight;
		while ((line = reader.readLine()) != null) {
			String[] parts = line.split("   ");
			if (parts.length == 3) {
				from = Integer.parseInt(parts[0]);
				to = Integer.parseInt(parts[1]);
				weight = Double.parseDouble(parts[2]);
				addEdge(new GraphEdge(from, to, weight));
			}
			else if(parts.length > 3) readGraphwithBL(file);


			printGraph(parts);
		}

	}

	public void readGraphwithBL(String file) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		int from,to,blVertex;
		int blWeight = Integer.MAX_VALUE;
		String[] parts = line.split("   ");
		while ((line = reader.readLine()) != null) {

			if(parts.length > 3){
				from = Integer.parseInt(parts[0]);
				to = Integer.parseInt(parts[1]);
				int blSize = Integer.parseInt(parts[2]);
				int blackList[] = new int[blSize];
				for (int i = 0; i < blackList.length; i++){
					blVertex =Integer.parseInt(parts[i+3]);
					blVertex = blWeight;
				}
				addEdge(new GraphEdge(from, to, blWeight));
			}

		}

	}



	/**
	 * @param vertex
	 * @return list of edges vertex is connected to.
	 */
	public ArrayList<GraphEdge> edgesOf(int vertex) {
		return adj.get(vertex);
	}
	
	

	/**
	 * @return list of all edges in the graph.
	 */
	public ArrayList<GraphEdge> edges() {
		ArrayList list = new ArrayList<GraphEdge>();
		for (int from : adj.keySet()) {
			ArrayList<GraphEdge> currentEdges = adj.get(from);
			for (GraphEdge e : currentEdges) {
				list.add(e);
			}
		}
		return list;
	}

	/**
	 * @return iterable of all vertices in the graph.
	 */

	public Iterable<Integer> vertices() {
		HashSet set = new HashSet();
		for (GraphEdge edge : edges()) {
			set.add(edge.from());
			set.add(edge.to());
		}

		return set;
	}

	/**
	 * @return size of adjacency list
	 */
	public int size() { return adj.size(); }

	/**
	 * @return string representation of digraph
	 */
	public String toString() {
		String out = "";
		for (int from : adj.keySet()) {
			ArrayList<GraphEdge> currentEdges = adj.get(from);
			out += from + " -> ";

			if (currentEdges.size() == 0)
				out += "-,";

			for (GraphEdge edge : currentEdges)
				out += edge.to() + " @ " + edge.weight() + ", ";

			out += "\n";
		}

		return out;
	}
	/*public String toString() {
	String out = " ";

	  for (int from : adj.keySet()) {
	   ArrayList<GraphEdge> currentEdges = adj.get(from);

	   if (currentEdges.size() == 0)
	    out += "-,";

	   for (GraphEdge edge : currentEdges){
	    out+=edge.from()+ "->"+edge.to()+ " " + edge.weight() +  ",";
	   }

	   out = out.substring(0,out.length() - 1);
	   out += "\n";
	  }

	  return out;
	}*/
	/**
	 * Add new edge to the system.
	 * @param newEdge
	 */
	public void addEdge(GraphEdge newEdge) {
		// create empty connection set
		if (!adj.containsKey(newEdge.from()))
			adj.put(newEdge.from(), new ArrayList<GraphEdge>());

		ArrayList<GraphEdge> currentEdges = adj.get(newEdge.from());

		/* Check if edge already exists,
		 * if it is, replace it with new one assuming it needs to be updated */
		boolean edgeExists = false;
		for (int i = 0; i < currentEdges.size(); i++) {
			if (currentEdges.get(i).to() == newEdge.to()) {
				currentEdges.set(i, newEdge);
				edgeExists = true;
				break;
			}
		}

		if (!edgeExists)
			currentEdges.add(newEdge);

		adj.put(newEdge.from(), currentEdges);
	}

	/**
	 * Graph Tests
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String args[]) throws IOException {
		Graph graph = new Graph("C:\\Users\\naor\\Desktop\\Graphs_small\\test3.txt");
		System.out.println();
		System.out.print(graph);
	}
}
