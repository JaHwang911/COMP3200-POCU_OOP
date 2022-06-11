package test.app;

import test.*;

public class Program {
    public static void main(String[] airgs) {
        Person person = new Person("tes", "t");
        Teacher teacher = (Teacher) person;

        System.out.println("No prob");
    }
}
