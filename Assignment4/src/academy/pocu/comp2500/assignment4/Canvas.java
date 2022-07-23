package academy.pocu.comp2500.assignment4;

import java.util.ArrayList;

public class Canvas {
    protected final int width;
    protected final int height;
    protected final ArrayList<ArrayList<Character>> canvas;

    public Canvas(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.canvas = new ArrayList<>(height);

        for (int i = 0; i < height; ++i) {
            this.canvas.add(new ArrayList<>(width));

            for (int j = 0; j < width; ++j) {
                this.canvas.get(i).add(' ');
            }
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void drawPixel(final int x, final int y, final char character) {
        if (!checkRange(x, y)) {
            return;
        }

        this.canvas.get(y).set(x, character);
    }

    public char getPixel(final int x, final int y) {
        return this.canvas.get(y).get(x);
    }

    public boolean increasePixel(final int x, final int y) {
        if (!checkRange(x, y)) {
            return false;
        }

        char origin = this.canvas.get(y).get(x);

        if ((int) origin == '~') {
            return false;
        }

        this.canvas.get(y).set(x, ++origin);

        return true;
    }

    public boolean decreasePixel(final int x, final int y) {
        if (!checkRange(x, y)) {
            return false;
        }

        char origin = this.canvas.get(y).get(x);

        if ((int) origin == ' ') {
            return false;
        }

        this.canvas.get(y).set(x, --origin);

        return true;
    }

    public void toUpper(final int x, final int y) {
        if (!checkRange(x, y)) {
            return;
        }

        char origin = this.canvas.get(y).get(x);

        if (origin < 'a' || origin > 'z') {
            return;
        }

        char bitmask = 32;
        this.canvas.get(y).set(x, (char) (origin ^ bitmask));
    }

    public void toLower(final int x, final int y) {
        if (!checkRange(x, y)) {
            return;
        }

        char origin = this.canvas.get(y).get(x);

        if (origin < 'A' || origin > 'Z') {
            return;
        }

        char bitmask = 32;
        this.canvas.get(y).set(x, (char) (origin ^ bitmask));
    }

    public void fillHorizontalLine(final int y, final char character) {
        if (y < 0 || y >= this.height) {
            return;
        }

        for (int i = 0; i < this.width; ++i) {
            this.canvas.get(y).set(i, character);
        }
    }

    public void fillVerticalLine(final int x, final char character) {
        if (x < 0 || x >= this.width) {
            return;
        }

        for (int i = 0; i < this.height; ++i) {
            this.canvas.get(i).set(x, character);
        }
    }

    public void clear() {
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                this.canvas.get(i).set(j, ' ');
            }
        }
    }

    public String getDrawing() {
        StringBuilder sb = new StringBuilder();

        sb.append('+');
        for (int i = 0; i < this.width; ++i) {
            sb.append('-');
        }
        sb.append('+');
        sb.append(System.lineSeparator());


        for (int i = 0; i < this.height; ++i) {
            sb.append('|');

            for (int j = 0; j < this.width; ++j) {
                sb.append(this.canvas.get(i).get(j));
            }

            sb.append('|');
            sb.append(System.lineSeparator());
        }

        sb.append('+');
        for (int i = 0; i < this.width; ++i) {
            sb.append('-');
        }
        sb.append('+');
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    protected boolean checkRange(final int x, final int y) {
        return (x >= 0 && x < this.width && y >= 0 && y < this.height);
    }
}
