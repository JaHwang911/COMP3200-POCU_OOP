package academy.pocu.comp2500.assignment1;

import java.time.OffsetDateTime;
import java.util.ArrayList;

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
        this.tags = new ArrayList<>(128);
        this.comments = new ArrayList<>(128);
        reactionGreat = new ArrayList<>(32);
        reactionSad = new ArrayList<>(32);
        reactionAngry = new ArrayList<>(32);
        reactionFun = new ArrayList<>(32);
        reactionLove = new ArrayList<>(32);
        this.createdTime = now;
        this.modifiedTime = now;
    }

    public String getName() {
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
        if (!name.equals(comment.getName())) {
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

    public boolean addReaction(String name, ReactionType reactionType) {
        switch (reactionType) {
            case GREAT:
                for (String userName : this.reactionGreat) {
                    if (userName.equals(name)) {
                        return false;
                    }
                }

                reactionGreat.add(name);
                return true;
            case SAD:
                for (String userName : this.reactionSad) {
                    if (userName.equals(name)) {
                        return false;
                    }
                }

                reactionSad.add(name);
                return true;
            case ANGRY:
                for (String userName : this.reactionAngry) {
                    if (userName.equals(name)) {
                        return false;
                    }
                }

                reactionAngry.add(name);
                return true;
            case FUN:
                for (String userName : this.reactionFun) {
                    if (userName.equals(name)) {
                        return false;
                    }
                }

                reactionFun.add(name);
                return true;
            case LOVE:
                for (String userName : this.reactionLove) {
                    if (userName.equals(name)) {
                        return false;
                    }
                }

                reactionLove.add(name);
                return true;
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
                for (String userName : this.reactionGreat) {
                    if (userName.equals(name)) {
                        this.reactionGreat.remove(userName);
                        return true;
                    }
                }

                return false;
            case SAD:
                for (String userName : this.reactionSad) {
                    if (userName.equals(name)) {
                        this.reactionSad.remove(userName);
                        return true;
                    }
                }

                return false;
            case ANGRY:
                for (String userName : this.reactionAngry) {
                    if (userName.equals(name)) {
                        this.reactionAngry.remove(userName);
                        return true;
                    }
                }

                return false;
            case FUN:
                for (String userName : this.reactionFun) {
                    if (userName.equals(name)) {
                        this.reactionFun.remove(userName);
                        return true;
                    }
                }

                return false;
            case LOVE:
                for (String userName : this.reactionLove) {
                    if (userName.equals(name)) {
                        this.reactionLove.remove(userName);
                        return true;
                    }
                }

                return false;
            default:
                assert false : "Unknown Reaction type";
                return false;
        }
    }

    public boolean modifyTitle(String author, String title) {
        if (!this.author.equals(author)) {
            return false;
        }

        this.title = title;
        this.modifiedTime = OffsetDateTime.now();

        return true;
    }

    public boolean modifyBody(String author, String body) {
        if (!this.author.equals(author)) {
            return false;
        }

        this.body = body;
        this.modifiedTime = OffsetDateTime.now();

        return true;
    }
}
