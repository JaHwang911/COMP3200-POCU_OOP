package test;

public class Person {
    private String firstName;
    private String lastName;
    protected String email;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = String.format("%c%s@hogwarts.academy", firstName.toLowerCase().charAt(0), lastName.toLowerCase());
    }

    public String getName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }

    public String getEmail() {
        return this.email;
    }

    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null
                || !(obj instanceof Person)
                || this.hashCode() != obj.hashCode()) {
            return false;
        }

        Person p = (Person) obj;
        return this.firstName.equals(p.firstName) && this.lastName.equals(p.lastName);
    }

    @Override
    public int hashCode() {
        return this.firstName.hashCode() ^ (this.lastName.hashCode() << 31);
    }

    public void say() {
        System.out.println("I'm a person");
    }
}
