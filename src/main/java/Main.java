import java.io.IOException;

public class Main {

    public static void main(String[] args) throws CustomException, IOException {
//        UndirectedGraph ug = new UndirectedGraph(4);
//        ug.addEdge(0, 1);
//        ug.addEdge(1, 2);
//        ug.addEdge(2, 3);
//        ug.addEdge(3, 1);
//        ug.addVertex(5);
//        ug.addEdge(4, 3);
//        System.out.println(ug.dfsComponents(0));

        try{
            DirectedGraph graph = new DirectedGraph();
            graph.addVertex(100000);
            graph.addVertex(2);
            Vertex<Integer> e = new Vertex<Integer>(0);
            Vertex<Integer> b = new Vertex<Integer>(154);
            graph.addEdge(e,b);

            System.out.println(graph.isEdge(new Vertex<Integer>(0), new Vertex<Integer>(161)));
            System.out.println(graph.isEdge(new Vertex<Integer>(0), new Vertex<Integer>(162)));
            graph.addCostOfEdge(new Vertex<Integer>(0), new Vertex<Integer>(161), 1);
        }catch (CustomException e){
            System.out.println(e.getMsg());
        }
    }

}
