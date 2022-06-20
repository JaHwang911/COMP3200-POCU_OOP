package test;

public class Math {
    public int add(int... args) {
        int sum = 0;

        for (int num : args) {
            sum += num;
        }

        return sum;
    }
}
