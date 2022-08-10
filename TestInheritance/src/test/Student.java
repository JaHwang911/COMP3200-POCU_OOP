package test;

public class Student extends Person {
    private Houses house;

    public Student(String firsName, String lastName) {
        super(firsName, lastName);
    }

    public Houses getHouse() {
        return this.house;
    }

    public void setHouse(Houses house) {
        this.house = house;
    }
}
