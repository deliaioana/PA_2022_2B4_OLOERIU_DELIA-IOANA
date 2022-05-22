import java.net.Socket;

public class WrongCommand {
    public synchronized void execute(Socket socket){
        MessageSender.send("Incorrect command. Try typing:\n - login <username>" +
                "\n - register <username>" +
                "\n - send <message>" +
                "\n - read" +
                "\n - stop" +
                "\n - exit", socket);
    }
}
