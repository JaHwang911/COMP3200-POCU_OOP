package academy.pocu.comp2500.lab6;

import java.util.ArrayList;

public class Pizza extends Menu {
    protected final ArrayList<Topping> toppings;

    protected Pizza(PizzaType type) {
        super(type);

        this.toppings = new ArrayList<>();
    }

    public ArrayList<Topping> getToppings() {
        return this.toppings;
    }
}
