package graphClasses;

import java.io.Serializable;
import java.util.ArrayList;

public class Node implements Serializable {
    private int id;
    private int row;
    private int col;
    private boolean used = false;
    private ArrayList<Node> connections = new ArrayList<>();
    private boolean player;
    private ArrayList<Integer> neighboursIDs = new ArrayList<Integer>();

    public Node(int row, int col, int ID) {
        this.setCol(col);
        this.setRow(row);
        this.setID(ID);
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
        neighboursIDs.add(node1.id);
    }

    public Boolean isNeighbourWith(Node node){
        return (connections.contains(node));
    }

    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{\n" + id +
                "\n" + row +
                "\n" + col +
                "\n" + used +
                "\n" + player +
                "\n" + neighboursIDsToString() +
                "}\n";
    }

    private String neighboursIDsToString() {
        StringBuilder info = new StringBuilder("");
        info.append("[\n");
        for (Integer id : neighboursIDs) {
            info.append(id);
            info.append("\n");
        }
        info.append("]\n");
        return info.toString();
    }

    public ArrayList<Integer> getNeighboursIDs() {
        return neighboursIDs;
    }

    public void setNeighboursIDs(ArrayList<Integer> neighboursIDs) {
        this.neighboursIDs = neighboursIDs;
    }

    public boolean isInList(Integer id) {
        if(this.id == 26) {
            boolean d = true;
        }
        for (Node node : connections) {
            if(node.getId() == id)
                return true;
        }
        return false;
    }
}
