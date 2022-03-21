import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

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

        System.out.println("------------------------ print catalog ------------------------");
        System.out.println(catalog);

        Catalog catalog2 = new Catalog("Second Catalog");
        Item item3 = new Book("100", "Design Patterns", "d:/books/dp.pdf", 1994, "Erich Gamma", "book");

        catalog2.add(item3);
        catalog2.add(item1);
        catalog2.add(item2);

        Catalog.save(catalog2, "D:\\E\\Anul_2_Semestrul_2\\PA\\here\\json.json");

        System.out.println("------------------------ print loaded catalog ------------------------");
        Catalog loadedCatalog;
        loadedCatalog = Catalog.load("D:\\E\\Anul_2_Semestrul_2\\PA\\here\\json.json");
        System.out.println(loadedCatalog);
    }
}
