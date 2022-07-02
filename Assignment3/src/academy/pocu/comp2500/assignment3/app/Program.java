package academy.pocu.comp2500.assignment3.app;

import academy.pocu.comp2500.assignment3.IntVector2D;
import academy.pocu.comp2500.assignment3.Marine;
import academy.pocu.comp2500.assignment3.SimulationManager;
import academy.pocu.comp2500.assignment3.Unit;

import java.util.ArrayList;

public class Program {

    public static void main(String[] args) {
        testVisible();

        System.out.println("No prob: assignment 3");
    }

    private static void testPrintMap() {
        System.out.print("Test print map: ");

        Unit u0 = new Marine(new IntVector2D(0, 0));
        Unit u1 = new Marine(new IntVector2D(1, 1));
        Unit u2 = new Marine(new IntVector2D(2, 2));
        Unit u3 = new Marine(new IntVector2D(3, 3));
        Unit u4 = new Marine(new IntVector2D(4, 4));
        Unit u5 = new Marine(new IntVector2D(5, 5));

        ArrayList<Unit> units = new ArrayList<>();
        units.add(u0);
        units.add(u1);
        units.add(u2);
        units.add(u3);
        units.add(u4);
        units.add(u5);

        SimulationManager simulationManger = SimulationManager.getInstance();

        for (Unit unit : units) {
            simulationManger.spawn(unit);
        }

        simulationManger.printMap();
        System.out.println("Ok");
    }

    private static void testVisible() {
        System.out.print("Test visible unit: ");
        SimulationManager simulationManger = SimulationManager.getInstance();

        Unit u0 = new Marine(new IntVector2D(7, 4));
        Unit u1 = new Marine(new IntVector2D(6, 3));
        Unit u2 = new Marine(new IntVector2D(0, 0));

        ArrayList<Unit> units = new ArrayList<>();
        units.add(u0);
        units.add(u1);
        units.add(u2);

        for (Unit unit : units) {
            simulationManger.spawn(unit);
        }

        var visibleUnits = simulationManger.visibleEnemy(u0);

        assert visibleUnits.size() == 1;
        assert visibleUnits.get(0) == u1;

        System.out.println("Ok");
    }
}
