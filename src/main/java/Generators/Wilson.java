package Generators;

import Graph.Arc;
import Graph.Graph;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by moi on 11/12/2018.
 */
public class Wilson {
    private boolean[] reached;
    Graph graph;
    Random random = new Random();



    public Wilson(Graph graph) {
        this.graph = graph;
        this.reached = new boolean[graph.getOrder()];
        //initialiseList();;
    }

    /*private void initialiseList(){
        arcsParcourus = new ArrayList<>();
        for (int i = 0; i < graph.getOrder(); i++)
            arcsParcourus.add(new ArrayList<Arc>());
    }*/

    public boolean allVertexesReached(){
        for(Boolean bool : reached)
            if (!bool) return bool;
        return true;
    }

    public int maximumDegreeVertex(){
        int maxSize = 0;
        int maxVertex = -1;
        for (ArrayList<Arc> arcs : graph.inAdjacency)
            if(arcs != null)
                if(arcs.size() > maxSize){
                    maxSize = arcs.size();
                    maxVertex = arcs.get(0).getDest();
                }
        return maxVertex;
    }

    public ArrayList<Arc> generateSpanningTree(){
        ArrayList<Arc> result = new ArrayList<>();
        int currentVertex = maximumDegreeVertex();
        reached[currentVertex] = true;

        while(!allVertexesReached()) {
            while (reached[currentVertex])
                currentVertex = random.nextInt(graph.getOrder());


            ArrayList<Arc> resultParcours = parcours(currentVertex);
            updateReached(resultParcours);
            /*for(Arc arc : test)
                System.out.println(arc.toString());
            System.out.println("---------------------------------------------");*/
            result.addAll(resultParcours);
        }
        return result;
    }

    public ArrayList<Arc> parcours(int vertex){
        ArrayList<Arc> chemin = new ArrayList<>();
        initialiseList(chemin, graph.getOrder());
        ArrayList<Arc> neighbours = graph.outNeighbours(vertex);
        int randomNeighbour = random.nextInt(neighbours.size());
        chemin.set(neighbours.get(randomNeighbour).getSource(),neighbours.get(randomNeighbour));
        int currentVertex;

        while(!reached[neighbours.get(randomNeighbour).getDest()]){//chemin.get()
            currentVertex = neighbours.get(randomNeighbour).getDest();
            neighbours = graph.outNeighbours(currentVertex);
            randomNeighbour = random.nextInt(neighbours.size());
            chemin.set(neighbours.get(randomNeighbour).getSource(),neighbours.get(randomNeighbour));
        }
        return cheminSansCycles(vertex, chemin);
    }
    public void initialiseList(ArrayList<Arc> list, int size){
        for(int i =0; i < size; i++)
            list.add(null);
    }

    public ArrayList<Arc> cheminSansCycles(int startVertex,ArrayList<Arc> chemin){
        ArrayList<Arc> result = new ArrayList<>();
        int currentVertex = startVertex;
        while(chemin.get(currentVertex) != null){
            result.add(chemin.get(currentVertex));
            currentVertex = chemin.get(currentVertex).getDest();
        }
        return result;
    }
    public void updateReached(ArrayList<Arc> resultParcours){
        for(Arc arc : resultParcours)
            reached[arc.getSource()] = true;
    }
}
