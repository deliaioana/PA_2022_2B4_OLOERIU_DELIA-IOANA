import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NetworkTest {

    @Test
    void firstTest() {
        var network = new Network("name");
        IPAddressGenerator generator = new IPAddressGenerator();

        Node first = new Computer(generator.randomIP(), "Computer A", 250, 100, "Center 1");
        Node second = new Router(generator.randomIP(), "Router A", 4, "Center 1");

        network.addNode(first);
        network.addNode(second);

        first.addProbabilityOfFailure(second, 0.3);

        assertEquals(0.7, network.computeSafestPath(first, second));
    }

    @Test
    void secondTest() {
        Network network = new Network("Network 1");

        IPAddressGenerator generator = new IPAddressGenerator();

        Node first = new Computer(generator.randomIP(), "Computer A", 250, 100, "Center 1");
        Node second = new Router(generator.randomIP(), "Router A", 4, "Center 1");
        Node third = new Switch("Switch A", 10, 3, "Center 1");

        network.addNode(first);
        network.addNode(second);
        network.addNode(third);

        first.addProbabilityOfFailure(second, 0.5);
        first.addProbabilityOfFailure(third, 0.1);
        second.addProbabilityOfFailure(third, 0.1);

        assertEquals(0.81, network.computeSafestPath(first, second));
    }

    @Test
    void thirdTest() {
        Network network = new Network("Network 1");

        IPAddressGenerator generator = new IPAddressGenerator();

        Node first = new Computer(generator.randomIP(), "Computer A", 250, 100, "Center 1");
        Node second = new Router(generator.randomIP(), "Router A", 4, "Center 1");
        Node third = new Switch("Switch A", 10, 3, "Center 1");
        Node fourth = new Switch("Switch B", 15, 10, "Center 2");

        network.addNode(first);
        network.addNode(second);
        network.addNode(third);
        network.addNode(fourth);

        first.addProbabilityOfFailure(second, 0.3);
        second.addProbabilityOfFailure(third, 0.5);
        second.addProbabilityOfFailure(fourth, 0.1);
        third.addProbabilityOfFailure(fourth, 0.1);

        assertEquals(0.5670000000000001, network.computeSafestPath(first, third));
    }
}