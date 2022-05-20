package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;

public class Comment {
    private final String name;
    private String comment;
    private final ArrayList<Comment> subcomments;
    private final ArrayList<String> upvoter;
    private final ArrayList<String> downvoter;

    public Comment(String name, String comment) {
        this.name = name;
        this.comment = comment;
        this.subcomments = new ArrayList<>(32);
        this.upvoter = new ArrayList<>(32);
        this.downvoter = new ArrayList<>(32);
    }

    public String getComment() {
        return this.comment;
    }

    public String getName() {
        return this.name;
    }

    public void addSubcomment(Comment comment) {
        this.subcomments.add(comment);
    }

    public ArrayList<Comment> getSubcomments() {
        this.subcomments.sort((a, b) -> b.getVoteRatio() - a.getVoteRatio());
        return this.subcomments;
    }

    public boolean removeSubcomment(String name, Comment comment) {
        if (!name.equals(comment.getName())) {
            return false;
        }

        return this.subcomments.remove(comment);
    }

    public int getUpvote() {
        return this.upvoter.size();
    }

    public int getDownvote() {
        return this.downvoter.size();
    }

    public int getVoteRatio() {
        return this.upvoter.size() - this.downvoter.size();
    }

    public boolean addUpvote(String name) {
        if (this.upvoter.contains(name)) {
            return false;
        }

        this.upvoter.add(name);
        return true;
    }

    public boolean cancelUpvote(String name) {
        return this.upvoter.remove(name);
    }

    public boolean addDownvote(String name) {
        if (this.downvoter.contains(name)) {
            return false;
        }

        this.downvoter.add(name);
        return true;
    }

    public boolean cancelDownvote(String name) {
        return this.downvoter.remove(name);
    }

    public boolean modifyComment(String name, String comment) {
        if (!this.name.equals(name)) {
            System.out.println("This is not your comment");
            return false;
        }

        this.comment = comment;

        return true;
    }
}
