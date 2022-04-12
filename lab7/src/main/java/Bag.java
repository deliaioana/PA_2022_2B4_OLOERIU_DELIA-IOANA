import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Bag {
    private ConcurrentHashMap<Tile, Integer> tiles;
    private Map<Character, Integer> possibleTiles;

    Bag(){
        tiles = new ConcurrentHashMap();
        possibleTiles = new HashMap();
    }

    public ConcurrentHashMap getTiles() {
        return tiles;
    }

    public void setTiles(ConcurrentHashMap tiles) {
        this.tiles = tiles;
    }

    public void initializeTiles() throws FileNotFoundException {
        String fileName = new String("LettersAndPoints.txt");
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        String line;
        line = scanner.nextLine(); //irrelevant first line

        parseTilePoints(scanner);

        parseNumberOfTiles(scanner);
        scanner.close();
    }

    private void parseTilePoints(Scanner scanner) {
        String line;
        line = scanner.nextLine();
        while(!line.equals("Number of letters in a bag:")) {
            int points;
            points = Integer.parseInt(line);
            line = scanner.nextLine(); //skip {
            line = scanner.nextLine();
            while(!line.equals("}")){
                Character letter;
                letter = line.charAt(0);
                possibleTiles.put(letter, points);
                line = scanner.nextLine();
            }
            line = scanner.nextLine();
        }
    }

    private void parseNumberOfTiles(Scanner scanner) {
        String line;
        line = scanner.nextLine();
        while(line != null){
            Character letter;
            letter = line.charAt(0);
            line = scanner.nextLine();
            int numberOfTiles;
            numberOfTiles = Integer.parseInt(line);

            Tile tile = new Tile(letter, possibleTiles.get(letter));
            tiles.put(tile, numberOfTiles);
        }
    }
}
