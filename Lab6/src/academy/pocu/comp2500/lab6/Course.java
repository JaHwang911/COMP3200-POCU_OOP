package academy.pocu.comp2500.lab6;

import java.util.ArrayList;

public class Course extends Menu {
    protected ArrayList<Appetizer> appetizers;
    protected ArrayList<MainCourse> mainCourses;
    protected ArrayList<Dessert> desserts;

    protected Course(CourseType type, int maxAppetizerCount, int maxMainCourseCount, int maxDessertCount) {
        super(type, maxAppetizerCount, maxMainCourseCount, maxDessertCount);

        this.appetizers = new ArrayList<>();
        this.mainCourses = new ArrayList<>();
        this.desserts = new ArrayList<>();
    }

    public ArrayList<Appetizer> getAppetizers() {
        return this.appetizers;
    }

    public ArrayList<MainCourse> getMainCourses() {
        return this.mainCourses;
    }

    public ArrayList<Dessert> getDesserts() {
        return this.desserts;
    }
}
