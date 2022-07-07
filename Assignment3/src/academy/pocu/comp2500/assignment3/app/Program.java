package academy.pocu.comp2500.assignment3.app;

import academy.pocu.comp2500.assignment3.*;
import academy.pocu.comp2500.assignment3.registry.Registry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Program {
    //TODO
    // 1. 레이스 움직임
    // 2. 마인의 collision은 공중 유닛은 해당 안된다 가정
    // 3. 마인 끼리도 볼 수 없다.

    public static void main(String[] args) {
        // 스마트 마인 테스트
        // 레이스 이동 테스트
        testOfficial();
//        testMarine();
//        testTank();
//        testDestroyer();
//        testMarineDiceFiveTurret();
//        testWraithDiceFiveTurret();
//        testMine();
//        testWraithAttackAir();

        Registry registry = new Registry();
        App app = new App(registry);
        registry.validate();

        System.out.println("No prob: assignment 3");
    }

    private static void testOfficial() {
        SimulationManager.clear();
        SimulationManager simulationManager = SimulationManager.getInstance();

        Unit u0 = new Mine(new IntVector2D(12, 1), 2);
        Unit u1 = new Marine(new IntVector2D(0, 5));
        Unit u2 = new Turret(new IntVector2D(5, 6));
        Unit u3 = new Tank(new IntVector2D(2, 4));
        Unit u4 = new Marine(new IntVector2D(2, 4));
        Unit u5 = new Wraith(new IntVector2D(2, 7));

        ArrayList<Unit> units = new ArrayList<>();

        units.add(u0);
        units.add(u1);
        units.add(u2);
        units.add(u3);
        units.add(u4);
        units.add(u5);

        startSimulation(units, 10);
    }

    private static void testDestroyer() {
        SimulationManager.clear();
        SimulationManager simulationManager = SimulationManager.getInstance();

        Unit u0 = new Mine(new IntVector2D(12, 1), 2);
        Unit u1 = new Marine(new IntVector2D(0, 5));
        Unit u2 = new Turret(new IntVector2D(5, 6));
        Unit u3 = new Tank(new IntVector2D(2, 4));
        Unit u4 = new Marine(new IntVector2D(2, 4));
        Unit u5 = new Wraith(new IntVector2D(2, 7));
        Unit u6 = new Destroyer(new IntVector2D(15, 7));

        ArrayList<Unit> units = new ArrayList<>();

        units.add(u0);
        units.add(u1);
        units.add(u2);
        units.add(u3);
        units.add(u4);
        units.add(u5);
        units.add(u6);

        startSimulation(units, 5);
    }

    private static void testMarine() {
        clearConsole();
        SimulationManager.clear();
        SimulationManager simulationManager = SimulationManager.getInstance();
        Unit u0 = new Marine(new IntVector2D(7, 4));
        Unit u1 = new Marine(new IntVector2D(7, 3));
        Unit u2 = new Marine(new IntVector2D(5, 1));
        Unit u3 = new Marine(new IntVector2D(13, 7));
        Unit u4 = new Marine(new IntVector2D(11, 6));
        Unit u5 = new Marine(new IntVector2D(9, 7));
        Unit u6 = new Tank(new IntVector2D(1, 6));

        ArrayList<Unit> units = new ArrayList<>();
        units.add(u0);
        units.add(u1);
        units.add(u2);
        units.add(u3);
        units.add(u4);
        units.add(u5);
        units.add(u6);

        startSimulation(units, 10);
    }

    private static void testTank() {
        clearConsole();
        SimulationManager.clear();
        SimulationManager simulationManager = SimulationManager.getInstance();

        Unit u0 = new Tank(new IntVector2D(14, 7));
        Unit u1 = new Marine(new IntVector2D(11, 6));

        ArrayList<Unit> units = new ArrayList<>();
        units.add(u0);
        units.add(u1);

        startSimulation(units, 10);
    }

    private static void testMarineDiceFiveTurret() {
        clearConsole();
        SimulationManager.clear();
        SimulationManager simulationManager = SimulationManager.getInstance();
        Unit u0 = new Marine(new IntVector2D(6, 4));
        Unit u1 = new Turret(new IntVector2D(4, 2));
        Unit u2 = new Turret(new IntVector2D(8, 2));
        Unit u3 = new Turret(new IntVector2D(4, 6));
        Unit u4 = new Turret(new IntVector2D(8, 6));

        ArrayList<Unit> units = new ArrayList<>();
        units.add(u0);
        units.add(u1);
        units.add(u2);
        units.add(u3);
        units.add(u4);

        startSimulation(units, 20);
    }

    private static void testWraithDiceFiveTurret() {
        clearConsole();
        SimulationManager.clear();
        SimulationManager simulationManager = SimulationManager.getInstance();
        Unit u0 = new Wraith(new IntVector2D(6, 4));
        Unit u1 = new Turret(new IntVector2D(4, 2));
        Unit u2 = new Turret(new IntVector2D(8, 2));
        Unit u3 = new Turret(new IntVector2D(4, 6));
        Unit u4 = new Turret(new IntVector2D(8, 6));

        ArrayList<Unit> units = new ArrayList<>();
        units.add(u0);
        units.add(u1);
        units.add(u2);
        units.add(u3);
        units.add(u4);

        startSimulation(units, 20);
    }

    private static void testMine() {
        clearConsole();
        SimulationManager.clear();
        SimulationManager simulationManager = SimulationManager.getInstance();
        Unit u0 = new Marine(new IntVector2D(7, 4));
        Unit u1 = new SmartMine(new IntVector2D(7, 4), 3, 10);
        Unit u2 = new Turret(new IntVector2D(8, 4));
        Unit u3 = new Wraith(new IntVector2D(7, 4));
        Unit u4 = new Wraith(new IntVector2D(8, 4));

        ArrayList<Unit> units = new ArrayList<>();
        units.add(u0);
        units.add(u1);
        units.add(u2);
        units.add(u3);
        units.add(u4);

        startSimulation(units, 10);
    }

    private static void testWraithAttackAir() {
        clearConsole();
        SimulationManager.clear();
        SimulationManager simulationManager = SimulationManager.getInstance();
        Unit u0 = new Marine(new IntVector2D(8, 3));
        Unit u3 = new Wraith(new IntVector2D(7, 4));
        Unit u4 = new Wraith(new IntVector2D(8, 4));

        ArrayList<Unit> units = new ArrayList<>();
        units.add(u0);
        units.add(u3);
        units.add(u4);

        startSimulation(units, 10);
    }

    private static void startSimulation(ArrayList<Unit> units, int frame) {
        SimulationManager simulationManager = SimulationManager.getInstance();

        for (Unit unit : units) {
            simulationManager.spawn(unit);
        }

        SimulationVisualizer visualizer = new SimulationVisualizer(units);
        for (int i = 0; i < frame; ++i) {
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
