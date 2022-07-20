package academy.pocu.comp2500.lab10;

import academy.pocu.comp2500.lab10.pocuflix.ResultBase;

public class CacheMiddleware implements IRequestHandler {
    private final int EXPIRE_COUNT;
    private final IRequestHandler handler;
    private CacheResult cacheResult;

    public CacheMiddleware(IRequestHandler handler, int expireCount, CacheResult cacheResult){
        this.handler = handler;
        this.EXPIRE_COUNT = expireCount;
        this.cacheResult = cacheResult;
    }

    public ResultBase handle(Request request) {
        return null;
    }
}
