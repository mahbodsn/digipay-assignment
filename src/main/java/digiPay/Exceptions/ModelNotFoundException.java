package digiPay.Exceptions;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ModelNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 2L;
    private String msg;

    public ModelNotFoundException(String msg) {
        this.msg = msg;
    }
}
