public class Main {

    public static void main(String[] args) {
	    Network network = new Network("Network 1");
        IPAddressGenerator generator = new IPAddressGenerator();

        Node node1 = new Computer(generator.randomIP(), "Computer A", 250, 100, "Center 1");
        Node node2 = new Router(generator.randomIP(), "Router A", 4, "Center 1");
        Node node3 = new Switch("Switch A", 10, 3, "Center 1");
        Node node4 = new Switch("Switch B", 15, 10, "Center 2");
        Node node5 = new Router(generator.randomIP(), "Router B", 5, "Center 2");
        Node node6 = new Computer(generator.randomIP(), "Computer B", 300, 150, "Center 2");

        network.addNode(node1);
        network.addNode(node2);
        network.addNode(node3);
        network.addNode(node4);
        network.addNode(node5);
        network.addNode(node6);

        node1.addCost(node2, 10);
        node1.addCost(node3, 50);
        node2.addCost(node3, 20);
        node2.addCost(node4, 20);
        node2.addCost(node5, 10);
        node3.addCost(node4, 20);
        node4.addCost(node5, 30);
        node4.addCost(node6, 10);
        node5.addCost(node6, 20);

        System.out.println("\n\n--------------------------------------------- First task ---------------------------------------------");
        System.out.println(network);

        System.out.println("\n\n--------------------------------------------- Second task ---------------------------------------------");
        System.out.println("Storage capacity in gb: " + ((Computer) node1).getStorageCapacity());
        System.out.println("Storage capacity in kb: " + ((Computer) node1).corvertCapacityUnit("kilobytes"));
        System.out.println("Storage capacity in mb: " + ((Computer) node1).corvertCapacityUnit("megabytes"));
        System.out.println("Storage capacity in b: " + ((Computer) node1).corvertCapacityUnit("bytes"));

        System.out.println("\n\n--------------------------------------------- Third task ---------------------------------------------");
        network.printIdentifiableNodes();

        System.out.println("\n\n--------------------------------------------- Fourth task ---------------------------------------------");
        network.printShortestPathBetweenNodes(node1, node3);
        network.printShortestPathBetweenNodes(node1, node6);
        network.printShortestPathBetweenNodes(node5, node3);

            //network.printAllShortestPaths();
    }
}
