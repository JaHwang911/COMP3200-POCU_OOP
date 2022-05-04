package sample.vehicle;

import java.util.ArrayList;

public class Vehicle {
    public VehicleType type;
    public ArrayList<Passenger> passengers;
    public double fuelAmount;
    public int mileage;

    public Vehicle(VehicleType type) {
        this(type, new ArrayList<Passenger>(), 0.0);
    }

    public Vehicle(VehicleType type, ArrayList<Passenger> passengers) {
        this(type, passengers, 0.0);
    }

    public Vehicle(VehicleType type, double fuelAmount) {
        this(type, new ArrayList<Passenger>(), fuelAmount);
    }

    public Vehicle(VehicleType type,
                   ArrayList<Passenger> passengers,
                   double fuelAmount) {
        this.type = type;
        this.passengers = passengers;
        this.fuelAmount = fuelAmount;
        this.mileage = 0; // 굳이 할 필요는 없음
    }

    public void addPassenger(Passenger passenger) {
        this.passengers.add(passenger);
    }

    public void removePassenger(String name) {
        for (Passenger p: this.passengers) {
            if (p.name.equals(name)) {
                this.passengers.remove(p);
                break;
            }
        }
    }

    public void addFuel(double fuel) {
        this.fuelAmount += fuel;
    }

    public void drive(int distance) {
        System.out.println(String.format("Traveling %dkm!", distance));

        double gasMileage = 10000;

        switch (this.type) {
            case MOTORCYCLE:
                gasMileage = 0.05;
                break;
            case SEDAN:
                gasMileage = 0.07;
                break;
            case MINIVAN:
                gasMileage = 0.1;
                break;
            default:
                assert(false) : "Unrecognize Vehicle type" + this.type;
                break;
        }

        double requireFuel = gasMileage * distance + 0.01 * this.passengers.size();

        if (requireFuel > this.fuelAmount) {
            System.out.println("Not enough fuel!!");
            return;
        }

        this.fuelAmount -= requireFuel;
        this.mileage += mileage;

        System.out.println(String.format("Fuel amount: %.2f", this.fuelAmount));
        System.out.println(String.format("Mileage: %dkm", this.mileage));
    }
}
