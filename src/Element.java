public interface Element {
    void print();

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
