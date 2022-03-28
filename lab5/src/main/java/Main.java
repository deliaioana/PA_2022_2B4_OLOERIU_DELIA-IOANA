import com.github.javafaker.Faker;
import commands.*;
import exceptions.InvalidCatalogException;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import model.Book;
import model.Catalog;
import model.Item;

import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException, InvalidCatalogException {
        System.out.println("\n------------------------ homework ------------------------");

        Command addCommand = new AddCommand();
        Command infoCommand = new InfoCommand();
        Command listCommand = new ListCommand();
        Command loadCommand = new LoadCommand();
        Command reportCommand = new ReportCommand();
        Command saveCommand = new SaveCommand();
        Command viewCommand = new ViewCommand();

        Faker faker = new Faker();
        String path = "D:\\E\\Anul_2_Semestrul_2\\PA\\PA_2022_2B4_OLOERIU_DELIA-IOANA\\lab5\\here\\json.json";

        /* Create and adjust configuration */
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_29);
        configuration.setDirectoryForTemplateLoading(
                new File("D:\\E\\Anul_2_Semestrul_2\\PA\\PA_2022_2B4_OLOERIU_DELIA-IOANA\\lab5\\here\\"));
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setLogTemplateExceptions(false);
        configuration.setWrapUncheckedExceptions(true);
        configuration.setFallbackOnNullLoopVariable(false);

        Catalog catalog = new Catalog("First Catalog");
        Item[] itemArray = IntStream.range(1,11).mapToObj(index ->
                new Book(faker.idNumber().valid(), faker.book().title(),
                        faker.lordOfTheRings().location(), faker.number().numberBetween(1990, 2022),
                        faker.book().author(), "book")).toArray(Item[]::new);

        catalog.addItems(itemArray);

        System.out.println("\n------------------------ save ------------------------");
        saveCommand.applyCommand(catalog, path);
        System.out.println("--catalog saved!--");

        System.out.println("\n------------------------ load ------------------------");
        Catalog loadedCatalog = new Catalog();
        loadCommand.applyCommand(loadedCatalog, path);
        System.out.println("--catalog loaded!--");

        System.out.println("\n------------------------ list ------------------------");
        listCommand.applyCommand(loadedCatalog);
        System.out.println("--catalog items listed!--");

        System.out.println("\n------------------------ view ------------------------");
        viewCommand.applyCommand(catalog, path);
        System.out.println("--check visual studio!--");

        System.out.println("\n------------------------ add ------------------------");
        addCommand.applyCommand(catalog, itemArray[0]);
        System.out.println("--items added!--");
        listCommand.applyCommand(catalog);

        System.out.println("\n------------------------ report ------------------------");
        reportCommand.applyCommand(catalog, configuration);
    }
}
