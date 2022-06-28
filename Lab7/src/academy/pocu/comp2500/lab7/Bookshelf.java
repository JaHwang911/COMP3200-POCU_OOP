package academy.pocu.comp2500.lab7;

import java.util.ArrayList;
import java.util.HashSet;

public class Bookshelf {
    private final int capacity;
    private final ArrayList<Book> books;

    public Bookshelf(final int capacity) {
        this.capacity = capacity;
        this.books = new ArrayList<>(capacity);
    }

    public boolean add(Book book) {
        if (this.books.size() >= this.capacity) {
            return false;
        }

        return this.books.add(book);
    }

    public boolean remove(Book book) {
        return this.books.remove(book);
    }
}
