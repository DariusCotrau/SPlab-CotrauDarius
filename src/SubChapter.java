import java.util.ArrayList;
import java.util.List;

public class SubChapter extends TableOfContents {
    String name;
    private final List<TableOfContents> elements = new ArrayList<>();

    public SubChapter(String name) {
        this.name = name;
    }

    public Paragraph addParagraph(String text) {
        Paragraph p = new Paragraph(text);
        elements.add(p);
        return p;
    }

    public Image addImage(String imageName) {
        Image img = new Image(imageName);
        elements.add(img);
        return img;
    }

    public Table addTable(String title) {
        Table t = new Table(title);
        elements.add(t);
        return t;
    }

    public List<TableOfContents> getElements() {
        return elements;
    }
}

