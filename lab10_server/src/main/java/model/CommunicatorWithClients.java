package model;

import commands.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CommunicatorWithClients extends Thread{
    public Socket socket = null ;
    private LoginCommand loginCommand = new LoginCommand();
    private RegisterCommand registerCommand = new RegisterCommand();
    private FriendCommand friendCommand = new FriendCommand();
    private WrongCommand wrongCommand = new WrongCommand();
    private UserInfo loggedInUser = null;
    private SendCommand sendCommand = new SendCommand();
    private ReadCommand readCommand = new ReadCommand();
    private StopCommand stopCommand = new StopCommand();
    private TimeOut timer = new TimeOut(this, 120);

    public Boolean exceptionWasThrown = false;
    public String command = "";
    public Boolean clientExited = false;

    public CommunicatorWithClients (Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream;

            timer.start();

            while(!command.equals("stop")) {
                if(clientExited) {
                    break;
                }
                try {
                    command = inputStream.readUTF();
                }
                catch (IOException e) {
                    System.out.println("This socket is not active.");
                    socket.close();
                    exceptionWasThrown = true;
                }

                if(!clientExited)
                    System.out.println("The command received was: " + command);
                timer.reset();

                if(!exceptionWasThrown) {
                    try {
                        outputStream = new DataOutputStream(socket.getOutputStream());
                        executeCommand(command);
                        if(command.equals("exit")) {
                            clientExited = true;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopServer(){
        stopCommand.execute(socket);
    }

    private void executeCommand(String command) {
        String firstWord = command.split(" ")[0];

        switch(firstWord) {
            case "exit":
                try {
                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                    outputStream.writeUTF("This client will now stop.");
                    socket.close();
                    //System.exit(0);
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
                executeRead();
                break;
            case "send":
                executeSend(command);
                break;
            case "stop":
                stopServer();
                break;
            default:
                executeWrongCommand();
                break;
        }
    }

    private void executeRead() {
        readCommand.execute(loggedInUser, socket);
    }

    private void executeSend(String command) {
        sendCommand.execute(loggedInUser, socket, command);
    }

    private void executeWrongCommand() {
        wrongCommand.execute(socket);
    }

    private void executeFriendCommand(String command) {
        friendCommand.execute(command, socket, loggedInUser);
    }

    private void executeLogin(String nickname) {
        UserInfo currentUser = loginCommand.execute(nickname, socket);
        loggedInUser = currentUser;
    }

    private void executeRegister(String nickname) {
        registerCommand.execute(nickname, socket);
    }

//    public void setClientExited(Boolean exited){
//        this.clientExited = exited;
//    }
//    public void setExceptionWasThrown(Boolean exception){
//        this.exceptionWasThrown = exception;
//    }
}
