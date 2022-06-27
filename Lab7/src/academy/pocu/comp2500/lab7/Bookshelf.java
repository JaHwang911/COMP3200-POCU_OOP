package academy.pocu.comp2500.lab7;

import java.util.ArrayList;
import java.util.HashMap;

public class Bookshelf {
    private final int capacity;
    private final ArrayList<Book> books;

    public Bookshelf(final int capacity) {
        this.capacity = capacity;
        this.books = new ArrayList<>(capacity);
    }
}
