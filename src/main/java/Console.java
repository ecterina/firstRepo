import java.io.IOException;
import java.util.*;

public class Console {

    private void menu(){
        System.out.println("Please chose one option:");
        System.out.println("0 - to exit");
        System.out.println("1 - get the number of vertices");
        System.out.println("2 - get the indegree of a vertex");
        System.out.println("3 - get the outdegree of a vertex");
        System.out.println("4 - add a vertex");
        System.out.println("5 - remove a vertex");
        System.out.println("6 - add an edge");
        System.out.println("7 - remove an edge");
        System.out.println("8 - add cost of edge");
        System.out.println("9 - update the cost of an edge");
        System.out.println("10 - get cost of an edge");
        System.out.println("11 - find is there edge between two vertices");
        System.out.println("12 - get the inbound vertices");
        System.out.println("13 - get the outbound of vertices");
        System.out.println("14 - write the graph into a file");

    }

    private void inDegree(DirectedGraph graph){
        System.out.println("Give the label of the vertex:");
        Scanner input = new Scanner(System.in);
        try{
            int label = input.nextInt();
            System.out.println(graph.getInDegree(label));
        } catch (InputMismatchException e) {
            System.out.println("Wrong input");
        } catch (CustomException e) {
            System.out.println(e.getMsg());
        }
    }

    private void outDegree(DirectedGraph graph){
        System.out.println("Give the label of the vertex:");
        Scanner input = new Scanner(System.in);
        try{
            int label = input.nextInt();
            System.out.println(graph.getOutDegree(label));
        } catch (InputMismatchException e) {
            System.out.println("Wrong input");
        } catch (CustomException e) {
            System.out.println(e.getMsg());
        }
    }

    private void addVertex(DirectedGraph graph){
        System.out.println("Give the label of the vertex:");
        Scanner input = new Scanner(System.in);
        try{
            int label = input.nextInt();
            graph.addVertex(label);
        } catch (InputMismatchException e) {
            System.out.println("Wrong input");
        } catch (CustomException e) {
            System.out.println(e.getMsg());
        }
    }

    private void removeVertex(DirectedGraph graph){
        System.out.println("Give the label of the vertex:");
        Scanner input = new Scanner(System.in);
        try{
            int label = input.nextInt();
            graph.removeVertex(label);
        } catch (InputMismatchException e) {
            System.out.println("Wrong input");
        } catch (CustomException e) {
            System.out.println(e.getMsg());
        }
    }

    private void addEdge(DirectedGraph graph){
        Scanner input = new Scanner(System.in);
        try{
            System.out.println("Give the label of the out vertex:");
            int out = input.nextInt();
            System.out.println("Give the label of the in vertex:");
            int in = input.nextInt();
            graph.addEdge(out, in);
        } catch (InputMismatchException e) {
            System.out.println("Wrong input");
        } catch (CustomException e) {
            System.out.println(e.getMsg());
        }
    }

    private void removeEdge(DirectedGraph graph){
        Scanner input = new Scanner(System.in);
        try{
            System.out.println("Give the label of the out vertex:");
            int out = input.nextInt();
            System.out.println("Give the label of the in vertex:");
            int in = input.nextInt();
            graph.removeEdge(out, in);
        } catch (InputMismatchException e) {
            System.out.println("Wrong input");
        } catch (CustomException e) {
            System.out.println(e.getMsg());
        }
    }

    private void addCost(DirectedGraph graph){
        Scanner input = new Scanner(System.in);
        try{
            System.out.println("Give the label of the out vertex:");
            int out = input.nextInt();
            System.out.println("Give the label of the in vertex:");
            int in = input.nextInt();
            System.out.println("Give the cost of this edge");
            int cost = input.nextInt();
            graph.addCostOfEdge(out, in, cost);
        } catch (InputMismatchException e) {
            System.out.println("Wrong input");
        } catch (CustomException e) {
            System.out.println(e.getMsg());
        }
    }

    private void updateCost(DirectedGraph graph){
        Scanner input = new Scanner(System.in);
        try{
            System.out.println("Give the label of the out vertex:");
            int out = input.nextInt();
            System.out.println("Give the label of the in vertex:");
            int in = input.nextInt();
            System.out.println("Give the cost of this edge");
            int cost = input.nextInt();
            graph.updateCostOfEdge(out, in, cost);
        } catch (InputMismatchException e) {
            System.out.println("Wrong input");
        } catch (CustomException e) {
            System.out.println(e.getMsg());
        }
    }

    private void isEdge(DirectedGraph graph){
        Scanner input = new Scanner(System.in);
        try{
            System.out.println("Give the label of the out vertex:");
            int out = input.nextInt();
            System.out.println("Give the label of the in vertex:");
            int in = input.nextInt();
            Vertex<Integer> outV = new Vertex<>(out);
            Vertex<Integer> inV = new Vertex<>(in);
            if (graph.isEdge(outV, inV) == true){
                System.out.println("There is edge between this vertices");
            } else System.out.println("No such edge");
        } catch (InputMismatchException e) {
            System.out.println("Wrong input");
        } catch (CustomException e) {
            System.out.println(e.getMsg());
        }
    }

    private void getCost(DirectedGraph graph){
        Scanner input = new Scanner(System.in);
        try{
            System.out.println("Give the label of the out vertex:");
            int out = input.nextInt();
            System.out.println("Give the label of the in vertex:");
            int in = input.nextInt();
            if (graph.getCostOfEdge(out, in) != -1){
                System.out.println("Cost of this edge if: " + graph.getCostOfEdge(out, in));
            } else System.out.println("No such edge");
        } catch (InputMismatchException e) {
            System.out.println("Wrong input");
        } catch (CustomException e) {
            System.out.println(e.getMsg());
        }
    }

    private void getInbound(DirectedGraph graph){
        Scanner input = new Scanner(System.in);
        System.out.println("Give the label of the vertex:");
        int v = input.nextInt();
        Iterator iter = graph.getInboundIterator(v);
        while (iter.hasNext()){
            Vertex<Integer> vert = (Vertex<Integer>) iter.next();
            System.out.print(vert.getLabel() + " , ");
        }
        System.out.println();

    }

    private void getOutbound(DirectedGraph graph){
        Scanner input = new Scanner(System.in);
        System.out.println("Give the label of the vertex:");
        int v = input.nextInt();
        Iterator iter = graph.getOutboundIterator(v);
        while (iter.hasNext()){
            Vertex<Integer> vert = (Vertex<Integer>) iter.next();
            System.out.print(vert.getLabel() + " , ");
        }
        System.out.println();
    }

    public void run() throws IOException, CustomException {
        DirectedGraph graph = new DirectedGraph();
        int option;
        boolean flag = true;
        while(flag) {
            try {
                menu();
                Scanner input = new Scanner(System.in);
                option = input.nextInt();
                switch (option) {
                    case 0:
                        flag = false;
                        break;
                    case 1:
                        System.out.println(graph.getNrVertices());
                        break;
                    case 2:
                        inDegree(graph);
                        break;
                    case 3:
                        outDegree(graph);
                        break;
                    case 4:
                        addVertex(graph);
                        break;
                    case 5:
                        removeVertex(graph);
                        break;
                    case 6:
                        addEdge(graph);
                        break;
                    case 7:
                        removeEdge(graph);
                        break;
                    case 8:
                        addCost(graph);
                        break;
                    case 9:
                        updateCost(graph);
                        break;
                    case 10:
                        getCost(graph);
                        break;
                    case 11:
                        isEdge(graph);
                        break;
                    case 12:
                        getInbound(graph);
                        break;
                    case 13:
                        getOutbound(graph);
                        break;
                    case 14:
                        graph.writeToFile();
                        break;
                    default:
                        System.out.println("No such option");
                        break;
                }
            } catch (InputMismatchException e){
                System.out.println("Wrong input");
            }
        }
    }

}
