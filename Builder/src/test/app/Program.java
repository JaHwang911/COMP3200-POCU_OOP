package test.app;

import test.EmployeeBuilder;

public class Program {
    public static void main(String[] args) {
        EmployeeBuilder employee = new EmployeeBuilder(1)
                .withAge(28)
                .withName("Hi")
                .withStartingYear(2022)
                .build();

        assert employee.getName().equals("Hi");
        assert (employee.getAge() == 28);
        assert (employee.getStartingYear() == 2022);
        assert (employee.getId() == 1);

        System.out.println("No prob: Builder");
    }
}
