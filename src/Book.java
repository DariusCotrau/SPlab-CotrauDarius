import java.util.ArrayList;
import java.util.List;

public class Book extends TableOfContents {
    String title;
    private final List<Author> authors = new ArrayList<>();
    private final List<Chapter> chapters = new ArrayList<>();
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

    public Chapter addChapter(String name) {
        Chapter c = new Chapter(name);
        chapters.add(c);
        // exemplu: adăugăm capitolul și în TOC-ul asociat
        tableOfContents.addEntry(name);
        return c;
    }

    public Chapter getChapter(int index) {
        return chapters.get(index);
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public TableOfContents getTableOfContents() {
        return tableOfContents;
    }
}

