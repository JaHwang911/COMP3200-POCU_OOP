package academy.pocu.comp2500.assignment2;

public class ImageAperture extends Aperture {
    private final String path;

    public ImageAperture(int width, int height, String path) {
        super(width, height);

        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
