package academy.pocu.comp2500.lab6;

public class HousePizza extends Pizza {
    private static final int MAX_MEAT_COUNT = 2;
    private static final int MAX_VEGGIE_COUNT = 0;
    private static final int MAX_CHEESE_COUNT = 0;

    public HousePizza() {
        super(PizzaType.HOUSE, MAX_MEAT_COUNT, MAX_VEGGIE_COUNT, MAX_CHEESE_COUNT);
    }

    public boolean addBacon() {
        if (isValid()) {
            return false;
        }

        super.toppings.add(Topping.BACON);
        ++super.meatCount;
        return true;
    }

    public boolean removeBacon() {
        boolean isRemoved = super.toppings.remove(Topping.BACON);

        if (isRemoved) {
            --super.meatCount;
        }

        return isRemoved;
    }

    public boolean addPeperoni() {
        if (isValid()) {
            return false;
        }

        super.toppings.add(Topping.PEPERONI);
        ++super.meatCount;
        return true;
    }

    public boolean removePeperoni() {
        boolean isRemoved = super.toppings.remove(Topping.PEPERONI);

        if (isRemoved) {
            --super.meatCount;
        }

        return isRemoved;
    }

    public boolean addSausages() {
        if (isValid()) {
            return false;
        }

        super.toppings.add(Topping.SAUSAGES);
        ++super.meatCount;
        return true;
    }

    public boolean removeSausages() {
        boolean isRemoved = super.toppings.remove(Topping.SAUSAGES);

        if (isRemoved) {
            --super.meatCount;
        }

        return isRemoved;
    }
}
