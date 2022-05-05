package academy.pocu.comp2500.assignment1;

import java.time.OffsetDateTime;

public class Article {
    private String title;
    private String text;
    private String author;
    private OffsetDateTime time;

    public Article(String fullName, String title, String text) {
        this.title = title;
        this.text = text;
        this.author = fullName;
        this.time.now();
    }

    public String getTitle() {
        return this.title;
    }
    public String getContent() {
        return this.text;
    }

    public String getAuthor() {
        return this.author;
    }

    public OffsetDateTime getTime() {
        return this.time;
    }

    public void modifiedArticle(String text) {
        this.text = text;
    }
}
