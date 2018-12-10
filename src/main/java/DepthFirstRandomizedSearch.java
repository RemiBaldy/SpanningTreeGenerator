import java.util.ArrayList;
import java.util.Random;

public class DepthFirstRandomizedSearch {
    private ArrayList<ArrayList<Arc>> arcsParcourus;
    private boolean[] reached;
    Graph graph;

    public DepthFirstRandomizedSearch(Graph graph) {
        this.graph = graph;
        this.reached = new boolean[graph.getOrder()];
    }

    private void initialiseList(){
        arcsParcourus = new ArrayList<>();
        for (int i = 0; i < graph.getOrder(); i++)
            arcsParcourus.add(new ArrayList<Arc>());
    }

    public boolean allVertexesReached(){
        for(Boolean bool : reached)
            if (!bool) return bool;
        return true;
    }

    public boolean outNeighboursUnreached(int sommet){
        if(graph.outNeighbours(sommet) == null) return false;
        for(Arc arc : graph.outNeighbours(sommet))
            if(!reached[arc.getDest()])
                return true;
        return false;
    }

    public ArrayList<Arc> generateSpanningTree(){
        initialiseList();
        Random rand = new Random();
        int vertexStart = rand.nextInt(graph.getOrder());
        reached[vertexStart] = true;
        System.out.println("vertexStart = " + vertexStart);
        parcours(vertexStart);

        ArrayList<Arc> result = new ArrayList<>();
        for(ArrayList<Arc> arcs: arcsParcourus) {
            if(!arcs.isEmpty())
                result.add(arcs.get(0));
        }
        return result;
    }

    public void parcours(int vertex){
        Random rand = new Random();
        while(outNeighboursUnreached(vertex) && !allVertexesReached()){
            ArrayList<Arc> neighbours = graph.outNeighbours(vertex);
            int randomNeighbour = rand.nextInt(neighbours.size());
            int vertexReached = neighbours.get(randomNeighbour).getDest();
            reached[vertexReached] = true;
            arcsParcourus.get(neighbours.get(randomNeighbour).getDest()).add(neighbours.get(randomNeighbour));
            parcours(vertexReached);
        }
    }
}
