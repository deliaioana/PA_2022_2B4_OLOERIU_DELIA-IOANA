public class Computer extends Node implements Identifiable, Storage {

    private String IP;
    private int storageCapacity;
    private int storageUsed;

    public Computer(String IP, String name, int storageCapacity, int storageUsed, String location) {
        this.setName(name);
        this.setIPAddress(IP);
        this.setStorageCapacity(storageCapacity);
        this.setStorageUsed(storageUsed);
        this.setLocation(location);
    }

    public Computer() {
    }

    @Override
    public int getStorageCapacity() {
        return storageCapacity;
    }

    @Override
    public void setStorageCapacity(int storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    @Override
    public int getStorageUsed() {
        return storageUsed;
    }

    @Override
    public void setStorageUsed(int storageUsed) {
        this.storageUsed = storageUsed;
    }

    @Override
    public String getIPAddress() {
        return this.IP;
    }

    @Override
    public void setIPAddress(String IP) {
        this.IP = IP;
    }

    @Override
    public String toString() {
        return "\nComputer{" +
                "name=" + this.getName() +
                ", IP=" + IP +
                ", location=" + this.getLocation() +
                ", storageCapacity=" + storageCapacity +
                ", storageUsed=" + storageUsed +
                '}';
    }

    public long corvertCapacityUnit(String unit){
        return Storage.super.corvertCapacityUnit(unit);
    }
}
