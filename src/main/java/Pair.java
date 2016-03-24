public class Pair<E, T> {

    private E el1;
    private T el2;

    public Pair(E el1, T el2) {
        this.el1 = el1;
        this.el2 = el2;
    }

    public E getEl1() {
        return el1;
    }

    public void setEl1(E el1) {
        this.el1 = el1;
    }

    public T getEl2() {
        return el2;
    }

    public void setEl2(T el2) {
        this.el2 = el2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (!el1.equals(pair.el1)) return false;
        return el2.equals(pair.el2);

    }

    @Override
    public int hashCode() {
        int result = el1.hashCode();
        result = 31 * result + el2.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "el1=" + el1 +
                ", el2=" + el2 +
                '}';
    }
}
