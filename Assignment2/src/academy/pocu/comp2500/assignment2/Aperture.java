package academy.pocu.comp2500.assignment2;

public class Aperture {
    private final int width;
    private final int height;

    public Aperture(int width, int height) {
        this.width = Math.max(1, width);
        this.height = Math.max(1, height);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
