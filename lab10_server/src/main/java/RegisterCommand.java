import java.net.Socket;

public class RegisterCommand {
    public synchronized void execute(String nickname, Socket socket){
        for (UserInfo user : Server.getUsers()) {
            if(user.getName().equals(nickname)) {
                MessageSender.send("This username is already taken.", socket);
            }
        }
        UserInfo newUser = new UserInfo(nickname);
        Server.getUsers().add(newUser);
        MessageSender.send("Registered with success.", socket);
    }
}
