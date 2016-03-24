//import java.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class UndirectedGraph {

    //    private List<Vertex> vertexList
    private int[][] adjMatrix;
    private int nrVr;

    public UndirectedGraph(int nrVr) {
        this.nrVr = nrVr;
        adjMatrix = new int[nrVr * 2][nrVr * 2];
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

    public boolean isConnected(int l1, int l2) {
        int value = adjMatrix[l1][l2];
        return value != 0;
    }

    public List<Integer> dfsComponents(int label){
        Stack<Integer> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();

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
