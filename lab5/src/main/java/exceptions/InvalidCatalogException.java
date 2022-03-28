package exceptions;

import java.io.IOException;

public class InvalidCatalogException extends IOException {
    public InvalidCatalogException(Exception exception) {
        super("Invalid catalog file.", exception);
    }
}
