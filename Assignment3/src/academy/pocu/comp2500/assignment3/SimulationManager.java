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
    private final ArrayList<Unit>[][] unitPositions = new ArrayList[NUM_ROWS][NUM_COLUMNS];

    private SimulationManager() {
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
        this.unitPositions[pos.getY()][pos.getX()].add(unit);
    }

    public void registerThinkable(Unit thinkable) {
        this.thinkableUnits.add((IThinkable) thinkable);
    }

    public void registerMovable(Unit movable) {
        this.movableUnits.add((IMovable) movable);
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
            this.unitPositions[currentPosition.getY()][currentPosition.getX()].remove(unit);

            unit.move();
            currentPosition = ((Unit) unit).getPosition();
            this.unitPositions[currentPosition.getY()][currentPosition.getX()].add((Unit) unit);
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

            ArrayList<Unit> targets = this.unitPositions[attackPosition.getY()][attackPosition.getX()];

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
    }

    public ArrayList<Unit> getUnitInPosition(int x, int y) {
        return this.unitPositions[y][x];
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
                char sign = this.unitPositions[i][j] == null ? 'X' : 'O';

                System.out.printf("%c", sign);
            }

            System.out.println();
        }

        System.out.println("================");
    }

    /*
    private Unit[][] checkVisibleEnemy(Unit unit) {
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
        int maxVisibleX = Math.min(NUM_COLUMNS, myPositionX + vision);
        int maxVisibleY = Math.min(NUM_ROWS, myPositionY + vision);

        for (int i = startPosY; i < maxVisibleY; ++i) {
            for (int j = startPosX; j < maxVisibleX; ++j) {
                ArrayList<Unit> enemy = this.unitPositions[i][j];

                if (enemy != null) {
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
