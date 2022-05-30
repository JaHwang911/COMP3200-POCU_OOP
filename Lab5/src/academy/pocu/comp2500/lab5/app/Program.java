package academy.pocu.comp2500.lab5.app;

import academy.pocu.comp2500.lab5.Barbarian;
import academy.pocu.comp2500.lab5.Gladiator;
import academy.pocu.comp2500.lab5.Move;

public class Program {

    public static void main(String[] args) {
        Barbarian barbarian0 = new Barbarian("Dragonborn Whiterun", 250, 88, 10);
        Barbarian barbarian1 = new Barbarian("Ulfric Stormcloak", 300, 70, 15);
        Gladiator gladiator0 = new Gladiator("Ja", 600, 300, 200);
        Gladiator gladiator1 = new Gladiator("Ja", 10000, 150, 700);

        barbarian0.attack(barbarian1);
        System.out.println(barbarian1.getHp());

        Move move = new Move("Power strike", 20, 3);

        gladiator0.addMove(move);
        gladiator0.attack("Power strike", gladiator1);
        int gladiator1Hp = gladiator1.getHp();

        gladiator0.attack("Power strike", barbarian0);
        gladiator0.attack("Power strike", barbarian1);
        gladiator0.attack("Power strike", gladiator1);
        assert gladiator1Hp == gladiator1.getHp();

        System.out.println("No prob");
    }
}
