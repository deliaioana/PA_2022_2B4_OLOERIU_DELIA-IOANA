import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {
    private int row;
    private int col;
    private boolean used = false;
    private ArrayList<Node> connections = new ArrayList<>();
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

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public void setConnections(ArrayList<Node> newConnections){
        connections.clear();
        connections.addAll(newConnections);
    }

    public ArrayList<Node> getConnections(){
        return connections;
    }

    public void addNeighbour(Node node1) {
        connections.add(node1);
    }

    public Boolean isNeighbourWith(Node node){
        return (connections.contains(node));
    }
}
