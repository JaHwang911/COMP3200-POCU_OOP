package academy.pocu.comp2500.assignment1.app;

import academy.pocu.comp2500.assignment1.*;
import academy.pocu.comp2500.assignment1.registry.Registry;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        testConstructorRef();
        Registry registry = new Registry();
        App app = new App(registry);
        registry.validate();

        System.out.println("No prob");
    }

    private static void testConstructorRef() {
        String str1 = "nana";
        String str2 = str1;

        str1 = "Hello";

        System.out.printf("%s\n", str1);
        System.out.printf("%s\n", str2);
    }
}
