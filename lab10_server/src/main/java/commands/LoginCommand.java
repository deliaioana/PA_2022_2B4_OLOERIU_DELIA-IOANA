package commands;

import commands.MessageSender;
import model.Server;
import model.UserInfo;

import java.net.Socket;

public class LoginCommand {
    public synchronized UserInfo execute(String nickname, Socket socket){
        for (UserInfo user : Server.getUsers()) {
            if(user.getName().equals(nickname)) {
                if(user.getLoggedIn()) {
                    MessageSender.send("Already logged in.", socket);
                }
                else {
                    user.setLoggedIn(true);
                    MessageSender.send("Successfully logged in.", socket);
                    return user;
                }
            }
        }
        MessageSender.send("Login failed.", socket);
        return null;
    }
}
