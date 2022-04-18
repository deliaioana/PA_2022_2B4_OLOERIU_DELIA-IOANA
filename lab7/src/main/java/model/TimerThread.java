package model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class TimerThread extends Thread {
    private boolean isActive;
    private int currentHour;
    private int currentMinute;

    TimerThread(){

    }

    private void displayTime(){
        System.out.println("Current time: " + currentHour + ":" + currentMinute);
    }

    @Override
    public void run() {

        System.out.println("LocalDate.now()");

        setActive(true);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        System.out.println(dateTimeFormatter.format(localDate));

        System.out.println("------------------------------------------CURRENT TIME: " + currentHour + ":" + currentMinute);


        while(isActive){
            //String time = formatter.format(date);
            //computeCurrentTime(time);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                printCurrentTime();
            }
        }
        setActive(false);
    }

    private void printCurrentTime() {
        System.out.println("TIMER: " + getCurrentHour() + ":" + getCurrentMinute());
    }

    private void computeCurrentTime(String time) {
        computeHour(time);
        computeMinute(time);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void stopThread(){
        setActive(false);
    }

    public int getCurrentHour() {
        return currentHour;
    }

    public void setCurrentHour(int currentHour) {
        this.currentHour = currentHour;
    }

    public int getCurrentMinute() {
        return currentMinute;
    }

    public void setCurrentMinute(int currentMinute) {
        this.currentMinute = currentMinute;
    }

    private void computeHour(String time){
        //compute and set
    }

    private void computeMinute(String time){

    }
}
