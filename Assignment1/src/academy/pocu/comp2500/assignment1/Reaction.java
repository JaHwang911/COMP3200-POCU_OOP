package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;

public class Reaction {
    private ReactionType type;
    private int count;
    private ArrayList<String> userNames;

    public Reaction(ReactionType type) {
        this.type = type;
        this.count = 0;
        this.userNames = new ArrayList<String>(32);
    }

    public ReactionType getType() {
        return this.type;
    }

    public int getCount() {
        return this.count;
    }

    public boolean addCount(User user) {
        String name = user.getUserName();

        for (String u : this.userNames) {
            if (u.equals(name)) {
                return false;
            }
        }

        this.userNames.add(name);
        ++this.count;

        return true;
    }

    public boolean subCount(User user) {
        String name = user.getUserName();

        for (String u : this.userNames) {
            if (u.equals(name)) {
                --this.count;
                return true;
            }
        }

        return false;
    }
}
