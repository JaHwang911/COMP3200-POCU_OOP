package academy.pocu.comp2500.lab10;

public final class Request {
    private String movieTitle;
    private User user;

    public Request(String title) {
        this.movieTitle = title;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
