package academy.pocu.comp2500.lab9;

import java.util.*;

public final class BuyOneGetOneFree implements IPricingModel {
    private final HashSet<UUID> skus;

    public BuyOneGetOneFree(HashSet<UUID> skus) {
        this.skus = skus;
    }

    public int getTotalPrice(Collection<Book> books) {
        int totalPrice = 0;
        HashMap<UUID, Integer> countByBookId = new HashMap<>();

        for (Book book : books) {
            if (this.skus.contains(book.getSku())) {
                if (!countByBookId.containsKey(book.getSku())) {
                    countByBookId.put(book.getSku(), 1);
                    totalPrice += book.getPrice();
                    continue;
                }

                int value = countByBookId.get(book.getSku());

                if (value % 2 == 0) {
                    totalPrice += book.getPrice();
                }

                countByBookId.replace(book.getSku(), value + 1);
            } else {
                totalPrice += book.getPrice();
            }
        }

        return totalPrice;
    }
}
