package commands;

import exceptions.*;
import model.Catalog;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ViewCommand extends Command{
    public void applyCommand(Catalog catalog, String path) throws IOException {
        super.setPath(path);
        this.applyCommand(catalog);
    }

    @Override
    public void applyCommand(Catalog catalog) throws InvalidCommandException, InvalidTemplateException,
            FileWriterException, DesktopOpenException, ReadException {
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(new File(super.getPath()));
        } catch (IOException exception) {
            DesktopOpenException desktopOpenException = new DesktopOpenException(exception);
            throw desktopOpenException;
        }
    }
}
