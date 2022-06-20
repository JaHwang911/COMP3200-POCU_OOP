package test.app;

import test.Math;

public class Program {
    public static void main(String[] args) {
        Math math = new Math();

        int sum = math.add(1, 2, 3, 4, 5, 6);

        assert sum == 15;

        System.out.println("Args No prob");
    }
}
