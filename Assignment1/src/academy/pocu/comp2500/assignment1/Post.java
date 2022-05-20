package academy.pocu.comp2500.assignment1;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;

public class Post {
    private final String author;
    private String title;
    private String body;
    private final ArrayList<String> tags;
    private final ArrayList<Comment> comments;
    private final ArrayList<String> reactionGreat;
    private final ArrayList<String> reactionSad;
    private final ArrayList<String> reactionAngry;
    private final ArrayList<String> reactionFun;
    private final ArrayList<String> reactionLove;
    private final OffsetDateTime createdTime;
    private OffsetDateTime modifiedTime;

    public Post(String author, String title, String body) {
        OffsetDateTime now = OffsetDateTime.now();

        this.author = author;
        this.title = title;
        this.body = body;
        this.tags = new ArrayList<>(32);
        this.comments = new ArrayList<>(32);
        reactionGreat = new ArrayList<>(32);
        reactionSad = new ArrayList<>(32);
        reactionAngry = new ArrayList<>(32);
        reactionFun = new ArrayList<>(32);
        reactionLove = new ArrayList<>(32);
        this.createdTime = now;
        this.modifiedTime = now;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public String getBody() {
        return this.body;
    }

    public OffsetDateTime getCreatedTime() {
        return this.createdTime;
    }

    public OffsetDateTime getModifiedTime() {
        return this.modifiedTime;
    }

    public boolean addTag(String author, String tag) {
        if (!this.author.equals(author) || this.tags.contains(tag)) {
            return false;
        }

        return this.tags.add(tag);
    }

    public ArrayList<String> getTag() {
        return this.tags;
    }

    public boolean removeTag(String author, String tag) {
        if (!this.author.equals(author)) {
            return false;
        }

        return this.tags.remove(tag);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public ArrayList<Comment> getComments() {
        this.comments.sort((a, b) -> b.getVoteRatio() - a.getVoteRatio());
        return this.comments;
    }

    public boolean removeComment(String name, Comment comment) {
        if (!name.equals(comment.getName())) {
            return false;
        }

        return this.comments.remove(comment);
    }

    public boolean addReaction(String name, ReactionType reactionType) {
        switch (reactionType) {
            case GREAT:
                if (this.reactionGreat.contains(name)) {
                    return false;
                }

                return this.reactionGreat.add(name);
            case SAD:
                if (this.reactionSad.contains(name)) {
                    return false;
                }

                return this.reactionSad.add(name);
            case ANGRY:
                if (this.reactionAngry.contains(name)) {
                    return false;
                }

                return this.reactionAngry.add(name);
            case FUN:
                if (this.reactionFun.contains(name)) {
                    return false;
                }

                return this.reactionFun.add(name);
            case LOVE:
                if (this.reactionLove.contains(name)) {
                    return false;
                }

                return this.reactionLove.add(name);
            default:
                assert false : "Unknown Reaction type";
                return false;
        }
    }

    public int getReactionCount(ReactionType reactionType) {
        switch (reactionType) {
            case GREAT:
                return this.reactionGreat.size();
            case SAD:
                return this.reactionSad.size();
            case FUN:
                return this.reactionFun.size();
            case ANGRY:
                return this.reactionAngry.size();
            case LOVE:
                return this.reactionLove.size();
            default:
                assert false : "Unknown Reaction type";
                return -1;
        }
    }

    public boolean removeReaction(String name, ReactionType reactionType) {
        switch (reactionType) {
            case GREAT:
                return this.reactionGreat.remove(name);
            case SAD:
                return this.reactionSad.remove(name);
            case ANGRY:
                return this.reactionAngry.remove(name);
            case FUN:
                return this.reactionFun.remove(name);
            case LOVE:
                return this.reactionLove.remove(name);
            default:
                assert false : "Unknown Reaction type";
                return false;
        }
    }

    public boolean modifyTitle(String author, String title) {
        if (!this.author.equals(author)) {
            System.out.println("This is not your post");
            return false;
        }

        this.title = title;
        this.modifiedTime = OffsetDateTime.now();

        return true;
    }

    public boolean modifyBody(String author, String body) {
        if (!this.author.equals(author)) {
            System.out.println("This is not your post");
            return false;
        }

        this.body = body;
        this.modifiedTime = OffsetDateTime.now();

        return true;
    }
}
