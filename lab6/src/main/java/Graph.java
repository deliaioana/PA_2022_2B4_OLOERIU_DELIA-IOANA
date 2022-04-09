import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Graph {
    private List<Node> nodes = new ArrayList();
    private final Node nullNode = null;
    private Node lastNode = null;

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void addNode(Node node){
        nodes.add(node);
    }

    public void clearGraph(){
        nodes.clear();
    }

    public Node getNodeByRowCol(int row, int col) {
        for(Node node : nodes){
            if(node.getRow() == row && node.getCol() == col)
                return node;
        }
        return nullNode;
    }

    public void printGraphInConsole(){
        for (Node node : nodes) {
            System.out.println("ROW:" + node.getRow() + ", COL:" + node.getCol());
            System.out.println("adiacente: ");
            for (Node node2 : node.getConnections()) {
                System.out.print("r:" + node2.getRow() + " c:" + node2.getCol() + "; ");
            }
        }
    }

    public void setLastNode(Node node) {
        lastNode = node;
    }

    public Node getLastNode() {
        return lastNode;
    }
}
