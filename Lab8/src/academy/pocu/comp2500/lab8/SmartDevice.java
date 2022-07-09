package academy.pocu.comp2500.lab8;

public abstract class SmartDevice {
    protected boolean isOn;
    protected int tickCount;
    protected int tickLastUpdate;

    public boolean isOn() {
        return this.isOn;
    }

    public abstract void onTick();

    public int getTicksSinceLastUpdate() {
        return this.tickCount - this.tickLastUpdate;
    }

    public abstract void register(Planter planter);
}
