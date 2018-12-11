package Generators;

import Graph.Arc;
import Graph.Graph;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by moi on 11/12/2018.
 */
public class Wilson {
    private ArrayList<Arc> arcsParcourus;
    private boolean[] reached;
    Graph graph;


    public Wilson(Graph graph) {
        this.graph = graph;
        this.reached = new boolean[graph.getOrder()];
        initialiseList();;
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

    public int maximumDegreeVertex(){
        int maxSize = 0;
        int maxVertex = -1;
        for (ArrayList<Arc> arcs : graph.inAdjacency)
            if(arcs != null)
                if(arcs.size() > maxSize){
                    maxSize = arcs.size();
                    maxVertex = arcs.get(0).getDest();
                }
        return maxVertex;
    }

    public ArrayList<Arc> generateSpanningTree(){
        ArrayList<Arc> result = new ArrayList<>();
        int currentVertex = maximumDegreeVertex();
        reached[currentVertex] = true;

        Random random = new Random();
        while(reached[currentVertex])
            currentVertex = random.nextInt(graph.getOrder());

        parcours(currentVertex);
        reached[currentVertex] = true;


        return result;

    }

    public void parcours(int vertex){
        Random rand = new Random();
        ArrayList<Arc> neighbours = graph.outNeighbours(vertex);
        int randomNeighbour = rand.nextInt(neighbours.size());
        arcsParcourus.add()

        while(!reached[neighbours.get(randomNeighbour).getDest()]){
            ArrayList<Arc> neighbours = graph.outNeighbours(vertex);
            int randomNeighbour = rand.nextInt(neighbours.size());
            if(!reached[neighbours.get(randomNeighbour).getDest()]) {
                int vertexReached = neighbours.get(randomNeighbour).getDest();
                reached[vertexReached] = true;
                arcsParcourus.get(neighbours.get(randomNeighbour).getDest()).add(neighbours.get(randomNeighbour));
                parcours(vertexReached);
            }
        }
    }
}
