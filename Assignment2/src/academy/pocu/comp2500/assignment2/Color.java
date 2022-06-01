package academy.pocu.comp2500.assignment2;

public class Color {
    private final int red;
    private final int blue;
    private final int green;

    public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
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
