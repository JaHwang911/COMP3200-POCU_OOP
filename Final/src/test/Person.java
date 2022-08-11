package test;

public class Person {
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || !(obj instanceof Person)) {
            return false;
        }

        Person other = (Person) obj;
        return this.firstName.equals(other.firstName)
                && this.lastName.charAt(0) == other.lastName.charAt(0);
    }
}
