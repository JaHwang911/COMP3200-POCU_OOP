package test.app;

import test.DepartmentType;
import test.Teacher;

public class Program {
    public static void main(String[] args) {
        testEquals();

        System.out.println("No prob: equals test");
    }

    private static void testEquals() {
        Teacher teacher0 = new Teacher("Ja", "Hwang", DepartmentType.WING);
        Teacher teacher1 = new Teacher("Ja", "Hwang", DepartmentType.WING);
        Teacher teacher2 = new Teacher("Ja", "Hwang", DepartmentType.BLACK);
        Teacher teacher3 = new Teacher("Baro", "Kim", DepartmentType.WING);
        Teacher teacher4 = new Teacher("Charlie", "Puth", DepartmentType.CHEMISTRY);

        assert teacher0.equals(teacher1);
        assert !teacher0.equals(teacher2);
        assert !teacher0.equals(teacher3);
        assert !teacher0.equals(teacher4);
    }
}
