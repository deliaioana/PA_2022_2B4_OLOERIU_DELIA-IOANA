package model;

import com.github.javafaker.Faker;
import usefulComponents.Word;
import java.util.HashMap;
import java.util.Map;

public class Player implements Runnable{
    private Map<Tile, Integer> tiles = new HashMap<>();
    private int id;
    private boolean stillPlaying;
    private String name;
    private Game game;
    private boolean isActive;

    Player(int id, Game game){
        this.setId(id);
        this.setGame(game);
        Faker faker = new Faker();
        this.setName(faker.funnyName().name());
    }

    public Player() {

    }

    public Map<Tile, Integer> getTiles() {
        return tiles;
    }

    public void setTiles(Map<Tile, Integer> tiles) {
        this.tiles = tiles;
    }

    public synchronized void submitWord(Word word, Game game){
       game.getBoard().submitWordFromPlayer(word, this);
       discardAllTiles();
    }

    public synchronized void requestMissingTiles(Game game){
        if(game.getBag().getTiles().isEmpty()){
            this.setTiles(null);
        }
        else{
            int numberOfRequestedTiles = 7 - tiles.size();
            for(int i = 0; i < numberOfRequestedTiles; ++i){
                Tile tile = game.getBag().extractTile();

                if(tile == null) {
                    i = numberOfRequestedTiles + 1; //exiting the loop
                }
                else {
                    if(!this.tiles.containsKey(tile)){
                        tiles.put(tile, 1);
                    }
                    else {
                        int currentNumberOfSpecificTile = tiles.get(tile);
                        tiles.put(tile, currentNumberOfSpecificTile + 1);
                    }
                }

            }
        }


    }

    public void discardAllTiles(){
        tiles.clear();
    }

    @Override
    public void run() {
        System.out.println("Player " + name + "(" + id + ") starts playing");
        stillPlaying = true;
        isActive = true;
        requestMissingTiles(game);
        while(stillPlaying){
            if(tiles != null){
                Word word = new Word(tiles);
                if(word != null){
                    if(word.getContainedTiles() != null)
                        submitWord(word, game);
                }
            }

            requestMissingTiles(game);
            if(tiles == null){
                stillPlaying = false;
            }
        }
        isActive = false;
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

    @Override
    public String toString() {
        return name + ", id:" + id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
