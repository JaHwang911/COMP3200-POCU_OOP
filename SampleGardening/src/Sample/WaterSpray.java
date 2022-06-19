package Sample;

public class WaterSpray {
    private int capacity;
    private int remainingWaterInMl;

    public WaterSpray(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getRemainingWaterInMl() {
        return this.remainingWaterInMl;
    }

    public void spray() {
        this.remainingWaterInMl -= Math.min(this.remainingWaterInMl, 5);
    }

    public void addWater(int amountInMl) {
        this.remainingWaterInMl += amountInMl;
        this.remainingWaterInMl = Math.min(this.remainingWaterInMl, this.capacity);
    }

    public void fillUp() {
        this.remainingWaterInMl = this.capacity;
    }
}
