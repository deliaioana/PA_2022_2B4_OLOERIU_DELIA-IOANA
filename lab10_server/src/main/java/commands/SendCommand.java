package commands;

import model.Server;
import model.UserInfo;

import java.net.Socket;

public class SendCommand {
    public synchronized void execute(UserInfo currentUser, Socket socket, String message){
        for (UserInfo user : Server.getUsers()) {
            if(currentUser.getFriendsIDs().contains(user.getID())) {
                user.addMessage(message);
            }
        }
        MessageSender.send("Message successfully sent.", socket);
    }
}
