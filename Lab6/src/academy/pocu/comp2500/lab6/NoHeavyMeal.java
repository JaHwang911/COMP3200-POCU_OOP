package academy.pocu.comp2500.lab6;

public class NoHeavyMeal extends Course {
    private static final int MAX_APPETIZER_COUNT = 2;
    private static final int MAX_MAIN_COURSE_COUNT = 0;
    private static final int MAX_DESSERT_COUNT = 1;

    public NoHeavyMeal() {
        super(CourseType.NO_HEAVY_MEAL, MAX_APPETIZER_COUNT, MAX_MAIN_COURSE_COUNT, MAX_DESSERT_COUNT);
    }

    public void setAppetizers(Appetizer appetizer1, Appetizer appetizer2) {
        this.appetizers.clear();

        this.appetizers.add(appetizer1);
        this.appetizers.add(appetizer2);
    }

    public void setDessert(Dessert dessert) {
        this.desserts.clear();

        this.desserts.add(dessert);
    }
}
