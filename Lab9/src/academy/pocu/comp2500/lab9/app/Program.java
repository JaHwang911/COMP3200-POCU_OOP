package academy.pocu.comp2500.lab9.app;

import academy.pocu.comp2500.lab9.Book;
import academy.pocu.comp2500.lab9.BuyOneGetOneFree;
import academy.pocu.comp2500.lab9.DecadeMadness;
import academy.pocu.comp2500.lab9.IPricingModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

public class Program {

    public static void main(String[] args) {
        testBuyOneGetOneFree();
        testDecadeMadness();

        System.out.println("No prob: lab 9");
    }

    private static void testBuyOneGetOneFree() {
        UUID sku0 = UUID.randomUUID();
        UUID sku1 = UUID.randomUUID();

        Book book0 = new Book(sku0, "Event horizon", 10, 2009);
        Book book1 = new Book(sku0, "Event horizon", 10, 2009);
        Book book2 = new Book(sku0, "Event horizon", 10, 2009);
        Book book3 = new Book(sku0, "Event horizon", 10, 2009);
        Book book4 = new Book(sku0, "Event horizon", 10, 2009);
        Book book5 = new Book(sku1, "Unreal", 20, 2009);
        Book book6 = new Book(sku1, "Unreal", 20, 2009);
        Book book7 = new Book(UUID.randomUUID(), "Unity", 15, 2009);
        Book book8 = new Book(UUID.randomUUID(), "Unity", 15, 2009);

        ArrayList<Book> books = new ArrayList<>();
        books.add(book0);
        books.add(book1);
        books.add(book7);
        books.add(book2);
        books.add(book4);
        books.add(book5);
        books.add(book3);
        books.add(book6);
        books.add(book8);

        HashSet<UUID> skus = new HashSet<>();
        skus.add(sku0);
        skus.add(sku1);

        IPricingModel pricingModel = new BuyOneGetOneFree(skus);
        int price = pricingModel.getTotalPrice(books);

        assert price == 80;
    }

    private static void testDecadeMadness() {
        Book book0 = new Book(UUID.randomUUID(), "Galactic War", 10, 1991);
        Book book1 = new Book(UUID.randomUUID(), "You and me", 15, 1995);
        Book book2 = new Book(UUID.randomUUID(), "Me and you", 10, 1996);
        Book book3 = new Book(UUID.randomUUID(), "R2D2", 20, 2011);
        Book book4 = new Book(UUID.randomUUID(), "The Rebels", 20, 2003);
        Book book5 = new Book(UUID.randomUUID(), "Teehee", 10, 2001);
        Book book6 = new Book(UUID.randomUUID(), "Unreal", 28, 2022);

        ArrayList<Book> books = new ArrayList<>();
        books.add(book0);
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book6);

        IPricingModel pricingModel = new DecadeMadness();

        int price = pricingModel.getTotalPrice(books);

        assert price == 100;
    }
}
