package academy.pocu.comp2500.assignment4;

import java.util.ArrayList;
import java.util.LinkedList;

public final class OverdrawAnalyzer extends Canvas {
    private final ArrayList<ArrayList<LinkedList<Character>>> pixelHistory;

    public OverdrawAnalyzer(final int width, final int height) {
        super(width, height);

        this.pixelHistory = new ArrayList<>(height);

        for (int i = 0; i < height; ++i) {
            this.pixelHistory.add(new ArrayList<>(width));

            for (int j = 0; j < width; ++j) {
                this.pixelHistory.get(i).add(new LinkedList<>());
                this.pixelHistory.get(i).get(j).add(' ');
            }
        }
    }

    public LinkedList<Character> getPixelHistory(int x, int y) {
        return this.pixelHistory.get(y).get(x);
    }

    public int getOverdrawCount(int x, int y) {
        return this.pixelHistory.get(y).get(x).size();
    }

    public int getOverdrawCount() {
        int totalCount = 0;

        for (int i = 0; i < super.height; ++i) {
            for (int j = 0; j < super.width; ++j) {
                int overDrawCount = this.pixelHistory.get(i).get(j).size() - 2;

                if (overDrawCount > 0) {
                    totalCount += overDrawCount;
                }
            }
        }

        return totalCount;
    }

    @Override
    public char getPixel(int x, int y) {
        return this.pixelHistory.get(y).get(x).getLast();
    }

    @Override
    public void drawPixel(final int x, final int y, final char character) {
        if (!checkRange(x, y)) {
            return;
        }

        LinkedList<Character> linkedCharacters = this.pixelHistory.get(y).get(x);
        char currentCharacter = linkedCharacters.getLast();

        if (currentCharacter == character) {
            return;
        }

        linkedCharacters.add(character);
    }

    @Override
    public boolean increasePixel(final int x, final int y) {
        if (!checkRange(x, y)) {
            return false;
        }

        LinkedList<Character> linkedCharacters = this.pixelHistory.get(y).get(x);
        char origin = linkedCharacters.getLast();

        if ((int) origin == '~') {
            return false;
        }

        linkedCharacters.add(++origin);

        return true;
    }

    @Override
    public boolean decreasePixel(final int x, final int y) {
        if (!checkRange(x, y)) {
            return false;
        }

        LinkedList<Character> linkedCharacters = this.pixelHistory.get(y).get(x);
        char origin = linkedCharacters.getLast();

        if ((int) origin == ' ') {
            return false;
        }

        linkedCharacters.add(++origin);

        return true;
    }

    @Override
    public void toUpper(final int x, final int y) {
        if (!checkRange(x, y)) {
            return;
        }

        LinkedList<Character> linkedCharacters = this.pixelHistory.get(y).get(x);
        char origin = linkedCharacters.getLast();

        if (origin < 'a' || origin > 'z') {
            return;
        }

        char bitmask = 32;
        linkedCharacters.add((char) (origin ^ bitmask));
    }

    @Override
    public void toLower(final int x, final int y) {
        if (!checkRange(x, y)) {
            return;
        }

        LinkedList<Character> linkedCharacters = this.pixelHistory.get(y).get(x);
        char origin = linkedCharacters.getLast();

        if (origin < 'A' || origin > 'Z') {
            return;
        }

        char bitmask = 32;
        linkedCharacters.add((char) (origin ^ bitmask));
    }

    @Override
    public void fillHorizontalLine(final int y, final char character) {
        if (y < 0 || y >= super.height) {
            return;
        }

        for (int i = 0; i < super.width; ++i) {
            LinkedList<Character> linkedCharacters = this.pixelHistory.get(y).get(i);

            if (linkedCharacters.getLast() == character) {
                continue;
            }

            linkedCharacters.add(character);
        }
    }

    @Override
    public void fillVerticalLine(final int x, final char character) {
        if (x < 0 || x >= this.width) {
            return;
        }

        for (int i = 0; i < this.height; ++i) {
            LinkedList<Character> linkedCharacters = this.pixelHistory.get(i).get(x);

            if (linkedCharacters.getLast() == character) {
                continue;
            }

            linkedCharacters.add(character);
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                LinkedList<Character> linkedCharacters = this.pixelHistory.get(i).get(j);

                if (linkedCharacters.getLast() == ' ') {
                    continue;
                }

                linkedCharacters.add(' ');
            }
        }
    }

    @Override
    public String getDrawing() {
        StringBuilder sb = new StringBuilder();

        sb.append('+');
        for (int i = 0; i < super.width; ++i) {
            sb.append('-');
        }
        sb.append('+');
        sb.append(System.lineSeparator());


        for (int i = 0; i < super.height; ++i) {
            sb.append('|');

            for (int j = 0; j < super.width; ++j) {
                sb.append(this.pixelHistory.get(i).get(j).getLast());
            }

            sb.append('|');
            sb.append(System.lineSeparator());
        }

        sb.append('+');
        for (int i = 0; i < super.width; ++i) {
            sb.append('-');
        }
        sb.append('+');
        sb.append(System.lineSeparator());

        return sb.toString();
    }
}
