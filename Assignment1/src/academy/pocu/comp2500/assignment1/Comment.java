package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;
import java.util.Collections;

public class Comment {
    private String comment;
    private ArrayList<Comment> subComments;
    private ArrayList<String> upvoter;
    private ArrayList<String> downvoter;
    private String author;

    public Comment(String author, String text) {
        this.comment = text;
        this.subComments = new ArrayList<>(32);
        this.upvoter = new ArrayList<>(32);
        this.downvoter = new ArrayList<>(32);
        this.author = author;
    }

    public String getComment() {
        return this.comment;
    }

    public String getAuthor() {
        return this.author;
    }

    public void addSubcomment(String name, String text) {
        this.subComments.add(new Comment(name, text));
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

    public boolean addUpvote(String name) {
        for (String uName : this.upvoter) {
            if (uName.equals(name)) {
                return false;
            }
        }

        this.upvoter.add(name);
        return true;
    }

    public boolean cancelUpvote(String name) {
        for (String uName : this.upvoter) {
            if (uName.equals(name)) {
                this.upvoter.remove(uName);
                return true;
            }
        }

        return false;
    }

    public boolean addDownvote(String name) {
        for (String uName : this.downvoter) {
            if (uName.equals(name)) {
                return false;
            }
        }

        this.downvoter.add(name);
        return true;
    }

    public boolean cancelDownvote(String name) {
        for (String uName : this.downvoter) {
            if (uName.equals(name)) {
                this.downvoter.remove(uName);
                return true;
            }
        }

        return false;
    }

    //Modify
    public boolean modifyComment(String name, String text) {
        if (!name.equals(this.author)) {
            System.out.println("This Comment is not your");
            return false;
        }

        this.comment = text;

        return true;
    }
}
