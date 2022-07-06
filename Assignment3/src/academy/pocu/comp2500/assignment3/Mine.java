package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class Mine extends Unit implements ICollisionEventListener {
    private static final char SYMBOL = 'N';
    private static final UnitType UNIT_TYPE = UnitType.GROUND;
    private static final byte VISION = 0;
    private static final byte AOE = 0;
    private static final int AP = 10;
    private static final int MAX_HP = 1;
    private static final AttackableTarget ATTACKABLE_TARGET = AttackableTarget.GROUND;

    private SimulationManager instance;
    private int maxCollisionCount;
    private int collisionCount;

    public Mine(IntVector2D position, int count) {
        super(position, MAX_HP, AP, SYMBOL);
        this.maxCollisionCount = count;
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
        onAttacked(1);
        return new AttackIntent(this, new IntVector2D(this.position.getX(), this.position.getY()));
    }

    public void onSpawn() {
        this.instance = SimulationManager.getInstance();
        this.instance.registerCollisionEventListener(this);
    }

    public boolean isAlive() {
        if (this.hp == 0) {
            this.instance.deleteCollisionEventListener(this);

            return false;
        }

        return this.hp > 0;
    }

    public void collisionListener() {

    }
}
