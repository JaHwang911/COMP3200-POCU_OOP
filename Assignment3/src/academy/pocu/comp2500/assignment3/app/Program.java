package academy.pocu.comp2500.assignment3.app;

import academy.pocu.comp2500.assignment3.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Program {
    //TODO
    // 1. 레이스 움직임
    // 2. 탱크 레이스 공격 모두 같은 위치일 때

    public static void main(String[] args) {
        testMarine();
        testTank();

        System.out.println("No prob: assignment 3");
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

    private static void testTank() {
        clearConsole();
        SimulationManager.clear();
        SimulationManager simulationManager = SimulationManager.getInstance();

        Unit u0 = new Tank(new IntVector2D(14, 7));
        Unit u1 = new Marine(new IntVector2D(11, 6));

        ArrayList<Unit> units = new ArrayList<>();
        units.add(u0);
        units.add(u1);

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
