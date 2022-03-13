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
            System.out.println(node.getName() + ", IP:" + ((Identifiable) node).getIPAddress());
        }
    }

    public int computeSwhortestPathBetween(Node node1, Node node2){
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
            for (Map.Entry < Node, Integer> adjacencyEntry: currentNode.costs.entrySet()) {
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

    private void computeLowestDistance(Node currentNode, Integer edgeWeigh, Node sourceNode,
                                       Map<Node, Integer> shortestDistances) {
        int sourceDistance = shortestDistances.get(sourceNode);

        if (sourceDistance + edgeWeigh < shortestDistances.get(currentNode)) {
            shortestDistances.put(currentNode, sourceDistance + edgeWeigh);
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
                this.computeSwhortestPathBetween(sourceNode, destinationNode));
    }
}
