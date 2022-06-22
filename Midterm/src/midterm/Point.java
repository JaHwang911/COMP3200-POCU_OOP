package midterm;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void add(Point other) {
        this.x += other.x;
        this.y = other.y;
    }

    public void addTo(Point other) {
        other.x += this.x;
        other.y += this.y;
    }
}
