package exceptions;

import java.io.IOException;

public class DesktopOpenException extends IOException {
    public DesktopOpenException(Exception exception) {
        super("Desktop open exception.", exception);
    }
}
