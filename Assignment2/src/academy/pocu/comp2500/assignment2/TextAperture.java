package academy.pocu.comp2500.assignment2;

public class TextAperture extends Aperture {
    private final String text;

    public TextAperture(int posX, int posY, int width, int height, String text) {
        super(posX, posY, width, height);

        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
