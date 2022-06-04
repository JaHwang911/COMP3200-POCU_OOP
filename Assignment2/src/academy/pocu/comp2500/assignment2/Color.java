package academy.pocu.comp2500.assignment2;

public class Color {
    private final int red;
    private final int green;
    private final int blue;

    public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public String getRGB() {
        return String.format("RGB(%x,%x,%x)", this.red, this.green, this.blue);
    }

    public int getColor() {
        int color = this.red;

        color <<= 8;
        color += this.green;

        color <<= 8;
        color += this.blue;

        return color;
    }
}
