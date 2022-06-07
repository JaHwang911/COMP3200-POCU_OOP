package academy.pocu.comp2500.lab6;

public class VeggiePizza extends Pizza {
    public VeggiePizza() {
        super(PizzaType.VEGGIE);
    }

    public boolean addMozzarellaCheese() {
        if (isValid()) {
            return false;
        }

        super.toppings.add(Topping.MOZZARELLA_CHEESE);
        ++super.cheeseCount;
        return true;
    }

    public boolean removeMozzarellaCheese() {
        boolean isRemoved = super.toppings.remove(Topping.MOZZARELLA_CHEESE);

        if (isRemoved) {
            --super.cheeseCount;
        }

        return isRemoved;
    }

    public boolean addCheddarCheese() {
        if (isValid()) {
            return false;
        }

        super.toppings.add(Topping.CHEDDAR_CHEESE);
        ++super.cheeseCount;
        return true;
    }

    public boolean removeCheddarCheese() {
        boolean isRemoved = super.toppings.remove(Topping.CHEDDAR_CHEESE);

        if (isRemoved) {
            --super.cheeseCount;
        }

        return isRemoved;
    }

    public boolean addFetaCheese() {
        if (isValid()) {
            return false;
        }

        super.toppings.add(Topping.FETA_CHEESE);
        ++super.cheeseCount;
        return true;
    }

    public boolean removeFetaCheese() {
        boolean isRemoved = super.toppings.remove(Topping.FETA_CHEESE);

        if (isRemoved) {
            --super.cheeseCount;
        }

        return isRemoved;
    }
}
