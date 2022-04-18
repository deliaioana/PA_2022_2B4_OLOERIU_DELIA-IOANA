import java.sql.SQLException;

public class Main {
    public static void main(String args[]) throws SQLException {
        var continents = new ContinentDAO();
        continents.create("Europe");
        Database.getConnection().commit();

        var countries = new CountryDAO();
        int europeId = continents.findByName("Europe3");

        countries.create("Romania", europeId);
        countries.create("Ukraine", europeId);

        countries.create("Romania2", europeId);
        Database.getConnection().commit();

        //Database.printTable("countries");
        //Database.printTable("continents");

        Database.closeConnection();

    }
}
