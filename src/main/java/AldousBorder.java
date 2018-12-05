import java.util.ArrayList;

public class AldousBorder {
    private int sommetActuel;
    private ArrayList<ArrayList<Arc>> arcsParcourus;
    private Boolean[] reached;
    Graph graph;

    public AldousBorder(Graph graph) {
        this.graph = graph;
        this.sommetActuel = (int)(Math.random() * (graph.getOrder() + 1));
        this.reached = new Boolean[graph.getOrder()];
        reached[sommetActuel] = true;

    }

    private void initialiseList(){
        arcsParcourus = new ArrayList<>();
        for (int i = 0; i < graph.getOrder(); i++)
            arcsParcourus.set(i, new ArrayList<Arc>());
    }

    public boolean allVertexesReached(){
        for(Boolean bool : reached)
            if(!bool) return bool;
        return true;
    }

    public ArrayList<Arc> parcours(){
        initialiseList();

        while(!allVertexesReached()){
            ArrayList<Arc> neighbours = graph.outNeighbours(sommetActuel);

            int randomNeighbour = (int)(Math.random() * (graph.getOrder() + 1));
            sommetActuel = neighbours.get(randomNeighbour).getDest();
            reached[sommetActuel] = true;
            arcsParcourus.get(neighbours.get(randomNeighbour).getDest()).add(neighbours.get(randomNeighbour));
        }

        ArrayList<Arc> result = new ArrayList<>();
        for(int i = 0; i < graph.getOrder(); i++)
            result.add(arcsParcourus.get(i).get(0));

        return result;
    }
}
