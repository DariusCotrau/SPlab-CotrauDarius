import java.util.ArrayList;
import java.util.List;

public class Book {
    String title;
    private final List<Author> authors = new ArrayList<>();
    private final List<Element> content = new ArrayList<>();
    private final TableOfContents tableOfContents = new TableOfContents(); // asociere

    public Book(String title) {
        this.title = title;
    }

    public Author addAuthor(String name) {
        Author a = new Author(name);
        authors.add(a);
        return a;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public Section addSection(String name) {
        Section s = new Section(name);
        content.add(s);
        tableOfContents.addEntry(name);
        return s;
    }

    public void addContent(Element el) {
        content.add(el);
    }

    public Element getContent(int index) {
        return content.get(index);
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Element> getContent() {
        return content;
    }

    public TableOfContents getTableOfContents() {
        return tableOfContents;
    }

    public void print() {
        System.out.println("Book: " + title);
        if (!authors.isEmpty()) {
            System.out.println("Authors:");
            for (Author a : authors) {
                a.print();
            }
        }
        for (Element e : content) {
            e.print();
        }
    }
}

