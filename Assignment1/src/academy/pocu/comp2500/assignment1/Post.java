package academy.pocu.comp2500.assignment1;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class Post {
    private String title;
    private String body;
    private final String author;
    private final ArrayList<String> tags;
    private final ArrayList<Comment> comments;
    private final Reaction reactionGreat = new Reaction(ReactionType.GREAT);
    private final Reaction reactionSad = new Reaction(ReactionType.SAD);
    private final Reaction reactionAngry = new Reaction(ReactionType.ANGRY);
    private final Reaction reactionFun = new Reaction(ReactionType.FUN);
    private final Reaction reactionLove = new Reaction(ReactionType.LOVE);
    private final OffsetDateTime createdTime;
    private OffsetDateTime modifiedTime;

    public Post(String name, String title, String body) {
        OffsetDateTime now = OffsetDateTime.now();
        this.title = title;
        this.body = body;
        this.author = name;
        this.tags = new ArrayList<>(128);
        this.comments = new ArrayList<>(128);
        this.createdTime = now;
        this.modifiedTime = now;
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

    public OffsetDateTime getCreatedTime() {
        return this.createdTime;
    }

    public OffsetDateTime getModifiedTime() {
        return this.modifiedTime;
    }

    public boolean addTag(String name, String tag) {
        if (!this.author.equals(name)) {
            return false;
        }

        for (String t : this.tags) {
            if (t.equals(tag)) {
                return false;
            }
        }

        this.tags.add(tag);
        return true;
    }

    public ArrayList<String> getTags() {
        return this.tags;
    }

    public boolean hasTag(String tag) {
        for (String t : this.tags) {
            if (t.equals(tag)) {
                return true;
            }
        }

        return false;
    }

    public boolean removeTag(String name, String tag) {
        if (!this.author.equals(name)) {
            return false;
        }

        for (String t : this.tags) {
            if (t.equals(tag)) {
                this.tags.remove(t);
                return true;
            }
        }

        return false;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public ArrayList<Comment> getComments() {
        this.comments.sort((a, b) -> b.getVoteRatio() - a.getVoteRatio());
        return this.comments;
    }

    public boolean removeComment(String name, Comment comment) {
        if (!name.equals(comment.getAuthor())) {
            return false;
        }

        for (Comment c : this.comments) {
            if (c.equals(comment)) {
                this.comments.remove(comment);
                return true;
            }
        }

        return false;
    }

    public boolean addReaction(String user, ReactionType type) {
        switch (type) {
            case GREAT:
                return reactionGreat.addUser(user);
            case SAD:
                return reactionSad.addUser(user);
            case ANGRY:
                return reactionAngry.addUser(user);
            case FUN:
                return reactionFun.addUser(user);
            case LOVE:
                return reactionLove.addUser(user);
            default:
                assert false : "Unknown Reaction type";
                return false;
        }
    }

    public int getReactionCount(ReactionType type) {
        switch (type) {
            case GREAT:
                return this.reactionGreat.getCount();
            case SAD:
                return this.reactionSad.getCount();
            case FUN:
                return this.reactionFun.getCount();
            case ANGRY:
                return this.reactionAngry.getCount();
            case LOVE:
                return this.reactionLove.getCount();
            default:
                assert false : "Unknown Reaction type";
                return -1;
        }
    }

    public boolean removeReaction(String user, Reaction reaction) {
        return reaction.subUser(user);
    }

    public boolean modifyTitle(String name, String title) {
        if (!this.author.equals(name)) {
            return false;
        }

        this.title = title;
        this.modifiedTime = OffsetDateTime.now();

        return true;
    }

    public boolean modifyBody(String name, String body) {
        if (!this.author.equals(name)) {
            return false;
        }

        this.body = body;
        this.modifiedTime = OffsetDateTime.now();

        return true;
    }
}
