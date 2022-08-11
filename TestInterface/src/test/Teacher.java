package test;

public abstract class Teacher extends Person implements ITeachable {
    protected String department;

    public Teacher(String firstName, String lastName, String department) {
        super(firstName, lastName);

        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
}
