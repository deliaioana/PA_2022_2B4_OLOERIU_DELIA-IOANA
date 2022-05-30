package com;

import java.util.Locale;
import java.util.ResourceBundle;

public class DisplayLocales {
    static public void displayAll() {
        String baseName = "res.Messages_en";
        ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName, Locale.getDefault());

        System.out.println(resourceBundle.getString("locales"));
    }
}
