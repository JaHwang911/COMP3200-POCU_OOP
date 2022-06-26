package academy.pocu.comp2500.assignment3;

public class Unit {
    private int hp;
    private int ap;
    private int maxHp;
    private IntVector2D position;
    private char symbol;
    private boolean spawn;

    public Unit(final IntVector2D position, final int hp, final int ap, final char symbol) {
        this.position = position;
        this.hp = hp;
        this.maxHp = hp;
        this.ap = ap;
        this.symbol = symbol;
    }

    public final IntVector2D getPosition() {
        return this.position;
    }

    public final int getHp() {
        return this.hp;
    }

    public AttackIntent attack() {
        return null;
    }

    public final void onAttacked(int damage) {
        this.hp -= Math.min(damage, this.hp);
    }

    public void onSpawn() {
        this.spawn = true;
    }

    public char getSymbol() {
        return this.symbol;
    }
}
