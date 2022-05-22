import java.io.IOException;
import java.net.Socket;

public class StopCommand {
    public synchronized void execute(Socket socket) {
        try{
            Server.stopServer();
            socket.close();
            System.exit(0);
        }
        catch (IOException e){
            System.out.println("There was a problem.");
        }
    }
}
