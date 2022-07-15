package academy.pocu.comp2500.assignment4;

import java.util.ArrayList;

public class Canvas {
    private final int width;
    private final int height;
    ArrayList<ArrayList<Character>> canvas;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;

        this.canvas = new ArrayList<>(height + 2);

        for (int i = 0; i < height + 2; ++i) {
            this.canvas.add(new ArrayList<>(width + 2));

            if (i == 0 || i == height + 1) {
                this.canvas.get(i).add('+');

                for (int j = 1; j < width + 1; ++j) {
                    this.canvas.get(i).add('-');
                }

                this.canvas.get(i).add('+');
                continue;
            }

            this.canvas.get(i).add('|');

            for (int j = 1; j < width + 1; ++j) {
                this.canvas.get(i).add(' ');
            }

            this.canvas.get(i).add('|');
        }
    }

    public void clear() {
        for (int i = 1; i < this.height + 1; ++i) {
            for (int j = 1; j < this.width + 1; ++j) {
                this.canvas.get(i).set(j, ' ');
            }
        }
    }

    public String getDrawing() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.height + 2; ++i) {
            for (int j = 0; j < this.width + 2; ++j) {
                sb.append(this.canvas.get(i).get(j));
            }

            sb.append(System.lineSeparator());
        }

        sb.append(System.lineSeparator());

        return sb.toString();
    }
}
