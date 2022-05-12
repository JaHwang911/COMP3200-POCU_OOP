package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;

public class Reaction {
    private ReactionType type;
    private ArrayList<User> users;

    public Reaction(ReactionType type) {
        this.type = type;
        this.users = new ArrayList<>(32);
    }

    public ReactionType getType() {
        return this.type;
    }
    
    public ArrayList<User> getAllUsers() {
        return this.users;
    }

    public int getCount() {
        return users.size();
    }

    public boolean addUser(User user) {
        for (User u : this.users) {
            if (u.equals(user)) {
                return false;
            }
        }

        this.users.add(user);

        return true;
    }

    public boolean subUser(User user) {
        for (User u : this.users) {
            if (u.equals(user)) {
                return true;
            }
        }

        return false;
    }
}
