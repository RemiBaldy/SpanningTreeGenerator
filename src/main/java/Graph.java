import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Graph implements Iterable<Edge>{
    // classe de graphe non orientés permettant de manipuler
    // en même temps des arcs (orientés)
    // pour pouvoir stocker un arbre couvrant, en plus du graphe
    
	private int order;
	private int edgeCardinality;
	private Iterator adjacencyIt;
	
	ArrayList<LinkedList<Edge>> adjacency;
	ArrayList<LinkedList<Arc>> inAdjacency;
	ArrayList<LinkedList<Arc>> outAdjacency;

    public int getOrder() {
        return order;
    }

    private boolean isVertex(int index) {
        if(adjacency.get(index) != null)
            return true;
        return false;
        /*
        for(LinkedList<Edge> edges: adjacency) {
            if (edges.getFirst().source == index) {
                return true;
            }
        }
        return false;*/
	}
	
	public <T> ArrayList<LinkedList<T>> initialiseList(int size) {
		ArrayList<LinkedList<T>> res = new ArrayList<>(size);
		for(int i = 0; i <= size; i++) {
			res.add(null);
		}
		return res;
	}
	
	public Graph(int upperBound) {
	    adjacency = initialiseList(upperBound);
        inAdjacency = initialiseList(upperBound);
        outAdjacency = initialiseList(upperBound);
        order = 0;
        edgeCardinality = 0;
        adjacencyIt = adjacency.iterator();
	}
	
	public void addVertex(int indexVertex) {
	    order++;
	    adjacency.add(indexVertex, new LinkedList<Edge>());
        inAdjacency.add(indexVertex, new LinkedList<Arc>());
        outAdjacency.add(indexVertex, new LinkedList<Arc>());
	}
	
	public boolean ensureVertex(int indexVertex) {
		if(!isVertex(indexVertex)){
            addVertex(indexVertex);
            return true;
		}
		return false;
	}
	
	public void addArc(Arc arc) {
        inAdjacency.get(arc.getDest()).add(arc);
        outAdjacency.get(arc.getSource()).add(arc);
	}
	
	public void addEdge(Edge e) {
		edgeCardinality++;

		if(ensureVertex(e.source))
            adjacency.get(e.source).add(e);
		else
		    if(!isEdge(e))
                adjacency.get(e.source).add(e);
        //System.out.println("add" + e.source + " => "+ e.dest);

        Edge inversedEdge = new Edge(e.dest, e.source, e.weight);

        if(ensureVertex(inversedEdge.source))
            adjacency.get(inversedEdge.source).add(inversedEdge);
        else
            if(!isEdge(inversedEdge))
                adjacency.get(inversedEdge.source).add(inversedEdge);
        //System.out.println("add" + e.dest + " => "+ e.source);

        addArc(new Arc(e,false));
        addArc(new Arc(e, true));
	}

    private boolean isEdge(Edge e) {
        for(Edge edge : adjacency.get(e.source))
            if(edge.dest == e.dest)
                return true;
        return false;
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        /*for(LinkedList<Edge> adjacencies : adjacency)
            for(Edge edge: adjacencies) {
                result.append(edge.source).append(" => ").append(edge.dest).append("\n");
                System.out.println(result);
            }*/

        Iterator<Edge> graphIterator = iterator();
        while(graphIterator.hasNext()){
            Edge edge = graphIterator.next();
            result.append(edge.source).append(" => ").append(edge.dest).append("\n");
        }
        return result.toString();
    }

	@Override
	public Iterator<Edge> iterator() {
		return new GraphIterator();
	}

    public Arc[] outNeighbours(int sommet) {
        return null;
    }

    private class GraphIterator/*<Edge>*/ implements Iterator<Edge> {
	    int currentVertex = 0;
	    int currentEdge = 0;

        @Override
        public boolean hasNext() {

            if(currentVertex >= order)
                return false;

            if(adjacency.get(currentVertex).get(currentEdge) == adjacency.get(currentVertex).getLast()) {
                next();
                currentVertex++;
                currentEdge = 0;
                return false;
            }
            currentEdge++;
            return true;
        }

        @Override
        public Edge next() {
            return adjacency.get(currentVertex).get(currentEdge);
        }

        @Override
        public void remove() {

        }
    }

    public static void main(String argv[]){
        Graph graph = new Graph(20);
        for(int i = 0; i < 3; i++){
            for(int j = 0; j< 3; j++){
                graph.addEdge(new Edge(i,j,5));
            }
        }

        System.out.println(graph.toString());
    }


}
