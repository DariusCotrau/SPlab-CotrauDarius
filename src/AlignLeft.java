public class AlignLeft implements AlignStrategy {
    @Override
    public void render(Paragraph paragraph, RenderContext context) {
        System.out.println("Paragraph: " + paragraph.getText());
    }
}

