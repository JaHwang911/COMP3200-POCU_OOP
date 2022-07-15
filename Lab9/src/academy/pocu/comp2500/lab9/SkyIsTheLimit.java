package academy.pocu.comp2500.lab9;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SkyIsTheLimit implements IPricingModel {
    private final static int MIN_BOOK_COUNT = 5;
    private final int maxPrice;

    public SkyIsTheLimit(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getTotalPrice(Collection<Book> books) {
        int totalPrice = 0;
        ArrayList<Book> arrBooks = new ArrayList<>(books.size());

        for (Book book : books) {
            totalPrice += book.getPrice();
            arrBooks.add(book);
        }

        if (books.size() < MIN_BOOK_COUNT || totalPrice < maxPrice) {
            return totalPrice;
        }

        Collections.sort(arrBooks, new BookPriceComparator());

        double discountPrice = arrBooks.get(0).getPrice() * 0.5;
        totalPrice -= arrBooks.get(0).getPrice();
        totalPrice += discountPrice;

        discountPrice = arrBooks.get(1).getPrice() * 0.5;
        totalPrice -= arrBooks.get(1).getPrice();
        totalPrice += discountPrice;

        return totalPrice;
    }
}
