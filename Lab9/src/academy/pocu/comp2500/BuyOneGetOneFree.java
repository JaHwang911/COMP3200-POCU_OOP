package academy.pocu.comp2500;

import java.util.*;

public final class BuyOneGetOneFree {
    private final HashSet<UUID> skus;

    public BuyOneGetOneFree(HashSet<UUID> skus) {
        this.skus = skus;
    }

    public int getPrice(Collection<Book> books) {
        int sum = 0;
        HashMap<UUID, Integer> countByBookId = new HashMap<>();
        HashSet<Book> noDuplicatesBooks = new HashSet<>();

        for (Book book : books) {
            noDuplicatesBooks.add(book);
            if (!countByBookId.containsKey(book.getSku())) {
                countByBookId.put(book.getSku(), 1);

                continue;
            }

            int value = countByBookId.get(book.getSku());
            countByBookId.replace(book.getSku(), value + 1);
        }

        for (Book book : noDuplicatesBooks) {
            int bookCount = countByBookId.get(book.getSku());
            int weight = bookCount / 2 + bookCount % 2;

            sum += book.getPrice() * weight;
        }

        return sum;
    }
}
