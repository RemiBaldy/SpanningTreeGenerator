package Generators;

import Graph.*;

import java.util.ArrayList;
import java.util.Random;

public class AldousBroder {

    private int sommetActuel;
    private ArrayList<ArrayList<Arc>> arcsParcourus;
    private boolean[] reached;
    private Random rand;
    private Graph graph;

    public AldousBroder(Graph graph) {
        this.graph = graph;
        rand = new Random();
        this.sommetActuel = rand.nextInt(graph.getOrder());
        this.reached = new boolean[graph.getOrder()];
        reached[sommetActuel] = true;

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
        //System.out.println("depart : " + sommetActuel);

        while(!allVertexesReached()){
            ArrayList<Arc> neighbours = graph.outNeighbours(sommetActuel);

            int randomNeighbour = rand.nextInt(neighbours.size());
            sommetActuel = neighbours.get(randomNeighbour).getDest();
            if(!reached[sommetActuel]) {
                //System.out.println("premiere fois atteint: " + neighbours.get(randomNeighbour).toString());
                arcsParcourus.get(neighbours.get(randomNeighbour).getSource()).add(neighbours.get(randomNeighbour));
            }
            reached[sommetActuel] = true;
        }

        ArrayList<Arc> result = new ArrayList<>();
        for(ArrayList<Arc> arcs: arcsParcourus) {
            if(!arcs.isEmpty())
                result.addAll(arcs);
        }
        return result;
    }
}
