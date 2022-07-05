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

    public abstract boolean isAlive();

    public abstract UnitType getUnitType();

    public abstract byte getVision();

    public abstract byte getAoe();

    public abstract AttackableTarget getAttackableTarget();

    public abstract AttackIntent attack();

    public abstract void onAttacked(int damage);

    protected IntVector2D searchClockwise(int distance) {
        SimulationManager instance = SimulationManager.getInstance();
        final int currentPositionX = this.position.getX();
        final int currentPositionY = this.position.getY();
        int x = currentPositionX;
        int y = currentPositionY - distance;
        ArrayList<Unit> tmpUnits;

        for (; y <= currentPositionY; ++y) {
            tmpUnits = instance.getPositionUnitOrNull(x++, y);

            if (tmpUnits.size() > 0) {
                return new IntVector2D(tmpUnits.get(0).position.getX(), tmpUnits.get(0).position.getY());
            }
        }

        x = currentPositionX + distance - 1;
        y = currentPositionY + 1;

        for (; y <= currentPositionY + distance; ++y) {
            tmpUnits = instance.getPositionUnitOrNull(x--, y);

            if (tmpUnits != null && tmpUnits.size() > 0) {
                return new IntVector2D(tmpUnits.get(0).position.getX(), tmpUnits.get(0).position.getY());
            }
        }

        x = currentPositionX - 1;
        y = currentPositionY + distance - 1;

        for (; y >= currentPositionY; --y) {
            tmpUnits = instance.getPositionUnitOrNull(x--, y);

            if (tmpUnits.size() > 0) {
                return new IntVector2D(tmpUnits.get(0).position.getX(), tmpUnits.get(0).position.getY());
            }
        }

        x = currentPositionX - distance + 1;
        y = currentPositionY - 1;

        for (; y > currentPositionY - distance; --y) {
            tmpUnits = instance.getPositionUnitOrNull(x++, y);

            if (tmpUnits.size() > 0) {
                return new IntVector2D(tmpUnits.get(0).position.getX(), tmpUnits.get(0).position.getY());
            }
        }

        assert false : "wrong check clockwise";
        return null;
    }
}
