public class Paragraph implements Element {
    String text;
    private AlignStrategy alignStrategy;

    public Paragraph(String text) {
        this.text = text;
    }

    @Override
    public void print() {
        if (alignStrategy == null) {
            System.out.println("Paragraph: " + text);
        } else {
            alignStrategy.render(this, RenderContext.defaultContext());
        }
    }

    public void setAlignStrategy(AlignStrategy alignStrategy) {
        this.alignStrategy = alignStrategy;
    }

    public AlignStrategy getAlignStrategy() {
        return alignStrategy;
    }

    public String getText() {
        return text;
    }

    @Override
    public Element copy() {
        Paragraph p = new Paragraph(this.text);
        p.setAlignStrategy(this.alignStrategy);
        return p;
    }
}
