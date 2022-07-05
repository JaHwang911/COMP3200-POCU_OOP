package academy.pocu.comp2500.assignment3.app;

import academy.pocu.comp2500.assignment3.IntVector2D;
import academy.pocu.comp2500.assignment3.Marine;
import academy.pocu.comp2500.assignment3.SimulationManager;
import academy.pocu.comp2500.assignment3.Unit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Program {
    //TODO
    // 1. 공격 대상 북, 동, 남, 서로 확인하기
    // 2. 움직일 곳
    // 3. onSpawn() registerXXX

    public static void main(String[] args) {
//        testPrintMap();
        testVisible();
        testMarine();

        System.out.println("No prob: assignment 3");
    }

    private static void testPrintMap() {
        SimulationManager.clear();

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

        SimulationManager simulationManager = SimulationManager.getInstance();

        for (Unit unit : units) {
            simulationManager.spawn(unit);
        }

        simulationManager.printMap();
    }

    private static void testVisible() {
        SimulationManager.clear();
        SimulationManager simulationManager = SimulationManager.getInstance();

        Unit u0 = new Marine(new IntVector2D(7, 4));
        Unit u1 = new Marine(new IntVector2D(6, 3));
        Unit u2 = new Marine(new IntVector2D(0, 0));

        ArrayList<Unit> units = new ArrayList<>();
        units.add(u0);
        units.add(u1);
        units.add(u2);

        for (Unit unit : units) {
            simulationManager.spawn(unit);
        }

        var visibleUnits = simulationManager.visibleEnemy(u0);

        assert visibleUnits.size() == 2;
    }

    private static void testMarine() {
        SimulationManager.clear();
        SimulationManager simulationManager = SimulationManager.getInstance();
        Unit u0 = new Marine(new IntVector2D(7, 4));
        Unit u1 = new Marine(new IntVector2D(7, 3));
        Unit u2 = new Marine(new IntVector2D(5, 1));

        ArrayList<Unit> units = new ArrayList<>();
        units.add(u0);
        units.add(u1);
        units.add(u2);

        for (Unit unit : units) {
            simulationManager.spawn(unit);
        }

        SimulationVisualizer visualizer = new SimulationVisualizer(units);
        for (int i = 0; i < 10; ++i) {
            clearConsole();
            visualizer.visualize(i, simulationManager.getUnits());
            simulationManager.update();
            continueOnEnter();
        }
    }

    public static void continueOnEnter() {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Press enter to continue");
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
