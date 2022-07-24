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
        return this.pixelHistorys.get(y).get(x).size();
    }

    public int getOverdrawCount() {
        int totalCount = 0;

        for (int i = 0; i < super.height; ++i) {
            for (int j = 0; j < super.width; ++j) {
                int overDrawCount = this.pixelHistorys.get(i).get(j).size() - 1;

                if (overDrawCount > 1) {
                    totalCount += overDrawCount;
                }
            }
        }

        return totalCount;
    }
}
