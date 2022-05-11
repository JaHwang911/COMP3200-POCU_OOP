package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;

public class Subcomment {
    private String comment;
    private ArrayList<Subcomment> subcomments;
    private int upvote;
    private int downvote;
    private String author;

    public Subcomment(User user, String comment) {
        this.comment = comment;
        this.subcomments = new ArrayList<>(32);
        this.upvote = 0;
        this.downvote = 0;
        this.author = user.getUserName();
    }

    public String getComment() {
        return this.comment;
    }

    public int getUpvote() {
        return this.upvote;
    }

    public int getDownvote() {
        return this.downvote;
    }

    public int getVoteRatio() {
        return this.upvote - this.downvote;
    }

    public void addUpvote() {
        ++this.upvote;
    }

    public void addDownvote() {
        ++this.downvote;
    }

    public void addSubcomment(User user, String comment) {
        this.subcomments.add(new Subcomment(user, comment));
    }

    public boolean modifySubcomment(User user, String comment) {
        if (!user.getUserName().equals(this.author)) {
            System.out.println("This comment is not your");
            return false;
        }

        this.comment = comment;

        return true;
    }
}
