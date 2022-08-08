package test.app;

import test.Head;
import test.Robot;

public class Program {
    public static void main(String[] args) {
        Head head = new Head(90);
        Robot robot = new Robot(head, "I", 200);

        Robot clone;

        try {
            clone = (Robot) robot.clone();
        } catch (CloneNotSupportedException e) {

        }
    }
}
