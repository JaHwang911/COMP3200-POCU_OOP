package test.app;

import test.*;

import java.util.ArrayList;
import java.util.HashSet;

public class Program {
    public static void main(String[] args) {
        B b = new B(1, 2);

        try {
            B bCopy = (B) b.clone();

            bCopy.setX(11);
            bCopy.setY(10);

            System.out.println(b.getX());
            System.out.println(b.getY());
        } catch (CloneNotSupportedException e) {
            System.out.println("Error");
        }
    }

    private static void testA() {
        HashSet<A> as = new HashSet<>();

        as.add(new A(1, 2, 3));
        as.add(new A(2, 2, 2));
        as.add(new A(2, 4, 2));
        as.add(new A(5, 2, 1));
        as.add(new A(3, 2, 1));

        System.out.println(as.size());
    }

    private static void testB() {
        B b = new B(1, 2);

        try {
            B bCopy = (B) b.clone();

            bCopy.setX(11);
            bCopy.setY(10);

            System.out.println(b.getX());
            System.out.println(b.getY());
        } catch (CloneNotSupportedException e) {
            System.out.println("Error");
        }
    }
}
