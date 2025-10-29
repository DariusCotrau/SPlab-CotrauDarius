public interface Element {
    void print();

    // Used by Solution 1 (clone on add)
    Element copy();

    default void add(Element element) {
        throw new UnsupportedOperationException("add not supported");
    }

    default void remove(Element element) {
        throw new UnsupportedOperationException("remove not supported");
    }

    default Element get(int index) {
        throw new UnsupportedOperationException("get not supported");
    }
}
