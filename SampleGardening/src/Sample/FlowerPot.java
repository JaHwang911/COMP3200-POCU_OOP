package Sample;

public class FlowerPot {
    private final int minDailyWaterInMl;
    private int dailyWaterReceived;
    private boolean alive = true;

    public FlowerPot(int minDailyWaterInMl) {
        assert minDailyWaterInMl != 0;

        this.minDailyWaterInMl = minDailyWaterInMl;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public int getMinDailyWaterInMl() {
        return this.minDailyWaterInMl;
    }

    public void addWater(WaterSpray spray) {
        int water = spray.getRemainingWaterInMl();
        spray.spray();
        water -= spray.getRemainingWaterInMl();

        this.dailyWaterReceived += water;
    }

    public void liveAnotherDay() {
        if (this.dailyWaterReceived < this.minDailyWaterInMl) {
            this.alive = false;
        }

        this.dailyWaterReceived = 0;
    }
}
