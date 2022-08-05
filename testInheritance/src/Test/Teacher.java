package Test;

public class Teacher extends Person {
    private Department department;

    public Teacher(String firstName, String lastName, Department department) {
        super(firstName, lastName);

        this.department = department;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setEmail(String email) {
        super.email = String.format("%s@hogwarts.academy", email);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null
                || !super.equals(obj)
                || !(obj instanceof Teacher)
                || this.hashCode() != obj.hashCode()) {
            return false;
        }

        Teacher t = (Teacher) obj;
        return (this.department == t.department);
    }

    @Override
    public int hashCode() {
        return this.department.hashCode();
    }
}
