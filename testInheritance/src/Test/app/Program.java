package Test.app;

import Test.*;

public class Program {
    public static void main(String[] args) {
        testStudent();
        testTeacher();
        testGetClass();

        System.out.println("Inheritance No prob");
    }

    private static void testStudent() {
        Student student = new Student("Ja", "Hwang");
        Person person = student;
        Student actuallyStudent = (Student) person;

        actuallyStudent.setHouse(Houses.HUFFLEPUFF);

        assert student.getName().equals("Ja Hwang");
        assert student.getHouse() == Houses.HUFFLEPUFF;
    }

    private static void testTeacher() {
        PartTimeTeacher partTimeTeacher0 = new PartTimeTeacher("Ja", "Hwang", Department.WING);
        Person person0 = partTimeTeacher0;
        Teacher teacher0 = (Teacher) person0;
        FullTimeTeacher fullTimeTeacher;

        assert teacher0 instanceof PartTimeTeacher;

        if (teacher0 instanceof PartTimeTeacher) {
            fullTimeTeacher = null;
        } else {
            fullTimeTeacher = (FullTimeTeacher) teacher0;
        }

        assert teacher0.getName().equals("Ja Hwang");
    }

    private static void testGetClass() {
        Student student = new Student("Ja", "Hwang");
        Person person = student;

        System.out.println(student.getClass().getName());
        System.out.println(person.getClass().getName());

        PartTimeTeacher partTimeTeacher0 = new PartTimeTeacher("Ja", "Hwang", Department.WING);
        Person person0 = partTimeTeacher0;
        Teacher teacher0 = (Teacher) person0;

        assert teacher0.getClass().getName().equals("Test.PartTimeTeacher");
    }
}
