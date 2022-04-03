import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int n = 0, p = 0, m = 100, BOUND = 200;
        long startTime = System.nanoTime();

        //validation
        if (args.length <= 2)
            System.err.println("Too few arguments!");
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException numberException) {
            System.err.println("First argument is not a valid number");
            System.exit(0);
        }

        try {
            p = Integer.parseInt(args[1]);
        } catch (NumberFormatException numberException) {
            System.err.println("Second argument is not a valid number");
            System.exit(1);
        }

        char[] alphabet = new char[m];
        for (int i = 2; i < args.length; ++i) {
            if (!((args[i].charAt(0) >= 'A' && args[i].charAt(0) <= 'Z') || (args[i].charAt(0) >= 'a' && args[i].charAt(0) <= 'z'))
                    || args[i].length() > 1) {
                System.err.println("Argument on position " + (i + 1) + " is not an accepted letter");
                System.exit(2);
            }
            alphabet[i - 2] = args[i].charAt(0);
        }

        //n random words
        String[] words = new String[n];
        for (int i = 0; i < n; ++i)
            words[i] = getRandomWordFromAlphabet(alphabet, p, args.length - 2);
        if (n < BOUND)
            System.out.println("Generated array of random words: " + Arrays.toString(words));

        //adjacency matrix
        boolean[][] adjacency = new boolean[n][n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                adjacency[i][j] = false;

        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                adjacency[i][j] = areNeighbors(i, j, p, words);

        /* // print adj matrix
        if (n < BOUND) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j)
                    System.out.print((adjacency[i][j]) + " ");
                System.out.println('\n');
            }
        }
        */

            //neighbors array
            ArrayList<ArrayList<String>> neighbors = new ArrayList<>(n);
            for (int i = 0; i < n; ++i) {
                ArrayList<String> nb = new ArrayList<>();
                for (int j = 0; j < n; ++j) {
                    if (adjacency[i][j])
                        nb.add(words[j]);
                }
                neighbors.add(nb);

            }

            //running time in nanoseconds for a bigger n
            if (n < BOUND)
                System.out.println(neighbors);
            else{
                long endTime = System.nanoTime();
                long timeDifference = endTime - startTime;
                System.out.println("Running time of the application in nanoseconds: " + timeDifference);
            }
    }

    private static boolean areNeighbors ( int x, int y, int length, String[] words){
        for (int i = 0; i < length; ++i)
            if (words[x].contains(String.valueOf(words[y].charAt(i))))
                return true;
        return false;
    }

    private static String getRandomWordFromAlphabet ( char[] alphabet, int length, int alphabetSize){
        StringBuilder s = new StringBuilder();
        for (int j = 0; j < length; ++j) {
            char c = alphabet[(int) (Math.random() * (alphabetSize - 1))];
            s.append(c);
        }
        return s.toString();
    }
}
