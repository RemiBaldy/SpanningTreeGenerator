package Generators;

import Graph.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by moi on 10/12/2018.
 */
public class RandomArcInsertions {
    private ArrayList<ArrayList<Arc>> spanningTreeOut;
    private boolean visited[];
    private boolean vertexRecursionTreated[];

    private int spanningTreeSize;
    Graph graph;

    public RandomArcInsertions(Graph graph) {
        this.spanningTreeOut = new ArrayList<>();
        this.graph = graph;
        this.spanningTreeSize = 0;
        initialiseList();

    }

    private void initialiseList(){
        spanningTreeOut = new ArrayList<>();
        for (int i = 0; i < graph.getOrder(); i++) {
            spanningTreeOut.add(new ArrayList<Arc>());
        }
    }
    /*private void resetWeight(){
        for(ArrayList<Graph.Arc> arcs: spanningTreeOut)
            if(arcs != null)
                for(Graph.Graph.Arc arc : arcs)
                    arc.support.weight = 0;
    }*/

    public ArrayList<Arc> generateTree(){
        Arc rdmArc = getRandomArc();
        addArc(rdmArc);
        spanningTreeSize++;

        while (spanningTreeSize < graph.getOrder() - 1) {
            rdmArc = getRandomArc();
            spanningTreeOut.get(rdmArc.getSource()).add(rdmArc);
            if (isCycle())
                spanningTreeOut.get(rdmArc.getSource()).remove(rdmArc);
            else {
                spanningTreeSize++;
                System.out.println("new arc : " + rdmArc.toString());
            }
        }
        ArrayList<Arc> result = new ArrayList<>();
        for(ArrayList<Arc> arcs: spanningTreeOut) {
            if(!arcs.isEmpty())
                result.addAll(arcs);
        }
        return result;
    }


    private Arc getRandomArc(){

        Random rand = new Random();
        int source = rand.nextInt(graph.getOrder());
        int dest = rand.nextInt(graph.outAdjacency.get(source).size());
        /*rajouter !reached[source] | !reached[dest]*/
        if(source == dest | isAlreadyArc(graph.outAdjacency.get(source).get(dest)))
            return getRandomArc();
        return graph.outAdjacency.get(source).get(dest);
    }

    private boolean isAlreadyArc(Arc newArc){
        if(spanningTreeOut.get(newArc.getSource()).isEmpty())
            return false;
        for(Arc arc : spanningTreeOut.get(newArc.getSource()))
            if(arc.getDest() == newArc.getDest())
                return true;
        return false;
    }

    private void addArc(Arc arc){
        spanningTreeOut.get(arc.getSource()).add(arc);
    }

    private boolean isCycle(){
        visited = new boolean[graph.getOrder()];
        vertexRecursionTreated = new boolean[graph.getOrder()];

        for(ArrayList<Arc> arcs : spanningTreeOut)
            if(!arcs.isEmpty())
                if(isCycleRecursive(arcs.get(0).getSource()))
                    return true;
        return false;
    }

    private boolean isCycleRecursive(int vertex){
        if (vertexRecursionTreated[vertex])
            return true;

        if (visited[vertex])
            return false;

        visited[vertex] = true;

        vertexRecursionTreated[vertex] = true;

        for (Arc arc : spanningTreeOut.get(vertex))
            if (isCycleRecursive(arc.getDest()))
                return true;

        vertexRecursionTreated[vertex] = false;

        return false;
    }
}
