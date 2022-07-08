package academy.pocu.comp2500.lab8;

import java.util.ArrayList;

public class Planter {
    private int waterAmount;
    private final ArrayList<SmartDevice> devices;
    private final ArrayList<ISprayable> sprayable;
    private final ArrayList<IDetectable> detectable;

    public Planter(int waterAmount) {
        this.waterAmount = waterAmount;
        this.devices = new ArrayList<>();
        this.sprayable = new ArrayList<>();
        this.detectable = new ArrayList<>();
    }

    public int getWaterAmount() {
        return this.waterAmount;
    }

    public void addWater(int water) {
        this.waterAmount += water;
    }

    public void subWater(int water) {
        this.waterAmount -= water;
    }

    public void installSmartDevice(SmartDevice smartDevice) {
        this.devices.add(smartDevice);
        smartDevice.installedPlanter(this);
    }

    public void installSprayable(ISprayable device) {
        this.sprayable.add(device);
    }

    public void installDetectable(IDetectable device) {
        this.devices.remove(device);
        this.detectable.add(device);
    }

    public void tick() {
        for (IDetectable device : this.detectable) {
            device.detected();
        }

        for (SmartDevice device : this.devices) {
            device.onTick();
        }

        this.waterAmount = Math.max(0, this.waterAmount - 2);
    }
}
