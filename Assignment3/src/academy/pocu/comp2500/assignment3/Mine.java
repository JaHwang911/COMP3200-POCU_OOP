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

    private final int maxCollisionCount;
    private int collisionCount;

    public Mine(IntVector2D position, int maxCollisionCount) {
        super(position, SYMBOL, UNIT_TYPE, VISION, AOE, AP, MAX_HP, ATTACKABLE_TARGET);

        this.maxCollisionCount = maxCollisionCount;
    }

    public void onAttacked(int damage) {
        super.hp = Math.max(0, this.hp - damage);
    }

    public void onAttacked(int damage, Unit attackerInfo) {
        printAttackedInfo(damage, attackerInfo);
        super.hp = Math.max(0, this.hp - damage);
    }

    public AttackIntent attack() {
        if (this.collisionCount < this.maxCollisionCount) {
            return new AttackIntent(this, new IntVector2D(-1, -1));
        }

        onAttacked(this.hp, this);
        return new AttackIntent(this, new IntVector2D(this.position.getX(), this.position.getY()));
    }

    public void onSpawn() {
        SimulationManager.getInstance().registerCollisionEventListener(this);
    }

    public boolean isAlive() {
        if (this.hp == 0) {
            SimulationManager.getInstance().deleteCollisionEventListener(this);

            return false;
        }

        return this.hp > 0;
    }

    public void collisionListen() {
        ArrayList<Unit> positionUnits = SimulationManager.getInstance().getPositionUnitOrNull(this, this.position.getX(), this.position.getY());

        if (positionUnits == null) {
            return;
        }

        positionUnits.removeIf(unit -> (unit.unitType == UnitType.AIR));
        this.collisionCount += positionUnits.size();
    }
}
