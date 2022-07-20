package academy.pocu.comp2500.lab10;

import academy.pocu.comp2500.lab10.pocuflix.ResultBase;
import academy.pocu.comp2500.lab10.pocuflix.ResultCode;

public class CacheResult extends ResultBase {
    private int currentCount;

    public CacheResult(int expireCount) {
        super(ResultCode.NOT_MODIFIED);
        this.currentCount = expireCount;
    }

    public int getExpiryCount() {
        return this.currentCount;
    }
}
