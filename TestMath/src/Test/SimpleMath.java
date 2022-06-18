package Test;

public class SimpleMath {
    private String name;

    public SimpleMath(String name) {
        this.name = name;
    }

    public static int abs(int a) {
        return a < 0 ? -a : a;
    }

    public static void setName(SimpleMath math) {
        math.name = "Hi";
    }

    public static int min(int a, int b) {
        return a < b ? a : b;
    }

    public static int max(int a, int b) {
        return a > b ? a : b;
    }
}
