//import java.util.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class UndirectedGraph {

    //    private List<Vertex> vertexList
    private int[][] adjMatrix;
    private int nrVr;
    private static final String FILE =  "src/main/resources/graphUNDIRECTED.txt";

    public int getNrVertices(){
        return nrVr;
    }

    public int[][] getAdjMatrix() {
        return adjMatrix;
    }

    public UndirectedGraph(int nrVr) {
        this.nrVr = nrVr;
        adjMatrix = new int[nrVr][nrVr];
    }

    public UndirectedGraph() throws IOException, CustomException {
        readFromFile();
    }

    public void addVertex(int vLabel) throws CustomException {
        if (vLabel > adjMatrix.length) {
            resizeMatrix();
            nrVr++;
        } else if (vLabel == nrVr + 1) {
            nrVr++;
        } else {
            throw new CustomException("Invalid vertex index");
        }
    }

    private void resizeMatrix() {
        int[][] temp = new int[adjMatrix.length * 2][adjMatrix.length * 2];
        for (int i = 0; i < adjMatrix.length; i++) {
            System.arraycopy(adjMatrix[i], 0, temp[i], 0, adjMatrix.length);
        }
    }

    public void addEdge(int l1, int l2) throws CustomException {
        if (l1 <= nrVr && l2 <= nrVr) {
            adjMatrix[l1][l2] = 1;
            adjMatrix[l2][l1] = 1;
        }else throw new CustomException("Invalid vertex index");
    }

    public void removeEdge(int l1, int l2) throws CustomException {
        if (l1 <= nrVr && l2 <= nrVr) {
            adjMatrix[l1][l2] = 0;
            adjMatrix[l2][l1] = 0;
        }else throw new CustomException("Invalid vertex index");
    }

    private void readFromFile() throws IOException, CustomException {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(FILE)))){
            String strLine = br.readLine();
            String[] array = strLine.split(" ");
            nrVr = Integer.parseInt(array[0]);
            adjMatrix = new int[nrVr][nrVr];
            while ((strLine = br.readLine()) != null){
                array = strLine.split(" ");
                int v1 = Integer.parseInt(array[0]);
                int v2 = Integer.parseInt(array[1]);
                addEdge(v1, v2);
            }
        } catch (FileNotFoundException e){
            System.err.println("file not found");
        } catch (IOException e){
            System.err.println("Error while reading file");
        }
    }

    public boolean isConnected(int l1, int l2) {
        int value = adjMatrix[l1][l2];
        return value != 0;
    }

    public void result (){
        List<Integer> visited  = new ArrayList<>();
        int i = 0;
        List<Integer> r;
        while (visited.size() != nrVr){
            if (!visited.contains(i)){
                r = dfsComponents(i);
                System.out.println(r);
                for (int j = 0; j < r.size(); j++) {
                    if (!visited.contains(r.get(j))){
                        visited.add(r.get(j));
                    }
                }
            }
            i++;
        }
    }

    public List<Integer> dfsComponents(int label){
        Stack<Integer> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        result.add(label);

        for (int i = 0; i < nrVr; i++) {
            if (adjMatrix[label][i] == 1) {
                stack.push(i);
            }
        }

        while (!stack.empty()) {
            Integer elem = stack.pop();
            if (!result.contains(elem)){
                result.add(elem);

            }else{
                continue;
            }
            for (int i = 0; i < nrVr; i++) {
                if (adjMatrix[elem][i] == 1) {
                    if (i == label) {
                        continue;
                    }
                    stack.push(i);
                }
            }
        }
        return result;
    }
}

// afiseaza edges , use accessible from py, afiseaza doar cite o conected components, nu pentru fiecare vertex
// have a check visited for vertex in case it was used
// problem 6