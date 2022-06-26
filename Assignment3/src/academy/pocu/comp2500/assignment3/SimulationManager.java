package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class SimulationManager {
    private static SimulationManager instance;
    private ArrayList<Unit> units = new ArrayList<>();

    private SimulationManager() {
    }

    public static SimulationManager getInstance() {
        if (instance == null) {
            instance = new SimulationManager();
        }

        return instance;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void spawn(Unit unit) {
        this.units.add(unit);
    }

    public void registerThinkable(Unit thinkable) {

    }

    public void registerMovable(Unit movable) {

    }

    public void registerCollisionEventListener(Unit listener) {

    }

    public void update() {

    }
}
