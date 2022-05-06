package academy.pocu.comp2500.assignment1;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import academy.pocu.comp2500.assignment1.user.User;

public class Post {
    private String title;
    private String body;
    private final String author;
    private String tag;
    private ArrayList<Comment> comments = new ArrayList<Comment>(128);
    private ArrayList<Reaction> reactions = new ArrayList<Reaction>(128);
    private OffsetDateTime writeTime;
    private OffsetDateTime modifiedTime;

    public Post(String fullName, String title, String body) {
        this(fullName, title, body, null);
    }

    public Post(String fullName, String title, String body, String tag) {
        this.title = title;
        this.body = body;
        this.author = fullName;
        this.tag = tag;
        this.writeTime.now();
    }

    public String getTitle() {
        return this.title;
    }
    public String getBody() {
        return this.body;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTag() {
        return this.tag;
    }

    public ArrayList<Comment> getCommentsOrNULL() {
        return comments;
    }

    public OffsetDateTime getTime() {
        return this.writeTime;
    }

    // Test 지워야함
    public void setTitle(String title) {
        this.title = title;
    }

    public void addComment(User user, String comment) {
        Comment newComment = new Comment(user.getUserName(), comment);
        this.comments.add(newComment);
    }

    public void addReaction(Reaction reaction) {
        this.reactions.add(reaction);
    }

    void modified(String title, String body) {
        this.title = title;
        this.body = body;
        this.modifiedTime.now();
    }

    void modified(String title, String body, String tag) {
        this.title = title;
        this.body = body;
        this.tag = tag;
        this.modifiedTime.now();
    }

    public boolean modifiedComment(User user, Comment comment, String text) {
        if (!user.getUserName().equals(comment.getUserName())) {
            return false;
        }

        return false;
    }
}
