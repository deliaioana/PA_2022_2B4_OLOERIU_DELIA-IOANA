package exceptions;

import java.io.IOException;

public class ObjectMapperException extends IOException {
    public ObjectMapperException(Exception exception) {
        super("Object mapper exception.", exception);
    }
}
