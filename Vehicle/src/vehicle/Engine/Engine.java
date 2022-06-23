package vehicle.Engine;

public class Engine {
    //TODO
    // add fuel tank class
    // add speed
    private final EngineType engineType;
    private final FuelType fuelType;
    private boolean power;
    private final short horsepower;

    public Engine(EngineType engineType, FuelType fuelType) {
        this.engineType = engineType;
        this.fuelType = fuelType;

        switch (this.engineType) {
            case INLINE_ENGINE:
                this.horsepower = 100;
                break;
            case V_ENGINE:
                this.horsepower = 200;
                break;
            default:
                this.horsepower = -1;
                assert false : "Unknown engine type";
                break;
        }
    }

    public void startUp() {
        this.power = true;
    }

    public EngineType getEngineType() {
        return this.engineType;
    }

    public FuelType getFuelType() {
        return this.fuelType;
    }

    public int getHorsepower() {
        return this.horsepower;
    }

    public boolean isEngineOn() {
        return this.power;
    }
}
