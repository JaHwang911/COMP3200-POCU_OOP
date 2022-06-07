package academy.pocu.comp2500.lab6;

import java.util.ArrayList;
/*
    디저트는 모든 코스에 포함됨 그 부분만 일반화
    isValid 도 가능 할 듯
 */
public class Course extends Menu {
    protected ArrayList<Appetizer> appetizers;
    protected ArrayList<MainCourse> mainCourses;
    protected ArrayList<Dessert> desserts;

    protected Course(CourseType type) {
        super(type);
    }
}
