package academy.pocu.comp2500.lab10;

import academy.pocu.comp2500.lab10.pocuflix.ResultBase;
import academy.pocu.comp2500.lab10.pocuflix.User;

public final class Request {
    private final String movieTitle;
    private User user;

    public Request(String title) {
        this.movieTitle = title;
    }

    public String getMovieTitle() {
        return this.movieTitle;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
