package commands;

import com.fasterxml.jackson.databind.ObjectMapper;

import exceptions.*;
import model.*;
import java.io.File;
import java.io.IOException;

public class LoadCommand extends Command {

    @Override
    public void applyCommand(Catalog catalog) throws InvalidCommandException, InvalidTemplateException,
            FileWriterException, DesktopOpenException, ReadException {
        Catalog loadedCatalog;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            loadedCatalog = objectMapper.readValue(new File(super.getPath()), Catalog.class);
        } catch (IOException exception) {
            ReadException readException = new ReadException(exception);
            throw readException;
        }

        catalog.setItems(loadedCatalog.getItems());
        catalog.setName(loadedCatalog.getName());
    }

    public void applyCommand(Catalog catalog, String path) throws IOException {
        super.setPath(path);
        this.applyCommand(catalog);
    }
}
