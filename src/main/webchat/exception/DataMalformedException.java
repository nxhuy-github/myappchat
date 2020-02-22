package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Data is malformed")
public class DataMalformedException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public DataMalformedException(String dataJson){
        super(dataJson + " is malformed!");
    }
}
