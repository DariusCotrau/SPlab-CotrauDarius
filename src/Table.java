public class Table implements Element {
    String title;

    public Table(String title) {
        this.title = title;
    }

    @Override
    public void print() {
        System.out.println("Table: " + title);
    }

    @Override
    public Element copy() {
        return new Table(this.title);
    }
}
