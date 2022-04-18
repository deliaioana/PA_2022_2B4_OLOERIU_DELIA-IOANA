import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class ContinentDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void create(String name) throws SQLException {

        Connection connection = Database.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into continents (name) values (?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection connection = Database.getConnection();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select id from continents where name='" + name + "'")) {
            return resultSet.next() ? resultSet.getInt(1) : null;
        }
    }

    public String findById(int id)  throws SQLException {
        Connection connection = Database.getConnection();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select id from continent where id='" + id + "'")) {
            return resultSet.next() ? resultSet.getString(1) : null;
        }
    }
}
