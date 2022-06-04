package academy.pocu.comp2500.assignment2;

import java.util.ArrayList;

public class Cart {
    private final ArrayList<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public int getProductsCount() {
        return this.products.size();
    }

    public ArrayList<Product> getTotalProducts() {
        return this.products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public boolean removeProduct(Product product) {
        return this.products.remove(product);
    }

    public int getTotalPrice() {
        int totalPrice = 0;

        for (Product product : this.products) {
            totalPrice += product.getPrice();
        }

        return totalPrice;
    }
}
