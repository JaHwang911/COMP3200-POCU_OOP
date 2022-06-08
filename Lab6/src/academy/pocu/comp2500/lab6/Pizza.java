package academy.pocu.comp2500.lab6;

import java.util.ArrayList;

public class Pizza extends Menu {
    private final int maxMeatCount;
    private final int maxVeggieCount;
    private final int maxCheeseCount;
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

        switch (type) {
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

    public boolean isValid() {
        return this.meatCount == this.maxMeatCount
                && this.veggieCount == this.maxVeggieCount
                && this.cheeseCount == this.maxCheeseCount;
    }

    public ArrayList<Topping> getToppings() {
        return this.toppings;
    }

    public boolean addTopping(Topping topping) {
        if ((isMeat(topping) && this.meatCount >= this.maxMeatCount)
                || (isVeggie(topping) && this.veggieCount >= this.maxVeggieCount)
                || (isCheese(topping) && this.cheeseCount >= this.maxCheeseCount)) {
            return false;
        }

        this.toppings.add(topping);

        if (isMeat(topping)) {
            ++this.meatCount;
        }

        if (isVeggie(topping)) {
            ++this.veggieCount;
        }

        if (isCheese(topping)) {
            ++this.cheeseCount;
        }

        return true;
    }

    public boolean removeTopping(Topping topping) {
        boolean isRemoved = this.toppings.remove(topping);

        if (isRemoved) {
            if (isMeat(topping)) {
                --this.meatCount;
            }

            if (isVeggie(topping)) {
                --this.veggieCount;
            }

            if (isCheese(topping)) {
                --this.cheeseCount;
            }
        }

        return isRemoved;
    }

    private static boolean isMeat(Topping topping) {
        return topping == Topping.BACON
                || topping == Topping.CHICKEN
                || topping == Topping.PEPERONI
                || topping == Topping.SAUSAGES
                || topping == Topping.HAM;
    }

    private static boolean isVeggie(Topping topping) {
        return topping == Topping.BLACK_OLIVES
                || topping == Topping.RED_ONIONS
                || topping == Topping.GREEN_PEPPERS;
    }

    private static boolean isCheese(Topping topping) {
        return topping == Topping.MOZZARELLA_CHEESE
                || topping == Topping.CHEDDAR_CHEESE
                || topping == Topping.FETA_CHEESE;
    }
}
