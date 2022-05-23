package model;

import testClasses.*;
import testClasses.ClassInterface;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String commandName = scanner.next();
            if (commandName.equalsIgnoreCase("exit")) {
                break;
            }
            String[] params = scanner.nextLine().trim().split("\\s+");
            try {
                // The command name is actually the class name
                Class className = Class.forName(commandName);
                ClassInterface command = (ClassInterface) className.newInstance();
                command.execute(params);
            } catch (ClassNotFoundException | InstantiationException |
                     IllegalAccessException | IOException e) {
                System.err.println(e);
            }
        }
    }
}
