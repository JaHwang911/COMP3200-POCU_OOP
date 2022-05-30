package academy.pocu.comp2500.lab5.app;

import academy.pocu.comp2500.lab5.Barbarian;
import academy.pocu.comp2500.lab5.Gladiator;
import academy.pocu.comp2500.lab5.Move;

public class Program {

    public static void main(String[] args) {
        testAttack();
        testTooManyMoves();

        System.out.println("No prob");
    }

    private static void testAttack() {
        Barbarian barbarian0 = new Barbarian("Dragonborn Whiterun", 250, 88, 10);
        Barbarian barbarian1 = new Barbarian("Ulfric Stormcloak", 300, 70, 15);

        barbarian0.attack(barbarian1);

        Gladiator gladiator0 = new Gladiator("Ja", 600, 300, 200);
        Gladiator gladiator1 = new Gladiator("Ga", 10000, 150, 700);

        Move move = new Move("Power strike", 20, 3);
        gladiator0.addMove(move);

        gladiator0.attack("Power strike", gladiator1);
        gladiator0.attack("Power strike", barbarian0);
        gladiator0.attack("Thunder bolt", barbarian1);
        gladiator1.attack(gladiator0);

        barbarian0.attack(gladiator0);
    }

    private static void testTooManyMoves() {
        Gladiator ja = new Gladiator("Ja", 700, 300, 150);
        Gladiator bot = new Gladiator("Teemo", 20000, 0, 20000);

        assert ja.addMove(new Move("Power Strike", 5, 8));
        assert !ja.addMove(new Move("Power Strike", 5, 8));
        assert ja.addMove(new Move("Thunder bolt", 15, 3));
        assert ja.addMove(new Move("Set glass", 1, 10));
        assert ja.addMove(new Move("Event horizon", 200, 1));
        assert !ja.addMove(new Move("Meteor", 1000, 1));

        for (int i = 0; i < 8; ++i) {
            ja.attack("Power Strike", bot);
        }

        int currentBotHp = bot.getHp();

        ja.attack("Power Strike", bot);
        assert currentBotHp == bot.getHp();

        ja.rest();
        ja.attack("Power Strike", bot);
        assert currentBotHp != bot.getHp();
    }
}
