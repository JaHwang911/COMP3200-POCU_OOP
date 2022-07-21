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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Request) || this.hashCode() != obj.hashCode()) {
            return false;
        }

        Request other = (Request) obj;

        if (this.user == null || other.user == null) {
            return this.movieTitle.equals(other.movieTitle);
        }

        return this.movieTitle.equals(other.movieTitle) && this.user.equals(other.user);
    }

    @Override
    public int hashCode() {
        int hash = 17;

        hash = hash * 31 + this.movieTitle.hashCode();

        if (this.user != null) {
            hash = hash * 31 + this.user.hashCode();
        }

        return hash;
    }
}
