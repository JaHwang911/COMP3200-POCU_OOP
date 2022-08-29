package test;

public class A {
    private int x;
    private int y;
    private int z;

    public A(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object obj) {
        A other = (A) obj;

        return (this.x == other.x && this.y == other.y && this.z == other.z);
    }
}
