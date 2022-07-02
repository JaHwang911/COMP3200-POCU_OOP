package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class Marine extends Unit {
    private static final int MAX_HP = 35;
    private static final int AP = 6;
    private static final char SYMBOL = 'M';
    private static final byte VISION = 2;
    private static final byte AOE = 0;
    private static final UnitType UNIT_TYPE = UnitType.GROUND;
    private static final AttackableTarget ATTACKABLE_TARGET = AttackableTarget.ALL;

    private ActionType action = ActionType.NONE;
    private Unit target;

    public Marine(IntVector2D position) {
        super(position, MAX_HP, AP, SYMBOL);
    }

    public byte getVision() {
        return VISION;
    }

    public void onAttacked(int damage) {
        super.hp = Math.max(0, this.hp - damage);
    }

    public AttackableTarget getAttackableTarget() {
        return ATTACKABLE_TARGET;
    }

    public UnitType getUnitType() {
        return UNIT_TYPE;
    }

    public AttackIntent attack() {
        return new AttackIntent(this, this.target.getPosition());
    }

    public void move() {
        int distanceY = this.position.getY() - this.target.position.getY();
        int distanceX = distanceY == 0 ? this.position.getX() - this.target.position.getX() : 0;

        this.position.setY(distanceY * -1);
        this.position.setX(distanceX * -1);
    }

    public void decideAction(ArrayList<Unit> units) {
        if (units.size() == 0) {
            this.action = ActionType.NONE;
            return;
        }

        this.action = ActionType.MOVE;

        for (Unit unit : units) {
            int distance = this.position.getDistance(unit.position);

            if (distance == 1) {
                this.action = ActionType.ATTACK;
                return;
            }
        }
    }

    public void think(ArrayList<Unit> units) {
        this.target = null;

        switch (this.action) {
            case MOVE:
                int maxDistance = 22;

                for (Unit unit : units) {
                    int distance = this.position.getDistance(unit.position);

                    if (maxDistance > distance) {
                        this.target = unit;
                        continue;
                    }

                    if (maxDistance == distance) {
                        this.target = this.target.getHp() > unit.getHp() ? unit : this.target;
                    }
                }
                break;
            case ATTACK:
                for (Unit unit : units) {
                    int distance = this.position.getDistance(unit.position);

                    if (distance == 0) {
                        this.target = unit;
                        return;
                    }

                    if (distance == 1) {
                        if (this.target == null || this.target.getHp() > unit.getHp()) {
                            this.target = unit;
                        }
                    }
                }
                break;
            case NONE:
                break;
            default:
                assert false : "Unknown action type";
                break;
        }
    }

    public void action() {
        switch (this.action) {
            case MOVE:
                move();
                break;
            case ATTACK:
                attack();
                break;
            case NONE:
                break;
            default:
                assert false : "Unknown action type";
                break;
        }
    }
}
