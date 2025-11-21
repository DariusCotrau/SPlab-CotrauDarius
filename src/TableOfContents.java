import java.util.ArrayList;
import java.util.List;

public class TableOfContents {
    public static class Entry {
        public final String title;
        public final Integer page;

        public Entry(String title, Integer page) {
            this.title = title;
            this.page = page;
        }
    }

    protected final List<Entry> entries = new ArrayList<>();

    public void addEntry(String title) {
        addEntry(title, null);
    }

    public void addEntry(String title, Integer page) {
        entries.add(new Entry(title, page));
    }

    public void print() {
        System.out.println("TableOfContents");
        for (Entry e : entries) {
            if (e.page != null) {
                System.out.println(" - " + e.title + " .... " + e.page);
            } else {
                System.out.println(" - " + e.title);
            }
        }
    }
}
