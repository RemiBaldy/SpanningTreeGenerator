import java.util.ArrayList;

public class MainTest {
    public static void main(String argv[]) {
        Graph graph = new Graph(20);

        ArrayList<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1,2,5));
        edges.add(new Edge(2,3,6));
        edges.add(new Edge(1,0,6));
        edges.add(new Edge(0,4,6));
        edges.add(new Edge(0,2,6));
        edges.add(new Edge(3,5,6));
        edges.add(new Edge(0,5,6));
        edges.add(new Edge(3,1,6));
        edges.add(new Edge(5,1,6));

        for(Edge edge : edges){
            graph.addEdge(edge);
            graph.addArc(new Arc(edge, false));
        }

        System.out.println(graph.arcsAdjacencyToString());

        DepthFirstAldousBorder aldousBorder = new DepthFirstAldousBorder(graph);

        for(Arc arc : aldousBorder.generateSpanningTree())
            System.out.println(arc.toString());


        /* Test BFS*/
        /*BreadthFirstSearch bfs = new BreadthFirstSearch(graph);
        for(Arc arc : bfs.generateTree(1))
            System.out.println(arc.toString());*/


        /*Minimum random weight*/
        /*MinimumRandomWeight min = new MinimumRandomWeight(graph);
        for(Arc arc : min.Prim())
            System.out.println(arc.toString());*/

        /*Random weight*/
        /*System.out.println(min.randomizeWeightArcs(graph).arcsAdjacencyToString());*/


    }
}
