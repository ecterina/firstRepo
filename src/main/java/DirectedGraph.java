import java.io.*;
import java.util.*;
import java.util.List;

public class DirectedGraph {

    private static final String FILE =  "src/main/resources/graph.txt";
    private Map<Vertex<Integer>, List<Vertex<Integer>>> inboundNeighbours = new HashMap<>();
    private Map<Vertex<Integer>, List<Vertex<Integer>>> outboundNeighbours = new HashMap<>();
    private List<Vertex<Integer>> vertexList = new ArrayList<>();
    private Map<Pair<Vertex<Integer>,Vertex<Integer>>, Integer > costOfEdge = new HashMap<>();

    public DirectedGraph(int nrVertices) throws CustomException {
        if (nrVertices < 0) throw new CustomException("Number of vertices in a graph must be positive");
        initListOfVertices(nrVertices);
        initInboundNeighbours();
        initOutboundNeighbours();
    }

    public DirectedGraph() throws IOException, CustomException {
        readFromFile();
    }

    public void initListOfVertices(int nrVertices){
        vertexList = new ArrayList<>(nrVertices);
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

    public void addVertex(int label) throws CustomException {
        Vertex<Integer> v = new Vertex<>(label);
        if (vertexList.contains(v)){
            throw new CustomException("Such Vertex already exists");
        }
        vertexList.add(v);
        inboundNeighbours.put(v, new ArrayList<>());
        outboundNeighbours.put(v, new ArrayList<>());
    }

    public void addEdge(Vertex<Integer> out, Vertex<Integer> in) throws CustomException {
        if (!(vertexList.contains(out) || vertexList.contains(in))){
            throw new CustomException("No such vertices in graph");
        }
        if (isEdge(out, in)){
            throw new CustomException("Such edge already exists");
        }
        outboundNeighbours.get(out).add(in);
        inboundNeighbours.get(in).add(out);
    }

    public boolean isEdge(Vertex<Integer> out, Vertex<Integer> in){
        return inboundNeighbours.get(out).contains(in) && outboundNeighbours.get(in).contains(out);
    }

    public void addCostOfEdge(Vertex<Integer> out, Vertex<Integer> in, int cost) throws CustomException {
        Pair<Vertex<Integer>, Vertex<Integer>> edge = new Pair<>(out, in);
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
                Vertex<Integer> v1 = new Vertex<>(Integer.parseInt(array[0]));
                Vertex<Integer> v2 = new Vertex<>(Integer.parseInt(array[1]));
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


}
