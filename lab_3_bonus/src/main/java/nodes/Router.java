package nodes;

import useful.Identifiable;

public class Router extends Node implements Identifiable {
    private String IP;
    private int numberOfAntennas;


    public Router(String IP, String name, int numberOfAntennas, String location) {
        this.setName(name);
        this.setIPAddress(IP);
        this.setNumberOfAntennas(numberOfAntennas);
        this.setLocation(location);
    }

    @Override
    public String getIPAddress() {
        return IP;
    }

    @Override
    public void setIPAddress(String IP) {
        this.IP = IP;
    }

    @Override
    public String toString() {
        return "\nRouter{" +
                "name=" + this.getName() +
                ", IP=" + IP +
                ", location=" + this.getLocation() +
                ", numberOfAntennas=" + numberOfAntennas +
                '}';
    }

    public int getNumberOfAntennas() {
        return numberOfAntennas;
    }

    public void setNumberOfAntennas(int numberOfAntennas) {
        this.numberOfAntennas = numberOfAntennas;
    }
}
