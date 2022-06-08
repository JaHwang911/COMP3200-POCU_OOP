package academy.pocu.comp2500.lab6;

import java.util.ArrayList;

public class Course extends Menu {
    protected Course(CourseType type, int maxAppetizerCount, int maxMainCourseCount, int maxDessertCount) {
        super(type, maxAppetizerCount, maxMainCourseCount, maxDessertCount);
    }

    public ArrayList<Appetizer> getAppetizers() {
        return super.appetizers;
    }

    public ArrayList<MainCourse> getMainCourses() {
        return super.mainCourses;
    }

    public ArrayList<Dessert> getDesserts() {
        return super.desserts;
    }
}
