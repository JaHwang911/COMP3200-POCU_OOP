package academy.pocu.comp2500.lab10;

import academy.pocu.comp2500.lab10.pocuflix.Movie;
import academy.pocu.comp2500.lab10.pocuflix.NotFoundResult;
import academy.pocu.comp2500.lab10.pocuflix.OkResult;
import academy.pocu.comp2500.lab10.pocuflix.ResultBase;

import java.util.ArrayList;
import java.util.HashMap;

public class MovieStore implements IRequestHandler {
    private final HashMap<String, Movie> movies;
    private final ArrayList<String> moviesString;

    public MovieStore() {
        this.movies = new HashMap<>();
        this.moviesString = new ArrayList<>();
    }

    public void add(Movie movie) {
        this.movies.put(movie.getTitle(), movie);
        this.moviesString.add(movie.getTitle());
    }

    public boolean remove(int index) {
        if (index > this.moviesString.size()) {
            return false;
        }

        this.movies.remove(this.moviesString.get(index));
        return true;
    }

    public ResultBase handle(Request request) {
        assert this.movies.size() == this.moviesString.size();

        if (!this.moviesString.contains(request.getMovieTitle())) {
            return new NotFoundResult();
        }

        return new OkResult(this.movies.get(request.getMovieTitle()));
    }
}
