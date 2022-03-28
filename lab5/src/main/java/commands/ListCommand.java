package commands;

import exceptions.DesktopOpenException;
import exceptions.FileWriterException;
import exceptions.InvalidCommandException;
import exceptions.InvalidTemplateException;
import model.Catalog;
import model.Item;

public class ListCommand extends Command{

    @Override
    public void applyCommand(Catalog catalog) throws InvalidCommandException, InvalidTemplateException,
            FileWriterException, DesktopOpenException {
        int index = 1;
        for (Item item: catalog.getItems()){
            System.out.println("ITEM " + index + ": " + item.toString());
            ++index;
        }
    }
}
