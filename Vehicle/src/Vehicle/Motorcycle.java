package Vehicle;

import Vehicle.Engine.Engine;

public class Motorcycle extends Vehicle {
    private static int count;

    private final int serialNumber;
    private final String brandName;
    private final String name;

    public Motorcycle(Engine engine, String brandName, String name) {
        super(engine, VehicleType.MOTORCYCLE);

        this.brandName = brandName;
        this.name = name;
        this.serialNumber = count++;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public String getName() {
        return this.name;
    }

    public int getSerialNumber() {
        return this.serialNumber;
    }
}
