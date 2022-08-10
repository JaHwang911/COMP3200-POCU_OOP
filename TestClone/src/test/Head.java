package test;

public class Head implements Cloneable {
    private int fov;

    public Head(int fov) {
        this.fov = fov;
    }

    public int getFov() {
        return this.fov;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
