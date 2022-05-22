package model;

import org.springframework.web.bind.annotation.*;
import usefulClasses.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RestController
@RequestMapping("/test")
public class NewPersonController {

    @PostMapping("/newPerson")
    public String newPerson(@RequestParam(value = "id", defaultValue = "20") Integer id,
                          @RequestParam(value = "name", defaultValue = "person name") String name,
                          @RequestParam(value = "password", defaultValue = "p") String password,
                          @RequestParam(value = "messages", defaultValue = "message1") String messages,
                          @RequestParam(value = "friends_ids", defaultValue = "") String friends_ids,
                          @RequestParam(value = "is_online", defaultValue = "0") Integer is_online
                          ) {

        Connection connection = Database.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into social_network values " +
                        "(?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, messages);
            preparedStatement.setString(5, friends_ids);
            preparedStatement.setInt(6, is_online);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            return "failed";
        }
        return "success";
    }
}