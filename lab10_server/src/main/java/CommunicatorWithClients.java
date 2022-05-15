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

                try {
                    outputStream = new DataOutputStream(socket.getOutputStream());
                    outputStream.writeUTF("Server received the request: " + command);
                    executeCommand(command);
                } catch (IOException e) {
                    e.printStackTrace();
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

    private void executeCommand(String command) {
        String firstWord = command.split(" ")[0];

        switch(firstWord) {
            case "exit":
                try {
                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                    outputStream.writeUTF("This client will now stop.");
                    //executeCommand(command);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "register":
                executeRegister(command.split(" ")[1]);
                break;
            case "friend":
                executeFriendCommand(command);
                break;
            case "login":
                executeLogin(command.split(" ")[1]);
                break;
            case "read":
                //executeRead();
                break;
            case "send":
                executeSend(command);
                break;


        }
    }

    private void executeSend(String command) {
        System.out.println("The message will now be sent.");
    }

    private void executeFriendCommand(String command) {
        System.out.println("The users will now be added as friends.");
    }

    private void executeLogin(String s) {
        System.out.println("The user will now be logged in.");
    }

    private void executeRegister(String s) {
        System.out.println("The user will be registered.");
    }
}
