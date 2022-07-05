package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class Tank extends Unit implements IThinkable, IMovable{
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
    private ArrayList<IntVector2D> attackablePoint;
    private SimulationManager instance;

    public Tank(IntVector2D position) {
        super(position, MAX_HP, AP, SYMBOL);

        this.attackPosition = super.nullPosition;
        this.movePosition = super.nullPosition;
        this.attackablePoint = new ArrayList<>(ATTACKABLE_POINT_COUNT);
        this.moveRight = true;
    }

    public byte getVision() {
        return VISION;
    }

    public UnitType getUnitType() {
        return UNIT_TYPE;
    }


    public AttackableTarget getAttackableTarget() {
        return ATTACKABLE_TARGET;
    }

    public byte getAoe() {
        return AOE;
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

        this.position.setY(currentY + movePointY);
        this.position.setX(currentX + movePointX);

        if (this.position.getX() == instance.getNumColumns() - 1) {
            this.moveRight = false;
        } else if (this.position.getX() == 0) {
            this.moveRight = true;
        }
    }

    public void think(ArrayList<Unit> units) {
        units.remove(this);

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

        ArrayList<Unit> attackableUnits = new ArrayList<>();

        setAttakablePoints();

        for (IntVector2D point : this.attackablePoint) {
            ArrayList<Unit> tmp = this.instance.getPositionUnitOrNull(point.getX(), point.getY());

            if (tmp == null || tmp.size() == 0) {
                continue;
            }

            attackableUnits.addAll(tmp);
        }

        if (attackableUnits.size() > 0) {
            assert this.isSiegeMode;

            Unit target = attackableUnits.get(0);

            for (int i = 1; i < attackableUnits.size(); ++i) {
                Unit tmpTarget = attackableUnits.get(i);

                if (target.getHp() > tmpTarget.getHp()) {
                    attackableUnits.remove(target);
                    target = tmpTarget;
                }
            }

            if (attackableUnits.size() == 1) {
                this.attackPosition = new IntVector2D(target.position.getX(), target.position.getY());
                return;
            }

            // check attackable direction
            for (IntVector2D point : this.attackablePoint) {
                ArrayList<Unit> tmp = this.instance.getPositionUnitOrNull(point.getX(), point.getY());

                if (tmp == null || tmp.size() == 0) {
                    continue;
                }

                this.attackPosition = point;
            }
        }

        assert attackableUnits.size() == 0;
        if (units.size() > 0) {
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

    private void setAttakablePoints() {
        int curX = this.position.getX();
        int curY = this.position.getY();

        this.attackablePoint.add(new IntVector2D(curX, curY - 2));
        this.attackablePoint.add(new IntVector2D(curX + 1, curY - 2));
        this.attackablePoint.add(new IntVector2D(curX + 2, curY - 1));
        this.attackablePoint.add(new IntVector2D(curX + 2, curY));
        this.attackablePoint.add(new IntVector2D(curX + 2, curY + 1));
        this.attackablePoint.add(new IntVector2D(curX + 1, curY + 2));
        this.attackablePoint.add(new IntVector2D(curX, curY + 2));
        this.attackablePoint.add(new IntVector2D(curX - 1, curY + 2));
        this.attackablePoint.add(new IntVector2D(curX - 2, curY + 1));
        this.attackablePoint.add(new IntVector2D(curX - 2, curY));
        this.attackablePoint.add(new IntVector2D(curX - 2, curY - 1));
        this.attackablePoint.add(new IntVector2D(curX - 1, curY - 2));
    }
}
