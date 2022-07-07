package academy.pocu.comp2500.lab8;

import java.util.ArrayList;

public class Planter {
    private int waterAmount;
    private ArrayList<SmartDevice> devices;

    public Planter(int waterAmount) {
        this.waterAmount = waterAmount;
        this.devices = new ArrayList<>();
    }

    public int getWaterAmount() {
        return this.waterAmount;
    }

    public void tick() {
        for (SmartDevice device : this.devices) {
            device.onTick();
        }

        this.waterAmount -= 2;
    }
}
