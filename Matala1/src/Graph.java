
/**
 * Implementation of directed, weighed graph using a HashMap to represent adjacency list.
 *
 * @author marcinkossakowski on 9/24/14.
 */

// For file reading
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Graph {
	private HashMap<Integer, ArrayList<GraphEdge>> adj = new HashMap(); // adjacency-list
	static Scanner scanner ;
	public int numNodes; // number of verices in the graph
	// array of vertices
	ArrayList<GraphEdge> adj1;
	static GraphEdge graphEdge=new GraphEdge(0, 0, 0);
	public Graph() {}
	/**
	 * Constructor for Graph
	 * 
	 * @param size
	 *            : int - number of vertices
	 */
	Graph(int size) {
		numNodes = size;
		adj1 = new ArrayList<GraphEdge>(size + 1);
		adj1.add( 0, null);
		// create an array of Vertex objects
	}

	public void printGraph(String[] n) {
		for (int i = 0; i < n.length; i++) {
			System.out.print(n[i]+" ");
		}
		System.out.println();
	}


	/**
	 * Instantiate graph from file with data
	 * @param file
	 * @throws IOException
	 */
	/*	public Graph(String file) throws IOException {
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


			//printGraph(parts);
		}

	}*/
	public static void myraed( String f ) throws IOException
	{


		long start = new Date().getTime();

		BufferedReader br = new BufferedReader(new FileReader(f));
		String line = br.readLine();
		String[] startdst = line.split(" ");

		while ((line  = br.readLine())!=null)
		{
			String[] sa = line.split(" ");
			int source = Integer.parseInt(sa[0]);
			int target = Integer.parseInt(sa[1]);

			//   GraphEdge graphEdge=new GraphEdge(source,target);
			int size_of_BL = Integer.parseInt(sa[2]);
			int[] BL = new int[size_of_BL];
			for (int i = 0; i < size_of_BL; i++) {
				BL[i] = Integer.parseInt(sa[(i + 3)]);
				int inf=Integer.MAX_VALUE;
				//    graphEdge.setWeight(inf);
				graphEdge=new GraphEdge(source, target, inf);
				//   graphEdge.BellmanFordSP(graphEdge, target); 

			}

		}
		long s2 = new Date().getTime();
		System.out.println("Total time: " + (s2 - start) + "  ms");

	}



	// Create a graph from file
	public Graph(String fileName) throws IOException {
		long start = new Date().getTime();
		FileReader fr = new FileReader(fileName);
		BufferedReader is = new BufferedReader(fr);
		String num_of_lines = is.readLine();
		String s = is.readLine();
		int ll = 0;

		String[] startdst = s.split(" ");
		while ((s != null) && (ll < 20))
		{
			String[] sa = s.split(" ");
			int source = Integer.parseInt(sa[0]);
			graphEdge.setFrom(source);
			int target = Integer.parseInt(sa[1]);
			graphEdge.setTo(target);
			int size_of_BL = Integer.parseInt(sa[2]);
			int[] BL = new int[size_of_BL];
			for (int i = 0; i < size_of_BL; i++) {
				BL[i] = Integer.parseInt(sa[(i + 3)]);

				int w=Integer.parseInt(sa[i+3]);
			 
				w=Integer.MAX_VALUE;
		            System.out.println(graphEdge.getFrom()+" "+graphEdge.getTo()+" "+graphEdge.getWeight());
				graphEdge=new GraphEdge(source, target, w);
			}
			// double dist = sp(G, source, target, BL);

			ll++;
			s = is.readLine();
		}
		long s2 = new Date().getTime();
		System.out.println("Total time: " + (s2 - start) + "  ms");



	}
	public void readGraphwithBL(String file) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(file));


		int from,to,blVertex;
		int blWeight = Integer.MAX_VALUE;

		String line = reader.readLine() ;
		String[] parts=line.split(",|\\s+");		    
		while ((line = reader.readLine()) != null) {

			//	if(parts.length > 3){
			from = Integer.parseInt(parts[0]);
			System.out.println(from);
			line=reader.readLine();

			to = Integer.parseInt(parts[1]);
			System.out.println(to);
			int blSize = Integer.parseInt(parts[2]);
			int blackList[] = new int[blSize];
			for (int i = 0; i < blackList.length; i++){
				blVertex =Integer.parseInt(parts[i+3]);


			}
			addEdge(new GraphEdge(from, to));
		}

		//}

	}



	/**
	 * @param vertex
	 * @return list of edges vertex is connected to.
	 */
	public ArrayList<GraphEdge> edgesOf(int vertex) {
		return adj.get(vertex);
	}

	public double BlackList(int a,int b,int[] BL){

		Graph g = new Graph();
		Graph_algo ans1 = new Graph_algo(g);
		for (int i = 0; i < BL.length; i++) {
			if(BL[i] == a || BL[i] == b) System.out.println("one of the nodes is in the blackList");
		}
		for (GraphEdge edge_a : edgesOf(a)) {
			edge_a.setWeight(Double.MAX_VALUE);

		}
		for (GraphEdge edge_b : edgesOf(b)) {
			edge_b.setWeight(Double.MAX_VALUE);

		}

		return ans1.CheapestPrice(a, b);
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
		String filePath="C:\\Users\\Mesfin\\Desktop\\Graphs_small\\test1.txt";   // scanner to read file
		Scanner in;
		Graph G =new Graph(filePath);

		//readGraph(new Scanner(new FileReader(filePath)));

		//	Graph graph = new Graph(filePath);
		//graph.readGraphwithBL(filePath);
		//System.out.println();
		//System.out.print(graph);
	}
}
