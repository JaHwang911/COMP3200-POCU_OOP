package academy.pocu.comp2500.lab6;

import java.util.ArrayList;

public class DeathByDesserts extends Course {
    private boolean isValid;
    private ArrayList<Dessert> desserts = new ArrayList<>();

    public DeathByDesserts() {
        super(CourseType.DEATH_BY_DESSERTS);
    }

    public boolean isValid() {
        return this.isValid;
    }

    public ArrayList<Dessert> getDesserts() {
        return this.desserts;
    }

    public void setDesserts(Dessert dessert1, Dessert dessert2, Dessert dessert3, Dessert dessert4) {
        this.desserts.clear();

        this.desserts.add(dessert1);
        this.desserts.add(dessert2);
        this.desserts.add(dessert3);
        this.desserts.add(dessert4);
        this.isValid = true;
    }
}
