package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;
import java.util.Collections;

public class Comment {
    private String comment;
    private ArrayList<Subcomment> subComments;
    private int upvote;
    private int downvote;
    private String author;

    public Comment(User user, String comment) {
        this.comment = comment;
        this.subComments = new ArrayList<>(32);
        this.upvote = 0;
        this.downvote = 0;
        this.author = user.getUserName();
    }

    // Get
    public String getComment() {
        return this.comment;
    }

    public int getUpvote() {
        return this.upvote;
    }

    public int getDownvote() {
        return this.downvote;
    }

    public String getAuthor() {
        return this.author;
    }

    public ArrayList<Subcomment> getAllSubcomments() {
        Collections.sort(this.subComments, (a, b) -> b.getVoteRatio() - a.getVoteRatio());

        return this.subComments;
    }

    public int getVoteRatio() {
        return this.upvote - this.downvote;
    }

    //Add
    public void addUpvote() {
        ++this.upvote;
    }

    public void addDownvote() {
        ++this.downvote;
    }

    public void addSubcomment(User user, String comment) {
        this.subComments.add(new Subcomment(user, comment));
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
