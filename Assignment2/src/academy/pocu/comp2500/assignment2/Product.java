package academy.pocu.comp2500.assignment2;

public class Product {
    protected int red;
    protected int blue;
    protected int green;
    protected int width;
    protected int height;
    protected int price;
    protected DeliveryMethod deliveryMethod = DeliveryMethod.PICKUP;

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getRed() {
        return this.red;
    }

    public int getGreen() {
        return this.green;
    }

    public int getBlue() {
        return this.blue;
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
