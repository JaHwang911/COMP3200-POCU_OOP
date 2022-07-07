package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class Tank extends Unit implements IThinkable, IMovable {
    private static final char SYMBOL = 'T';
    private static final UnitType UNIT_TYPE = UnitType.GROUND;
    private static final byte VISION = 3;
    private static final byte AOE = 1;
    private static final int AP = 8;
    private static final int ATTACKABLE_POINT_COUNT = 12;
    private static final int MAX_HP = 85;
    private static final AttackableTarget ATTACKABLE_TARGET = AttackableTarget.GROUND;

    private IntVector2D attackPosition;
    private IntVector2D movePosition;
    private boolean isSiegeMode;
    private boolean moveRight;
    private final ArrayList<IntVector2D> attackablePositions;

    public Tank(IntVector2D position) {
        super(position, SYMBOL, UNIT_TYPE, VISION, AOE, AP, MAX_HP, ATTACKABLE_TARGET);

        this.attackablePositions = new ArrayList<>(ATTACKABLE_POINT_COUNT);
        this.moveRight = true;
    }

    public void onAttacked(int damage) {

        damage = this.isSiegeMode ? damage * 2 : damage;
        super.hp = Math.max(0, this.hp - damage);
    }

    public void onAttacked(int damage, Unit attackerInfo) {
        printAttackedInfo(damage, attackerInfo);

        damage = this.isSiegeMode ? damage * 2 : damage;
        super.hp = Math.max(0, this.hp - damage);
    }

    public AttackIntent attack() {
        if (this.attackPosition == null) {
            return new AttackIntent(this, new IntVector2D(-1, -1));
        }

        return new AttackIntent(this, this.attackPosition);
    }

    public void onSpawn() {
        SimulationManager.getInstance().registerThinkable(this);
        SimulationManager.getInstance().registerMovable(this);
    }

    public boolean isAlive() {
        if (this.hp == 0) {
            SimulationManager.getInstance().deleteThinkable(this);
            SimulationManager.getInstance().deleteMovable(this);

            return false;
        }

        return this.hp > 0;
    }

    public void move() {
        if (this.movePosition == null) {
            return;
        }

        final int currentY = this.position.getY();
        final int currentX = this.position.getX();
        int movePointY = 0;
        int movePointX = 0;

        if (currentY != this.movePosition.getY()) {
            movePointY = currentY - this.movePosition.getY() > 0 ? -1 : 1;
        }

        if (movePointY == 0) {
            movePointX = currentX - this.movePosition.getX() > 0 ? -1 : 1;
        }

        this.position.setX(currentX + movePointX);
        this.position.setY(currentY + movePointY);

        if (this.position.getX() == 15) {
            this.moveRight = false;
        } else if (this.position.getX() == 0) {
            this.moveRight = true;
        }
    }

    public void think(ArrayList<Unit> units) {
        this.attackPosition = null;
        this.movePosition = null;

        if (units.size() == 0) {
            setMovePosition();
            return;
        }

        if (!this.isSiegeMode) {
            this.isSiegeMode = true;
            return;
        }

        setAttakablePositions();
        ArrayList<Unit> attackableUnits = new ArrayList<>();

        for (IntVector2D position : this.attackablePositions) {
            ArrayList<Unit> tmp = SimulationManager.getInstance().getPositionUnitOrNull(this, position.getX(), position.getY());

            if (tmp == null || tmp.size() == 0) {
                continue;
            }

            for (Unit unit : tmp) {
                if (unit.getUnitType() == UnitType.GROUND) {
                    attackableUnits.add(unit);
                }
            }
        }

        if (attackableUnits.size() > 0) {
            assert this.isSiegeMode;

            int minHp = Integer.MAX_VALUE;

            for (Unit unit : attackableUnits) {
                if (minHp > unit.getHp()) {
                    minHp = unit.getHp();
                }
            }

            final int minimumHp = minHp;
            attackableUnits.removeIf(unit -> (unit.getHp() > minimumHp));

            this.attackPosition = new IntVector2D(attackableUnits.get(0).position.getX(), attackableUnits.get(0).position.getY());
            return;
        }

        assert attackableUnits.size() == 0;

        if (this.isSiegeMode) {
            return;
        }

        setMovePosition();
    }

    private void setMovePosition() {
        if (this.isSiegeMode) {
            this.isSiegeMode = false;
            return;
        }

        if (this.moveRight) {
            this.movePosition = new IntVector2D(15, this.position.getY());
            return;
        }

        this.movePosition = new IntVector2D(0, this.position.getY());
    }

    private void setAttakablePositions() {
        this.attackablePositions.clear();

        int curX = this.position.getX();
        int curY = this.position.getY();

        this.attackablePositions.add(new IntVector2D(curX, curY - 2));
        this.attackablePositions.add(new IntVector2D(curX + 1, curY - 2));
        this.attackablePositions.add(new IntVector2D(curX + 2, curY - 1));
        this.attackablePositions.add(new IntVector2D(curX + 2, curY));
        this.attackablePositions.add(new IntVector2D(curX + 2, curY + 1));
        this.attackablePositions.add(new IntVector2D(curX + 1, curY + 2));
        this.attackablePositions.add(new IntVector2D(curX, curY + 2));
        this.attackablePositions.add(new IntVector2D(curX - 1, curY + 2));
        this.attackablePositions.add(new IntVector2D(curX - 2, curY + 1));
        this.attackablePositions.add(new IntVector2D(curX - 2, curY));
        this.attackablePositions.add(new IntVector2D(curX - 2, curY - 1));
        this.attackablePositions.add(new IntVector2D(curX - 1, curY - 2));
    }
}
