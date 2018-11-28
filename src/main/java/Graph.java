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
        return(adjacency.get(index) != null);
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
        System.out.println("adjacency add "+ indexVertex);
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

		if(ensureVertex(e.source)){
            adjacency.get(e.source).add(e);
            System.out.println("add11 "+ e.source +" => "+ e.dest);
		}
		else if(!isEdge(e)) {
            adjacency.get(e.source).add(e);
            System.out.println("add12 "+ e.source +" => "+ e.dest);
        }

        Edge inversedEdge = new Edge(e.dest, e.source, e.weight);

        if(ensureVertex(inversedEdge.source)){
            adjacency.get(inversedEdge.source).add(inversedEdge);
            System.out.println("add21 "+ inversedEdge.source +" => "+ inversedEdge.dest);
        }
        else if(!isEdge(inversedEdge)){
            adjacency.get(inversedEdge.source).add(inversedEdge);
            System.out.println("add22 "+ inversedEdge.source +" => "+ inversedEdge.dest);
        }


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
        for(LinkedList<Edge> adjacencies : adjacency) {
            for (Edge edge : adjacencies) {
                result.append(edge.source).append(" => ").append(edge.dest).append("\n");
                System.out.println(result);
            }
        }
            /*Iterator<Edge> graphIterator = iterator();
        while(graphIterator.hasNext()){
            Edge edge = graphIterator.next();
            result.append(edge.source).append(" => ").append(edge.dest).append("\n");
        }*/
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

            /*(adjacency.get(currentVertex).get(currentEdge) == adjacency.get(currentVertex).getLast()) {
                currentVertex++;
                currentEdge = 0;
                return false;
            }
            currentEdge++;*/
            return true;
        }

        @Override
        public Edge next() {
            System.out.println(adjacency.get(currentVertex).get(currentEdge).source + " "+ adjacency.get(currentVertex).get(currentEdge).dest);
            if(adjacency.get(currentVertex).get(currentEdge) == adjacency.get(currentVertex).getLast()) {
                currentVertex++;
                currentEdge = 0;
                return adjacency.get(currentVertex-1).getLast();
            }
            return adjacency.get(currentVertex).get(currentEdge++);
        }

        @Override
        public void remove() {

        }
    }

    public static void main(String argv[]){
        Graph graph = new Graph(20);
        /*for(int i = 0; i < 3; i++){
            for(int j = 3; j< 5; j++){
                graph.addEdge(new Edge(i,j,5));
            }
        }*/

        graph.addEdge(new Edge(2,3,5));
        graph.addEdge(new Edge(2,4,6));
        graph.addEdge(new Edge(1,3,6));
        graph.addEdge(new Edge(1,4,6));
        graph.addEdge(new Edge(3,5,6));


        /*graph.addEdge(new Edge(0,3,5));
        graph.addEdge(new Edge(0,4,6));
        //graph.addEdge(new Edge(3,2,6));
        graph.addEdge(new Edge(1,3,6));
        graph.addEdge(new Edge(1,4,6));*/

        for(Edge e : graph.adjacency.get(1))
            System.out.println(e.source + " => " +e.dest);
        System.out.println();



        for(Edge e : graph.adjacency.get(3))
            System.out.println(e.source + " => " +e.dest);
        System.out.println();

        for(Edge e : graph.adjacency.get(4))
            System.out.println(e.source + " => " +e.dest);
        System.out.println();

        for(Edge e : graph.adjacency.get(5))
            System.out.println(e.source + " => " +e.dest);




        /*for(Edge edge : graph)
            result.append(edge.source).append(" => ").append(edge.dest).append("\n");*/

       //System.out.println(graph.toString());
        /*if(graph.adjacency.get(3) == null)
            System.out.println(graph.adjacency.get(3).getFirst().source);*/

    }


}
