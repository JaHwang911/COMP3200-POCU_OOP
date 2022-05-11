package academy.pocu.comp2500.assignment1;

import academy.pocu.comp2500.assignment1.user.User;

public class Subcomment {
    private String  comment;
    private int     upvote;
    private int     downvote;
    private String  author;

    public Subcomment(User user, String comment) {
        this.comment = comment;
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

    public void addUpvote() {
        ++this.upvote;
    }

    public void addDownvote() {
        ++this.downvote;
    }

    public boolean modifySubcomment(User user, String comment) {
        if (!user.getUserName().equals(this.author)) {
            return false;
        }

        this.comment = comment;

        return true;
    }
}
