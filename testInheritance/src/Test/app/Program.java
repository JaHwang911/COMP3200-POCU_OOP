package Test.app;

import Test.*;

public class Program {
    public static void main(String[] airgs) {
        Person person = new Person("tes", "t");

        assert !(person instanceof Teacher);

        Integer num = 3;
        num += 2;

        assert num == 5;

        System.out.println("Inheritance No prob");
    }
}
