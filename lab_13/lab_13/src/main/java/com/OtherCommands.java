package com;

import java.util.Locale;
import java.util.ResourceBundle;

public class OtherCommands {
    public static void execute(String command) {
        String baseName = "res.Messages_en";
        ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName, Locale.getDefault());

        switch (command) {
            case "prompt" -> System.out.println(resourceBundle.getString("prompt"));
            case "help" -> System.out.println(resourceBundle.getString("help"));
            case "current" -> System.out.println(resourceBundle.getString("locale.set"));
            default -> System.out.println(resourceBundle.getString("invalid"));
        }
    }
}
