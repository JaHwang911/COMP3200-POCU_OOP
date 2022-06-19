package Sample.app;

import Sample.FlowerPot;
import Sample.WaterSpray;

public class Program {
    public static void main(String[] args) {
        WaterSpray spray = new WaterSpray(20);
        FlowerPot flowerPot0 = new FlowerPot(20);
        FlowerPot flowerPot1 = new FlowerPot(5);
        FlowerPot flowerPot2 = new FlowerPot(5);

        spray.fillUp();

        assert flowerPot0.getMinDailyWaterInMl() == 20;
        assert flowerPot0.isAlive();
        flowerPot0.addWater(spray);
        flowerPot0.addWater(spray);
        flowerPot0.addWater(spray);
        flowerPot0.liveAnotherDay();
        assert !flowerPot0.isAlive();

        assert spray.getRemainingWaterInMl() == 5;

        assert flowerPot1.getMinDailyWaterInMl() == 5;
        assert flowerPot1.isAlive();
        flowerPot1.addWater(spray);
        flowerPot1.addWater(spray);
        flowerPot1.liveAnotherDay();
        assert flowerPot1.isAlive();

        assert spray.getRemainingWaterInMl() == 0;

        assert flowerPot2.getMinDailyWaterInMl() == 5;
        assert flowerPot2.isAlive();
        flowerPot2.addWater(spray);
        flowerPot2.liveAnotherDay();
        assert !flowerPot2.isAlive();

        System.out.println("Gardening No prob");
    }
}
