package academy.pocu.comp2500.lab10.app;

import academy.pocu.comp2500.lab10.*;
import academy.pocu.comp2500.lab10.pocuflix.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class Program {

    public static void main(String[] args) {
        testOfficial();
        testMovieStore();
        testMaintenanceMiddleware();
        testAuthorizationMiddleware();
        testCacheMiddleware();
        testResultValid();

        test0();
        testCacheMiddlewareChanning();

        System.out.println("No prob: lab 10");
    }

    private static void testOfficial() {
        MovieStore store = new MovieStore();

        store.add(new Movie("Harry Potter", Rating.PG13, 180));
        store.add(new Movie("The Lord of the Rings", Rating.R, 180));

        assert (!store.remove(2));
        assert (store.remove(1));

        {
            Request request = new Request("None");

            ResultBase result = store.handle(request);

            assert (result.getCode() == ResultCode.NOT_FOUND);
            assert (result instanceof NotFoundResult);

            request = new Request("Harry Potter");

            result = store.handle(request);

            assert (result.getCode() == ResultCode.OK);
            assert (result instanceof OkResult);

            OkResult okResult = (OkResult) result;

            assert (okResult.getMovie().getTitle().equals("Harry Potter"));
            assert (okResult.getMovie().getRating() == Rating.PG13);
            assert (okResult.getMovie().getPlayTime() == 180);
        }

        {
            OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
            OffsetDateTime startDateTime = now.plusSeconds(5);
            OffsetDateTime endDateTime = startDateTime.plusHours(1);

            MaintenanceMiddleware middleware = new MaintenanceMiddleware(store, startDateTime);

            Request request = new Request("Harry Potter");

            ResultBase result = middleware.handle(request);

            assert (result.getCode() == ResultCode.OK);
            assert (result instanceof OkResult);

            sleep(10);

            request = new Request("Harry Potter");

            result = middleware.handle(request);

            assert (result.getCode() == ResultCode.SERVICE_UNAVAILABLE);
            assert (result instanceof ServiceUnavailableResult);

            ServiceUnavailableResult serviceUnavailableResult = (ServiceUnavailableResult) result;

            assert (serviceUnavailableResult.getStartDateTime().compareTo(startDateTime) == 0);
            assert (serviceUnavailableResult.getEndDateTime().compareTo(endDateTime) == 0);
        }

        {
            HashSet<User> users = new HashSet<>();
            users.add(new User("Jane", "Doe"));

            AuthorizationMiddleware middleware = new AuthorizationMiddleware(store, users);

            Request request = new Request("Harry Potter");

            ResultBase result = middleware.handle(request);

            assert (result.getCode() == ResultCode.UNAUTHORIZED);
            assert (result instanceof UnauthorizedResult);

            UnauthorizedResult unauthorizedResult = (UnauthorizedResult) result;

            assert (unauthorizedResult.getErrorMessage().equals("Unauthorized access"));

            request = new Request("Harry Potter");
            request.setUser(new User("James", "Bob"));

            result = middleware.handle(request);

            assert (result.getCode() == ResultCode.UNAUTHORIZED);
            assert (result instanceof UnauthorizedResult);

            unauthorizedResult = (UnauthorizedResult) result;

            assert (unauthorizedResult.getErrorMessage().equals("Unauthorized access"));

            request = new Request("Harry Potter");
            request.setUser(new User("Jane", "Doe"));

            result = middleware.handle(request);

            assert (result.getCode() == ResultCode.OK);
            assert (result instanceof OkResult);
        }

        {
            CacheMiddleware middleware = new CacheMiddleware(store, 3);

            Request request = new Request("Harry Potter");

            ResultBase result = middleware.handle(request);

            assert (result.getCode() == ResultCode.OK);
            assert (result instanceof OkResult);

            request = new Request("Harry Potter");

            result = middleware.handle(request);

            assert (result.getCode() == ResultCode.NOT_MODIFIED);
            assert (result instanceof CachedResult);

            CachedResult cachedResult = (CachedResult) result;

            assert (cachedResult.getExpiryCount() == 2);

            request = new Request("Harry Potter");
            request.setUser(new User("Jane", "Doe"));

            result = middleware.handle(request);

            assert (result.getCode() == ResultCode.OK);
            assert (result instanceof OkResult);

            request = new Request("Harry Potter");

            result = middleware.handle(request);

            assert (result.getCode() == ResultCode.NOT_MODIFIED);
            assert (result instanceof CachedResult);

            cachedResult = (CachedResult) result;

            assert (cachedResult.getExpiryCount() == 1);

            request = new Request("Harry Potter");
            request.setUser(new User("Jane", "Doe"));

            result = middleware.handle(request);

            assert (result.getCode() == ResultCode.NOT_MODIFIED);
            assert (result instanceof CachedResult);

            cachedResult = (CachedResult) result;

            assert (cachedResult.getExpiryCount() == 2);

            request = new Request("Harry Potter");

            result = middleware.handle(request);

            assert (result.getCode() == ResultCode.OK);
            assert (result instanceof OkResult);
        }
        {
            OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);

            ResultBase result = new ServiceUnavailableResult(now, now);

            ResultValidator validator = new ResultValidator(result);

            assert (validator.isValid(ResultCode.SERVICE_UNAVAILABLE));
            assert (!validator.isValid(ResultCode.OK));
            assert (!validator.isValid(ResultCode.NOT_FOUND));
        }
    }

    private static void test0() {
        User user0 = new User("user0", "userpw0");
        User user1 = new User("user1", "userpw1");
        User user2 = new User("user2", "userpw2");

        Request request0 = new Request("Movie 0");
        Request request1 = new Request("Movie 1");
        Request request2 = new Request("Movie 2");
        Request request3 = new Request("Unknown Movie");
        Request request4 = new Request("Unknown Movie");

        request0.setUser(user0);
        request1.setUser(user1);
        request2.setUser(user2);
        request3.setUser(user0); // authorized
        request4.setUser(user2); // unauthorized

        Movie movie0 = new Movie("Movie 0", Rating.R, 180);
        Movie movie1 = new Movie("Movie 1", Rating.PG, 60);
        Movie movie2 = new Movie("Movie 2", Rating.PG13, 70);

        MovieStore movieStore = new MovieStore();
        movieStore.add(movie0);
        movieStore.add(movie1);
        movieStore.add(movie2);

        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        OffsetDateTime startDateTime = now.plusSeconds(5);
        OffsetDateTime endDateTime = startDateTime.plusHours(1);
        MaintenanceMiddleware maintenanceMiddleware = new MaintenanceMiddleware(movieStore, startDateTime);

        HashSet<User> authorizedUserSet = new HashSet<>();
        authorizedUserSet.add(user0);
        authorizedUserSet.add(user1);
        AuthorizationMiddleware authorizationMiddleware = new AuthorizationMiddleware(maintenanceMiddleware, authorizedUserSet);

        CacheMiddleware cacheMiddleware = new CacheMiddleware(authorizationMiddleware, 2);

        ResultBase result = cacheMiddleware.handle(request2);
        ResultValidator validator = new ResultValidator(result);
        assert (validator.isValid(ResultCode.UNAUTHORIZED));

        result = cacheMiddleware.handle(request3);
        validator = new ResultValidator(result);
        assert (validator.isValid(ResultCode.NOT_FOUND));

        result = cacheMiddleware.handle(request4);
        validator = new ResultValidator(result);
        assert (validator.isValid(ResultCode.UNAUTHORIZED));

        result = cacheMiddleware.handle(request0);
        validator = new ResultValidator(result);
        assert (validator.isValid(ResultCode.OK));

        result = cacheMiddleware.handle(request0);
        validator = new ResultValidator(result);
        assert (validator.isValid(ResultCode.NOT_MODIFIED));
        assert (((CachedResult) result).getExpiryCount() == 1);

        result = cacheMiddleware.handle(request1);
        validator = new ResultValidator(result);
        assert (validator.isValid(ResultCode.OK));

        result = cacheMiddleware.handle(request1);
        validator = new ResultValidator(result);
        assert (validator.isValid(ResultCode.NOT_MODIFIED));
        assert (((CachedResult) result).getExpiryCount() == 1);

        result = cacheMiddleware.handle(request0);
        validator = new ResultValidator(result);
        assert (validator.isValid(ResultCode.OK));

        result = cacheMiddleware.handle(request1);
        validator = new ResultValidator(result);
        assert (validator.isValid(ResultCode.OK));

        result = cacheMiddleware.handle(request0); // remove cache
        validator = new ResultValidator(result);
        assert (validator.isValid(ResultCode.NOT_MODIFIED));
        assert (((CachedResult) result).getExpiryCount() == 1);

        result = cacheMiddleware.handle(request1); // remove cache
        validator = new ResultValidator(result);
        assert (validator.isValid(ResultCode.NOT_MODIFIED));
        assert (((CachedResult) result).getExpiryCount() == 1);

        sleep(10); // maintenance

        result = cacheMiddleware.handle(request2);
        validator = new ResultValidator(result);
        assert (validator.isValid(ResultCode.UNAUTHORIZED));

        result = cacheMiddleware.handle(request3);
        validator = new ResultValidator(result);
        assert (validator.isValid(ResultCode.SERVICE_UNAVAILABLE));

        result = cacheMiddleware.handle(request4);
        validator = new ResultValidator(result);
        assert (validator.isValid(ResultCode.UNAUTHORIZED));

        result = cacheMiddleware.handle(request0);
        validator = new ResultValidator(result);
        assert (validator.isValid(ResultCode.SERVICE_UNAVAILABLE));

        result = cacheMiddleware.handle(request0);
        validator = new ResultValidator(result);
        assert (validator.isValid(ResultCode.SERVICE_UNAVAILABLE));

        result = cacheMiddleware.handle(request1);
        validator = new ResultValidator(result);
        assert (validator.isValid(ResultCode.SERVICE_UNAVAILABLE));

        result = cacheMiddleware.handle(request1);
        validator = new ResultValidator(result);
        assert (validator.isValid(ResultCode.SERVICE_UNAVAILABLE));

        result = cacheMiddleware.handle(request0);
        validator = new ResultValidator(result);
        assert (validator.isValid(ResultCode.SERVICE_UNAVAILABLE));

        result = cacheMiddleware.handle(request1);
        validator = new ResultValidator(result);
        assert (validator.isValid(ResultCode.SERVICE_UNAVAILABLE));





// ===========================================
// CacheMiddleware channing test
// ===========================================

    }

    private static void testCacheMiddlewareChanning() {
        MovieStore store = new MovieStore();

        store.add(new Movie("Harry Potter", Rating.PG13, 180));
        store.add(new Movie("The Lord of the Rings", Rating.R, 180));

        Request request;
        ResultBase result;

        // ===========================================
        // 1.MaintenanceMiddleware -> CacheMiddleware
        // ===========================================
        {
            // maintain middleware
            OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
            OffsetDateTime startDateTime = now.plusSeconds(3);

            MaintenanceMiddleware maintainMiddleware = new MaintenanceMiddleware(store, startDateTime);

            request = new Request("Harry Potter");
            result = maintainMiddleware.handle(request);
            assert (result.getCode() == ResultCode.OK);
            assert (result instanceof OkResult);

            // cached middle ware
            CacheMiddleware cachedMiddleware = new CacheMiddleware(maintainMiddleware, 3);

            request = new Request("Harry Potter");
            result = cachedMiddleware.handle(request);
            assert (result.getCode() == ResultCode.OK);
            assert (result instanceof OkResult);


            request = new Request("Harry Potter");
            result = cachedMiddleware.handle(request);
            assert (result.getCode() == ResultCode.NOT_MODIFIED);
            assert (result instanceof CachedResult);

            CachedResult cachedResult = (CachedResult) result;
            assert (cachedResult.getExpiryCount() == 2);


            sleep(5);

            request = new Request("Harry Potter");
            result = cachedMiddleware.handle(request);
            assert (result.getCode() == ResultCode.NOT_MODIFIED);
            assert (result instanceof CachedResult);

            cachedResult = (CachedResult) result;
            assert (cachedResult.getExpiryCount() == 1);

            request = new Request("Harry Potter");
            result = cachedMiddleware.handle(request);
            assert (result.getCode() == ResultCode.SERVICE_UNAVAILABLE);
            assert (result instanceof ServiceUnavailableResult);

        }

        // ===========================================
        // 2.AuthorizationMiddleware -> CacheMiddleware
        // ===========================================
        {
            // authorization middleware
            HashSet<User> users = new HashSet<>();
            users.add(new User("Jane", "Doe"));

            AuthorizationMiddleware authorizationMiddleware = new AuthorizationMiddleware(store, users);

            request = new Request("Harry Potter");
            request.setUser(new User("Jane", "Doe"));
            result = authorizationMiddleware.handle(request);

            assert (result.getCode() == ResultCode.OK);
            assert (result instanceof OkResult);

            // cached middle ware
            CacheMiddleware cachedMiddleware = new CacheMiddleware(authorizationMiddleware, 3);
            CachedResult cachedResult;

            request = new Request("Harry Potter");
            result = cachedMiddleware.handle(request);
            assert (result.getCode() == ResultCode.UNAUTHORIZED);
            assert (result instanceof UnauthorizedResult);


            request = new Request("Harry Potter");
            request.setUser(new User("Jane", "Doe"));
            result = cachedMiddleware.handle(request);
            assert (result.getCode() == ResultCode.OK);
            assert (result instanceof OkResult);

            request = new Request("Harry Potter");
            request.setUser(new User("Jane", "Doe"));
            result = cachedMiddleware.handle(request);
            assert (result.getCode() == ResultCode.NOT_MODIFIED);
            assert (result instanceof CachedResult);

            cachedResult = (CachedResult) result;
            assert (cachedResult.getExpiryCount() == 2);

            request = new Request("Harry Potter");
            request.setUser(new User("Jane", "Doe"));
            result = cachedMiddleware.handle(request);
            assert (result.getCode() == ResultCode.NOT_MODIFIED);
            assert (result instanceof CachedResult);

            cachedResult = (CachedResult) result;
            assert (cachedResult.getExpiryCount() == 1);
        }

        // ===========================================
        // 3.CacheMiddleware  -> AuthorizationMiddleware
        // ===========================================
        {
            // cached middle ware
            CacheMiddleware cachedMiddleware = new CacheMiddleware(store, 3);
            request = new Request("Harry Potter");
            result = cachedMiddleware.handle(request);
            assert (result.getCode() == ResultCode.OK);
            assert (result instanceof OkResult);

            request = new Request("Harry Potter");
            result = cachedMiddleware.handle(request);
            assert (result.getCode() == ResultCode.NOT_MODIFIED);
            assert (result instanceof CachedResult);

            CachedResult cachedResult = (CachedResult) result;
            assert (cachedResult.getExpiryCount() == 2);

            // authorization middleware
            HashSet<User> users = new HashSet<>();
            users.add(new User("Jane", "Doe"));

            AuthorizationMiddleware authorizationMiddleware = new AuthorizationMiddleware(cachedMiddleware, users);

            request = new Request("Harry Potter");
            request.setUser(new User("Jane", "Doe"));
            result = authorizationMiddleware.handle(request);

            assert (result.getCode() == ResultCode.OK);
            assert (result instanceof OkResult);

            request = new Request("Harry Potter");
            request.setUser(new User("Jane", "Doe"));
            result = authorizationMiddleware.handle(request);

            assert (result.getCode() == ResultCode.NOT_MODIFIED);
            assert (result instanceof CachedResult);

            cachedResult = (CachedResult) result;
            assert (cachedResult.getExpiryCount() == 2);
        }

        // ===========================================
        // 4.CacheMiddleware  -> MaintenanceMiddleware
        // ===========================================
        {
            // cached middle ware
            CacheMiddleware cachedMiddleware = new CacheMiddleware(store, 3);
            request = new Request("Harry Potter");
            result = cachedMiddleware.handle(request);
            assert (result.getCode() == ResultCode.OK);
            assert (result instanceof OkResult);

            // maintain middleware
            OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
            OffsetDateTime startDateTime = now.plusSeconds(3);

            MaintenanceMiddleware maintainMiddleware = new MaintenanceMiddleware(cachedMiddleware, startDateTime);

            request = new Request("Harry Potter");
            result = maintainMiddleware.handle(request);
            assert (result.getCode() == ResultCode.NOT_MODIFIED);
            assert (result instanceof CachedResult);

            CachedResult cachedResult = (CachedResult) result;
            assert (cachedResult.getExpiryCount() == 2);

            sleep(5);

            request = new Request("Harry Potter");
            result = maintainMiddleware.handle(request);
            assert (result.getCode() == ResultCode.SERVICE_UNAVAILABLE);
            assert (result instanceof ServiceUnavailableResult);
        }
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

    private static void testCacheMiddleware() {
        MovieStore store = new MovieStore();
        Movie movie = new Movie("The Lord of the Rings", Rating.R, 180);

        store.add(movie);

        CacheMiddleware middleware = new CacheMiddleware(store, 4);

        Request request = new Request("The Lord of the Rings");

        ResultBase result = middleware.handle(request); // OkResult
        assert (result.getCode() == ResultCode.OK);

        request = new Request("The Lord of the Rings");

        result = middleware.handle(request); // CachedResult: 3
        assert (result.getCode() == ResultCode.NOT_MODIFIED);
        assert (((CachedResult) result).getExpiryCount() == 3);

        request = new Request("The Lord of the Rings");
        request.setUser(new User("Ja Hwang", "1111"));

        result = middleware.handle(request);
        assert (result.getCode() == ResultCode.OK);

        request = new Request("Ready player one");

        result = middleware.handle(request);
        assert (result.getCode() == ResultCode.NOT_FOUND);

        request = new Request("The Lord of the Rings");

        result = middleware.handle(request); // CachedResult: 2
        assert (result.getCode() == ResultCode.NOT_MODIFIED);
        assert (((CachedResult) result).getExpiryCount() == 2);

        request = new Request("The Lord of the Rings");
        request.setUser(new User("Ja Hwang", "1111"));

        result = middleware.handle(request); // CachedResult: 2
        assert (result.getCode() == ResultCode.NOT_MODIFIED);
        assert (((CachedResult) result).getExpiryCount() == 3);

        request = new Request("The Lord of the Rings");

        result = middleware.handle(request); // CachedResult: 1
        assert (result.getCode() == ResultCode.NOT_MODIFIED);
        assert (((CachedResult) result).getExpiryCount() == 1);

        request = new Request("The Lord of the Rings");

        result = middleware.handle(request); // OkResult
        assert (result.getCode() == ResultCode.OK);
    }

    private static void testResultValid() {
        ResultBase result = new UnauthorizedResult();

        ResultValidator validator = new ResultValidator(result);

        assert validator.isValid(ResultCode.UNAUTHORIZED);
        assert !validator.isValid(ResultCode.OK);
    }

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
