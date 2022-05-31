package academy.pocu.comp2500.lab5.app;

import academy.pocu.comp2500.lab5.Barbarian;
import academy.pocu.comp2500.lab5.Gladiator;
import academy.pocu.comp2500.lab5.Knight;
import academy.pocu.comp2500.lab5.Move;
import academy.pocu.comp2500.lab5.Pet;

public class Program {

    public static void main(String[] args) {
        testAttack();
        testTooManyMoves();
        testOfficial();

        System.out.println("No prob");
    }

    private static void testOfficial() {
        Barbarian barbarian0 = new Barbarian("Dragonborn Whiterun", 250, 210, 60);
        Barbarian barbarian1 = new Barbarian("Ulfric Stormcloak", 200, 70, 10);

        barbarian0.attack(barbarian1);

        assert barbarian1.getHp() == 100;
        assert barbarian1.isAlive();

        barbarian0.attack(barbarian1);

        assert barbarian1.getHp() == 0;
        assert !barbarian1.isAlive();

        barbarian1.attack(barbarian0);

        Gladiator gladiator0 = new Gladiator("Parthunax", 250, 210, 10);
        Gladiator gladiator1 = new Gladiator("Zoro", 100, 150, 65);
        Move move0 = new Move("Gomu Gomu no pistol", 50, 10);
        Move move1 = new Move("Thunderbolt", 90, 15);
        Move move2 = new Move("Ice Beam", 90, 10);
        Move move3 = new Move("Surf", 90, 15);

        assert gladiator0.addMove(move0);
        assert gladiator0.addMove(move1);
        assert gladiator0.addMove(move2);
        assert gladiator0.addMove(move3);

        assert gladiator0.removeMove("Surf");

        gladiator0.attack("Gomu Gomu no pistol", barbarian0);

        assert barbarian0.getHp() == 163;
        assert barbarian0.isAlive();

        gladiator0.attack("Gomu Gomu no pistol", gladiator1);

        assert gladiator1.getHp() == 20;

        gladiator1.rest();

        assert gladiator1.getHp() == 30;

        Knight knight0 = new Knight("Naruto", 252, 310, 99);
        Knight knight1 = new Knight("Sasuke", 250, 290, 111);
        Pet pet0 = new Pet("Giant Toad", 180);

        knight0.setPet(pet0);

        knight0.attackTogether(gladiator0);

        assert gladiator0.getHp() == 10;

        knight0.attackTogether(knight1);

        assert knight1.getHp() == 61;

        barbarian0.attack(barbarian0);
        gladiator0.attack("Gomu Gomu no pistol", gladiator0);
        knight0.attackTogether(knight0);
    }

    private static void testAttack() {
        Barbarian barbarian0 = new Barbarian("Dragonborn Whiterun", 250, 88, 10);
        Barbarian barbarian1 = new Barbarian("Ulfric Stormcloak", 300, 70, 15);

        barbarian0.attack(barbarian1);

        Gladiator gladiator0 = new Gladiator("Ja", 600, 300, 200);
        Gladiator gladiator1 = new Gladiator("Ga", 10000, 150, 700);

        Move move0 = new Move("Power strike", 20, 30);
        Move move1 = new Move("Thunder bolt", 30, 10);
        Move move2=  new Move("Meteor", 100, 3);
        gladiator0.addMove(move0);

        gladiator0.attack("Power strike", gladiator1);
        gladiator0.attack("Power strike", barbarian0);
        gladiator0.attack("Thunder bolt", barbarian1);
        gladiator1.attack(gladiator0);

        gladiator0.rest();

        barbarian0.attack(gladiator0);

        Knight knight0 = new Knight("nasuse", 1000, 500, 350);
        Knight testBot = new Knight("Teemo", 20000, 1, 300);

        knight0.attack(barbarian1);
        knight0.attack(gladiator0);

        knight0.addMove(move0);
        knight0.addMove(move1);
        knight0.addMove(move2);

        knight0.attack("Thunder bolt", testBot);
        knight0.attack("Thunder bolt", testBot);
        knight0.attack("Meteor", testBot);

        testBot.rest();
    }

    private static void testTooManyMoves() {
        Gladiator ja = new Gladiator("Ja", 700, 300, 150);
        Gladiator bot = new Gladiator("Teemo", 20000, 0, 20000);
        Move powerStrike = new Move("Power Strike", 5, 8);
        Move thunderBolt = new Move("Thunder bolt", 15, 3);
        Move glass = new Move("Set glass", 1, 10);
        Move eventHorizon = new Move("Event horizon", 200, 1);
        Move meteor = new Move("Meteor", 1000, 1);

        assert ja.addMove(powerStrike);
        assert !ja.addMove(powerStrike);
        assert ja.addMove(thunderBolt);
        assert ja.addMove(glass);
        assert ja.addMove(eventHorizon);
        assert !ja.addMove(meteor);

        for (int i = 0; i < 8; ++i) {
            ja.attack("Power Strike", bot);
        }

        int currentBotHp = bot.getHp();

        ja.attack("Power Strike", bot);
        assert currentBotHp == bot.getHp();

        ja.rest();
        ja.attack("Power Strike", bot);
        assert currentBotHp != bot.getHp();

        currentBotHp = bot.getHp();

        assert ja.removeMove("Power Strike");
        assert ja.addMove(meteor);

        ja.attack("Power strike", bot);
        assert currentBotHp == bot.getHp();

        ja.attack("Meteor", bot);
        assert currentBotHp != bot.getHp();
    }
}
