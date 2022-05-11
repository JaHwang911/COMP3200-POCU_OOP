package academy.pocu.comp2500.assignment1;

public class Reaction {
    private ReactionType type;
    private String userName;

    public Reaction(User user, ReactionType type) {
        this.type = type;
        this.userName = user.getUserName();
    }

    public ReactionType getType() {
        return this.type;
    }

    public String getUserName() {
        return this.userName;
    }
}
