package academy.pocu.comp2500.lab7;

import java.util.HashSet;

public class Bundle {
    private final String name;
    private final HashSet<Book> books = new HashSet<>();

    public Bundle(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean add(Book book) {
        return this.books.add(book);
    }

    public boolean remove(Book book) {
        return this.books.remove(book);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Bundle) ||
                ((Bundle) obj).books.size() != this.books.size() ||
                this.hashCode() != obj.hashCode()) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 17;

        hash = hash * 31 + this.name.hashCode();

        for (Book book : books) {
            hash = hash * 31 + book.hashCode();
        }

        return hash;
    }
}
