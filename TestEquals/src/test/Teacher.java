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
                || !(obj instanceof Teacher)
                || this.hashCode() != obj.hashCode()) {
            return false;
        }

        Person p = (Person) obj;

        return (super.firstName.equals(p.firstName) && super.lastName.equals(p.lastName));
    }

    @Override
    public int hashCode() {
        return this.type.hashCode();
    }
}
