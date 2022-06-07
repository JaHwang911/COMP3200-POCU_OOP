package academy.pocu.comp2500.lab6;

public class MeatLoverPizza extends Pizza {
    public MeatLoverPizza() {
        super(PizzaType.MEAT_LOVER);
    }

    public boolean addBlackOlives() {
        if (isValid()) {
            return false;
        }

        super.toppings.add(Topping.BLACK_OLIVES);
        super.isVeggieAdded = true;
        return true;
    }

    public boolean removeBlackOlives() {
        boolean isRemoved = super.toppings.remove(Topping.BLACK_OLIVES);

        if (isRemoved) {
            super.isVeggieAdded = false;
        }

        return isRemoved;
    }

    public boolean addRedOnions() {
        if (isValid()) {
            return false;
        }

        super.toppings.add(Topping.RED_ONIONS);
        super.isVeggieAdded = true;
        return true;
    }

    public boolean removeRedOnions() {
        boolean isRemoved = super.toppings.remove(Topping.RED_ONIONS);

        if (isRemoved) {
            super.isVeggieAdded = false;
        }

        return isRemoved;
    }

    public boolean addGreenPeppers() {
        if (isValid()) {
            return false;
        }

        super.toppings.add(Topping.GREEN_PEPPERS);
        super.isVeggieAdded = true;
        return true;
    }

    public boolean removeGreenPeppers() {
        boolean isRemoved = super.toppings.remove(Topping.GREEN_PEPPERS);

        if (isRemoved) {
            super.isVeggieAdded = false;
        }

        return isRemoved;
    }
}
