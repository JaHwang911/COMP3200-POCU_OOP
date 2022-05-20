package test;

public class DangerGetter {
    private final User user;

    public DangerGetter(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
}
