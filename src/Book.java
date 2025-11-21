import java.util.ArrayList;
import java.util.List;

public class Book extends Section {
    private final List<Author> authors = new ArrayList<>();
    private final TableOfContents tableOfContents = new TableOfContents();

    public Book(String title) {
        super(title);
    }

    public Author addAuthor(String name) {
        Author a = new Author(name);
        authors.add(a);
        return a;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void addContent(Element el) {
        super.add(el);
    }

    public Section addSection(String name) {
        Section s = new Section(name);
        super.add(s);
        tableOfContents.addEntry(name);
        return s;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public TableOfContents getTableOfContents() {
        return tableOfContents;
    }

    @Override
    protected void printThisBefore() {
    }

    @Override
    public void print() {
        System.out.println("Book: " + getTitle());
        System.out.println();
        if (!authors.isEmpty()) {
            System.out.println("Authors:");
            for (Author a : authors) {
                a.print();
            }
            System.out.println();
        }
        super.print();
    }
}
