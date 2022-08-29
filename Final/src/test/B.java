package test;

public class B implements Cloneable {
    private int x;
    private int y;

    public B(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
