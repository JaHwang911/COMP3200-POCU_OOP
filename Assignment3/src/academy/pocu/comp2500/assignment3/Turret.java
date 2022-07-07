package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class Turret extends Unit implements IThinkable {
    private static final char SYMBOL = 'U';
    private static final UnitType UNIT_TYPE = UnitType.GROUND;
    private static final byte VISION = 2;
    private static final byte AOE = 0;
    private static final int AP = 7;
    private static final int MAX_HP = 99;
    private static final AttackableTarget ATTACKABLE_TARGET = AttackableTarget.AIR;

    private IntVector2D attackPosition;
    private final ArrayList<IntVector2D> attackablePositions;
    private SimulationManager instance;

    public Turret(IntVector2D position) {
        super(position, SYMBOL, UNIT_TYPE, VISION, AOE, AP, MAX_HP, ATTACKABLE_TARGET);

        this.attackablePositions = new ArrayList<>();
    }

    public void onAttacked(int damage) {
        super.hp = Math.max(0, this.hp - damage);
    }

    public AttackIntent attack() {
        if (this.attackPosition == null) {
            return new AttackIntent(this, new IntVector2D(-1, -1));
        }
        return new AttackIntent(this, this.attackPosition);
    }

    public void onSpawn() {
        this.instance = SimulationManager.getInstance();
        this.instance.registerThinkable(this);
    }

    public boolean isAlive() {
        if (this.hp == 0) {
            this.instance.deleteThinkable(this);

            return false;
        }

        return this.hp > 0;
    }

    public void think(ArrayList<Unit> units) {
        this.attackPosition = null;

        if (units.size() == 0) {
            return;
        }

        setAttackablePositions();
        ArrayList<Unit> attackableUnits = new ArrayList<>();

        for (IntVector2D position : this.attackablePositions) {
            ArrayList<Unit> tmp = this.instance.getPositionUnitOrNull(position.getX(), position.getY());

            if (tmp == null || tmp.size() == 0) {
                continue;
            }

            for (Unit unit : tmp) {
                if (unit.getUnitType() == UnitType.AIR) {
                    attackableUnits.add(unit);
                }
            }
        }

        if (attackableUnits.size() > 0) {
            int minHp = Integer.MAX_VALUE;

            for (Unit unit : attackableUnits) {
                if (minHp > unit.getHp()) {
                    minHp = unit.getHp();
                }
            }

            final int minimumHp = minHp;
            attackableUnits.removeIf(unit -> (unit.getHp() > minimumHp));

            this.attackPosition = new IntVector2D(attackableUnits.get(0).position.getX(), attackableUnits.get(0).position.getY());
        }
    }

    private void setAttackablePositions() {
        this.attackablePositions.clear();

        int curX = this.position.getX();
        int curY = this.position.getY();

        this.attackablePositions.add(new IntVector2D(curX, curY));
        this.attackablePositions.add(new IntVector2D(curX, curY - 1));
        this.attackablePositions.add(new IntVector2D(curX + 1, curY - 1));
        this.attackablePositions.add(new IntVector2D(curX + 1, curY));
        this.attackablePositions.add(new IntVector2D(curX + 1, curY + 1));
        this.attackablePositions.add(new IntVector2D(curX, curY + 1));
        this.attackablePositions.add(new IntVector2D(curX - 1, curY + 1));
        this.attackablePositions.add(new IntVector2D(curX - 1, curY));
        this.attackablePositions.add(new IntVector2D(curX - 1, curY - 1));
    }
}
