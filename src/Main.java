public class Main {
    public static void main(String[] args) {

        Book noapteBuna = new Book("Noapte buna, copii!");
        Author rpGheo = new Author("Radu Pavel Gheo");
        noapteBuna.addAuthor(rpGheo);

        noapteBuna.addContent(new Paragraph("Multumesc celor care ..."));

        // Build the structure using the composite-friendly helpers to avoid sharing
        Section cap1 = noapteBuna.addSection("Capitolul 1");
        cap1.addParagraph("Moto capitol");

        Section cap11 = cap1.addSection("Capitolul 1.1");
        cap11.addParagraph("Text from subchapter 1.1");

        Section cap111 = cap11.addSection("Capitolul 1.1.1");
        cap111.addParagraph("Text from subchapter 1.1.1");

        Section cap1111 = cap111.addSection("Subchapter 1.1.1.1");
        cap1111.addImage("Image subchapter 1.1.1.1");

        noapteBuna.print();
    }
}
