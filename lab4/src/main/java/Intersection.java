import java.util.HashMap;
import java.util.Map;

public class Intersection {
    private String name;
    private Map<Intersection, Street> connections = new HashMap<>();

    public String getName() {
        return name;
    }

    public Intersection(){
    }

    public Intersection(String name){
        this.setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addConnection(Street street, Intersection intersection){
        this.connections.put(intersection, street);
    }

    @Override
    public String toString(){
        return "\nIntersection: " + name;
    }

    public Map<Intersection, Street> getConnections() {
        return connections;
    }

    public void setConnections(Map<Intersection, Street> connections) {
        this.connections = connections;
    }
}
