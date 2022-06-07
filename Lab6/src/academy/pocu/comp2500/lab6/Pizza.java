package academy.pocu.comp2500.lab6;

import java.util.ArrayList;

public class Pizza extends Menu {
    protected static final int MAX_MEAT_COUNT = 2;
    protected static final int MAX_VEGGIE_COUNT = 2;
    protected static final int MAX_CHEESE_COUNT = 2;

    private final PizzaType pizzaType;
    protected final ArrayList<Topping> toppings;
    protected int meatCount;
    protected int veggieCount;
    protected int cheeseCount;
    protected boolean isVeggieAdded;
    protected boolean isCheeseAdded;

    protected Pizza(PizzaType type) {
        super(type);

        this.pizzaType = type;
        this.toppings = new ArrayList<>();
        this.meatCount = 0;
        this.veggieCount = 0;
        this.cheeseCount = 0;
        this.isVeggieAdded = false;
        this.isCheeseAdded = false;

        switch (this.pizzaType) {
            case HOUSE:
                this.toppings.add(Topping.BLACK_OLIVES);
                this.toppings.add(Topping.RED_ONIONS);
                this.toppings.add(Topping.GREEN_PEPPERS);
                this.toppings.add(Topping.MOZZARELLA_CHEESE);
                break;
            case MEAT_LOVER:
                this.toppings.add(Topping.BACON);
                this.toppings.add(Topping.PEPERONI);
                this.toppings.add(Topping.HAM);
                this.toppings.add(Topping.SAUSAGES);
                this.toppings.add(Topping.CHEDDAR_CHEESE);
                break;
            case VEGGIE:
                this.toppings.add(Topping.BLACK_OLIVES);
                this.toppings.add(Topping.RED_ONIONS);
                this.toppings.add(Topping.GREEN_PEPPERS);
                break;
            case FREE_SOUL:
                break;
            default:
                assert false : "Unknown pizza type";
                break;
        }
    }

    public ArrayList<Topping> getToppings() {
        return this.toppings;
    }

    public boolean isValid() {
        switch (this.pizzaType) {
            case HOUSE:
                return this.meatCount == MAX_MEAT_COUNT;
            case MEAT_LOVER:
                return this.isVeggieAdded;
            case VEGGIE:
                return this.cheeseCount == MAX_CHEESE_COUNT;
            case FREE_SOUL:
                return this.meatCount == MAX_MEAT_COUNT
                        && this.veggieCount == MAX_VEGGIE_COUNT
                        && this.isCheeseAdded;
            default:
                assert false : "Unknown pizza type";
                return false;
        }
    }
}
