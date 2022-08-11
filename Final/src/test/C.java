package test;

public class C extends B{
    public C(String s) {
        super(s);
    }

    public void printS() {
        System.out.println(String.format("C: %s", this.getS()));
    }

    public void doMagic(int x) {
        System.out.println((int) (x * x));
    }
}
