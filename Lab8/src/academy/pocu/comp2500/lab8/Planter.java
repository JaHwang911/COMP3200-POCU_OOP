package academy.pocu.comp2500.lab8;

import java.util.ArrayList;

public class Planter {
    private int waterAmount;
    private final ArrayList<ISprayable> sprayable;
    private final ArrayList<IWaterDetectable> detectable;
    private final ArrayList<IDrainable> drainable;

    public Planter(int waterAmount) {
        this.waterAmount = waterAmount;
        this.sprayable = new ArrayList<>();
        this.detectable = new ArrayList<>();
        this.drainable = new ArrayList<>();
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
        smartDevice.installedPlanter(this);
    }

    public void installSprayable(ISprayable device) {
        this.sprayable.add(device);
    }

    public void installDetectable(IWaterDetectable device) {
        this.detectable.add(device);
    }

    public void installDrainable(IDrainable device) {
        this.drainable.add(device);
    }

    public void tick() {
        for (IWaterDetectable device : this.detectable) {
            device.detect(this.waterAmount);
        }

        for (IDrainable device : this.drainable) {
            device.drain(this);
        }

        for (ISprayable device : this.sprayable) {
            device.spray(this);
        }

        this.waterAmount = Math.max(0, this.waterAmount - 2);
    }
}
