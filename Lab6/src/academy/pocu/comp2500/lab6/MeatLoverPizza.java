package academy.pocu.comp2500.lab6;

public class MeatLoverPizza extends Pizza {
    private static final int MAX_MEAT_COUNT = 0;
    private static final int MAX_VEGGIE_COUNT = 1;
    private static final int MAX_CHEESE_COUNT = 0;

    public MeatLoverPizza() {
        super(PizzaType.MEAT_LOVER, MAX_MEAT_COUNT, MAX_VEGGIE_COUNT, MAX_CHEESE_COUNT);
    }

    public boolean addBlackOlives() {
        if (isValid()) {
            return false;
        }

        super.toppings.add(Topping.BLACK_OLIVES);
        ++super.veggieCount;
        return true;
    }

    public boolean removeBlackOlives() {
        boolean isRemoved = super.toppings.remove(Topping.BLACK_OLIVES);

        if (isRemoved) {
            --super.veggieCount;
        }

        return isRemoved;
    }

    public boolean addRedOnions() {
        if (isValid()) {
            return false;
        }

        super.toppings.add(Topping.RED_ONIONS);
        ++super.veggieCount;
        return true;
    }

    public boolean removeRedOnions() {
        boolean isRemoved = super.toppings.remove(Topping.RED_ONIONS);

        if (isRemoved) {
            --super.veggieCount;
        }

        return isRemoved;
    }

    public boolean addGreenPeppers() {
        if (isValid()) {
            return false;
        }

        super.toppings.add(Topping.GREEN_PEPPERS);
        ++super.veggieCount;
        return true;
    }

    public boolean removeGreenPeppers() {
        boolean isRemoved = super.toppings.remove(Topping.GREEN_PEPPERS);

        if (isRemoved) {
            --super.veggieCount;
        }

        return isRemoved;
    }
}
