package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;
import java.util.Collections;

public class Comment {
    private String comment;
    private final ArrayList<Comment> subComments;
    private final ArrayList<User> upvoter;
    private final ArrayList<User> downvoter;
    private final User owner;

    public Comment(User user, String text) {
        this.comment = text;
        this.subComments = new ArrayList<>(32);
        this.upvoter = new ArrayList<>(32);
        this.downvoter = new ArrayList<>(32);
        this.owner = user;
    }

    public String getComment() {
        return this.comment;
    }

    public User getOwner() {
        return this.owner;
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

    public ArrayList<Comment> getAllSubcomments() {
        Collections.sort(this.subComments, (a, b) -> b.getVoteRatio() - a.getVoteRatio());

        return this.subComments;
    }

    public void addSubcomment(User user, String text) {
        this.subComments.add(new Comment(user, text));
    }

    public boolean addUpvote(User user) {
        for (User u : this.upvoter) {
            if (u.equals(user)) {
                return false;
            }
        }

        this.upvoter.add(user);
        return true;
    }

    public boolean cancelUpvote(User user) {
        for (User u : this.upvoter) {
            if (u.equals(user)) {
                this.upvoter.remove(u);
                return true;
            }
        }

        return false;
    }

    public boolean addDownvote(User user) {
        for (User u : this.downvoter) {
            if (u.equals(user)) {
                return false;
            }
        }

        this.downvoter.add(user);
        return true;
    }

    public boolean cancelDownvote(User user) {
        for (User u : this.downvoter) {
            if (u.equals(user)) {
                this.downvoter.remove(user);
                return true;
            }
        }

        return false;
    }

    //Modify
    public boolean modifyComment(User user, String text) {
        if (!this.owner.equals(user)) {
            System.out.println("This Comment is not your");
            return false;
        }

        this.comment = text;

        return true;
    }
}
