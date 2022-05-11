package academy.pocu.comp2500.assignment1;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class Post {
    private String title;
    private String body;
    private String author;
    private String tag;
    private ArrayList<Comment> comments;
    private ArrayList<Reaction> reactions;
    private OffsetDateTime createdTime;
    private OffsetDateTime modifiedTime;

    public Post(User user, String title, String body) {
        if (user.getUserType() != UserType.WRITER) {
            System.out.println("Invalid user type");
            return;
        }

        this.title = title;
        this.body = body;
        this.author = user.getUserName();
        this.tag = "";
        this.comments = new ArrayList<>(128);
        this.reactions = new ArrayList<>(128);
        this.createdTime = OffsetDateTime.now();
        this.modifiedTime = OffsetDateTime.now();
    }

    // Getter
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

    public ArrayList<Comment> getAllCommentsOrNULL() {
        Collections.sort(this.comments, (a, b) -> b.getUpvote() - a.getUpvote());

        return this.comments;
    }

    public ArrayList<Reaction> getAllReactions() {
        return this.reactions;
    }

    public OffsetDateTime getTime() {
        return this.createdTime;
    }

    public OffsetDateTime getModifiedTime() {
        return this.modifiedTime;
    }

    // Add
    public boolean addTag(User user, String tag) {
        if (!user.getUserName().equals(this.author)) {
            return false;
        }

        this.tag = tag;

        return true;
    }

    public void addComment(User user, String comment) {
        Comment newComment = new Comment(user.getUserName(), comment);
        this.comments.add(newComment);
    }

    public void addReaction(User user, ReactionType type) {
        Reaction newReaction = new Reaction(user, type);
        reactions.add(newReaction);
    }

    // Modify
    public boolean modifyTitle(User user, String title) {
        if (!user.getUserName().equals(this.author)) {
            System.out.println("This post is not yours");
            return false;
        }

        this.title = title;
        this.modifiedTime = OffsetDateTime.now();

        return true;
    }

    public boolean modifyBody(User user, String body) {
        if (!user.getUserName().equals(this.author)) {
            System.out.println("This post is not yours");
            return false;
        }

        this.body = body;
        this.modifiedTime = OffsetDateTime.now();

        return true;
    }

    // Remove
    public boolean removeReaction(User user, Reaction reaction) {
        if (!user.getUserName().equals(reaction.getUserName())) {
            return false;
        }

        for (Reaction r : this.reactions) {
            if (r == reaction) {
                reactions.remove(r);
                return true;
            }
        }

        return false;
    }
}
