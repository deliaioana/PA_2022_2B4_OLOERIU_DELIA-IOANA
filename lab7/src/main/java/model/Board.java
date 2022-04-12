package model;

import usefulComponents.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Board {
    private CopyOnWriteArrayList<Player> players;
    private ConcurrentHashMap<Player, Integer> scores = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Player, List<Word>> playerWords = new ConcurrentHashMap<>();

    Board(CopyOnWriteArrayList<Player> players){
        this.setPlayers(players);
        resetScores();
    }

    private void resetScores(){
        for (Player player : scores.keySet()) {
            scores.put(player, 0);
        }
    }

    public CopyOnWriteArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(CopyOnWriteArrayList<Player> players) {
        this.players = players;
        for (Player player : players) {
            scores.put(player, 0);
        }
    }

    public int computeWordScore(Word word){
        int score = 0;
        for (Tile tile : word.getContainedTiles().keySet()) {
            score += tile.getPoints() * word.getContainedTiles().get(tile);
        }
        return score;
    }

    public void updatePlayerScore(Player player, int wordScore){
        int currentScore = scores.get(player);
        int updatedScore = currentScore + wordScore;
        scores.put(player, updatedScore);
    }

    public ConcurrentHashMap<Player, List<Word>> getPlayerWords() {
        return playerWords;
    }

    public void printAllInfoWordsAndScores(){
        for (Player player : players) {
            System.out.println("\n\nPlayer " + player.getName() + "(" + player.getId() + ") got: " + scores.get(player) + ".");
            System.out.println("Their words were: ");
            for (Word word : playerWords.get(player)) {
                System.out.print(word + " ");
            }
        }
        System.out.print("\n\n");
    }

    public void submitWordFromPlayer(Word word, Player player) {
        System.out.println("Player " + player.getId() + " submitted this word: " + word);

        if(getPlayerWords().containsKey(player)){
            List<Word> words = new ArrayList<>(getPlayerWords().get(player));
            words.add(word);
            getPlayerWords().put(player, words);
        }
        else {
            getPlayerWords().put(player, new ArrayList<>());
            getPlayerWords().get(player).add(word);
        }

        System.out.println(player.getId() + "playerWords: " + getPlayerWords().get(player));

        int wordScore = computeWordScore(word);
        updatePlayerScore(player, wordScore);
    }
}
