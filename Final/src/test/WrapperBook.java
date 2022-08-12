package test;

public class WrapperBook {
    private Book book;

    public WrapperBook() {
        book = new Book("Harry Poter", 256, new Author("rolling", "james"));

    }

    public Author getAuthor() {
        return this.book.getAuthor();
    }

    public int getPageCount() {
        return this.book.getPageCount();
    }

    public String getTitle() {
        return this.book.getTitle();
    }
}
