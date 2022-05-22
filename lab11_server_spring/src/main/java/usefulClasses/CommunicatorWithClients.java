import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CommunicatorWithClients extends Thread{
    private Socket socket = null ;
    public CommunicatorWithClients (Socket socket) { this.socket = socket ; }

    public void run() {
        try {
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream;

            String command = "";

            while(!command.equals("stop")) {
                command = inputStream.readUTF();
                System.out.println("The command received was: " + command);

                if(!socket.isClosed()){
                    try {
                        outputStream = new DataOutputStream(socket.getOutputStream());
                        outputStream.writeUTF("Server received the request: " + command);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    System.out.println("Client quited.");
                }

            }


            Server.stopServer();
            inputStream.close();
            socket.close();
            System.exit(0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
