package academy.pocu.comp2500.lab6;

public class Menu {
    protected final int price;

    protected Menu(PizzaType pizzaType) {
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

    protected Menu(CourseType courseType) {
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
}
