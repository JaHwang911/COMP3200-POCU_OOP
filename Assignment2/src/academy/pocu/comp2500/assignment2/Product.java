package academy.pocu.comp2500.assignment2;

public class Product {
    protected int widthMillimeter;
    protected int heightMillimeter;
    protected int price;

    public Product(int width, int height) {
        this.widthMillimeter = width;
        this.heightMillimeter = height;
    }

    public int getWidthMillimeter() {
        return this.widthMillimeter;
    }

    public int getHeightMillimeter() {
        return this.heightMillimeter;
    }

    public int getSize() {
        return this.widthMillimeter * this.heightMillimeter;
    }

    public int getPrice() {
        return this.price;
    }
}
