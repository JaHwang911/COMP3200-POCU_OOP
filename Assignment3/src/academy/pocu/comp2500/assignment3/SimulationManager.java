package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public final class SimulationManager {
    private static final int NUM_COLUMNS = 16;
    private static final int NUM_ROWS = 8;
    private static SimulationManager instance;

    private final ArrayList<Unit> units = new ArrayList<>();
    private final ArrayList<IThinkable> thinkableUnits = new ArrayList<>();
    private final ArrayList<IMovable> movableUnits = new ArrayList<>();
    private final ArrayList<ICollisionEventListener> listeners = new ArrayList<>();
    private final ArrayList<ArrayList<ArrayList<Unit>>> unitPositions = new ArrayList<>();

    private SimulationManager() {
        for (int i = 0; i < NUM_ROWS; ++i) {
            this.unitPositions.add(new ArrayList<>());

            for (int j = 0; j < NUM_COLUMNS; ++j) {
                this.unitPositions.get(i).add(new ArrayList<>());
            }
        }
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
        unit.onSpawn();
        this.units.add(unit);

        IntVector2D pos = unit.getPosition();
        this.unitPositions.get(pos.getY()).get(pos.getX()).add(unit);
    }

    public int getNumColumns() {
        return NUM_COLUMNS;
    }

    public int getNumRows() {
        return NUM_ROWS;
    }

    public void deleteUnit(Unit unit) {
        this.units.remove(unit);
    }

    public void registerThinkable(IThinkable unit) {
        this.thinkableUnits.add(unit);
    }

    public void deleteThinkable(IThinkable unit) {
        this.thinkableUnits.remove(unit);
    }

    public void registerMovable(IMovable unit) {
        this.movableUnits.add(unit);
    }

    public void deleteMovable(IMovable unit) {
        this.movableUnits.remove(unit);
    }

    public void registerCollisionEventListener(Unit listener) {
        this.listeners.add((ICollisionEventListener) listener);
    }

    public void update() {
        for (IThinkable unit : this.thinkableUnits) {
            unit.think(checkVisibleEnemy((Unit) unit));
        }

        for (IMovable unit : this.movableUnits) {
            IntVector2D currentPosition = ((Unit) unit).getPosition();
            this.unitPositions.get(currentPosition.getY()).get(currentPosition.getX()).remove(unit);

            unit.move();
            this.unitPositions.get(currentPosition.getY()).get(currentPosition.getX()).add((Unit) unit);
        }

        for (ICollisionEventListener unit : this.listeners) {
            unit.collisionListener();
        }

        for (Unit unit : this.units) {
            AttackIntent attackIntent = unit.attack();
            IntVector2D attackPosition = attackIntent.getPosition();
            int damage = attackIntent.getDamage();

            if (attackPosition.isSamePosition(-1, -1)) {
                continue;
            }

            ArrayList<Unit> targets = this.unitPositions.get(attackPosition.getY()).get(attackPosition.getX());

            if (targets == null) {
                continue;
            }

            UnitType attackableType = attackIntent.getAttackedUnitType();

            for (Unit target : targets) {
                if (attackableType == UnitType.UNKNOWN || attackableType == target.getUnitType()) {
                    if (unit == target) {
                        continue;
                    }

                    target.onAttacked(damage);
                }
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

    public static void clear() {
        instance = null;
    }

    @Deprecated
    public ArrayList<Unit> visibleEnemy(Unit unit) {
        return checkVisibleEnemy(unit);
    }

    public void printMap() {
        System.out.println("================");

        for (int i = 0; i < NUM_ROWS; ++i) {
            for (int j = 0; j < NUM_COLUMNS; ++j) {
                char sign = this.unitPositions.get(i).get(j) == null ? 'X' : 'O';

                System.out.printf("%c", sign);
            }

            System.out.println();
        }

        System.out.println("================");
    }

    public ArrayList<Unit> getPositionUnitOrNull(int x, int y) {
        if (x >= NUM_COLUMNS || y >= NUM_ROWS) {
            return null;
        }

        return this.unitPositions.get(y).get(x);
    }

    /*
    private ArrayList<Unit>[][] checkVisibleEnemy(Unit unit) {
        final int currentPositionX = unit.getPosition().getX();
        final int currentPositionY = unit.getPosition().getY();
        final int vision = unit.getVision();
        final int visibleArea = vision * 2 + 1;

        Unit[][] ret = new Unit[visibleArea][visibleArea];

        int startPosX = Math.max(0, currentPositionX - vision);
        int startPosY = Math.max(0, currentPositionY - vision);
        int maxVisibleX = Math.min(NUM_COLUMNS, currentPositionX + vision);
        int maxVisibleY = Math.min(NUM_ROWS, currentPositionY + vision);

        int countY = 0;

        for (int i = startPosY; i < maxVisibleY; ++i) {
            int countX = 0;

            for (int j = startPosX; j < maxVisibleX; ++j) {
                ret[countY][countX++] = this.unitPositions[i][j];
            }

            countY++;
        }

        return ret;
    }
    */

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

        for (Unit u : ret) {
            if (u.getUnitType() != visibleType) {
                ret.remove(u);
            }
        }

        return ret;
    }
}
