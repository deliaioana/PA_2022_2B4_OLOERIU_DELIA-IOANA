import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class Player implements Runnable{
    private Map<Tile, Integer> tiles = new HashMap<>();
    private int id;
    private boolean stillPlaying;
    private String name;
    private Game game;

    Player(int id, Game game){
        this.setId(id);
        this.setGame(game);
        Faker faker = new Faker();
        this.setName(faker.funnyName().name());
    }

    public Map<Tile, Integer> getTiles() {
        return tiles;
    }

    public void setTiles(Map<Tile, Integer> tiles) {
        this.tiles = tiles;
    }

    public void submitWord(Word word, Game game){
        System.out.println("Player " + id + "submitted this word: " + word);
        CopyOnWriteArrayList<Word> words = new CopyOnWriteArrayList<>();
        words = game.getBoard().getPlayerWords().get(this);
        words.add(word);
        game.getBoard().getPlayerWords().put(this, words);
    }

    public void requestMissingTiles(Game game){
        int numberOfRequestedTiles = 7 - tiles.size();
        for(int i = 0; i < numberOfRequestedTiles; ++i){
            //Math.random() * 1000 ;
        }
    }

    public void discardAllTiles(){
    }

    @Override
    public void run() {
        System.out.println("Player " + name + "(" + id + ") starts playing");
        stillPlaying = true;
        requestMissingTiles(game);
        while(stillPlaying){
            Word word = new Word(tiles);
            submitWord(word, game);
            requestMissingTiles(game);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void stopPlaying() {
        stillPlaying = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
