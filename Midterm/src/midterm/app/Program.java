package midterm.app;

import midterm.*;

public class Program {
    public static void main(String[] args) {
        Point p1 = new Point(1, 5);
        Point p2 = new Point(-2, 1);
        Shape shape = new Rectangle("rect1", p1, p2);

        System.out.println(((Rectangle) shape).getArea());
    }
}
