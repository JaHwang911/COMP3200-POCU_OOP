package academy.pocu.comp2500.lab7;

public class Book {
    private String title;
    private Author author;
    private int publishedYears;
    private Genre genre;

    public Book(final String title, final Author author, final int publishedYears, final Genre genre) {
        this.title = title;
        this.author = author;
        this.publishedYears = publishedYears;
        this.genre = genre;
    }

    public String getTitle() {
        return this.title;
    }

    public Author getAuthor() {
        return this.author;
    }

    public int getPublishedYears() {
        return this.publishedYears;
    }

    public Genre getGenre() {
        return this.genre;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Book) || this.hashCode() != obj.hashCode()) {
            return false;
        }

        Book book = (Book) obj;

        return this.title.equals(book.title)
                && this.author.equals((Object) book.author)
                && this.publishedYears == book.publishedYears
                && this.genre == book.genre;
    }

    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + this.title.hashCode();
        hash = hash * 31 + this.author.hashCode();
        hash = hash * 31 + this.publishedYears;
        hash = hash * 31 + this.genre.hashCode();

        return hash;
    }
}
