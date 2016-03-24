public class Main {

    public static void main(String[] args) throws CustomException {
        UndirectedGraph ug = new UndirectedGraph(4);

        ug.addEdge(0, 1);
        ug.addEdge(1, 2);
        ug.addEdge(2, 3);
        ug.addEdge(3, 1);
        ug.addVertex(5);
        ug.addEdge(4, 3);
        System.out.println(ug.dfsComponents(0));
    }

}
