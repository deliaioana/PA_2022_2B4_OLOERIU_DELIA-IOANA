package useful;

import nodes.Node;

import java.util.Comparator;

public class NodeComparatorByID implements Comparator<Node> {
    public int compare(Node node1, Node node2)
    {
        return ((Identifiable) node1).getIPAddress().compareTo(((Identifiable) node2).getIPAddress());
    }
}
