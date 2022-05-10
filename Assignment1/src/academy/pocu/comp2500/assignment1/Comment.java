package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;
import academy.pocu.comp2500.assignment1.user.User;

public class Comment {
    private String              comment;
    private ArrayList<Subcomment>  subComments;
    private int                 upvote;
    private int                 downvote;
    private String              author;

    public Comment(String author, String comment) {
        this.comment = comment;
        this.subComments = new ArrayList<>(32);
        this.upvote = 0;
        this.downvote = 0;
        this.author = author;
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

    public ArrayList<Subcomment> getSubComments() {
        return subComments;
    }

    //Add
    public void addUpvote() {
        ++this.upvote;
    }

    public void addDownvote() {
        ++this.downvote;
    }

    public void addSubComment(User user, String comment) {
        Subcomment newSubcomment = new Subcomment(user, comment);
        subComments.add(newSubcomment);
    }

    //Modify
    public boolean modifyComment(User user, String text) {
        if (!user.getUserName().equals(this.author)) {
            return false;
        }

        this.comment = text;

        return true;
    }
}
