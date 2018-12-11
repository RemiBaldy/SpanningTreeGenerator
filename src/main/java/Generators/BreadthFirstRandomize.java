package Generators;

import Graph.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by moi on 11/12/2018.
 */
public class BreadthFirstRandomize {

    private Graph graph;
    private boolean[] reached;
    private LinkedList<Integer> vertexWaitingRoom;

    public BreadthFirstRandomize(Graph graph) {
        this.graph = graph;
        this.reached = new boolean[graph.getOrder()];
        this.vertexWaitingRoom = new LinkedList<>();
    }

    public ArrayList<Arc> generateTree(int vertexStart){
        Random rdm = new Random();
        ArrayList<Arc> bfsResult = new ArrayList<>();
        addReached(vertexStart);

        while(!vertexWaitingRoom.isEmpty()){
            ArrayList<Arc> neighbours = graph.outNeighbours(vertexWaitingRoom.remove(rdm.nextInt(vertexWaitingRoom.size())));
            for(Arc arc : neighbours) {
                if (!reached[arc.getDest()]) {
                    addReached(arc.getDest());
                    bfsResult.add(arc);
                }
            }
        }
        return bfsResult;
    }

    public void addReached(int vertex){
        vertexWaitingRoom.push(vertex);
        reached[vertex] = true;
    }
}
