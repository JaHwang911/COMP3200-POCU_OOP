package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;
import java.util.Collections;

public class Comment {
    private String comment;
    private ArrayList<Comment> subComments;
    private ArrayList<String> upvoter;
    private ArrayList<String> downvoter;
    private String author;

    public Comment(User user, String text) {
        this.comment = text;
        this.subComments = new ArrayList<>(32);
        this.upvoter = new ArrayList<>(32);
        this.downvoter = new ArrayList<>(32);
        this.author = user.getUserName();
    }

    public String getComment() {
        return this.comment;
    }

    public String getAuthor() {
        return this.author;
    }

    public void addSubcomment(User user, String text) {
        this.subComments.add(new Comment(user, text));
    }

    public ArrayList<Comment> getAllSubcomments() {
        Collections.sort(this.subComments, (a, b) -> b.getVoteRatio() - a.getVoteRatio());

        return this.subComments;
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

    public boolean addUpvote(User user) {
        String userName = user.getUserName();

        for (String u : this.upvoter) {
            if (u.equals(userName)) {
                return false;
            }
        }

        this.upvoter.add(userName);
        return true;
    }

    public boolean cancelUpvote(User user) {
        String userName = user.getUserName();

        for (String u : this.upvoter) {
            if (u.equals(userName)) {
                this.upvoter.remove(u);
                return true;
            }
        }

        return false;
    }

    public boolean addDownvote(User user) {
        String userName = user.getUserName();

        for (String u : this.downvoter) {
            if (u.equals(userName)) {
                return false;
            }
        }

        this.downvoter.add(userName);
        return true;
    }

    public boolean cancelDownvote(User user) {
        String userName = user.getUserName();

        for (String u : this.downvoter) {
            if (u.equals(userName)) {
                this.downvoter.remove(u);
                return true;
            }
        }

        return false;
    }

    //Modify
    public boolean modifyComment(User user, String text) {
        if (!user.getUserName().equals(this.author)) {
            System.out.println("This Comment is not your");
            return false;
        }

        this.comment = text;

        return true;
    }
}
