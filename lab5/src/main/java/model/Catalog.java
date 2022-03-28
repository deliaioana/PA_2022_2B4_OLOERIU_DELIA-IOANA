package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import commands.AddCommand;
import exceptions.InvalidCatalogException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Catalog {
    private ArrayList<Item> items = new ArrayList<>();
    private String name;
    private String url;

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

    @Override
    public String toString() {
        return "model.Catalog{\n" +
                "name=" + name +
                "\nitems=" + items +
                '}';
    }

    public static void save(Catalog catalog, String path) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.writeValue(new File(path), catalog);
    }

    public static Catalog load(String path) throws IOException, InvalidCatalogException {
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

    public void addItems(Item[] itemArray) {
        Arrays.stream(itemArray).forEach(this::add);
    }

    public void add(Item item){
        AddCommand addCommand = new AddCommand();
        addCommand.applyCommand(this, item);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
