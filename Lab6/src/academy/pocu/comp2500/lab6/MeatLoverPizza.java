package academy.pocu.comp2500.lab6;

public class MeatLoverPizza extends Pizza {
    private boolean isVeggieAdded;

    public MeatLoverPizza() {
        super(PizzaType.MEAT_LOVER);
    }

    public boolean isValid() {
        return this.isVeggieAdded;
    }

    public boolean addBlackOlives() {
        if (isValid()) {
            return false;
        }

        this.toppings.add(Topping.BLACK_OLIVES);
        this.isVeggieAdded = true;
        return true;
    }

    public boolean removeBlackOlives() {
        boolean isRemoved = this.toppings.remove(Topping.BLACK_OLIVES);

        if (isRemoved) {
            this.isVeggieAdded = false;
        }

        return isRemoved;
    }

    public boolean addRedOnions() {
        if (isValid()) {
            return false;
        }

        this.toppings.add(Topping.RED_ONIONS);
        this.isVeggieAdded = true;
        return true;
    }

    public boolean removeRedOnions() {
        boolean isRemoved = this.toppings.remove(Topping.RED_ONIONS);

        if (isRemoved) {
            this.isVeggieAdded = false;
        }

        return isRemoved;
    }

    public boolean addGreenPeppers() {
        if (isValid()) {
            return false;
        }

        this.toppings.add(Topping.GREEN_PEPPERS);
        this.isVeggieAdded = true;
        return true;
    }

    public boolean removeGreenPeppers() {
        boolean isRemoved = this.toppings.remove(Topping.GREEN_PEPPERS);

        if (isRemoved) {
            this.isVeggieAdded = false;
        }

        return isRemoved;
    }
}
