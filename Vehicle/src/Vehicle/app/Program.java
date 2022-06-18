package Vehicle.app;

import Vehicle.Engine.Engine;
import Vehicle.Engine.EngineType;
import Vehicle.Engine.FuelType;
import Vehicle.Motorcycle;
import Vehicle.SpeedType;

public class Program {
    public static void main(String[] args) {
        Motorcycle cbr = new Motorcycle(new Engine(EngineType.INLINE_ENGINE, FuelType.GASOLINE), "Honda", "CBR");

        cbr.move(SpeedType.FAST);
        assert cbr.getKilometers() == 0;

        cbr.startUp();
        cbr.addFuel(5);
        cbr.move(SpeedType.NORMAL);
        assert cbr.getKilometers() == 1;
        assert cbr.getSpeed() == 60;

        System.out.println(cbr.getFuelGauge());

        System.out.println("Vehicle no prob");
    }
}
