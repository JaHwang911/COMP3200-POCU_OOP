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
    }

    public boolean isValid() {
        return this.meatCount == this.maxMeatCount
                && this.veggieCount == this.maxVeggieCount
                && this.cheeseCount == this.maxCheeseCount;
    }

    public ArrayList<Topping> getToppings() {
        return this.toppings;
    }
}
