package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class SmartMine extends Unit implements IThinkable, ICollisionEventListener {
    private static final char SYMBOL = 'A';
    private static final UnitType UNIT_TYPE = UnitType.GROUND;
    private static final byte VISION = 1;
    private static final byte AOE = 1;
    private static final int AP = 15;
    private static final int MAX_HP = 1;
    private static final AttackableTarget ATTACKABLE_TARGET = AttackableTarget.GROUND;

    private IntVector2D attackPosition;
    private SimulationManager instance;
    private final int maxCollisionCount;
    private int collisionCount;
    private final int maxDetectedUnit;

    public SmartMine(IntVector2D position, int maxCollisionCount, int maxDetectedUnit) {
        super(position, SYMBOL, UNIT_TYPE, VISION, AOE, AP, MAX_HP, ATTACKABLE_TARGET);

        this.attackPosition = super.nullPosition;
        this.maxCollisionCount = maxCollisionCount;
        this.maxDetectedUnit = maxDetectedUnit;
    }

    public void onAttacked(int damage) {
        super.hp = Math.max(0, this.hp - damage);
    }

    public AttackIntent attack() {
        if (this.attackPosition.isSamePosition(this.position)) {
            onAttacked(this.hp);
        }

        return new AttackIntent(this, this.attackPosition);
    }

    public void onSpawn() {
        this.instance = SimulationManager.getInstance();
        this.instance.registerThinkable(this);
        this.instance.registerCollisionEventListener(this);
    }

    public boolean isAlive() {
        if (this.hp == 0) {
            this.instance.deleteThinkable(this);

            return false;
        }

        return this.hp > 0;
    }

    public void think(ArrayList<Unit> units) {
        this.attackPosition = super.nullPosition;

        if (units.size() == 0) {
            return;
        }

        if (units.size() >= this.maxDetectedUnit) {
            this.attackPosition = new IntVector2D(this.position.getX(), this.position.getY());
        }
    }

    public void collisionListener() {
        ArrayList<Unit> positionUnits = this.instance.getPositionUnitOrNull(this.position.getX(), this.position.getY());

        if (positionUnits == null) {
            return;
        }

        positionUnits.removeIf(unit -> (unit.unitType == UnitType.AIR));
        this.collisionCount += positionUnits.size();

        if (this.collisionCount >= this.maxCollisionCount) {
            this.attackPosition = new IntVector2D(this.position.getX(), this.position.getY());
        }
    }
}
