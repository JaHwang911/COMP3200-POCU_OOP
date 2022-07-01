package academy.pocu.comp2500.assignment3;

public class AttackIntent {
    private final Unit attacker;
    private IntVector2D position;

    public AttackIntent(Unit attacker, IntVector2D position) {
        this.attacker = attacker;
        this.position = position;
    }

    public Unit getAttacker() {
        return this.attacker;
    }

    public int getDamage() {
        return this.attacker.getAp();
    }

    public IntVector2D getPosition() {
        return this.position;
    }
}
