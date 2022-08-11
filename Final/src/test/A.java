package test;

public class A {
    private final String s;

    public A(String s) {
        this.s = s;
    }

    public String getS() {
        return this.s;
    }

    public void printS() {
        System.out.println(String.format("A: %s", this.s));
    }

    public void doMagic(int x) {
        System.out.println(x + this.s.length());
    }
}
