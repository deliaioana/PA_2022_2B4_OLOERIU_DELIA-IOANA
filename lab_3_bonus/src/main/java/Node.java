import java.util.HashMap;
import java.util.Map;

/**
 * This is the Node class
 */

public abstract class Node implements Comparable<Node>{
            /* Node implementation comment */
    private String name; // unique, used as an id
    private String location;
    public Map<Node, Integer> costs;
    public Map<Node, Double> probabilityOfFailure;
    public Double probabilityOfReachingNode = 0.0;

    public Node() {
        costs = new HashMap<>();
        probabilityOfFailure =  new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public int compareTo(Node node) {
        return this.getName().compareTo(node.getName());
    }

    /**
     *  This is a new method */
    public void addCost(Node node, Integer cost){
        this.costs.put(node, cost);
        if(!node.costs.containsKey(this))
            node.addCost(this, cost);
    }

    public StringBuilder getNodeCostsToString(){
        StringBuilder costsString = new StringBuilder();
        costsString.append(this.name).append(" COSTS: ");
        for (Map.Entry<Node,Integer> entry : costs.entrySet()){
            costsString.append(entry.getKey().name).append(" (").append(entry.getValue()).append("), ");
        }
        return costsString;
            //commented code
    }

    public void addProbabilityOfFailure(Node second, double probability) {
        probabilityOfFailure.put(second, probability);
        second.probabilityOfFailure.put(this, probability);
    }
}
