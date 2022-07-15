package academy.pocu.comp2500.assignment4;

import java.util.ArrayList;

public final class Canvas {
    private final int width;
    private final int height;
    private final ArrayList<ArrayList<Character>> canvas;

    public Canvas(int width, int height) {
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

    public void drawPixel(int x, int y, char character) {
        if (!checkRange(x, y)) {
            return;
        }

        this.canvas.get(y).set(x, character);
    }

    public char getPixel(int x, int y) {
        return this.canvas.get(y).get(x);
    }

    public boolean increasePixel(int x, int y) {
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

    public boolean decreasePixel(int x, int y) {
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

    public void toUpper(int x, int y) {
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

    public void toLower(int x, int y) {
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

    public void fillHorizontalLine(int y, char character) {
        if (y < 0 || y >= this.height) {
            return;
        }

        for (int i = 0; i < this.width; ++i) {
            this.canvas.get(y).set(i, character);
        }
    }

    public void fillVerticalLine(int x, char character) {
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

    private boolean checkRange(int x, int y) {
        return (x >= 0 && x < this.width && y >= 0 && y < this.height);
    }
}
