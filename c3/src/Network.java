import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Override
    public String toString() {
        return "Network{" +
                "name='" + name + '\'' +
                ", nodes=" + nodes +
                '}';
    }
}
