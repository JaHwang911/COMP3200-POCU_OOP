package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class Marine extends Unit implements IMovable, IThinkable {
    private static final int MAX_HP = 35;
    private static final int AP = 6;
    private static final char SYMBOL = 'M';
    private static final byte VISION = 2;
    private static final byte AOE = 0;
    private static final UnitType UNIT_TYPE = UnitType.GROUND;
    private static final AttackableTarget ATTACKABLE_TARGET = AttackableTarget.ALL;

    private IntVector2D attackPosition = super.nullPosition;
    private IntVector2D movePosition = super.nullPosition;
    private SimulationManager instance;

    public Marine(IntVector2D position) {
        super(position, MAX_HP, AP, SYMBOL);
    }

    public byte getVision() {
        return VISION;
    }

    public AttackableTarget getAttackableTarget() {
        return ATTACKABLE_TARGET;
    }

    public UnitType getUnitType() {
        return UNIT_TYPE;
    }

    public void onAttacked(int damage) {
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
        if (this.hp == 0) {
            this.instance.deleteThinkable(this);
            this.instance.deleteMovable(this);
            this.instance.deleteUnit(this);

            return false;
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

        this.position.setY(currentY + movePointY);
        this.position.setX(currentX + movePointX);
    }

    public void think(ArrayList<Unit> units) {
        units.remove(this);
        this.attackPosition = super.nullPosition;
        this.movePosition = super.nullPosition;

        if (units.size() == 0) {
            return;
        }

        ArrayList<Unit> attackableUnits = new ArrayList<>();

        for (Unit unit : units) {
            int distance = this.position.getDistance(unit.position);

            if (distance <= 1) {
                attackableUnits.add(unit);
            }
        }

        /*
        * 다음은 해병의 교전규칙입니다. (우선순위 순)
        가장 약한 유닛(HP가 가장 낮은 유닛)이 있는 타일을 공격
        자신의 위치에 유닛이 있다면 그 타일을 공격. 그렇지 않을 경우 북쪽(위쪽)에 유닛이 있다면 그 타일을 공격. 그렇지 않을 경우 시계 방향으로 검색하다 찾은 유닛의 타일을 공격
         */

        if (attackableUnits.size() > 0) {
            Unit target = attackableUnits.get(0);

            for (int i = 1; i < attackableUnits.size(); ++i) {
                Unit tmpTarget = attackableUnits.get(i);

                if (target.getHp() > tmpTarget.getHp()) {
                    attackableUnits.remove(target);
                    target = tmpTarget;
                }
            }

            if (attackableUnits.size() == 1) {
                this.attackPosition = new IntVector2D(target.getPosition().getX(), target.getPosition().getY());
                return;
            }

            for (int i = 0; i < attackableUnits.size(); ++i) {
                Unit tmpUnit = attackableUnits.get(i);

                if (this.position.isSamePosition(tmpUnit.position)) {
                    this.attackPosition = new IntVector2D(target.getPosition().getX(), target.getPosition().getY());
                    return;
                }
            }

            // check attackable direction
            final int currentPositionX = this.getPosition().getX();
            final int currentPositionY = this.getPosition().getY();

            IntVector2D north = new IntVector2D(currentPositionX, currentPositionY - 1);
            IntVector2D east = new IntVector2D(currentPositionX + 1, currentPositionY);
            IntVector2D south = new IntVector2D(currentPositionX, currentPositionY + 1);
            IntVector2D west = new IntVector2D(currentPositionX - 1, currentPositionY);

            ArrayList<IntVector2D> directions = new ArrayList<>(4);
            directions.add(north);
            directions.add(east);
            directions.add(south);
            directions.add(west);

            for (IntVector2D direction : directions) {
                for (Unit unit : attackableUnits) {
                    if (direction.isSamePosition(unit.getPosition())) {
                        this.attackPosition = new IntVector2D(target.getPosition().getX(), target.getPosition().getY());
                        return;
                    }
                }
            }
        }

        assert attackableUnits.size() == 0;

        /*
         * 다음은 해병이 시야 안에서 적을 발견했을 때 따르는 이동 규칙입니다. (역시 우선순위 순)
            가장 가까이 있는 유닛 쪽으로 이동. 가장 가까운 유닛은 맨해튼 거리를 사용하여 판단합니다.
            가장 약한 유닛 쪽으로 이동
            북쪽에 있는 유닛 쪽으로 이동, 북쪽에 유닛이 없다면 시계 방향으로 검색하다 찾은 유닛 쪽으로 이동
            이동할 때는 언제나 y축을 따라 다 이동한 뒤 x축을 따라 이동합니다.
            해병이 시야 안에서 적을 찾지 못한 경우, 현재 타일에서 움직이지 않습니다.
         */

        ArrayList<Unit> targets = new ArrayList<>();
        int maxDistance = this.position.getDistance(units.get(0).getPosition());
        targets.add(units.get(0));

        for (int i = 1; i < units.size(); ++i) {
            Unit unit = units.get(i);
            int distance = this.position.getDistance(unit.position);

            if (maxDistance > distance) {
                maxDistance = distance;
                targets.clear();
                targets.add(unit);
                continue;
            }

            if (maxDistance == distance) {
                Unit tmpTarget = targets.get(0);

                if (tmpTarget.hp > unit.hp) {
                    targets.clear();
                }

                targets.add(unit);
            }
        }
        Unit target = null;

        if (targets.size() == 1) {
            target = targets.get(0);
            this.movePosition = new IntVector2D(target.getPosition().getX(), target.getPosition().getY());
            return;
        }

        final int currentPositionX = this.getPosition().getX();
        final int currentPositionY = this.getPosition().getY();
        // 시계 방향 검색

//        target = target.getHp() > unit.getHp() ? unit : target;
        this.movePosition = new IntVector2D(target.getPosition().getX(), target.getPosition().getY());
    }
}
