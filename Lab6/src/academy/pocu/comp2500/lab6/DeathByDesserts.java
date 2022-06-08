package academy.pocu.comp2500.lab6;

import java.util.ArrayList;

public class DeathByDesserts extends Course {
    private static final int MAX_APPETIZER_COUNT = 0;
    private static final int MAX_MAIN_COURSE_COUNT = 0;
    private static final int MAX_DESSERT_COUNT = 4;

    public DeathByDesserts() {
        super(CourseType.DEATH_BY_DESSERTS, MAX_APPETIZER_COUNT, MAX_MAIN_COURSE_COUNT, MAX_DESSERT_COUNT);
    }

    public void setDesserts(Dessert dessert1, Dessert dessert2, Dessert dessert3, Dessert dessert4) {
        this.desserts.clear();

        this.desserts.add(dessert1);
        this.desserts.add(dessert2);
        this.desserts.add(dessert3);
        this.desserts.add(dessert4);
    }
}
