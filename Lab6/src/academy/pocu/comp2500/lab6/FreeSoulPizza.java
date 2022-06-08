package academy.pocu.comp2500.lab6;

public class FreeSoulPizza extends Pizza {
    private static final int MAX_MEAT_COUNT = 2;
    private static final int MAX_VEGGIE_COUNT = 2;
    private static final int MAX_CHEESE_COUNT = 1;

    public FreeSoulPizza() {
        super(PizzaType.FREE_SOUL, MAX_MEAT_COUNT, MAX_VEGGIE_COUNT, MAX_CHEESE_COUNT);
    }
}
