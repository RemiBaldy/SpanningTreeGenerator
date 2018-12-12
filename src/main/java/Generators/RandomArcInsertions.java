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
    private boolean isVertexRecursionTreated[];

    private int spanningTreeSize;
    Graph graph;
    int order;

    public RandomArcInsertions(Graph graph) {
        this.spanningTreeOut = new ArrayList<>();
        this.graph = graph;
        this.spanningTreeSize = 0;
        this.order = graph.getOrder();
        initialiseList();

    }

    private void initialiseList() {
        spanningTreeOut = new ArrayList<>();
        for (int i = 0; i < graph.getOrder(); i++) {
            spanningTreeOut.add(new ArrayList<Arc>());
        }
    }

    public ArrayList<Arc> generateTree(){
        Arc rdmArc;/* = getRandomArc();
        addArc(rdmArc);
        spanningTreeSize++;*/

        while (spanningTreeSize < graph.getOrder() - 1) {
            if(order % 100 == 0)
                System.out.println("order : "+order+ "  garphorder "+graph.getOrder() );
            rdmArc = getRandomArc();
            spanningTreeOut.get(rdmArc.getSource()).add(rdmArc);
            if (isCycle())
                remove(rdmArc);
            else {
                spanningTreeSize++;
                //System.out.println("new arc : " + rdmArc.toString());
            }
        }
        return result();
    }


    private Arc getRandomArc(){
        Random rand = new Random();
        int source = rand.nextInt(order/*graph.getOrder()*/);
        if(source >= order)
            System.out.println(source);
        int dest = rand.nextInt(graph.outAdjacency.get(source).size());
        if(/*isAlreadyArc(graph.outAdjacency.get(source).get(dest)) | */(isReached(graph.outAdjacency.get(source).get(dest).getSource()) && isReached(graph.outAdjacency.get(source).get(dest).getDest())))
            return getRandomArc();
        else {
            Arc arc = graph.outAdjacency.get(source).get(dest);
            graph.outAdjacency.get(source).remove(arc);
            if (graph.outAdjacency.get(source).isEmpty() | graph.outAdjacency.get(source) == null) {
                graph.outAdjacency.remove(graph.outAdjacency.get(source));
                order--;
            }
            return arc;
        }
    }

    private boolean isAlreadyArc(Arc newArc){
        if(spanningTreeOut.get(newArc.getSource()).isEmpty())
            return false;
        for(Arc arc : spanningTreeOut.get(newArc.getSource()))
            if(arc.getDest() == newArc.getDest())
                return true;
        return false;
        //return (!visited[newArc.getSource()] | !visited[newArc.getDest()]);
    }

    private void addArc(Arc arc){
        spanningTreeOut.get(arc.getSource()).add(arc);
    }

    private boolean isCycle(){
        visited = new boolean[graph.getOrder()];
        isVertexRecursionTreated = new boolean[graph.getOrder()];

        for(ArrayList<Arc> arcs : spanningTreeOut)
            if(!arcs.isEmpty())
                if(isCycleRecursive(arcs.get(0).getSource()))
                    return true;
        return false;
    }

    private boolean isCycleRecursive(int vertex){
        if (isVertexRecursionTreated[vertex])
            return true;

        if (visited[vertex])
            return false;

        visited[vertex] = true;

        isVertexRecursionTreated[vertex] = true;

        for (Arc arc : spanningTreeOut.get(vertex))
            if (isCycleRecursive(arc.getDest()))
                return true;

        isVertexRecursionTreated[vertex] = false;

        return false;
    }

    private void remove(Arc rdmArc){
        spanningTreeOut.get(rdmArc.getSource()).remove(rdmArc/*spanningTreeOut.get(rdmArc.getSource()).size()-1*/);
    }
    private ArrayList<Arc> result(){
         ArrayList<Arc> result = new ArrayList<>();
         for(ArrayList<Arc> arcs: spanningTreeOut) {
             if(arcs != null && !arcs.isEmpty())
                 result.addAll(arcs);
         }
         return result;
    }

    private boolean isReached(int vertex){
        return !spanningTreeOut.get(vertex).isEmpty();
    }
}
