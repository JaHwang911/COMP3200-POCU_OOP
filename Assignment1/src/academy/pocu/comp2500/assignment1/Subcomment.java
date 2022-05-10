package academy.pocu.comp2500.assignment1;

import academy.pocu.comp2500.assignment1.user.User;

public class Subcomment {
    private String comment;
    private String author;

    public Subcomment(User user, String comment) {
        this.comment = comment;
        this.author = user.getUserName();
    }

    public boolean modifySubcomment(User user, String comment) {
        if (!user.getUserName().equals(this.author)) {
            return false;
        }

        this.comment = comment;

        return true;
    }
}
