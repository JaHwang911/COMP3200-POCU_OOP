package test;

public class PartTimeTeacher extends Teacher {
    private int weeklyHours;

    public PartTimeTeacher(String firstName, String lastName, String department, int weeklyHours) {
        super(firstName, lastName, department);

        this.weeklyHours = weeklyHours;
    }

    public int getWeeklyHours() {
        return weeklyHours;
    }

    public void setWeeklyHours(int hours) {
        weeklyHours = hours;
    }

    @Override
    public void teach() {

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null
                || !(obj instanceof Person)
                || !super.firstName.equals(((Person) obj).firstName)
                || !super.lastName.equals(((Person) obj).lastName)) {
            return false;
        }

        if (!(obj instanceof Teacher) || !super.department.equals(((Teacher) obj).department)) {
            return false;
        }

        if (!(obj instanceof PartTimeTeacher) || this.hashCode() != obj.hashCode()) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return weeklyHours;
    }
}
