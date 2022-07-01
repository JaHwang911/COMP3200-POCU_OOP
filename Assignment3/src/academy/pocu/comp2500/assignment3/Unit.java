package academy.pocu.comp2500.assignment3;

public abstract class Unit {
    private int hp;
    private final int ap;
    private IntVector2D position;
    private final char symbol;
    private boolean spawn;

    public Unit(final IntVector2D position, final int hp, final int ap, final char symbol) {
        this.position = position;
        this.hp = hp;
        this.ap = ap;
        this.symbol = symbol;
    }

    public IntVector2D getPosition() {
        return this.position;
    }

    public int getHp() {
        return this.hp;
    }

    public int getAp() {
        return this.ap;
    }

    public abstract AttackIntent attack();

    public void onAttacked(int damage) {
        this.hp -= Math.min(damage, this.hp);
    }

    public void onSpawn() {
        this.spawn = true;
    }

    public char getSymbol() {
        return this.symbol;
    }
}
