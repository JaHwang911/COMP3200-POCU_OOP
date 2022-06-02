package academy.pocu.comp2500.assignment2;

public class Product {
    protected ProductType productType;
    protected int widthMillimeter;
    protected int heightMillimeter;
    protected int price;
    protected DeliveryMethod deliveryMethod;

    public Product(ProductType type) {
        this.productType = type;
        this.deliveryMethod = DeliveryMethod.PICKUP;
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

    public DeliveryMethod getDeliveryType() {
        return this.deliveryMethod;
    }

    public void setDeliveryType(DeliveryMethod type) {
        this.deliveryMethod = type;
    }

    public int getPrice() {
        return this.price;
    }
}
