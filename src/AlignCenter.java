public class AlignCenter implements AlignStrategy {
    @Override
    public void render(Paragraph paragraph, RenderContext context) {
        String text = paragraph.getText();
        int width = context.getWidth();
        int padding = Math.max(0, (width - text.length()) / 2);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < padding; i++) sb.append(' ');
        sb.append(text);
        System.out.println("Paragraph: " + sb);
    }
}

