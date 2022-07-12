package academy.pocu.comp2500;

import java.util.UUID;

public final class Book {
    private final UUID sku;
    private final String title;
    private final int price;
    private final int publishedYear;

    public Book(UUID sku, String title, int price, int publishedYear) {
        this.sku = sku;
        this.title = title;
        this.price = price;
        this.publishedYear = publishedYear;
    }

    public int getPrice() {
        return this.price;
    }

    public int getPublishedYear() {
        return this.publishedYear;
    }

    public String getTitle() {
        return this.title;
    }

    public UUID getSku() {
        return this.sku;
    }

    @Override
    public int hashCode() {
        return this.sku.toString().hashCode();
    }
}
