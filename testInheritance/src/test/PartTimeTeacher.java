package test;

public class PartTimeTeacher extends Teacher implements ITeachable {
    private int weeklyHours;

    public PartTimeTeacher(String firstName, String lastName, Department department) {
        super(firstName, lastName, department);
    }

    public int getWeeklyHours() {
        return this.weeklyHours;
    }

    public void setWeeklyHours(int hours) {
        this.weeklyHours = hours;
    }

    public void teaching() {

    }
}
