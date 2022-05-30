package app;

import com.DisplayLocales;
import com.Info;
import com.MessageHandler;
import com.SetLocale;

import java.util.Scanner;

public class CommandHandler {
    private boolean stillRunning = true;
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        printCommands();
        while(stillRunning) {
            String command = scanner.next();
            switch (command) {
                case "stop":
                    System.out.println("The application will stop.");
                    stillRunning = false;
                    break;
                case "display":
                    DisplayLocales.displayAll();
                    break;
                case "set":
                    SetLocale.setCurrent();
                    break;
                case "info":
                    Info.getInfo();
                    break;
                case "using":
                    MessageHandler.execute(command);
                    break;
                case "help":
                    printCommands();
                    break;
                default:
                    MessageHandler.execute("invalid");
                    break;
            }
        }
    }

    private void printCommands() {
        System.out.println("\n--------------------------------------------");
        System.out.println("You can use the following commands:\n");
        System.out.println(" - set <locale> (to change the current locale)");
        System.out.println(" - display (to display all locales)");
        System.out.println(" - info (to get info about the current locale)");
        System.out.println(" - current (to see the current locale used)");
        System.out.println(" - help (to print possible commands)");
        System.out.println(" - stop (to stop the program)");
        System.out.println("--------------------------------------------\n");
    }
}
