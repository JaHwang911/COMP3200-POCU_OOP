package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public final class SimulationManager {
    private static final int NUM_COLUMNS = 16;
    private static final int NUM_ROWS = 8;
    private static SimulationManager instance;

    private final ArrayList<Unit> units = new ArrayList<>();
    private final ArrayList<IThinkable> thinkable = new ArrayList<>();
    private final ArrayList<IMovable> movable = new ArrayList<>();
    private final ArrayList<ICollisionEventListener> subscriber = new ArrayList<>();
    private final ArrayList<ArrayList<ArrayList<Unit>>> unitPositions = new ArrayList<>();

    private SimulationManager() {
        for (int i = 0; i < NUM_ROWS; ++i) {
            this.unitPositions.add(new ArrayList<>());

            for (int j = 0; j < NUM_COLUMNS; ++j) {
                this.unitPositions.get(i).add(new ArrayList<>());
            }
        }
    }

    public static void clear() {
        instance = null;
    }

    public static SimulationManager getInstance() {
        if (instance == null) {
            instance = new SimulationManager();
        }

        return instance;
    }

    public ArrayList<Unit> getUnits() {
        return this.units;
    }

    public void spawn(Unit unit) {
        int posX = unit.getPosition().getX();
        int posY = unit.getPosition().getY();

        if (posX >= NUM_COLUMNS || posX < 0 || posY >= NUM_ROWS || posY < 0) {
            return;
        }

        unit.onSpawn();
        this.units.add(unit);
        this.unitPositions.get(unit.getPosition().getY()).get(unit.getPosition().getX()).add(unit);
    }

    public void registerThinkable(IThinkable thinkable) {
        this.thinkable.add(thinkable);
    }

    public void deleteThinkable(IThinkable thinkable) {
        this.thinkable.remove(thinkable);
    }

    public void registerMovable(IMovable movable) {
        this.movable.add(movable);
    }

    public void deleteMovable(IMovable movable) {
        this.movable.remove(movable);
    }

    public void registerCollisionEventListener(Unit listener) {
        this.subscriber.add((ICollisionEventListener) listener);
    }

    public void deleteCollisionEventListener(ICollisionEventListener listener) {
        this.subscriber.remove(listener);
    }

    public void update() {
        for (IThinkable unit : this.thinkable) {
            unit.think(checkVisibleEnemy((Unit) unit));
        }

        for (IMovable unit : this.movable) {
            IntVector2D currentPosition = ((Unit) unit).getPosition();
            this.unitPositions.get(currentPosition.getY()).get(currentPosition.getX()).remove(unit);

            unit.move();
            currentPosition = ((Unit) unit).getPosition();
            this.unitPositions.get(currentPosition.getY()).get(currentPosition.getX()).add((Unit) unit);
        }

        for (ICollisionEventListener unit : this.subscriber) {
            unit.collisionListen();
        }

        for (Unit unit : this.units) {
            AttackIntent attackIntent = unit.attack();
            IntVector2D attackPosition = attackIntent.getPosition();

            if (attackPosition.isSamePosition(-1, -1)) {
                continue;
            }

            ArrayList<Unit> targets = new ArrayList<>();
            ArrayList<Unit> aoeTargets = null;

            targets.addAll(getPositionUnitOrNull(attackIntent.getAttacker(), attackPosition.getX(), attackPosition.getY()));

            int damage = attackIntent.getDamage();
            int aoe = attackIntent.getAoe();

            if (aoe > 0) {
                aoeTargets = new ArrayList<>();
                int startAoeRangeX = Math.max(0, attackPosition.getX() - aoe);
                int startAoeRangeY = Math.max(0, attackPosition.getY() - aoe);
                int endAoeRangeX = Math.min(NUM_COLUMNS - 1, attackPosition.getX() + aoe);
                int endAoeRangeY = Math.min(NUM_ROWS - 1, attackPosition.getY() + aoe);

                for (int i = startAoeRangeY; i <= endAoeRangeY; ++i) {
                    for (int j = startAoeRangeX; j <= endAoeRangeX; ++j) {
                        aoeTargets.addAll(getPositionUnitOrNull(attackIntent.getAttacker(), j, i));
                    }
                }

                if (aoeTargets.size() > 0) {
                    aoeTargets.removeIf(u -> (u.position.isSamePosition(attackPosition)));

                    if (attackIntent.getAttackedUnitType() != UnitType.UNKNOWN) {
                        aoeTargets.removeIf(u -> (u.unitType != attackIntent.getAttackedUnitType()));
                    }
                }
            }

            if (targets.size() == 0) {
                if (aoeTargets == null) {
                    continue;
                }

                onAttackedAoe(aoeTargets, attackIntent);
                continue;
            }

            UnitType attackableUnitType = attackIntent.getAttackedUnitType();

            for (Unit target : targets) {
                if (attackableUnitType == UnitType.UNKNOWN || attackableUnitType == target.getUnitType()) {
                    if (unit == target) {
                        continue;
                    }

                    target.onAttacked(damage);
                }
            }

            if (aoeTargets != null) {
                onAttackedAoe(aoeTargets, attackIntent);
            }
        }

        ArrayList<Unit> removedUnit = new ArrayList<>();

        for (Unit unit : this.units) {
            if (!unit.isAlive()) {
                removedUnit.add(unit);
                this.unitPositions.get(unit.getPosition().getY()).get(unit.getPosition().getX()).remove(unit);
            }
        }

        for (Unit unit : removedUnit) {
            this.units.remove(unit);
        }

        removedUnit = null;
    }

    public ArrayList<Unit> getPositionUnitOrNull(Unit unit, int x, int y) {
        if (x >= NUM_COLUMNS || y >= NUM_ROWS || x < 0 || y < 0) {
            return null;
        }

        ArrayList<Unit> ret = new ArrayList<>();

        for (Unit enemy : this.unitPositions.get(y).get(x)) {
            if (!this.subscriber.contains(unit) && this.subscriber.contains(enemy)) {
                continue;
            }

            ret.add(enemy);
        }

        ret.remove(unit);

        return ret;
    }

    private void onAttackedAoe(ArrayList<Unit> aoeTargets, AttackIntent attackIntent) {
        UnitType attackableUnitType = attackIntent.getAttackedUnitType();

        for (Unit target : aoeTargets) {
            if (attackableUnitType == UnitType.UNKNOWN || attackableUnitType == target.getUnitType()) {
                int targetPositionX = Math.abs(target.getPosition().getX() - attackIntent.getPosition().getX());
                int targetPositionY = Math.abs(target.getPosition().getY() - attackIntent.getPosition().getY());
                int distance = Math.max(targetPositionX, targetPositionY);
                int aoeDamage = attackIntent.getDamage() * (1 - distance / (attackIntent.getAoe() + 1));

                target.onAttacked(aoeDamage);
            }
        }
    }

    private ArrayList<Unit> checkVisibleEnemy(Unit unit) {
        ArrayList<Unit> ret = new ArrayList<>();

        final int myPositionX = unit.getPosition().getX();
        final int myPositionY = unit.getPosition().getY();
        final int vision = unit.getVision();

        int startPosX = Math.max(0, myPositionX - vision);
        int startPosY = Math.max(0, myPositionY - vision);
        int maxVisibleX = Math.min(NUM_COLUMNS - 1, myPositionX + vision);
        int maxVisibleY = Math.min(NUM_ROWS - 1, myPositionY + vision);

        for (int i = startPosY; i <= maxVisibleY; ++i) {
            for (int j = startPosX; j <= maxVisibleX; ++j) {
                ArrayList<Unit> enemy = this.unitPositions.get(i).get(j);

                if (enemy.size() > 0) {
                    ret.addAll(enemy);
                }
            }
        }

        ret.remove(unit);
        ret.removeIf(u -> (this.subscriber.contains(u)));

        UnitType visibleType;

        switch (unit.getAttackableTarget()) {
            case ALL:
                return ret;
            case AIR:
                visibleType = UnitType.AIR;
                break;
            case GROUND:
                visibleType = UnitType.GROUND;
                break;
            default:
                assert false : "Unknown attackable type";
                visibleType = null;
                return null;
        }

        ret.removeIf(u -> (u.getUnitType() != visibleType));

        return ret;
    }
}
