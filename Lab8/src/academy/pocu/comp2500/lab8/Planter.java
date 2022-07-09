package academy.pocu.comp2500.lab8;

import java.util.ArrayList;

public class Planter {
    private int waterAmount;
    private final ArrayList<IWaterDetectable> detectable;
    private final ArrayList<IDrainable> drainable;
    private final ArrayList<ISprayable> sprayable;

    public Planter(int waterAmount) {
        this.waterAmount = waterAmount;
        this.detectable = new ArrayList<>();
        this.drainable = new ArrayList<>();
        this.sprayable = new ArrayList<>();
    }

    public int getWaterAmount() {
        return this.waterAmount;
    }

    public void addWater(int waterAmount) {
        this.waterAmount += waterAmount;
    }

    public void subWater(int waterAmount) {
        this.waterAmount -= waterAmount;
    }

    public void installSmartDevice(SmartDevice smartDevice) {
        smartDevice.register(this);
    }

    public void installDetectable(IWaterDetectable device) {
        this.detectable.add(device);
    }

    public void installDrainable(IDrainable device) {
        this.drainable.add(device);
    }

    public void installSprayable(ISprayable device) {
        this.sprayable.add(device);
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
