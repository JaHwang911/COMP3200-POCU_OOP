package academy.pocu.comp2500.lab6;

import java.util.ArrayList;

public class Pizza extends Menu {
    protected final ArrayList<Topping> toppings;

    protected Pizza(PizzaType type) {
        super(type);

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

    public ArrayList<Topping> getToppings() {
        return this.toppings;
    }
}
