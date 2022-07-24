package academy.pocu.comp2500.assignment4;

import java.util.ArrayList;
import java.util.LinkedList;

public final class OverdrawAnalyzer extends Canvas {
    private final ArrayList<ArrayList<LinkedList<Character>>> pixelHistorys;

    public OverdrawAnalyzer(final int width, final int height) {
        super(width, height);

        this.pixelHistorys = new ArrayList<>(height);

        for (int i = 0; i < height; ++i) {
            this.pixelHistorys.add(new ArrayList<>(width));

            for (int j = 0; j < width; ++j) {
                this.pixelHistorys.get(i).add(new LinkedList<>());
            }
        }
    }

    public LinkedList<Character> getPixelHistory(int x, int y) {
        return this.pixelHistorys.get(y).get(x);
    }

    public int getOverdrawCount(int x, int y) {
        return this.pixelHistorys.get(y).get(x).size() - 1;
    }

    public int getOverdrawCount() {
        int totalCount = 0;

        for (int i = 0; i < super.getHeight(); ++i) {
            for (int j = 0; j < super.getWidth(); ++j) {
                int overDrawCount = this.pixelHistorys.get(i).get(j).size() - 1;

                if (overDrawCount > 0) {
                    totalCount += overDrawCount;
                }
            }
        }

        return totalCount;
    }

    @Override
    public char getPixel(int x, int y) {
        if (this.pixelHistorys.get(y).get(x).size() == 0) {
            return ' ';
        }

        return this.pixelHistorys.get(y).get(x).getLast();
    }

    @Override
    public void drawPixel(final int x, final int y, final char character) {
        if (!checkRange(x, y)) {
            return;
        }

        LinkedList<Character> pixelHistory = this.pixelHistorys.get(y).get(x);

        if (pixelHistory.size() == 0) {
            pixelHistory.add(character);
            return;
        }

        if (pixelHistory.getLast() == character) {
            return;
        }

        this.pixelHistorys.get(y).get(x).add(character);
    }

    @Override
    public boolean increasePixel(final int x, final int y) {
        if (!checkRange(x, y)) {
            return false;
        }

        LinkedList<Character> pixelHistory = this.pixelHistorys.get(y).get(x);

        if (pixelHistory.size() == 0) {
            pixelHistory.add((char) (' ' + 1));
        }

        char origin = pixelHistory.getLast();

        if (origin == '~') {
            return false;
        }

        pixelHistory.add((char) (origin + 1));
        return true;
    }

    @Override
    public boolean decreasePixel(final int x, final int y) {
        if (!checkRange(x, y)) {
            return false;
        }

        LinkedList<Character> pixelHistory = this.pixelHistorys.get(y).get(x);

        if (pixelHistory.size() == 0) {
            return false;
        }

        char origin = pixelHistory.getLast();

        if ((int) origin == ' ') {
            return false;
        }

        pixelHistory.add((char) (origin - 1));
        return true;
    }

    @Override
    public void toUpper(final int x, final int y) {
        if (!checkRange(x, y)) {
            return;
        }

        LinkedList<Character> pixelHistory = this.pixelHistorys.get(y).get(x);

        if (pixelHistory.size() == 0) {
            return;
        }

        char origin = pixelHistory.getLast();

        if (origin < 'a' || origin > 'z') {
            return;
        }

        char bitmask = 32;
        pixelHistory.add((char) (origin ^ bitmask));
    }

    @Override
    public void toLower(final int x, final int y) {
        if (!checkRange(x, y)) {
            return;
        }

        LinkedList<Character> pixelHistory = this.pixelHistorys.get(y).get(x);

        if (pixelHistory.size() == 0) {
            return;
        }

        char origin = pixelHistory.getLast();

        if (origin < 'A' || origin > 'Z') {
            return;
        }

        char bitmask = 32;
        pixelHistory.add((char) (origin ^ bitmask));
    }

    @Override
    public void fillHorizontalLine(final int y, final char character) {
        if (y < 0 || y >= super.getHeight()) {
            return;
        }

        for (int i = 0; i < super.getWidth(); ++i) {
            LinkedList<Character> pixelHistory = this.pixelHistorys.get(y).get(i);

            if (pixelHistory.size() == 0) {
                pixelHistory.add(character);
                continue;
            } else if(pixelHistory.getLast() == character) {
                continue;
            }

            pixelHistory.add(character);
        }
    }

    @Override
    public void fillVerticalLine(final int x, final char character) {
        if (x < 0 || x >= super.getWidth()) {
            return;
        }

        for (int i = 0; i < super.getHeight(); ++i) {
            LinkedList<Character> pixelHistory = this.pixelHistorys.get(i).get(x);

            if (pixelHistory.size() == 0) {
                pixelHistory.add(character);
                continue;
            } else if(pixelHistory.getLast() == character) {
                continue;
            }

            pixelHistory.add(character);
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < super.getHeight(); ++i) {
            for (int j = 0; j < super.getWidth(); ++j) {
                LinkedList<Character> pixelHistory = this.pixelHistorys.get(i).get(j);

                if (pixelHistory.size() == 0 || pixelHistory.getLast() == ' ') {
                    continue;
                }

                pixelHistory.add(' ');
            }
        }
    }

    @Override
    public String getDrawing() {
        StringBuilder sb = new StringBuilder();

        sb.append('+');
        for (int i = 0; i < super.getWidth(); ++i) {
            sb.append('-');
        }
        sb.append('+');
        sb.append(System.lineSeparator());


        for (int i = 0; i < super.getHeight(); ++i) {
            sb.append('|');

            for (int j = 0; j < super.getWidth(); ++j) {
                LinkedList<Character> pixel = this.pixelHistorys.get(i).get(j);

                if (pixel.size() == 0) {
                    sb.append(' ');
                    continue;
                }

                sb.append(pixel.getLast());
            }

            sb.append('|');
            sb.append(System.lineSeparator());
        }

        sb.append('+');
        for (int i = 0; i < super.getWidth(); ++i) {
            sb.append('-');
        }
        sb.append('+');
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    private boolean checkRange(final int x, final int y) {
        return (x >= 0 && x < super.getWidth() && y >= 0 && y < super.getHeight());
    }
}
