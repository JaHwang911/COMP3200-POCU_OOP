package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;

public class Comment {
    private String              comment;
    private ArrayList<String>   subComments;
    private String              userName;

    public Comment(String userName, String comment) {
        this.comment = comment;
        this.subComments = new ArrayList<String>(32);
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<String> getSubComments() {
        return subComments;
    }

    public void addSubComment(String comment) {
        subComments.add(comment);
    }
}
