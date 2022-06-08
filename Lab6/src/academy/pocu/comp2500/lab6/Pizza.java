package academy.pocu.comp2500.lab6;

import java.util.ArrayList;

public class Pizza extends Menu {
    protected final int maxMeatCount;
    protected final int maxVeggieCount;
    protected final int maxCheeseCount;
    protected int meatCount;
    protected int veggieCount;
    protected int cheeseCount;
    protected final ArrayList<Topping> toppings;


    protected Pizza(PizzaType type, int maxMeatCount, int maxVeggieCount, int maxCheeseCount) {
        super(type);

        this.maxMeatCount = maxMeatCount;
        this.maxVeggieCount = maxVeggieCount;
        this.maxCheeseCount = maxCheeseCount;
        this.toppings = new ArrayList<>();

        setDefaultTopping(type);
    }

    public boolean isValid() {
        return this.meatCount == this.maxMeatCount
                && this.veggieCount == this.maxVeggieCount
                && this.cheeseCount == this.maxCheeseCount;
    }

    public ArrayList<Topping> getToppings() {
        return this.toppings;
    }

    private void setDefaultTopping(PizzaType type) {
        switch(type) {
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
                // intentional fallthrough
            case FREE_SOUL:
                break;
            default:
                assert false : "Unknown pizza type";
                break;
        }
    }
}
