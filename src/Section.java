import java.util.ArrayList;
import java.util.List;

public class Section implements Element {
    private final String title;
    private final List<Element> children = new ArrayList<>();

    public Section(String title) {
        this.title = title;
    }

    // Composite operations
    @Override
    public void add(Element element) {
        children.add(element);
    }

    @Override
    public void remove(Element element) {
        children.remove(element);
    }

    @Override
    public Element get(int index) {
        return children.get(index);
    }

    public List<Element> getElements() {
        return children;
    }

    // Convenience builders
    public Section addSection(String name) {
        Section s = new Section(name);
        this.add(s);
        return s;
    }

    public Paragraph addParagraph(String text) {
        Paragraph p = new Paragraph(text);
        this.add(p);
        return p;
    }

    public Image addImage(String imageName) {
        Image i = new Image(imageName);
        this.add(i);
        return i;
    }

    public Table addTable(String title) {
        Table t = new Table(title);
        this.add(t);
        return t;
    }

    @Override
    public void print() {
        System.out.println(title);
        for (Element e : children) {
            e.print();
        }
    }
}
