package academy.pocu.comp2500.lab7;

import java.util.HashSet;

public class Bookshelf {
    private final int capacity;
    private final HashSet<Book> books;

    public Bookshelf(final int capacity) {
        this.capacity = capacity;
        this.books = new HashSet<>(capacity);
    }

    public boolean add(Book book) {
        if (this.books.contains(book)) {
            return false;
        }

        return this.books.add(book);
    }

    public boolean remove(Book book) {
        return this.books.remove(book);
    }
}
