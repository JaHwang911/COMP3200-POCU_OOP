package academy.pocu.comp2500.assignment2;

public class Aperture {
    private final ApertureType type;
    private final int posX;
    private final int posY;
    private final int width;
    private final int height;

    protected Aperture(ApertureType type, int x, int y, int width, int height) {
        this.type = type;
        this.posX = x;
        this.posY = y;
        this.width = Math.max(1, width);
        this.height = Math.max(1, height);
    }

    public ApertureType getType() {
        return this.type;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
