package gardening;

public class WaterSpray {
    private int capacity;
    private int remainingWaterMl;

    public WaterSpray(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getRemainingWaterMl() {
        return this.remainingWaterMl;
    }

    public void addWater(int amount) {
        this.remainingWaterMl += amount;
        this.remainingWaterMl = Math.min(this.remainingWaterMl, this.capacity);
    }

    public void fillUp() {
        this.remainingWaterMl = capacity;
    }

    public void spray() {
        this.remainingWaterMl -= Math.min(this.remainingWaterMl, 5);
    }
}
