import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game {
    private CopyOnWriteArrayList<Player> players = new CopyOnWriteArrayList<>();
    private Bag bag = new Bag();
    private Board board;

    Game(int numberOfPlayers){
        PlayerIDGenerator playerIDGenerator = new PlayerIDGenerator();
        for(int i=0; i<numberOfPlayers; ++i){
            Player player = new Player(playerIDGenerator.getNewID(), this);
            players.add(player);
        }
        board = new Board(players);
        this.start();
    }

    public void start() {
        System.out.println("Game is starting!");
        for (Player player : players ) {
            new Thread(player).start();
        }
        while(this.isStillGoing()){

        }
        stopAllPlayerThreads();
        //print rankings
        System.out.println("Game is finished!");
    }

    private void stopAllPlayerThreads() {
        for (Player player : players) {
            player.stopPlaying();
        }
    }

    private boolean isStillGoing() {
        return !bag.getTiles().isEmpty();
    }

    public CopyOnWriteArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(CopyOnWriteArrayList<Player> players) {
        this.players = players;
    }

    public Bag getBag() {
        return bag;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
