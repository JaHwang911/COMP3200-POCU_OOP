package academy.pocu.comp2500.lab8;

public class Drainer extends SmartDevice implements IDetectable{
    private int maxWaterAmount;

    public Drainer(int maxWaterAmount) {
        this.maxWaterAmount = maxWaterAmount;
    }

    public void onTick() {

    }

    public void detected(Planter planter) {

    }
}
