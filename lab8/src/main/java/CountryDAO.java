import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class CountryDAO {

    public void create(String name, int continentID) throws SQLException {
        Connection connection = Database.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into countries (name) values (?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection connection = Database.getConnection();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select id from countries where name='" + name + "'")) {
            return resultSet.next() ? resultSet.getInt(1) : null;
        }
    }

    public String findById(int id) throws SQLException {
        Connection connection = Database.getConnection();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select id from countries where id='" + id + "'")) {
            return resultSet.next() ? resultSet.getString(1) : null;
        }
    }
}
