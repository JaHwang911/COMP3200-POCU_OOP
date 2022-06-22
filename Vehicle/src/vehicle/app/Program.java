package vehicle.app;

import vehicle.Engine.Engine;
import vehicle.Engine.EngineType;
import vehicle.Engine.FuelType;
import vehicle.Motorcycle;
import vehicle.SpeedType;

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
