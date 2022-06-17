package Test;

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
}
