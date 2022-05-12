package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;

public class Reaction {
    private ReactionType type;
    private ArrayList<String> userNames;

    public Reaction(ReactionType type) {
        this.type = type;
        this.userNames = new ArrayList<String>(32);
    }

    public ReactionType getType() {
        return this.type;
    }

    public int getCount() {
        return userNames.size();
    }

    public boolean addUser(String name) {
        for (String u : this.userNames) {
            if (u.equals(name)) {
                return false;
            }
        }

        this.userNames.add(name);

        return true;
    }

    public boolean subUser(String name) {
        for (String u : this.userNames) {
            if (u.equals(name)) {
                return true;
            }
        }

        return false;
    }
}
