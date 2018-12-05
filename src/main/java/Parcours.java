import java.util.ArrayList;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.Stack;


public class Parcours {
	Graph graph;
	Stack<Arc> frontier;
	BitSet reached;
	ArrayList<Arc> predecessor;
	
	private void etendsFrontier(int sommet) {
		for (Arc a : graph.outNeighbours(sommet))
			frontier.add(a);
	}

	
	private void explore(Arc a) {
		if (reached.get(a.getDest())) return;
		reached.set(a.getDest());
		etendsFrontier(a.getDest());
		predecessor.set(a.getDest(), a);
	}
	
	private void parcours(int source) {
		reached.set(source);
		etendsFrontier(source);
		while (!frontier.isEmpty())
			explore(frontier.pop());
		
	}
	
	private Parcours(Graph graph) {
		this.graph = graph;
		this.frontier = new Stack<>();
		this.reached = new BitSet(graph.getOrder());
		this.predecessor = new ArrayList<>(graph.getOrder());
		for (int i = 0; i < graph.getOrder(); i++) {
			predecessor.add(null);
		}
	}
	

	public static ArrayList<Arc> algo(Graph graph, int source) {
		Parcours p = new Parcours(graph);
		p.parcours(source);
		return p.predecessor;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
