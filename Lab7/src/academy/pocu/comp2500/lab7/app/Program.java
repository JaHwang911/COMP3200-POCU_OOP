package academy.pocu.comp2500.lab7.app;

import academy.pocu.comp2500.lab7.Author;
import academy.pocu.comp2500.lab7.Book;
import academy.pocu.comp2500.lab7.Genre;

import java.util.HashSet;

public class Program {

    public static void main(String[] args) {
        Author author0 = new Author("Ja", "Hwang");
        Author author1 = author0;
        Author author2 = new Author("Ja", "Hwang");
        Author author3 = new Author("Pope", "Kim");

        assert author0 == author1;
        assert author0.equals(author1);
        assert author0.equals(author2);
        assert !author0.equals(author3);

        Book book0 = new Book("Event horizon", author0, 2022, Genre.SUSPENSE);
        Book book1 = new Book("Event horizon", author0, 2022, Genre.SUSPENSE);
        Book book2 = new Book("Hello Coding", author3, 2022, Genre.SUSPENSE);

        assert book0 != book1;
        assert book0.equals(book1);
        assert !book0.equals(book2);

        HashSet<Book> books = new HashSet<>();
        books.add(book0);
        books.add(book2);

        assert books.contains(book1);

        System.out.println("No prob: lab7");
    }
}
