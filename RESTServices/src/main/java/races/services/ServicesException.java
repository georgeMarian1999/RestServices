package races.services;

import java.io.Serializable;

public class ServicesException extends RuntimeException {
    public ServicesException(Exception e) {
        super(e);
    }

    public ServicesException(String message) {
        super(message);
    }
}
