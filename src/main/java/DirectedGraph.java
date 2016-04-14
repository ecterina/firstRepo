import org.omg.PortableInterceptor.INACTIVE;

import java.io.*;
import java.util.*;
import java.util.List;

public class DirectedGraph {

    private static final String FILE =  "src/main/resources/graph.txt";
    private static final String NEWFILE =  "src/main/resources/newGraph.txt";
    private Map<Vertex<Integer>, List<Vertex<Integer>>> inboundNeighbours = new HashMap<>();
    private Map<Vertex<Integer>, List<Vertex<Integer>>> outboundNeighbours = new HashMap<>();
    private List<Vertex<Integer>> vertexList = new ArrayList<>();
    private Map<Pair<Vertex<Integer>,Vertex<Integer>>, Integer > costOfEdge = new HashMap<>();


    //Constructor of a directed graph
    //Input: nrVertices - an integer, number of vertices the graph is initialized with
    public DirectedGraph(int nrVertices) throws CustomException {
        if (nrVertices < 0) throw new CustomException("Number of vertices in a graph must be positive");
        initListOfVertices(nrVertices);
        initInboundNeighbours();
        initOutboundNeighbours();
    }

    //Constructor of a directed graph for the case of data given from file
    public DirectedGraph() throws IOException, CustomException {
        readFromFile();
    }

    public void initListOfVertices(int nrVertices){
        for (int i = 0; i < nrVertices; i++) {
             vertexList.add(new Vertex<Integer>(i));

        }
    }

    public void initInboundNeighbours(){
        inboundNeighbours = new HashMap<>();
        for (Vertex<Integer> aVertexList : vertexList) {
            inboundNeighbours.put(aVertexList, new ArrayList<>());
        }
    }

    public void initOutboundNeighbours(){
        outboundNeighbours = new HashMap<>();
        for (Vertex<Integer> aVertexList : vertexList) {
            outboundNeighbours.put(aVertexList, new ArrayList<>());
        }

    }

    public int getNrVertices(){
        return vertexList.size();
    }

    public int getInDegree(int label) throws CustomException {
        Vertex<Integer> vertex = getVertex(label);
        if (vertexList.contains(vertex)){
            return inboundNeighbours.get(vertex).size();
        }else throw new CustomException("No such vertex");
    }

    public int getOutDegree(int label) throws CustomException {
        Vertex<Integer> vertex = getVertex(label);
        if (vertexList.contains(vertex)){
            return outboundNeighbours.get(vertex).size();
        }else throw new CustomException("No such vertex");
    }

    public Iterator getInboundIterator(int label){
        Vertex<Integer> vertex = getVertex(label);
        return inboundNeighbours.get(vertex).iterator();
    }

    public Iterator getOutboundIterator(int label){
        Vertex<Integer> vertex = getVertex(label);
        return outboundNeighbours.get(vertex).iterator();
    }

    public void addVertex(int label) throws CustomException {
        Vertex<Integer> v = new Vertex<>(label);
        if (vertexList.contains(v)){
            throw new CustomException("Such Vertex already exists");
        }
        vertexList.add(v);
        inboundNeighbours.put(v, new ArrayList<>());
        outboundNeighbours.put(v, new ArrayList<>());
    }

    public void removeVertex(int label) throws CustomException {
        Vertex<Integer> vertex = getVertex(label);
        if (!vertexList.contains(vertex)) throw new CustomException("No such vertex");

        vertexList.remove(vertex);

        for (Vertex<Integer> inVertex : inboundNeighbours.get(vertex)) {
            outboundNeighbours.get(inVertex).remove(vertex);
        }

        for (Vertex<Integer> outVertex : inboundNeighbours.get(vertex)) {
            inboundNeighbours.get(outVertex).remove(vertex);
        }

        inboundNeighbours.remove(vertex);
        outboundNeighbours.remove(vertex);

        for(Iterator<Map.Entry<Pair<Vertex<Integer>, Vertex<Integer>>, Integer>> it = costOfEdge.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Pair<Vertex<Integer>, Vertex<Integer>>, Integer> entry = it.next();
            if(entry.getKey().getEl1().getLabel() == label || entry.getKey().getEl2().getLabel() == label) {
                it.remove();
            }
        }
    }

    public Vertex<Integer> getVertex(int label){
        for (Vertex<Integer> aVertexList : vertexList) {
            if (aVertexList.getLabel() == label) {
                return aVertexList;
            }
        }
        return null;
    }

    public void removeEdge(int out, int in) throws CustomException {
        Vertex<Integer> outVertex = getVertex(out);
        Vertex<Integer> inVertex = getVertex(in);
        if (isEdge(outVertex, inVertex)){
            inboundNeighbours.get(inVertex).remove(outVertex);
            outboundNeighbours.get(outVertex).remove(inVertex);
            Pair<Vertex<Integer>,Vertex<Integer>> edge = new Pair<>(outVertex, inVertex);
            costOfEdge.remove(edge);
        } else
            throw new CustomException("No such edge");
    }

    public void addEdge(int out, int in) throws CustomException {
        Vertex<Integer> outVertex = new Vertex<>(out);
        Vertex<Integer> inVertex = new Vertex<>(in);
        if ((! vertexList.contains(outVertex) || ! vertexList.contains(inVertex))){
            throw new CustomException("No such vertices in graph");
        }
        if (isEdge(outVertex, inVertex)){
            throw new CustomException("Such edge already exists");
        }
        outboundNeighbours.get(outVertex).add(inVertex);
        inboundNeighbours.get(inVertex).add(outVertex);
    }

    public void updateCostOfEdge(int out, int in, int cost) throws CustomException {
        Vertex<Integer> v1 = getVertex(out);
        Vertex<Integer> v2 = getVertex(in);
        Pair<Vertex<Integer>, Vertex<Integer>> edge = new Pair<>(v1, v2);
        if (isEdge(v1, v2)) {
            costOfEdge.put(edge, cost);
        } else throw new CustomException("No such edge");
    }

    public int getCostOfEdge(int out, int in) throws CustomException {
        Vertex<Integer> v1 = getVertex(out);
        Vertex<Integer> v2 = getVertex(in);
        Pair<Vertex<Integer>, Vertex<Integer>> edge = new Pair<>(v1, v2);
        if (isEdge(v1, v2)) {
            return costOfEdge.get(edge);
        }
        return -1;
    }

    public boolean isEdge(Vertex<Integer> out, Vertex<Integer> in) throws CustomException {
        if (! vertexList.contains(out) || ! vertexList.contains(in))
        {
            throw new CustomException("no such verteces");
        }
        if (inboundNeighbours.get(in).contains(out)) if (outboundNeighbours.get(out).contains(in)) return true;
        return false;
    }

    public void addCostOfEdge(int out, int in, int cost) throws CustomException {
        Vertex<Integer> v1 = getVertex(out);
        Vertex<Integer> v2 = getVertex(in);
        Pair<Vertex<Integer>, Vertex<Integer>> edge = new Pair<>(v1, v2);
        if (costOfEdge.containsKey(edge)){
            throw new CustomException("Cost to this edge is already added");
        }
        costOfEdge.put(edge, cost);
    }

    private void readFromFile() throws IOException, CustomException {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(FILE)))){
            String strLine = br.readLine();
            String[] array = strLine.split(" ");
            int nrVertices = Integer.parseInt(array[0]);
            initListOfVertices(nrVertices);
            initInboundNeighbours();
            initOutboundNeighbours();
            while ((strLine = br.readLine()) != null){
                array = strLine.split(" ");
                int v1 = Integer.parseInt(array[0]);
                int v2 = Integer.parseInt(array[1]);
                int cost = Integer.parseInt(array[2]);
                addEdge(v1, v2);
                addCostOfEdge(v1, v2, cost);
            }
        } catch (FileNotFoundException e){
            System.err.println("file not found");
        } catch (IOException e){
            System.err.println("Error while reading file");
        }
    }

    public void writeToFile(){
        try (BufferedWriter br = new BufferedWriter(new FileWriter(new File(NEWFILE)))){
            br.write(String.valueOf(vertexList.size()) + " ");
            int nrEdges = costOfEdge.size();
            br.write(String.valueOf(nrEdges) + " ");
            br.newLine();
            for (Pair<Vertex<Integer>, Vertex<Integer>> vertexPair : costOfEdge.keySet()) {
                br.write(String.valueOf(vertexPair.getEl1().getLabel()) + " ");
                br.write(String.valueOf(vertexPair.getEl2().getLabel()) + " ");
                br.write(String.valueOf(costOfEdge.get(vertexPair)) + " ");
                br.newLine();
            }
            br.close();
            System.out.println("Done");
        } catch (IOException e) {
            System.err.println("Error while writing to file");
        }
    }
}
