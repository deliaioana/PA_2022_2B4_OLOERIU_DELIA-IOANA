package model;

import model.Tile;

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
        try {
            this.initializeTiles();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public synchronized Tile extractTile(){
            Set<Tile> keySet = tiles.keySet();
            List<Tile> keyList = new ArrayList<>(keySet);

            if(keyList.size() == 0)
                return null;

            int size = keyList.size();
            int randomIndex = new Random().nextInt(size);

            Tile randomKey = keyList.get(randomIndex);
            Integer numberOfTiles = tiles.get(randomKey);

            tiles.put(randomKey, numberOfTiles-1);
            if(numberOfTiles == 1)
                tiles.remove(randomKey);

            return randomKey;
    }

    public ConcurrentHashMap getTiles() {
        return tiles;
    }

    public void setTiles(ConcurrentHashMap tiles) {
        this.tiles = tiles;
    }

    public void initializeTiles() throws FileNotFoundException {
        String fileName = new String("src/main/resources/LettersAndPoints.txt");
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
        while(!line.equals("END")){
            Character letter;
            letter = line.charAt(0);
            line = scanner.nextLine();
            int numberOfTiles;
            numberOfTiles = Integer.parseInt(line);

            Tile tile = new Tile(letter, possibleTiles.get(letter));
            tiles.put(tile, numberOfTiles);

            line = scanner.nextLine();
        }
    }
}
