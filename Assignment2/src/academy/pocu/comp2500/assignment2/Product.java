package academy.pocu.comp2500.assignment2;

public class Product {
    protected ProductType productType = ProductType.UNSET;
    protected int widthMillimeter;
    protected int heightMillimeter;
    protected int price;

    public int getWidthMillimeter() {
        return this.widthMillimeter;
    }

    public int getHeightMillimeter() {
        return this.heightMillimeter;
    }

    public ProductType getProductType() {
        return this.productType;
    }

    public int getPrice() {
        return this.price;
    }
}
