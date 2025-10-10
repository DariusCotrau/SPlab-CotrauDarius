public class Image implements Element {
    String imageName;

    public Image(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public void print() {
        System.out.println("Image with name:" + imageName);
    }
}

