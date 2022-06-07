package academy.pocu.comp2500.lab6;

import java.util.ArrayList;

public class NoHeavyMeal extends Course {
    private ArrayList<Appetizer> appetizers;
    private Dessert dessert;

    public NoHeavyMeal() {
        super(CourseType.NO_HEAVY_MEAL);

        this.appetizers = new ArrayList<>();
    }

    public boolean isValid() {
        return this.appetizers.size() == 2 && this.dessert != null;
    }

    public ArrayList<Appetizer> getAppetizers() {
        return this.appetizers;
    }

    public Dessert getDessert() {
        assert (this.dessert != null) : "call isValid() first!";
        return this.dessert;
    }

    public void setAppetizers(Appetizer appetizer1, Appetizer appetizer2) {
        this.appetizers.clear();

        this.appetizers.add(appetizer1);
        this.appetizers.add(appetizer2);
    }

    public void setDessert(Dessert dessert) {
        this.dessert = dessert;
    }
}
