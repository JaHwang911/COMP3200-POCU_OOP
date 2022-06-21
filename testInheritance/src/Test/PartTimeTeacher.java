package Test;

public class PartTimeTeacher extends Teacher {
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
}
