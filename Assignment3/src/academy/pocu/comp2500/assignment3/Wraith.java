package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class Wraith extends Unit implements IThinkable, IMovable {
    private static final char SYMBOL = 'W';
    private static final UnitType UNIT_TYPE = UnitType.AIR;
    private static final byte VISION = 4;
    private static final byte AOE = 0;
    private static final int AP = 6;
    private static final int MAX_HP = 80;
    private static final AttackableTarget ATTACKABLE_TARGET = AttackableTarget.ALL;

    private final IntVector2D startPosition;
    private IntVector2D attackPosition;
    private IntVector2D movePosition;
    private final ArrayList<IntVector2D> attackablePositions;
    private boolean hasShield;
    private boolean attacked;

    public Wraith(IntVector2D position) {
        super(position, SYMBOL, UNIT_TYPE, VISION, AOE, AP, MAX_HP, ATTACKABLE_TARGET);


        this.startPosition = new IntVector2D(position.getX(), position.getY());
        this.attackablePositions = new ArrayList<>();
        this.hasShield = true;
    }

    public void onAttacked(int damage) {
        if (this.hasShield) {
            this.attacked = true;
            return;
        }

        super.hp = Math.max(0, this.hp - damage);
    }

    public void onAttacked(int damage, Unit attackerInfo) {
        printAttackedInfo(damage, attackerInfo);
        if (this.hasShield) {
            this.attacked = true;
            return;
        }

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
        // 실수로 프레임이 끝나는 부분이 아닌 곳에서 호출하면 레이스의 쉴드에 문제 생김 어떻게 해결?
        if (this.hp == 0) {
            SimulationManager.getInstance().deleteThinkable(this);
            SimulationManager.getInstance().deleteMovable(this);

            return false;
        }

        if (this.attacked) {
            this.hasShield = false;
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
            this.movePosition = this.startPosition;
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
                if (unit.getUnitType() == UnitType.AIR) {
                    attackableUnits.add(unit);
                }
            }
        }

        if (attackableUnits.size() == 0) {
            for (IntVector2D position : this.attackablePositions) {
                ArrayList<Unit> tmp = SimulationManager.getInstance().getPositionUnitOrNull(this, position.getX(), position.getY());

                if (tmp == null || tmp.size() == 0) {
                    continue;
                }

                attackableUnits.addAll(tmp);
            }
        }

        if (attackableUnits.size() > 0) {
            compareHp(attackableUnits);

            this.attackPosition = new IntVector2D(attackableUnits.get(0).position.getX(), attackableUnits.get(0).position.getY());
            return;
        }

        assert attackableUnits.size() == 0;

        ArrayList<Unit> targets = new ArrayList<>();

        for (Unit unit : units) {
            if (unit.getUnitType() == UnitType.AIR) {
                targets.add(unit);
            }
        }

        if (targets.size() == 0) {
            targets.addAll(units);
        }

        int minDistance = this.position.getDistance(targets.get(0).getPosition());

        for (Unit unit : targets) {
            int distance = this.position.getDistance(unit.position);

            if (minDistance > distance) {
                minDistance = distance;
            }
        }

        final int minimumDistance = minDistance;

        targets.removeIf(unit -> (minimumDistance < this.position.getDistance(unit.position)));
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
        // searchClockwise가 남은 유닛이 지상과 공중이 섞여있을 때 문제 될 수 있음
        this.movePosition = searchClockwise(minDistance);
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

    /*private IntVector2D searchClockwise(int distance) {
        IntVector2D ret = null;
        final int currentPositionX = this.position.getX();
        final int currentPositionY = this.position.getY();
        int x = currentPositionX;
        int y = currentPositionY - distance;
        ArrayList<Unit> units = new ArrayList<>();

        for (; y <= currentPositionY; ++y) {
            var tmp = SimulationManager.getInstance().getPositionUnitOrNull(this, x++, y);

            if (tmp != null && tmp.size() > 0) {
                units.addAll(tmp);
            }
        }

        x = currentPositionX + distance - 1;
        y = currentPositionY + 1;

        for (; y <= currentPositionY + distance; ++y) {
            var tmp = SimulationManager.getInstance().getPositionUnitOrNull(this, x--, y);

            if (tmp != null && tmp.size() > 0) {
                units.addAll(tmp);
            }
        }

        x = currentPositionX - 1;
        y = currentPositionY + distance - 1;

        for (; y >= currentPositionY; --y) {
            var tmp = SimulationManager.getInstance().getPositionUnitOrNull(this, x--, y);

            if (tmp != null && tmp.size() > 0) {
                units.addAll(tmp);
            }
        }

        x = currentPositionX - distance + 1;
        y = currentPositionY - 1;

        for (; y > currentPositionY - distance; --y) {
            var tmp = SimulationManager.getInstance().getPositionUnitOrNull(this, x++, y);

            if (tmp != null && tmp.size() > 0) {
                units.addAll(tmp);
            }
        }

        assert units.size() > 0;

        for (Unit unit : units) {
            if (unit.unitType == UnitType.AIR) {
                ret = new IntVector2D(unit.position.getX(), unit.position.getY());
                break;
            }
        }

        compareHp(units);

        if (ret == null) {
            ret = new IntVector2D(units.get(0).position.getX(), units.get(0).position.getY());
        }

        return ret;
    }*/

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
            case 5:
                vectors = getDistance5Vectors();
                break;
            case 6:
                vectors = getDistance6Vectors();
                break;
            case 7:
                vectors = getDistance7Vectors();
                break;
            case 8:
                vectors = getDistance8Vectors();
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

        for (Unit unit : units) {
            if (unit.unitType == UnitType.AIR) {
                ret = new IntVector2D(unit.position.getX(), unit.position.getY());
                break;
            }
        }

        compareHp(units);

        if (ret == null) {
            ret = new IntVector2D(units.get(0).position.getX(), units.get(0).position.getY());
        }

        return ret;
    }

    private ArrayList<IntVector2D> getDistance2Vectors() {
        final int MAX_VECTORS_NUM = 8;
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

        ArrayList<IntVector2D> vectors = new ArrayList<>(MAX_VECTORS_NUM);
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
        final int MAX_VECTORS_NUM = 12;
        final int currentPositionX = this.position.getX();
        final int currentPositionY = this.position.getY();

        IntVector2D vector0 = new IntVector2D(currentPositionX, currentPositionY - 3);
        IntVector2D vector1 = new IntVector2D(currentPositionX + 1, currentPositionY - 2);
        IntVector2D vector2 = new IntVector2D(currentPositionX + 2, currentPositionY - 1);
        IntVector2D vector3 = new IntVector2D(currentPositionX + 3, currentPositionY);
        IntVector2D vector4 = new IntVector2D(currentPositionX + 2, currentPositionY + 1);
        IntVector2D vector5 = new IntVector2D(currentPositionX + 1, currentPositionY + 2);
        IntVector2D vector6 = new IntVector2D(currentPositionX, currentPositionY + 3);
        IntVector2D vector7 = new IntVector2D(currentPositionX - 1, currentPositionY + 2);
        IntVector2D vector8 = new IntVector2D(currentPositionX - 2, currentPositionY + 1);
        IntVector2D vector9 = new IntVector2D(currentPositionX - 3, currentPositionY);
        IntVector2D vector10 = new IntVector2D(currentPositionX - 2, currentPositionY - 1);
        IntVector2D vector11 = new IntVector2D(currentPositionX - 1, currentPositionY - 2);


        ArrayList<IntVector2D> vectors = new ArrayList<>(MAX_VECTORS_NUM);
        vectors.add(vector0);
        vectors.add(vector1);
        vectors.add(vector2);
        vectors.add(vector3);
        vectors.add(vector4);
        vectors.add(vector5);
        vectors.add(vector6);
        vectors.add(vector7);
        vectors.add(vector8);
        vectors.add(vector9);
        vectors.add(vector10);
        vectors.add(vector11);

        return vectors;
    }

    private ArrayList<IntVector2D> getDistance4Vectors() {
        final int MAX_VECTORS_NUM = 16;
        final int currentPositionX = this.position.getX();
        final int currentPositionY = this.position.getY();

        IntVector2D vector0 = new IntVector2D(currentPositionX, currentPositionY - 4);
        IntVector2D vector1 = new IntVector2D(currentPositionX + 1, currentPositionY - 3);
        IntVector2D vector2 = new IntVector2D(currentPositionX + 2, currentPositionY - 2);
        IntVector2D vector3 = new IntVector2D(currentPositionX + 3, currentPositionY - 1);
        IntVector2D vector4 = new IntVector2D(currentPositionX + 4, currentPositionY);
        IntVector2D vector5 = new IntVector2D(currentPositionX + 3, currentPositionY + 1);
        IntVector2D vector6 = new IntVector2D(currentPositionX + 2, currentPositionY + 2);
        IntVector2D vector7 = new IntVector2D(currentPositionX + 1, currentPositionY + 3);
        IntVector2D vector8 = new IntVector2D(currentPositionX, currentPositionY + 4);
        IntVector2D vector9 = new IntVector2D(currentPositionX - 1, currentPositionY + 3);
        IntVector2D vector10 = new IntVector2D(currentPositionX - 2, currentPositionY + 2);
        IntVector2D vector11 = new IntVector2D(currentPositionX - 3, currentPositionY + 1);
        IntVector2D vector12 = new IntVector2D(currentPositionX - 4, currentPositionY);
        IntVector2D vector13 = new IntVector2D(currentPositionX - 3, currentPositionY - 1);
        IntVector2D vector14 = new IntVector2D(currentPositionX - 2, currentPositionY - 2);
        IntVector2D vector15 = new IntVector2D(currentPositionX - 1, currentPositionY - 3);

        ArrayList<IntVector2D> vectors = new ArrayList<>(MAX_VECTORS_NUM);
        vectors.add(vector0);
        vectors.add(vector1);
        vectors.add(vector2);
        vectors.add(vector3);
        vectors.add(vector4);
        vectors.add(vector5);
        vectors.add(vector6);
        vectors.add(vector7);
        vectors.add(vector8);
        vectors.add(vector9);
        vectors.add(vector10);
        vectors.add(vector11);
        vectors.add(vector12);
        vectors.add(vector13);
        vectors.add(vector14);
        vectors.add(vector15);

        return vectors;
    }

    private ArrayList<IntVector2D> getDistance5Vectors() {
        final int MAX_VECTORS_NUM = 16;
        final int currentPositionX = this.position.getX();
        final int currentPositionY = this.position.getY();

        IntVector2D vector0 = new IntVector2D(currentPositionX + 1, currentPositionY - 4);
        IntVector2D vector1 = new IntVector2D(currentPositionX + 2, currentPositionY - 3);
        IntVector2D vector2 = new IntVector2D(currentPositionX + 3, currentPositionY - 2);
        IntVector2D vector3 = new IntVector2D(currentPositionX + 4, currentPositionY - 1);
        IntVector2D vector4 = new IntVector2D(currentPositionX + 4, currentPositionY + 1);
        IntVector2D vector5 = new IntVector2D(currentPositionX + 3, currentPositionY + 2);
        IntVector2D vector6 = new IntVector2D(currentPositionX + 2, currentPositionY + 3);
        IntVector2D vector7 = new IntVector2D(currentPositionX + 1, currentPositionY + 4);
        IntVector2D vector8 = new IntVector2D(currentPositionX - 1, currentPositionY + 4);
        IntVector2D vector9 = new IntVector2D(currentPositionX - 2, currentPositionY + 3);
        IntVector2D vector10 = new IntVector2D(currentPositionX - 3, currentPositionY + 2);
        IntVector2D vector11 = new IntVector2D(currentPositionX - 4, currentPositionY + 1);
        IntVector2D vector12 = new IntVector2D(currentPositionX - 4, currentPositionY - 1);
        IntVector2D vector13 = new IntVector2D(currentPositionX - 3, currentPositionY - 2);
        IntVector2D vector14 = new IntVector2D(currentPositionX - 2, currentPositionY - 3);
        IntVector2D vector15 = new IntVector2D(currentPositionX - 1, currentPositionY - 4);

        ArrayList<IntVector2D> vectors = new ArrayList<>(MAX_VECTORS_NUM);
        vectors.add(vector0);
        vectors.add(vector1);
        vectors.add(vector2);
        vectors.add(vector3);
        vectors.add(vector4);
        vectors.add(vector5);
        vectors.add(vector6);
        vectors.add(vector7);
        vectors.add(vector8);
        vectors.add(vector9);
        vectors.add(vector10);
        vectors.add(vector11);
        vectors.add(vector12);
        vectors.add(vector13);
        vectors.add(vector14);
        vectors.add(vector15);

        return vectors;
    }

    private ArrayList<IntVector2D> getDistance6Vectors() {
        final int MAX_VECTORS_NUM = 12;
        final int currentPositionX = this.position.getX();
        final int currentPositionY = this.position.getY();

        IntVector2D vector0 = new IntVector2D(currentPositionX + 2, currentPositionY - 4);
        IntVector2D vector1 = new IntVector2D(currentPositionX + 3, currentPositionY - 3);
        IntVector2D vector2 = new IntVector2D(currentPositionX + 4, currentPositionY - 2);
        IntVector2D vector3 = new IntVector2D(currentPositionX + 4, currentPositionY + 2);
        IntVector2D vector4 = new IntVector2D(currentPositionX + 3, currentPositionY + 3);
        IntVector2D vector5 = new IntVector2D(currentPositionX + 2, currentPositionY + 4);
        IntVector2D vector6 = new IntVector2D(currentPositionX - 2, currentPositionY + 4);
        IntVector2D vector7 = new IntVector2D(currentPositionX - 3, currentPositionY + 3);
        IntVector2D vector8 = new IntVector2D(currentPositionX - 4, currentPositionY + 2);
        IntVector2D vector9 = new IntVector2D(currentPositionX - 4, currentPositionY - 2);
        IntVector2D vector10 = new IntVector2D(currentPositionX - 3, currentPositionY - 3);
        IntVector2D vector11 = new IntVector2D(currentPositionX - 2, currentPositionY - 4);

        ArrayList<IntVector2D> vectors = new ArrayList<>(MAX_VECTORS_NUM);
        vectors.add(vector0);
        vectors.add(vector1);
        vectors.add(vector2);
        vectors.add(vector3);
        vectors.add(vector4);
        vectors.add(vector5);
        vectors.add(vector6);
        vectors.add(vector7);
        vectors.add(vector8);
        vectors.add(vector9);
        vectors.add(vector10);
        vectors.add(vector11);

        return vectors;
    }

    private ArrayList<IntVector2D> getDistance7Vectors() {
        final int MAX_VECTORS_NUM = 8;
        final int currentPositionX = this.position.getX();
        final int currentPositionY = this.position.getY();

        IntVector2D vector0 = new IntVector2D(currentPositionX + 3, currentPositionY - 4);
        IntVector2D vector1 = new IntVector2D(currentPositionX + 4, currentPositionY - 3);
        IntVector2D vector2 = new IntVector2D(currentPositionX + 4, currentPositionY + 3);
        IntVector2D vector3 = new IntVector2D(currentPositionX + 3, currentPositionY + 4);
        IntVector2D vector4 = new IntVector2D(currentPositionX - 3, currentPositionY + 4);
        IntVector2D vector5 = new IntVector2D(currentPositionX - 4, currentPositionY + 3);
        IntVector2D vector6 = new IntVector2D(currentPositionX - 4, currentPositionY - 3);
        IntVector2D vector7 = new IntVector2D(currentPositionX - 3, currentPositionY - 4);

        ArrayList<IntVector2D> vectors = new ArrayList<>(MAX_VECTORS_NUM);
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

    private ArrayList<IntVector2D> getDistance8Vectors() {
        final int MAX_VECTORS_NUM = 4;
        final int currentPositionX = this.position.getX();
        final int currentPositionY = this.position.getY();

        IntVector2D vector0 = new IntVector2D(currentPositionX + 4, currentPositionY - 4);
        IntVector2D vector1 = new IntVector2D(currentPositionX + 4, currentPositionY + 4);
        IntVector2D vector2 = new IntVector2D(currentPositionX - 4, currentPositionY + 4);
        IntVector2D vector3 = new IntVector2D(currentPositionX - 4, currentPositionY - 4);

        ArrayList<IntVector2D> vectors = new ArrayList<>(MAX_VECTORS_NUM);
        vectors.add(vector0);
        vectors.add(vector1);
        vectors.add(vector2);
        vectors.add(vector3);

        return vectors;
    }
}
