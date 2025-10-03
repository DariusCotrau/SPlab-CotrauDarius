import java.util.ArrayList;
import java.util.List;

public class Chapter extends TableOfContents {
    String name;
    private final List<SubChapter> subChapters = new ArrayList<>();

    public Chapter(String name) {
        this.name = name;
    }

    public SubChapter addSubChapter(String name) {
        SubChapter sc = new SubChapter(name);
        subChapters.add(sc);
        return sc;
    }

    public List<SubChapter> getSubChapters() {
        return subChapters;
    }
}

