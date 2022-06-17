package Test;

public class Student extends Person {
    private Houses house;

    public Student(String firsName, String lastName, Houses house) {
        super(firsName, lastName);

        this.house = house;
    }

    public Houses getHouse() {
        return this.house;
    }
}
