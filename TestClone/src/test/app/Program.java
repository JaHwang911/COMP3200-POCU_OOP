package test.app;

import test.Head;
import test.Robot;

public class Program {
    public static void main(String[] args) {
        Head head = new Head(90);
        Robot robot = new Robot(head, "I", 100, 200);

        Robot clone = null;

        try {
            clone = (Robot) robot.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("no");
            throw new RuntimeException();
        }

        robot.takeDamage(10);

        assert (robot.getHp() != clone.getHp());
        assert (robot.getHead().getFov() == clone.getHead().getFov());

        System.out.println("No prob: test clone");
    }
}
