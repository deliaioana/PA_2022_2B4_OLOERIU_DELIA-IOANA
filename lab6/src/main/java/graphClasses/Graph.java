package graphClasses;

import graphClasses.Node;

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
        System.out.println("GRAFUL ESTE: ");
        for (Node node : nodes) {
            System.out.println("\nROW:" + node.getRow() + ", COL:" + node.getCol());
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

    public void saveToFile(String fileName, FileWriter fileWriter) throws IOException {
        fileWriter.write(lastNode.toString());
        for (Node node : getNodes()){
            fileWriter.write(node.toString());
        }
        fileWriter.write("\n\n");
        fileWriter.close();
    }

    public void getFromFile(File file, String fileName, Scanner scanner) throws FileNotFoundException {
        clearGraph();
        String line;

        line = scanner.nextLine(); //empty line {
        if(lastNode == null)
            lastNode = new Node();
        parseNode(file, fileName, scanner, lastNode, line);

        line = scanner.nextLine();
        while(!line.isEmpty()){
            Node node = new Node();
            parseNode(file, fileName, scanner, node, line);
            nodes.add(node);
            line = scanner.nextLine(); //skip over {
        }

        restoreConnectionsFromIDs();
        scanner.close();
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
                if(!node.isInList(id))
                    node.getConnections().add(findNodeByID(id));
                if(!findNodeByID(id).isInList(node.getId()))
                    findNodeByID(id).getConnections().add(node);
            }
        }
        lastNode = findNodeByID(lastNode.getId());
    }

    private Node findNodeByID(Integer id) {
        for(Node node : nodes){
            if(node.getId() == id)
                return node;
        }
        return nullNode;
    }
}
