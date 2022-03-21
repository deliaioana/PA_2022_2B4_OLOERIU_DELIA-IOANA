import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Catalog {
    private ArrayList<Item> items = new ArrayList<>();
    private String name;

    public Catalog(String name){
        this.setName(name);
    }

    public Catalog(){
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void add(Item item){
        items.add(item);
    }

    @Override
    public String toString() {
        return "Catalog{\n" +
                "name=" + name +
                "\nitems=" + items +
                '}';
    }

    public static void save(Catalog catalog, String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.writeValue(new File(path), catalog);
    }

    public static Catalog load(String path) throws InvalidCatalogException, IOException {
        Catalog loadedCatalog;
        ObjectMapper objectMapper = new ObjectMapper();
        loadedCatalog = objectMapper.readValue(new File(path), Catalog.class);
        return loadedCatalog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
