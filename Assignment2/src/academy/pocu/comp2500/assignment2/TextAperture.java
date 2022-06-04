package academy.pocu.comp2500.assignment2;

public class TextAperture extends Aperture {
    private final String text;

    public TextAperture(int x, int y, int width, int height, String text) {
        super(x, y, width, height);

        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
