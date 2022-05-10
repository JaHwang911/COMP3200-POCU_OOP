package academy.pocu.comp2500.assignment1;

import academy.pocu.comp2500.assignment1.user.User;

public class Reaction {
    private ReactionType    type;
    private String          userName;

    public Reaction(User user, ReactionType type) {
        this.type = type;
        this.userName = user.getUserName();
    }

    public String getUserName() {
        return this.userName;
    }
}
