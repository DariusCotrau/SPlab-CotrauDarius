public class Main {
    public static void main(String[] args) {
        Book book = new Book("Design Patterns in Java");
        book.addAuthor("Gamma");
        Chapter ch1 = book.addChapter("Chapter 1");
        SubChapter sc1 = ch1.addSubChapter("Intro");
        sc1.addParagraph("First paragraph");
        sc1.addImage("diagram.png");
        sc1.addTable("Table 1.1");

        // Demonstrăm print-ul moștenit
        book.print();
        ch1.print();
        sc1.print();
        for (TableOfContents el : sc1.getElements()) {
            el.print();
        }
        // și accesul la asocierea Book-TOC
        book.getTableOfContents().print();
    }
}

