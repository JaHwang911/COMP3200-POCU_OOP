package academy.pocu.comp2500.lab8;

public class Drainer extends SmartDevice implements IDetectable {
    private static final int DRAINING_PER_TICK = 7;

    private final int maxWaterAmount;
    private Planter planter;

    public Drainer(int maxWaterAmount) {
        this.maxWaterAmount = maxWaterAmount;
    }

    public void onTick() {
        ++super.tickCount;
    }

    public void installedPlanter(Planter planter) {
        this.planter = planter;
        planter.installDetectable(this);
    }

    public void detected() {
        onTick();

        if (this.planter != null && this.planter.getWaterAmount() >= maxWaterAmount) {
            if (!super.isOn) {
                super.isOn = true;
                super.tickLastUpdate = super.tickCount;
            }

            this.planter.subWater(DRAINING_PER_TICK);
        } else if (super.isOn) {
            super.isOn = false;
            super.tickLastUpdate = super.tickCount;
        }
    }
}
