package exceptions;

import java.io.IOException;

public class InvalidTemplateException extends IOException {
    public InvalidTemplateException(Exception exception) {
        super("Invalid template", exception);
    }
}
