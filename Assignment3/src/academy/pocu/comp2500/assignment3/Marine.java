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
    private SimulationManager instance;

    public Marine(IntVector2D position) {
        super(position, MAX_HP, AP, SYMBOL);

        this.attackPosition = super.nullPosition;
        this.movePosition = super.nullPosition;
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

        this.position.setY(currentY + movePointY);
        this.position.setX(currentX + movePointX);
    }

    public void think(ArrayList<Unit> units) {
        units.remove(this);

        this.attackPosition = super.nullPosition;
        this.movePosition = super.nullPosition;

        if (units.size() == 0) {
            return;
        }

        ArrayList<Unit> attackableUnits = new ArrayList<>();

        for (Unit unit : units) {
            int distance = this.position.getDistance(unit.position);

            if (distance <= 1) {
                attackableUnits.add(unit);
            }
        }

        if (attackableUnits.size() > 0) {
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

            for (int i = 0; i < attackableUnits.size(); ++i) {
                Unit tmpUnit = attackableUnits.get(i);

                if (this.position.isSamePosition(tmpUnit.position)) {
                    this.attackPosition = new IntVector2D(target.position.getX(), target.position.getY());
                    return;
                }
            }

            // check attackable direction
            final int currentPositionX = this.getPosition().getX();
            final int currentPositionY = this.getPosition().getY();

            IntVector2D north = new IntVector2D(currentPositionX, currentPositionY - 1);
            IntVector2D east = new IntVector2D(currentPositionX + 1, currentPositionY);
            IntVector2D south = new IntVector2D(currentPositionX, currentPositionY + 1);
            IntVector2D west = new IntVector2D(currentPositionX - 1, currentPositionY);

            ArrayList<IntVector2D> directions = new ArrayList<>(4);
            directions.add(north);
            directions.add(east);
            directions.add(south);
            directions.add(west);

            for (IntVector2D direction : directions) {
                for (Unit unit : attackableUnits) {
                    if (direction.isSamePosition(unit.getPosition())) {
                        this.attackPosition = new IntVector2D(target.position.getX(), target.position.getY());
                        return;
                    }
                }
            }
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
}
