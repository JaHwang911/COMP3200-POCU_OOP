package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;

public class Reaction {
    private ReactionType type;
    private ArrayList<String> users;

    public Reaction(ReactionType type) {
        this.type = type;
        this.users = new ArrayList<>(32);
    }

    public ReactionType getType() {
        return this.type;
    }
    
    public ArrayList<String> getAllUsers() {
        return this.users;
    }

    public int getCount() {
        return users.size();
    }

    public boolean addUser(String user) {
        for (String u : this.users) {
            if (u.equals(user)) {
                return false;
            }
        }

        this.users.add(user);

        return true;
    }

    public boolean subUser(String user) {
        for (String u : this.users) {
            if (u.equals(user)) {
                return true;
            }
        }

        return false;
    }
}
