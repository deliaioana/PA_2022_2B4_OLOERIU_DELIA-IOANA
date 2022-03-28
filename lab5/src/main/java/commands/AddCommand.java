package commands;
import model.*;

public class AddCommand extends Command {
    public void applyCommand(Catalog catalog, Item item){
        setItem(item);
        this.applyCommand(catalog);
    }

    @Override
    public void applyCommand(Catalog catalog) {
        catalog.getItems().add(super.getItem());
    }
}
