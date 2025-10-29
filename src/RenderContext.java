public class RenderContext {
    private final int width;

    private static final RenderContext DEFAULT = new RenderContext(50);

    public RenderContext(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public static RenderContext defaultContext() {
        return DEFAULT;
    }
}

