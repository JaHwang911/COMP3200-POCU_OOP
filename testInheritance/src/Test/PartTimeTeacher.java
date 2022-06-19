package Test;

public class PartTimeTeacher extends Teacher {
    private int officeNumber;

    public PartTimeTeacher(String firstName, String lastName, Department department) {
        super(firstName, lastName, department);
    }

    public int getOfficeNumber() {
        return this.officeNumber;
    }

    public void setOfficeNumber(int officeNumber) {
        this.officeNumber = officeNumber;
    }
}
