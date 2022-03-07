public abstract class Node implements Comparable<Node>{
    private String name;
    private String location;

    public Node() {
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
}
