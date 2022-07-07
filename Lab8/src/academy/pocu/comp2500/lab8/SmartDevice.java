package academy.pocu.comp2500.lab8;

public abstract class SmartDevice {
    protected boolean isOn;
    protected int tickCount;
    protected int lastUpdateTick;

    public boolean isOn() {
        return this.isOn;
    }

    public abstract void onTick();

    public void getTicksSinceLastUpdate() {

    }
}
