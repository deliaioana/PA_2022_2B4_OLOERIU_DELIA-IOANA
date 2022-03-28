package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import exceptions.*;
import model.*;
import java.io.File;
import java.io.IOException;

public class SaveCommand extends Command {
    @Override
    public void applyCommand(Catalog catalog) throws InvalidCommandException, InvalidTemplateException,
            FileWriterException, DesktopOpenException, ReadException, ObjectMapperException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            objectMapper.writeValue(new File(super.getPath()), catalog);
        } catch (IOException exception) {
            ObjectMapperException objectMapperException = new ObjectMapperException(exception);
            throw objectMapperException;
        }
    }

    @Override
    public void applyCommand(Catalog catalog, String path) throws IOException {
        super.setPath(path);
        this.applyCommand(catalog);
    }
}
