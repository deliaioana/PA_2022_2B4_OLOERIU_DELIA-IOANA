package commands;

import exceptions.*;
import freemarker.template.Configuration;
import model.*;
import java.io.IOException;

public abstract class Command {
    private Item item;
    private String path;
    private Configuration configuration;

    public abstract void applyCommand(Catalog catalog) throws InvalidCommandException, InvalidTemplateException,
            FileWriterException, DesktopOpenException, ReadException, ObjectMapperException;

    public void applyCommand(Catalog catalog, Item item) {}

    public void applyCommand(Catalog catalog, String path) throws IOException {}

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }

    public void setConfiguration (Configuration configuration) {
        this.configuration = configuration;
    }

    public void applyCommand(Catalog catalog, Configuration configuration) throws InvalidCommandException {
    }
}
