package academy.pocu.comp2500.lab10;

import academy.pocu.comp2500.lab10.pocuflix.ResultBase;
import academy.pocu.comp2500.lab10.pocuflix.ResultCode;

import java.util.HashMap;

public class CacheMiddleware implements IRequestHandler {
    private final IRequestHandler handler;
    private final int expireCount;
    private final HashMap<Request, CachedResult> cached;

    public CacheMiddleware(IRequestHandler handler, int expireCount) {
        this.handler = handler;
        this.expireCount = expireCount;
        this.cached = new HashMap<>();
    }

    @Override
    public ResultBase handle(Request request) {
        CachedResult cachedResult = this.cached.get(request);

        if (cachedResult == null) {
            return setCache(request);
        }

        int expiryCount = cachedResult.getExpiryCount();

        if (expiryCount > 1) {
            CachedResult cache = new CachedResult(expiryCount - 1);
            this.cached.put(request, cache);

            return cache;
        }

        this.cached.remove(request);

        return setCache(request);
    }

    private ResultBase setCache(Request request) {
        ResultBase handleResult = this.handler.handle(request);

        if (handleResult.getCode() == ResultCode.OK) {
            this.cached.put(request, new CachedResult(this.expireCount));
        }

        return handleResult;
    }
}
