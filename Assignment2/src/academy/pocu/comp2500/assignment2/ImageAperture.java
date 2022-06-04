package academy.pocu.comp2500.assignment2;

public class ImageAperture extends Aperture {
    private final String imagePath;

    public ImageAperture(int posX, int posY, int width, int height, String imagePath) {
        super(posX, posY, width, height);

        this.imagePath = imagePath;
    }

    public String getPath() {
        return this.imagePath;
    }
}
