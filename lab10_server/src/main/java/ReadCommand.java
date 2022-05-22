import java.net.Socket;

public class ReadCommand {
    public synchronized void execute(UserInfo currentUser, Socket socket) {
        if(currentUser.getMessages().isEmpty()) {
            MessageSender.send("You have no messages.", socket);
        }
        else {
            MessageSender.send("Your messages are:\n" + currentUser.getMessages().toString(), socket);
        }
    }
}
