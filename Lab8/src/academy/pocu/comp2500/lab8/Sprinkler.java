package academy.pocu.comp2500.lab8;

import java.util.ArrayList;

public class Sprinkler extends SmartDevice {
    private ArrayList<Schedule> schedules;

    public void addSchedule(Schedule schedule) {
        this.schedules.add(schedule);
    }
}
