package Test;

public class FullTimeTeacher extends Teacher {
    private int weeklyHours;

    public FullTimeTeacher(String firstName, String lastName, Department department) {
        super(firstName, lastName, department);
    }

    public int getWeeklyHours() {
        return this.weeklyHours;
    }

    public void setWeeklyHours(int hours) {
        this.weeklyHours = hours;
    }
}
