package academy.pocu.comp2500.lab10;

import academy.pocu.comp2500.lab10.pocuflix.ResultBase;
import academy.pocu.comp2500.lab10.pocuflix.ResultCode;

public class ResultValidator {
    private final ResultCode resultCode;

    public ResultValidator(ResultBase resultBase) {
        this.resultCode = resultBase.getCode();
    }

    public boolean isValid(ResultCode resultCode) {
        return (this.resultCode == resultCode);
    }
}
