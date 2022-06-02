package academy.pocu.comp2500.assignment2;

public class Product {
    protected ProductType productType;
    protected int widthMillimeter;
    protected int heightMillimeter;
    protected int price;
    protected DeliveryType deliveryType;

    public Product(ProductType type) {
        this.productType = type;
        this.deliveryType = DeliveryType.PICKUP;
    }

    public int getWidthMillimeter() {
        return this.widthMillimeter;
    }

    public int getHeightMillimeter() {
        return this.heightMillimeter;
    }

    public String getProductType() {
        switch (this.productType) {
            case STAMP:
                return "Stamp";
            case CALENDAR:
                return "Calendar";
            case BANNER:
                return "Banner";
            case BUSINESS_CARD:
                return "Business Card";
            default:
                assert false : "Unset or unknown product type";
                return "";
        }
    }

    public DeliveryType getDeliveryType() {
        return this.deliveryType;
    }

    public void setDeliveryType(DeliveryType type) {
        this.deliveryType = type;
    }

    public int getPrice() {
        return this.price;
    }
}
