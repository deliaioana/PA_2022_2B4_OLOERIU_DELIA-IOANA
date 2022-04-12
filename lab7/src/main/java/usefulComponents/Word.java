package usefulComponents;

import model.Tile;
import java.util.HashMap;
import java.util.Map;

public class Word {
    private Map<Tile, Integer> containedTiles;
    private String word;

    public Word(Map<Tile, Integer> tiles){
        this.setContainedTiles(tiles);
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Map<Tile, Integer> getContainedTiles() {
        return containedTiles;
    }

    public void setContainedTiles(Map<Tile, Integer> tiles) {
        containedTiles = new HashMap<>();
        for (Tile tile : tiles.keySet()) {
            Tile wordTile = new Tile();

            wordTile.copyTile(tile);

            if(containedTiles.keySet() != null) {
                if(!containedTiles.containsKey(wordTile)){
                    containedTiles.put(wordTile, 1);
                }
                else{
                    int currentNumberOfThisTile = containedTiles.get(wordTile);
                    containedTiles.put(wordTile, currentNumberOfThisTile + 1);
                }
            }
            else {
                containedTiles.put(wordTile, 1);
            }


        };
    }

    @Override
    public String toString() {
        StringBuilder word = new StringBuilder();
        for (Tile tile : containedTiles.keySet()) {
            word.append(String.valueOf(tile.getLetter()).repeat(Math.max(0, containedTiles.get(tile))));
        }
        return word.toString();
    }
}
