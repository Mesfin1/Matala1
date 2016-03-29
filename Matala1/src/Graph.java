
/**
 * Implementation of directed, weighed graph using a HashMap to represent adjacency list.
 *
 * @author marcinkossakowski on 9/24/14.
 */

// For file reading
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

public class Graph {
	private HashMap<Integer, ArrayList<GraphEdge>> adj = new HashMap(); // adjacency-list
	static Scanner scanner ;
	public int numNodes; // number of verices in the graph
	// array of vertices
	private static int points;
	ArrayList<GraphEdge> adj1;
	static GraphEdge graphEdge=new GraphEdge(0, 0, 0);
	Graph_algo graph_algo=new Graph_algo();	
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

	public static void readGraph(String s,String s1) throws FileNotFoundException{




		int size_of_BL=0;
		int BL[] = null;
		Vector<Integer>myBL=new Vector<>();
		try {

			File file = new File(s);
			Scanner input = new Scanner(file);
			points = (int)input.nextDouble();
			double we=0;

			int count=0;
			int[] array;
			while (count<points ) {
				int a = input.nextInt();
				int b=input.nextInt();

				int size=input.nextInt();
				// System.out.println("black list "+size);
				array = new int[size];
				int t=0;
				if(size>0){

					for (int j = 0; j < array.length; j++) {
						array[j]=input.nextInt();
						we=graphEdge.getWeigth(a,b,s);	
					}
					System.out.println(a+" "+b+" "+size+" "+Arrays.toString(array)+" "+ we);
				}
				else {
					we=graphEdge.getWeigth(a,b,s);	
					System.out.println(a+" "+b+" "+we);			

				}

				/*for (int i = 0; i < BL.length; i++) {
					System.out.println("BL "+BL[i]);
				}*/



				/*	  if(c<=0){
        		  System.out.println("no negative input allowed");
        	  	  return;}*/

				count++;
				if(input.hasNextLine())
				input.nextLine();

			}

			input.close();




		} catch (Exception ex) {
			ex.printStackTrace();
		}



	}

	/**
	 * Instantiate graph from file with data
	 * @param file
	 * @throws IOException
	 */

	public static void RGraph(String fileName) throws IOException {
		/*long start = new Date().getTime();
		FileReader fr = new FileReader(fileName);
		BufferedReader is = new BufferedReader(fr);
		String num_of_lines = is.readLine();
		System.out.println(num_of_lines);
		String s = is.readLine();
 		int ll = 0;
 		int[] BL = null ;
 		Vector<Integer>myBl=new Vector<>();
		String[] startdst = s.split(" ");
		for (int i = 0; i < num_of_lines.length(); i++) {


			String[] parts = s.split(" ");
			int source = Integer.parseInt(parts[0]);
			graphEdge.setFrom(source);
			int target = Integer.parseInt(parts[1]);
			graphEdge.setTo(target);

			double wei = graphEdge.getWeight();

 			int size_of_BL = Integer.parseInt(parts[2]);
 			System.err.println(size_of_BL);
			graphEdge=new GraphEdge(source, target, wei);

			if(s.length()>3){
			 BL = new int[size_of_BL];
				for (int j = 0; j < size_of_BL; j++) {
					BL[j] = Integer.parseInt(parts[(j + 3)]);
                     myBl.addElement(BL[i]);
					int w=Integer.parseInt(parts[j+3]);
					w=Integer.MAX_VALUE;
					System.out.println(graphEdge.getFrom()+" "+graphEdge.getTo()+" "+graphEdge.getWeight());
					graphEdge=new GraphEdge(source, target, w);
				}
				// double dist = sp(G, source, target, BL);


			}	
			ll++;

		}
		for (int i = 0; i < myBl.size(); i++) {
			System.out.println(myBl.get(i));
		}


		long s2 = new Date().getTime();
		System.out.println("Total time: " + (s2 - start) + "  ms");*/
		//readGraph(fileName);

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
	 * Function writes to a new file
	 * @param from
	 * @param into
	 */
	public void MakeNewFile(String from,String into){

		try {
			// the readFile
			String name = from;
			FileReader fr = null;
			BufferedReader br = null;
			fr = new FileReader(name);
			br = new BufferedReader(fr);
			// /the ansFile
			String name2 = into;
			FileWriter fw = null;
			BufferedWriter bw = null;
			fw = new FileWriter(name2);
			bw = new BufferedWriter(fw);
			// if (!name.endsWith(".txt")) {
			// name2 = name + ".txt";
			// }
			// כתיבת כמות הבדיקות
			/*			int numberChecks = Integer.parseInt(br.readLine());
			System.err.println(numberChecks);
			bw.write(numberChecks+"\n");*/

			for (int i = 0; i < 5; i++) {
				String ans = " ";
				String s = br.readLine();
				ans = s;
				StringTokenizer help = new StringTokenizer(s);
				int start = Integer.parseInt((String) help.nextElement());
				int end = Integer.parseInt((String) help.nextElement());
				int numberOfBlacked = Integer.parseInt((String) help
						.nextElement());
				Vector<Integer> blacked = new Vector<Integer>(numberOfBlacked);

				for (int j = 0; j < numberOfBlacked; j++) {
					blacked.add(Integer.parseInt((String) help.nextElement()));
				}

				//ans += " "+graph.BellmanFord(start, blacked, end)+"\n";
				bw.write(ans);
			}
			bw.close();
			fw.close();
			br.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	/**
	 * Graph Tests
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String args[]) throws IOException {




		String filePath="C:\\Users\\Mesfin\\Desktop\\Graphs_small\\test4.txt";   // scanner to read file
		String anString="C:\\Users\\Mesfin\\Desktop\\Graphs_small\\tinyEWD.txt";

		//String theans="C:\\Users\\Mesfin\\Desktop\\ans.txt";
		readGraph(filePath,anString);
		//System.out.println(graph_algo.shortestPath(4, 5));
		//RGraph(filePath);


	}
}
