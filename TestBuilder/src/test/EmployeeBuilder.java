package test;

public class EmployeeBuilder {
    private String name;
    private int age;
    private int startingYear;
    private int id;

    public EmployeeBuilder(int id) {
        this.id = id;
    }

    public EmployeeBuilder build() {
        return this;
    }

    public EmployeeBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public EmployeeBuilder withAge(int age) {
        this.age = age;
        return this;
    }

    public EmployeeBuilder withStartingYear(int year) {
        this.startingYear = year;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public int getStartingYear() {
        return this.startingYear;
    }

    public int getId() {
        return this.id;
    }
}
