package app;

import com.DisplayLocales;
import com.Info;
import com.OtherCommands;
import com.SetLocale;

import javax.print.attribute.standard.MediaSize;
import java.util.Scanner;

public class CommandHandler {
    private boolean stillRunning = true;
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        OtherCommands.execute("help");

        while(stillRunning) {
            OtherCommands.execute("prompt");
            String command = scanner.next();

            switch (command) {
                case "stop" -> {
                    System.out.println("The application will stop.");
                    stillRunning = false;
                }
                case "display" -> DisplayLocales.displayAll();
                case "set" -> SetLocale.setCurrent();
                case "info" -> Info.getInfo();
                case "current" -> OtherCommands.execute("current");
                case "help" -> OtherCommands.execute("help");
                default -> OtherCommands.execute("invalid");
            }
        }
        scanner.close();
    }

}
