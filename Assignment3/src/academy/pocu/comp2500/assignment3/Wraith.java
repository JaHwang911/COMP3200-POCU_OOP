package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class Wraith extends Unit implements IThinkable, IMovable {
    private static final char SYMBOL = 'W';
    private static final UnitType UNIT_TYPE = UnitType.AIR;
    private static final byte VISION = 4;
    private static final byte AOE = 0;
    private static final int AP = 6;
    private static final int MAX_HP = 80;
    private static final AttackableTarget ATTACKABLE_TARGET = AttackableTarget.ALL;

    private SimulationManager instance;
    private final IntVector2D startPosition;
    private IntVector2D attackPosition;
    private IntVector2D movePosition;
    private final ArrayList<IntVector2D> attackablePositions;
    private boolean hasShield;
    private boolean attacked;

    public Wraith(IntVector2D position) {
        super(position, MAX_HP, AP, SYMBOL);

        this.startPosition = position;
        this.attackPosition = super.nullPosition;
        this.movePosition = super.nullPosition;
        this.attackablePositions = new ArrayList<>();
        this.hasShield = true;
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
        if (this.hasShield) {
            this.attacked = true;
            return;
        }

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
        // 실수로 프레임이 끝나는 부분이 아닌 곳에서 호출하면 레이스의 쉴드에 문제 생김 어떻게 해결?
        if (this.hp == 0) {
            this.instance.deleteThinkable(this);
            this.instance.deleteMovable(this);

            return false;
        }

        if (this.attacked) {
            this.hasShield = false;
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

        this.position = new IntVector2D(currentX + movePointX, currentY + movePointY);
    }

    public void think(ArrayList<Unit> units) {
        units.remove(this);

        this.attackPosition = super.nullPosition;
        this.movePosition = super.nullPosition;

        if (units.size() == 0) {
            this.movePosition = this.startPosition;
            return;
        }

        setAttakablePositions();
        ArrayList<Unit> attackableUnits = new ArrayList<>();

        for (IntVector2D position : this.attackablePositions) {
            ArrayList<Unit> tmp = this.instance.getPositionUnitOrNull(position.getX(), position.getY());

            if (tmp == null || tmp.size() == 0) {
                continue;
            }

            for (Unit unit : tmp) {
                if (unit.getUnitType() == UnitType.AIR) {
                    attackableUnits.add(unit);
                }
            }
        }

        if (attackableUnits.size() == 0) {
            for (IntVector2D position : this.attackablePositions) {
                ArrayList<Unit> tmp = instance.getPositionUnitOrNull(position.getX(), position.getY());

                if (tmp == null || tmp.size() == 0) {
                    continue;
                }

                attackableUnits.addAll(tmp);
            }
        }

        if (attackableUnits.size() > 0) {
            ArrayList<Unit> removed = new ArrayList<>();
            int maxHp = Integer.MAX_VALUE;

            // set minimum hp
            for (Unit unit : attackableUnits) {
                if (maxHp > unit.getHp()) {
                    maxHp = unit.getHp();
                }
            }

            // check over minimum hp
            for (Unit unit : attackableUnits) {
                if (maxHp < unit.getHp()) {
                    removed.add(unit);
                }
            }

            for (Unit unit : removed) {
                attackableUnits.remove(unit);
            }

            removed = null;

            IntVector2D samePosition = new IntVector2D(attackableUnits.get(0).position.getX(), attackableUnits.get(0).position.getY());

            if (attackableUnits.size() == 1) {
                this.attackPosition = samePosition;
                return;
            }

            for (int i = 1; i < attackableUnits.size(); ++i) {
                Unit tmpTarget = attackableUnits.get(i);

                if (!tmpTarget.position.isSamePosition(samePosition)) {
                    break;
                }

                if (i == attackableUnits.size() - 1 && tmpTarget.position.isSamePosition(samePosition)) {
                    this.attackPosition = new IntVector2D(samePosition.getX(), samePosition.getY());
                    return;
                }
            }

            // Attack this position
            // attack clockwise
            this.attackPosition = samePosition;
            return;
        }

        assert attackableUnits.size() == 0;

        /*
        다음은 망령이 시야 안에서 적을 발견할 경우 따르는 이동 규칙입니다. (역시 우선순위 순)
            공중 유닛들을 따라갈 후보로 선택. 선택할 공중 유닛이 없다면 지상 유닛들을 선택
            가장 가까이 있는 유닛 쪽으로 이동
            가장 약한 유닛 쪽으로 이동
            북쪽에 있는 유닛 쪽으로 이동. 북쪽에 유닛이 없다면 시계 방향으로 검색하다 찾은 유닛 쪽으로 이동
            이동할 때는 언제나 y축을 따라 이동하는 게 우선입니다.

            망령이 시야 안에서 적을 찾지 못한 경우, 자기의 처음 위치 쪽으로 이동해야 합니다. 이때 역시 y축을 따라 먼저 이동합니다.
         */

        ArrayList<Unit> targets = new ArrayList<>();
        ArrayList<Unit> removed = new ArrayList<>();

        for (Unit unit : units) {
            if (unit.getUnitType() == UnitType.AIR) {
                targets.add(unit);
            }
        }

        if (targets.size() == 0) {
            targets.addAll(units);
        }

        int maxDistance = this.position.getDistance(targets.get(0).getPosition());

        for (Unit unit : targets) {
            int distance = this.position.getDistance(unit.position);

            if (maxDistance > distance) {
                maxDistance = distance;
            }
        }

        for (Unit unit : targets) {
            int distance = this.position.getDistance(unit.position);

            if (maxDistance < distance) {
                removed.add(unit);
            }
        }

        for (Unit unit : removed) {
            targets.remove(unit);
        }

        removed = null;

        if (targets.size() == 1) {
            this.movePosition = new IntVector2D(targets.get(0).getPosition().getX(), targets.get(0).getPosition().getY());
            return;
        }

        // check clockwise
        // 남은 유닛이 지상과 공중이 섞여있을 때 문제 될 수 있음
        this.movePosition = searchClockwise(maxDistance);
        assert !this.movePosition.isSamePosition(nullPosition);
    }

    private void setAttakablePositions() {
        this.attackablePositions.clear();

        int curX = this.position.getX();
        int curY = this.position.getY();

        this.attackablePositions.add(new IntVector2D(curX, curY));
        this.attackablePositions.add(new IntVector2D(curX, curY - 1));
        this.attackablePositions.add(new IntVector2D(curX + 1, curY));
        this.attackablePositions.add(new IntVector2D(curX, curY + 1));
        this.attackablePositions.add(new IntVector2D(curX - 1, curY));
    }
}
