import java.util.ArrayList;
import java.util.Random;

/**
 * Created by moi on 10/12/2018.
 */
public class RandomArcInsertions {
    ArrayList<ArrayList<Arc>> spanningTreeOut;
    ArrayList<ArrayList<Arc>> spanningTreeIn;
    boolean reached[];
    int spanningTreeSize;
    Graph graph;

    public RandomArcInsertions(Graph graph) {
        this.spanningTreeOut = new ArrayList<>();
        this.spanningTreeIn = new ArrayList<>();
        this.graph = graph;
        this.spanningTreeSize = 0;
        this.reached = new boolean[graph.getOrder()];
    }

    private void initialiseList(){
        spanningTreeOut = new ArrayList<>();
        spanningTreeIn = new ArrayList<>();
        for (int i = 0; i < graph.getOrder(); i++) {
            spanningTreeOut.add(new ArrayList<Arc>());
            spanningTreeIn.add(new ArrayList<Arc>());
        }
    }
    private void resetWeight(){
        for(ArrayList<Arc> arcs: spanningTreeOut)
            if(arcs != null)
                for(Arc arc : arcs)
                    arc.support.weight = 0;
    }

    public void generateTree(){
        Arc rdmArc = getRandomArcc();
        reached[rdmArc.getSource()] = true;
        reached[rdmArc.getDest()] = true;
        addArc(rdmArc);

        while (spanningTreeOut.size() < graph.getOrder() - 1) {
            rdmArc = getRandomArcc();
            spanningTreeOut.get(rdmArc.getSource()).add(rdmArc);
            if (searchForCycles())
                spanningTreeOut.get(rdmArc.getSource()).remove(rdmArc);
            else
                spanningTreeSize++;
        }
    }


    private Arc getRandomArc(){
        Random rand = new Random();
        int source = rand.nextInt(graph.getOrder());
        int dest = rand.nextInt(graph.getOrder());
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
        spanningTreeIn.get(arc.getDest()).add(arc);
    }

    public boolean searchForCycles(){
        resetWeight();
        Arc previousArc;

        for(ArrayList<Arc> arcs : spanningTreeOut)
            if(!arcs.isEmpty())
                for(Arc arc : arcs)
                    if(!reached[arc.getDest()]) {
                        reached[arc.getDest()] = true;
                        arc.support.weight = arc.getSource();
                        nextArc();
                    }
                    else if()
    }
}
