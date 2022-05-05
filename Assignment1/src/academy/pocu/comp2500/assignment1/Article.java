package academy.pocu.comp2500.assignment1;

import java.time.OffsetDateTime;

public class Article {
    private String content;
    private String author;
    private OffsetDateTime time;

    public String getContent() {
        return this.content;
    }

    public String getAuthor() {
        return this.author;
    }
}
