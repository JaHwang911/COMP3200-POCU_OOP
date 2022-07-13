package academy.pocu.comp2500.lab9;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class DecadeMadness implements IPricingModel {
    public int getTotalPrice(Collection<Book> books) {
        int totalPrice = 0;
        HashMap<Integer, ArrayList<Book>> countByPublishedYear = new HashMap<>();

        for (Book book : books) {
            int year = book.getPublishedYear() / 10;

            if (!countByPublishedYear.containsKey(year)) {
                ArrayList<Book> tmp = new ArrayList<>();
                tmp.add(book);
                countByPublishedYear.put(year, tmp);
                continue;
            }

            ArrayList<Book> tmpBooks = countByPublishedYear.get(year);
            tmpBooks.add(book);
        }

        for (ArrayList<Book> tmpBooks : countByPublishedYear.values()) {
            if (tmpBooks.size() == 1) {
                totalPrice += tmpBooks.get(0).getPrice();
                continue;
            }

            double tmpPrice = 0;
            for (Book book : tmpBooks) {
                tmpPrice += book.getPrice();
            }

            tmpPrice *= 0.8;
            totalPrice += tmpPrice;
        }

        return totalPrice;
    }
}
