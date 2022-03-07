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

        System.out.println(network);

    }
}
