package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class Destroyer extends Unit {
    private static final char SYMBOL = 'D';
    private static final UnitType UNIT_TYPE = UnitType.UNKNOWN;
    private static final byte VISION = 0;
    private static final byte AOE = 0;
    private static final int AP = 0;
    private static final int MAX_HP = Integer.MAX_VALUE;
    private static final AttackableTarget ATTACKABLE_TARGET = AttackableTarget.ALL;

    public Destroyer(IntVector2D position) {
        super(position, SYMBOL, UNIT_TYPE, VISION, AOE, AP, MAX_HP, ATTACKABLE_TARGET);
    }

    public void onAttacked(int damage) {
        super.hp -= 1;
    }

    public void onAttacked(int damage, Unit attackerInfo) {
        printAttackedInfo(damage, attackerInfo);
        super.hp -= 1;
    }

    public AttackIntent attack() {
        ArrayList<Unit> allUnits = SimulationManager.getInstance().getUnits();

        allUnits.remove(this);

        for (Unit unit : allUnits) {
            int damage = unit.getHp();
            unit.onAttacked(damage);
        }

        return new AttackIntent(this, new IntVector2D(-1, -1));
    }

    public void onSpawn() {
        SimulationManager.getInstance().registerDestroyer(this);
    }

    public boolean isAlive() {
        if (this.hp == 0) {
            return false;
        }

        return this.hp > 0;
    }
}
