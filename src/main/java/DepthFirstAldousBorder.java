import java.util.ArrayList;
import java.util.Random;

public class DepthFirstAldousBorder {
    //private int sommetActuel;
    private ArrayList<ArrayList<Arc>> arcsParcourus;
    private boolean[] reached;
    Graph graph;

    public DepthFirstAldousBorder(Graph graph) {
        this.graph = graph;
        //this.sommetActuel = (int)(Math.random() * (graph.getOrder() + 1));
        this.reached = new boolean[graph.getOrder()];
        //reached[sommetActuel] = true;

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


    /*public ArrayList<Arc> parcours(){
        initialiseList();
        Random rand = new Random();

        while(!allVertexesReached()){
            ArrayList<Arc> neighbours = graph.outNeighbours(sommetActuel);
            //cas ou sommet n'a pas de voisin, pas tous les sommets sotn atteints
            int randomNeighbour = rand.nextInt(neighbours.size());
            sommetActuel = neighbours.get(randomNeighbour).getDest();
            reached[sommetActuel] = true;
            arcsParcourus.get(neighbours.get(randomNeighbour).getDest()).add(neighbours.get(randomNeighbour));
        }

        ArrayList<Arc> result = new ArrayList<>();
        for(int i = 0; i < graph.getOrder(); i++)
            result.add(arcsParcourus.get(i).get(0));

        return result;
    }*/
    public boolean outNeighboursUnreached(int sommet){
        if(graph.outNeighbours(sommet) == null) return false;
        for(Arc arc : graph.outNeighbours(sommet))
            if(!reached[arc.getDest()])
                return true;
        return false;
    }

    public ArrayList<Arc> generateSpanningTree(){
        initialiseList();
        Random rand = new Random();
        int vertexStart = rand.nextInt(graph.getOrder());
        reached[vertexStart] = true;
        System.out.println("vertexStart = " + vertexStart);
        parcours(vertexStart);

        ArrayList<Arc> result = new ArrayList<>();
        for(ArrayList<Arc> arcs: arcsParcourus) {
            if(!arcs.isEmpty())
                result.add(arcs.get(0));
        }
        return result;
    }

    public void parcours(int vertex){
        Random rand = new Random();
        while(outNeighboursUnreached(vertex) && !allVertexesReached()){
            ArrayList<Arc> neighbours = graph.outNeighbours(vertex);
            int randomNeighbour = rand.nextInt(neighbours.size());
            int vertexReached = neighbours.get(randomNeighbour).getDest();
            reached[vertexReached] = true;
            arcsParcourus.get(neighbours.get(randomNeighbour).getDest()).add(neighbours.get(randomNeighbour));
            parcours(vertexReached);
        }
    }
}
