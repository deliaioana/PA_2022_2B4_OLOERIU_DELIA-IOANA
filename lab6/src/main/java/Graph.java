import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Graph implements Serializable {
    private List<Node> nodes = new ArrayList();
    private final Node nullNode = null;
    private Node lastNode = nullNode;

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

    private void writeObject(java.io.ObjectOutputStream stream) throws IOException {
        stream.writeObject(lastNode);
        for (Node node : getNodes()){
            stream.writeObject(node);
        }
    }

    private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
        lastNode = (Node) stream.readObject();
        nodes = ((List) stream.readObject());
    }

    public void setLastNode(Node node) {
        lastNode = node;
    }

    public Node getLastNode() {
        return lastNode;
    }

    public void saveToFile(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(lastNode.toString());
        for (Node node : getNodes()){
            fileWriter.write(node.toString());
        }
        fileWriter.write("\n\n");
        fileWriter.close();
    }

    public void getFromFile(String fileName) throws FileNotFoundException {
        clearGraph();
        String line;
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        line = scanner.nextLine(); //empty line {
        //line = scanner.nextLine();
        parseNode(file, fileName, scanner, lastNode, line);

        line = scanner.nextLine();
        while(!line.isEmpty()){
            //line = scanner.nextLine();
            Node node = new Node();
            parseNode(file, fileName, scanner, node, line);
            nodes.add(node);
            line = scanner.nextLine(); //skip over {
        }

        restoreConnectionsFromIDs();
    }

    private void parseNode(File file, String fileName, Scanner scanner, Node node, String line) {
        line = scanner.nextLine();
        node.setID(Integer.parseInt(line));
        line = scanner.nextLine();
        node.setRow(Integer.parseInt(line));
        line = scanner.nextLine();
        node.setCol(Integer.parseInt(line));
        line = scanner.nextLine();
        node.setUsed(Boolean.parseBoolean(line));
        line = scanner.nextLine();
        node.setPlayer(Boolean.parseBoolean(line));

        line = scanner.nextLine(); //empty line
        ArrayList<Integer> neighbours = new ArrayList<>();

        if(line.compareTo("]")!=0)
            line = scanner.nextLine();

        while(line.compareTo("]") != 0){
            neighbours.add(Integer.parseInt(line));
            line = scanner.nextLine();
        }
        node.setNeighboursIDs(neighbours);
        line = scanner.nextLine(); //skip over }
    }

    public void restoreConnectionsFromIDs(){
        for (Node node : nodes) {
            for (Integer id : node.getNeighboursIDs()) {
                node.getConnections().add(findNodeByID(id));
                findNodeByID(id).getConnections().add(node);
            }
        }
    }

    private Node findNodeByID(Integer id) {
        for(Node node : nodes){
            if(node.getId() == id)
                return node;
        }
        return nullNode;
    }
}
