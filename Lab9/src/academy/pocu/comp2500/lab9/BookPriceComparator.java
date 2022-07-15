package academy.pocu.comp2500.lab9;

import java.util.Comparator;

public class BookPriceComparator implements Comparator<Book> {
    public int compare(Book book0, Book book1) {
        return book1.getPrice() - book0.getPrice();
    }
}
