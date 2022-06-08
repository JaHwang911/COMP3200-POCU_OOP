package academy.pocu.comp2500.lab6;

import java.util.ArrayList;

public class Course extends Menu {
    private final int maxAppetizerCount;
    private final int maxMainCourseCount;
    private final int maxDessertCount;
    protected ArrayList<Appetizer> appetizers;
    protected ArrayList<MainCourse> mainCourses;
    protected ArrayList<Dessert> desserts;

    protected Course(CourseType type, int maxAppetizerCount, int maxMainCourseCount, int maxDessertCount) {
        super(type);

        this.maxAppetizerCount = maxAppetizerCount;
        this.maxMainCourseCount = maxMainCourseCount;
        this.maxDessertCount = maxDessertCount;
        this.appetizers = new ArrayList<>();
        this.mainCourses = new ArrayList<>();
        this.desserts = new ArrayList<>();
    }

    public boolean isValid() {
        return this.appetizers.size() == maxAppetizerCount
                && this.mainCourses.size() == maxMainCourseCount
                && this.desserts.size() == maxDessertCount;
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
