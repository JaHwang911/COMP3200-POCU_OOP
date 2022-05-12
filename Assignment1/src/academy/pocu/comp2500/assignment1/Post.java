package academy.pocu.comp2500.assignment1;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class Post {
    private String title;
    private String body;
    private final User owner;
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

    public Post(User user, String title, String body) {
        OffsetDateTime now = OffsetDateTime.now();
        this.title = title;
        this.body = body;
        this.owner = user;
        this.author = user.getUserName();
        this.tags = new ArrayList<>(128);
        this.comments = new ArrayList<>(128);
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

    public UserType getAuthorType() {
        return this.owner.getUserType();
    }

    public User getOwner() {
        return this.owner;
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

    public OffsetDateTime getCreatedTime() {
        return this.createdTime;
    }

    public OffsetDateTime getModifiedTime() {
        return this.modifiedTime;
    }

    public boolean addTag(String name, String tag) {
        if (!name.equals(this.author)) {
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

    public void addComment(String name, String comment) {
        Comment newComment = new Comment(name, comment);
        this.comments.add(newComment);
    }

    // Reaction
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

    public boolean addReaction(String name, ReactionType type) {
        switch (type) {
            case GREAT:
                return reactionGreat.addUser(name);
            case SAD:
                return reactionSad.addUser(name);
            case ANGRY:
                return reactionAngry.addUser(name);
            case FUN:
                return reactionFun.addUser(name);
            case LOVE:
                return reactionLove.addUser(name);
            default:
                assert false : "Unknown Reaction type";
                return false;
        }
    }

    public boolean removeReaction(String name, Reaction reaction) {
        return reaction.subUser(name);
    }

    public boolean modifyTitle(User user, String title) {
        if (this.owner != user) {
            System.out.println("This post is not your");
            return false;
        }

        this.title = title;
        this.modifiedTime = OffsetDateTime.now();
        return true;
    }

    public boolean modifyBody(User user, String body) {
        if (this.owner != user) {
            System.out.println("This post is not your");
            return false;
        }

        this.body = body;
        this.modifiedTime = OffsetDateTime.now();
        return true;
    }
}
