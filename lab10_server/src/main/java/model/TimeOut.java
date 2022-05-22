package model;

import model.CommunicatorWithClients;

import java.io.IOException;

public class TimeOut extends Thread {

    private CommunicatorWithClients communicatorWithClients = null;
    private int timer;
    private int currentTime;


    public TimeOut(CommunicatorWithClients communicatorWithClients, int maxTime) {
        this.communicatorWithClients = communicatorWithClients;
        timer = maxTime;
        currentTime = 0;
    }

    public void reset() {
        currentTime = 0;
        //System.out.println("reset");
    }

    public void run() {
        while (currentTime < timer) {
            try {
                Thread.sleep(1000);
                currentTime++;
                //System.out.println(currentTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        System.out.println("Time exceeded ... The client will be disconnected");
        communicatorWithClients.clientExited = true;
        communicatorWithClients.exceptionWasThrown = true;
        try {
            communicatorWithClients.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int getCurrentTime() {
        return currentTime;
    }
}