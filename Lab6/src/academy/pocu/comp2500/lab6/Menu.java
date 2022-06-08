package academy.pocu.comp2500.lab6;

public class Menu {
    protected final int price;
    protected final int maxMeatCount;
    protected final int maxVeggieCount;
    protected final int maxCheeseCount;
    protected int meatCount;
    protected int veggieCount;
    protected int cheeseCount;

    private final int maxAppetizerCount;
    private final int maxMainCourseCount;
    private final int maxDessertCount;
    protected int appetizerCount;
    protected int mainCourseCount;
    protected int dessertCount;
    private final boolean isPizza;

    protected Menu(PizzaType pizzaType, int maxMeatCount, int maxVeggieCount, int maxCheeseCount) {
        this.isPizza = true;

        this.maxMeatCount = maxMeatCount;
        this.maxVeggieCount = maxVeggieCount;
        this.maxCheeseCount = maxCheeseCount;

        this.maxAppetizerCount = 0;
        this.maxMainCourseCount = 0;
        this.maxDessertCount = 0;

        switch (pizzaType) {
            case HOUSE:
                this.price = 20;
                break;
            case MEAT_LOVER:
                this.price = 21;
                break;
            case VEGGIE:
                this.price = 17;
                break;
            case FREE_SOUL:
                this.price = 25;
                break;
            default:
                this.price = -1;
                assert false : "Unknown pizza type";
                break;
        }
    }

    protected Menu(CourseType courseType, int maxAppetizerCount, int maxMainCourseCount, int maxDessertCount) {
        this.isPizza = false;

        this.maxAppetizerCount = maxAppetizerCount;
        this.maxMainCourseCount = maxMainCourseCount;
        this.maxDessertCount = maxDessertCount;

        this.maxMeatCount = 0;
        this.maxVeggieCount = 0;
        this.maxCheeseCount = 0;

        switch (courseType) {
            case NO_HEAVY_MEAL:
                this.price = 15;
                break;
            case THREE_COURSE_MEAL:
                this.price = 25;
                break;
            case DEATH_BY_DESSERTS:
                this.price = 20;
                break;
            default:
                this.price = -1;
                assert false : "Unknown course type";
                break;
        }
    }

    public int getPrice() {
        return this.price;
    }

    public boolean isValid() {
        if (this.isPizza) {
            return this.meatCount == this.maxMeatCount
                    && this.veggieCount == this.maxVeggieCount
                    && this.cheeseCount == this.maxCheeseCount;
        }

        return this.appetizerCount == this.maxAppetizerCount
                && this.mainCourseCount == this.maxMainCourseCount
                && this.dessertCount == this.maxDessertCount;
    }
}
