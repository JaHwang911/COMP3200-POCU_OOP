package test.app;

import test.FullTimeTeacher;
import test.PartTimeTeacher;
import test.Teacher;

import java.util.HashSet;

public class Program {
    public static void main(String[] args) {
        FullTimeTeacher full0 = new FullTimeTeacher("ja", "hwang", "Wing", 1);
        FullTimeTeacher full1 = new FullTimeTeacher("ja", "hwang", "Wing", 1);
        FullTimeTeacher full2 = full0;
        FullTimeTeacher full3 = new FullTimeTeacher("ja", "hwang", "Wing", 2);
        FullTimeTeacher full4 = new FullTimeTeacher("ja", "hwang", "Black", 1);
        FullTimeTeacher full5 = new FullTimeTeacher("ja", "Hwang", "Wing", 1);

        assert full0.equals(full1);
        assert full0.equals(full2);
        assert !full0.equals(full3);
        assert !full0.equals(full4);
        assert !full0.equals(full5);

        HashSet<Teacher> teachers = new HashSet<>();
        assert teachers.add(full0);
        assert !teachers.add(full1);
        assert !teachers.add(full2);
        assert teachers.add(full3);
        assert teachers.add(full4);
        assert teachers.add(full5);

        PartTimeTeacher part0 = new PartTimeTeacher("ja", "hwang", "Chemistry", 6);
        PartTimeTeacher part1 = new PartTimeTeacher("ja", "hwang", "Chemistry", 6);
        PartTimeTeacher part2 = new PartTimeTeacher("ja", "hwang", "Chemistry", 10);
        PartTimeTeacher part3 = new PartTimeTeacher("ja", "hwang", "History", 6);
        PartTimeTeacher part4 = new PartTimeTeacher("Ja", "hwang", "History", 6);

        assert part0.equals(part1);
        assert !part0.equals(part2);
        assert !part0.equals(part3);
        assert !part0.equals(part4);

        teachers.clear();

        assert teachers.add(part0);
        assert !teachers.add(part1);
        assert teachers.add(part2);
        assert teachers.add(part3);
        assert teachers.add(part4);

        System.out.println("No prob: test interface");
    }
}
