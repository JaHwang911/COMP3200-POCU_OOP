package academy.pocu.comp2500.assignment3;

public class Marine extends Unit implements IMovable {
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
        // 이 안에서 어떤 놈 공격 할 건지 확인하는 메서드를 만들고 그 메서드는 적 위치를 반환
        return null;
    }

    public void move() {
        return;
    }
}
