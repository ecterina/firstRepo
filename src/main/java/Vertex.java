public class Vertex<E> {

    private E label;
    private boolean visited;

    public Vertex(E label){
        this.label = label;
    }

    public E getLabel(){
        return label;
    }

    public boolean isVisited(){
        return visited;
    }

    public void setLabel(E label){
        this.label = label;
    }

    public void setVisited(boolean visited){
        this.visited = visited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex<?> vertex = (Vertex<?>) o;

        return label.equals(vertex.label);

    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "label=" + label +
                '}';
    }
}
