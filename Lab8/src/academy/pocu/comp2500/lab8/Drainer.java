package academy.pocu.comp2500.lab8;

public class Drainer extends SmartDevice implements IDrainable, IWaterDetectable {
    private static final int DRAINING_PER_TICK = 7;

    private final int maxWaterAmount;

    public Drainer(int maxWaterAmount) {
        this.maxWaterAmount = maxWaterAmount;
    }

    public void onTick() {
        ++super.tickCount;
    }

    public void installedPlanter(Planter planter) {
        planter.installDetectable(this);
        planter.installDrainable(this);
    }

    public void drain(Planter planter) {
        if (super.isOn) {
            planter.subWater(DRAINING_PER_TICK);
        }
    }

    public void detect(final int waterLevel) {
        onTick();

        if (waterLevel >= maxWaterAmount) {
            if (!super.isOn) {
                super.isOn = true;
                super.tickLastUpdate = super.tickCount;
            }
        } else if (super.isOn) {
            super.isOn = false;
            super.tickLastUpdate = super.tickCount;
        }
    }
}
