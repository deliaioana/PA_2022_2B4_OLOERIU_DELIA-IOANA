import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class CommandHandler {
    private Client client;


    void run(Client client, DataOutputStream outputStream,
             DataInputStream inputStream) throws IOException {
        setClient(client);
        Scanner inputScanner = new Scanner(System.in);
        while(client.getStillRunning()) {
            System.out.println("Enter next command:");

            String command = inputScanner.nextLine();
            System.out.println("Your entered command is: " + command);

            if(command.equals("exit")) {
                sendCommandToServer(command, outputStream);
                getFeedbackFromServer(inputStream);

                inputScanner.close();
                client.stop();
            }
            else {
                sendCommandToServer(command, outputStream);
                getFeedbackFromServer(inputStream);
            }
        }
    }

    private boolean sendCommandToServer(String command, DataOutputStream outputStream){
        System.out.println("Sending command to server.");
        try {
            outputStream.writeUTF(command);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("The server is not running anymore.");
            return false;
        }
        return true;
    }

    private void  getFeedbackFromServer(DataInputStream inputStream) {
        String feedback = "";
        try {
            feedback = inputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server feedback: " + feedback);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
