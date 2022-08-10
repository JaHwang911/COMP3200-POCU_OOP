package test;

public class FullTimeTeacher extends Teacher {
    private int officeNumber;

    public FullTimeTeacher(String firstName, String lastName, Department department) {
        super(firstName, lastName, department);
    }

    public int getOfficeNumber() {
        return this.officeNumber;
    }

    public void setOfficeNumber(int officeNumber) {
        this.officeNumber = officeNumber;
    }

    public void teaching() {

    }
}
