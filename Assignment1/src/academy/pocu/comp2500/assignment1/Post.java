package academy.pocu.comp2500.assignment1;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class Post {
    private final Blog blog;
    private String title;
    private String body;
    private final User author;
    private final ArrayList<String> tags;
    private final ArrayList<Comment> comments;
    private final Reaction reactionGreat = new Reaction(ReactionType.GREAT);
    private final Reaction reactionSad = new Reaction(ReactionType.SAD);
    private final Reaction reactionAngry = new Reaction(ReactionType.ANGRY);
    private final Reaction reactionFun = new Reaction(ReactionType.FUN);
    private final Reaction reactionLove = new Reaction(ReactionType.LOVE);
    private final OffsetDateTime createdTime;
    private OffsetDateTime modifiedTime;

    public Post(Blog blog, User user, String title, String body) {
        this(blog, user, title, body, new ArrayList<>(8));
    }

    public Post(Blog blog, User user, String title, String body, ArrayList<String> tags) {
        OffsetDateTime now = OffsetDateTime.now();
        this.blog = blog;
        this.title = title;
        this.body = body;
        this.author = user;
        this.tags = tags;
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

    public User getAuthor() {
        return this.author;
    }

    public Blog getBlog() {
        return this.blog;
    }

    public OffsetDateTime getCreatedTime() {
        return this.createdTime;
    }

    public OffsetDateTime getModifiedTime() {
        return this.modifiedTime;
    }

    public boolean addTag(User user, String tag) {
        if (!this.author.equals(user)) {
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

    public boolean removeTag(User user, String tag) {
        if (!this.author.equals(user)) {
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

    public void addComment(User user, String text) {
        Comment comment = new Comment(this, user, text);

        this.comments.add(comment);
    }

    public ArrayList<Comment> getComments() {
        Collections.sort(this.comments, (a, b) -> b.getVoteRatio() - a.getVoteRatio());

        return this.comments;
    }

    public boolean removeComment(User user, Comment comment) {
        if (!user.equals(comment.getAuthor())) {
            return false;
        } else if (!this.equals(comment.getPost())) {
            return false;
        }

        this.comments.remove(comment);

        return false;
    }

    public ArrayList<Comment> getSubcomments(Comment comment) {
        ArrayList<Comment> result = new ArrayList<>(32);

        for (Comment c : this.comments) {
            if (c.equals(comment)) {
                result.addAll(c.getSubcomments());
            }
        }

        return result;
    }

    public boolean addReaction(User user, ReactionType type) {
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

    public boolean removeReaction(User user, Reaction reaction) {
        return reaction.subUser(user);
    }

    public boolean modifyTitle(User user, String title) {
        if (!this.author.equals(user)) {
            return false;
        }

        this.title = title;
        this.modifiedTime = OffsetDateTime.now();

        return true;
    }

    public boolean modifyBody(User user, String body) {
        if (!this.author.equals(user)) {
            return false;
        }

        this.body = body;
        this.modifiedTime = OffsetDateTime.now();

        return true;
    }
}
