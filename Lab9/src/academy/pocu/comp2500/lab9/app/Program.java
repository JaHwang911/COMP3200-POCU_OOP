package academy.pocu.comp2500.lab9.app;

import academy.pocu.comp2500.lab9.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

public class Program {

    public static void main(String[] args) {
        testBuyOneGetOneFree();
        testDecadeMadness();
        testSkyIsTheLimit();
        testSimplePricing();
        test5();
        test();

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
        assert (pricingModel.getTotalPrice(books)) == 80;
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
        assert (pricingModel.getTotalPrice(books)) == 100;
    }

    private static void testSkyIsTheLimit() {
        // most expensive two book
        Book book0 = new Book(UUID.randomUUID(), "Galactic War", 10, 1991);
        Book book1 = new Book(UUID.randomUUID(), "You and me", 15, 1995);
        Book book2 = new Book(UUID.randomUUID(), "Me and you", 10, 1996);
        Book book3 = new Book(UUID.randomUUID(), "R2D2", 20, 2011);
        Book book4 = new Book(UUID.randomUUID(), "The Rebels", 20, 2003);
        Book book5 = new Book(UUID.randomUUID(), "Teehee", 10, 2001);

        ArrayList<Book> books = new ArrayList<>();
        books.add(book0);
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

        IPricingModel pricingModel = new SkyIsTheLimit(86);
        assert (pricingModel.getTotalPrice(books) == 85);

        pricingModel = new SkyIsTheLimit(80);
        assert (pricingModel.getTotalPrice(books) == 65);

        pricingModel = new SkyIsTheLimit(85);
        assert (pricingModel.getTotalPrice(books) == 65);

        //All same prices
        Book book6 = new Book(UUID.randomUUID(), "Galactic War", 10, 1991);
        Book book7 = new Book(UUID.randomUUID(), "You and me", 10, 1995);
        Book book8 = new Book(UUID.randomUUID(), "Me and you", 10, 1996);
        Book book9 = new Book(UUID.randomUUID(), "R2D2", 10, 2011);
        Book book10 = new Book(UUID.randomUUID(), "The Rebels", 10, 2003);
        Book book11 = new Book(UUID.randomUUID(), "Teehee", 10, 2001);

        books.clear();
        books.add(book6);
        books.add(book7);
        books.add(book8);
        books.add(book9);
        books.add(book10);
        books.add(book11);

        pricingModel = new SkyIsTheLimit(65);
        assert (pricingModel.getTotalPrice(books) == 60);

        pricingModel = new SkyIsTheLimit(40);
        assert (pricingModel.getTotalPrice(books)) == 50;

        pricingModel = new SkyIsTheLimit(60);
        assert (pricingModel.getTotalPrice(books)) == 50;


        //All different prices
        Book book12 = new Book(UUID.randomUUID(), "Galactic War", 11, 1991);
        Book book13 = new Book(UUID.randomUUID(), "You and me", 12, 1995);
        Book book14 = new Book(UUID.randomUUID(), "Me and you", 13, 1996);
        Book book15 = new Book(UUID.randomUUID(), "R2D2", 14, 2011);
        Book book16 = new Book(UUID.randomUUID(), "The Rebels", 15, 2003);
        Book book17 = new Book(UUID.randomUUID(), "Teehee", 16, 2001);

        books.clear();
        books.add(book12);
        books.add(book13);
        books.add(book14);
        books.add(book15);
        books.add(book16);
        books.add(book17);

        pricingModel = new SkyIsTheLimit(100);
        assert (pricingModel.getTotalPrice(books)) == 81;

        pricingModel = new SkyIsTheLimit(40);
        assert (pricingModel.getTotalPrice(books) == 65);

        pricingModel = new SkyIsTheLimit(60);
        assert (pricingModel.getTotalPrice(books) == 65);
    }

    private static void testSimplePricing() {
        Book book0 = new Book(UUID.randomUUID(), "Galactic War", 10, 1991);
        Book book1 = new Book(UUID.randomUUID(), "You and me", 15, 1995);
        Book book2 = new Book(UUID.randomUUID(), "Me and you", 10, 1996);
        Book book3 = new Book(UUID.randomUUID(), "R2D2", 20, 2011);
        Book book4 = new Book(UUID.randomUUID(), "The Rebels", 20, 2003);
        Book book5 = new Book(UUID.randomUUID(), "Teehee", 10, 2001);

        ArrayList<Book> books = new ArrayList<>();
        books.add(book0);
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

        IPricingModel pricingModel = new SimplePricing();
        assert (pricingModel.getTotalPrice(books) == 85);
    }

    private static void test5() {
        SkyIsTheLimit model0 = new SkyIsTheLimit(50);
        SkyIsTheLimit model1 = new SkyIsTheLimit(100);
        SkyIsTheLimit model2 = new SkyIsTheLimit(150);

        Book book0 = new Book(UUID.randomUUID(), "Hello", 10, 1991);
        Book book1 = new Book(UUID.randomUUID(), "Hello", 15, 1995);
        Book book2 = new Book(UUID.randomUUID(), "Hello", 20, 1996);
        Book book3 = new Book(UUID.randomUUID(), "Hello", 25, 2011);
        Book book4 = new Book(UUID.randomUUID(), "Hello", 30, 2003);

        ArrayList<Book> books1 = new ArrayList<>();
        books1.add(book0);
        books1.add(book1);
        books1.add(book2);
        books1.add(book3);

        assert (model0.getTotalPrice(books1) == 70);
        assert (model1.getTotalPrice(books1) == 70);
        assert (model2.getTotalPrice(books1) == 70);

        ArrayList<Book> books2 = new ArrayList<>();
        books2.add(book0);
        books2.add(book1);
        books2.add(book2);
        books2.add(book3);
        books2.add(book4);

        assert (model0.getTotalPrice(books2) == 72);
        assert (model1.getTotalPrice(books2) == 72);
        assert (model2.getTotalPrice(books2) == 100);
    }

    private static void test() {
        Book book0 = new Book(UUID.randomUUID(), "Hello", 12345677, 1991);
        Book book1 = new Book(UUID.randomUUID(), "Hello", 12345677, 1995);
        Book book2 = new Book(UUID.randomUUID(), "Hello", 12345677, 1996);
        Book book3 = new Book(UUID.randomUUID(), "Hello", 12345677, 2011);
        Book book4 = new Book(UUID.randomUUID(), "Hello", 12345677, 2003);
        Book book5 = new Book(UUID.randomUUID(), "Hello", 12345677, 2001);

        ArrayList<Book> books = new ArrayList<>();
        books.add(book0);
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

        SkyIsTheLimit pricingModel = new SkyIsTheLimit(74074070);
        int price = pricingModel.getTotalPrice(books);
        System.out.println(price);
        assert (price == 74074062);

        pricingModel = new SkyIsTheLimit(74074062);
        price = pricingModel.getTotalPrice(books);
        System.out.println(price);
        assert (price == 61728384);

        pricingModel = new SkyIsTheLimit(74074060);
        price = pricingModel.getTotalPrice(books);
        System.out.println(price);
        assert (price == 61728384);
    }
}
