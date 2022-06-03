package academy.pocu.comp2500.assignment2;

public class Product {
    protected int price;
    protected int width;
    protected int height;
    protected DeliveryMethod deliveryMethod = DeliveryMethod.PICKUP;

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
