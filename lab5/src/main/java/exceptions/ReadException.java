package exceptions;

import java.io.IOException;

public class ReadException extends IOException {
    public ReadException(Exception exception) {
        super("Read exception.", exception);
    }
}
