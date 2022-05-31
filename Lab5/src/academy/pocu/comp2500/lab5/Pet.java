package academy.pocu.comp2500.lab5;

public class Pet {
    private final String name;
    private final int attack;

    public Pet(String name, int attack) {
        this.name = name;
        this.attack = attack;
    }

    public String getName() {
        return this.name;
    }

    public int getAttack() {
        return this.attack;
    }
}
