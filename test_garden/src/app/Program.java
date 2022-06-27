package app;

import gardening.*;

public class Program {
    public static void main(String[] args) {
        WaterSpray spray = new WaterSpray(5);

        for (int i = 0; i < 5; ++i) {
            spray.addWater(5);
        }

        assert spray.getRemainingWaterMl() == 5;
        System.out.println("NO prob");
    }
}
