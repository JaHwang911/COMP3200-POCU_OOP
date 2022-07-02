package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class SimulationManager {
    private static final int NUM_COLUMNS = 16;
    private static final int NUM_ROWS = 8;
    private static SimulationManager instance;

    private ArrayList<Unit> units = new ArrayList<>();
    private ArrayList<IThinkable> thinkableUnits = new ArrayList<>();
    private ArrayList<IMovable> movableUnits = new ArrayList<>();
    private Unit[][] unitPositions = new Unit[NUM_ROWS][NUM_COLUMNS];;

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

        try {
            this.thinkableUnits.add((IThinkable) unit);
            this.movableUnits.add((IMovable) unit);
        } catch(ClassCastException e) {
        }

        IntVector2D pos = unit.getPosition();
        this.unitPositions[pos.getY()][pos.getX()] = unit;
    }

    public void registerThinkable(Unit thinkable) {

    }

    public void registerMovable(Unit movable) {

    }

    public void registerCollisionEventListener(Unit listener) {

    }

    public void update() {
        for (Unit unit : this.units) {
            unit.decideAction(checkVisibleEnemy(unit));
        }

        for (Unit unit : this.units) {
            unit.action();
        }
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
                Unit enemy = this.unitPositions[i][j];

                if (enemy != null && !unit.equals(enemy)) {
                    ret.add(enemy);
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

    public static void clear() {
        instance = null;
    }
}
