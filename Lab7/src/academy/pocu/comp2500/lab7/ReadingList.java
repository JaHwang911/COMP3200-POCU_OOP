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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ReadingList) ||
                this.readingList.size() != ((ReadingList) obj).readingList.size() ||
                this.hashCode() != obj.hashCode()) {
            return false;
        }

//        ReadingList readingList = (ReadingList) obj;
//
//        for (int i = 0; i < this.readingList.size(); ++i) {
//            if (!this.readingList.get(i).equals(readingList.readingList.get(i))) {
//                return false;
//            }
//        }

        return true;
    }

    public int hashCode() {
        int hash = 17;

        for (Book book : this.readingList) {
            hash = hash * 31 + book.hashCode();
        }

        hash = hash * 31 + this.name.hashCode();

        return hash;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int count = 1;

        for (Book book : this.readingList) {
            sb.append(String.format("%d. %s%s", count++, book.toString(), System.lineSeparator()));
        }

        return sb.toString();
    }
}
