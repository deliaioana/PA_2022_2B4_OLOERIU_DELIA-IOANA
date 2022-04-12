import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Board {
    private CopyOnWriteArrayList<Player> players;
    private ConcurrentHashMap<Player, Integer> scores = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Player, CopyOnWriteArrayList<Word>> playerWords = new ConcurrentHashMap<>();

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
    }

    public int computeWordScore(Word word){
        return 5;
    }

    public void updatePlayerScore(Player player, int wordScore){
        int currentScore = scores.get(player);
        int updatedScore = currentScore + wordScore;
        scores.put(player, updatedScore);
    }

    public ConcurrentHashMap<Player, CopyOnWriteArrayList<Word>> getPlayerWords() {
        return playerWords;
    }

    public void printAllInfoWordsAndScores(){
        for (Player player : players) {
            System.out.println("Player " + player.getName() + "(" + player.getId() + ") got: " + scores.get(player) + ".");
            System.out.println("Their words were: ");
            for (Word word : playerWords.get(player)) {
                System.out.print(word + ", ");
            }
        }
        System.out.print("\n");
    }

}
