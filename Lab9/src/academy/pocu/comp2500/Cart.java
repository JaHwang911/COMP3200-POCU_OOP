package academy.pocu.comp2500;

import java.util.ArrayList;
import java.util.UUID;

public final class Cart {
    private ArrayList<Book> books = new ArrayList<>();

    public Book getBookOrNull(Book book) {
        int index = this.books.indexOf(book);

        if (index < 0) {
            return null;
        }

        return this.books.get(index);
    }

    public int getBookCount() {
        return this.books.size();
    }

    public void addBooks(ArrayList<Book> books) {
        this.books.addAll(books);
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public boolean remove(Book book) {
        return this.books.remove(book);
    }

    public int getTotalPrice() {
        int sum = 0;

        for (Book book : this.books) {
            sum += book.getPrice();
        }

        return sum;
    }

    public int getTotalPrice(BuyOneGetOneFree pricingModel) {
        return 0;
    }
}