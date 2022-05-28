package test;

public class Teacher extends Person {
    public Department department;

    public Teacher(String firstName, String lastName, Department department) {
        super(firstName, lastName);

        this.department = department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setEmail(String email) {
        super.email = String.format("%s@hogwarts.academy", email);
    }
}
