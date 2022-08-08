package test;

public class Teacher extends Person {
    private DepartmentType type;

    public Teacher(String firstName, String lastName, DepartmentType type) {
        super(firstName, lastName);

        this.type = type;
    }

    public DepartmentType getType() {
        return this.type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null
                || !(obj instanceof Person)
                || !super.firstName.equals(((Person) obj).getFirstName())
                || !super.lastName.equals(((Person) obj).getLastName())) {
            return false;
        }

        if (!(obj instanceof Teacher) || this.hashCode() != obj.hashCode()) {
            return false;
        }

        Teacher t = (Teacher) obj;

        return (this.type == t.type);
    }

    @Override
    public int hashCode() {
        return this.type.hashCode();
    }
}
