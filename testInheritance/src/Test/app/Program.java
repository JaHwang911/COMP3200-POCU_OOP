package Test.app;

import Test.*;

public class Program {
    public static void main(String[] args) {
        testStudent();
        testTeacher();
        testGetClass();
        testTrolling();

        Student student = new Student("Ja", "Hwnag");
        System.out.println(student.toString());

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
        assert person0 instanceof PartTimeTeacher;

        if (teacher0 instanceof PartTimeTeacher) {
            fullTimeTeacher = null;
        } else {
            fullTimeTeacher = (FullTimeTeacher) teacher0;
        }

        assert fullTimeTeacher == null;
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

    private static void testTrolling() {
        Teacher teacher = new Teacher("harry", "poter", Department.WING);
        Student student = new Student("Ja", "Hwang");

        assert student.getEmail().equals("jhwang@hogwarts.academy");

        Person person = student;
        teacher.doMagic(person);
        assert student.getEmail().equals("troll@voldemort.crew");
    }
}
