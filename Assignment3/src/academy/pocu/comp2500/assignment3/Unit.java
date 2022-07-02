package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public abstract class Unit {
    protected int hp;
    protected final int ap;
    protected IntVector2D position;
    protected final char symbol;
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

    public abstract void onAttacked(int damage);

    public abstract void move(int x, int y);

    public abstract Turn think(ArrayList<Unit> units);

    public void onSpawn() {
        this.spawn = true;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public abstract byte getVision();

    public abstract AttackableTarget getAttackableTarget();

    public abstract UnitType getUnitType();
}
