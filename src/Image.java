public class Image implements Element {
    String imageName;

    public Image(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public void print() {
        System.out.println("Image with name: " + imageName);
    }

    @Override
    public Element copy() {
        return new Image(this.imageName);
    }
}
