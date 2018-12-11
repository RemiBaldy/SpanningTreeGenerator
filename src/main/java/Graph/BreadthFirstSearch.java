package Graph;


import java.util.ArrayList;
import java.util.LinkedList;

public class BreadthFirstSearch {

    private Graph graph;
    private boolean[] reached;
    private LinkedList<Integer> vertexWaitingRoom;

    public BreadthFirstSearch(Graph graph) {
        this.graph = graph;
        this.reached = new boolean[graph.getOrder()];
        this.vertexWaitingRoom = new LinkedList<>();
    }

    public ArrayList<Arc> generateTree(int vertexStart){
        ArrayList<Arc> bfsResult = new ArrayList<>();
        addReached(vertexStart);

        while(!vertexWaitingRoom.isEmpty()){
            ArrayList<Arc> neighbours = graph.outNeighbours(vertexWaitingRoom.removeLast());
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

   /* public static ArrayList<Graph.Graph.Arc> generateTree(Graph.Graph graph, int i) {

        return null;
    }*/
}
