import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws CustomException, IOException {
        UndirectedGraph ug = new UndirectedGraph();
//        ug.addEdge(0, 1);
//        ug.addEdge(1, 2);
//        ug.addEdge(2, 3);
//        ug.addEdge(3, 1);
//        ug.addVertex(5);
//        ug.addEdge(4, 3);
//        int[][] adjMatrix = ug.getAdjMatrix();
//        for (int i = 0; i < ug.getNrVertices(); i++) {
//            System.out.println(Arrays.toString(adjMatrix[i]));
//        }

//        for (int i = 0; i < ug.getNrVertices(); i++) {
//            System.out.println(ug.dfsComponents(i));
//        }

//        Console console = new Console();
//        console.run();

        ug.result();
    }

}
