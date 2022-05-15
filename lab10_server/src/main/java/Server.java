import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket socket;
    private ServerSocket server;
    private DataOutputStream outputStream;
    private static boolean serverRunning = true;
    private String fileName = "networkData.txt";
    private File file = null;

    public static void stopServer(){
        serverRunning = false;
        System.out.println("The server stopped.");

    }

    Server(int portNumber) {
        createOrLoadFile();

        try {
            server = new ServerSocket(portNumber);
            System.out.println("Waiting for a client to connect ...");
            while(serverRunning) {
                socket = server.accept();
                System.out.println("A client connected.");
                new CommunicatorWithClients(socket).start();
            }
            try {
                outputStream = new DataOutputStream(socket.getOutputStream());
                outputStream.writeUTF("Server stopped");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createOrLoadFile() {
        try {
            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
