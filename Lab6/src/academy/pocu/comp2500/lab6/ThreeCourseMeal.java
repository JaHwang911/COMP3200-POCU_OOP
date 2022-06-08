package academy.pocu.comp2500.lab6;

public class ThreeCourseMeal extends Course {
    private static final int MAX_APPETIZER_COUNT = 1;
    private static final int MAX_MAIN_COURSE_COUNT = 1;
    private static final int MAX_DESSERT_COUNT = 1;

    public ThreeCourseMeal() {
        super(CourseType.THREE_COURSE_MEAL, MAX_APPETIZER_COUNT, MAX_MAIN_COURSE_COUNT, MAX_DESSERT_COUNT);
    }

    public void setMainCourse(MainCourse mainCourse) {
        super.mainCourses.clear();

        super.mainCourses.add(mainCourse);
    }

    public void setAppetizer(Appetizer appetizer) {
        super.appetizers.clear();

        super.appetizers.add(appetizer);
    }

    public void setDessert(Dessert dessert) {
        super.desserts.clear();

        super.desserts.add(dessert);
    }
}
