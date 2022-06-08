package academy.pocu.comp2500.lab6;

public class FreeSoulPizza extends Pizza {
    private static final int MAX_MEAT_COUNT = 2;
    private static final int MAX_VEGGIE_COUNT = 2;
    private static final int MAX_CHEESE_COUNT = 1;

    public FreeSoulPizza() {
        super(PizzaType.FREE_SOUL, MAX_MEAT_COUNT, MAX_VEGGIE_COUNT, MAX_CHEESE_COUNT);
    }

    public boolean addTopping(Topping topping) {
        if ((isMeat(topping) && super.meatCount >= MAX_MEAT_COUNT)
                || (isVeggie(topping) && super.veggieCount >= MAX_VEGGIE_COUNT)
                || (isCheese(topping) && super.cheeseCount >= MAX_CHEESE_COUNT)) {
            return false;
        }

        super.toppings.add(topping);

        if (isMeat(topping)) {
            ++super.meatCount;
        }

        if (isVeggie(topping)) {
            ++super.veggieCount;
        }

        if (isCheese(topping)) {
            ++super.cheeseCount;
        }

        return true;
    }

    public boolean removeTopping(Topping topping) {
        boolean isRemoved = super.toppings.remove(topping);

        if (isRemoved) {
            if (isMeat(topping)) {
                --super.meatCount;
            }

            if (isVeggie(topping)) {
                --super.veggieCount;
            }

            if (isCheese(topping)) {
                --super.cheeseCount;
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
