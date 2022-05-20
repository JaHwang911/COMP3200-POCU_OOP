package gardening;

public class Faucet {
    public void addWaterTo(WaterSpray spray, int amount) {
        spray.remainingWaterMl += amount;
    }
}
