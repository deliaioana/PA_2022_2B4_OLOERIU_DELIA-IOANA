package exceptions;

import java.io.IOException;

public class FileWriterException extends IOException {
    public FileWriterException(Exception exception) {
        super("File writer exception.", exception);
    }
}
