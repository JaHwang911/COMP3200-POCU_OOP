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

    private IntVector2D attackPosition;

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
        assert attackPosition != null;

        return new AttackIntent(this, attackPosition);
    }

    public void move(int x, int y) {
        this.position.setX(this.position.getX() + x);
        this.position.setY(this.position.getY() + y);
    }

    public Turn think(ArrayList<Unit> units) {
        /*
        이번 턴 만약에 공격이라면
         */
        return null;
    }
}
