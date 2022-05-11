package academy.pocu.comp2500.assignment1;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class Post {
    private String title;
    private String body;
    private String author;
    private ArrayList<String> tags;
    private ArrayList<Comment> comments;
    private ArrayList<Reaction> reactions;
    private OffsetDateTime createdTime;
    private OffsetDateTime modifiedTime;

    public Post(User user, String title, String body) {
        OffsetDateTime now = OffsetDateTime.now();
        this.title = title;
        this.body = body;
        this.author = user.getUserName();
        this.tags = new ArrayList<>(128);
        this.comments = new ArrayList<>(128);
        this.reactions = new ArrayList<>(128);
        this.createdTime = now;
        this.modifiedTime = now;
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

    public ArrayList<String> getTags() {
        return this.tags;
    }

    public ArrayList<Comment> getAllComments() {
        Collections.sort(this.comments, (a, b) -> b.getVoteRatio() - a.getVoteRatio());

        return this.comments;
    }

    public ArrayList<Reaction> getAllReactions() {
        return this.reactions;
    }

    public OffsetDateTime getCreatedTime() {
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

        this.tags.add(tag);

        return true;
    }

    public void addComment(User user, String comment) {
        Comment newComment = new Comment(user, comment);
        this.comments.add(newComment);
    }

    public void addReaction(User user, ReactionType type) {
        Reaction newReaction = new Reaction(user, type);
        this.reactions.add(newReaction);
    }

    // Modify
    public boolean modifyTitle(User user, String title) {
        if (!user.getUserName().equals(this.author)) {
            System.out.println("This post is not your");
            return false;
        }

        this.title = title;
        this.modifiedTime = OffsetDateTime.now();
        return true;
    }

    public boolean modifyBody(User user, String body) {
        if (!user.getUserName().equals(this.author)) {
            System.out.println("This post is not your");
            return false;
        }

        this.body = body;
        this.modifiedTime = OffsetDateTime.now();
        return true;
    }

    // Remove
    public boolean removeReaction(User user, Reaction reaction) {
        if (!user.getUserName().equals(reaction.getUserName())) {
            System.out.println("This reaction is not your");
            return false;
        }

        for (Reaction r : this.reactions) {
            if (r == reaction) {
                this.reactions.remove(r);
                return true;
            }
        }

        return false;
    }
}
