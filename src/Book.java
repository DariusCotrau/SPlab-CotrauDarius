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

    // Helper for backwards compatibility with earlier labs
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
        // Book should not print its own title as a plain section title here
        // Title and authors are handled in print() below.
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
        // Delegate traversal to Section.print(), but with the book's own
        // title suppressed via overridden printThisBefore().
        super.print();
    }
}
