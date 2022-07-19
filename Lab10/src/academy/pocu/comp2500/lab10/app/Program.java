package academy.pocu.comp2500.lab10.app;

import academy.pocu.comp2500.lab10.AuthorizationMiddleware;
import academy.pocu.comp2500.lab10.MaintenanceMiddleware;
import academy.pocu.comp2500.lab10.MovieStore;
import academy.pocu.comp2500.lab10.Request;
import academy.pocu.comp2500.lab10.pocuflix.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;

public class Program {

    public static void main(String[] args) {
        testMovieStore();
        testMaintenanceMiddleware();
        testAuthorizationMiddleware();

        System.out.println("No prob: lab 10");
    }

    private static void testMovieStore() {
        MovieStore store = new MovieStore();
        Movie movie0 = new Movie("Spider man", Rating.G, 160);
        Movie movie1 = new Movie("Thor", Rating.G, 120);

        store.add(movie0);
        store.add(movie1);

        Request request0 = new Request("Spider man");
        Request request1 = new Request("Doctor strange");

        assert (ResultCode.OK == store.handle(request0).getCode());
        assert (ResultCode.NOT_FOUND == store.handle(request1).getCode());
    }

    private static void testMaintenanceMiddleware() {
        MovieStore store = new MovieStore();
        Movie movie = new Movie("The Lord of the Rings", Rating.R, 180);

        store.add(movie);

        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);

        MaintenanceMiddleware middleware = new MaintenanceMiddleware(store, now);

        Request request = new Request("The Lord of the Rings");
        assert (middleware.handle(request).getCode() == ResultCode.SERVICE_UNAVAILABLE);
    }

    private static void testAuthorizationMiddleware() {
        MovieStore store = new MovieStore();
        Movie movie = new Movie("Ready player one", Rating.G, 111);

        store.add(movie);

        HashSet<User> users= new HashSet<>();
        users.add(new User("Ja Hwang", "1111"));

        AuthorizationMiddleware middleware = new AuthorizationMiddleware(store, users);

        Request request = new Request("Ready player one");
        User user = new User("Bilbo", "Baggins");

        request.setUser(user);

        assert (middleware.handle(request).getCode() == ResultCode.UNAUTHORIZED);

        user = new User("Ja Hwang", "1111");

        request.setUser(user);

        assert (middleware.handle(request).getCode() == ResultCode.OK);
    }
}
