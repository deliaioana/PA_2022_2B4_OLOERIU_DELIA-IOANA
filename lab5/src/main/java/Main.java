import com.github.javafaker.Faker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException, InvalidCatalogException {
        Catalog catalog = new Catalog("First Catalog");
        Item item1 = new Book("knuth67", "The Art of Computer Programming",
                "d:/books/programming/tacp.ps", 1967, "Donald E. Knuth", "book");

        Item item2 = new Article("java17", "The Java Language Specification",
                "https://docs.oracle.com/javase/specs/jls/se17/html/index.html",
                2021, "James Gosling & others", "article");

        catalog.add(item1);
        catalog.add(item2);

        System.out.println("\n------------------------ print catalog ------------------------");
        System.out.println(catalog);

        Faker faker = new Faker();
        Catalog catalog3 = new Catalog("Second Catalog");
        Item[] itemArray = IntStream.range(1,11).mapToObj(index ->
                new Book(faker.idNumber().valid(), faker.book().title(),
                        faker.lordOfTheRings().location(), faker.number().numberBetween(1990, 2022), faker.book().author(), "book")).toArray(Item[]::new);

        catalog3.addItems(itemArray);

        Catalog.save(catalog3, "D:\\E\\Anul_2_Semestrul_2\\PA\\here\\json.json");
        System.out.println("\n------------------------ print loaded catalog ------------------------");
        Catalog loadedCatalog;
        loadedCatalog = Catalog.load("D:\\E\\Anul_2_Semestrul_2\\PA\\here\\json.json");
        System.out.println(loadedCatalog);
    }
}
