package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public abstract class Unit {
    protected int hp;
    protected final int ap;
    protected IntVector2D position;
    protected final char symbol;
    protected final IntVector2D nullPosition = new IntVector2D(-1, -1);

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

    public char getSymbol() {
        return this.symbol;
    }

    public abstract void onSpawn();

    public abstract byte getVision();

    public abstract AttackableTarget getAttackableTarget();

    public abstract UnitType getUnitType();

    public abstract AttackIntent attack();

    public abstract void onAttacked(int damage);
}
