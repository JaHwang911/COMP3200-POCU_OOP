package academy.pocu.comp2500.lab7;

import java.util.ArrayList;

public class ReadingList {
    private final String name;
    private final ArrayList<Book> readingList = new ArrayList<>();

    public ReadingList(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void add(Book book) {
        this.readingList.add(book);
    }

    public boolean remove(Book book) {
        return this.readingList.remove(book);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int count = 1;

        for (Book book : this.readingList) {
            sb.append(String.format("%d. %s [%s]%s", count++, book.getTitle(), book.getAuthor().getFullName(), System.lineSeparator()));
        }

        return sb.toString();
    }
}
