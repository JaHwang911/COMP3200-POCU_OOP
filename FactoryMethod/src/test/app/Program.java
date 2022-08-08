package test.app;

import test.*;

public class Program {
    public static void main(String[] args) {
        testFactory();
    }

    private static void testFactory() {
        Menu kMenu = new KoreanMenu();
        Cup cup = kMenu.createCupOrNull(CupSize.MEDIUM);

        System.out.println(cup.getCupSize());

        Menu aMenu = new AmericanMenu();
        Cup cup1 = aMenu.createCupOrNull(CupSize.LARGE);

        System.out.println(cup1.getCupSize());
    }
}
