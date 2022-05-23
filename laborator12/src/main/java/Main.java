import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Class className = null;
        while (true) {
            String commandName = scanner.next();
            if (commandName.equalsIgnoreCase("exit")) {
                break;
            }
            String[] params = scanner.nextLine().trim().split("\\s+");
            try {
                URL url;
                url = new URL("file:///D:\\1_SCOALA\\Anul_2_Semestrul_2\\PA\\PA_2022_2B4_OLOERIU_DELIA-IOANA\\laborator12");
                URL[] urlVector = {url};
                URLClassLoader urlLoader = new URLClassLoader(urlVector);
                className = urlLoader.loadClass(commandName);
            } catch (ClassNotFoundException | IOException e) {
                System.err.println(e);
            }

            ClassHandler classHandler = new ClassHandler(className);
            classHandler.printMethods();
        }
    }
}
//Person a 2 b c d e