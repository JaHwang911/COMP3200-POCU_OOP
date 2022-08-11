package test;

public class Knight extends Person {
    public Knight(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public String getFullName() {
        return "Sir " + super.getFullName();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && this == obj;
    }
}
