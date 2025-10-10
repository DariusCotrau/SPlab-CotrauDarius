public interface Element {
    void print();

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
