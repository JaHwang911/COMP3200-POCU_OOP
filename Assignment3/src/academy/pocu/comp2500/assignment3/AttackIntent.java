package academy.pocu.comp2500.assignment3;

public class AttackIntent {
    private final Unit attacker;
    private final IntVector2D position;

    public AttackIntent(Unit attacker, IntVector2D position) {
        this.attacker = attacker;
        this.position = position;
    }

    public Unit getAttacker() {
        return this.attacker;
    }

    public int getDamage() {
        return this.attacker.getAp();
    }

    public IntVector2D getPosition() {
        return this.position;
    }

    public UnitType getAttackedUnitType() {
        switch (this.attacker.getAttackableTarget()) {
            case ALL:
                return UnitType.UNKNOWN;
            case GROUND:
                return UnitType.GROUND;
            case AIR:
                return UnitType.AIR;
            default:
                assert false : "Unknown unit type";
                return null;
        }
    }
}
