package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Index out of range")
public class IndexOutOfRangeException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String errCode;
    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public IndexOutOfRangeException(String index, String errCode, String errMsg){
        super("The index cause problem: " + index );
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
