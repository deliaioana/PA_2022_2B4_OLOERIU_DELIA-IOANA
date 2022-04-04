import java.util.Map;

public class Word {
    private Map<Tile, Tile> containedTiles;
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Map<Tile, Tile> getContainedTiles() {
        return containedTiles;
    }

    public void setContainedTiles(Map<Tile, Tile> containedTiles) {
        this.containedTiles = containedTiles;
    }
}
