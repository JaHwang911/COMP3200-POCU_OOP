package test;

public class B extends A {
    public B(String s) {
        super(s);
    }

    public void printS() {
        System.out.println(String.format("B: %s", this.getS()));
    }

    public float doMagic(float x) {
        float ret = 2 * x;
        System.out.println(ret);

        return ret;
    }
}
