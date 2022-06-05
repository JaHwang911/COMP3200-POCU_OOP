package academy.pocu.comp2500.assignment2;

public class ImageAperture extends Aperture {
    private final String imagePath;

    public ImageAperture(int x, int y, int width, int height, String imagePath) {
        super(ApertureType.IMAGE, x, y, width, height);

        this.imagePath = imagePath;
    }

    public String getPath() {
        return this.imagePath;
    }
}
