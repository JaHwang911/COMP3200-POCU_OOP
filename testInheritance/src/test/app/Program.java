package test.app;

import test.*;

public class Program {
    public static void main(String[] airgs) {
        Student Ja = new Student("Ja", "Hwang", Houses.HUFFLEPUFF);

        Student harry = new Student("Harry", "Potter", Houses.GRYFFINDOR);
        Student lon = new Student("Ron", "Weasley", Houses.GRYFFINDOR);
        Student hermione = new Student("Hermione", "Granger", Houses.GRYFFINDOR);

        Teacher hagird = new Teacher("Rubeus", "Hagrid", Department.MATERIAL);

        Person corruption = Ja;
        Student promotion = (Student) corruption;

        System.out.println(promotion.getHouse());

        System.out.println("No prob");
    }
}
