package academy.pocu.comp2500.assignment3;

public class Marine extends Unit implements IMoveable {
    private static final int MAX_HP = 35;
    private static final int AP = 6;
    private static final char SYMBOL = 'M';
    private static final byte VISION = 2;
    private static final byte AOE = 0;
    private static final UnitType UNIT_TYPE = UnitType.GROUND;
    private static final AttackableTarget ATTACKABLE_TARGET = AttackableTarget.ALL;

    public Marine(IntVector2D position) {
        super(position, MAX_HP, AP, SYMBOL);
    }

    @Override
    public AttackIntent attack() {
        return null;
    }

    public void move() {
        return;
    }
}
