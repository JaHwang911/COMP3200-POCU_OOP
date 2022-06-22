package midterm;

public class Rectangle extends Shape {
    private Point p1;
    private Point p2;

    public Rectangle(String name, Point p1, Point p2) {
        super(name);

        this.p1 = p1;
        this.p2 = p2;
    }

    public Point getP1() {
        return this.p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return this.p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public int getArea() {
        return Math.abs(this.p1.getX() - this.p2.getX()) * Math.abs(this.p1.getY() - this.p2.getY());
    }
}
