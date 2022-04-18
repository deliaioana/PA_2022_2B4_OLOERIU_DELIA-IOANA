package model;
import usefulComponents.PlayerIDGenerator;

import java.util.concurrent.CopyOnWriteArrayList;

public class Game {
    private CopyOnWriteArrayList<Player> players = new CopyOnWriteArrayList<>();
    private Bag bag = new Bag();
    private Board board;
    private Player winner = new Player();

    public Game(int numberOfPlayers){
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

        TimerThread timerThread = new TimerThread();
        timerThread.setDaemon(true);
        timerThread.start();

        for (Player player : players ) {
            new Thread(player).start();
        }
        while(this.isStillGoing()){

        }
        stopAllPlayerThreads();
        //stopDaemonThread(timerThread);

        board.printAllInfoWordsAndScores();
        System.out.println("Game is finished!");
        winner = board.getWinner();
        int winnerPoints = board.getWinnerPoints();
        System.out.println("The winner is: " + winner);
        System.out.println("With a total of: " + winnerPoints);
    }

    private void stopDaemonThread(TimerThread timerThread) {
        timerThread.stopThread();
    }

    private void stopAllPlayerThreads() {
        for (Player player : players) {
            player.stopPlaying();
        }
    }

    private boolean isStillGoing() {
        if(bag.getTiles().isEmpty()){
            for (Player player: players) {
                if(player.isActive())
                    return true;
            }
            return false;
        }
        return true;
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
