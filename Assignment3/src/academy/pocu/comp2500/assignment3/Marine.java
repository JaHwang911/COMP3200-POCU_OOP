package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class Marine extends Unit implements IMovable, IThinkable {
    private static final char SYMBOL = 'M';
    private static final UnitType UNIT_TYPE = UnitType.GROUND;
    private static final byte VISION = 2;
    private static final byte AOE = 0;
    private static final int AP = 6;
    private static final int MAX_HP = 35;
    private static final AttackableTarget ATTACKABLE_TARGET = AttackableTarget.ALL;

    private IntVector2D attackPosition;
    private IntVector2D movePosition;
    private final ArrayList<IntVector2D> attackablePositions;
    private SimulationManager instance;

    public Marine(IntVector2D position) {
        super(position, MAX_HP, AP, SYMBOL);

        this.attackPosition = super.nullPosition;
        this.movePosition = super.nullPosition;
        this.attackablePositions = new ArrayList<>();
    }

    public UnitType getUnitType() {
        return UNIT_TYPE;
    }

    public byte getVision() {
        return VISION;
    }

    public AttackableTarget getAttackableTarget() {
        return ATTACKABLE_TARGET;
    }

    public byte getAoe() {
        return AOE;
    }

    public void onAttacked(int damage) {
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
    }

    // 매개변수도 없애고 이동이 필요할 때 호출 하는 방법으로
    public void think(ArrayList<Unit> units) {
        units.remove(this);

        this.attackPosition = super.nullPosition;
        this.movePosition = super.nullPosition;

        if (units.size() == 0) {
            return;
        }

        ArrayList<Unit> attackableUnits = new ArrayList<>();

        for (IntVector2D position : this.attackablePositions) {
            ArrayList<Unit> tmp = this.instance.getPositionUnitOrNull(position.getX(), position.getY());

            if (tmp == null || tmp.size() == 0) {
                continue;
            }

            attackableUnits.addAll(tmp);
        }

        if (attackableUnits.size() > 0) {
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

            IntVector2D samePosition = new IntVector2D(attackableUnits.get(0).position.getX(), attackableUnits.get(0).position.getY());

            if (attackableUnits.size() == 1) {
                this.attackPosition = samePosition;
                return;
            }

            for (int i = 1; i < attackableUnits.size(); ++i) {
                Unit tmpTarget = attackableUnits.get(i);

                if (!tmpTarget.position.isSamePosition(samePosition)) {
                    break;
                }

                if (i == attackableUnits.size() - 1 && tmpTarget.position.isSamePosition(samePosition)) {
                    this.attackPosition = new IntVector2D(samePosition.getX(), samePosition.getY());
                    return;
                }
            }

            this.attackPosition = samePosition;
        }

        assert attackableUnits.size() == 0;

        ArrayList<Unit> targets = new ArrayList<>();
        int maxDistance = this.position.getDistance(units.get(0).getPosition());
        targets.add(units.get(0));

        for (int i = 1; i < units.size(); ++i) {
            Unit unit = units.get(i);
            int distance = this.position.getDistance(unit.position);

            if (maxDistance > distance) {
                maxDistance = distance;
                targets.clear();
                targets.add(unit);
                continue;
            }

            if (maxDistance == distance) {
                Unit tmpTarget = targets.get(0);

                if (tmpTarget.hp > unit.hp) {
                    targets.clear();
                } else if (tmpTarget.position.isSamePosition(unit.position)) {
                    continue;
                }

                targets.add(unit);
            }
        }

        if (targets.size() == 1) {
            this.movePosition = new IntVector2D(targets.get(0).position.getX(), targets.get(0).position.getY());
            return;
        }

        // check clockwise
        this.movePosition = searchClockwise(maxDistance);
        assert !this.movePosition.isSamePosition(nullPosition);
    }

    private void setAttakablePositions() {
        this.attackablePositions.clear();

        int curX = this.position.getX();
        int curY = this.position.getY();

        this.attackablePositions.add(new IntVector2D(curX, curY));
        this.attackablePositions.add(new IntVector2D(curX, curY - 1));
        this.attackablePositions.add(new IntVector2D(curX + 1, curY));
        this.attackablePositions.add(new IntVector2D(curX, curY + 1));
        this.attackablePositions.add(new IntVector2D(curX - 1, curY));
    }
}
