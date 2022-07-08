package academy.pocu.comp2500.lab8;

import java.util.ArrayList;

public class Sprinkler extends SmartDevice implements ISprayable {
    private static final int WATER_PER_TICK = 15;

    private final ArrayList<Schedule> schedules;
    private Schedule scheduleInProgress;

    public Sprinkler() {
        this.schedules = new ArrayList<>();
    }

    public void addSchedule(Schedule schedule) {
        this.schedules.add(schedule);
    }

    @Override
    public void installedPlanter(Planter planter) {
        planter.installSprayable(this);
    }

    public void onTick() {
        ++super.tickCount;

        if (this.scheduleInProgress == null && this.schedules.size() > 0) {
            this.scheduleInProgress = this.schedules.get(0);
        } else if (this.schedules.size() == 0) {
            return;
        }

        if (super.tickCount == this.scheduleInProgress.getOffTickCount()) {
            if (super.isOn) {
                super.isOn = false;
                super.tickLastUpdate = super.tickCount;
            }

            this.schedules.remove(this.scheduleInProgress);
            this.scheduleInProgress = null;
            this.schedules.removeIf(schedule -> (schedule.getOffTickCount() <= super.tickCount));

            return;
        }

        if (super.tickCount == scheduleInProgress.getOnTickCount() ||
                super.tickCount == 1 && scheduleInProgress.getOnTickCount() == 0) {
            super.isOn = true;
            super.tickLastUpdate = super.tickCount;
        }
    }

    public void spray(Planter planter) {
        onTick();

        if (this.isOn) {
            planter.addWater(WATER_PER_TICK);
        }
    }
}
