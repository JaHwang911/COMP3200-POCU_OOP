package test;

public final class Book {
    private final String title;
    private final int pageCount;
    private final Author author;

    public Book(final String title, final int pageCount, final Author author) {
        this.title = title;
        this.pageCount = pageCount;
        this.author = author;
    }

    public int hashCode() {
        int hash = this.author.hashCode();
        hash += pageCount;
        hash += title.hashCode();

        return hash;
    }

    public String toString() {
        return this.title + String.format("(%d)", this.pageCount) + " by " + this.author.toString();
    }
}
