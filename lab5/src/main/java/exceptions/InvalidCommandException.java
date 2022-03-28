package exceptions;

import java.io.IOException;

public class InvalidCommandException extends IOException {
    public InvalidCommandException(Exception exception) {
        super("Invalid command", exception);
    }
}
