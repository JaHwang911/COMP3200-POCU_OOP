package vehicle;

import vehicle.Engine.Engine;
import vehicle.Engine.FuelType;

public class Vehicle {
    private static final byte METER = 1;
    protected final Engine engine;
    protected boolean enginePower;
    protected float kilometers;
    protected short speed;
    protected final float maxFuelGauge;
    protected float fuelGauge;
    protected final float fuelEfficiency;

    protected Vehicle(Engine engine, VehicleType vehicleType) {
        this.engine = engine;

        switch (vehicleType) {
            case CAR:
                this.maxFuelGauge = 80;
                this.fuelEfficiency = 10;
                break;
            case MOTORCYCLE:
                this.maxFuelGauge = 50;
                this.fuelEfficiency = 20;
                break;
            default:
                this.maxFuelGauge = -1;
                this.fuelEfficiency = -1;
                assert false : "Unknown vehicle type";
                break;
        }
    }

    public Engine getEngine() {
        return this.engine;
    }

    public FuelType getFuelType() {
        return this.engine.getFuelType();
    }

    public boolean isEngineOn() {
        return this.engine.isEngineOn();
    }

    public void startUp() {
        this.engine.startUp();
        this.enginePower = true;
    }

    public short getSpeed() {
        return this.speed;
    }

    public float getKilometers() {
        return this.kilometers;
    }

    public float getFuelGauge() {
        return this.fuelGauge;
    }

    public void addFuel(float fuel) {
        this.fuelGauge += fuel;
    }

    public void fillUp() {
        this.fuelGauge = this.maxFuelGauge;
    }

    public void move(SpeedType speedType) {
        if (this.fuelGauge < METER / this.fuelEfficiency || !this.enginePower) {
            System.out.println("Not enough fuel or engine stopped");
            return;
        }

        final float speedWeight;

        switch (speedType) {
            case SLOW:
                speedWeight = 0.6f;
                this.speed = 30;
                break;
            case NORMAL:
                speedWeight = 1.0f;
                this.speed = 60;
                break;
            case FAST:
                speedWeight = 1.3f;
                this.speed = 100;
                break;
            case MAX:
                speedWeight = 1.8f;
                this.speed = 180;
                break;
            default:
                assert false : "Unknown speed type";
                return;
        }

        this.fuelGauge -= METER / this.fuelEfficiency * speedWeight;
        this.kilometers += METER;
    }
}
