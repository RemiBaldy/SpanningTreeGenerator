package Generators;

import Graph.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by moi on 09/12/2018.
 */
public class MinimumRandomWeight {

    Graph graph;
    private boolean[] reached;

    public MinimumRandomWeight(Graph graph) {
        this.graph = randomizeWeightArcs(graph);
        this.reached = new boolean[graph.getOrder()];
    }

    public Graph randomizeWeightArcs(Graph graph){
        Graph randomizedGraph = new Graph(graph.getOrder());
        Random rand = new Random();
        for(ArrayList<Arc> vertexAjacencies: graph.outAdjacency){
            if(vertexAjacencies != null){
                for(Arc arc : vertexAjacencies){
                    Edge rdmEdge = new Edge(arc.getSource(), arc.getDest(), rand.nextDouble());
                    randomizedGraph.addEdge(rdmEdge);
                    randomizedGraph.addArc(new Arc(rdmEdge,false));
                }
            }
        }
        return randomizedGraph;
    }

    public boolean allVertexesReached(){
        for(Boolean bool : reached)
            if (!bool) return bool;
        return true;
    }

    public ArrayList<Arc> Prim(){
        Random rand = new Random();
        reached[rand.nextInt(graph.getOrder())] = true;

        ArrayList<Arc> result = new ArrayList<>();

        double minimumWeightArc;
        Arc minimumArc = null;

        while(!allVertexesReached()){
            minimumWeightArc = graph.getOrder();
            for(int i = 0; i < graph.getOrder(); i++){
                if(reached[i]){
                    if(graph.outNeighbours(i) != null){
                        for(Arc arc : graph.outNeighbours(i)){
                            if(!reached[arc.getDest()] && arc.support.weight <= minimumWeightArc){
                                minimumWeightArc = arc.support.weight;
                                minimumArc = arc;
                            }
                        }
                    }
                }
            }
            reached[minimumArc.getDest()] = true;
            result.add(minimumArc);
        }
        return result;
    }
}
