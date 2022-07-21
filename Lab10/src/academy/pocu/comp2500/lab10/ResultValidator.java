package academy.pocu.comp2500.lab10;

import academy.pocu.comp2500.lab10.pocuflix.NotFoundResult;
import academy.pocu.comp2500.lab10.pocuflix.OkResult;
import academy.pocu.comp2500.lab10.pocuflix.ResultBase;
import academy.pocu.comp2500.lab10.pocuflix.ResultCode;

public class ResultValidator {
    private final ResultBase resultbase;

    public ResultValidator(ResultBase resultBase) {
        this.resultbase = resultBase;
    }

    public boolean isValid(ResultCode resultCode) {
        if (this.resultbase.getCode() != resultCode) {
            return false;
        }

        switch (resultCode) {
            case OK:
                return this.resultbase instanceof OkResult;
            case NOT_MODIFIED:
                return this.resultbase instanceof CachedResult;
            case SERVICE_UNAVAILABLE:
                return this.resultbase instanceof ServiceUnavailableResult;
            case UNAUTHORIZED:
                return this.resultbase instanceof UnauthorizedResult;
            case NOT_FOUND:
                return this.resultbase instanceof NotFoundResult;
            default:
                assert false : "Unknown result type";
                return false;
        }
    }
}
