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

    public Marine(IntVector2D position) {
        super(position, SYMBOL, UNIT_TYPE, VISION, AOE, AP, MAX_HP, ATTACKABLE_TARGET);

        this.attackablePositions = new ArrayList<>();
    }

    public void onAttacked(int damage) {
        super.hp = Math.max(0, this.hp - damage);
    }

    public void onAttacked(int damage, Unit attackerInfo) {
        printAttackedInfo(damage, attackerInfo);
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
            int diff = currentY - this.movePosition.getY();

            if (diff > 0) {
                movePointY = -1;
            } else if (diff < 0) {
                movePointY = 1;
            }
        }

        if (movePointY == 0) {
            int diff = currentX - this.movePosition.getX();

            if (diff > 0) {
                movePointX = -1;
            } else if (diff < 0) {
                movePointX = 1;
            }
        }

        this.position.setX(currentX + movePointX);
        this.position.setY(currentY + movePointY);
    }

    public void think(ArrayList<Unit> units) {
        this.attackPosition = null;
        this.movePosition = null;

        if (units.size() == 0) {
            return;
        }

        setAttakablePositions();
        ArrayList<Unit> attackableUnits = new ArrayList<>();

        for (IntVector2D position : this.attackablePositions) {
            ArrayList<Unit> tmp = SimulationManager.getInstance().getPositionUnitOrNull(this, position.getX(), position.getY());

            if (tmp == null || tmp.size() == 0) {
                continue;
            }

            attackableUnits.addAll(tmp);
        }

        if (attackableUnits.size() > 0) {
            compareHp(attackableUnits);

            this.attackPosition = new IntVector2D(attackableUnits.get(0).position.getX(), attackableUnits.get(0).position.getY());
            return;
        }

        assert attackableUnits.size() == 0;

        ArrayList<Unit> removed = new ArrayList<>();
        ArrayList<Unit> targets = new ArrayList<>();
        int maxDistance = this.position.getDistance(units.get(0).getPosition());

        for (Unit unit : units) {
            int distance = this.position.getDistance(unit.position);

            if (maxDistance > distance) {
                maxDistance = distance;
            }

            targets.add(unit);
        }

        for (Unit unit : targets) {
            int distance = this.position.getDistance(unit.position);

            if (maxDistance < distance) {
                removed.add(unit);
            }
        }

        for (Unit unit : removed) {
            targets.remove(unit);
        }

        compareHp(targets);

        IntVector2D targetPosition = new IntVector2D(targets.get(0).position.getX(), targets.get(0).position.getY());

        if (targets.size() == 1) {
            this.movePosition = targetPosition;
            return;
        }

        for (int i = 1; i < targets.size(); ++i) {
            Unit tmpTarget = targets.get(i);

            if (!tmpTarget.position.isSamePosition(targetPosition)) {
                break;
            }

            if (i == targets.size() - 1 && tmpTarget.position.isSamePosition(targetPosition)) {
                this.movePosition = new IntVector2D(targetPosition.getX(), targetPosition.getY());
                return;
            }
        }

        // check clockwise
        this.movePosition = searchClockwise(maxDistance);
    }

    private void compareHp(ArrayList<Unit> outUnits) {
        int minHp = Integer.MAX_VALUE;

        for (Unit unit : outUnits) {
            if (minHp > unit.getHp()) {
                minHp = unit.getHp();
            }
        }

        final int minimumHp = minHp;
        outUnits.removeIf(unit -> (unit.getHp() > minimumHp));
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

    private IntVector2D searchClockwise(int distance) {
        IntVector2D ret = null;
        ArrayList<Unit> units = new ArrayList<>();
        ArrayList<IntVector2D> vectors;

        switch (distance) {
            case 2:
                vectors = getDistance2Vectors();
                break;
            case 3:
                vectors = getDistance3Vectors();
                break;
            case 4:
                vectors = getDistance4Vectors();
                break;
            default:
                assert false : "Out of vision range";
                return null;
        }

        for (IntVector2D vector : vectors) {
            var tmp = SimulationManager.getInstance().getPositionUnitOrNull(this, vector.getX(), vector.getY());

            if (tmp != null && tmp.size() > 0) {
                units.addAll(tmp);
            }
        }

        assert units.size() > 0;
        compareHp(units);

        ret = new IntVector2D(units.get(0).position.getX(), units.get(0).position.getY());

        return ret;
    }

    private ArrayList<IntVector2D> getDistance2Vectors() {
        final int maxVectorsNum = 8;
        final int currentPositionX = this.position.getX();
        final int currentPositionY = this.position.getY();

        IntVector2D vector0 = new IntVector2D(currentPositionX, currentPositionY - 2);
        IntVector2D vector1 = new IntVector2D(currentPositionX + 1, currentPositionY - 1);
        IntVector2D vector2 = new IntVector2D(currentPositionX + 2, currentPositionY);
        IntVector2D vector3 = new IntVector2D(currentPositionX + 1, currentPositionY + 1);
        IntVector2D vector4 = new IntVector2D(currentPositionX, currentPositionY + 2);
        IntVector2D vector5 = new IntVector2D(currentPositionX - 1, currentPositionY + 1);
        IntVector2D vector6 = new IntVector2D(currentPositionX - 2, currentPositionY);
        IntVector2D vector7 = new IntVector2D(currentPositionX - 1, currentPositionY - 1);

        ArrayList<IntVector2D> vectors = new ArrayList<>(maxVectorsNum);
        vectors.add(vector0);
        vectors.add(vector1);
        vectors.add(vector2);
        vectors.add(vector3);
        vectors.add(vector4);
        vectors.add(vector5);
        vectors.add(vector6);
        vectors.add(vector7);

        return vectors;
    }

    private ArrayList<IntVector2D> getDistance3Vectors() {
        final int maxVectorsNum = 8;
        final int currentPositionX = this.position.getX();
        final int currentPositionY = this.position.getY();

        IntVector2D vector0 = new IntVector2D(currentPositionX + 1, currentPositionY - 2);
        IntVector2D vector1 = new IntVector2D(currentPositionX + 2, currentPositionY - 1);
        IntVector2D vector2 = new IntVector2D(currentPositionX + 2, currentPositionY + 1);
        IntVector2D vector3 = new IntVector2D(currentPositionX + 1, currentPositionY + 2);
        IntVector2D vector4 = new IntVector2D(currentPositionX - 1, currentPositionY + 2);
        IntVector2D vector5 = new IntVector2D(currentPositionX - 2, currentPositionY + 1);
        IntVector2D vector6 = new IntVector2D(currentPositionX - 2, currentPositionY - 1);
        IntVector2D vector7 = new IntVector2D(currentPositionX - 1, currentPositionY - 2);

        ArrayList<IntVector2D> vectors = new ArrayList<>(maxVectorsNum);
        vectors.add(vector0);
        vectors.add(vector1);
        vectors.add(vector2);
        vectors.add(vector3);
        vectors.add(vector4);
        vectors.add(vector5);
        vectors.add(vector6);
        vectors.add(vector7);

        return vectors;
    }

    private ArrayList<IntVector2D> getDistance4Vectors() {
        final int maxVectorsNum = 4;
        final int currentPositionX = this.position.getX();
        final int currentPositionY = this.position.getY();

        IntVector2D vector0 = new IntVector2D(currentPositionX + 2, currentPositionY - 2);
        IntVector2D vector1 = new IntVector2D(currentPositionX + 2, currentPositionY + 2);
        IntVector2D vector2 = new IntVector2D(currentPositionX - 2, currentPositionY + 2);
        IntVector2D vector3 = new IntVector2D(currentPositionX - 2, currentPositionY - 2);

        ArrayList<IntVector2D> vectors = new ArrayList<>(maxVectorsNum);
        vectors.add(vector0);
        vectors.add(vector1);
        vectors.add(vector2);
        vectors.add(vector3);

        return vectors;
    }
}
