package academy.pocu.comp2500.lab8;

import java.util.ArrayList;

public class Sprinkler extends SmartDevice implements ISprayable {
    private static final int WATER_PER_TICK = 15;

    private final ArrayList<Schedule> schedules;
    private Schedule scheduleInProgress;
    private Planter planter;

    public Sprinkler() {
        this.schedules = new ArrayList<>();
    }

    public void addSchedule(Schedule schedule) {
        this.schedules.add(schedule);
    }

    @Override
    public void installedPlanter(Planter planter) {
        this.planter = planter;
        planter.installSprayable(this);
    }

    public void onTick() {
        ++super.tickCount;

        if (this.scheduleInProgress != null) {
            if (getTicksSinceLastUpdate() == this.scheduleInProgress.getTickWhile()) {
                super.isOn = false;
                this.schedules.remove(this.scheduleInProgress);
                this.scheduleInProgress = null;
                super.tickLastUpdate = super.tickCount;

                return;
            }

            spray();
            return;
        }

        for (Schedule schedule : this.schedules) {
            if (super.tickCount == schedule.getOnTickCount() ||
                    super.tickCount == 1 && schedule.getOnTickCount() == 0) {
                super.isOn = true;
                super.tickLastUpdate = super.tickCount;
                this.scheduleInProgress = schedule;
                spray();

                break;
            }
        }
    }

    public void spray() {
        if (this.planter == null) {
            return;
        }

        this.planter.addWater(WATER_PER_TICK);
    }
}
