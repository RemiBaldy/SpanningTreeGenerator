package Graph;

import java.util.ArrayList;
import java.util.Iterator;



public class Graph implements Iterable<Edge>{
    // classe de graphe non orientés permettant de manipuler
    // en même temps des arcs (orientés)
    // pour pouvoir stocker un arbre couvrant, en plus du graphe
    
	private int order;
	private int edgeCardinality;

	
	public ArrayList<ArrayList<Edge>> adjacency;
	public ArrayList<ArrayList<Arc>> inAdjacency;
	public ArrayList<ArrayList<Arc>> outAdjacency;

    public int getOrder() {
        return order;
    }

    private boolean isVertex(int index) {
        return(adjacency.get(index) != null);
	}
	
	public <T> ArrayList<ArrayList<T>> initialiseList(int size) {
		ArrayList<ArrayList<T>> res = new ArrayList<>(size);
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
	}
	
	public void addVertex(int indexVertex) {
	    order++;
	    adjacency.set(indexVertex, new ArrayList<Edge>());
        inAdjacency.set(indexVertex, new ArrayList<Arc>());
        outAdjacency.set(indexVertex, new ArrayList<Arc>());
	}
	
	public void ensureVertex(int indexVertex) {
		if(!isVertex(indexVertex))
            addVertex(indexVertex);
	}
	
	public void addArc(Arc arc) {
        inAdjacency.get(arc.getDest()).add(arc);
        //inAdjacency.get(arc.getSource()).add(arc);
        outAdjacency.get(arc.getSource()).add(arc);
	}
	
	public void addEdge(Edge e) {
		edgeCardinality++;
        Edge inversedEdge = new Edge(e.dest, e.source, e.weight);

		ensureVertex(e.source);
		if(!isEdge(e))
            adjacency.get(e.source).add(e);



        ensureVertex(inversedEdge.source);
        if(!isEdge(inversedEdge)) {
            adjacency.get(inversedEdge.source).add(inversedEdge);
            addArc(new Arc(e, true));
            addArc(new Arc(e, false));
        }

	}

    private boolean isEdge(Edge e) {
        for(Edge edge : adjacency.get(e.source))
            if(edge.dest == e.dest)
                return true;
        return false;
    }

    public ArrayList<Arc> outNeighbours(int sommet) {
        return outAdjacency.get(sommet);
    }


    public String toString(){
        StringBuilder result = new StringBuilder();

        Iterator<Edge> graphIterator = iterator();
        while(graphIterator.hasNext()){
            Edge edge = graphIterator.next();
            result.append(edge.source).append(" <=> ").append(edge.dest).append("\n");
        }
        return result.toString();
    }
    public String arcsAdjacencyToString(){
        StringBuilder result = new StringBuilder();
        result.append("inAdjacency : \n");
        for(ArrayList<Arc> arcs : inAdjacency)
            if(arcs != null)
                for(Arc arc : arcs)
                    result.append(arc.getDest()).append(" <= ").append(arc.getSource()).append("\n");

        result.append("outAdjacency : \n");
        for(ArrayList<Arc> arcs : outAdjacency)
            if(arcs != null)
                for(Arc arc : arcs)
                    result.append(arc.toString());

        return result.toString();
    }



	@Override
	public Iterator<Edge> iterator() {
		return new GraphIterator();
	}

    private class GraphIterator/*<Graph.Edge>*/ implements Iterator<Edge> {
	    int currentVertex = 0;
	    int currentEdge = 0;

        @Override
        public boolean hasNext() {

            if(currentVertex > order)
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
            while(adjacency.get(currentVertex) == null)
                currentVertex++;
            if(currentEdge == adjacency.get(currentVertex).size() - 1) {
                currentEdge = 0;
                return adjacency.get(currentVertex++).get(adjacency.get(currentVertex-1).size() - 1);
            }
            return adjacency.get(currentVertex).get(currentEdge++);
        }

        @Override
        public void remove() {

        }

    }

    public static void main(String argv[]){
        Graph graph = new Graph(20);


        graph.addEdge(new Edge(1,2,5));
        graph.addEdge(new Edge(2,3,6));
        graph.addEdge(new Edge(1,3,6));
        graph.addEdge(new Edge(1,4,6));
        graph.addEdge(new Edge(3,5,6));


        System.out.println(graph.toString());
        System.out.println(graph.arcsAdjacencyToString());


    }


}
