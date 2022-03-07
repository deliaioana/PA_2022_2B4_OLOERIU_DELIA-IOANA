public class Switch extends Node {
    private int numberOfPorts;
    private int portsUsed;

    public Switch(String name, int numberOfPorts, int portsUsed, String location) {
        this.setName(name);
        this.setNumberOfPorts(numberOfPorts);
        this.setPortsUsed(portsUsed);
        this.setLocation(location);
    }

    public int getNumberOfPorts() {
        return numberOfPorts;
    }

    public void setNumberOfPorts(int numberOfPorts) {
        this.numberOfPorts = numberOfPorts;
    }

    public int getPortsUsed() {
        return portsUsed;
    }

    public void setPortsUsed(int portsUsed) {
        this.portsUsed = portsUsed;
    }

    @Override
    public String toString() {
        return "\nSwitch{" +
                "name=" + this.getName() +
                ", location=" + this.getLocation() +
                ", numberOfPorts=" + numberOfPorts +
                ", portsUsed=" + portsUsed +
                '}';
    }
}
