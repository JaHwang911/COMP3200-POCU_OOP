package academy.pocu.comp2500.assignment2;

import java.util.ArrayList;

public class Product {
    protected Color color;
    protected int width;
    protected int height;
    protected final ArrayList<Aperture> textApertures;
    protected final ArrayList<Aperture> imageApertures;
    protected int price;
    protected DeliveryMethod deliveryMethod = DeliveryMethod.PICKUP;

    protected Product() {
        this.textApertures = new ArrayList<>();
        this.imageApertures = new ArrayList<>();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Color getColor() {
        return this.color;
    }

    public int getPrice() {
        return this.price;
    }

    public DeliveryMethod getDeliveryType() {
        return this.deliveryMethod;
    }

    public void setDeliveryType(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }
}
