package test.app;

import test.*;

import java.util.ArrayList;
import java.util.HashSet;

public class Program {
    public static void main(String[] args) {
        HashSet<Book> books = new HashSet<>();

        Author author0 = new Author("Jane", "Doe");
        Author author1 = new Author("Jane", "Doe");
        Author author2 = new Author("John", "Mayor");
        Author author3 = new Author("John", "Mayor");

        Book book0 = new Book("A New Hope", 100, author0);
        Book book1 = new Book("A New Hope", 100, author1);
        Book book2 = new Book("The Empire Strikes Back", 50, author2);
        Book book3 = new Book("The Empire Strikes Back", 50, author3);

        books.add(book0);
        books.add(book1);
        books.add(book2);
        books.add(book3);

        for (Book book : books) {
            System.out.println(book.toString());
        }
    }
}
