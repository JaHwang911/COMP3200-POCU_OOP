package academy.pocu.comp2500.assignment2;

public class Aperture {
    private final int posX;
    private final int posY;
    private final int width;
    private final int height;

    public Aperture(int posX, int posY, int width, int height) {
        this.posX = posX;
        this.posY = posY;
        this.width = Math.max(1, width);
        this.height = Math.max(1, height);
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
