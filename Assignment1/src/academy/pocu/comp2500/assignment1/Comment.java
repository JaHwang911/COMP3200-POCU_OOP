package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;
import java.util.Collections;

public class Comment {
    private String comment;
    private final String userName;
    private final ArrayList<Comment> subcomments;
    private final ArrayList<String> upvoter;
    private final ArrayList<String> downvoter;

    public Comment(String userName, String comment) {
        this.comment = comment;
        this.userName = userName;
        this.subcomments = new ArrayList<>(32);
        this.upvoter = new ArrayList<>(32);
        this.downvoter = new ArrayList<>(32);
    }

    public String getComment() {
        return this.comment;
    }

    public String getUserName() {
        return this.userName;
    }

    public void addSubcomment(Comment comment) {
        this.subcomments.add(comment);
    }

    public ArrayList<Comment> getSubcomments() {
        this.subcomments.sort((a, b) -> b.getVoteRatio() - a.getVoteRatio());

        return this.subcomments;
    }

    public boolean removeSubcomment(String name, Comment comment) {
        if (!name.equals(comment.getUserName())) {
            return false;
        }

        for (Comment sc : this.subcomments) {
            if (sc.equals(comment)) {
                this.subcomments.remove(sc);
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

    public boolean addUpvote(String userName) {
        for (String u : this.upvoter) {
            if (u.equals(userName)) {
                return false;
            }
        }

        this.upvoter.add(userName);
        return true;
    }

    public boolean cancelUpvote(String userName) {
        for (String u : this.upvoter) {
            if (u.equals(userName)) {
                this.upvoter.remove(u);
                return true;
            }
        }

        return false;
    }

    public boolean addDownvote(String userName) {
        for (String u : this.downvoter) {
            if (u.equals(userName)) {
                return false;
            }
        }

        this.downvoter.add(userName);
        return true;
    }

    public boolean cancelDownvote(String userName) {
        for (String u : this.downvoter) {
            if (u.equals(userName)) {
                this.downvoter.remove(userName);
                return true;
            }
        }

        return false;
    }

    public boolean modifyComment(String userName, String comment) {
        if (!this.userName.equals(userName)) {
            System.out.println("This Comment is not your");
            return false;
        }

        this.comment = comment;

        return true;
    }
}
