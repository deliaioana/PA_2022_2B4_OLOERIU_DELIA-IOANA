package model;

import nodes.Node;
import org.w3c.dom.ranges.DocumentRange;
import usefulClasses.Identifiable;
import usefulClasses.NodeComparatorByID;

import java.util.*;

public class Network {
    private String name;
    private List<Node> nodes;

    public Network(String name){
        this.setName(name);
        nodes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void addNode(Node n) {
        this.nodes.add(n);
        Collections.sort(nodes);
    }

    public void removeNode(Node n) {
        this.nodes.remove(n);
    }

    public Node getNodeByIndex(int index) {
        return this.nodes.get(index);
    }

    public StringBuilder getNetworkCostsToString(){
        StringBuilder costsString = new StringBuilder();
        for(Node currentNode : this.nodes){
            StringBuilder nodeCosts;
            nodeCosts = currentNode.getNodeCostsToString();
            costsString.append(nodeCosts).append('\n');
        }
        return costsString;
    }

    @Override
    public String toString() {
        return "Network{" +
                "name='" + name + "'\n" +
                "Nodes=" + nodes +
                "\n\nNetwork time costs:\n" + this.getNetworkCostsToString() +
                '}';
    }

    public void printIdentifiableNodes(){
        System.out.println("Identifiable nodes from '" + this.name + "':\n");
        List<Node> identifiable = new ArrayList<>();
        for(Node node : nodes){
            if(node instanceof Identifiable){
                identifiable.add(node);
            }
        }
        identifiable.sort(new NodeComparatorByID());

        for(Node node : identifiable){
            System.out.println(node.getName() + ", IP: " + ((Identifiable) node).getIPAddress());
        }
    }

    public int computeShortestPathBetween(Node node1, Node node2){
        Map<Node, Integer> shortestPaths = new HashMap<>();
        computeShortestPathsFromSource(node1, shortestPaths);
        return shortestPaths.get(node2);
    }

    public void computeShortestPathsFromSource(Node sourceNode, Map<Node, Integer> shortestDistances){
        Set<Node> checkedNeighbors = new HashSet<>();
        Set<Node> unvisited = new HashSet<>();

        this.initializeShortestDistances(shortestDistances);
        shortestDistances.put(sourceNode, 0);
        this.addAllNodes(unvisited);

        while (unvisited.size() > 0) {
            Node currentNode = getLowestDistanceNode(unvisited, shortestDistances);
            unvisited.remove(currentNode);
            for (Map.Entry <Node, Integer> adjacencyEntry: currentNode.costs.entrySet()) {
                Node adjacentNode = adjacencyEntry.getKey();
                Integer edgeCost = adjacencyEntry.getValue();
                if (!checkedNeighbors.contains(adjacentNode) && unvisited.contains(adjacentNode)) {
                    computeLowestDistance(adjacentNode, edgeCost, currentNode, shortestDistances);
                    checkedNeighbors.add(adjacentNode);
                }
            }
            nodes.forEach(checkedNeighbors::remove);
        }
    }

    private void initializeShortestDistances(Map<Node, Integer> shortestDistances) {
        for(Node node : nodes){
            shortestDistances.put(node, Integer.MAX_VALUE);
        }
    }

    private void addAllNodes(Set<Node> unvisited) {
        unvisited.addAll(nodes);
    }

    private Node getLowestDistanceNode(Set<Node> unvisited, Map<Node, Integer> shortestDistances) {
        int lowestDistance = Integer.MAX_VALUE;
        Node lowestDistanceNode = null;

        for (Node currentNode: unvisited) {
            int currentDistance = shortestDistances.get(currentNode);
            if (lowestDistance > currentDistance) {
                lowestDistance = currentDistance;
                lowestDistanceNode = currentNode;
            }
        }
        return lowestDistanceNode;
    }

    private void computeLowestDistance(Node currentNode, Integer edgeCost, Node sourceNode,
                                       Map<Node, Integer> shortestDistances) {
        int sourceDistance = shortestDistances.get(sourceNode);

        if (sourceDistance + edgeCost < shortestDistances.get(currentNode)) {
            shortestDistances.put(currentNode, sourceDistance + edgeCost);
        }
    }

    public void printAllShortestPaths(){
        for(Node sourceNode : this.getNodes()){
            for(Node destinationNode : this.getNodes()){
                printShortestPathBetweenNodes(sourceNode, destinationNode);
            }
        }
    }

    public void printShortestPathBetweenNodes(Node sourceNode, Node destinationNode){
        System.out.println("Shortest distance form node '" + sourceNode.getName() +
                "' to node '" + destinationNode.getName() + "' is: " +
                this.computeShortestPathBetween(sourceNode, destinationNode));
    }

    public void computeSafestPath(Node start, Node finish) {
        //modified bfs
        nullifyProbabilities();
        start.probabilityOfReachingNode = 1.0;
        List<Node> queue = new ArrayList();

        queue.add(start);

        while(!queue.isEmpty())
        {
            Node currentNode = queue.get(0);
            queue.remove(0);

            for (Node adjacent : currentNode.probabilityOfFailure.keySet())
            {
                if (improvesProbability(currentNode, adjacent))
                {
                    improveProbability(currentNode, adjacent);
                    if(!queue.contains(adjacent)) {
                        queue.add(adjacent);
                    }
                }
            }
            queue.remove(currentNode);
        }

        printSafestPath(start, finish);
    }

    private void printSafestPath(Node start, Node finish) {
        Node currentNode = finish;
        List<Node> path = new ArrayList<>();

        System.out.println("\nThe highest probability of arrival is " + finish.probabilityOfReachingNode);
        System.out.println("\nThis is the safest path from node " + start.getName()
                + ", to node " + finish.getName() + " is: \n");

        while(currentNode != start) {
            path.add(currentNode);

            for (Node adjacent : currentNode.probabilityOfFailure.keySet()) {
                Double probability = currentNode.probabilityOfReachingNode;
                Double probabilityOfEdgeSuccess = 1- currentNode.probabilityOfFailure.get(adjacent);
                Double adjacentProbability = adjacent.probabilityOfReachingNode;
                if(probability == probabilityOfEdgeSuccess * adjacentProbability) {
                    currentNode = adjacent;
                    break;
                }
            }
        }

        path.add(currentNode);

        for(int i = path.size()-1; i>=0; --i) {
            System.out.println(path.size()-i + ": " + path.get(i).getName() + ", prob of getting here: " + path.get(i).probabilityOfReachingNode);
        }
    }

    private void improveProbability(Node currentNode, Node adjacent) {
        Double edgeProbability = currentNode.probabilityOfFailure.get(adjacent);
        adjacent.probabilityOfReachingNode = currentNode.probabilityOfReachingNode * (1-edgeProbability);
    }

    private boolean improvesProbability(Node currentNode, Node adjacent) {
        Double edgeProbability = currentNode.probabilityOfFailure.get(adjacent);
        if(currentNode.probabilityOfReachingNode * (1-edgeProbability) > adjacent.probabilityOfReachingNode) {
            return true;
        }
        return false;
    }

    private void nullifyProbabilities() {
        for (Node node : nodes) {
            node.probabilityOfReachingNode = 0.0;
        }
    }
}













