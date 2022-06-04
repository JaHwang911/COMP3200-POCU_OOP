package academy.pocu.comp2500.assignment2;

public class Product {
    protected Color color;
    protected int width;
    protected int height;
    protected int price;
    protected DeliveryMethod deliveryMethod = DeliveryMethod.PICKUP;

    protected Product() {
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
