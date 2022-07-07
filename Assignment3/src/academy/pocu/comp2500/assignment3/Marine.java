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
            ArrayList<Unit> tmp = SimulationManager.getInstance().getPositionUnitOrNull(position.getX(), position.getY());

            if (tmp == null || tmp.size() == 0) {
                continue;
            }

            attackableUnits.addAll(tmp);
        }

        attackableUnits.remove(this);

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
                this.attackPosition = new IntVector2D(targetPosition.getX(), targetPosition.getY());
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
}
