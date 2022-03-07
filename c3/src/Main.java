public class Main {

    public static void main(String[] args) {
	    Network network = new Network("Network 1");
        IPAddressGenerator generator = new IPAddressGenerator();

        Node v1 = new Computer(generator.randomIP(), "Computer A", 250, 100, "Center 1");
        Node v2 = new Router(generator.randomIP(), "Router A", 4, "Center 1");
        Node v3 = new Switch("Switch A", 10, 3, "Center 1");
        Node v4 = new Switch("Switch B", 15, 10, "Center 2");
        Node v5 = new Router(generator.randomIP(), "Router B", 5, "Center 2");
        Node v6 = new Computer(generator.randomIP(), "Computer B", 300, 150, "Center 2");

        network.addNode(v1);
        network.addNode(v2);
        network.addNode(v3);
        network.addNode(v4);
        network.addNode(v5);
        network.addNode(v6);

        System.out.println(network);

    }
}
