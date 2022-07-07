package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public abstract class Unit {
    protected int hp;
    protected final int ap;
    protected final IntVector2D position;
    protected final char symbol;
    protected final UnitType unitType;
    protected final byte vision;
    protected final byte aoe;
    protected final AttackableTarget attackableTargetType;

    public Unit(final IntVector2D position, final char symbol, final UnitType unitType, final byte vision, final byte aoe, final int ap, final int hp, final AttackableTarget attackableTarget) {
        this.position = position;
        this.symbol = symbol;
        this.unitType = unitType;
        this.vision = vision;
        this.aoe = aoe;
        this.ap = ap;
        this.hp = hp;
        this.attackableTargetType = attackableTarget;
    }

    public IntVector2D getPosition() {
        return this.position;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public UnitType getUnitType() {
        return this.unitType;
    }

    public byte getVision() {
        return this.vision;
    }

    public byte getAoe() {
        return this.aoe;
    }

    public int getAp() {
        return this.ap;
    }

    public int getHp() {
        return this.hp;
    }

    public AttackableTarget getAttackableTarget() {
        return this.attackableTargetType;
    }

    public abstract void onSpawn();

    public abstract boolean isAlive();

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
            tmpUnits = instance.getPositionUnitOrNull(null, x++, y);

            if (tmpUnits.size() > 0) {
                return new IntVector2D(tmpUnits.get(0).position.getX(), tmpUnits.get(0).position.getY());
            }
        }

        x = currentPositionX + distance - 1;
        y = currentPositionY + 1;

        for (; y <= currentPositionY + distance; ++y) {
            tmpUnits = instance.getPositionUnitOrNull(null, x--, y);

            if (tmpUnits != null && tmpUnits.size() > 0) {
                return new IntVector2D(tmpUnits.get(0).position.getX(), tmpUnits.get(0).position.getY());
            }
        }

        x = currentPositionX - 1;
        y = currentPositionY + distance - 1;

        for (; y >= currentPositionY; --y) {
            tmpUnits = instance.getPositionUnitOrNull(null, x--, y);

            if (tmpUnits.size() > 0) {
                return new IntVector2D(tmpUnits.get(0).position.getX(), tmpUnits.get(0).position.getY());
            }
        }

        x = currentPositionX - distance + 1;
        y = currentPositionY - 1;

        for (; y > currentPositionY - distance; --y) {
            tmpUnits = instance.getPositionUnitOrNull(null, x++, y);

            if (tmpUnits.size() > 0) {
                return new IntVector2D(tmpUnits.get(0).position.getX(), tmpUnits.get(0).position.getY());
            }
        }

        assert false : "wrong check clockwise";
        return null;
    }
}
