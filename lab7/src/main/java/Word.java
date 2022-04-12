import java.util.Map;

public class Word {
    private Map<Tile, Integer> containedTiles;
    private String word;

    Word(Map<Tile, Integer> tiles){
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

    public void setContainedTiles(Map<Tile, Integer> containedTiles) {
        this.containedTiles = containedTiles;
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
