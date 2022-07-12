package academy.pocu.comp2500.lab9.app;

import academy.pocu.comp2500.Book;
import academy.pocu.comp2500.BuyOneGetOneFree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

public class Program {

    public static void main(String[] args) {
        UUID sku0 = UUID.randomUUID();
        Book book0 = new Book(sku0, "Event horizon", 20000, 2009);
        Book book1 = new Book(sku0, "Event horizon", 20000, 2009);
        Book book2 = new Book(sku0, "Event horizon", 20000, 2009);
        Book book3 = new Book(UUID.randomUUID(), "Unreal", 30000, 2009);

        ArrayList<Book> books = new ArrayList<>();
        books.add(book0);
        assert books.contains(book1);
        books.add(book1);
        books.add(book2);
        books.add(book3);

        HashSet<UUID> skus = new HashSet<>();
        skus.add(sku0);
        BuyOneGetOneFree pricingModel = new BuyOneGetOneFree(skus);

        int price = pricingModel.getPrice(books);

        System.out.printf("Price: %d%s", price, System.lineSeparator());
    }
}
