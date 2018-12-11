import Generators.*;
import Graph.*;

import java.util.ArrayList;

public class MainTest {
    public static void main(String argv[]) {
        Graph graph = new Graph(20);

        ArrayList<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1,2,5));
        edges.add(new Edge(2,3,6));
        edges.add(new Edge(1,4,6));
        edges.add(new Edge(0,2,6));
        edges.add(new Edge(3,5,6));
        edges.add(new Edge(0,5,6));
        edges.add(new Edge(5,1,6));

        edges.add(new Edge(1,0,6));
        edges.add(new Edge(3,1,6));

        for(Edge edge : edges){
            graph.addEdge(edge);
            /*graph.addArc(new Arc(edge, false));
            graph.addArc(new Arc(edge, true));*/
        }

        //System.out.println(graph.arcsAdjacencyToString());




        /* Test BFS*/
        /*BreadthFirstSearch bfs = new BreadthFirstSearch(graph);
        for(Arc arc : bfs.generateTree(1))
            System.out.println(arc.toString());*/

        /*Bfs random*/
        /*BreadthFirstRandomize bfs = new BreadthFirstRandomize(graph);
        for(Arc arc : bfs.generateTree(1))
            System.out.println(arc.toString());*/




        /*Dfs random*/
        /*DepthFirstRandomizedSearch dfsRdm = new Generators.DepthFirstRandomizedSearch(graph);
        for(Arc arc : dfsRdm.generateSpanningTree())
            System.out.println(arc.toString());*/




        /*Aldous Broder*/
        /*AldousBroder aldousBroder = new Generators.AldousBroder(graph);
        for(Arc arc : aldousBroder.generateSpanningTree())
            System.out.println(arc.toString());*/




        /*Minimum random weight*/
        /*Generators.MinimumRandomWeight min = new Generators.MinimumRandomWeight(graph);
        for(Arc arc : min.Prim())
            System.out.println(arc.toString());*/

        /*Random weight*/
        //System.out.println(min.randomizeWeightArcs(graph).arcsAdjacencyToString());





        /*Random Arc Insertions*/
        RandomArcInsertions rdmArcIns = new RandomArcInsertions(graph);
        for(Arc arc : rdmArcIns.generateTree())
            System.out.println(arc.toString());




        /*Wilson*/
        /*Wilson wilson = new Wilson(graph);
        for(Arc arc : wilson.generateSpanningTree())
            System.out.println(arc.toString());*/

    }
}
