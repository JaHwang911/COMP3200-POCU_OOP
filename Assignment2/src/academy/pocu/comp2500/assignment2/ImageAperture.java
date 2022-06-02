package academy.pocu.comp2500.assignment2;

public class ImageAperture extends Aperture {
    private final String imagePath;

    public ImageAperture(int width, int height, String imagePath) {
        super(width, height);

        this.imagePath = imagePath;
    }

    public String getPath() {
        return this.imagePath;
    }
}
