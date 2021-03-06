package Generators;

import Graph.*;

import java.util.ArrayList;
import java.util.Collections;
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



    public ArrayList<Arc> generateSpanningTree(){
        initialiseList();
        Random rand = new Random();
        int vertexStart = rand.nextInt(graph.getOrder());
        reached[vertexStart] = true;
        System.out.println("vertexStart = " + vertexStart);
        parcours(vertexStart);


        return result();
    }

    public void parcours(int vertex){
        /*while(outNeighboursUnreached(vertex) && !allVertexesReached()){*/
        ArrayList<Arc> neighbours = graph.outNeighbours(vertex);
        if(!neighbours.isEmpty()) {
            Collections.shuffle(neighbours);
            for (Arc arc : neighbours) {
                if (!reached[arc.getDest()]) {
                    //System.out.println("non atteint : "+ arc.getDest());
                    int vertexReached = arc.getDest();
                    reached[vertexReached] = true;
                    arcsParcourus.get(arc.getSource()).add(arc);
                    parcours(vertexReached);
                }
            }
        }
    }

    public boolean outNeighboursUnreached(int sommet){
        if(graph.outNeighbours(sommet) == null | graph.outNeighbours(sommet).isEmpty() ) return false;
        for(Arc arc : graph.outNeighbours(sommet))
            if(!reached[arc.getDest()])
                return true;
        return false;
    }

    public ArrayList<Arc> result(){
        ArrayList<Arc> result = new ArrayList<>();
        for(ArrayList<Arc> arcs: arcsParcourus) {
            if(!arcs.isEmpty())
                result.addAll(arcs);
        }
        return result;
    }
}
