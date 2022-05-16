package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;
import java.util.Collections;

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

        for (Comment c : this.subcomments) {
            if (c.equals(comment)) {
                this.subcomments.remove(c);
                return true;
            }
        }

        return false;
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
        for (String u : this.upvoter) {
            if (u.equals(name)) {
                return false;
            }
        }

        this.upvoter.add(name);
        return true;
    }

    public boolean cancelUpvote(String name) {
        for (String u : this.upvoter) {
            if (u.equals(name)) {
                this.upvoter.remove(u);
                return true;
            }
        }

        return false;
    }

    public boolean addDownvote(String name) {
        for (String u : this.downvoter) {
            if (u.equals(name)) {
                return false;
            }
        }

        this.downvoter.add(name);
        return true;
    }

    public boolean cancelDownvote(String name) {
        for (String u : this.downvoter) {
            if (u.equals(name)) {
                this.downvoter.remove(name);
                return true;
            }
        }

        return false;
    }

    public boolean modifyComment(String name, String comment) {
        if (!this.name.equals(name)) {
            System.out.println("This Comment is not your");
            return false;
        }

        this.comment = comment;

        return true;
    }
}
