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
    private SimulationManager instance;

    public Tank(IntVector2D position) {
        super(position, SYMBOL, UNIT_TYPE, VISION, AOE, AP, MAX_HP, ATTACKABLE_TARGET);

        this.attackPosition = super.nullPosition;
        this.movePosition = super.nullPosition;
        this.attackablePositions = new ArrayList<>(ATTACKABLE_POINT_COUNT);
        this.moveRight = true;
    }

    public void onAttacked(int damage) {
        damage = this.isSiegeMode ? damage * 2 : damage;
        super.hp = Math.max(0, this.hp - damage);
    }

    public AttackIntent attack() {
        return new AttackIntent(this, this.attackPosition);
    }

    public void onSpawn() {
        this.instance = SimulationManager.getInstance();
        this.instance.registerThinkable(this);
        this.instance.registerMovable(this);
    }

    public boolean isAlive() {
        if (this.hp == 0) {
            this.instance.deleteThinkable(this);
            this.instance.deleteMovable(this);

            return false;
        }

        return this.hp > 0;
    }

    public void move() {
        if (this.movePosition.isSamePosition(super.nullPosition)) {
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

        this.position = new IntVector2D(currentX + movePointX, currentY + movePointY);

        if (this.position.getX() == instance.getNumColumns() - 1) {
            this.moveRight = false;
        } else if (this.position.getX() == 0) {
            this.moveRight = true;
        }
    }

    public void think(ArrayList<Unit> units) {
        this.attackPosition = super.nullPosition;
        this.movePosition = super.nullPosition;

        if (units.size() == 0) {
            setMovePosition();
            return;
        }

        if (units.size() > 0 && !this.isSiegeMode) {
            this.isSiegeMode = true;
            return;
        }

        setAttakablePositions();
        ArrayList<Unit> attackableUnits = new ArrayList<>();

        for (IntVector2D position : this.attackablePositions) {
            ArrayList<Unit> tmp = this.instance.getPositionUnitOrNull(position.getX(), position.getY());

            if (tmp == null || tmp.size() == 0) {
                continue;
            }

            for (Unit unit : tmp) {
                if (unit.getUnitType() == UnitType.GROUND) {
                    attackableUnits.add(unit);
                }
            }
        }

        attackableUnits.remove(this);

        if (attackableUnits.size() > 0) {
            assert this.isSiegeMode;

            ArrayList<Unit> removed = new ArrayList<>();
            int maxHp = Integer.MAX_VALUE;

            // set minimum hp
            for (Unit unit : attackableUnits) {
                if (maxHp > unit.getHp()) {
                    maxHp = unit.getHp();
                }
            }

            // check over minimum hp
            for (Unit unit : attackableUnits) {
                if (maxHp < unit.getHp()) {
                    removed.add(unit);
                }
            }

            for (Unit unit : removed) {
                attackableUnits.remove(unit);
            }

            removed = null;

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
            this.movePosition = new IntVector2D(instance.getNumColumns() - 1, this.position.getY());
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
