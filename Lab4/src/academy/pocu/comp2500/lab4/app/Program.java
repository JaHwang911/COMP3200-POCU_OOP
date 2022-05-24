package academy.pocu.comp2500.lab4.app;

import java.util.ArrayList;

public class Program {

    public static void main(String[] args) {
        ArrayList<String> test = new ArrayList<>();

        test.add("Hi");
        test.add("My");
        test.add("Name");

        test.remove(1);

        System.out.println(test.get(0));
        System.out.println(test.get(1));

        // 만약 현재 위치에 있는 인데스
    }
}
