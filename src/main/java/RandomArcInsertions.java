import java.util.ArrayList;
import java.util.Random;

/**
 * Created by moi on 10/12/2018.
 */
public class RandomArcInsertions {
    ArrayList<ArrayList<Arc>> spanningTree;
    Graph graph;

    public RandomArcInsertions(Graph graph) {
        this.spanningTree = new ArrayList<>();
        this.graph = graph;
    }

    private void initialiseList(){
        spanningTree = new ArrayList<>();
        for (int i = 0; i < graph.getOrder(); i++)
            spanningTree.add(new ArrayList<Arc>());
    }

    public void generateTree(){
        Arc rdmArc = getRandomArcc();
        spanningTree.get(rdmArc.getSource()).add(rdmArc);
        if(searchForCycles())
            spanningTree.get(rdmArc.getSource()).remove(rdmArc);
    }


    private Arc getRandomArcc(){
        Random rand = new Random();
        return graph.outAdjacency.get(rand.nextInt(graph.getOrder())).get(rand.nextInt(graph.getOrder()));
    }

    public boolean searchForCycles(){
        for(ArrayList<Arc> arcs : spanningTree)
            if(!arcs.isEmpty())

    }
}
