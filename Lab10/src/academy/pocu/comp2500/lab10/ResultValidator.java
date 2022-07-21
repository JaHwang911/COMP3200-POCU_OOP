package academy.pocu.comp2500.lab10;

import academy.pocu.comp2500.lab10.pocuflix.ResultBase;
import academy.pocu.comp2500.lab10.pocuflix.ResultCode;

public class ResultValidator {
    private final ResultBase resultbase;

    public ResultValidator(ResultBase resultBase) {
        this.resultbase = resultBase;
    }

    public boolean isValid(ResultCode resultCode) {
        return (this.resultbase.getCode() == resultCode);
    }
}
