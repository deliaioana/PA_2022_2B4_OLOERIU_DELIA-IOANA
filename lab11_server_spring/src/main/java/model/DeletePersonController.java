package model;

import org.springframework.web.bind.annotation.*;
import usefulClasses.Database;
import java.sql.Connection;

@RestController
@RequestMapping("/test")
public class DeletePersonController {

    @PostMapping("/newPerson")
    public void newPerson() {
        Connection connection = Database.getConnection();
        //delete person code
    }
}