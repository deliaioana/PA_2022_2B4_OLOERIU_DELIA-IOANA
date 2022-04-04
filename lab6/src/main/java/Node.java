import java.util.HashMap;
import java.util.Map;

public class Node {
    private int row;
    private int col;
    private boolean used = false;
    private Map<Node, String> connections = new HashMap<>();
    private boolean player;

    public Node(int row, int col) {
        this.setCol(col);
        this.setRow(row);
    }

    public Node(){

    }

    public boolean getPlayer(){
        return player;
    }

    public void setPlayer(boolean player){
        this.player = player;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Map<Node, String> getConnections() {
        return connections;
    }

    public void setConnections(Map<Node, String> connections) {
        this.connections = connections;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
