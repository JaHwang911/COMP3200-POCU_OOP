package test;

public class FullTimeTeacher extends Teacher {
    private int officeNumber;

    public FullTimeTeacher(String firstName, String lastName, String department, int officeNumber) {
        super(firstName, lastName, department);

        this.officeNumber = officeNumber;
    }

    public int getOfficeNumber() {
        return officeNumber;
    }

    @Override
    public void teach() {

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || !(obj instanceof Person)
                || !super.firstName.equals(((Person) obj).firstName)
                || !super.lastName.equals(((Person) obj).lastName)) {
            return false;
        }

        if (!(obj instanceof Teacher) || !super.department.equals(((Teacher) obj).department)) {
            return false;
        }

        if (!(obj instanceof FullTimeTeacher) || this.hashCode() != obj.hashCode()) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return officeNumber;
    }
}
