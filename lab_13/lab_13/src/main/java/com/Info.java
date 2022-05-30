package com;

import java.util.Locale;
import java.util.ResourceBundle;

public class Info {
    public static void getInfo() {
        String baseName = "res.Messages_en";
        ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName, Locale.getDefault());

        System.out.println(resourceBundle.getString("info"));
        System.out.println("The country is: " + resourceBundle.getString("Country"));
        System.out.println("The language is: " + resourceBundle.getString("Language"));
        System.out.println("The currency is: " + resourceBundle.getString("Currency"));
        System.out.println("The weekdays are: " + resourceBundle.getString("Weekdays"));
        System.out.println("The months are: " + resourceBundle.getString("Months"));
        System.out.println("Today is: " + resourceBundle.getString("Today"));
    }
}
